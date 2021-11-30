import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.IOException;

public class GUICreateResearch implements ActionListener {
    private final JFrame mainFrame;
    private final JButton goBack = factory.makeFlatButton("BACK");
    private final JLabel titleLabel = new JLabel("Create Research");

    private final JLabel researchTitleLabel = new JLabel("*Title:");
    private final JTextField researchTitleInput = new JTextField();

    private final JLabel academicResearchingLabel = new JLabel("Researchers:");
    private final JTextField academicsResearchingInput = new JTextField();
    private final JButton addNewAcademic = factory.makeFlatButton("Add Another");

    private final JButton createButton = factory.makeFlatButton("Create");

    ////////////////////////////////////////////////////////////////////////////////////////
    private String ResearchTitle;
    private String[] AcademicsResearching = new String[0];

    public GUICreateResearch(JFrame mainFrame) {
        this.mainFrame = mainFrame;
        this.mainFrame.setTitle("EHYou - Create Research");

        //Setting up the back Button
        goBack.setBounds(0, 0,100,50);
        goBack.addActionListener(this);

        titleLabel.setBounds(450, 30,450,110);
        titleLabel.setFont(new Font("Georgia", Font.PLAIN,55));

        int xLabel = 400;
        int wLabel = 100;
        int hLabel = 50;
        researchTitleLabel.setBounds(xLabel,150,wLabel,hLabel);
        academicResearchingLabel.setBounds(xLabel,200,wLabel,hLabel);

        int xInput = 510;
        int wInput = 250;
        int hInput = 30;
        researchTitleInput.setBounds(xInput, 160,wInput,hInput);
        academicsResearchingInput.setBounds(xInput, 210,wInput,hInput);

        addNewAcademic.setBounds(775,212,125,25);
        addNewAcademic.addActionListener(this);

        createButton.setBounds(535, 270, 200, 35);
        createButton.addActionListener(this);


        mainFrame.add(goBack);
        mainFrame.add(titleLabel);
        mainFrame.add(researchTitleLabel);
        mainFrame.add(researchTitleInput);
        mainFrame.add(academicResearchingLabel);
        mainFrame.add(academicsResearchingInput);
        mainFrame.add(addNewAcademic);
        mainFrame.add(createButton);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource()==goBack){
            mainFrame.getContentPane().removeAll();
            mainFrame.repaint();
            GUICreateHome createhomepage = new GUICreateHome(this.mainFrame);
        }

        if(e.getSource()==addNewAcademic){
            String inputtedAcademic = academicsResearchingInput.getText();
            try {
                boolean exists = quickMethods.checkIfInFile("codes/staffcodes.csv",inputtedAcademic);
                boolean correct = true;
                if(exists == false){
                    JOptionPane.showMessageDialog(null, "That is not a valid Staff code, make sure you have created the Academic first. Error Code: 50X40", "Oops", JOptionPane.ERROR_MESSAGE);
                    correct = false;
                }
                for (int i = 0; i < this.AcademicsResearching.length; i++) {
                    if(this.AcademicsResearching[i].equals(inputtedAcademic)){
                        JOptionPane.showMessageDialog(null, "You've already added this code. Error Code: 256X01", "Oops", JOptionPane.ERROR_MESSAGE);
                        correct = false;
                    }
                }
                if (correct==true){
                    this.AcademicsResearching = AddToArray.string(this.AcademicsResearching, inputtedAcademic);
                    academicsResearchingInput.setText("");
                }
            }
            catch (FileNotFoundException ex) {
                JOptionPane.showMessageDialog(null, "You are missing a file! Error Code: 1000X40", "Oops", JOptionPane.ERROR_MESSAGE);
            }
        }

        if(e.getSource()==createButton){
            boolean anyInvalid = false;

            //Checking required field
            if(researchTitleInput.getText().equals("")){
                JOptionPane.showMessageDialog(null, "You've left a required field blank. Error Code: 7", "Oops", JOptionPane.ERROR_MESSAGE);
                anyInvalid = true;
            }

            this.ResearchTitle = researchTitleInput.getText();

            //checking academics
            if(!anyInvalid && academicsResearchingInput.getText().length()>0){
                String inputtedAcademic = academicsResearchingInput.getText();
                try {
                    boolean exists = quickMethods.checkIfInFile("codes/staffcodes.csv",inputtedAcademic);
                    boolean correct = true;
                    if(exists == false){
                        JOptionPane.showMessageDialog(null, "That is not a valid Staff code, make sure you have created the Academic first. Error Code: 50X40", "Oops", JOptionPane.ERROR_MESSAGE);
                        correct = false;
                        anyInvalid = true;
                    }
                    for (int i = 0; i < this.AcademicsResearching.length; i++) {
                        if(this.AcademicsResearching[i].equals(inputtedAcademic)){
                            JOptionPane.showMessageDialog(null, "You've already added this code. Error Code: 256X01", "Oops", JOptionPane.ERROR_MESSAGE);
                            correct = false;
                            anyInvalid = true;
                        }
                    }
                    if (correct==true){
                        this.AcademicsResearching = AddToArray.string(this.AcademicsResearching, inputtedAcademic);
                        academicsResearchingInput.setText("");
                    }
                }
                catch (FileNotFoundException ex) {
                    JOptionPane.showMessageDialog(null, "You are missing a file! Error Code: 1000X40", "Oops", JOptionPane.ERROR_MESSAGE);
                    anyInvalid = true;
                }
            }

            //creating the object{
            if(!anyInvalid){
                try{
                    Research newResearch = new Research(this.ResearchTitle,this.AcademicsResearching);
                    JOptionPane.showMessageDialog(null, "Research was created successfully! New Research code: " + newResearch.getResearchCode(), "Success!", JOptionPane.INFORMATION_MESSAGE);
                    mainFrame.getContentPane().removeAll();
                    mainFrame.repaint();
                    GUICreateHome createhomepage = new GUICreateHome(this.mainFrame);
                } catch (IOException | ClassNotFoundException ioException) {
                    JOptionPane.showMessageDialog(null, "An error occured. Error Code: 4000X60", "Oops", JOptionPane.ERROR_MESSAGE);
                    anyInvalid = true;
                }
            }

        }
    }
}
