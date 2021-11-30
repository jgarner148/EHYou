import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Calendar;

public class GUICreateTutor implements ActionListener {
    private JFrame mainFrame;
    private JButton goBack = factory.makeFlatButton("BACK");
    private JLabel titleLabel = new JLabel("Create Tutor");

    private JLabel fnameLabel = new JLabel("*First name:");
    private JTextField fnameInput = new JTextField();

    private JLabel lnameLabel = new JLabel("*Last name:");
    private JTextField lnameInput = new JTextField();

    private JLabel dobLabel = new JLabel("*Date of birth:");
    private JTextField dobInput = new JTextField();

    private JLabel StartYrLabel = new JLabel("*Start Year:");
    private JTextField StartYrInput = new JTextField();

    private JLabel salaryLabel = new JLabel("*Salary");
    private JTextField salaryInput = new JTextField();

    private JLabel officeLabel = new JLabel("*Office");
    private JTextField officeInput = new JTextField();

    private JLabel degreeLabel = new JLabel("*Degree");
    private JTextField degreeInput = new JTextField();

    private JLabel modulesTeachingLabel = new JLabel("Teaching:");
    private JTextField moduleTeachingInput = new JTextField();
    private JButton addAnothermoduleTeaching = factory.makeFlatButton("Add Another");

    private JLabel modulesMonitoringLabel = new JLabel("Moderating:");
    private JTextField modulesMonitoringInput = new JTextField();
    private JButton addAnotherModuleModerating = factory.makeFlatButton("Add Another");

    private JButton createButton = factory.makeFlatButton("Create");

    //////////////////////////////////////////////////////////////////////////////////////////////
    private String fname;
    private String lname;
    private String dob;
    private int startyr;
    private int salary;
    private String office;
    private String degree;
    private String[] modulesTeaching = new String[0];
    private String[] modulesModerating = new String[0];


    public GUICreateTutor(JFrame mainFrame) {
        this.mainFrame = mainFrame;
        this.mainFrame.setTitle("EHYou - Create Tutor");

        //Setting up the back Button
        goBack.setBounds(0, 0, 100, 50);
        goBack.addActionListener((ActionListener) this);

        titleLabel.setBounds(450, 30, 450, 110);
        titleLabel.setFont(new Font("Georgia", Font.PLAIN, 55));

        int xLabel = 400;
        fnameLabel.setBounds(xLabel, 150, 100, 50);
        lnameLabel.setBounds(xLabel, 200, 100, 50);
        dobLabel.setBounds(xLabel, 250, 100, 50);
        StartYrLabel.setBounds(xLabel, 300, 100, 50);
        salaryLabel.setBounds(xLabel, 350, 100, 50);
        officeLabel.setBounds(xLabel, 400, 100, 50);
        degreeLabel.setBounds(xLabel, 450, 100, 50);
        modulesTeachingLabel.setBounds(xLabel, 500, 100, 50);
        modulesMonitoringLabel.setBounds(xLabel, 550, 100, 50);

        int xInput = 510;
        int wInput = 250;
        int hInput = 30;
        fnameInput.setBounds(xInput, 160, wInput, hInput);
        lnameInput.setBounds(xInput, 210, wInput, hInput);
        dobInput.setBounds(xInput, 260, wInput, hInput);
        StartYrInput.setBounds(xInput, 310, wInput, hInput);
        salaryInput.setBounds(xInput, 360, wInput, hInput);
        officeInput.setBounds(xInput, 410, wInput, hInput);
        degreeInput.setBounds(xInput, 460, wInput, hInput);
        moduleTeachingInput.setBounds(xInput, 510, wInput, hInput);
        modulesMonitoringInput.setBounds(xInput, 560, wInput, hInput);

        addAnothermoduleTeaching.setBounds(775, 512, 125, 25);
        addAnothermoduleTeaching.addActionListener(this);

        addAnotherModuleModerating.setBounds(775, 562, 125, 25);
        addAnotherModuleModerating.addActionListener(this);

        createButton.setBounds(535, 620, 200, 35);
        createButton.addActionListener(this);

        mainFrame.add(goBack);
        mainFrame.add(titleLabel);

        mainFrame.add(fnameLabel);
        mainFrame.add(lnameLabel);
        mainFrame.add(dobLabel);
        mainFrame.add(fnameInput);
        mainFrame.add(lnameInput);
        mainFrame.add(dobInput);
        mainFrame.add(StartYrLabel);
        mainFrame.add(StartYrInput);
        mainFrame.add(salaryLabel);
        mainFrame.add(salaryInput);
        mainFrame.add(officeLabel);
        mainFrame.add(officeInput);
        mainFrame.add(degreeLabel);
        mainFrame.add(degreeInput);
        mainFrame.add(modulesTeachingLabel);
        mainFrame.add(moduleTeachingInput);
        mainFrame.add(addAnothermoduleTeaching);
        mainFrame.add(modulesMonitoringLabel);
        mainFrame.add(modulesMonitoringInput);
        mainFrame.add(addAnotherModuleModerating);

        mainFrame.add(createButton);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == goBack) {
            mainFrame.getContentPane().removeAll();
            mainFrame.repaint();
            GUICreateHome createhomepage = new GUICreateHome(this.mainFrame);
        }

        if (e.getSource() == addAnothermoduleTeaching) {
            String inputtedModule = moduleTeachingInput.getText();
            try {
                boolean exists = quickMethods.checkIfInFile("codes/modulecodes.csv", inputtedModule);
                boolean correct = true;
                if (exists == false) {
                    JOptionPane.showMessageDialog(null, "That is not a valid Module, make sure you have created the module first. Error Code: 50X10", "Oops", JOptionPane.ERROR_MESSAGE);
                    correct = false;
                }
                for (int i = 0; i < this.modulesTeaching.length; i++) {
                    if (this.modulesTeaching[i].equals(inputtedModule)) {
                        JOptionPane.showMessageDialog(null, "You've already added this code. Error Code: 256X01", "Oops", JOptionPane.ERROR_MESSAGE);
                        correct = false;
                    }
                }
                if (correct == true) {
                    this.modulesTeaching = AddToArray.string(this.modulesTeaching, inputtedModule);
                    moduleTeachingInput.setText("");
                }
            } catch (FileNotFoundException ex) {
                JOptionPane.showMessageDialog(null, "You are missing a file! Error Code: 1000X30", "Oops", JOptionPane.ERROR_MESSAGE);
            }
        }

        if(e.getSource()==addAnotherModuleModerating){
            String inputtedModule = modulesMonitoringInput.getText();
            try {
                boolean exists = quickMethods.checkIfInFile("codes/modulecodes.csv",inputtedModule);
                boolean correct = true;
                if(exists == false){
                    JOptionPane.showMessageDialog(null, "That is not a valid Module code, make sure you have created the module first. Error Code: 50X10", "Oops", JOptionPane.ERROR_MESSAGE);
                    correct = false;
                }
                for (int i = 0; i < this.modulesModerating.length; i++) {
                    if(this.modulesModerating[i].equals(inputtedModule)){
                        JOptionPane.showMessageDialog(null, "You've already added this code. Error Code: 256X01", "Oops", JOptionPane.ERROR_MESSAGE);
                        correct = false;
                    }
                }
                if (correct==true){
                    this.modulesModerating = AddToArray.string(this.modulesModerating, inputtedModule);
                    modulesMonitoringInput.setText("");
                }
            }
            catch (FileNotFoundException ex) {
                JOptionPane.showMessageDialog(null, "You are missing a file! Error Code: 1000X30", "Oops", JOptionPane.ERROR_MESSAGE);
            }
        }

        if(e.getSource()==createButton){
            boolean anyInvalid = false;

            //checking required fields
            if(fnameInput.getText().equals("")||lnameInput.getText().equals("")||dobInput.getText().equals("")||StartYrInput.getText().equals("")||salaryInput.getText().equals("")||officeInput.getText().equals("")||degreeInput.getText().equals("")){
                JOptionPane.showMessageDialog(null, "You've left a required field blank. Error Code: 7", "Oops", JOptionPane.ERROR_MESSAGE);
                anyInvalid = true;
            }

            this.fname = fnameInput.getText();
            this.lname = lnameInput.getText();

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

            //checking start year
            if(!anyInvalid){
                int startyrAsInt = Integer.parseInt(StartYrInput.getText());
                int currentyear = Calendar.getInstance().get(Calendar.YEAR);
                currentyear = currentyear +2;
                if(startyrAsInt>1900 && startyrAsInt<currentyear){
                    this.startyr = startyrAsInt;
                }
                else{
                    JOptionPane.showMessageDialog(null, "Start year is not valid. Error Code: 500", "Oops", JOptionPane.ERROR_MESSAGE);
                    anyInvalid = true;
                }
            }

            this.salary = Integer.parseInt(salaryInput.getText());
            this.office = officeInput.getText();
            this.degree = degreeInput.getText();

            //checking teaching module
            if(!anyInvalid && moduleTeachingInput.getText().length()>0){
                String inputtedModule = moduleTeachingInput.getText();
                try {
                    boolean exists = quickMethods.checkIfInFile("codes/modulecodes.csv", inputtedModule);
                    boolean correct = true;
                    if (exists == false) {
                        JOptionPane.showMessageDialog(null, "That is not a valid Module, make sure you have created the module first. Error Code: 50X10", "Oops", JOptionPane.ERROR_MESSAGE);
                        correct = false;
                        anyInvalid = true;
                    }
                    for (int i = 0; i < this.modulesTeaching.length; i++) {
                        if (this.modulesTeaching[i].equals(inputtedModule)) {
                            JOptionPane.showMessageDialog(null, "You've already added this code. Error Code: 256X01", "Oops", JOptionPane.ERROR_MESSAGE);
                            correct = false;
                            anyInvalid = true;
                        }
                    }
                    if (correct == true) {
                        this.modulesTeaching = AddToArray.string(this.modulesTeaching, inputtedModule);
                        moduleTeachingInput.setText("");
                    }
                } catch (FileNotFoundException ex) {
                    JOptionPane.showMessageDialog(null, "You are missing a file! Error Code: 1000X30", "Oops", JOptionPane.ERROR_MESSAGE);
                    anyInvalid = true;
                }
            }

            //checking moderating module
            if(!anyInvalid && modulesMonitoringInput.getText().length()>0){
                String inputtedModule = modulesMonitoringInput.getText();
                try {
                    boolean exists = quickMethods.checkIfInFile("codes/modulecodes.csv",inputtedModule);
                    boolean correct = true;
                    if(exists == false){
                        JOptionPane.showMessageDialog(null, "That is not a valid Module code, make sure you have created the module first. Error Code: 50X10", "Oops", JOptionPane.ERROR_MESSAGE);
                        correct = false;
                        anyInvalid = true;
                    }
                    for (int i = 0; i < this.modulesModerating.length; i++) {
                        if(this.modulesModerating[i].equals(inputtedModule)){
                            JOptionPane.showMessageDialog(null, "You've already added this code. Error Code: 256X01", "Oops", JOptionPane.ERROR_MESSAGE);
                            correct = false;
                            anyInvalid = true;
                        }
                    }
                    if (correct==true){
                        this.modulesModerating = AddToArray.string(this.modulesModerating, inputtedModule);
                        modulesMonitoringInput.setText("");
                    }
                }
                catch (FileNotFoundException ex) {
                    JOptionPane.showMessageDialog(null, "You are missing a file! Error Code: 1000X30", "Oops", JOptionPane.ERROR_MESSAGE);
                    anyInvalid = true;
                }
            }

            //creating the object
            if(!anyInvalid){
                try{
                    Tutor newTutor = new Tutor(this.fname,this.lname,this.dob,this.startyr,this.salary,this.office,this.degree,this.modulesTeaching,this.modulesModerating);
                    JOptionPane.showMessageDialog(null, "Tutor was created successfully! New Staff number: " + newTutor.getStaffID(), "Success!", JOptionPane.INFORMATION_MESSAGE);
                    mainFrame.getContentPane().removeAll();
                    mainFrame.repaint();
                    GUICreateHome createhomepage = new GUICreateHome(this.mainFrame);


                } catch (IOException | ClassNotFoundException ioException) {
                    JOptionPane.showMessageDialog(null, "An error occured. Error Code: 4000X40", "Oops", JOptionPane.ERROR_MESSAGE);
                    anyInvalid = true;
                }
            }

        }
        }
    }
