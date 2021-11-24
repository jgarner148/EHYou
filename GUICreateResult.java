import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.IOException;

public class GUICreateResult implements ActionListener {
    private JFrame mainFrame;
    private  JButton goBack = factory.makeFlatButton("BACK");
    private  JLabel titleLabel = new JLabel("Create Result");

    private JLabel assModuleLabel = new JLabel("*Module:");
    private JTextField assModuleInput = new JTextField();

    private JLabel weekAssLabel = new JLabel("*Week Assigned:");
    private JTextField weekAssInput = new JTextField();

    private JLabel gradeLabel = new JLabel("*Grade:");
    private JTextField gradeInput = new JTextField();

    private JLabel assStudnetLabel = new JLabel("*Student code:");
    private JTextField assStudentInput = new JTextField();

    private JLabel feedbackLabel = new JLabel("*Feedback:");
    private JTextField feedbackInput = new JTextField();

    private JButton createButton = factory.makeFlatButton("Create");

    /////////////////////////////////////////////////////////////////////////
    private String AssModule;
    private String WeekAss;
    private int Grade;
    private String Feedback;
    private String assStudent;

    public GUICreateResult(JFrame mainFrame) {
        this.mainFrame = mainFrame;
        this.mainFrame.setTitle("EHYou - Create Result");

        //Setting up the back Button
        goBack.setBounds(0, 0,100,50);
        goBack.addActionListener(this);

        titleLabel.setBounds(450, 30,450,110);
        titleLabel.setFont(new Font("Georgia", Font.PLAIN,55));

        int xLabel = 400;
        int wLabel = 100;
        int hLabel = 50;
        assModuleLabel.setBounds(xLabel, 150,wLabel,hLabel);
        weekAssLabel.setBounds(xLabel,200,wLabel,hLabel);
        gradeLabel.setBounds(xLabel,250,wLabel,hLabel);
        assStudnetLabel.setBounds(xLabel,300,wLabel,hLabel);
        feedbackLabel.setBounds(xLabel, 350,wLabel,hLabel);



        int xInput = 510;
        int wInput = 250;
        int hInput = 30;
        assModuleInput.setBounds(xInput, 160,wInput,hInput);
        weekAssInput.setBounds(xInput,210,wInput,hInput);
        gradeInput.setBounds(xInput,260,wInput,hInput);
        assStudentInput.setBounds(xInput,310,wInput,hInput);
        feedbackInput.setBounds(xInput, 360,wInput,hInput);


        createButton.setBounds(535, 420, 200, 35);
        createButton.addActionListener(this);

        mainFrame.add(goBack);
        mainFrame.add(titleLabel);

        mainFrame.add(assModuleLabel);
        mainFrame.add(assModuleInput);
        mainFrame.add(weekAssLabel);
        mainFrame.add(weekAssInput);
        mainFrame.add(gradeLabel);
        mainFrame.add(gradeInput);
        mainFrame.add(assStudnetLabel);
        mainFrame.add(assStudentInput);
        mainFrame.add(feedbackLabel);
        mainFrame.add(feedbackInput);

        mainFrame.add(createButton);

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource()==goBack){
            mainFrame.getContentPane().removeAll();
            mainFrame.repaint();
            GUICreateHome createhomepage = new GUICreateHome(this.mainFrame);
        }

        if (e.getSource()==createButton){
            boolean anyInvalid = false;

            //Checking required fields are filled
            if(assModuleInput.getText().equals("")||weekAssInput.getText().equals("")||gradeInput.getText().equals("")||assStudentInput.getText().equals("")||feedbackInput.getText().equals("")){
                JOptionPane.showMessageDialog(null, "You've left a required field blank. Error Code: 7", "Oops", JOptionPane.ERROR_MESSAGE);
                anyInvalid = true;
            }

            //Checking module code
            if(!anyInvalid) {
                String inputtedModule = assModuleInput.getText();
                try {
                    boolean exists = quickMethods.checkIfInFile("codes/modulecodes.csv", inputtedModule);
                    if (exists == true) {
                        this.AssModule = inputtedModule;
                    }

                    if (exists == false) {
                        JOptionPane.showMessageDialog(null, "That is not a valid module code. Error Code: 50X10", "Oops", JOptionPane.ERROR_MESSAGE);
                        anyInvalid = true;
                    }
                } catch (FileNotFoundException ex) {
                    JOptionPane.showMessageDialog(null, "You are missing a file! Error Code: 1000X10", "Oops", JOptionPane.ERROR_MESSAGE);
                    anyInvalid = true;
                }
            }

            this.WeekAss = weekAssInput.getText();

            //Checking Grade
            if(!anyInvalid){
                int gradeAsInt = Integer.parseInt(gradeInput.getText());
                if(gradeAsInt>100 || gradeAsInt<0){
                    JOptionPane.showMessageDialog(null, "Grade was not valid. Make sure it is between 0 & 100. Error Code: 500", "Oops", JOptionPane.ERROR_MESSAGE);
                    anyInvalid = true;
                }
                else {
                   this.Grade = gradeAsInt;
                }
            }

            //Checking student code
            if(!anyInvalid){
                String inputtedStudent = assStudentInput.getText();
                try {
                    boolean exists = quickMethods.checkIfInFile("codes/studentNumbers.csv",inputtedStudent);
                    if(!exists){
                        JOptionPane.showMessageDialog(null, "That is not a valid Student code, make sure you have created the student first. Error Code: 50X30", "Oops", JOptionPane.ERROR_MESSAGE);
                        anyInvalid = true;
                    }
                    if (exists){
                        this.assStudent =inputtedStudent;
                    }
                }
                catch (FileNotFoundException ex) {
                    JOptionPane.showMessageDialog(null, "You are missing a file! Error Code: 1000X30", "Oops", JOptionPane.ERROR_MESSAGE);
                    anyInvalid = true;
                }
            }

            this.Feedback = feedbackInput.getText();

            if(!anyInvalid){
                try{
                    Result newResult = new Result(this.AssModule,this.WeekAss,this.Grade,this.Feedback,this.assStudent);
                    JOptionPane.showMessageDialog(null, "Result was created successfully! New Result Code " + newResult.getResultCode() , "Success!", JOptionPane.INFORMATION_MESSAGE);
                    mainFrame.getContentPane().removeAll();
                    mainFrame.repaint();
                    GUICreateHome createhomepage = new GUICreateHome(this.mainFrame);
                } catch (IOException | ClassNotFoundException ioException) {
                    JOptionPane.showMessageDialog(null, "An error occured. Error Code: 4000X30", "Oops", JOptionPane.ERROR_MESSAGE);
                    anyInvalid = true;
                }
            }
        }
    }
}
