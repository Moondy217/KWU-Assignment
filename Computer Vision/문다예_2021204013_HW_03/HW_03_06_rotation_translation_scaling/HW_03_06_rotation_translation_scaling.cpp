#include <iostream>
#include <opencv2/opencv.hpp>

cv::Mat image;
cv::Mat zoomedImage;
cv::Point zoomCenter;
double zoomFactor = 1.0;
double rotationAngle = 0.0;
cv::Point prevMousePos;
bool isDragging = false;

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

        // Calculate the zoom center position based on the mouse position
        zoomCenter.x = x;
        zoomCenter.y = y;
    }
    else if (event == cv::EVENT_RBUTTONDOWN)
    {
        isDragging = true;
        prevMousePos = cv::Point(x, y);
    }
    else if (event == cv::EVENT_RBUTTONUP)
    {
        isDragging = false;
    }
    else if (event == cv::EVENT_MOUSEMOVE && (flags & cv::EVENT_FLAG_RBUTTON))
    {
        if (isDragging)
        {
            cv::Point currMousePos(x, y);
            cv::Point delta = currMousePos - prevMousePos;

            // Update the rotation angle based on the mouse movement
            rotationAngle += static_cast<double>(delta.x) * 0.5;

            prevMousePos = currMousePos;
        }
    }

    else if (event == cv::EVENT_LBUTTONDOWN)
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

            // Update the zoom center based on the mouse movement
            zoomCenter += delta;
            prevMousePos = currMousePos;
        }
    }


    // Perform zoom and rotation transformations
    cv::Mat zoomMatrix = cv::getRotationMatrix2D(zoomCenter, rotationAngle, zoomFactor);
    cv::warpAffine(image, zoomedImage, zoomMatrix, image.size());

    // Display the zoomed and rotated image
    cv::imshow("Zoomed Image", zoomedImage);
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
