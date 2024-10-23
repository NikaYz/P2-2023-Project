#include <iostream>
#include <cmath>
#include <algorithm>
#include <vector>
#include "libraryInterfaces_BrightnessInterface.h"
#include "Brightness.h"
#include "../Pixel.h"
using namespace std;

vector< vector<Pixel> > changeBrightness(vector< vector<Pixel> > imageVector , float amount){
    vector< vector<Pixel> > inputImage = imageVector;
    int row = inputImage.size();
    int col = inputImage[0].size();
    for(int i = 0 ; i < row ; i++){
           for(int j = 0 ; j < col ; j++){
                int g_value = static_cast<int>(min(static_cast<float>(255),inputImage[i][j].g+(inputImage[i][j].g)*amount/100));
                inputImage[i][j].g = g_value;
                int r_value = static_cast<int>(min(static_cast<float>(255),inputImage[i][j].r+(inputImage[i][j].r)*amount/100));
                inputImage[i][j].r = r_value;
                int b_value = static_cast<int>(min(static_cast<float>(255),inputImage[i][j].b+(inputImage[i][j].b)*amount/100));
                inputImage[i][j].b = b_value;

           }
    }
    return inputImage;
}