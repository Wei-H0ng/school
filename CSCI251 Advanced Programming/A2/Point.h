
#ifndef POINT_H
#define POINT_H

#include <iostream>


using namespace std;

class Point
{
	public:
		Point (int a=0, int b=0);
		int getx() const;
		int gety() const;
		
	private:
		int x, y;

};

#endif
