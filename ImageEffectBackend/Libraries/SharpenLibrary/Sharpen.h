
#ifndef SHARPEN_H
#define SHARPEN_H
#include "../Pixel.h"
#include <vector>
using namespace std;
vector< vector<Pixel> > changeSharpness(vector< vector<Pixel> > imageVector , float amount);

#endif // SHARPEN_H