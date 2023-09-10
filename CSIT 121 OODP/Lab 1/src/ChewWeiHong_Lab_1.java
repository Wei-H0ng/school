import java.util.ArrayList;


class personalInfo{

    private String name;
    private String nationality;
    private date DOB;
    private ArrayList<String> hobbylist;
    private ArrayList<wishes> wishlist;

    // Main object student constructor
    public personalInfo(String name, String nationality, date DOB, ArrayList<String> hobbylist, ArrayList<wishes> wishlist){
        
        setDetails(name, nationality, DOB, hobbylist, wishlist);
    }

    //copy constructor for personalInfo object
    public personalInfo(personalInfo i){

        setDetails(i.name, i.nationality, i.DOB, i.hobbylist, i.wishlist);
    }

    //Accessor methods
    public String getName() {
        return name;
    }

    public String getNationality(){
        return nationality;
    }

    public date getDOB() {
        return DOB;
    }

    public ArrayList<String> getHobbyList() {
        return hobbylist;
    }

    public ArrayList<wishes> getWishList() {
        return wishlist;
    }


    // Mutator methods
    public void setDetails(String name, String nationality, date DOB, ArrayList<String> hobbylist, ArrayList<wishes> wishlist) {
        
        this.name = name;
        this.nationality = nationality;
        this.DOB = DOB;
        this.hobbylist = hobbylist;
        this.wishlist = wishlist;

    }

    //To String methods
    public void wishlistToString(ArrayList<wishes> list) {

        System.out.printf("My %d wishes %n", list.size());
        System.out.println("------------------------------------------------------");
        System.out.println("|No.| Wishes                         | YearToAchieve |");
        System.out.println("------------------------------------------------------");

        //iterates through the array and print the object as reaable strings
        for (int i = 0; i < list.size(); i++) {
            wishes w = list.get(i);

            //prints continuous in place of 0 if wish object doesnt have a time constraint
            if (w.getYearToAchieve() == 0) {

                System.out.printf("|%3d| %-30s | continuous    |%n", i+1, w.getName());

            } else {

                System.out.printf("|%3d| %-30s | %-13d |%n", i+1, w.getName(), w.getYearToAchieve());
            }
        }
        System.out.println("------------------------------------------------------");
    }

    //prints formatted output for the hobbylist
    public void listToString(ArrayList <String> list) {
        System.out.printf("My %d hobbies. %n", list.size());
        System.out.println("---------------------------------------");
        System.out.println("|No.| Hobbies                         |");
        System.out.println("---------------------------------------");

        for (int i = 0; i < list.size(); i++) {
            System.out.printf("|%3d| %-32s|%n", i+1, list.get(i));
        }
        System.out.println("---------------------------------------");
    }

    //calls other toString methods to diaplay complete information
    public void displayPersonalInfo() {
        System.out.println("My very accurate information");
        System.out.printf("Name : %s%n", this.getName());
        System.out.printf("Date of birth : %s%n", this.getDOB().toString());
        System.out.printf("Nationality : %s%n", this.getNationality());
        System.out.println("---------------------------------------");
        this.listToString(hobbylist);
        this.wishlistToString(wishlist);

    }
}


class wishes{

    private String name;
    private int yearToAchieve;
    
    //constructor for wish object without time constraint
    public wishes(String name){
        this(name, 0);
    }

    //overloaded constuctor for wish object with time constraints
    public wishes(String name, int yearToAchieve){
        setDetails(name, yearToAchieve);
    
    }

    //accessor and mutator methods
    public String getName(){
        return name;
    }

    public int getYearToAchieve() {
        return yearToAchieve;
    }

    public void setDetails(String name, int yearToAchieve) {
        this.name = name;
        this.yearToAchieve = yearToAchieve;
    }

}

class date {
    private int day;
    private String month;
    private int year;

    //date constructor
    public date (int day, String month, int year){
        setDate(day, month, year);
    }

    //assesor method
    public int getDay() {
        return day;
    }

    public String getMonth(){
        return month;
    }
    
    public int getYear(){
        return year;
    }

    //mutator method
    public void setDate(int day, String month, int year) {
        this.day = day;
        this.year = year;
        this.month = month;
    }

    //turns date object into reable string
    public String toString() {

        String s = String.format("%d %s %d",this.day , this.month, this.year);
        
        return s;
    }

}

public class ChewWeiHong_Lab_1 {

    private static personalInfo constructPIObj() {

        //instantiating my personal detials that are definitely accurate
    
        String name = "Wei Hong";
        String nationality = "African";
        date DOB = new date(01, "December", 2000);
    
        //instantiating hobbies and adding them to an array list
        
        String[] hobbies = {"Badminton","Basketball","Drawing","Chess","Making lists"};

        ArrayList <String> hobbylist = new ArrayList<String>();

        // copies hobby list into array list

        for (String i : hobbies) { 
            hobbylist.add(i);
        }
    
        //instantiating wishes and adding them to an array list
    
        ArrayList <wishes> wishlist = new ArrayList<wishes>();
        wishes a = new wishes("To become a millionaire", 25);
        wishlist.add(a);
        wishes b = new wishes("To have a GPA of 4.0", 2);
        wishlist.add(b);
        wishes c = new wishes("To get better at chess");
        wishlist.add(c);
    
        //initialize personal info object with all its attributes
    
        personalInfo me = new personalInfo(name, nationality, DOB, hobbylist, wishlist);
        
        return me;
        
    }

    public static void main(String[] args) throws Exception {
        
        personalInfo deepcopy = new personalInfo(constructPIObj());
        
        deepcopy.displayPersonalInfo();

    }

    

}