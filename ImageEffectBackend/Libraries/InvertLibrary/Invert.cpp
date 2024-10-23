#include <iostream>
#include <cmath>
#include <algorithm>
#include <vector>
#include "libraryInterfaces_InvertInterface.h"
#include "Invert.h"
#include "../Pixel.h"
using namespace std;

vector< vector<Pixel> > changeInvert(vector< vector<Pixel> > imageVector ){
    vector< vector<Pixel> > inputImage = imageVector;
    int row = inputImage.size();
    int col = inputImage[0].size();
    for(int i = 0 ; i < row ; i++){
           for(int j = 0 ; j < col ; j++){
                    inputImage[i][j].g = (255-inputImage[i][j].g);
                    inputImage[i][j].r = (255-inputImage[i][j].r);
                    inputImage[i][j].b = (255-inputImage[i][j].b);
          }
    }
    return inputImage;
}