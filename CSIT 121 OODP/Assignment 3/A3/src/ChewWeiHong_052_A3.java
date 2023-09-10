// Chew Wei Hong
// Full time
// 052
// I will demo 2 tasks
// java 17.0.1
// Code written below is my own work and has not been passed to anyone else
// I am willing to accept any penalty given if the above is not true

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;



abstract class PersonInfo{ 

    protected String name;
    protected String title;
    protected String imageFile;
    private String[] greetings = {"Sir", "Dr Heng", "Prof", "Mr Heng", "Cikgu"};
    public static Random rdm = new Random();

    public PersonInfo(String name, String title, String imageFile){
        setInfo(name, title, imageFile);
    }

    public PersonInfo (PersonInfo PI){
        this(PI.name, PI.title, PI.imageFile);
    }

    public String getName() {
        return name;
    }

    public String getTitle() {
        return title;
    }

    public String getImageFile() {
        return imageFile;
    }
    
    public void setInfo(String name, String title, String imageFile) {
        this.name = name;
        this.title = title;
        this.imageFile = imageFile;
    }

    public String getGreeting(){
        return greetings[rdm.nextInt(5)];
    }
    public String toString() {
        return String.format("%s%n%s",getName(),getTitle());
    }
}

class Student extends PersonInfo{

    private String group;
    private String demoWhat;

    public Student(String name, String title, String imageFile,String group, String demoWhat){
        super(name, title, imageFile);
        this.group = group;
        this.demoWhat = demoWhat;
    }

    public String getGroup() {
        return group;
    }

    public String getDemoWhat(){
        return demoWhat;
    }

    public void setInfo(String name, String title, String imageFile,String group, String demoWhat) {
        super.setInfo(name, title, imageFile);
        this.group = group;
        this.demoWhat = demoWhat;
    }

    public String toString(){
        return String.format("<html>Hi %s, I am %s<br>%s<br>I am from group %s<br>I wish to demo %s</html>", getGreeting(),getName(),getTitle(),getGroup(),getDemoWhat());
    }
}

class Lecturer extends PersonInfo{

    private ArrayList<String> message = new ArrayList<String>();

    public Lecturer(String name, String title, String imageFile, ArrayList<String> message){
        super(name, title, imageFile);
        this.message = message;
    }

    public Lecturer(Lecturer lect){
        this(lect.getName(), lect.getTitle(), lect.getImageFile(), lect.getMessage());
    }

    public ArrayList<String> getMessage() {
        return message;
    }

    public String toString() {
        return super.toString();
    }

}

class Demo extends JFrame{

    private final String[] message = {"Well done","Statements too long","Bad indentations", "Overall design ok","Use meaningdul identifiers", "Some improvements needed"};
    private final String[] groups = {"T01","T02","T03","T04","T05","T06","T07","T08"};
    private final String[] names = {"Mary Jane","Alice Hertsworth","John Myers","James Murray","Jacob Smith"};
    // 2F 3M
    private final String[] imageArray = {"s1.png","s2.png","s3.png","s4.png","s5.png"};
    private final String[] imgStringsOtr = {"school.png","l1.png"};

    // private final String[] imageArray = {"1.jpg","2.jpg","3.jpg"};
    // private final String[] imgStringsOtr = {"simuow.jpg","heng.jpg"};
    private ArrayList<Student> aList = new ArrayList<Student>();
    private ArrayList<Lecturer> bList = new ArrayList<Lecturer>();
    private final String[] assignments = {"Assignemnt 1","Assignment 2","Assignment 3","Lab 1","Lab 2","Lab 3"};
    private final String[] studentTitle = {"Full time student","Part time student"};
    private JButton ok,refresh;
    private JLabel welcome,schLogo;
    private int listIndex;

    Random rdm = new Random();
 
    public Demo(){

        super("Let us start");
        setLayout(new FlowLayout());
        
        welcome = new JLabel();
        Icon wlc = new ImageIcon("welcome.gif");
        welcome.setIcon(wlc);
        ok = new JButton("OK");
        eventHandler handler = new eventHandler();
        ok.addActionListener(handler);
        add(welcome);
        add(ok);

    }

    private class eventHandler implements ActionListener {
        
        public void actionPerformed(ActionEvent e){
            
            if (e.getSource() == ok) {

                load();
                Collections.shuffle(aList);
                dispose();
                JFrame mainFrame = new JFrame("Welcome to 121 demo System");
                mainFrame.setSize(750,330);
                mainFrame.setVisible(true);
                mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                mainFrame.setLayout(new FlowLayout());
                schLogo = new JLabel();
                Icon sch = new ImageIcon(imgStringsOtr[0]);
                schLogo.setIcon(sch);
                refresh = new JButton("refresh for a new student");
                eventHandler refHandler = new eventHandler();
                refresh.addActionListener(refHandler);
                mainFrame.add(schLogo);
                mainFrame.add(refresh);
                mainFrame.getContentPane().setBackground(Color.blue);

            }else if (e.getSource() == refresh && listIndex < aList.size()) {

                Icon Lphoto = new ImageIcon(bList.get(0).getImageFile());
                String title = String.format("Let us welcome %s", aList.get(listIndex).getName() );
                JLabel introduction = new JLabel(aList.get(listIndex).toString());
                // String introduction = aList.get(listIndex).toString();
                Icon photo = new ImageIcon(aList.get(listIndex).getImageFile());
                JOptionPane.showMessageDialog(null, introduction, title,JOptionPane.PLAIN_MESSAGE, photo);

                String replyTitle = String.format("Hi %s, my comment to your %s", aList.get(listIndex).getName(), aList.get(listIndex).getDemoWhat());
                String suggestions = String.format("My suggestions, if any: %n%s%n%s", msgListToString(getMessageList()),bList.get(0).toString());
                JOptionPane.showMessageDialog(null, suggestions, replyTitle,JOptionPane.PLAIN_MESSAGE, Lphoto);

                listIndex++;

            }else{
                listIndex = 0;
                Icon sch = new ImageIcon(imgStringsOtr[0]);
                JOptionPane.showMessageDialog(null, String.format("No more students, %nHope you enjoyed%n using the system"),"Thank you",JOptionPane.PLAIN_MESSAGE,sch);
            
            }
        }
    }

    private String demoWhat() {
        return assignments[rdm.nextInt(6)];
    }

    private String getFTPT() {
        return studentTitle[rdm.nextInt(2)];
    }

    private String getGroup(){
        return groups[rdm.nextInt(8)];
    }

    private String getMessage(boolean x){
        //if x is true, "well done" is not in the list
        if(x){
            return message[rdm.nextInt(5)+1];
        } else {
            return message[rdm.nextInt(6)];
        }
    }

    private ArrayList<String> getMessageList() {
        ArrayList<String> m = new ArrayList<>();
        //generates a list of random combination of messages to be displayed
        String m1 = getMessage(false);
        int noOfMsg = rdm.nextInt(5);
        if (m1 != message[0]) {
            m.add(m1);
            for (int i = 0; i < noOfMsg; i++) {
                m1 = getMessage(true);
                if (m.contains(m1)) {
                    continue;
                }else{
                    m.add(m1);
                }
            }
        } else {
            m.add(m1);
        }
        
        return m;
    }

    private String msgListToString(ArrayList<String> m){
        String s = "";
        for (String i : m) {
            s += String.format("- %s%n", i);
        }
        return s;
    }

    private void load(){
        
        for (int i=0;i<imageArray.length;i++) {
            Student s = new Student(names[i], getFTPT(), imageArray[i], getGroup(), demoWhat());
            aList.add(s);
        }
        Lecturer l = new Lecturer("Heng AK", "Lecturer", imgStringsOtr[1], new ArrayList<String>());
        bList.add(l);
        
    }
}

public class ChewWeiHong_052_A3{

    private static void createAndShowUI() {
        
        Demo aFrame = new Demo();
        aFrame.setSize(550,350);
        aFrame.setVisible(true);
        aFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
    public static void main(String[] args) throws Exception {
         
        createAndShowUI();

    }
}
