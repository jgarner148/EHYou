import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Calendar;

public class GUICreateAcademic implements ActionListener {
    private JFrame mainFrame;
    private  JButton goBack = factory.makeFlatButton("BACK");
    private  JLabel titleLabel = new JLabel("Create Academic");

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

    private JLabel currentResearchLabel = new JLabel("Research");
    private JTextField currentResearchInput = new JTextField();
    private JButton addToCurrentResearch = factory.makeFlatButton("Add Another");

    private JButton createButton = factory.makeFlatButton("Create");

    /////////////////////////////////////////////////////////////////////////////////////////////////////////
    private String fname;
    private String lname;
    private String dob;
    private int startyr;
    private int salary;
    private String office;
    private String degree;
    private String[] currentResearch=new String[0];


    public GUICreateAcademic(JFrame mainFrame) {
        this.mainFrame = mainFrame;
        this.mainFrame.setTitle("EHYou - Create Academic");

        //Setting up the back Button
        goBack.setBounds(0, 0,100,50);
        goBack.addActionListener((ActionListener) this);

        titleLabel.setBounds(450, 30,450,110);
        titleLabel.setFont(new Font("Georgia", Font.PLAIN,55));

        int xLabel = 400;
        fnameLabel.setBounds(xLabel, 150,100,50);
        lnameLabel.setBounds(xLabel, 200,100,50);
        dobLabel.setBounds(xLabel, 250,100,50);
        StartYrLabel.setBounds(xLabel, 300,100,50);
        salaryLabel.setBounds(xLabel, 350, 100,50);
        officeLabel.setBounds(xLabel,400,100,50);
        degreeLabel.setBounds(xLabel,450,100,50);
        currentResearchLabel.setBounds(xLabel, 500,100,50);

        int xInput = 510;
        int wInput = 250;
        int hInput = 30;
        fnameInput.setBounds(xInput, 160,wInput,hInput);
        lnameInput.setBounds(xInput, 210,wInput,hInput);
        dobInput.setBounds(xInput, 260,wInput,hInput);
        StartYrInput.setBounds(xInput, 310,wInput,hInput);
        salaryInput.setBounds(xInput,360,wInput,hInput);
        officeInput.setBounds(xInput,410,wInput,hInput);
        degreeInput.setBounds(xInput,460,wInput,hInput);
        currentResearchInput.setBounds(xInput,510,wInput,hInput);

        addToCurrentResearch.setBounds(775,512,125,25);
        addToCurrentResearch.addActionListener(this);

        createButton.setBounds(535, 570, 200, 35);
        createButton.addActionListener(this);


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
        mainFrame.add(currentResearchLabel);
        mainFrame.add(currentResearchInput);
        mainFrame.add(addToCurrentResearch);

        mainFrame.add(createButton);

        mainFrame.add(goBack);
        mainFrame.add(titleLabel);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource()==goBack){
            mainFrame.getContentPane().removeAll();
            mainFrame.repaint();
            GUICreateHome createhomepage = new GUICreateHome(this.mainFrame);
        }

        if(e.getSource()==addToCurrentResearch){
            String inputtedresearch = currentResearchInput.getText();
            try {
                boolean exists = quickMethods.checkIfInFile("codes/researchcodes.csv", inputtedresearch);
                boolean correct = true;
                if (exists == false) {
                    JOptionPane.showMessageDialog(null, "That is not valid Research, make sure you have created the Research first. Error Code: 50X50", "Oops", JOptionPane.ERROR_MESSAGE);
                    correct = false;
                }
                for (int i = 0; i < this.currentResearch.length; i++) {
                    if (this.currentResearch[i].equals(inputtedresearch)) {
                        JOptionPane.showMessageDialog(null, "You've already added this code. Error Code: 256X01", "Oops", JOptionPane.ERROR_MESSAGE);
                        correct = false;
                    }
                }
                if (correct == true) {
                    this.currentResearch = AddToArray.string(this.currentResearch, inputtedresearch);
                    currentResearchInput.setText("");
                }
            } catch (FileNotFoundException ex) {
                JOptionPane.showMessageDialog(null, "You are missing a file! Error Code: 1000X50", "Oops", JOptionPane.ERROR_MESSAGE);
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

            //checking research
            if(!anyInvalid && currentResearchInput.getText().length()>0) {
                String inputtedresearch = currentResearchInput.getText();
                try {
                    boolean exists = quickMethods.checkIfInFile("codes/researchcodes.csv", inputtedresearch);
                    boolean correct = true;
                    if (exists == false) {
                        JOptionPane.showMessageDialog(null, "That is not valid Research, make sure you have created the Research first. Error Code: 50X50", "Oops", JOptionPane.ERROR_MESSAGE);
                        correct = false;
                        anyInvalid = true;
                    }
                    for (int i = 0; i < this.currentResearch.length; i++) {
                        if (this.currentResearch[i].equals(inputtedresearch)) {
                            JOptionPane.showMessageDialog(null, "You've already added this code. Error Code: 256X01", "Oops", JOptionPane.ERROR_MESSAGE);
                            correct = false;
                            anyInvalid = true;
                        }
                    }
                    if (correct == true) {
                        this.currentResearch = AddToArray.string(this.currentResearch, inputtedresearch);
                        currentResearchInput.setText("");
                    }
                } catch (FileNotFoundException ex) {
                    JOptionPane.showMessageDialog(null, "You are missing a file! Error Code: 1000X50", "Oops", JOptionPane.ERROR_MESSAGE);
                    anyInvalid = true;
                }
            }

            //Creating the object
            if(!anyInvalid){
                try{
                    Academic newAcademic = new Academic(this.fname,this.lname,this.dob,this.startyr,this.salary,this.office,this.degree,this.currentResearch);
                    JOptionPane.showMessageDialog(null, "Academic was created successfully! New Staff number: " + newAcademic.getStaffID(), "Success!", JOptionPane.INFORMATION_MESSAGE);
                    mainFrame.getContentPane().removeAll();
                    mainFrame.repaint();
                    GUICreateHome createhomepage = new GUICreateHome(this.mainFrame);


                } catch (IOException | ClassNotFoundException ioException) {
                    JOptionPane.showMessageDialog(null, "An error occurred. Error Code: 4000X50", "Oops", JOptionPane.ERROR_MESSAGE);
                }
            }

        }
    }
}
