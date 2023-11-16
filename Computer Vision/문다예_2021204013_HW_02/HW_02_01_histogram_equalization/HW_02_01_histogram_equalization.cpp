#include <iostream>
using namespace std;

#include <opencv2/opencv.hpp>
using namespace cv;

int main()
{
    // Load a grayscale image
    Mat srcImage = imread("lena.jpg");

    // Check if image loading is successful
    if (srcImage.empty())
    {
        cout << "Error: No image exists!" << endl;
        exit(-1);
    }

    // Split BGR channels
    vector<Mat> BGR_planes;
    split(srcImage, BGR_planes);

    // void calcHist(const Mat *images, int nimages, const int *channels,
	//               InputArray mask, OutputArray hist,
    //               int dims, const int *histSize, const float **ranges,
    //               bool uniform = true, bool accumulate = false)

    // Create the output histogram
    vector<Mat> hists(3);

    // The number of bins
    int histSize = 256; // for every intensity

    // The value ranges
    float range[] = {0, 256}; // the upper boundary is exclusive
    const float* ranges[] = {range};

    // Calculate the image histogram
    for (int c = 0; c < 3; c++)
    {
        calcHist(&BGR_planes[c], 1, nullptr, noArray(), // do not use mask 
                 hists[c], 1, &histSize, ranges, 
                 true, // the histogram is uniform
                 false // do not accumulate
                 );

        // Equalize the histogram for the current channel **
        equalizeHist(BGR_planes[c], BGR_planes[c]);
    }

    // Merge the equalized BGR channels **
    Mat eqImage;
    merge(BGR_planes, eqImage);

    // Calculate the cumulative histogram for each channel **
    vector<Mat> cumHists(3);
    for (int c = 0; c < 3; c++)
    {
        cumHists[c] = Mat::zeros(Size(histSize, 1), CV_32FC1);
        float* cumHistPtr = cumHists[c].ptr<float>();
        for (int b = 0; b < histSize; b++)
        {
            if (b == 0)
            {
                cumHistPtr[b] = hists[c].at<float>(b);
            }
            else
            {
                cumHistPtr[b] = cumHistPtr[b-1] + hists[c].at<float>(b);
            }
        }
        normalize(cumHists[c], cumHists[c], 0, 1, NORM_MINMAX, -1, noArray());
    }


    // Histogram image
    int hist_w = 512, hist_h = 400;
    int bin_w = cvRound((double) hist_w/histSize);
    Mat histImage(hist_h, hist_w, CV_8UC3, Scalar(255, 255, 255));

    // For each channel
    for (int c = 0; c < 3; c++)
    {
        // Normalize the histogram in [0, hist_h]
        normalize(hists[c], hists[c], 0, hist_h, NORM_MINMAX, -1, noArray());

        // Plot the histogram
        Scalar color(c==0?255:0, c==1?255:0, c==2?255:0);
        for(int b = 1; b < histSize; b++)
        {
            line(histImage, Point(bin_w*(b-1), hist_h - cvRound(hists[c].at<float>(b-1))),
                            Point(bin_w*(b),   hist_h - cvRound(hists[c].at<float>(b))),
                            color, 2);

        }
    }

    // Merge the equalized channels **
    Mat equalizedImage;
    merge(BGR_planes, equalizedImage);

    // Calculate the cumulative histogram for the equalized image
    Mat cumHist;
    for (int c = 0; c < 3; c++)
    {
        calcHist(&BGR_planes[c], 1, nullptr, noArray(),
            cumHists[c], 1, &histSize, ranges,
            true, true); // accumulate the histogram
        cumHists[c].copyTo(cumHist);
        normalize(cumHist, cumHist, 0, hist_h, NORM_MINMAX, -1, noArray());
    }

    // Display the equalized image and its cumulative histogram
    imshow("Equalized Image", equalizedImage);
    Mat cumHistImage(hist_h, hist_w, CV_8UC3, Scalar(255, 255, 255));
    for (int c = 0; c < 3; c++)
    {
        Scalar color(c==0?255:0, c==1?255:0, c==2?255:0);
        for(int b = 1; b < histSize; b++)
        {
            line(cumHistImage, Point(bin_w*(b-1), hist_h - cvRound(cumHist.at<float>(b-1))),
                        Point(bin_w*(b),   hist_h - cvRound(cumHist.at<float>(b))),
                        color, 2);
        }
    }
    
    imshow("Source Image", srcImage);
    imshow("Histogram", histImage);
    imshow("Cumulative Histogram", cumHistImage);
    waitKey();

    // Destroy all windows
    destroyAllWindows();

    return 0;
}
