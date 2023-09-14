#include <string>
#include <iostream>
#include <string>
#include <sstream>
#include <cmath>
#include "Circle.h"
#include "Point.h"

Circle::Circle(string name, bool containsWarpSpace, Point tcenter, double tradius) : ShapeTwoD (name, containsWarpSpace)
{   
    radius = tradius;
    center = tcenter;
}

string Circle::toString()
{  
    string spacetype;
    if(containsWarpSpace){
        spacetype = "WS";
    }else{
        spacetype = "NS";
    }
    ostringstream oss;
    oss << "Name : " << name 
    << "\nSpecial Type : " << spacetype 
    << "\nRadius : " << radius
    << "\nArea : " << area << " units square"
    << "\nVertices : ";
    
	oss << "\nPoint ["<< 0 << "] : (" << center.getx() << ", " << center.gety() <<")";
    
    oss << "\n\nPoints on perimeter : ";

    for (Point i : pointsOnShape)
    {
        oss << "(" << i.getx() << ", " << i.gety() << ") ";
    }

    oss << "\n\nPoints wihtin shape : ";

    for (Point i : pointsInShape)
    {
        oss << "(" << i.getx() << ", " << i.gety() << ") ";
    }
    
    string s = oss.str();

    return s;
}

double Circle::computeArea()
{
    return double(M_PI* radius*radius);
}

bool Circle::isPointInShape(int x, int y)
{    
    double distance = calculateDistance(Point(x, y), center);
    if(distance > radius){
        return false;
    }else{
        return true;
    }
}

bool Circle::isPointOnShape(int x, int y)
{   
    if(center.getx() == x)
    {
        if (y == center.gety()+ radius || y == center.gety() - radius)
        {
            return true;
        }else{
            return false;
        }
    }
    if(center.gety() == y)
    {
        if (x == center.getx() + radius || x == center.getx() - radius)
        {
            return true;
        }else{
            return false;
        }
    }else{
        return false;
    }
}

double Circle::calculateDistance(Point p1, Point p2)
{   
    return sqrt((p2.getx() - p1.getx()) * (p2.getx() - p1.getx()) + (p2.gety() - p1.gety()) * (p2.gety() - p1.gety()));
}

double Circle::getRadius()
{
    return radius;
}


Point * Circle::getArray()  {
    return pointsArray;
}

void Circle::setNSEW()
{
    pointsArray[0] = Point(center.getx(), center.gety() + radius);
    pointsArray[1] = Point(center.getx(), center.gety() - radius);
    pointsArray[2] = Point(center.getx() + radius, center.gety());
    pointsArray[3] = Point(center.getx() - radius, center.gety());
}

void Circle::setArea(){
    area = computeArea();
}

void Circle::getPointsInOnshape()
{   
    for (int x = minBinding.getx(); x <= maxBinding.getx(); x++)
    {
        for (int y = minBinding.gety(); y <= maxBinding.gety(); y++)
        {   
            if (isPointOnShape(x,y))
            {   
                pointsOnShape.push_back(Point(x,y));
            }else if (isPointInShape(x,y))
            {   
                pointsInShape.push_back(Point(x,y));
            }else{
                continue;
            }
        }
    }
}

void Circle::getBindingPoints()
{
    int minX = pointsArray[0].getx();
    int maxX = pointsArray[0].getx();
    int minY = pointsArray[0].gety();
    int maxY = pointsArray[0].gety();

    for (const Point& point : pointsArray) {
        minX = std::min(minX, point.getx());
        maxX = std::max(maxX, point.getx());
        minY = std::min(minY, point.gety());
        maxY = std::max(maxY, point.gety());
    }

    minBinding = Point(minX,minY);
    maxBinding = Point(maxX,maxY);
}