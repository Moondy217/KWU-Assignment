cmake_minimum_required(VERSION 3.0.0)
project(Top)

# OpenCV 4.7.0
set(OpenCV_DIR ${CMAKE_SOURCE_DIR}/third_party/opencv/build)
set(OpenCV_STATIC_LIB  ${OpenCV_DIR}/x64/vc16/lib/opencv_world470.lib)
set(OpenCV_DYNAMIC_LIB ${OpenCV_DIR}/x64/vc16/bin/opencv_world470.dll)
include_directories(${OpenCV_DIR}/include)

# Data
set(DATA_DIR ${CMAKE_SOURCE_DIR}/data)

# Projects
add_subdirectory("${CMAKE_SOURCE_DIR}/HW_01_01_draw_rectangles")
add_subdirectory("${CMAKE_SOURCE_DIR}/HW_01_02_draw_rectangles_correctly")
add_subdirectory("${CMAKE_SOURCE_DIR}/HW_01_03_draw_rectangles_and_ellipses")
add_subdirectory("${CMAKE_SOURCE_DIR}/HW_01_04_draw_rectangles_ellipses_and_brush")

# Homeworks
#add_subdirectory("${CMAKE_SOURCE_DIR}/project/81_homework_01")
#add_subdirectory("${CMAKE_SOURCE_DIR}/project/82_homework_02")