/**
 * Class that houses the GUI for viewing an Acadmeic
 */

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

public class GUIViewAcademic implements ActionListener {
    //Creating the elements that house the academic data as well as the buttons the edit them
    private final JFrame IDCardFrame = new JFrame();
    private final String staffID;
    private final Academic academicBeingviewed;
    private final JLabel titleLabel = new JLabel("Academic details");
    private final JLabel IDLabel = new JLabel("");
    private final JLabel fnameLabel = new JLabel("");
    private final JButton fnameEditButton = factory.makeFlatButton("Edit");
    private final JLabel lnameLabel = new JLabel("");
    private final JButton lnameEditButton = factory.makeFlatButton("Edit");
    private final JLabel dobLabel = new JLabel("");
    private final JButton dobEditButton = factory.makeFlatButton("Edit");
    private final JLabel staryYrLabel = new JLabel("");
    private final JButton startYrEditButton = factory.makeFlatButton("Edit");
    private final JLabel salaryLabel = new JLabel("");
    private final JButton salaryEditButton = factory.makeFlatButton("Edit");
    private final JLabel officeLabel = new JLabel("");
    private final JButton officeEditButton = factory.makeFlatButton("Edit");
    private final JLabel degreeLabel = new JLabel("");
    private final JButton degreeEditButton = factory.makeFlatButton("Edit");
    private final JLabel currentResearchLabel = new JLabel("");
    private final JButton currentResearchAddButton = factory.makeFlatButton("Add");
    private final JButton currentResearchDeleteButton = factory.makeFlatButton("Delete");

    //Creating the standard font
    private final Font labelFont = new Font("Georgia", Font.ITALIC, 15);

    //Creating the object delete button
    private final JButton deletebutton = factory.makeFlatButton("Delete");

    //Creating the elements for the edit pages
    private final JTextField editAndCalcInput = new JTextField();
    private final JButton editFnameEnterButton = factory.makeFlatButton("Enter");
    private final JButton editLnameEnterButton = factory.makeFlatButton("Enter");
    private final JButton editDOBEnterButton = factory.makeFlatButton("Enter");
    private final JButton editStartYrEnterButton = factory.makeFlatButton("Enter");
    private final JButton editsalaryEnterButton = factory.makeFlatButton("Enter");
    private final JButton editofficeEnterButton = factory.makeFlatButton("Enter");
    private final JButton editdegreeEnterButton = factory.makeFlatButton("Enter");
    private final JButton addResearchEnterButton = factory.makeFlatButton("Enter");
    private final JButton removeResearchEnterButton = factory.makeFlatButton("Enter");

    /**
     * Constructor for the class that will be called when wanting to view an academic
     * @param staffID The ID of the academic being viewed
     * @throws IOException IO exception
     * @throws ClassNotFoundException Class not found exception
     */
    public GUIViewAcademic(String staffID) throws IOException, ClassNotFoundException {
        //Setting up some global variables
        this.staffID = staffID;
        this.academicBeingviewed = getobject.academic(this.staffID);

        //Setting up the ID card frame
        this.IDCardFrame.setSize(560, 560);
        this.IDCardFrame.getContentPane().setBackground(new Color(165, 213, 220));
        this.IDCardFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.IDCardFrame.setTitle("ID card - " + academicBeingviewed.getFname() + " " + academicBeingviewed.getLname() + " (" + academicBeingviewed.getStaffID() + ")");
        ImageIcon logoIcon = new ImageIcon("Images/icon.png");
        this.IDCardFrame.setIconImage(logoIcon.getImage());
        this.IDCardFrame.setLayout(null);
        this.IDCardFrame.setLocationRelativeTo(null);
        this.IDCardFrame.setResizable(false);
        this.IDCardFrame.setVisible(true);

        //Setting up the title label
        titleLabel.setBounds(20, 15, 225, 25);
        titleLabel.setFont(new Font("Georgia", Font.ITALIC, 25));

        //Setting up the object information labels
        int xLabel = 20;
        int wLabel = 250;
        int hLabel = 25;
        IDLabel.setBounds(xLabel, 50, wLabel, hLabel);
        IDLabel.setFont(labelFont);
        IDLabel.setText("Staff ID: " + academicBeingviewed.getStaffID());

        fnameLabel.setBounds(xLabel, 70, wLabel, hLabel);
        fnameLabel.setFont(labelFont);
        fnameLabel.setText("First Name: " + academicBeingviewed.getFname());

        lnameLabel.setBounds(xLabel, 90, wLabel, hLabel);
        lnameLabel.setFont(labelFont);
        lnameLabel.setText("Last Name: " + academicBeingviewed.getLname());

        dobLabel.setBounds(xLabel, 110, wLabel, hLabel);
        dobLabel.setFont(labelFont);
        dobLabel.setText("Date of Birth: " + academicBeingviewed.getDob());

        staryYrLabel.setBounds(xLabel, 130, wLabel, hLabel);
        staryYrLabel.setFont(labelFont);
        staryYrLabel.setText("Year Started: " + academicBeingviewed.getStartyr());

        salaryLabel.setBounds(xLabel, 150, wLabel, hLabel);
        salaryLabel.setFont(labelFont);
        salaryLabel.setText("Salary: " + academicBeingviewed.getSalary());

        officeLabel.setBounds(xLabel, 170, wLabel, hLabel);
        officeLabel.setFont(labelFont);
        officeLabel.setText("Office: " + academicBeingviewed.getOffice());

        degreeLabel.setBounds(xLabel, 190, wLabel, hLabel);
        degreeLabel.setFont(labelFont);
        degreeLabel.setText("Degree: " + academicBeingviewed.getDegree());

        //Adding the content of the all research array to the label and then setting up the label
        String[] allResearch = academicBeingviewed.getCurrentResearch();
        String allResearchText = "";
        for (int i = 0; i < allResearch.length; i++) {
            String currentcode = allResearch[i];
            Research currentResarch = getobject.research(currentcode);
            allResearchText = allResearchText + currentResarch.getResearchCode() + " - " + currentResarch.getResearchTitle() + "<br>";
        }
        currentResearchLabel.setBounds(xLabel, 200, 400, 240);
        currentResearchLabel.setFont(labelFont);
        currentResearchLabel.setText("<html>" + "Research:<br>" + allResearchText);

        //Setting up the delete button
        deletebutton.setBounds(320, 460, 200, 50);
        deletebutton.addActionListener(this);

        //Setting up all buttons
        int xButton = 420;
        int wButton = 75;
        int hButton = 18;
        fnameEditButton.setBounds(xButton, 72, wButton, hButton);
        fnameEditButton.addActionListener(this);
        lnameEditButton.setBounds(xButton, 92, wButton, hButton);
        lnameEditButton.addActionListener(this);
        dobEditButton.setBounds(xButton, 112, wButton, hButton);
        dobEditButton.addActionListener(this);
        startYrEditButton.setBounds(xButton, 132, wButton, hButton);
        startYrEditButton.addActionListener(this);
        salaryEditButton.setBounds(xButton, 152, wButton, hButton);
        salaryEditButton.addActionListener(this);
        officeEditButton.setBounds(xButton, 172, wButton, hButton);
        officeEditButton.addActionListener(this);
        degreeEditButton.setBounds(xButton, 192, wButton, hButton);
        degreeEditButton.addActionListener(this);

        currentResearchAddButton.setBounds(20, 475, 85, hButton);
        currentResearchAddButton.addActionListener(this);

        currentResearchDeleteButton.setBounds(115, 475, 85, hButton);
        currentResearchDeleteButton.addActionListener(this);

        //Adding all the elements to the frame
        IDCardFrame.add(titleLabel);
        IDCardFrame.add(IDLabel);
        IDCardFrame.add(fnameLabel);
        IDCardFrame.add(lnameLabel);
        IDCardFrame.add(dobLabel);
        IDCardFrame.add(staryYrLabel);
        IDCardFrame.add(salaryLabel);
        IDCardFrame.add(officeLabel);
        IDCardFrame.add(degreeLabel);
        IDCardFrame.add(currentResearchLabel);
        IDCardFrame.add(deletebutton);
        IDCardFrame.add(fnameEditButton);
        IDCardFrame.add(lnameEditButton);
        IDCardFrame.add(dobEditButton);
        IDCardFrame.add(startYrEditButton);
        IDCardFrame.add(salaryEditButton);
        IDCardFrame.add(officeEditButton);
        IDCardFrame.add(degreeEditButton);
        IDCardFrame.add(currentResearchAddButton);
        IDCardFrame.add(currentResearchDeleteButton);
    }

    /**
     * Invoked when an action occurs.
     *
     * @param e the event to be processed
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        boolean alreadyOpen = false; //Boolean that stops the program from displaying multiple error messages/input windows by setting itself to true when an action is performed
        //Method performed when the delete button is clicked that deletes the academic object and its class file
        if (e.getSource() == deletebutton && !alreadyOpen) {
            alreadyOpen = true;
            int confirmation = JOptionPane.showConfirmDialog(null, "Are you sure you want to delete " + academicBeingviewed.getStaffID(), "Confirm delete", JOptionPane.YES_NO_OPTION, JOptionPane.ERROR_MESSAGE);
            if (confirmation == 0) {
                String filename = "Academics/" + academicBeingviewed.getStaffID() + ".txt";
                File oldFile = new File(filename);
                oldFile.delete();
                IDCardFrame.dispatchEvent(new WindowEvent(IDCardFrame, WindowEvent.WINDOW_CLOSING));
                JOptionPane.showMessageDialog(null, "Academic Deleted", "Deleted!", JOptionPane.INFORMATION_MESSAGE);
            }
        }
        //Method performed when changing the first name
        if(e.getSource() == editFnameEnterButton && !alreadyOpen){
            alreadyOpen = true;
            try {
                this.academicBeingviewed.setFname(this.editAndCalcInput.getText());
                this.fnameLabel.setText("First Name: " + academicBeingviewed.getFname());
                JOptionPane.showMessageDialog(null, "First Name changed successfully", "Complete!", JOptionPane.INFORMATION_MESSAGE);
                editAndCalcInput.setText("");
            } catch (IOException ex) {
                JOptionPane.showMessageDialog(null, "Error with Academic object. Error Code: 4000X50", "Oops", JOptionPane.ERROR_MESSAGE);
            }
        }
        //Method performed when changing the last name
        if(e.getSource() == editLnameEnterButton && !alreadyOpen){
            alreadyOpen = true;
            try {
                this.academicBeingviewed.setLname(this.editAndCalcInput.getText());
                this.lnameLabel.setText("Last Name: " + academicBeingviewed.getLname());
                JOptionPane.showMessageDialog(null, "Last Name changed successfully", "Complete!", JOptionPane.INFORMATION_MESSAGE);
                editAndCalcInput.setText("");
            } catch (IOException ex) {
                JOptionPane.showMessageDialog(null, "Error with Academic object. Error Code: 4000X50", "Oops", JOptionPane.ERROR_MESSAGE);
            }
        }
        //Method performed when changing the date of birth
        if(e.getSource() == editDOBEnterButton && !alreadyOpen){
            alreadyOpen = true;
            boolean isValid = quickMethods.checkDOB(editAndCalcInput.getText());
            if(isValid){
                try {
                    this.academicBeingviewed.setDob(this.editAndCalcInput.getText());
                    this.dobLabel.setText("Date of Birth: " + academicBeingviewed.getDob());
                    JOptionPane.showMessageDialog(null, "Date of Birth changed successfully", "Complete!", JOptionPane.INFORMATION_MESSAGE);
                    editAndCalcInput.setText("");
                } catch (IOException ex) {
                    JOptionPane.showMessageDialog(null, "Error with Academic object. Error Code: 4000X50", "Oops", JOptionPane.ERROR_MESSAGE);
                }
            }
            else{
                JOptionPane.showMessageDialog(null, "Date of birth not valid, please use the format DD/MM/YYYY. Error Code: 500", "Oops", JOptionPane.ERROR_MESSAGE);
            }

        }
        //Method performed when changing the start year
        if(e.getSource() == editStartYrEnterButton && !alreadyOpen){
            alreadyOpen = true;
            if(Integer.parseInt(editAndCalcInput.getText())>1900){
                try {
                    this.academicBeingviewed.setStartyr(Integer.parseInt(this.editAndCalcInput.getText()));
                    this.staryYrLabel.setText("Year Started: " + academicBeingviewed.getStartyr());
                    JOptionPane.showMessageDialog(null, "Start Year changed successfully", "Complete!", JOptionPane.INFORMATION_MESSAGE);
                    editAndCalcInput.setText("");
                } catch (IOException ex) {
                    JOptionPane.showMessageDialog(null, "Error with Academic object. Error Code: 4000X50", "Oops", JOptionPane.ERROR_MESSAGE);
                }
            }
            else{
                JOptionPane.showMessageDialog(null, "Invalid Input. Error Code: 500", "Oops", JOptionPane.ERROR_MESSAGE);
            }
        }
        //Method performed when changing the salary
        if(e.getSource() == editsalaryEnterButton && !alreadyOpen){
            alreadyOpen = true;
            try {
                this.academicBeingviewed.setSalary(Integer.parseInt(this.editAndCalcInput.getText()));
                this.salaryLabel.setText("Salary: " + academicBeingviewed.getSalary());
                JOptionPane.showMessageDialog(null, "Salary changed successfully", "Complete!", JOptionPane.INFORMATION_MESSAGE);
                editAndCalcInput.setText("");
            } catch (IOException ex) {
                JOptionPane.showMessageDialog(null, "Error with Academic object. Error Code: 4000X50", "Oops", JOptionPane.ERROR_MESSAGE);
            }
        }
        //Method performed when changing the office
        if(e.getSource() == editofficeEnterButton && !alreadyOpen){
            alreadyOpen = true;
            try {
                this.academicBeingviewed.setOffice(this.editAndCalcInput.getText());
                this.officeLabel.setText("Office: " + academicBeingviewed.getOffice());
                JOptionPane.showMessageDialog(null, "Office changed successfully", "Complete!", JOptionPane.INFORMATION_MESSAGE);
                editAndCalcInput.setText("");
            } catch (IOException ex) {
                JOptionPane.showMessageDialog(null, "Error with Academic object. Error Code: 4000X50", "Oops", JOptionPane.ERROR_MESSAGE);
            }
        }
        //Method performed when changing the degree
        if(e.getSource() == editdegreeEnterButton && !alreadyOpen){
            alreadyOpen = true;
            try {
                this.academicBeingviewed.setDegree(this.editAndCalcInput.getText());
                this.degreeLabel.setText("Degree: " + academicBeingviewed.getDegree());
                JOptionPane.showMessageDialog(null, "Degree changed successfully", "Complete!", JOptionPane.INFORMATION_MESSAGE);
                editAndCalcInput.setText("");
            } catch (IOException ex) {
                JOptionPane.showMessageDialog(null, "Error with Academic object. Error Code: 4000X50", "Oops", JOptionPane.ERROR_MESSAGE);
            }
        }
        //Method performed when adding another research code to the array
        if(e.getSource() == addResearchEnterButton && !alreadyOpen){
            alreadyOpen = true;
            String inputtedResearch = editAndCalcInput.getText();
            try {
                boolean exists = quickMethods.checkIfInFile("codes/researchcodes.csv", inputtedResearch);
                if (!exists) {
                    JOptionPane.showMessageDialog(null, "That is not a valid research code, make sure you have created the research first. Error Code: 50X50", "Oops", JOptionPane.ERROR_MESSAGE);
                }
                if (exists) {
                    this.academicBeingviewed.addToCurrentResearch(inputtedResearch);
                    Research newresearch = getobject.research(inputtedResearch);
                    this.currentResearchLabel.setText(this.currentResearchLabel.getText() + newresearch.getResearchCode() + " - " + newresearch.getResearchTitle() + "<br>");
                    JOptionPane.showMessageDialog(null, "Research Code added successfully", "Complete!", JOptionPane.INFORMATION_MESSAGE);
                    editAndCalcInput.setText("");
                }
            } catch (FileNotFoundException ex) {
                JOptionPane.showMessageDialog(null, "You are missing a file! Error Code: 1000X50", "Oops", JOptionPane.ERROR_MESSAGE);
            } catch (IOException ioException) {
                JOptionPane.showMessageDialog(null, "Error with Research object. Error Code: 4000X60", "Oops", JOptionPane.ERROR_MESSAGE);
            } catch (ClassNotFoundException classNotFoundException) {
                JOptionPane.showMessageDialog(null, "That is not a valid research code, make sure you have created the research first. Error Code: 50X50", "Oops", JOptionPane.ERROR_MESSAGE);
            }
        }
        //Method performed when removing a research code from the array
        if(e.getSource() == removeResearchEnterButton && !alreadyOpen){
            alreadyOpen = true;
            String inputtedResearch = editAndCalcInput.getText();
            try {
                boolean exists = quickMethods.checkIfInFile("codes/researchcodes.csv", inputtedResearch);
                if (!exists) {
                    JOptionPane.showMessageDialog(null, "That is not a valid research code, make sure you have created the research first. Error Code: 50X50", "Oops", JOptionPane.ERROR_MESSAGE);
                }
                if (exists) {
                    this.academicBeingviewed.removeFromCurrentResearch(inputtedResearch);
                    Research newresearch = getobject.research(inputtedResearch);
                    newresearch.removeFromAcademicResearching(academicBeingviewed.getStaffID());
                    JOptionPane.showMessageDialog(null, "Research removed successfully, refresh to see changes", "Complete!", JOptionPane.INFORMATION_MESSAGE);
                    editAndCalcInput.setText("");
                }
            } catch (FileNotFoundException ex) {
                JOptionPane.showMessageDialog(null, "You are missing a file! Error Code: 1000X50", "Oops", JOptionPane.ERROR_MESSAGE);
            } catch (IOException ioException) {
                JOptionPane.showMessageDialog(null, "Error with Research object. Error Code: 4000X60", "Oops", JOptionPane.ERROR_MESSAGE);
            } catch (ClassNotFoundException classNotFoundException) {
                JOptionPane.showMessageDialog(null, "That is not a valid research code, make sure you have created the research first. Error Code: 50X50", "Oops", JOptionPane.ERROR_MESSAGE);
            }
        }
        if (!alreadyOpen) {
            //Setting up the edit frame
            JFrame InputFrame = new JFrame();
            InputFrame.setSize(480, 200);
            InputFrame.getContentPane().setBackground(new Color(216, 198, 236));
            InputFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            ImageIcon logoIcon = new ImageIcon("Images/icon.png");
            InputFrame.setIconImage(logoIcon.getImage());
            InputFrame.setLayout(null);
            InputFrame.setResizable(false);
            InputFrame.setLocationRelativeTo(null);
            InputFrame.setVisible(true);
            JLabel title = new JLabel("Unknown Error");
            title.setFont(labelFont);
            title.setBounds(150, 0, 150, 50);
            editAndCalcInput.setBounds(150, 65, 150, 25);
            InputFrame.add(editAndCalcInput);
            InputFrame.add(title);
            //Method to ask the user for a new first name
            if (e.getSource() == fnameEditButton) {
                InputFrame.setTitle("Edit First Name");
                title.setText("Enter new First Name");

                this.editFnameEnterButton.setBounds(200, 100, 75, 25);
                this.editFnameEnterButton.addActionListener(this);

                InputFrame.add(this.editFnameEnterButton);
            }
            //Method to ask the user for a new last name
            if (e.getSource() == lnameEditButton) {
                InputFrame.setTitle("Edit Last Name");
                title.setText("Enter new Last Name");

                this.editLnameEnterButton.setBounds(200, 100, 75, 25);
                this.editLnameEnterButton.addActionListener(this);

                InputFrame.add(this.editLnameEnterButton);
            }
            //MEthod to ask the user for a new date of birth
            if (e.getSource() == dobEditButton) {
                InputFrame.setTitle("Edit Date of Birth");
                title.setText("Enter new DOB");

                this.editDOBEnterButton.setBounds(200, 100, 75, 25);
                this.editDOBEnterButton.addActionListener(this);

                InputFrame.add(editDOBEnterButton);
            }
            //Method to ask the user for a new start year
            if (e.getSource() == startYrEditButton) {
                InputFrame.setTitle("Edit Start Year");
                title.setText("Enter new Start Year");

                this.editStartYrEnterButton.setBounds(200, 100, 75, 25);
                this.editStartYrEnterButton.addActionListener(this);

                InputFrame.add(this.editStartYrEnterButton);
            }
            //Method to ask the user for a new salary
            if (e.getSource() == salaryEditButton) {
                InputFrame.setTitle("Edit Salary");
                title.setText("Enter new Salary");

                this.editsalaryEnterButton.setBounds(200, 100, 75, 25);
                this.editsalaryEnterButton.addActionListener(this);

                InputFrame.add(this.editsalaryEnterButton);
            }
            //Method to ask the user for a new office
            if (e.getSource() == officeEditButton) {
                InputFrame.setTitle("Edit Office");
                title.setText("Enter new Office");

                this.editofficeEnterButton.setBounds(200, 100, 75, 25);
                this.editofficeEnterButton.addActionListener(this);

                InputFrame.add(this.editofficeEnterButton);
            }
            //Method to ask the user for a new degree
            if (e.getSource() == degreeEditButton) {
                InputFrame.setTitle("Edit Degree");
                title.setText("Enter new Degree");

                this.editdegreeEnterButton.setBounds(200, 100, 75, 25);
                this.editdegreeEnterButton.addActionListener(this);

                InputFrame.add(this.editdegreeEnterButton);
            }
            //Method to ask the user for a research code to be added to the array
            if (e.getSource() == currentResearchAddButton) {
                InputFrame.setTitle("Add Research");
                title.setText("Enter new Research");

                this.addResearchEnterButton.setBounds(200, 100, 75, 25);
                this.addResearchEnterButton.addActionListener(this);

                InputFrame.add(this.addResearchEnterButton);
            }
            //Method to ask the user for a research code to be removed from the array
            if (e.getSource() == currentResearchDeleteButton) {
                InputFrame.setTitle("Delete Research");
                title.setText("Enter Research Code");

                this.removeResearchEnterButton.setBounds(200, 100, 75, 25);
                this.removeResearchEnterButton.addActionListener(this);

                InputFrame.add(this.removeResearchEnterButton);
            }

        }
    }
}
