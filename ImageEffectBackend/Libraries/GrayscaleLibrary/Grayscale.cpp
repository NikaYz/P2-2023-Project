#include <iostream>
#include <cmath>
#include <algorithm>
#include <vector>
#include "libraryInterfaces_GrayscaleInterface.h"
#include "Grayscale.h"
#include "../Pixel.h"
using namespace std;

vector< vector<Pixel> > changeGrayscale(vector< vector<Pixel> > imageVector ){
    vector< vector<Pixel> > inputImage = imageVector;
    int row = inputImage.size();
    int col = inputImage[0].size();
    for(int i = 0 ; i < row ; i++){
         for(int j = 0 ; j < col ; j++){
             int value =static_cast<int> (0.2126*inputImage[i][j].r + 0.7152*inputImage[i][j].g + 0.0722*inputImage[i][j].b);
             inputImage[i][j].g = value;
             inputImage[i][j].r = value;
             inputImage[i][j].b = value;
         }
    }
    return inputImage;
}