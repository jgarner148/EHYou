import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.IOException;

/**
 * The class that houses the GUI for creating an academic
 */
public class GUICreateAcademic implements ActionListener {
    //Setting up the global GUI elements
    private final JFrame mainFrame;
    private final JButton goBack = factory.makeFlatButton("BACK");
    private final JLabel titleLabel = new JLabel("Create Academic");

    private final JLabel fnameLabel = new JLabel("*First name:");
    private final JTextField fnameInput = new JTextField();

    private final JLabel lnameLabel = new JLabel("*Last name:");
    private final JTextField lnameInput = new JTextField();

    private final JLabel dobLabel = new JLabel("*Date of birth:");
    private final JTextField dobInput = new JTextField();

    private final JLabel StartYrLabel = new JLabel("*Start Year:");
    private final JTextField StartYrInput = new JTextField();

    private final JLabel salaryLabel = new JLabel("*Salary");
    private final JTextField salaryInput = new JTextField();

    private final JLabel officeLabel = new JLabel("*Office");
    private final JTextField officeInput = new JTextField();

    private final JLabel degreeLabel = new JLabel("*Degree");
    private final JTextField degreeInput = new JTextField();

    private final JLabel currentResearchLabel = new JLabel("Research");
    private final JTextField currentResearchInput = new JTextField();
    private final JButton addToCurrentResearch = factory.makeFlatButton("Add Another");

    private final JButton createButton = factory.makeFlatButton("Create");

    //Setting up the global variables that will have the values of the academic being created assigned to them.
    private String fname;
    private String lname;
    private String dob;
    private int startyr;
    private int salary;
    private String office;
    private String degree;
    private String[] currentResearch=new String[0];

    /**
     * Constructor for the class that will be called when wanting to display the GUI for creating an academic
     * @param mainFrame The master frame that is already being displayed to the user when the method is called
     */
    public GUICreateAcademic(JFrame mainFrame) {
        //Configuring the main frame
        this.mainFrame = mainFrame;
        this.mainFrame.setTitle("EHYou - Create Academic");

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
        StartYrLabel.setBounds(xLabel, 300,100,50);
        salaryLabel.setBounds(xLabel, 350, 100,50);
        officeLabel.setBounds(xLabel,400,100,50);
        degreeLabel.setBounds(xLabel,450,100,50);
        currentResearchLabel.setBounds(xLabel, 500,100,50);

        //Setting input locations
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

        //Setting up the add to buttons
        addToCurrentResearch.setBounds(775,512,125,25);
        addToCurrentResearch.addActionListener(this);

        //Setting up the create button
        createButton.setBounds(535, 570, 200, 35);
        createButton.addActionListener(this);

        //Adding all the elements to the frame
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

        //Method for when the adding to research button is clicked that checks if the research code exists and then adds it to the array if it does not
        if(e.getSource()==addToCurrentResearch){
            String inputtedresearch = currentResearchInput.getText();
            try {
                boolean exists = quickMethods.checkIfInFile("codes/researchcodes.csv", inputtedresearch);
                boolean correct = true;
                if (!exists) {
                    JOptionPane.showMessageDialog(null, "That is not valid Research, make sure you have created the Research first. Error Code: 50X50", "Oops", JOptionPane.ERROR_MESSAGE);
                    correct = false;
                }
                for (int i = 0; i < this.currentResearch.length; i++) {
                    if (this.currentResearch[i].equals(inputtedresearch)) {
                        JOptionPane.showMessageDialog(null, "You've already added this code. Error Code: 256X01", "Oops", JOptionPane.ERROR_MESSAGE);
                        correct = false;
                    }
                }
                if (correct) {
                    this.currentResearch = AddToArray.string(this.currentResearch, inputtedresearch);
                    currentResearchInput.setText("");
                }
            } catch (FileNotFoundException ex) {
                JOptionPane.showMessageDialog(null, "You are missing a file! Error Code: 1000X50", "Oops", JOptionPane.ERROR_MESSAGE);
            }
        }

        //Method for when the create button is clicked that takes the information inputted into the form, checks it for errors and then makes the object
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

            //Checking start year
            if(!anyInvalid){
                if(Integer.parseInt(StartYrInput.getText())<1900){
                    JOptionPane.showMessageDialog(null, "Start year is not valid. Error Code: 500", "Oops", JOptionPane.ERROR_MESSAGE);
                    anyInvalid = true;
                }
                else{
                    this.startyr = Integer.parseInt(StartYrInput.getText());
                }
            }

            this.salary = Integer.parseInt(salaryInput.getText());
            this.office = officeInput.getText();
            this.degree = degreeInput.getText();

            //checking Research
            if(!anyInvalid && currentResearchInput.getText().length()>0){
                String inputtedresearch = currentResearchInput.getText();
                try {
                    boolean exists = quickMethods.checkIfInFile("codes/researchcodes.csv", inputtedresearch);
                    boolean correct = true;
                    if (!exists) {
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
                    if (correct) {
                        this.currentResearch = AddToArray.string(this.currentResearch, inputtedresearch);
                        currentResearchInput.setText("");
                    }
                } catch (FileNotFoundException ex) {
                    JOptionPane.showMessageDialog(null, "You are missing a file! Error Code: 1000X50", "Oops", JOptionPane.ERROR_MESSAGE);
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
