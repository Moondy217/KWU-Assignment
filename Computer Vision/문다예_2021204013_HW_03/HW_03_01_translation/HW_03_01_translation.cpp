#include <iostream>
#include <opencv2/opencv.hpp>

cv::Mat image;
cv::Mat translatedImage;
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

            // Compute the translation bounds
            int translationLeft = std::max(0, std::min(delta.x, image.cols - 1));
            int translationTop = std::max(0, std::min(delta.y, image.rows - 1));
            int translationRight = std::max(0, std::min(delta.x + image.cols, image.cols - 1));
            int translationBottom = std::max(0, std::min(delta.y + image.rows, image.rows - 1));

            // Define the translation matrix
            cv::Mat translationMatrix = (cv::Mat_<double>(2, 3) << 1, 0, delta.x, 0, 1, delta.y);

            // Create a canvas to hold the translated image
            translatedImage = cv::Mat::zeros(image.size(), image.type());

            // Copy the visible portion of the original image to the translated image
            cv::Rect visibleRegion = cv::Rect(translationLeft, translationTop, translationRight - translationLeft, translationBottom - translationTop);
            cv::Rect imageRegion = cv::Rect(-delta.x, -delta.y, image.cols, image.rows);
            cv::Mat visibleImage = image(imageRegion & visibleRegion);

            // Compute the translated region within the canvas
            cv::Rect translatedRegion = cv::Rect(0, 0, visibleImage.cols, visibleImage.rows);

            // Paste the visible image onto the translated region
            visibleImage.copyTo(translatedImage(translatedRegion));

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
