import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

public class GUIViewReseach implements ActionListener {
    private final JFrame IDCardFrame = new JFrame();
    private final Research researchBeingviewed;
    private final String researchCode;
    private final JLabel titleLabel = new JLabel("Research details");
    private final JLabel IDLabel = new JLabel("");
    private final JLabel researchTitleLabel = new JLabel("");
    private final JButton researchTitleEditButton = factory.makeFlatButton("Edit");
    private final JLabel allResearchesLabel = new JLabel("");
    private final JButton allResearchesAddButton = factory.makeFlatButton("Add");
    private final JButton allResearchesDeleteButton = factory.makeFlatButton("Delete");

    private final Font labelFont = new Font("Georgia", Font.ITALIC,15);
    private final JButton deletebutton = factory.makeFlatButton("Delete");

    private final JTextField editAndCalcInput = new JTextField();
    private final JButton editResearchTitleEnterbutton = factory.makeFlatButton("Enter");
    private final JButton addAcademicEnterbutton = factory.makeFlatButton("Enter");
    private final JButton removeAcademicEnterbutton = factory.makeFlatButton("Enter");

    public GUIViewReseach(String researchCode) throws IOException, ClassNotFoundException {
        this.researchCode = researchCode;
        this.researchBeingviewed = getobject.research(this.researchCode);
        this.IDCardFrame.setSize(560,560);
        this.IDCardFrame.getContentPane().setBackground(new Color(165,213,220));
        this.IDCardFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE );
        this.IDCardFrame.setTitle("ID card - " + researchBeingviewed.getResearchTitle()+ " (" + researchBeingviewed.getResearchCode() + ")");
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
        IDLabel.setText("Research Code: " + researchBeingviewed.getResearchCode());

        researchTitleLabel.setBounds(xLabel,70,wLabel,hLabel);
        researchTitleLabel.setFont(labelFont);
        researchTitleLabel.setText("Title: " + researchBeingviewed.getResearchTitle());

        String[] allAcademics = researchBeingviewed.getAcademicsResearching();
        String allAcademicText = "";
        for(int i = 0; i<allAcademics.length;i++){
            String currentCode = allAcademics[i];
            Academic currentAcdemic = getobject.academic(currentCode);
            allAcademicText = allAcademicText + currentAcdemic.getStaffID() + " - " +  currentAcdemic.getFname() + " " + currentAcdemic.getLname() +"<br>";
        }

        allResearchesLabel.setBounds(xLabel, 200, wLabel, 240);
        allResearchesLabel.setFont(labelFont);
        allResearchesLabel.setText("<html>" + "Academics Researching:<br>" + allAcademicText);

        deletebutton.setBounds(320, 460 ,200,50);
        deletebutton.addActionListener(this);

        int xButton = 420;
        int wButton = 75;
        int hButton = 18;
        researchTitleEditButton.setBounds(xButton,72,wButton,hButton);
        researchTitleEditButton.addActionListener(this);

        allResearchesAddButton.setBounds(20,475,85,hButton);
        allResearchesAddButton.addActionListener(this);

        allResearchesDeleteButton.setBounds(115,475,85,hButton);
        allResearchesDeleteButton.addActionListener(this);


        IDCardFrame.add(titleLabel);

        IDCardFrame.add(IDLabel);
        IDCardFrame.add(researchTitleLabel);
        IDCardFrame.add(allResearchesLabel);

        IDCardFrame.add(deletebutton);
        IDCardFrame.add(researchTitleEditButton);
        IDCardFrame.add(allResearchesAddButton);
        IDCardFrame.add(allResearchesDeleteButton);

    }

    /**
     * Invoked when an action occurs.
     *
     * @param e the event to be processed
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        boolean alreadyOpen = false;
        if(e.getSource() == deletebutton && !alreadyOpen){
            alreadyOpen = true;
            int confirmation = JOptionPane.showConfirmDialog(null, "Are you sure you want to delete " + researchBeingviewed.getResearchCode(), "Confirm delete", JOptionPane.YES_NO_OPTION, JOptionPane.ERROR_MESSAGE);
            if (confirmation == 0){
                String filename = "Researches/" +researchBeingviewed.getResearchCode() + ".txt";
                File oldFile = new File(filename);
                oldFile.delete();
                IDCardFrame.dispatchEvent(new WindowEvent(IDCardFrame, WindowEvent.WINDOW_CLOSING));
                JOptionPane.showMessageDialog(null, "Research Deleted", "Deleted!", JOptionPane.INFORMATION_MESSAGE);
            }
        }
        if(e.getSource() == editResearchTitleEnterbutton && !alreadyOpen){
            alreadyOpen = true;
            try {
                this.researchBeingviewed.setResearchTitle(this.editAndCalcInput.getText());
                this.researchTitleLabel.setText("Title: " + researchBeingviewed.getResearchTitle());
                JOptionPane.showMessageDialog(null, "Research Title changed successfully", "Complete!", JOptionPane.INFORMATION_MESSAGE);
                editAndCalcInput.setText("");
            } catch (IOException ex) {
                JOptionPane.showMessageDialog(null, "Error with Research object. Error Code: 4000X60", "Oops", JOptionPane.ERROR_MESSAGE);
            }
        }
        if(e.getSource() == addAcademicEnterbutton && !alreadyOpen){
            alreadyOpen = true;
            String inputtedAcademic = editAndCalcInput.getText();
            try {
                boolean exists = quickMethods.checkIfInFile("codes/staffcodes.csv",inputtedAcademic);
                if (!exists) {
                    JOptionPane.showMessageDialog(null, "That is not a valid Academic code, make sure you have created the academic first. Error Code: 50X40", "Oops", JOptionPane.ERROR_MESSAGE);
                }
                if(exists){
                    this.researchBeingviewed.addToAcademicsResearching(inputtedAcademic);
                    Academic newAcademic = getobject.academic(inputtedAcademic);
                    this.allResearchesLabel.setText(this.allResearchesLabel.getText() + newAcademic.getStaffID() + " - " + newAcademic.getFname() + " " + newAcademic.getLname() + "<br>");
                    JOptionPane.showMessageDialog(null, "Academic added successfully", "Complete!", JOptionPane.INFORMATION_MESSAGE);
                    editAndCalcInput.setText("");
                }
            } catch (FileNotFoundException ex) {
                JOptionPane.showMessageDialog(null, "You are missing a file! Error Code: 1000X40", "Oops", JOptionPane.ERROR_MESSAGE);
            } catch (IOException ioException) {
                JOptionPane.showMessageDialog(null, "Error with Academic object. Error Code: 4000X50", "Oops", JOptionPane.ERROR_MESSAGE);
            } catch (ClassNotFoundException classNotFoundException) {
                JOptionPane.showMessageDialog(null, "That is not a valid Academic code, make sure you have created the academic first. Error Code: 50X40", "Oops", JOptionPane.ERROR_MESSAGE);
            }
        }
        if(e.getSource() == removeAcademicEnterbutton && !alreadyOpen){
            alreadyOpen = true;
            String inputtedAcademic = editAndCalcInput.getText();
            try {
                boolean exists = quickMethods.checkIfInFile("codes/staffcodes.csv",inputtedAcademic);
                if (!exists) {
                    JOptionPane.showMessageDialog(null, "That is not a valid Academic code, make sure you have created the academic first. Error Code: 50X40", "Oops", JOptionPane.ERROR_MESSAGE);
                }
                if(exists){
                    this.researchBeingviewed.removeFromAcademicResearching(inputtedAcademic);
                    Academic newTeacher = getobject.academic(inputtedAcademic);
                    newTeacher.removeFromCurrentResearch(this.researchCode);
                    JOptionPane.showMessageDialog(null, "Academic removed successfully, refresh to see changes", "Complete!", JOptionPane.INFORMATION_MESSAGE);
                    editAndCalcInput.setText("");
                }
            } catch (FileNotFoundException ex) {
                JOptionPane.showMessageDialog(null, "You are missing a file! Error Code: 1000X40", "Oops", JOptionPane.ERROR_MESSAGE);
            } catch (IOException ioException) {
                JOptionPane.showMessageDialog(null, "Error with Academic object. Error Code: 4000X50", "Oops", JOptionPane.ERROR_MESSAGE);
            } catch (ClassNotFoundException classNotFoundException) {
                JOptionPane.showMessageDialog(null, "That is not a valid Academic code, make sure you have created the academic first. Error Code: 50X40", "Oops", JOptionPane.ERROR_MESSAGE);
            }
        }
        if(!alreadyOpen) {
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
            if(e.getSource() == researchTitleEditButton){
                InputFrame.setTitle("Edit Research Title");
                title.setText("Enter new Title");

                this.editResearchTitleEnterbutton.setBounds(200,100,75,25);
                this.editResearchTitleEnterbutton.addActionListener(this);

                InputFrame.add(this.editResearchTitleEnterbutton);
            }
            if(e.getSource() == allResearchesAddButton){
                InputFrame.setTitle("Add Academic");
                title.setText("Enter Academic code");

                this.addAcademicEnterbutton.setBounds(200,100,75,25);
                this.addAcademicEnterbutton.addActionListener(this);

                InputFrame.add(this.addAcademicEnterbutton);
            }
            if(e.getSource() == allResearchesDeleteButton){
                InputFrame.setTitle("Remove Academic");
                title.setText("Enter Academic code");

                this.removeAcademicEnterbutton.setBounds(200,100,75,25);
                this.removeAcademicEnterbutton.addActionListener(this);

                InputFrame.add(this.removeAcademicEnterbutton);

            }
        }
    }
}
