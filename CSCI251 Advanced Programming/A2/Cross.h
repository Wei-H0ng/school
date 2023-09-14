#ifndef CROSS_H
#define CROSS_H

#include "ShapeTwoD.h"
#include "Point.h"
#include <string>
#include <iostream>
#include <vector>
using namespace std;


class Cross : public ShapeTwoD
{
    private:
        Point pointsArray[12];
    public:
        Cross(string name = "cross", bool containsWarpSpace = false, 
        Point p1 = Point(0,0), Point p2 = Point(0,0), Point p3 = Point(0,0), Point p4 = Point(0,0),
        Point p5 = Point(0,0), Point p6 = Point(0,0), Point p7 = Point(0,0), Point p8 = Point(0,0),
        Point p9 = Point(0,0), Point p10 = Point(0,0), Point p11 = Point(0,0), Point p12 = Point(0,0));
        string toString();

        double computeArea();
        bool isPointInShape(int x, int y);
        bool isPointOnShape(int x, int y);

        double calculateDistance(Point p1, Point p2);

        Point * getArray();
        void setArea();
        
        void getPointsInOnshape();
        void getBindingPoints();
};

#endif