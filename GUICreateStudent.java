/**
 * The class that houses the GUI for creating a student
 */

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.IOException;

public class GUICreateStudent implements ActionListener {
    //Setting up the global GUI elements
    private final JFrame mainFrame;
    private final JButton goBack = factory.makeFlatButton("BACK");
    private final JLabel titleLabel = new JLabel("Create Student");

    private final JLabel fnameLabel = new JLabel("*First name:");
    private final JTextField fnameInput = new JTextField();

    private final JLabel lnameLabel = new JLabel("*Last name:");
    private final JTextField lnameInput = new JTextField();

    private final JLabel dobLabel = new JLabel("*Date of birth:");
    private final JTextField dobInput = new JTextField();

    private final JLabel ModulesTakingLabel = new JLabel("Module codes:");
    private final JTextField ModulesTakingInput = new JTextField();
    private final JButton addanotherModule = factory.makeFlatButton("Add another");

    private final JLabel AllResultsLabel = new JLabel("Result Codes:");
    private final JTextField AllResultsInput = new JTextField();
    private final JButton addanotherResult = factory.makeFlatButton("Add another");

    private final JLabel StartYrLabel = new JLabel("*Start Year:");
    private final JTextField StartYrInput = new JTextField();

    private final JLabel EndYrLabel = new JLabel("*Final Year:");
    private final JTextField EndYrInput = new JTextField();

    private final JButton createButton = factory.makeFlatButton("Create");

    //Setting up the global variables that will have the values of the student being created assigned to them.
    private String fname;
    private String lname;
    private String dob;
    private String[] ModulesTaking = new String[0];
    private String[] Allresults = new String[0];
    private int StartYr;
    private int EndYr;

    /**
     * Constructor for the class that will be called when wanting to display the GUI for creating a student
     * @param mainFrame The master frame that is already being dispayed when the method is called
     */
    public GUICreateStudent(JFrame mainFrame) {
        //Setting up the main frame
        this.mainFrame = mainFrame;
        this.mainFrame.setTitle("EHYou - Create Student");

        //Setting up the back Button
        goBack.setBounds(0, 0,100,50);
        goBack.addActionListener(this);

        //Setting up the title label
        titleLabel.setBounds(450, 30,450,110);
        titleLabel.setFont(new Font("Georgia", Font.PLAIN,55));

        //Setting label locations
        int xLabel = 400;
        fnameLabel.setBounds(xLabel, 150,100,50);
        lnameLabel.setBounds(xLabel, 200,100,50);
        dobLabel.setBounds(xLabel, 250,100,50);
        ModulesTakingLabel.setBounds(xLabel, 300,100,50);
        AllResultsLabel.setBounds(xLabel, 350,100,50);
        StartYrLabel.setBounds(xLabel, 400,100,50);
        EndYrLabel.setBounds(xLabel, 450,100,50);

        //Setting input locations
        int xInput = 510;
        int wInput = 250;
        int hInput = 30;
        fnameInput.setBounds(xInput, 160,wInput,hInput);
        lnameInput.setBounds(xInput, 210,wInput,hInput);
        dobInput.setBounds(xInput, 260,wInput,hInput);
        ModulesTakingInput.setBounds(xInput, 310,wInput,hInput);
        AllResultsInput.setBounds(xInput, 360,wInput,hInput);
        StartYrInput.setBounds(xInput, 410,wInput,hInput);
        EndYrInput.setBounds(xInput, 460,wInput,hInput);

        //Setting up the add another module button
        addanotherModule.setBounds(775, 312,125,25);
        addanotherModule.addActionListener(this);

        //Setting up the add another result button
        addanotherResult.setBounds(775, 362,125,25);
        addanotherResult.addActionListener(this);

        //Setting up the create button
        createButton.setBounds(535, 520, 200, 35);
        createButton.addActionListener(this);

        //Adding all the elements to the frame
        mainFrame.add(goBack);
        mainFrame.add(titleLabel);
        mainFrame.add(fnameLabel);
        mainFrame.add(lnameLabel);
        mainFrame.add(dobLabel);
        mainFrame.add(ModulesTakingLabel);
        mainFrame.add(AllResultsLabel);
        mainFrame.add(StartYrLabel);
        mainFrame.add(EndYrLabel);
        mainFrame.add(fnameInput);
        mainFrame.add(lnameInput);
        mainFrame.add(dobInput);
        mainFrame.add(ModulesTakingInput);
        mainFrame.add(AllResultsInput);
        mainFrame.add(StartYrInput);
        mainFrame.add(EndYrInput);
        mainFrame.add(addanotherModule);
        mainFrame.add(addanotherResult);
        mainFrame.add(createButton);
    }

    /**
     * Invoked when an action occurs.
     *
     * @param e the event to be processed
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        //Method for when the go back button is pressed that returns the user to the create home page
        if (e.getSource()==goBack){
            mainFrame.getContentPane().removeAll();
            mainFrame.repaint();
            GUICreateHome createhomepage = new GUICreateHome(this.mainFrame);
        }

        //Method for when the adding a module button is clicked that checks the module code is valid and then adds it to the array
        if (e.getSource()==addanotherModule){
            String inputtedModule = ModulesTakingInput.getText();
            try {
                boolean exists = quickMethods.checkIfInFile("codes/modulecodes.csv",inputtedModule);
                boolean correct = true;
                if(!exists){
                    JOptionPane.showMessageDialog(null, "That is not a valid Module code, make sure you have created the module first. Error Code: 50X10", "Oops", JOptionPane.ERROR_MESSAGE);
                    correct = false;
                }
                for (int i = 0; i < this.ModulesTaking.length; i++) {
                    if(this.ModulesTaking[i].equals(inputtedModule)){
                        JOptionPane.showMessageDialog(null, "You've already added this code. Error Code: 256X01", "Oops", JOptionPane.ERROR_MESSAGE);
                        correct = false;
                    }
                }
                if (correct){
                    this.ModulesTaking = AddToArray.string(this.ModulesTaking, inputtedModule);
                    ModulesTakingInput.setText("");
                }
            }
            catch (FileNotFoundException ex) {
                JOptionPane.showMessageDialog(null, "You are missing a file! Error Code: 1000X10", "Oops", JOptionPane.ERROR_MESSAGE);
            }
        }

        //Method for when the adding a result button is clicked that checks the result code is valid and then adds it to the array
        if (e.getSource()==addanotherResult){
            String inputtedModule = AllResultsInput.getText();
            try {
                boolean exists = quickMethods.checkIfInFile("codes/resultcodes.csv",inputtedModule);
                boolean correct = true;
                if(!exists){
                    JOptionPane.showMessageDialog(null, "That is not a valid Result code, make sure you have created the result first. Error Code: 50X20", "Oops", JOptionPane.ERROR_MESSAGE);
                    correct = false;
                }
                for (int i = 0; i < this.Allresults.length; i++) {
                    if(this.Allresults[i].equals(inputtedModule)){
                        JOptionPane.showMessageDialog(null, "You've already added this code. Error Code: 256X01", "Oops", JOptionPane.ERROR_MESSAGE);
                        correct = false;
                    }
                }
                if (correct){
                    this.Allresults = AddToArray.string(this.Allresults, inputtedModule);
                    AllResultsInput.setText("");
                    System.out.println("added");
                }
            }
            catch (FileNotFoundException ex) {
                JOptionPane.showMessageDialog(null, "You are missing a file! Error Code: 1000X20", "Oops", JOptionPane.ERROR_MESSAGE);
            }

        }

        //Method for when the create button is clicked that takes all the information from the form, checks it for errors and then makes the object
        if (e.getSource()==createButton){
            boolean anyInvalid = false;

            //Checking required fields
            if (fnameInput.getText().equals("") || lnameInput.getText().equals("") || dobInput.getText().equals("") || StartYrInput.getText().equals("") || EndYrInput.getText().equals("")){
                JOptionPane.showMessageDialog(null, "You've left a required field blank. Error Code: 7", "Oops", JOptionPane.ERROR_MESSAGE);
                anyInvalid = true;
            }

            this.fname=fnameInput.getText();
            this.lname=lnameInput.getText();

            //Checking Date of Birth
            if(!anyInvalid) {
                boolean DOBValid = quickMethods.checkDOB(dobInput.getText());
                if (DOBValid) {
                    this.dob = dobInput.getText();
                } else {
                    JOptionPane.showMessageDialog(null, "Date of birth not valid, please use the format DD/MM/YYYY. Error Code: 500", "Oops", JOptionPane.ERROR_MESSAGE);
                    anyInvalid = true;
                }
            }


            //checking module code exists
            if(!anyInvalid && ModulesTakingInput.getText().length()>0) {
                String inputtedModule = ModulesTakingInput.getText();
                try {
                    boolean exists = quickMethods.checkIfInFile("codes/modulecodes.csv", inputtedModule);
                    if (!exists) {
                        JOptionPane.showMessageDialog(null, "That is not a valid Module code, make sure you have created the module first. Error Code: 50X10", "Oops", JOptionPane.ERROR_MESSAGE);
                        anyInvalid = true;
                    }
                    for (int i = 0; i < this.ModulesTaking.length; i++) {
                        if (this.ModulesTaking[i].equals(inputtedModule)) {
                            JOptionPane.showMessageDialog(null, "You've already added this code. Error Code: 256X01", "Oops", JOptionPane.ERROR_MESSAGE);
                            anyInvalid=true;
                        }
                    }
                    if (!anyInvalid) {
                        this.ModulesTaking = AddToArray.string(this.ModulesTaking, inputtedModule);
                    }
                } catch (FileNotFoundException ex) {
                    JOptionPane.showMessageDialog(null, "You are missing a file! Error Code: 1000X10", "Oops", JOptionPane.ERROR_MESSAGE);
                }
            }

            //checking result code exists
            if(!anyInvalid && AllResultsInput.getText().length()>0){
                String inputtedModule = AllResultsInput.getText();
                try {
                    boolean exists = quickMethods.checkIfInFile("codes/resultcodes.csv",inputtedModule);
                    if(!exists){
                        JOptionPane.showMessageDialog(null, "That is not a valid Result code, make sure you have created the result first. Error Code: 50X20", "Oops", JOptionPane.ERROR_MESSAGE);
                        anyInvalid = true;
                    }
                    for (int i = 0; i < this.Allresults.length; i++) {
                        if(this.Allresults[i].equals(inputtedModule)){
                            JOptionPane.showMessageDialog(null, "You've already added this code. Error Code: 256X01", "Oops", JOptionPane.ERROR_MESSAGE);
                            anyInvalid = true;
                        }
                    }
                    if (!anyInvalid){
                        this.Allresults = AddToArray.string(this.Allresults, inputtedModule);
                    }
                }
                catch (FileNotFoundException ex) {
                    JOptionPane.showMessageDialog(null, "You are missing a file! Error Code: 1000X20", "Oops", JOptionPane.ERROR_MESSAGE);
                }
            }

            //checking start date and end date are valid
            if(!anyInvalid) {
                try {
                    int startYrAsInt = Integer.parseInt(StartYrInput.getText());
                    int endYrAsInt = Integer.parseInt(EndYrInput.getText());
                    if (startYrAsInt > 1900 && endYrAsInt > 1900) {
                        this.StartYr = startYrAsInt;
                        this.EndYr = endYrAsInt;
                    } else {
                        JOptionPane.showMessageDialog(null, "Start Year or Final Year not valid. Error Code: 500", "Oops", JOptionPane.ERROR_MESSAGE);
                        anyInvalid = true;
                    }
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(null, "Start Year or Final Year not valid. Error Code: 500", "Oops", JOptionPane.ERROR_MESSAGE);
                    anyInvalid = true;
                }
            }

            //creating the object
            if(!anyInvalid){
                try {
                    Student newStudent = new Student(this.fname, this.lname, this.dob, this.ModulesTaking, this.Allresults, this.StartYr, this.EndYr);
                    JOptionPane.showMessageDialog(null, "Student was created successfully! New student number: " + newStudent.getStudentNum(), "Success!", JOptionPane.INFORMATION_MESSAGE);
                    mainFrame.getContentPane().removeAll();
                    mainFrame.repaint();
                    GUICreateHome createhomepage = new GUICreateHome(this.mainFrame);

                } catch (IOException | ClassNotFoundException ex) {
                    JOptionPane.showMessageDialog(null, "An error occured. Error Code: 4000X10", "Oops", JOptionPane.ERROR_MESSAGE);
                    anyInvalid = true;
                }
            }
        }
    }
}
