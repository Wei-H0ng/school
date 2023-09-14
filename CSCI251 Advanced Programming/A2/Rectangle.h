#ifndef RECT_H
#define RECT_H

#include "ShapeTwoD.h"
#include "Point.h"
#include <string>
#include <iostream>
#include <vector>
using namespace std;


class Rectangle : public ShapeTwoD
{
    private:
        Point pointsArray[4];
        double length;
        double width;
    public:
        Rectangle(string name = "rectangle", bool containsWarpSpace = false, Point p1 = Point(0,0), Point p2 = Point(0,0), Point p3 = Point(0,0), Point p4 = Point(0,0));
        string toString();

        double computeArea();
        bool isPointInShape(int x, int y);
        bool isPointOnShape(int x, int y);

        double calculateDistance(Point p1, Point p2);
        double getLength();
        double getWidth();

        Point * getArray();
        void setLengthWidth();
        void setArea();
        
        void getPointsInOnshape();
        void getBindingPoints();
};

#endif