#include <iostream>
#include <cmath>
#include <algorithm>
#include <vector>
#include "libraryInterfaces_RotationInterface.h"
#include "Rotation.h"
#include "../Pixel.h"
using namespace std;

vector< vector<Pixel> > changeRotation(vector< vector<Pixel> > imageVector , int amount){
    for(int l = 0 ; l < amount ; l++){
         vector< vector<Pixel> > inputImage(imageVector[0].size()) ;
         int row = imageVector.size();
         int col = imageVector[0].size();
         for(int i = 0 ; i < col ; i++){
              for(int j = 0 ; j < row ; j++){
                  inputImage[i].push_back(imageVector[j][imageVector[0].size()-i-1]);
              }

         }
         imageVector = inputImage;
    }

    return imageVector;
}