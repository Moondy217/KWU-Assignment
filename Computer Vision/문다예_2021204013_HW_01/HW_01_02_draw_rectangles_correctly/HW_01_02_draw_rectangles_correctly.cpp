#include <iostream>
using namespace std;

#include <opencv2/opencv.hpp>
using namespace cv;

// Global variables
Mat    g_imgColor;
bool   g_isMousePressed = false;
int    g_mouseStartX = -1;
int    g_mouseStartY = -1;
Scalar color;

// OpenCV Random Number Generator
RNG g_rng(getTickCount());
Scalar randomColor(RNG& rng)
{
    int icolor = (unsigned)rng;
    return Scalar(icolor & 255, (icolor >> 8) & 255, (icolor >> 16) & 255);
}

// Mouse callback function
void mouse_callback(int event, int x, int y, int flags, void* param)
{
    // Left button pressed
    if (event == EVENT_LBUTTONDOWN)
    {
        // Flag on
        g_isMousePressed = true;

        // Record the mouse position
        g_mouseStartX = x;
        g_mouseStartY = y;

        // Pick a random color
        color = randomColor(g_rng);
    }

    // Mouse move with left button pressed
    if (event == EVENT_MOUSEMOVE && g_isMousePressed)
    {
        // Create a copy of the image
        Mat imgTemp = g_imgColor.clone();

        // Draw the rectangle on the temp image
        rectangle(imgTemp, Point(g_mouseStartX, g_mouseStartY), Point(x, y), color, -1);

        // Show the temp image
        imshow("Mouse Events", imgTemp);
    }

    // Left button released
    if (event == EVENT_LBUTTONUP)
    {
        // Flag off
        g_isMousePressed = false;

        // Draw the rectangle on the original image
        rectangle(g_imgColor, Point(g_mouseStartX, g_mouseStartY), Point(x, y), color, -1);
    }
}

int main()
{
    // Create a black image
    const int rows = 480;
    const int cols = 640;
    g_imgColor = Mat::zeros(rows, cols, CV_8UC3);

    // Create a window
    String strWindowName = "Mouse Events";
    namedWindow(strWindowName);

    // Register the mouse callback function
    setMouseCallback(strWindowName, mouse_callback);

    // Infinite loop
    while (true)
    {
        // Display the image
        imshow(strWindowName, g_imgColor);

        // Get user input
        char key = waitKey(1);

        // ESC
        if (key == 27)
        {
            break;
        }
    }

    // Destroy all windows
    destroyAllWindows();

    return 0;
}