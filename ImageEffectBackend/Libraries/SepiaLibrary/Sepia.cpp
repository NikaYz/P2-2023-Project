#include <iostream>
#include <cmath>
#include <algorithm>
#include <vector>
#include "libraryInterfaces_SepiaInterface.h"
#include "Sepia.h"
#include "../Pixel.h"
using namespace std;

vector< vector<Pixel> > changeSepia(vector< vector<Pixel> > imageVector){
    vector< vector<Pixel> > inputImage = imageVector;
    for(int i = 0 ; i < inputImage.size(); i++){
                    for(int j = 0 ; j < inputImage[0].size() ; j++){
                        //int g_value = (255-inputImage[i][j].getG());
                        int rvalue = static_cast<int>(0.393*inputImage[i][j].r + 0.769*inputImage[i][j].g + 0.189*inputImage[i][j].b);
                        //inputImage[i][j].setG(Math.min(255,rvalue));
                        //int r_value = (int)(Math.min(255,inputImage[i][j].getR()+(inputImage[i][j].getR())*amount/100));
                        int gvalue =static_cast<int>(0.349*inputImage[i][j].r + 0.686*inputImage[i][j].g + 0.168*inputImage[i][j].b);

                       // inputImage[i][j].setR(gvalue);
                        //int b_value = (int)(Math.min(255,inputImage[i][j].getB()+(inputImage[i][j].getB())*amount/100));
                        int bvalue =static_cast<int> (0.272*inputImage[i][j].r + 0.534*inputImage[i][j].g + 0.131*inputImage[i][j].b);
                        inputImage[i][j].r = min(255,rvalue);
                        inputImage[i][j].b = min(255,bvalue);
                        inputImage[i][j].g = min(255,gvalue);
                    }
                }
            return inputImage;
}