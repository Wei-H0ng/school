

// g++ ShapeTwoD.cpp Cross.cpp Circle.cpp Rectangle.cpp Point.cpp Square.cpp Assn2.cpp -o a2.exe


#include "ShapeTwoD.h"
#include "Point.h"
#include "Square.h"
#include "Rectangle.h"
#include "Circle.h"
#include "Cross.h"
#include "Assn.h"
#include <limits>
#include <vector>
#include <algorithm>

int main()
{
	ShapeTwoD *shapesArray [100];
	
	int index = 0;
	bool flag = true;

	//populateArray(shapesArray,index);

	do
	{
		int option = menu();
		switch (option)
		{
		case 1:{
			createShape(shapesArray,index);
		}
			break;
		case 2:{
			computeArea(shapesArray, index);
		}
			break;
		case 3:{
			printReport(shapesArray, index);
		}
			break;
		case 4:{
			int sortOption = sortMenu();
			std::cout << endl;
			switch (sortOption) 
			{
			case 1:
				std::cout << "Sort by area (smallest to largest)..." << endl;
				std::sort(shapesArray, shapesArray + index, compareAreaAscending);
				break;
			case 2:
				std::cout << "Sort by area (largest to smallest)..." << endl;
				std::sort(shapesArray,shapesArray + index, compareAreaDescending);
				break;
			case 3:
				std::cout << "Sort by area (Special type, largest to smallest)..." << endl;
				std::sort(shapesArray,shapesArray + index, compareBySpaceDescending);
				break;
			default:
				break;
			}
			std::cout << endl;
			printReport(shapesArray,index);
		}
			break;
		
		default:
			flag = false;
			break;
		}
	} while (flag);
	
	std::cout << "Thank You!" << endl;
	return 0;
}

void populateArray(ShapeTwoD *(&array)[], int &index)
{
	Point p1(1,1);
	Point p2(1,3);
	Point p3(3,3);
	Point p4(3,1);

	Square *sq = new Square("sq1", true,p1,p2,p3,p4);

	Rectangle *rec1 = new Rectangle("rec1", false, Point(5,7), Point(5,10), Point(10,10), Point(10,7));

	Circle *cir= new Circle("cir1", true, Point(15,15), 4.0);

	Cross *cross = new Cross("cross", false, Point(1,8), Point(1,11), Point(3,11), Point(3,14), Point(6,14), Point(6,10), Point(10,10), Point(10,9), Point(5,9), Point(5,7), Point(2,7), Point(2, 8));
	
	sq->setLength();
	sq->getBindingPoints();
	sq->getPointsInOnshape();

	rec1->setLengthWidth();
	rec1->getBindingPoints();
	rec1->getPointsInOnshape();

	cir->setNSEW();
	cir->getBindingPoints();
	cir->getPointsInOnshape();

	cross->getBindingPoints();
	cross->getPointsInOnshape();


	array[0] = sq;
	array[1] = rec1;
	array[2] = cir;
	array[3] = cross;

	index = 4;
}

bool strMatch(const string& a, const string& b)
{
    if (a.length() != b.length()) {
        return false;
    }
    for (size_t i = 0; i < a.length(); ++i) {
        if (std::tolower(a[i]) != std::tolower(b[i])) {
            return false;
        }
    }
    return true;
}

int menu()
{
	int option = 0;

	std::cout << "Student ID \t: 7911130"<< endl;
    std::cout << "Student Name\t: Chew Wei Hong"<< endl;
    std::cout << "----------------------------------------" << endl;
    std::cout << "Welcome to Assn2 Program" << endl;
    std::cout << endl;
    std::cout << "1)\t Input sensor data" << endl;
    std::cout << "2)\t Compute area (for all records)" << endl;
    std::cout << "3)\t Print shapes report" << endl;
    std::cout << "4)\t Sort shape data" << endl;
	std::cout << "5)\t Exit Program" << endl;
	std::cout << endl;
	std::cout << "Please enter your choice : ";
	while (true) {
        try {
            if (!(cin >> option)) {
                throw runtime_error("Invalid input. Please enter a valid integer.");
            }
            if (option > 5 || option < 1) { 
                throw runtime_error("Invalid input. Please enter a valid integer.");
            }
            //break out if input is valid
            std::cout << endl;
            break;
        } catch (exception& e) {
            // Clear the input stream and ignore the invalid input
            std::cin.clear();
            std::cin.ignore(std::numeric_limits<std::streamsize>::max(), '\n');
            std::cout << e.what() << std::endl;
            std::cout << "Please enter corresponding option : ";
        }
    }
    return option;
}

int sortMenu()
{
	
	string option;
	std::cout << "\ta)\tSort by area (ascending)" << endl;
	std::cout << "\tb)\tSort by area (descending)" << endl;
	std::cout << "\tc)\tsort by special type and area" << endl;
	std::cout << endl;
	std::cout << "Please select sort option ('q'to go back to main menu) : ";
	cin >> option;
	int intOption;
	if (option == "a")
	{
		intOption = 1;
	}else if (option == "b")
	{
		intOption = 2;
	}else if (option == "c")
	{
		intOption = 3;
	}else{
		intOption = 4;
	}
	return intOption;
}

void printReport(ShapeTwoD *(&array)[], int index)
{
	std::cout << "Total no. of records available = " << index << endl;
	std::cout << endl;
	
	for (int i = 0; i < index ; i++)
	{	
		std::cout << "Shape[" << i << "]" << std::endl;
		std::cout << array[i]->toString() << std::endl;
		std::cout << endl;
	}
	
}

vector<Point> getPoint(const int &pointCount, bool isCircle)
{
	int i = 1;
	vector<Point> tempArray;

	if(isCircle){
		int x;
		int y;
		std::cout << "Please enter x-ordiante of center : ";
		cin >> x;
		std::cout << "Please enter y-ordiante of center : ";
		cin >> y;
		cout << endl;

		tempArray.push_back(Point(x,y));
	}else{
		while (i <= pointCount)
		{	
			int x;
			int y;
			std::cout << "Please enter x-ordiante of pt." << i <<" : ";
			cin >> x;
			std::cout << "Please enter y-ordiante of pt." << i <<" : ";
			cin >> y;
			cout << endl;

			tempArray.push_back(Point(x,y));
			i++;
		}
	}
	return tempArray;
}

void createShape(ShapeTwoD *(&array)[], int &index)
{
	
	string shape;
	int option;
	bool spaceType;
	string spaceTypeString;
	std::cout << "Please enter name of shape : ";
	std::cin >> shape;
	
	if (strMatch(shape, "square"))
	{
		option = 1;
	}else if (strMatch(shape, "rectangle"))
	{
		option = 2;
	}else if (strMatch(shape, "circle"))
	{
		option = 3;
	}else if (strMatch(shape, "cross"))
	{
		option = 4;
	}else{
		option = 0;
	}
	std::cout << "Please enter special type : ";
	cin >> spaceTypeString;
	if(strMatch(spaceTypeString, "WS")){
		spaceType = true;
	}else if(strMatch(spaceTypeString, "NS")){
		spaceType = false;
	}else{
		std::cout<< "Invalid space type" << endl;
		return;
	}
	std::cout << endl;
	vector<Point> tempArray;
	switch (option)
	{
	case 1:{
		//square
		tempArray = getPoint(4, false);
		Square *sq = new Square("Square", spaceType, tempArray[0], tempArray[1], tempArray[2], tempArray[3]);
		sq->setLength();
		sq->getBindingPoints();
		sq->getPointsInOnshape();
		array[index] = sq;
		break;
	}
	case 2:{
		//rectangle
		tempArray = getPoint(4, false);
		Rectangle *rec = new Rectangle("Rectangle", spaceType, tempArray[0], tempArray[1], tempArray[2], tempArray[3]);
		rec->setLengthWidth();
		rec->getBindingPoints();
		rec->getPointsInOnshape();
		array[index] = rec;
		break;
	}
	case 3:{
		//circle
		double radius;
		tempArray = getPoint(1, true);
		cout << "Please enter radius (units) : ";
		cin >> radius;
		cout << endl;
		Circle *cir = new Circle("Circle", spaceType, tempArray[0], radius);
		cir->setNSEW();
		cir->getBindingPoints();
		cir->getPointsInOnshape();
		array[index] = cir;
		break;
	}
	case 4:{
		//cross
		tempArray = getPoint(12, false);
		Cross *cross = new Cross("Cross", spaceType, tempArray[0], tempArray[1], tempArray[2], tempArray[3], tempArray[4], tempArray[5], tempArray[6], tempArray[7], tempArray[8], tempArray[9], tempArray[10], tempArray[11]);
		cross->getBindingPoints();
		cross->getPointsInOnshape();
		array[index] = cross;
		break;
	}
	default:
		break;
	}
	std::cout << "Record successfully stored. Going back to main menu ..." << endl;
	std::cout << endl;
	index++;
}

void computeArea(ShapeTwoD *array[], int index)
{
	for (int i = 0; i < index; i++)
	{
		array[i]->setArea();
	}

	std::cout << "Computation completed! ( " << index <<" records were updated )" << endl;
	std::cout << endl;
}

bool compareAreaAscending(ShapeTwoD * shape1, ShapeTwoD *shape2)
{
	return shape1->getArea() < shape2->getArea();
}

bool compareAreaDescending(ShapeTwoD * shape1, ShapeTwoD *shape2)
{
	return shape1->getArea() > shape2->getArea();
}

bool compareBySpaceDescending(ShapeTwoD * shape1, ShapeTwoD *shape2)
{
    if (shape1->getContainsWarpSpace() != shape2->getContainsWarpSpace()) {
        // Sort by true first, then false
        return shape1->getContainsWarpSpace() > shape2->getContainsWarpSpace();
    } else {
        // Within each group (true and false), sort by area in descending order
        return shape1->getArea() > shape2->getArea();
    }
}
