import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.IOException;

public class GUICreateStudent implements ActionListener {
    private  JFrame mainFrame;
    private  JButton goBack = factory.makeFlatButton("BACK");
    private  JLabel titleLabel = new JLabel("Create Student");

    private JLabel fnameLabel = new JLabel("*First name:");
    private JTextField fnameInput = new JTextField();

    private JLabel lnameLabel = new JLabel("*Last name:");
    private JTextField lnameInput = new JTextField();

    private JLabel dobLabel = new JLabel("*Date of birth:");
    private JTextField dobInput = new JTextField();

    private JLabel ModulesTakingLabel = new JLabel("Module codes:");
    private JTextField ModulesTakingInput = new JTextField();
    private JButton addanotherModule = factory.makeFlatButton("Add another");

    private JLabel AllResultsLabel = new JLabel("Result Codes:");
    private JTextField AllResultsInput = new JTextField();
    private JButton addanotherResult = factory.makeFlatButton("Add another");

    private JLabel StartYrLabel = new JLabel("*Start Year:");
    private JTextField StartYrInput = new JTextField();

    private JLabel EndYrLabel = new JLabel("*Final Year:");
    private JTextField EndYrInput = new JTextField();

    private JButton createButton = factory.makeFlatButton("Create");

    ////////////////////Object variables//////////////////////////////
    private String fname;
    private String lname;
    private String dob;
    private String[] ModulesTaking = new String[0];
    private String[] Allresults = new String[0];
    private int StartYr;
    private int EndYr;


    public GUICreateStudent(JFrame mainFrame) {
        this.mainFrame = mainFrame;
        this.mainFrame.setTitle("EHYou - Create Student");

        //Setting up the back Button
        goBack.setBounds(0, 0,100,50);
        goBack.addActionListener(this);

        titleLabel.setBounds(450, 30,450,110);
        titleLabel.setFont(new Font("Georgia", Font.PLAIN,55));


        int xLabel = 400;
        fnameLabel.setBounds(xLabel, 150,100,50);
        lnameLabel.setBounds(xLabel, 200,100,50);
        dobLabel.setBounds(xLabel, 250,100,50);
        ModulesTakingLabel.setBounds(xLabel, 300,100,50);
        AllResultsLabel.setBounds(xLabel, 350,100,50);
        StartYrLabel.setBounds(xLabel, 400,100,50);
        EndYrLabel.setBounds(xLabel, 450,100,50);

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

        addanotherModule.setBounds(775, 312,125,25);
        addanotherModule.addActionListener(this);

        addanotherResult.setBounds(775, 362,125,25);
        addanotherResult.addActionListener(this);

        createButton.setBounds(535, 520, 200, 35);
        createButton.addActionListener(this);

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

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource()==goBack){
            mainFrame.getContentPane().removeAll();
            mainFrame.repaint();
            GUICreateHome createhomepage = new GUICreateHome(this.mainFrame);
        }

        if (e.getSource()==addanotherModule){
            String inputtedModule = ModulesTakingInput.getText();
            try {
                boolean exists = quickMethods.checkIfInFile("codes/modulecodes.csv",inputtedModule);
                boolean correct = true;
                if(exists == false){
                    JOptionPane.showMessageDialog(null, "That is not a valid Module code, make sure you have created the module first. Error Code: 50X10", "Oops", JOptionPane.ERROR_MESSAGE);
                    correct = false;
                }
                for (int i = 0; i < this.ModulesTaking.length; i++) {
                    if(this.ModulesTaking[i].equals(inputtedModule)){
                        JOptionPane.showMessageDialog(null, "You've already added this code. Error Code: 256X01", "Oops", JOptionPane.ERROR_MESSAGE);
                        correct = false;
                    }
                }
                if (correct==true){
                    this.ModulesTaking = AddToArray.string(this.ModulesTaking, inputtedModule);
                    ModulesTakingInput.setText("");
                }
            }
            catch (FileNotFoundException ex) {
                JOptionPane.showMessageDialog(null, "You are missing a file! Error Code: 1000X10", "Oops", JOptionPane.ERROR_MESSAGE);
            }

        }

        if (e.getSource()==addanotherResult){
            String inputtedModule = AllResultsInput.getText();
            try {
                boolean exists = quickMethods.checkIfInFile("codes/resultcodes.csv",inputtedModule);
                boolean correct = true;
                if(exists == false){
                    JOptionPane.showMessageDialog(null, "That is not a valid Result code, make sure you have created the result first. Error Code: 50X20", "Oops", JOptionPane.ERROR_MESSAGE);
                    correct = false;
                }
                for (int i = 0; i < this.Allresults.length; i++) {
                    if(this.Allresults[i].equals(inputtedModule)){
                        JOptionPane.showMessageDialog(null, "You've already added this code. Error Code: 256X01", "Oops", JOptionPane.ERROR_MESSAGE);
                        correct = false;
                    }
                }
                if (correct==true){
                    this.Allresults = AddToArray.string(this.Allresults, inputtedModule);
                    AllResultsInput.setText("");
                    System.out.println("added");
                }
            }
            catch (FileNotFoundException ex) {
                JOptionPane.showMessageDialog(null, "You are missing a file! Error Code: 1000X20", "Oops", JOptionPane.ERROR_MESSAGE);
            }

        }

        if (e.getSource()==createButton){
            boolean anyInvalid = false;

            //Checking required fields
            if (fnameInput.getText().equals("") || lnameInput.getText().equals("") || dobInput.getText().equals("") || StartYrInput.getText().equals("") || EndYrInput.getText().equals("")){
                JOptionPane.showMessageDialog(null, "You've left a required field blank. Error Code: 7", "Oops", JOptionPane.ERROR_MESSAGE);
                anyInvalid = true;
            }

            this.fname=fnameInput.getText();
            this.lname=lnameInput.getText();

            StringBuilder day = new StringBuilder();
            StringBuilder month = new StringBuilder();
            StringBuilder year = new StringBuilder();
            boolean slashcorrect1 = false;
            boolean slashcorrect2 = false;

            //Checking date length
            if((dobInput.getText().length()!= 10) && !anyInvalid){
                JOptionPane.showMessageDialog(null, "Date of birth not valid, please use the format DD/MM/YYYY. Error Code: 500", "Oops", JOptionPane.ERROR_MESSAGE);
                anyInvalid=true;
            }

            //checking date format
            try {
                if (!anyInvalid) {
                    for (int i = 0; i < 11; i++) {
                        if (i == 0 || i == 1) {
                            day.append(dobInput.getText().charAt(i));
                        }
                        if (i == 2) {
                            int comparison = Character.compare(dobInput.getText().charAt(i), '/');
                            if (comparison == 0) {
                                slashcorrect1 = true;
                            }
                        }
                        if (i == 3 || i == 4) {
                            month.append(dobInput.getText().charAt(i));
                        }
                        if (i == 5) {
                            int comparison = Character.compare(dobInput.getText().charAt(i), '/');
                            if (comparison == 0) {
                                slashcorrect2 = true;
                            }
                        }
                        if (i == 6 || i == 7 || i == 8 || i == 9) {
                            year.append(dobInput.getText().charAt(i));
                        }
                    }

                    int dayAsNum = Integer.parseInt(day.toString());
                    int monthAsNum = Integer.parseInt(month.toString());
                    int yearAsNum = Integer.parseInt(year.toString());

                    if (!slashcorrect1 || !slashcorrect2 || dayAsNum > 31 || monthAsNum > 13 || yearAsNum < 1900) {
                        JOptionPane.showMessageDialog(null, "Date of birth not valid, please use the format DD/MM/YYYY. Error Code: 500", "Oops", JOptionPane.ERROR_MESSAGE);
                        anyInvalid = true;
                    }

                    if ((monthAsNum == 4 || monthAsNum == 6 || monthAsNum == 9 || monthAsNum == 11) && !anyInvalid) {
                        if (dayAsNum > 30) {
                            JOptionPane.showMessageDialog(null, "Date of birth not valid, please use the format DD/MM/YYYY. Error Code: 500", "Oops", JOptionPane.ERROR_MESSAGE);
                            anyInvalid = true;
                        }
                    }

                    if (monthAsNum == 2 && !anyInvalid) {
                        if (dayAsNum > 29) {
                            JOptionPane.showMessageDialog(null, "Date of birth not valid, please use the format DD/MM/YYYY. Error Code: 500", "Oops", JOptionPane.ERROR_MESSAGE);
                            anyInvalid = true;
                        }
                    } else {
                        this.dob = dobInput.getText();
                    }

                }
            }
            catch (NumberFormatException ex){
                JOptionPane.showMessageDialog(null, "Date of birth not valid, please use the format DD/MM/YYYY. Error Code: 500", "Oops", JOptionPane.ERROR_MESSAGE);
                anyInvalid = true;
            }

            //checking module code exists
            if(!anyInvalid && ModulesTakingInput.getText().length()>0) {
                String inputtedModule = ModulesTakingInput.getText();
                try {
                    boolean exists = quickMethods.checkIfInFile("codes/modulecodes.csv", inputtedModule);
                    boolean correct = true;
                    if (exists == false) {
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
                    boolean correct = true;
                    if(exists == false){
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

                } catch (IOException ex) {
                    JOptionPane.showMessageDialog(null, "An error occured. Error Code: 4000X10", "Oops", JOptionPane.ERROR_MESSAGE);
                    anyInvalid = true;
                }
            }

        }
    }
}
