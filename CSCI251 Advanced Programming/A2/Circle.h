#ifndef CIRCLE_H
#define CIRCLE_H

#include "ShapeTwoD.h"
#include "Point.h"
#include <string>
#include <iostream>
#include <vector>
using namespace std;


class Circle : public ShapeTwoD
{ 
    private:
        Point pointsArray[4];
        double radius;
        Point center;
  
    public:
        Circle(string name = "Circle", bool containsWarpSpace = false, Point center = Point(0,0), double radius = 0.0);
        string toString();

        double computeArea();
        bool isPointInShape(int x, int y);
        bool isPointOnShape(int x, int y);

        double calculateDistance(Point p1, Point p2);
        double getRadius();

        Point * getArray();
        void setNSEW();
        void setArea();

        void getPointsInOnshape();
        void getBindingPoints();
};

#endif