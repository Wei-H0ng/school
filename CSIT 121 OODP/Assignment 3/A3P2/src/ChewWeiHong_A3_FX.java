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

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;



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
        return String.format("%s%n%s%n",getName(),getTitle());
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
        return String.format("Hi %s, I am %s%nI am from group %s%nI wish to demo %s%n", getGreeting(),super.toString(),getGroup(),getDemoWhat());
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

public class ChewWeiHong_A3_FX extends Application {

    private final String[] message = {"Well done","Statements too long","Bad indentations", "Overall design ok","Use meaningdul identifiers", "Some improvements needed"};
    private final String[] groups = {"T01","T02","T03","T04","T05","T06","T07","T08"};
    private final String[] names = {"Mary Jane","Alice Hertsworth","John Myers","James Murray","Jacob Smith"};
    // 2F 3M
    private final String[] imageArray = {"s1.png","s2.png","s3.png","s4.png","s5.png"};
    private final String[] imgStringsOtr = {"school.png","l1.png","welcome.gif"};
    private ArrayList<Student> aList = new ArrayList<Student>();
    private ArrayList<Lecturer> bList = new ArrayList<Lecturer>();
    private final String[] assignments = {"Assignemnt 1","Assignment 2","Assignment 3","Lab 1","Lab 2","Lab 3"};
    private final String[] studentTitle = {"Full time student","Part time student"};
    private int listIndex;

    Random rdm = new Random();

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
        
        for (int i=0;i<names.length;i++) {
            Student s = new Student(names[i], getFTPT(), imageArray[i], getGroup(), demoWhat());
            aList.add(s);
        }

        Lecturer l = new Lecturer("Heng AK", "Lecturer", imgStringsOtr[1], new ArrayList<String>());
        bList.add(l);
        
    }

    public void start(Stage primaryStage) {

        //3 buttons, 2 text area, 3 images
        load();
        Collections.shuffle(aList);
        
        Image schImage = new Image(imgStringsOtr[0]);
        Image lecImage = new Image(imgStringsOtr[1]);
        Image welcomeImage = new Image(imgStringsOtr[2]);

        TextArea sdtTA = new TextArea();
        sdtTA.setText("Student Corner");
        sdtTA.setPrefHeight(300);
        sdtTA.setPrefWidth(250);
        sdtTA.setEditable(false);
        
        TextArea lectTA = new TextArea();
        lectTA.setText("Lecturer Corner");
        lectTA.setPrefHeight(300);
        lectTA.setPrefWidth(250);
        lectTA.setEditable(false);

        ImageView welcomeImageView = new ImageView(welcomeImage);
        welcomeImageView.setFitHeight(250);
        welcomeImageView.setFitWidth(250);
        welcomeImageView.setPreserveRatio(true);
        ImageView personImageView = new ImageView(schImage);
        personImageView.setFitHeight(250);
        personImageView.setFitWidth(250);
        personImageView.setPreserveRatio(true);

        
        Button refButton = new Button("Refresh");
        Button lecCmtBtn = new Button("Lecturer comment");
        Button clrButton = new Button("Clear");

        refButton.setOnAction(e ->{

            if(listIndex < aList.size()){
                String s = String.format("Student Corner%n%n%s",aList.get(listIndex).toString() );
                sdtTA.setText(s);
                Image photo = new Image(aList.get(listIndex).getImageFile());
                personImageView.setImage(photo);
                
            }else{
                listIndex = 0;
                sdtTA.setText(String.format("Student Corner %n%nNo more students"));
                lectTA.setText(String.format("Lecturer Corner %n%nFinally I can relax"));
                personImageView.setImage(schImage);
            }
            
        });

        lecCmtBtn.setOnAction(e ->{
            String s = String.format("Lecturer Corner %n%n%s%n%n%n%n%s", msgListToString(getMessageList()),bList.get(0).toString());
            lectTA.setText(s);
            personImageView.setImage(lecImage);
            
        });

        clrButton.setOnAction(e ->{
            sdtTA.setText("Student Corner");
            lectTA.setText("Lecturer Corner");
            personImageView.setImage(schImage);
            listIndex++;
   
        });

        Insets generic = new Insets(10);

        GridPane pane = new GridPane();
        pane.setPadding(generic);
        pane.setHgap(5);
        pane.setVgap(5);
        
        pane.addRow(0,sdtTA,lectTA);
        pane.addRow(1, welcomeImageView,personImageView);

        HBox buttons = new HBox(refButton,lecCmtBtn,clrButton);
        buttons.setSpacing(10);;
        buttons.setAlignment(Pos.CENTER);
        
        BorderPane mainPane = new BorderPane();
        mainPane.setPadding(new Insets(20));
        mainPane.setCenter(pane);
        mainPane.setBottom(buttons);

        Scene mainScene = new Scene(mainPane, 570,600);
        primaryStage.setTitle("Your turn to demo");
        primaryStage.setScene(mainScene);
        primaryStage.show();

    }
    
    public static void main(String[] args) throws Exception {
        launch(args);
    }
}
