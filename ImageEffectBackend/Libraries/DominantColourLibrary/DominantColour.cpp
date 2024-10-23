#include <iostream>
#include <vector>
#include <unordered_map>

#include "libraryInterfaces_DominantColourInterface.h"
#include "DominantColour.h"
#include "../Pixel.h"

using namespace std;

struct Hash {
    std::size_t operator()(const Pixel& pixel) const {
        std::hash<int> hasher;
        return hasher(pixel.r) ^ hasher(pixel.g) ^ hasher(pixel.b);
    }
};


struct Equal {
    bool operator()(const Pixel& pixel1, const Pixel& pixel2) const {
        return pixel1.r == pixel2.r &&
               pixel1.g == pixel2.g &&
               pixel1.b == pixel2.b;
    }
};

vector< vector < Pixel > > applyDominantColour(vector < vector < Pixel > > & imageVector) {
    unordered_map<Pixel, int, Hash, Equal> pixelCountMap;

    for (const auto& row : imageVector) {
        for (const auto& pixel : row) {
            pixelCountMap[pixel]++;
        }
    }

    // Find the pixel with the highest count
    Pixel mostFrequentPixel = imageVector[0][0]; // Default to the first pixel
    int maxCount = 0;

    for (const auto& entry : pixelCountMap) {
        if (entry.second > maxCount) {
            mostFrequentPixel = entry.first;
            maxCount = entry.second;
        }
    }

    // Replace all pixels with the most frequent pixel
    for (int i = 0; i < imageVector.size(); i++) {
        for (int j = 0; j < imageVector[0].size(); j++) {
            imageVector[i][j] = mostFrequentPixel;
        }
    }

    return imageVector;
}


