
#include "ShapeTwoD.h"
#include "Point.h"

//test function that populates the array
void populateArray(ShapeTwoD *(&array)[], int &index);

//matches 2 strings
bool strMatch(const string& a, const string& b);

//displays the main menu
int menu();

//displays the sorting menu (option4)
int sortMenu();

//prints all shapes in array
void printReport(ShapeTwoD *(&array)[], int index);

//gets x set(s) of vertices from user input and returns them in a vector
vector<Point> getPoint(const int &pointCount, bool isCircle);

//gets all required attributes from the user and creates shape
void createShape(ShapeTwoD *(&array)[], int &index);

//computes the area of all shapes in array
void computeArea(ShapeTwoD *array[], int index);

//compare(area in ascending) function for std::sort
bool compareAreaAscending(ShapeTwoD * shape1, ShapeTwoD *shape2);

//compare(area in descending) function for std::sort
bool compareAreaDescending(ShapeTwoD * shape1, ShapeTwoD *shape2);

//compare(space type then area in descending) function for std::sort
bool compareBySpaceDescending(ShapeTwoD * shape1, ShapeTwoD *shape2);