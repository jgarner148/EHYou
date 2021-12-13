import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.IOException;

/**
 * The class that houses the GUI for creating a Module
 */
public class GUICreateModule implements ActionListener{
    //Setting up the global GUI elements
    private final JFrame mainFrame;
    private final JButton goBack = factory.makeFlatButton("BACK");
    private final JLabel titleLabel = new JLabel("Create Module");

    private final JLabel modNameLabel = new JLabel("*Module name:");
    private final JTextField modNameInput = new JTextField();

    private final JLabel modCodeLabel = new JLabel("*Module code: ");
    private final JTextField modCodeInput = new JTextField();

    private final JLabel studentsLabel = new JLabel("Students: ");
    private final JTextField studentsInput = new JTextField();
    private final JButton anotherStudent = factory.makeFlatButton("Add Another");

    private final JLabel teachersLabel = new JLabel("Teachers: ");
    private final JTextField teachersInput = new JTextField();
    private final JButton anotherTeacher = factory.makeFlatButton("Add Another");

    private final JLabel moderatorLabel = new JLabel("Moderator: ");
    private final JTextField moderatorInput = new JTextField();

    private final JButton createButton = factory.makeFlatButton("Create");

    //Setting up the global variables that will have the values of the module being created assigned to them.
    private String modName;
    private String modCode;
    private String[] studentsTaking = new String[0];
    private String[] teachers = new String[0];
    private String moderator = "";

    /**
     * Constructor for the class that will be called when wanting to display the GUI for creating a module
     * @param mainFrame The master frame that is already being displayed to the user when the method is called
     */
    public GUICreateModule(JFrame mainFrame) {
        //Setting up the main frame
        this.mainFrame = mainFrame;
        this.mainFrame.setTitle("EHYou - Create Module");

        //Setting up the back Button
        goBack.setBounds(0, 0,100,50);
        goBack.addActionListener(this);

        //Setting up the title label
        titleLabel.setBounds(450, 30,450,110);
        titleLabel.setFont(new Font("Georgia", Font.PLAIN,55));

        //Setting label locations
        int xLabel = 400;
        int wLabel = 100;
        int hLabel = 50;
        modNameLabel.setBounds(xLabel, 150,wLabel,hLabel);
        modCodeLabel.setBounds(xLabel, 200,wLabel,hLabel);
        studentsLabel.setBounds(xLabel, 250,wLabel,hLabel);
        teachersLabel.setBounds(xLabel,300,wLabel,hLabel);
        moderatorLabel.setBounds(xLabel, 350,wLabel,hLabel);

        //Setting input locations
        int xInput = 510;
        int wInput = 250;
        int hInput = 30;
        modNameInput.setBounds(xInput, 160,wInput,hInput);
        modCodeInput.setBounds(xInput, 210,wInput,hInput);
        studentsInput.setBounds(xInput, 260,wInput,hInput);
        teachersInput.setBounds(xInput,310,wInput,hInput);
        moderatorInput.setBounds(xInput,360,wInput,hInput);

        //Setting up the add another student button
        anotherStudent.setBounds(775, 262, 125,25);
        anotherStudent.addActionListener(this);

        //Setting up the add another teacher button
        anotherTeacher.setBounds(775, 312, 125,25);
        anotherTeacher.addActionListener(this);

        //Setting up the create button
        createButton.setBounds(535, 420, 200, 35);
        createButton.addActionListener(this);

        //Adding all the elements to the frame
        mainFrame.add(goBack);
        mainFrame.add(titleLabel);
        mainFrame.add(modNameLabel);
        mainFrame.add(modCodeLabel);
        mainFrame.add(modNameInput);
        mainFrame.add(modCodeInput);
        mainFrame.add(studentsLabel);
        mainFrame.add(studentsInput);
        mainFrame.add(anotherStudent);
        mainFrame.add(teachersLabel);
        mainFrame.add(teachersInput);
        mainFrame.add(anotherTeacher);
        mainFrame.add(moderatorLabel);
        mainFrame.add(moderatorInput);
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

        //Method for when the adding a student button is clicked that checks the student code is valid and then adds it to the array
        if(e.getSource()==anotherStudent){
            String inputtedModule = studentsInput.getText();
            try {
                boolean exists = quickMethods.checkIfInFile("codes/studentNumbers.csv",inputtedModule);
                boolean correct = true;
                if(!exists){
                    JOptionPane.showMessageDialog(null, "That is not a valid Student code, make sure you have created the student first. Error Code: 50X30", "Oops", JOptionPane.ERROR_MESSAGE);
                    correct = false;
                }
                for (int i = 0; i < this.studentsTaking.length; i++) {
                    if(this.studentsTaking[i].equals(inputtedModule)){
                        JOptionPane.showMessageDialog(null, "You've already added this code. Error Code: 256X01", "Oops", JOptionPane.ERROR_MESSAGE);
                        correct = false;
                    }
                }
                if (correct){
                    this.studentsTaking = AddToArray.string(this.studentsTaking, inputtedModule);
                    studentsInput.setText("");
                }
            }
            catch (FileNotFoundException ex) {
                JOptionPane.showMessageDialog(null, "You are missing a file! Error Code: 1000X30", "Oops", JOptionPane.ERROR_MESSAGE);
            }
        }

        //Methods for when the adding a teacher button is clicked that checks the tutor code is valid and then adds it to the array
        if(e.getSource()==anotherTeacher){
            String inputtedModule = teachersInput.getText();
            try {
                boolean exists = quickMethods.checkIfInFile("codes/staffcodes.csv",inputtedModule);
                boolean correct = true;
                if(!exists){
                    JOptionPane.showMessageDialog(null, "That is not a valid Staff code, make sure you have created the Teacher first. Error Code: 50X40", "Oops", JOptionPane.ERROR_MESSAGE);
                    correct = false;
                }
                for (int i = 0; i < this.teachers.length; i++) {
                    if(this.teachers[i].equals(inputtedModule)){
                        JOptionPane.showMessageDialog(null, "You've already added this code. Error Code: 256X01", "Oops", JOptionPane.ERROR_MESSAGE);
                        correct = false;
                    }
                }
                if (correct){
                    this.teachers = AddToArray.string(this.teachers, inputtedModule);
                    teachersInput.setText("");
                }
            }
            catch (FileNotFoundException ex) {
                JOptionPane.showMessageDialog(null, "You are missing a file! Error Code: 1000X40", "Oops", JOptionPane.ERROR_MESSAGE);
            }
        }

        //Method for when the create button is clicked that takes all the information from the form, checks it for errors and then makes the object
        if(e.getSource()==createButton){
            boolean anyInvalid = false;

            //Checking required fields are filled
            if(modNameInput.getText().equals("")||modCodeInput.getText().equals("")){
                JOptionPane.showMessageDialog(null, "You've left a required field blank. Error Code: 7", "Oops", JOptionPane.ERROR_MESSAGE);
                anyInvalid = true;
            }

            this.modName=modNameInput.getText();

            //checking that the module code isn't already assigned to another module
            if(!anyInvalid) {
                String inputtedModule = modCodeInput.getText();
                try {
                    boolean exists = quickMethods.checkIfInFile("codes/modulecodes.csv", inputtedModule);
                    if (exists) {
                        JOptionPane.showMessageDialog(null, "That module code is already taken. Error Code: 85X10", "Oops", JOptionPane.ERROR_MESSAGE);
                        anyInvalid = true;
                    }

                    if (!exists) {
                        this.modCode = modCodeInput.getText();
                    }
                } catch (FileNotFoundException ex) {
                    JOptionPane.showMessageDialog(null, "You are missing a file! Error Code: 1000X10", "Oops", JOptionPane.ERROR_MESSAGE);
                    anyInvalid = true;
                }
            }

            //checking student code
            if(!anyInvalid && studentsInput.getText().length()>0){
                String inputtedModule = studentsInput.getText();
                try {
                    boolean exists = quickMethods.checkIfInFile("codes/studentNumbers.csv",inputtedModule);
                    boolean correct = true;
                    if(!exists){
                        JOptionPane.showMessageDialog(null, "That is not a valid Student code, make sure you have created the student first. Error Code: 50X30", "Oops", JOptionPane.ERROR_MESSAGE);
                        anyInvalid = true;
                        correct = false;
                    }
                    for (int i = 0; i < this.studentsTaking.length; i++) {
                        if(this.studentsTaking[i].equals(inputtedModule)){
                            JOptionPane.showMessageDialog(null, "You've already added this code. Error Code: 256X01", "Oops", JOptionPane.ERROR_MESSAGE);
                            anyInvalid = true;
                            correct = false;
                        }
                    }
                    if (correct){
                        this.studentsTaking = AddToArray.string(this.studentsTaking, inputtedModule);
                        studentsInput.setText("");
                    }
                }
                catch (FileNotFoundException ex) {
                    JOptionPane.showMessageDialog(null, "You are missing a file! Error Code: 1000X30", "Oops", JOptionPane.ERROR_MESSAGE);
                    anyInvalid = true;
                }
            }

            //checking teacher code
            if(!anyInvalid && teachersInput.getText().length()>0){
                String inputtedModule = teachersInput.getText();
                try {
                    boolean exists = quickMethods.checkIfInFile("codes/staffcodes.csv",inputtedModule);
                    boolean correct = true;
                    if(!exists){
                        JOptionPane.showMessageDialog(null, "That is not a valid Staff code, make sure you have created the Teacher first. Error Code: 50X40", "Oops", JOptionPane.ERROR_MESSAGE);
                        correct = false;
                        anyInvalid = true;
                    }
                    for (int i = 0; i < this.teachers.length; i++) {
                        if(this.teachers[i].equals(inputtedModule)){
                            JOptionPane.showMessageDialog(null, "You've already added this code. Error Code: 256X01", "Oops", JOptionPane.ERROR_MESSAGE);
                            correct = false;
                            anyInvalid = true;
                        }
                    }
                    if (correct){
                        this.teachers = AddToArray.string(this.teachers, inputtedModule);
                        teachersInput.setText("");
                    }
                }
                catch (FileNotFoundException ex) {
                    JOptionPane.showMessageDialog(null, "You are missing a file! Error Code: 1000X40", "Oops", JOptionPane.ERROR_MESSAGE);
                    anyInvalid = true;
                }
            }

            //checking teacher code for moderator
            if(!anyInvalid && moderatorInput.getText().length()>0){
                String inputtedModule = moderatorInput.getText();
                try {
                    boolean exists = quickMethods.checkIfInFile("codes/staffcodes.csv",inputtedModule);
                    boolean correct = true;
                    if(!exists){
                        JOptionPane.showMessageDialog(null, "That is not a valid Staff code, make sure you have created the Teacher first. Error Code: 50X40", "Oops", JOptionPane.ERROR_MESSAGE);
                        correct = false;
                        anyInvalid = true;
                    }
                    if (correct){
                        this.moderator= moderatorInput.getText();
                    }
                }
                catch (FileNotFoundException ex) {
                    JOptionPane.showMessageDialog(null, "You are missing a file! Error Code: 1000X40", "Oops", JOptionPane.ERROR_MESSAGE);
                    anyInvalid = true;
                }
            }

            //Creating the object
            if(!anyInvalid){
                try{
                    Module newModule = new Module(this.modName,this.modCode,this.studentsTaking,this.teachers, this.moderator);
                    JOptionPane.showMessageDialog(null, "Module was created successfully!" , "Success!", JOptionPane.INFORMATION_MESSAGE);
                    mainFrame.getContentPane().removeAll();
                    mainFrame.repaint();
                    GUICreateHome createhomepage = new GUICreateHome(this.mainFrame);
                } catch (IOException | ClassNotFoundException ioException) {
                    JOptionPane.showMessageDialog(null, "An error occured. Error Code: 4000X20", "Oops", JOptionPane.ERROR_MESSAGE);
                    anyInvalid = true;
                }
            }
        }
    }
}
