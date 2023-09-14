#ifndef SQUARE_H
#define SQUARE_H

#include "ShapeTwoD.h"
#include "Point.h"
#include <string>
#include <iostream>
#include <vector>
using namespace std;


class Square : public ShapeTwoD
{
    private:
        Point pointsArray[4];
        double length;

    public:
        Square(string name = "square", bool containsWarpSpace = false, Point p1 = Point(0,0), Point p2 = Point(0,0), Point p3 = Point(0,0), Point p4 = Point(0,0));
        string toString();

        double computeArea();
        bool isPointInShape(int x, int y);
        bool isPointOnShape(int x, int y);

        double calculateDistance(Point p1, Point p2);
        double getLength();

        Point * getArray();
        void setLength();
        void setArea();
        
        void getPointsInOnshape();
        void getBindingPoints();
};

#endif