#include <iostream>
#include <cmath>
#include <algorithm>
#include <vector>
#include "libraryInterfaces_GaussianBlurInterface.h"
#include "GaussianBlur.h"
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
Pixel applyGaussianBlur(const vector< vector<Pixel> >& imageVector, int x, int y) {
    //int size = static_cast<int>(2 * radius) + 1;
    Pixel blurredPixel;
    blurredPixel.r =0;
    blurredPixel.g = 0;
    blurredPixel.b = 0;
    int sum = 0;
    int f[][3] = {{1, 2, 1}, {2, 4, 2}, {1, 2, 1}};

    for (int i = -1; i <= 1; ++i) {
        for (int j = -1; j <= 1; ++j) {
            int neighborX = x + i;
            int neighborY = y + j;

            if (neighborX >= 0 && neighborX < imageVector.size() && neighborY >= 0 && neighborY < imageVector[0].size()) {
                sum += f[i+1][j+1];
                blurredPixel.r =(blurredPixel.r + f[i+1][j+1] * imageVector[neighborX][neighborY].r);
                blurredPixel.g =(blurredPixel.g + f[i+1][j+1]* imageVector[neighborX][neighborY].g);
                blurredPixel.b =(blurredPixel.b + f[i+1][j+1] * imageVector[neighborX][neighborY].b);
            }
        }
    }

    blurredPixel.r = clamp(blurredPixel.r/sum);
    blurredPixel.g = clamp(blurredPixel.g/sum);
    blurredPixel.b = clamp(blurredPixel.b/sum);

    return blurredPixel;
    }

vector< vector<Pixel> > changeGaussianBlur(vector< vector<Pixel> > imageVector , float radius){
     radius = (radius/10);
     vector< vector<Pixel> > blurredImage = imageVector;
     for(float a = 0 ; a < radius ; a+=0.5){
        for (size_t i = 0; i < imageVector.size(); ++i) {
            for (size_t j = 0; j < imageVector[i].size(); ++j) {
                blurredImage[i][j] = applyGaussianBlur(imageVector, static_cast<int>(i), static_cast<int>(j));
            }
        }
        imageVector = blurredImage;
     }

     return blurredImage;
}