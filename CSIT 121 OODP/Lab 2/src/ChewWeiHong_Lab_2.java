import java.util.Random;

class Point{

    private int x;
    private int y;

    
    //default empty constructor
    public Point(){
    }


    public Point(int x, int y){
        setXY(x, y);
    }

    //copy constructor
    public Point(Point p){
        setXY(p.getX(), p.getY());
    }

    //accessor methods
    public int getX(){
        return x;
    }

    public int getY(){
        return y;
    }

    //mutator method
    public void setXY(int x, int y) {
        this.x = x;
        this.y = y;
    }

    private double distance(Point p){

        double distance =  Math.sqrt((Math.pow(this.getX()- p.getX(), 2)) + (Math.pow(this.getY() - p.getY(), 2)));
        return distance;

    }

    public double getdistance(Point p) {

        double d = distance(p);

        return d;
        
    }

    public String toString() {
        String s = String.format("Point(%d,%d)", this.getX(), this.getY());
        return s;
    }

}

class Line{

    private Point p1;
    private Point p2;

    //default empty constructor
    public Line(){
        
    }
    
    public Line(Point p1, Point p2){
        setP1P2(p1, p2);
    }

    //copy constructor
    public Line(Line aline){
        this(aline.getP1(), aline.getP2());
    }

    //accessor methods
    public Point getP1() {
        return p1;
    }

    public Point getP2() {
        return p2;
    }

    //mutator methods
    public void setP1P2(Point p1, Point p2){
        this.p1 = p1;
        this.p2 = p2;
    }

    public double getDistance(){

         return this.getP1().getdistance(this.getP2());
    }

    
    public String toString(){

        return String.format("Line (%s, %s, distance = %f)", this.getP1().toString(),this.getP2().toString(), this.getDistance());
        
    }


}

public class ChewWeiHong_Lab_2 {

    private static Random rand = new Random();

    private static int getInt(){
        int integer = (rand.nextInt(201)-100);
        return integer;

    }

    //generates 2 random points 
    private static void getTwoPoints(Point p1, Point p2){
        
        p1.setXY(getInt(), getInt());
        p2.setXY(getInt(), getInt());

    }

    //instantialize a line object
    private static Line instLine(){

        Point p1 = new Point();
        Point p2 = new Point();
        getTwoPoints(p1, p2);
        Line l1 = new Line(p1, p2);
        return l1;

    }

    private static void output(int x){

        for (int i = 0; i < x; i++) {

            Line a = instLine();

            System.out.printf("Set %d%n", i+1);
            System.out.println("Given " + a.getP1().toString());
            System.out.println("Given " + a.getP2().toString());
            System.out.println(a.toString());
            System.out.println("---------------------------");
            
        }

    }
    
    public static void main(String[] args) throws Exception {
        
        // variable for number of outputs
        int x = 5;

        output(x);

        
    }
}
  