
#include "ShapeTwoD.h"
#include <sstream>

using namespace std;

ShapeTwoD::ShapeTwoD(string tname, bool tcontainsWarpSpace)
{
    name = tname;
    containsWarpSpace = tcontainsWarpSpace;
}

string ShapeTwoD::getName()
{
    return name;
}

bool ShapeTwoD::getContainsWarpSpace()
{
    return containsWarpSpace;
}

double ShapeTwoD::getArea()
{
    return area;
}

string ShapeTwoD::toString()
{
    ostringstream oss;
    oss << "Name : " << name << "\nContainsWarpSpace : " << containsWarpSpace;
    string s = oss.str();
    return s;
}

void ShapeTwoD:: setName(string tname)
{
    name = tname;
}

void ShapeTwoD:: setContainsWarpSpace(bool tcontainsWarpSpace)
{
    containsWarpSpace = tcontainsWarpSpace;
}



