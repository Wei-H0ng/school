import java.util.InputMismatchException;
import java.util.Scanner;

class InvalidHrExcep extends Exception{
    private String message;

    public InvalidHrExcep(){
        this.message = "The value for hours must be between 0 and 12";
    }
    
    public InvalidHrExcep(String s){
        this.message = s;
    }

    public String getMessage(){
        return message;
    }

    public String toString(){
        return message;
    }

}
class InvalidMinExcep extends Exception{
    private String message;

    public InvalidMinExcep(){
        this.message = "The value for minutes must be between 0 and 60";
    }
    
    public InvalidMinExcep(String s){
        this.message = s;
    }

    public String getMessage(){
        return message;
    }

    public String toString(){
        return message;
    }

}
class InvalidSecExcep extends Exception{
    private String message;

    public InvalidSecExcep(){
        this.message = "The value for seconds must be between 0 and 60";
    }
    
    public InvalidSecExcep(String s){
        this.message = s;
    }

    public String getMessage(){
        return message;
    }

    public String toString(){
        return message;
    }

}

public class ChewWeiHong_052_Lab_4 {

    static int hours;
    static int mins;
    static int secs;
    static String AMPM;
    static Scanner input = new Scanner(System.in);

    public static int getHours() {
        boolean hrInput = false;
        
        while (!hrInput) {
            try {
                System.out.print("Enter hours: ");
                hours = input.nextInt();
                if(0 > hours || hours >12){
                    throw new InvalidHrExcep();
                }else{
                    hrInput = true;
                }
            } catch (InvalidHrExcep ex) {
                System.out.println(ex.toString());
            } catch (InputMismatchException ex){
                System.out.println("Input must be numerical");
                input.nextLine();
            }
        }
        return hours;
    }
    public static int getMins() {
        boolean minInput = false;
        
        while (!minInput) {
            try {
                System.out.print("Enter minutes: ");
                mins = input.nextInt();
                if(0 > mins || mins >59){
                    throw new InvalidMinExcep();
                }else{
                    minInput = true;
                }
            } catch (InvalidMinExcep ex) {
                System.out.println(ex.toString());
            } catch (InputMismatchException ex){
                System.out.println("Input must be numerical");
                input.nextLine();
            }
        }
        return mins;
    }
    public static int getSecs() {
        boolean secInput = false;
        
        while (!secInput) {
            try {
                System.out.print("Enter seconds: ");
                secs = input.nextInt();
                if(0 > secs || secs >59){
                    throw new InvalidSecExcep();
                }else{
                    secInput = true;
                }
            } catch (InvalidSecExcep ex) {
                System.out.println(ex.toString());
            } catch (InputMismatchException ex){
                System.out.println("Input must be numerical");
                input.nextLine();
            }
        }
        return secs;
    }
    public static void getAMPM() {
        input.nextLine();
        System.out.print("Enter AM or PM: ");
        AMPM = input.nextLine();
    }

    public static void print24HourTime(int hr, int min, int sec) {
        if (AMPM.equalsIgnoreCase("pm") && hr < 12) {
            hr += 12;
        }
        String formatedTime = String.format("%s:%s:%s", processTime(hr),processTime(min),processTime(sec));
        System.out.printf("24 hour clock time: %s", formatedTime);
        
    }
    public static String processTime(int i) {
        if (i <10) {
            return String.format("0%d", i);
        }else{
            return String.format("%d", i);
        }
    }

    public static void main(String[] args) throws Exception {

        getHours();
        getMins();
        getSecs();
        getAMPM();
        print24HourTime(hours, mins, secs);

    }
}
