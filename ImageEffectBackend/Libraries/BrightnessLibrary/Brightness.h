
#ifndef BRIGHTNESS_H
#define BRIGHTNESS_H
#include "../Pixel.h"
#include <vector>
using namespace std;
vector< vector<Pixel> > changeBrightness(vector< vector<Pixel> > imageVector , float amount);

#endif // BRIGHTNESS_H