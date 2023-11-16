#include <iostream>
#include <opencv2/opencv.hpp>

cv::Mat image;
cv::Point prevMousePos;
bool isDragging = false;

void onMouse(int event, int x, int y, int flags, void* userdata)
{
    if (event == cv::EVENT_LBUTTONDOWN)
    {
        isDragging = true;
        prevMousePos = cv::Point(x, y);
    }
    else if (event == cv::EVENT_LBUTTONUP)
    {
        isDragging = false;
    }
    else if (event == cv::EVENT_MOUSEMOVE && (flags & cv::EVENT_FLAG_LBUTTON))
    {
        if (isDragging)
        {
            cv::Point currMousePos(x, y);
            cv::Point delta = currMousePos - prevMousePos;

            // Define the translation matrix
            cv::Mat translationMatrix = (cv::Mat_<double>(2, 3) << 1, 0, delta.x, 0, 1, delta.y);

            // Apply the translation to the image
            cv::Mat translatedImage;
            cv::warpAffine(image, translatedImage, translationMatrix, image.size());

            // Display the translated image
            cv::imshow("Image", translatedImage);
        }
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
    cv::namedWindow("Image");
    cv::imshow("Image", image);

    // Set the mouse callback function
    cv::setMouseCallback("Image", onMouse);

    // Wait for a key press
    cv::waitKey(0);

    return 0;
}

