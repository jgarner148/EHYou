import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.IOException;

public class GUIViewStudent implements ActionListener {
    private JFrame IDCardFrame = new JFrame();
    private Student studentBeingViewed;
    private String studentCode;
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
    private JButton allModulesAddButton = factory.makeFlatButton("Add");
    private JLabel allResultsLabel = new JLabel("");
    private JButton allResultsAddButton = factory.makeFlatButton("Add");
    private JLabel containerLabel = new JLabel(" ");
    private JButton calculateButton = factory.makeFlatButton("Work out average per module");
    private Font labelFont = new Font("Georgia", Font.ITALIC,15);
    private JButton enterButton = factory.makeFlatButton("Enter");
    private JTextField moduleInput = new JTextField();


    public GUIViewStudent( String studentCode) throws IOException, ClassNotFoundException {
        this.studentCode = studentCode;
        this.studentBeingViewed = getobject.student(this.studentCode);
        this.IDCardFrame.setSize(560,560);
        this.IDCardFrame.getContentPane().setBackground(new Color(165,213,220));
        this.IDCardFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE );
        this.IDCardFrame.setTitle("ID card - " + studentBeingViewed.getFname() + " " + studentBeingViewed.getLname() + " (" + studentBeingViewed.getStudentNum() + ")");
        ImageIcon logoIcon = new ImageIcon("Images/icon.png");
        this.IDCardFrame.setIconImage(logoIcon.getImage());
        this.IDCardFrame.setResizable(false);
        this.IDCardFrame.setVisible(true);

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
        lnameLabel.setText("Last name: " + studentBeingViewed.getLname());

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
            System.out.println(currentcode);
            Module currentModule = getobject.module(currentcode);
            allModulesText = allModulesText + currentModule.getModName() + " - " + currentModule.getModCode() + "<br>";
        }

        allModulesLabel.setBounds(xLabel, 120, wLabel, 240);
        allModulesLabel.setFont(labelFont);
        allModulesLabel.setText("<html>" + "Modules:<br>" + allModulesText);

        String[] allResults = studentBeingViewed.getAllResults();
        String AllResultsText = "";
        for(int i = 0; i<allResults.length; i++){
            String currentcode = allResults[i];
            System.out.println(currentcode);
            Result currentResult = getobject.result(currentcode);
            AllResultsText = AllResultsText + currentResult.getResultCode() + " - " + currentResult.getGrade() + "<br>";
        }

        allResultsLabel.setBounds(220, 120, wLabel, 240);
        allResultsLabel.setFont(labelFont);
        allResultsLabel.setText("<html>" + "Results:<br>" + AllResultsText);

        calculateButton.setBounds(xLabel, 425,200, 50);
        calculateButton.addActionListener(this);

        this.fnameEditButton.setBounds(150,250,50,50);

        IDCardFrame.add(IDLabel);
        IDCardFrame.add(fnameLabel);
        IDCardFrame.add(lnameLabel);
        IDCardFrame.add(dobLabel);
        IDCardFrame.add(staryYrLabel);
        IDCardFrame.add(endYrLabel);
        IDCardFrame.add(allModulesLabel);
        IDCardFrame.add(allResultsLabel);
        IDCardFrame.add(calculateButton);
        IDCardFrame.add(containerLabel);

        IDCardFrame.add(this.fnameEditButton);

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource()==calculateButton){
            JFrame calulateFrame = new JFrame();
            calulateFrame.setSize(480,200);
            calulateFrame.getContentPane().setBackground(new Color(216,198,236));
            calulateFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            calulateFrame.setTitle("Calculate");
            ImageIcon logoIcon = new ImageIcon("Images/icon.png");
            calulateFrame.setIconImage(logoIcon.getImage());
            calulateFrame.setResizable(false);
            calulateFrame.setVisible(true);

            JLabel title = new JLabel("Enter module code");

            JLabel container = new JLabel("");

            title.setFont(labelFont);
            title.setBounds(150,0,150,50);

            moduleInput.setBounds(150,50,150,25);

            this.enterButton.setBounds(200,100,75,25);
            this.enterButton.addActionListener(this);

            calulateFrame.add(title);
            calulateFrame.add(moduleInput);
            calulateFrame.add(this.enterButton);
            calulateFrame.add(container);
        }
        if(e.getSource()==enterButton){
            String inputtedText = this.moduleInput.getText();
            try {
                boolean exists = quickMethods.checkIfInFile("codes/modulecodes.csv", inputtedText);
                if(!exists){
                    JOptionPane.showMessageDialog(null, "Error with Module code, make sure it is correct. Error Code: 50X10", "Oops", JOptionPane.ERROR_MESSAGE);
                }
                else{
                    try {
                        int result = this.studentBeingViewed.averageGradeForModule(inputtedText);
                        String resultAsString = Integer.toString(result);
                        JOptionPane.showMessageDialog(null, "Calculation Result for "  + inputtedText + ": " + resultAsString, "Complete!", JOptionPane.INFORMATION_MESSAGE);
                        this.moduleInput.setText("");
                    } catch (IOException | ClassNotFoundException ex) {
                        JOptionPane.showMessageDialog(null, "Error with Module code, make sure it is correct. Error Code: 50X10", "Oops", JOptionPane.ERROR_MESSAGE);
                    }
                }
            } catch (FileNotFoundException ex) {
                JOptionPane.showMessageDialog(null, "Error with Module code, make sure it is correct. Error Code: 50X10", "Oops", JOptionPane.ERROR_MESSAGE);
            }

        }
        }
    }