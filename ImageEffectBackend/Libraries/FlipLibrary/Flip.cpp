#include <iostream>
#include <cmath>
#include <algorithm>
#include <vector>
#include "libraryInterfaces_FlipInterface.h"
#include "Flip.h"
#include "../Pixel.h"
using namespace std;

vector< vector<Pixel> > changeFlip(vector< vector<Pixel> > imageVector ,  int horizontalFlipValue, int verticalFlipValue){
    vector< vector<Pixel> > inputImage = imageVector;
    int row = inputImage.size();
    int col = inputImage[0].size();
    if(horizontalFlipValue == 1){
          for(int i = 0 ; i < row; i++) {
               for (int j = 0; j < col/2; j++) {
                     Pixel p = inputImage[i][j];
                     inputImage[i][j] = inputImage[i][inputImage[0].size()-j-1];
                     inputImage[i][inputImage[0].size()-j-1] = p;
               }
          }
    }

    if(verticalFlipValue == 1){
            for(int i = 0 ; i < row/2 ; i++) {
                for (int j = 0; j < col; j++) {
                       Pixel p = inputImage[i][j];
                       inputImage[i][j] = inputImage[inputImage.size()-i-1][j];
                       inputImage[inputImage.size()-i-1][j] = p;
                }
            }
    }

    return inputImage;
}