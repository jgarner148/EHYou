import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

public class GUIViewStudent implements ActionListener {
    private JFrame IDCardFrame = new JFrame();
    private Student studentBeingViewed;
    private String studentCode;
    private JLabel titleLabel = new JLabel("Student details");
    private JLabel IDLabel = new JLabel("");
    private JLabel fnameLabel = new JLabel("");
    private JButton fnameEditButton = factory.makeFlatButton("Edit");
    private JLabel lnameLabel = new JLabel("");
    private JButton lnameEditButton = factory.makeFlatButton("Edit");
    private JLabel dobLabel = new JLabel("");
    private JButton dobEditButton = factory.makeFlatButton("Edit");
    private JLabel staryYrLabel = new JLabel("");
    private JButton startYrEditButton = factory.makeFlatButton("Edit");
    private JLabel endYrLabel = new JLabel("");
    private JButton endYrEditButton = factory.makeFlatButton("Edit");
    private JLabel allModulesLabel = new JLabel("");
    private JButton allModulesAddButton = factory.makeFlatButton("Add Module");
    private JLabel allResultsLabel = new JLabel("");
    private JButton allResultsAddButton = factory.makeFlatButton("Add Result");
    private JButton calculateButton = factory.makeFlatButton("Work out average per module");
    private Font labelFont = new Font("Georgia", Font.ITALIC,15);
    private JButton deletebutton = factory.makeFlatButton("Delete");

    private JTextField editAndCalcInput = new JTextField();
    private JButton CalculateEnterButton = factory.makeFlatButton("Enter");
    private JButton editFnameEnterButton = factory.makeFlatButton("Enter");
    private JButton editLnameEnterButton = factory.makeFlatButton("Enter");
    private JButton editDOBEnterButton = factory.makeFlatButton("Enter");
    private JButton editStartYrEnterButton = factory.makeFlatButton("Enter");
    private JButton editEndYrEnterButton = factory.makeFlatButton("Enter");


    public GUIViewStudent( String studentCode) throws IOException, ClassNotFoundException {
        this.studentCode = studentCode;
        this.studentBeingViewed = getobject.student(this.studentCode);
        this.IDCardFrame.setSize(560,560);
        this.IDCardFrame.getContentPane().setBackground(new Color(165,213,220));
        this.IDCardFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE );
        this.IDCardFrame.setTitle("ID card - " + studentBeingViewed.getFname() + " " + studentBeingViewed.getLname() + " (" + studentBeingViewed.getStudentNum() + ")");
        ImageIcon logoIcon = new ImageIcon("Images/icon.png");
        this.IDCardFrame.setIconImage(logoIcon.getImage());
        this.IDCardFrame.setLayout(null);
        this.IDCardFrame.setLocationRelativeTo(null);
        this.IDCardFrame.setResizable(false);
        this.IDCardFrame.setVisible(true);

        titleLabel.setBounds(20,15,225,25);
        titleLabel.setFont(new Font("Georgia", Font.ITALIC,25));

        int xLabel = 20;
        int wLabel = 250;
        int hLabel = 25;
        IDLabel.setBounds(xLabel,50,wLabel,hLabel);
        IDLabel.setFont(labelFont);
        IDLabel.setText("Student Number: " + studentBeingViewed.getStudentNum());

        fnameLabel.setBounds(xLabel,70,wLabel,hLabel);
        fnameLabel.setFont(labelFont);
        fnameLabel.setText("First Name: " + studentBeingViewed.getFname());

        lnameLabel.setBounds(xLabel,90,wLabel,hLabel);
        lnameLabel.setFont(labelFont);
        lnameLabel.setText("Last Name: " + studentBeingViewed.getLname());

        dobLabel.setBounds(xLabel,110,wLabel,hLabel);
        dobLabel.setFont(labelFont);
        dobLabel.setText("Date of Birth: " + studentBeingViewed.getDob());

        staryYrLabel.setBounds(xLabel,130,wLabel,hLabel);
        staryYrLabel.setFont(labelFont);
        staryYrLabel.setText("Year Started: " + studentBeingViewed.getStartYr());

        endYrLabel.setBounds(xLabel,150,wLabel,hLabel);
        endYrLabel.setFont(labelFont);
        endYrLabel.setText("Last Year: " + studentBeingViewed.getEndYr());

        String[] allModules = studentBeingViewed.getModulesTaking();
        String allModulesText = "";
        for(int i = 0; i<allModules.length; i++){
            String currentcode = allModules[i];
            Module currentModule = getobject.module(currentcode);
            allModulesText = allModulesText + currentModule.getModName() + " - " + currentModule.getModCode() + "<br>";
        }

        allModulesLabel.setBounds(xLabel, 200, wLabel, 240);
        allModulesLabel.setFont(labelFont);
        allModulesLabel.setText("<html>" + "Modules:<br>" + allModulesText);

        String[] allResults = studentBeingViewed.getAllResults();
        String AllResultsText = "";
        for(int i = 0; i<allResults.length; i++){
            String currentcode = allResults[i];
            Result currentResult = getobject.result(currentcode);
            AllResultsText = AllResultsText + currentResult.getResultCode() + " - " + currentResult.getGrade() + "<br>";
        }

        allResultsLabel.setBounds(255, 200, wLabel, 240);
        allResultsLabel.setFont(labelFont);
        allResultsLabel.setText("<html>" + "Results:<br>" + AllResultsText);

        calculateButton.setBounds(xLabel, 460,200, 50);
        calculateButton.addActionListener(this);

        deletebutton.setBounds(320, 460 ,200,50);
        deletebutton.addActionListener(this);

        int xButton = 420;
        int wButton = 75;
        int hButton = 18;
        fnameEditButton.setBounds(xButton,72,wButton,hButton);
        fnameEditButton.addActionListener(this);
        lnameEditButton.setBounds(xButton,92,wButton,hButton);
        lnameEditButton.addActionListener(this);
        dobEditButton.setBounds(xButton,112,wButton,hButton);
        dobEditButton.addActionListener(this);
        startYrEditButton.setBounds(xButton,132,wButton,hButton);
        startYrEditButton.addActionListener(this);
        endYrEditButton.setBounds(xButton,152,wButton,hButton);
        endYrEditButton.addActionListener(this);

        allModulesAddButton.setBounds(50,425,130,hButton);
        allModulesAddButton.addActionListener(this);
        allResultsAddButton.setBounds(315,425,130,hButton);
        allResultsAddButton.addActionListener(this);

        IDCardFrame.add(titleLabel);

        IDCardFrame.add(IDLabel);
        IDCardFrame.add(fnameLabel);
        IDCardFrame.add(lnameLabel);
        IDCardFrame.add(dobLabel);
        IDCardFrame.add(staryYrLabel);
        IDCardFrame.add(endYrLabel);
        IDCardFrame.add(allModulesLabel);
        IDCardFrame.add(allResultsLabel);
        IDCardFrame.add(calculateButton);
        IDCardFrame.add(deletebutton);

        IDCardFrame.add(fnameEditButton);
        IDCardFrame.add(lnameEditButton);
        IDCardFrame.add(dobEditButton);
        IDCardFrame.add(startYrEditButton);
        IDCardFrame.add(endYrEditButton);
        IDCardFrame.add(allModulesAddButton);
        IDCardFrame.add(allResultsAddButton);

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        boolean alreadyOpen = false;
        if(e.getSource() == deletebutton){
            alreadyOpen = true;
            int confirmation = JOptionPane.showConfirmDialog(null, "Are you sure you want to delete " + studentBeingViewed.getFname() + " " + studentBeingViewed.getLname(), "Confirm delete", JOptionPane.YES_NO_OPTION, JOptionPane.ERROR_MESSAGE);
            if (confirmation == 0){
                String filename = "Students/" +studentBeingViewed.getStudentNum() + ".txt";
                File oldFile = new File(filename);
                oldFile.delete();
                IDCardFrame.dispatchEvent(new WindowEvent(IDCardFrame, WindowEvent.WINDOW_CLOSING));
                JOptionPane.showMessageDialog(null, "Student Deleted", "Deleted!", JOptionPane.INFORMATION_MESSAGE);

            }
        }
        if (e.getSource() == CalculateEnterButton && !alreadyOpen) {
            alreadyOpen = true;
            String inputtedText = this.editAndCalcInput.getText();
            try {
                boolean exists = quickMethods.checkIfInFile("codes/modulecodes.csv", inputtedText);
                if (!exists) {
                    JOptionPane.showMessageDialog(null, "Error with Module code, make sure it is correct. Error Code: 50X10", "Oops", JOptionPane.ERROR_MESSAGE);
                } else {
                    try {
                        int result = this.studentBeingViewed.averageGradeForModule(inputtedText);
                        String resultAsString = Integer.toString(result);
                        JOptionPane.showMessageDialog(null, "Calculation Result for " + inputtedText + ": " + resultAsString, "Complete!", JOptionPane.INFORMATION_MESSAGE);
                        this.editAndCalcInput.setText("");
                    } catch (IOException | ClassNotFoundException ex) {
                        JOptionPane.showMessageDialog(null, "Error with Module code, make sure it is correct. Error Code: 50X10", "Oops", JOptionPane.ERROR_MESSAGE);
                    }
                }
            } catch (FileNotFoundException ex) {
                JOptionPane.showMessageDialog(null, "Error with Module code, make sure it is correct. Error Code: 50X10", "Oops", JOptionPane.ERROR_MESSAGE);
            }

        }
        if(e.getSource() == editFnameEnterButton && !alreadyOpen){
            alreadyOpen = true;
            try {
                this.studentBeingViewed.setFname(this.editAndCalcInput.getText());
                this.fnameLabel.setText("First Name: " + studentBeingViewed.getFname());
                JOptionPane.showMessageDialog(null, "First Name changed successfully", "Complete!", JOptionPane.INFORMATION_MESSAGE);
                editAndCalcInput.setText("");
            } catch (IOException ex) {
                JOptionPane.showMessageDialog(null, "Error with Student object. Error Code: 4000X10", "Oops", JOptionPane.ERROR_MESSAGE);
            }
        }
        if(e.getSource() == editLnameEnterButton && !alreadyOpen){
            alreadyOpen = true;
            try {
                this.studentBeingViewed.setLname(this.editAndCalcInput.getText());
                this.lnameLabel.setText("Last Name: " + studentBeingViewed.getLname());
                JOptionPane.showMessageDialog(null, "Last Name changed successfully", "Complete!", JOptionPane.INFORMATION_MESSAGE);
                editAndCalcInput.setText("");
            } catch (IOException ex) {
                JOptionPane.showMessageDialog(null, "Error with Student object. Error Code: 4000X10", "Oops", JOptionPane.ERROR_MESSAGE);
            }
        }
        if(e.getSource() == editDOBEnterButton && !alreadyOpen){
            alreadyOpen = true;
            boolean valid = true;
            if(editAndCalcInput.getText().length()!= 10){
                JOptionPane.showMessageDialog(null, "Date of birth not valid, please use the format DD/MM/YYYY. Error Code: 500", "Oops", JOptionPane.ERROR_MESSAGE);
                valid = false;
            }
            StringBuilder day = new StringBuilder();
            StringBuilder month = new StringBuilder();
            StringBuilder year = new StringBuilder();
            boolean slashcorrect1 = false;
            boolean slashcorrect2 = false;
            try {
                if (valid) {
                    for (int i = 0; i < 11; i++) {
                        if (i == 0 || i == 1) {
                            day.append(editAndCalcInput.getText().charAt(i));
                        }
                        if (i == 2) {
                            int comparison = Character.compare(editAndCalcInput.getText().charAt(i), '/');
                            if (comparison == 0) {
                                slashcorrect1 = true;
                            }
                        }
                        if (i == 3 || i == 4) {
                            month.append(editAndCalcInput.getText().charAt(i));
                        }
                        if (i == 5) {
                            int comparison = Character.compare(editAndCalcInput.getText().charAt(i), '/');
                            if (comparison == 0) {
                                slashcorrect2 = true;
                            }
                        }
                        if (i == 6 || i == 7 || i == 8 || i == 9) {
                            year.append(editAndCalcInput.getText().charAt(i));
                        }
                    }

                    int dayAsNum = Integer.parseInt(day.toString());
                    int monthAsNum = Integer.parseInt(month.toString());
                    int yearAsNum = Integer.parseInt(year.toString());

                    if (!slashcorrect1 || !slashcorrect2 || dayAsNum > 31 || monthAsNum > 13 || yearAsNum < 1900) {
                        JOptionPane.showMessageDialog(null, "Date of birth not valid, please use the format DD/MM/YYYY. Error Code: 500", "Oops", JOptionPane.ERROR_MESSAGE);
                        valid = false;
                    }

                    if ((monthAsNum == 4 || monthAsNum == 6 || monthAsNum == 9 || monthAsNum == 11) && valid) {
                        if (dayAsNum > 30) {
                            JOptionPane.showMessageDialog(null, "Date of birth not valid, please use the format DD/MM/YYYY. Error Code: 500", "Oops", JOptionPane.ERROR_MESSAGE);
                            valid = false;
                        }
                    }

                    if (monthAsNum == 2 && valid) {
                        if (dayAsNum > 29) {
                            JOptionPane.showMessageDialog(null, "Date of birth not valid, please use the format DD/MM/YYYY. Error Code: 500", "Oops", JOptionPane.ERROR_MESSAGE);
                            valid = false;
                        }
                    } else {
                        try {
                            if(valid) {
                                this.studentBeingViewed.setDob(this.editAndCalcInput.getText());
                                this.dobLabel.setText("Date of Birth: " + studentBeingViewed.getDob());
                                JOptionPane.showMessageDialog(null, "Date of Birth changed successfully", "Complete!", JOptionPane.INFORMATION_MESSAGE);
                                editAndCalcInput.setText("");
                            }
                        } catch (IOException ex) {
                            JOptionPane.showMessageDialog(null, "Error with Student object. Error Code: 4000X10", "Oops", JOptionPane.ERROR_MESSAGE);
                        }
                    }

                }
            }
            catch (NumberFormatException ex){
                JOptionPane.showMessageDialog(null, "Date of birth not valid, please use the format DD/MM/YYYY. Error Code: 500", "Oops", JOptionPane.ERROR_MESSAGE);
                valid = false;
            }

        }
        if(e.getSource() == editStartYrEnterButton && !alreadyOpen){
            alreadyOpen = true;
            if(Integer.parseInt(editAndCalcInput.getText())>1900){
                try {
                    this.studentBeingViewed.setStartYr(Integer.parseInt(this.editAndCalcInput.getText()));
                    this.staryYrLabel.setText("Year Started: " + studentBeingViewed.getStartYr());
                    JOptionPane.showMessageDialog(null, "Start Year changed successfully", "Complete!", JOptionPane.INFORMATION_MESSAGE);
                    editAndCalcInput.setText("");
                } catch (IOException ex) {
                    JOptionPane.showMessageDialog(null, "Error with Student object. Error Code: 4000X10", "Oops", JOptionPane.ERROR_MESSAGE);
                }
            }
            else{
                JOptionPane.showMessageDialog(null, "Invalid Input. Error Code: 500", "Oops", JOptionPane.ERROR_MESSAGE);
            }
        }
        if(e.getSource() == editEndYrEnterButton && !alreadyOpen){
            alreadyOpen = true;
            if(Integer.parseInt(editAndCalcInput.getText())>1900){
                try {
                    this.studentBeingViewed.setEndYr(Integer.parseInt(this.editAndCalcInput.getText()));
                    this.endYrLabel.setText("Last Year: " + studentBeingViewed.getEndYr());
                    JOptionPane.showMessageDialog(null, "Final Year changed successfully", "Complete!", JOptionPane.INFORMATION_MESSAGE);
                    editAndCalcInput.setText("");
                } catch (IOException ex) {
                    JOptionPane.showMessageDialog(null, "Error with Student object. Error Code: 4000X10", "Oops", JOptionPane.ERROR_MESSAGE);
                }
            }
            else{
                JOptionPane.showMessageDialog(null, "Invalid Input. Error Code: 500", "Oops", JOptionPane.ERROR_MESSAGE);
            }
        }
        if(!alreadyOpen){
            JFrame InputFrame = new JFrame();
            InputFrame.setSize(480,200);
            InputFrame.getContentPane().setBackground(new Color(216,198,236));
            InputFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            ImageIcon logoIcon = new ImageIcon("Images/icon.png");
            InputFrame.setIconImage(logoIcon.getImage());
            InputFrame.setLayout(null);
            InputFrame.setResizable(false);
            InputFrame.setLocationRelativeTo(null);
            InputFrame.setVisible(true);
            JLabel title = new JLabel("Unknown Error");
            title.setFont(labelFont);
            title.setBounds(150,0,150,50);
            editAndCalcInput.setBounds(150,65,150,25);
            InputFrame.add(editAndCalcInput);
            InputFrame.add(title);
            if (e.getSource()==calculateButton){
                InputFrame.setTitle("Calculate");
                title.setText("Enter Module Code");

                this.CalculateEnterButton.setBounds(200,100,75,25);
                this.CalculateEnterButton.addActionListener(this);

                InputFrame.add(this.CalculateEnterButton);
            }
            if(e.getSource()==fnameEditButton){
                InputFrame.setTitle("Edit First Name");
                title.setText("Enter new First Name");

                this.editFnameEnterButton.setBounds(200,100,75,25);
                this.editFnameEnterButton.addActionListener(this);

                InputFrame.add(this.editFnameEnterButton);
            }
            if(e.getSource()==lnameEditButton){
                InputFrame.setTitle("Edit Last Name");
                title.setText("Enter new Last Name");

                this.editLnameEnterButton.setBounds(200,100,75,25);
                this.editLnameEnterButton.addActionListener(this);

                InputFrame.add(this.editLnameEnterButton);
            }
            if(e.getSource() == dobEditButton){
                InputFrame.setTitle("Edit Date of Birth");
                title.setText("Enter new DOB");

                this.editDOBEnterButton.setBounds(200,100,75,25);
                this.editDOBEnterButton.addActionListener(this);

                InputFrame.add(editDOBEnterButton);
            }
            if(e.getSource() == startYrEditButton){
                InputFrame.setTitle("Edit Start Year");
                title.setText("Enter new Start Year");

                this.editStartYrEnterButton.setBounds(200,100,75,25);
                this.editStartYrEnterButton.addActionListener(this);

                InputFrame.add(this.editStartYrEnterButton);
            }
            if(e.getSource() == endYrEditButton){
                InputFrame.setTitle("Edit End Year");
                title.setText("Enter new Final Year");

                this.editEndYrEnterButton.setBounds(200,100,75,25);
                this.editEndYrEnterButton.addActionListener(this);

                InputFrame.add(this.editEndYrEnterButton);
            }
        }
        }
    }
