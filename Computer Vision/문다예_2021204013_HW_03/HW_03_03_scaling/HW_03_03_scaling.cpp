#include <iostream>
#include <opencv2/opencv.hpp>

cv::Mat image;
cv::Mat zoomedImage;
cv::Point zoomCenter;
double zoomFactor = 1.0;

void onMouse(int event, int x, int y, int flags, void* userdata)
{
    if (event == cv::EVENT_MOUSEWHEEL)
    {
        int delta = cv::getMouseWheelDelta(flags);

        if (delta > 0)
        {
            // Zoom in
            zoomFactor *= 1.2;
        }
        else if (delta < 0)
        {
            // Zoom out
            zoomFactor *= 0.8;
        }

        // Perform zoom around the mouse cursor
        cv::Mat zoomMatrix = cv::getRotationMatrix2D(zoomCenter, 0, zoomFactor);
        cv::warpAffine(image, zoomedImage, zoomMatrix, image.size());

        // Display the zoomed image
        cv::imshow("Zoomed Image", zoomedImage);
    }
}

int main()
{
    // Load the image
    image = cv::imread("messi5.jpg");
    if (image.empty())
    {
        std::cout << "Failed to load image!" << std::endl;
        return -1;
    }

    // Create a window and display the image
    cv::namedWindow("Zoomed Image");
    cv::imshow("Zoomed Image", image);

    // Set the mouse callback function
    cv::setMouseCallback("Zoomed Image", onMouse);

    // Set the initial zoom center to the center of the image
    zoomCenter = cv::Point(image.cols / 2, image.rows / 2);

    // Wait for a key press
    cv::waitKey(0);

    return 0;
}
