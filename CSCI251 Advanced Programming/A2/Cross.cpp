#include "Cross.h"
#include "Point.h"
#include <string>
#include <iostream>
#include <string>
#include <sstream>
#include <cmath>

Cross::Cross(string tname, bool tcontainsWarpSpace, Point p1, Point p2, Point p3, Point p4, Point p5, Point p6, Point p7, Point p8, Point p9, Point p10, Point p11, Point p12) : ShapeTwoD (tname, tcontainsWarpSpace)
{   
    pointsArray[0] = p1;
    pointsArray[1] = p2;
    pointsArray[2] = p3;
    pointsArray[3] = p4;
    pointsArray[4] = p5;
    pointsArray[5] = p6;
    pointsArray[6] = p7;
    pointsArray[7] = p8;
    pointsArray[8] = p9;
    pointsArray[9] = p10;
    pointsArray[10] = p11;
    pointsArray[11] = p12;
}

string Cross::toString()
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
    << "\nArea : " << area << " units square"
    << "\nVertices : ";
    
    Point * ptr = this->getArray();
	for(int i = 0; i < 12; i++){
		oss << "\nPoint ["<< i << "] : (" << ptr[i].getx() << ", " << ptr[i].gety() <<")";
	};
    
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

double Cross::computeArea()
{
    double area = 0.0;
    size_t numVertices = 12;

    for (size_t i = 0; i < numVertices; ++i) {
        const Point& p1 = pointsArray[i];
        const Point& p2 = pointsArray[(i + 1) % numVertices]; // Next vertex

        area += (p1.getx() * p2.gety()) - (p2.getx() * p1.gety());
    }
    return std::abs(area)/2.0;
}

bool Cross::isPointInShape(int x, int y)
{   
    int numVertices = 12;
    int   i, j=numVertices-1 ;
    bool  oddNodes=false;

    for (int i = 0; i < numVertices; i++)
    {
        if (pointsArray[i].getx() == x && pointsArray[i].gety() == y)
        {
            return false;
        }
        
    }

    for (i=0; i<numVertices; i++) {
        if ((pointsArray[i].gety()< y && pointsArray[j].gety()>=y
        ||   pointsArray[j].gety()< y && pointsArray[i].gety()>=y)
        &&  (pointsArray[i].getx()<=x || pointsArray[j].getx()<=x)) {
        oddNodes^=(pointsArray[i].getx()+(y-pointsArray[i].gety())/(pointsArray[j].gety()-pointsArray[i].gety())*(pointsArray[j].getx()-pointsArray[i].getx())<x); }
        j=i; }

    return oddNodes;
}

bool Cross::isPointOnShape(int x, int y)
{
    size_t numVertices = 12;

    for (int i = 0; i < numVertices; i++)
    {
        if (pointsArray[i].getx() == x && pointsArray[i].gety() == y)
        {
            return false;
        }
        
    }


    for (size_t i = 0; i < numVertices; ++i) {
        size_t nextIndex = (i + 1) % numVertices;

        const Point& vertex1 = pointsArray[i];
        const Point& vertex2 = pointsArray[nextIndex];

        // Check if the point is collinear with the edge formed by vertex1 and vertex2
        if ((x - vertex1.getx()) * (vertex2.gety() - vertex1.gety()) == 
            (y - vertex1.gety()) * (vertex2.getx() - vertex1.getx())) {
            
            // Check if the point lies within the range of the edge
            if (x >= std::min(vertex1.getx(), vertex2.getx()) && 
                x <= std::max(vertex1.getx(), vertex2.getx()) &&
                y >= std::min(vertex1.gety(), vertex2.gety()) &&
                y <= std::max(vertex1.gety(), vertex2.gety())) {
                return true;
            }
        }
    }

    return false;
}

double Cross::calculateDistance(Point p1, Point p2)
{   
    return sqrt((p2.getx() - p1.getx()) * (p2.getx() - p1.getx()) + (p2.gety() - p1.gety()) * (p2.gety() - p1.gety()));
}


Point * Cross::getArray()  {
    return pointsArray;
}

void Cross::setArea(){
    area = computeArea();
}

void Cross::getPointsInOnshape()
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

void Cross::getBindingPoints()
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