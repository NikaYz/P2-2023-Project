
#ifndef GAUSSIANBLUR_H
#define GAUSSIANBLUR_H
#include "../Pixel.h"
#include <vector>
using namespace std;
vector< vector<Pixel> > changeGaussianBlur(vector< vector<Pixel> > imageVector , float radius);
#endif // FLIP_H