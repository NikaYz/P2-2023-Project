#include <iostream>
#include <cmath>
#include <algorithm>
#include <vector>
#include "libraryInterfaces_SharpenInterface.h"
#include "Sharpen.h"
#include "../Pixel.h"
using namespace std;
int clamp(float v) //define a function to bound and round the input float value to 0-255
{
    if (v < 0)
        return 0;
    if (v > 255)
        return 255;
    return static_cast<int>(v);
}
Pixel sharpenPixel(const Pixel& center, const Pixel& neighbor, double amount) {
    Pixel result;
    result.r = clamp( center.r + amount * (center.r - neighbor.r));
    result.g = clamp(center.g + amount * (center.g - neighbor.g));
    result.b = clamp(center.b + amount * (center.b - neighbor.b));
    return result;
}
vector< vector<Pixel> > changeSharpness(vector< vector<Pixel> > imageVector , float amount){
    vector< vector<Pixel> > inputImage = imageVector;

        int rows = inputImage.size();
            int cols = inputImage[0].size();

            // Create a temporary vector to store the sharpened image
            vector< vector<Pixel> > sharpened_image(rows, std::vector<Pixel>(cols));

            // Apply sharpening to each pixel in the image
            for (int i = 1; i < rows - 1; ++i) {
                for (int j = 1; j < cols - 1; ++j) {
                    sharpened_image[i][j] = sharpenPixel(
                        inputImage[i][j],
                        inputImage[i - 1][j - 1],
                        amount/50
                    );
                }
            }

            // Update the original image with the sharpened values
           inputImage = sharpened_image;
        return inputImage;
}