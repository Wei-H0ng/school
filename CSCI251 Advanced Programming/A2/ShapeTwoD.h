
#ifndef SHAPE_H
#define SHAPE_H

#include <iostream>
#include "Point.h"
#include <vector>

using namespace std;

class ShapeTwoD
{
	public:
		ShapeTwoD(string name = "", bool containsWarpSpace = false);
        string getName();
        bool getContainsWarpSpace();
        double getArea();
        
        void setName(string name);
        void setContainsWarpSpace(bool containsWarpSpace);

        virtual string toString();
        virtual double computeArea() = 0;
        virtual bool isPointInShape(int x, int y) = 0;
        virtual bool isPointOnShape(int x, int y) = 0;
        virtual void setArea() = 0;
        
        void getPointsInOnshape();
        void getBindingPoints();

	protected:
		string name;
		bool containsWarpSpace;
        double area;
        vector<Point> pointsInShape;
        vector<Point> pointsOnShape;
        Point minBinding;
        Point maxBinding;

};

#endif



