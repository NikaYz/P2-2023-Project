#include <iostream>
#include <cmath>
#include <algorithm>
#include <vector>
#include "libraryInterfaces_ContrastInterface.h"
#include "Contrast.h"
#include "../Pixel.h"
using namespace std;

vector< vector<Pixel> > changeContrast(vector< vector<Pixel> > imageVector , float amount){
    vector< vector<Pixel> > inputImage = imageVector;
    int row = inputImage.size();
    int col = inputImage[0].size();
    for(int i = 0 ; i < row ; i++){
          for(int j = 0 ; j < col ; j++){
               int g_value = max(0,static_cast<int>(((amount/100))*(inputImage[i][j].g-128)+128));
               inputImage[i][j].g = min(255,g_value);
               int r_value = max(0,static_cast<int>(((amount/100))*(inputImage[i][j].r-128)+128));
               inputImage[i][j].r = min(255,r_value);
               int b_value = max(0,static_cast<int>(((amount/100))*(inputImage[i][j].b-128)+128));
               inputImage[i][j].b = min(255,b_value);
          }
    }
    return inputImage;
}