// Chew Wei Hong
// Full time
// T08
// Code written below is my own work and has not been passed to anyone else
// I am willing to accept any penalty given if the above is not true

import java.util.ArrayList;
import java.util.Random;

enum ShapeColor {
    Blue,
    Yellow,
    Red,
    Green,
    White;
}

interface ForTwoD {

    public double perimeter();

    public void recolor(ShapeColor sc);
}

interface Shape {

    public double area();

}

interface ForThreeD {

    public double volume();

    public void resize(double percentage);
}

abstract class TwoD implements ForTwoD, Shape {

    protected ShapeColor sc;
    protected int a;
    protected int b;
    protected int c;
    protected int d;

    public TwoD() {

    }

    public TwoD(ShapeColor sc, int a) {
        set(sc, a);
    }

    public TwoD(ShapeColor sc, int a, int b) {
        set(sc, a, b);

    }

    public TwoD(ShapeColor sc, int a, int b, int c) {
        set(sc, a, b, c);

    }

    public TwoD(ShapeColor sc, int a, int b, int c, int d) {
        set(sc, a, b, c, d);

    }

    public TwoD(TwoD td) {
        set(td.sc, td.a, td.b, td.c, td.d);
    }

    public int getA() {
        return a;
    }

    public int getB() {
        return b;
    }

    public int getC() {
        return c;
    }

    public int getD() {
        return d;
    }

    public ShapeColor getShapeColor() {
        return sc;
    }

    public void set(ShapeColor sc, int a) {
        this.sc = sc;
        this.a = a;
    }

    public void set(ShapeColor sc, int a, int b) {
        this.sc = sc;
        this.a = a;
        this.b = b;

    }

    public void set(ShapeColor sc, int a, int b, int c) {
        this.sc = sc;
        this.a = a;
        this.b = b;
        this.c = c;
    }

    public void set(ShapeColor sc, int a, int b, int c, int d) {
        this.sc = sc;
        this.a = a;
        this.b = b;
        this.c = c;
        this.d = d;
    }

    public void recolor(ShapeColor sc) {
        this.sc = sc;
    }

    public String toString() {
        if (getB() == 0) {
            String s = String.format("2D (%s, %d)", this.getShapeColor(), this.getA());
            return s;
        } else if (getC() == 0) {
            String s = String.format("2D (%s, %d, %d)", this.getShapeColor(), this.getA(), this.getB());
            return s;
        } else if (getD() == 0) {
            String s = String.format("2D (%s, %d, %d, %d)", this.getShapeColor(), this.getA(), this.getB(), this.getC());
            return s;
        } else {
            String s = String.format("2D (%s, %d, %d, %d, %d)", this.getShapeColor(), this.getA(), this.getB(), this.getC(), this.getD());
            return s;
        }
    }

}

abstract class ThreeD implements ForThreeD, Shape {

    protected ShapeColor sc;
    protected double a;

    public ThreeD() {
    }

    public ThreeD(ShapeColor sc, double a) {
        set(sc, a);
    }

    public ThreeD(ThreeD td) {
        this(td.getShapeColor(), td.getA());
    }

    public double getA() {
        return a;
    }

    public ShapeColor getShapeColor() {
        return sc;
    }

    public void set(ShapeColor sc, double a) {
        this.sc = sc;
        this.a = a;
    }

    public void resize(double percentage) {
        this.a = a * percentage;
    }

    public String toString() {
        String s = String.format("3D (%s, %.3f)", this.getShapeColor(), this.getA());
        return s;
    }

}

class Circle extends TwoD {

    public Circle() {

    }

    public Circle(ShapeColor sc, int radius) {
        set(sc, radius);
    }

    public Circle(Circle c) {
        this(c.getShapeColor(), c.getRadius());
    }

    public double area() {
        return Math.PI * Math.pow(getRadius(), 2);

    }

    public double perimeter() {
        return 2 * Math.PI * getRadius();
    }

    public int getRadius() {
        return getA();
    }

    public void set(ShapeColor sc, int radius) {
        super.set(sc, radius);
    }

    public String toString() {
        String s = String.format("Circle (%s)", super.toString());
        return s;
    }
}
 
class Rectangle extends TwoD {

    public Rectangle() {

    }

    public Rectangle(ShapeColor sc, int length, int width) {
        set(sc, length, width);
    }

    public Rectangle(Rectangle r) {
        this(r.getShapeColor(), r.getLength(), r.getWidth());
    }

    public double area() {
        return getLength() * getWidth();
    }

    public double perimeter() {
        return 2 * getLength() + 2 * getWidth();
    }

    public int getLength() {
        return getA();
    }

    public int getWidth() {
        return getB();
    }

    public void set(ShapeColor sc, int length, int width) {
        super.set(sc, length, width);
    }

    public String toString() {
        return String.format("Rectangle (%s)",super.toString());
    }

}

class Triangle extends TwoD {

    public Triangle() {

    }

    public Triangle(ShapeColor sc, int a, int b, int c) {
        set(sc, a, b, c);
    }

    public Triangle(Triangle t) {
        this(t.getShapeColor(), t.getA(), t.getB(), t.getC());
    }

    public double area() {
        // Heron's formular
        double s = perimeter() / 2;
        return Math.sqrt(s * (s - getA()) * (s - getB()) * (s - getC()));
    }

    public double perimeter() {
        return getA() + getB() + getC();
    }

    public int getA() {
        return super.getA();
    }

    public int getB() {
        return super.getB();
    }

    public int getC() {
        return super.getC();
    }

    public void set(ShapeColor sc, int a, int b, int c) {
        super.set(sc, a, b, c);
    }

    public String toString() {
        return String.format("Triangle (%s)", super.toString());
    }

}

class Trapezoid extends TwoD {

    private int h;

    public Trapezoid() {

    }

    public Trapezoid(ShapeColor sc, int a, int b, int c, int d, int h) {
        set(sc, a, b, c, d, h);
    }

    public Trapezoid(Trapezoid t){
        this(t.getShapeColor(),t.getA(),t.getB(),t.getC(),t.getD(),t.getHeight());
    }

    public double area() {
        return ((getA()+getB())/2)*getHeight();
    }

    public double perimeter() {
        return getA() + getB() + getC() + getD();
    }

    public int getA() {
        return super.getA();
    }

    public int getB(){
        return super.getB();
    }

    public int getC() {
        return super.getC();
    }

    public int getD() {
        return super.getD();
    }

    public int getHeight() {
        return h;
    }

    public void set(ShapeColor sc, int a, int b, int c ,int d, int h) {
        super.set(sc, a, b, c, d);
        this.h = h;
    }

    public String toString() {
        return String.format("Trapezoid (%s, %d)", super.toString(), getHeight());
    }
}

class Sphere extends ThreeD {

    public Sphere(){

    }

    public Sphere(ShapeColor sc, double a){
        set(sc, a);
    }

    public Sphere(Sphere s){
        super(s);
    }

    public double area() {
        return 4*Math.PI*Math.pow(getA(), 2);
    }

    public double volume() {
        return ((4.0/3.0)*Math.PI*Math.pow(getA(), 3));
    }

    public void set(ShapeColor sc, double a) {
        super.set(sc, a);
    }

    public double getA() {
        return super.getA();
    }

    public String toString() {
        return String.format("Sphere (%s)", super.toString());
    }
}

class Cube extends ThreeD {

    public Cube(){

    }

    public Cube(ShapeColor sc, double a){
        set(sc, a);
    }

    public Cube(Cube c){
        super(c);
    }

    public double area(){
        return getA()*getA()*6;
    }

    public double volume() {
        return Math.pow(getA(), 3);
    }

    public double getA() {
        return super.getA();
    }

    public void set(ShapeColor sc, double a) {
        super.set(sc, a);
    }

    public String toString() {
        return String.format("Cube (%s)", super.toString());
    }
}

class Tetrahedron extends ThreeD {

    public Tetrahedron(){

    }

    public Tetrahedron(ShapeColor sc, double a) {
        set(sc, a);
    }

    public Tetrahedron(Tetrahedron t){
        super(t);
    }

    public double area() {
        return Math.sqrt(3)*Math.pow(getA(), 2);
    }

    public double volume() {
        return Math.pow(getA(), 3)/(6*Math.sqrt(2));
    }

    public double getA() {
        return super.getA();
    }

    public void set(ShapeColor sc, double a) {
        super.set(sc, a);
    }

    public String toString() {
        return String.format("Tetrahedon (%s)", super.toString());
    }
}

public class ChewWeiHong_052_A2 {

    static Random rdm = new Random();
    static ArrayList<Shape> aList = new ArrayList<Shape>();

    private static int getInt() {
        int rnd = rdm.nextInt(30)+1;
        return rnd;
    }

    private static double getDouble() {
        double rnd = rdm.nextDouble(30)+1;
        return rnd;
    }

    private static ShapeColor getColor(){
        int rnd = rdm.nextInt(5);
        return ShapeColor.values()[rnd];
    }

    private static boolean isTriangle(int a, int b, int c){
        if (a + b <= c || a + c <= b || b + c <= a){
            return false;
        }else{
            return true;
        }
    }

    private static Triangle getTriangle() {

        int a = 0;
        int b = 0;
        int c = 0;
        while (isTriangle(a, b, c) == false) {
            a = getInt();
            b = getInt();
            c = getInt();
        }

        Triangle t = new Triangle(getColor(), a, b, c);
        return t;

    }

    private static TwoD getTwoD(){
        
        int rnd = rdm.nextInt(4);

        switch (rnd) {
            case 0:
                Circle cr = new Circle(getColor(),getInt());
                return cr;
            case 1:
                Rectangle r = new Rectangle(getColor(),getInt(),getInt());
                return r;
            case 2:
                return getTriangle();
            default:
                Trapezoid tz = new Trapezoid(getColor(), getInt(),getInt(),getInt(),getInt(),getInt());
                return tz;
            
        }
    }

    private static ThreeD getThreeD() {
        
        int rnd = rdm.nextInt(3);

        switch (rnd) {
            case 0:
                Cube c =new Cube(getColor(),getDouble());
                return c;
            case 1:
                Tetrahedron th = new Tetrahedron(getColor(), getDouble());
                return th;
            default:
                Sphere s = new Sphere(getColor(), getDouble());
                return s;

        }
    }

    private static void process2DShape(Shape ss) {

        ((TwoD)ss).recolor(getColor());
        System.out.printf("%nUpdated color: %s %n", ((TwoD)ss).getShapeColor().name());
        System.out.printf("Area = %.3f %n", ss.area());
        System.out.printf("I am a %s shape with color changed to %s %n", ss.getClass().getName(), ((TwoD)ss).getShapeColor().name());
        System.out.println("---------------------------------------------------------");
    }

    private static void process3DShape(Shape ss) {

        double d = rdm.nextDouble(1);
        System.out.printf("%nSurface area = %.3f %n", ss.area());
		System.out.printf("Volume = %.3f  %n", ((ThreeD)ss).volume());
        ((ThreeD)ss).resize(d);
        System.out.printf("Size has decreased by %.1f%% : %s %n", 100-(d*100), ss.toString());
        System.out.printf("Updated surface area = %.3f %n", ss.area());
		System.out.printf("Updated volume = %.3f %n", ((ThreeD)ss).volume());
        System.out.printf("I am a %s shape%n", ss.getClass().getName());
        System.out.println("---------------------------------------------------------");


    }

    private static void displayList(ArrayList<Shape>alist) {

        int i = 1;
        
        for (Shape s : alist) {
            
            System.out.printf("Shape %d: %s",i,s.toString());
            if (s instanceof TwoD) {
                process2DShape(s);
            } else {
                process3DShape(s);
            }
            i++;
        }
    }

    private static void generateList() {

        int rnd = 1;

        while (rnd != 0) {
            
            rnd = rdm.nextInt(3);
            
            switch (rnd) {
                
                case 1:
                    aList.add(getTwoD());
                    break;
            
                case 2:
                    aList.add(getThreeD());
                    break; 

            }
            
        }
    }

    public static void main(String[] args){

        generateList();
        displayList(aList);

    }
}
