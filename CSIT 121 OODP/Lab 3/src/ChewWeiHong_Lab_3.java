import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.plaf.DimensionUIResource;

import java.awt.FlowLayout;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

class myInfo extends JFrame {

    //3 texts
    //3 uneditable textfield with text in them
    //1 image with tooltip
    //1 text
    //2 editable textfield with event listener

    private final JLabel [] labelArray;
    private final String [] labelStrings = {"Name", "Date of birth",
    "Email", "My comments to the subject, will update further"};
    private final JTextField [] textFieldsArray;
    private final String [] textStrings = {"Chew Wei Hong", "1 December 2000", "whc760@uowmail.edu.au", "Dr Heng is a great professor" , "I hope I get High Distinction for 121"};
    private JLabel myImage;


    public myInfo(){

        super("Introduction to myself");
        setLayout(new FlowLayout());

        labelArray = new JLabel [labelStrings.length];
        textFieldsArray = new JTextField [textStrings.length];

        for (int i = 0; i < labelStrings.length; i++) {
            labelArray[i] = new JLabel();
            labelArray[i].setText(labelStrings[i]);
        };

        TextFieldHandler tfEventHandler = new TextFieldHandler();
        for (int i = 0; i < textStrings.length; i++) {
            textFieldsArray[i] = new JTextField();
            textFieldsArray[i].setText(textStrings[i]);
            textFieldsArray[i].setPreferredSize(new DimensionUIResource(230, 20));
        }

        textFieldsArray[0].setEditable(false);
        textFieldsArray[1].setEditable(false);
        textFieldsArray[2].setEditable(false);
        textFieldsArray[3].addActionListener(tfEventHandler);
        textFieldsArray[4].addActionListener(tfEventHandler);
        
        myImage = new JLabel();
        Icon me = new ImageIcon("image.jpg");
        myImage.setIcon(me);
        myImage.setToolTipText("Hey look! That's me!");

        //add to frame + layout
        //maybe try other layouts 
        add(labelArray[0]);
        add(textFieldsArray[0]);
        add(labelArray[1]);
        add(textFieldsArray[1]);
        add(labelArray[2]);
        add(textFieldsArray[2]);
        add(myImage);
        add(labelArray[3]);
        add(textFieldsArray[3]);
        add(textFieldsArray[4]);

    }

    private class TextFieldHandler implements ActionListener  {
        
        public void actionPerformed(ActionEvent e) {
            
            String s = String.format("Summary of your changes %n 1. %s%n 2. %s ", textFieldsArray[3].getText(), textFieldsArray[4].getText());
            JOptionPane.showMessageDialog(null, s, "My sugesstions to the course", JOptionPane.WARNING_MESSAGE);

        } 
    }
}

public class ChewWeiHong_Lab_3 {

    private static void createAndShowUI() {
        
        myInfo aFrame = new myInfo();
        aFrame.setSize(350,500);
        aFrame.setVisible(true);
        aFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    }
    public static void main(String[] args) throws Exception {

        createAndShowUI();

    }
}
