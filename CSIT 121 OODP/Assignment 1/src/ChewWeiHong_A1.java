// Chew Wei Hong
// Full time
// T08
// Code written below is my own work and has not been passed to anyone else
// I am willing to accept any penalty given if the above is not true

import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

enum ZodiacInfo {
    Aries,
    Taurus,
    Gemini,
    Cancer,
    Leo,
    Virgo,
    Libra,
    Scorpio,
    Sagittarius,
    Capricorn,
    Aquarius,
    Pisces
}



enum ZodiacType {
    ARI(ZodiacInfo.Aries , "March 21", "April 19"),
    TAU(ZodiacInfo.Taurus, "April 20", "May 20"),
    GEM(ZodiacInfo.Gemini, "May 21", "June 20"),
    CAN(ZodiacInfo.Cancer, "June 21", "July 22"),
    LEO(ZodiacInfo.Leo, "July 23", "August 22"),
    VIR(ZodiacInfo.Virgo, "August 23", "September 22"),
    LIB(ZodiacInfo.Libra, "September 23", "October 22"),
    SCO(ZodiacInfo.Scorpio, "October 23", "November 21"),
    SAG(ZodiacInfo.Sagittarius, "November 22", "December 21"),
    CAP(ZodiacInfo.Capricorn, "December 22", "January 19"),
    AQU(ZodiacInfo.Aquarius, "January 20", "February 18"),
    PIS(ZodiacInfo.Pisces, "February 19", "March 20");

    private ZodiacInfo zodiacInfo;
    private final String fromDate;
    private final String toDate;

    ZodiacType(ZodiacInfo z, String fromDate, String toDate) {
        
        this.zodiacInfo = z;
        this.fromDate = fromDate;
        this.toDate = toDate;
    }

    public ZodiacInfo getZodiacInfo(){
        return zodiacInfo;
    }

    public String getFromDate(){
        return fromDate;
    }

    public String getToDate(){
        return toDate;
    }


}

class Set{

    private ArrayList<ZodiacType> s;

    public Set(){
        s = new ArrayList<ZodiacType>();

    }

    public Set(Set otherSet){

        s = new ArrayList<ZodiacType>();

        for (ZodiacType i : otherSet.s) {
            
            this.s.add(i);
        }

    }

    public boolean isEmpty() {

        return this.s.isEmpty();
    }

    public int cardinality() {
        return this.s.size();
    }

    public boolean belongTo(ZodiacType element){
        return this.s.contains(element);
    }

    public void addElement(ZodiacType element) {

        this.s.add(element);
    }

    public boolean subset(Set otherSet) {
        
        return this.s.containsAll(otherSet.s);
    }

    public void union(Set otherSet) {
        
        for (ZodiacType i : otherSet.s) {
            if (this.belongTo(i) == false) {
                this.addElement(i);
            }
        }
    }

    public void intersection(Set otherSet) {
        
        Set temp = new Set(this);
        temp.union(otherSet);
        
        for (ZodiacType i : temp.s) {
            if (this.belongTo(i) && otherSet.belongTo(i)) {
                continue;
                
            }else{
                this.s.remove(i);
            }
        }
    }

    public void difference(Set otherSet){

        for (ZodiacType i : otherSet.s) {
            this.s.remove(i);
        }
        

        // returns difference of both sets
        // Set temp = new Set(this);
        // temp.union(otherSet);
        
        // for (ZodiacType i : temp.s) {
        //     if (this.belongTo(i) && otherSet.belongTo(i)) {
        //         this.s.remove(i); 
                
        //     }else{
        //         this.addElement(i);
        //     }
        // }

    }

    public Set complement() {
        
        Set universal = new Set();

        for (ZodiacType a : ZodiacType.values()) {
            universal.addElement(a);
        }

        for (ZodiacType i : this.s) {
            universal.s.remove(i);
        }
        
        return universal;

    }

    public boolean equality(Set otherSet) {

        if (this.subset(otherSet) && otherSet.subset(this)) {

            return true;

        } else {

            return false;
            
        }

    }

    public String toString() {

        String s = "";

        for (int i = 0; i < this.s.size(); i++) {
            if ((i+1) == this.s.size()) {
                s += this.s.get(i).name();
            } else {
                s += this.s.get(i).name() + ", ";
            }
        }
        return s;
    }

    public String getZodiacInfoFormat(){

        String s = "";
        
        for (int i = 0; i < this.s.size(); i++) {
            if ((i+1) == this.s.size()) {
                s += this.s.get(i).getZodiacInfo().name();
            } else {
                s += this.s.get(i).getZodiacInfo().name() + ", ";
            }
        }
        return s;
    }
}


public class ChewWeiHong_A1 {

    private static Scanner input = new Scanner(System.in);

    private static void dispalyZodiacTypeInfo(){

        System.out.println("Zodiac Type   Zodiac Info   From Date     To Date");
        for (ZodiacType i : ZodiacType.values()) {
            System.out.printf("%-13s %-13s %-13s %-13s %n",i.name(), i.getZodiacInfo().name(), i.getFromDate(), i.getToDate() );
        }
        System.out.println("");

    }

    private static ZodiacType getAnElement() {

        Random rand =  new Random();

        int n = rand.nextInt(12);

        return ZodiacType.values()[n];
        
    }

    private static Set getASet() {
        
        Random rand = new Random();
        int n = rand.nextInt(11)+1;

        Set a = new Set();

        for (int i = 0; i < n; i++) {

            ZodiacType zt = getAnElement();

            if (a.belongTo(zt)){ 
                continue;
            }else{
                a.addElement(zt);
            }
        }
 
        return a;
    }
    
    private static void displayMenu() {
        System.out.print("""
            Welcome to SIM Set Theory lesson

            0. Properties of a set
            1. Union example
            2. Intersection example
            3. Subset exmaple
            4. Difference example
            5. Complement example
            6. Sets equality example
            7. Distributive Law 1
            8. Distributive Law 2
            9. Quit

            Enter your option : """);
        
    }

    private static void unionExample() {
        
        Set A = getASet();
        Set B = getASet();

        System.out.println("Given sets");
        System.out.printf("     A = {%s}%n", A.toString());
        System.out.printf("     B = {%s}%n", B.toString());
        A.union(B);
        System.out.printf("     union of A and B = {%s}%n", A.toString());
        System.out.println("------------------------------------------");

    }

    private static void intersectionExample() {
        
        Set A = getASet();
        Set B = getASet();

        System.out.println("Given sets");
        System.out.printf("     A = {%s}%n", A.toString());
        System.out.printf("     B = {%s}%n", B.toString());
        A.intersection(B);
        System.out.printf("     Intersection of A and B = {%s}%n", A.toString());
        System.out.println("------------------------------------------");


    }

    private static void subsetExample() {

        Set A = getASet();
        Set B = getASet();

        System.out.println("Given sets");
        System.out.printf("     A = {%s}%n", A.toString());
        System.out.printf("     B = {%s}%n", B.toString());
        System.out.println("Conclusion");
        System.out.printf("     A is a subset of B : %b%n", B.subset(A));
        System.out.printf("     B is a subset of A : %b%n", A.subset(B));
        System.out.println("------------------------------------------");

    }

    private static void differenceExample() {
        
        Set A = getASet();
        Set B = getASet();

        System.out.println("Given sets");
        System.out.printf("     A = {%s}%n", A.toString());
        System.out.printf("     B = {%s}%n", B.toString());
        A.difference(B);
        System.out.printf("     A - B = {%s}%n", A.toString());
        System.out.println("------------------------------------------");

    }

    private static void complementExample() {

        Set A = getASet();

        System.out.println("Given sets");
        System.out.printf("     A = {%s}%n%n", A.toString());
        System.out.printf("     A' = {%s}%n%n", A.complement().toString());
        System.out.println("------------------------------------------");
        
    }

    private static void equalityExample() {

        Set A = getASet();
        Set B = getASet();

        System.out.println("Given sets");
        System.out.printf("     A = {%s}%n", A.toString());
        System.out.printf("     B = {%s}%n", B.toString());
        System.out.println("");
        System.out.println("Analysis");
        System.out.printf("     A is a subset of B : %b%n", B.subset(A));
        System.out.printf("     B is a subset of A : %b%n", A.subset(B));
        System.out.println("");
        System.out.println("Conclusion");
        System.out.printf("     A equals to B : %b%n", A.equality(B));
        System.out.println("------------------------------------------");
 
    }

    private static void distributiveLaw_1() {

        //create additional sets with deep copy so that the original set's values doesnt change
        Set A = getASet();
        Set B = getASet();
        Set C = getASet();
        Set Acopy = new Set(A);
        Set Bcopy = new Set(B);
        Set Acopy2 = new Set(A);

        
        System.out.println("We wish to prove: A U (B I C) = (A U B) I (A U C)");
        System.out.println("");
        System.out.println("Given sets");
        System.out.printf("     A = {%s}%n", A.toString());
        System.out.printf("     B = {%s}%n", B.toString());
        System.out.printf("     C = {%s}%n%n", C.toString());
        
        Bcopy.intersection(C);
        Acopy.union(Bcopy);
        System.out.println("LHS Analysis");
        System.out.printf("     LHS = {%s}%n", Acopy.toString());

        A.union(B);
        Acopy2.union(C);
        A.intersection(Acopy2);

        System.out.println("RHS Analysis");
        System.out.printf("     RHS = {%s}%n", A.toString());
        System.out.println("");
        System.out.println("Conclusion");
        System.out.printf("     LHS = RHS is %b%n", Acopy.equality(A));
        System.out.println("------------------------------------------");
        System.out.println("");
        
    }
    
    private static void distributiveLaw_2() {
        
        Set A = getASet();
        Set B = getASet();
        Set C = getASet();
        Set Acopy = new Set(A);
        Set Bcopy = new Set(B);
        Set Acopy2 = new Set(A);

        System.out.println("We wish to prove: A I (B U C) = (A I B) U (A I C)");
        System.out.println("");
        System.out.println("Given sets");
        System.out.printf("     A = {%s}%n", A.toString());
        System.out.printf("     B = {%s}%n", B.toString());
        System.out.printf("     C = {%s}%n%n", C.toString());
        Bcopy.union(C);
        Acopy.intersection(Bcopy);
        
        System.out.println("LHS Analysis");
        System.out.printf("     LHS = {%s}%n", Acopy.toString());

        A.intersection(B);
        Acopy2.intersection(C);
        A.union(Acopy2);

        System.out.println("RHS Analysis");
        System.out.printf("     RHS = {%s}%n", A.toString());
        System.out.println("");
        System.out.println("Conclusion");
        System.out.printf("     LHS = RHS is %b%n", Acopy.equality(A));
        System.out.println("------------------------------------------");
        System.out.println("");

    }
    
    public static void displaySubMenu() {

        System.out.print("""
            Some basic operations in Set
            
                1. Add an element
                2. Check an element
                3. Cardinality
                4. Zodiac Info Format
                9. Quit

            Enter your option : """);
        
    }

    public static void anExample() {
        
        System.out.println("Here is an exmaple of a set");
        System.out.println("");
        Set A = getASet();
        System.out.printf("    A = {%s}%n", A.toString());
        System.out.println("    All elements in set are distinct and in random order");
        System.out.println("");

        int option = 0;
        String ele = "";

        while (option != 9) {
            displaySubMenu();
            option = input.nextInt();
            System.out.println("");

            switch (option) {

                case 1:

                    System.out.print("Enter an element : ");
                    ele = input.next().toUpperCase();
                    if (A.belongTo(ZodiacType.valueOf(ele)) == false) {
                        
                        A.addElement(ZodiacType.valueOf(ele));
                    }else{
                        System.out.printf("Element %s is already  in set %n", ele);
                    }
                    System.out.printf("     A = {%s}%n", A.toString());
                    System.out.println("------------------------------------------");

                    break;

                case 2:

                    System.out.print("Enter an element : ");
                    ele = input.next().toUpperCase();
                    if (A.belongTo(ZodiacType.valueOf(ele))) {
                        
                        System.out.printf("Element %s is in set%n", ele);
                        System.out.println("------------------------------------------");
                    }else {
                        System.out.printf("Element %s is not in set%n", ele);
                        System.out.println("------------------------------------------");
                
                    }
                    
                    break;

                case 3:

                    System.out.printf("No of elements in set is %d%n", A.cardinality());
                    System.out.println("------------------------------------------");

                    break;

                case 4:

                    System.out.println("Notation in enum format");
                    System.out.printf("     A = {%s}%n", A.getZodiacInfoFormat());
                    System.out.println("------------------------------------------");

                    break;
            }
            
        } 


    }

    public static void main(String[] args) throws Exception {

        
        dispalyZodiacTypeInfo();

        int option = 0;
        while (option != 9) {
            
            displayMenu();
            option = input.nextInt();
            System.out.println("");
            
            switch (option) {
                case 0:
                    anExample();
                    break;

                case 1:
                    unionExample();
                    break;

                case 2:
                    intersectionExample();
                    break;

                case 3:
                    subsetExample();
                    break;

                case 4:
                    differenceExample();
                    break;
                
                case 5:
                    complementExample();
                    break;
                
                case 6:
                    equalityExample();
                    break;

                case 7:
                    distributiveLaw_1();
                    break;

                case 8:
                    distributiveLaw_2();
                    break; 

            }
        }
    }
}
