#include "Square.h"
#include "Point.h"
#include <string>
#include <iostream>
#include <string>
#include <sstream>
#include <cmath>

Square::Square(string tname, bool tcontainsWarpSpace, Point p1, Point p2, Point p3, Point p4) : ShapeTwoD (tname, tcontainsWarpSpace)
{   
    pointsArray[0] = p1;
    pointsArray[1] = p2;
    pointsArray[2] = p3;
    pointsArray[3] = p4;
}

string Square::toString()
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
    << "\nLength : " << length
    << "\nArea : " << area << " units square"
    << "\nVertices : ";
    
    Point * ptr = this->getArray();
	for(int i = 0; i < 4; i++){
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

double Square::computeArea()
{
    return double(length*length);
}

bool Square::isPointInShape(int x, int y)
{    
    int x1 = pointsArray[0].getx();
    int x3 = pointsArray[2].getx();
    int y1 = pointsArray[0].gety();
    int y3 = pointsArray[2].gety();
    // if x and y is enclosed within the min and max values of the opposite points of the square
    if (x > min(x1, x3) && x < max(x1, x3) && y > min(y1, y3) && y < max(y1, y3)) {
        return true;
    }
    return false;
}

bool Square::isPointOnShape(int x, int y)
{   
    size_t numVertices = 4;
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

double Square::calculateDistance(Point p1, Point p2)
{   
    return sqrt((p2.getx() - p1.getx()) * (p2.getx() - p1.getx()) + (p2.gety() - p1.gety()) * (p2.gety() - p1.gety()));
}

double Square::getLength()
{
    return length;
}


Point * Square::getArray()  {
    return pointsArray;
}

void Square::setLength()
{
    double d1 = calculateDistance(pointsArray[0],pointsArray[1]);
    length = d1;
}

void Square::setArea(){
    area = computeArea();
}

void Square::getPointsInOnshape()
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

void Square::getBindingPoints()
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