import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

public class GUIViewResult implements ActionListener {
    private final JFrame IDCardFrame = new JFrame();
    private final Result resultBeingViewed;
    private final String resultCode;
    private final JLabel titleLabel = new JLabel("Result details");
    private final JLabel IDLabel = new JLabel("");
    private final JLabel assStudentLabel = new JLabel("");
    private final JButton assStudentEditButton = factory.makeFlatButton("Edit");
    private final JLabel assModuleLabel = new JLabel("");
    private final JButton assModuleEditButton = factory.makeFlatButton("Edit");
    private final JLabel weekAssLabel = new JLabel("");
    private final JButton weekAssEditButton = factory.makeFlatButton("Edit");
    private final JLabel gradeLabel = new JLabel("");
    private final JButton gradeEditButton = factory.makeFlatButton("Edit");
    private final JLabel feedbackLabel = new JLabel("");
    private final JButton feedbackEditButton = factory.makeFlatButton("Edit");
    private final Font labelFont = new Font("Georgia", Font.ITALIC,15);
    private final JButton deletebutton = factory.makeFlatButton("Delete");

    private final JTextField editAndCalcInput = new JTextField();
    private final JButton editAssStudentEnterButton = factory.makeFlatButton("Enter");
    private final JButton editAssModuleEnterButton = factory.makeFlatButton("Enter");
    private final JButton editWeekAssEnterButton = factory.makeFlatButton("Enter");
    private final JButton editGradeEnterButton = factory.makeFlatButton("Enter");
    private final JButton editFeedbackEnterButton = factory.makeFlatButton("Enter");

    public GUIViewResult(String resultCode) throws IOException, ClassNotFoundException {
        this.resultCode = resultCode;
        this.resultBeingViewed = getobject.result(this.resultCode);
        this.IDCardFrame.setSize(560,360);
        this.IDCardFrame.getContentPane().setBackground(new Color(165,213,220));
        this.IDCardFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE );
        this.IDCardFrame.setTitle("ID card - " + resultBeingViewed.getResultCode());
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
        IDLabel.setText("Result Code: " + resultBeingViewed.getResultCode());

        assStudentLabel.setBounds(xLabel,70,wLabel,hLabel);
        assStudentLabel.setFont(labelFont);
        Student assStudent = getobject.student(resultBeingViewed.getAssStudent());
        assStudentLabel.setText("Student: " + assStudent.getStudentNum());

        assModuleLabel.setBounds(xLabel,90,wLabel,hLabel);
        assModuleLabel.setFont(labelFont);
        Module assModule = getobject.module(resultBeingViewed.getAssModule());
        assModuleLabel.setText("Module: " + assModule.getModName() + " - " + assModule.getModCode());

        weekAssLabel.setBounds(xLabel,110,wLabel,hLabel);
        weekAssLabel.setFont(labelFont);
        weekAssLabel.setText("Week: " + resultBeingViewed.getWeekAss());

        gradeLabel.setBounds(xLabel,130,wLabel,hLabel);
        gradeLabel.setFont(labelFont);
        gradeLabel.setText("Grade: " + Integer.toString(resultBeingViewed.getGrade()));

        feedbackLabel.setBounds(xLabel,150,wLabel,hLabel);
        feedbackLabel.setFont(labelFont);
        feedbackLabel.setText("Feedback: " + resultBeingViewed.getFeedback());

        deletebutton.setBounds(320, 260 ,200,50);
        deletebutton.addActionListener(this);

        int xButton = 420;
        int wButton = 75;
        int hButton = 18;
        assStudentEditButton.setBounds(xButton,72,wButton,hButton);
        assStudentEditButton.addActionListener(this);
        assModuleEditButton.setBounds(xButton,92,wButton,hButton);
        assModuleEditButton.addActionListener(this);
        weekAssEditButton.setBounds(xButton,112,wButton,hButton);
        weekAssEditButton.addActionListener(this);
        gradeEditButton.setBounds(xButton,132,wButton,hButton);
        gradeEditButton.addActionListener(this);
        feedbackEditButton.setBounds(xButton,152,wButton,hButton);
        feedbackEditButton.addActionListener(this);


        IDCardFrame.add(titleLabel);

        IDCardFrame.add(IDLabel);
        IDCardFrame.add(assStudentLabel);
        IDCardFrame.add(assModuleLabel);
        IDCardFrame.add(weekAssLabel);
        IDCardFrame.add(gradeLabel);
        IDCardFrame.add(feedbackLabel);
        IDCardFrame.add(deletebutton);

        IDCardFrame.add(assStudentEditButton);
        IDCardFrame.add(assModuleEditButton);
        IDCardFrame.add(weekAssEditButton);
        IDCardFrame.add(gradeEditButton);
        IDCardFrame.add(feedbackEditButton);
    }

    /**
     * Invoked when an action occurs.
     *
     * @param e the event to be processed
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        boolean alreadyOpen = false;
        if (e.getSource() == deletebutton) {
            alreadyOpen = true;
            int confirmation = JOptionPane.showConfirmDialog(null, "Are you sure you want to delete " + resultBeingViewed.getResultCode(), "Confirm delete", JOptionPane.YES_NO_OPTION, JOptionPane.ERROR_MESSAGE);
            if (confirmation == 0) {
                String filename = "Results/" + resultBeingViewed.getResultCode() + ".txt";
                File oldFile = new File(filename);
                oldFile.delete();
                IDCardFrame.dispatchEvent(new WindowEvent(IDCardFrame, WindowEvent.WINDOW_CLOSING));
                JOptionPane.showMessageDialog(null, "Result Deleted", "Deleted!", JOptionPane.INFORMATION_MESSAGE);
            }
        }
        if(e.getSource() == editAssStudentEnterButton && !alreadyOpen){
            alreadyOpen = true;
            String inputtedStudent = editAndCalcInput.getText();
            try {
                boolean exists = quickMethods.checkIfInFile("codes/studentNumbers.csv",inputtedStudent);
                if(!exists){
                    JOptionPane.showMessageDialog(null, "That is not a valid Student code, make sure you have created the student first. Error Code: 50X40", "Oops", JOptionPane.ERROR_MESSAGE);
                }
                if(exists){
                    Student oldStudent = getobject.student(resultBeingViewed.getAssStudent());
                    oldStudent.removeFromAllResults(this.resultCode);
                    this.resultBeingViewed.setAssStudent(inputtedStudent);
                    this.assStudentLabel.setText("Student: " + inputtedStudent);
                    JOptionPane.showMessageDialog(null, "Student changed successfully", "Complete!", JOptionPane.INFORMATION_MESSAGE);
                    editAndCalcInput.setText("");
                }
            } catch (FileNotFoundException ex) {
                JOptionPane.showMessageDialog(null, "You are missing a file! Error Code: 1000X30", "Oops", JOptionPane.ERROR_MESSAGE);
            } catch (IOException ioException) {
                JOptionPane.showMessageDialog(null, "Error with Student object. Error Code: 4000X10", "Oops", JOptionPane.ERROR_MESSAGE);
            } catch (ClassNotFoundException classNotFoundException) {
                JOptionPane.showMessageDialog(null, "That is not a valid Student code, make sure you have created the tutor first. Error Code: 50X30", "Oops", JOptionPane.ERROR_MESSAGE);
            }
        }
        if(e.getSource() == editAssModuleEnterButton && !alreadyOpen){
            alreadyOpen = true;
            String inputtedModule = editAndCalcInput.getText();
            try {
                boolean exists = quickMethods.checkIfInFile("codes/modulecodes.csv",inputtedModule);
                if(!exists){
                    JOptionPane.showMessageDialog(null, "That is not a valid Module code, make sure you have created the module first. Error Code: 50X10", "Oops", JOptionPane.ERROR_MESSAGE);
                }
                if(exists){
                    this.resultBeingViewed.setAssModule(inputtedModule);
                    this.assModuleLabel.setText("Module: " + inputtedModule);
                    JOptionPane.showMessageDialog(null, "Module changed successfully", "Complete!", JOptionPane.INFORMATION_MESSAGE);
                    editAndCalcInput.setText("");
                }
            } catch (FileNotFoundException ex) {
                JOptionPane.showMessageDialog(null, "You are missing a file! Error Code: 1000X10", "Oops", JOptionPane.ERROR_MESSAGE);
            } catch (IOException ioException) {
                JOptionPane.showMessageDialog(null, "Error with result object. Error Code: 4000X30", "Oops", JOptionPane.ERROR_MESSAGE);
            }
        }
        if(e.getSource() == editWeekAssEnterButton && !alreadyOpen){
            alreadyOpen = true;
            try {
                this.resultBeingViewed.setWeekAss(this.editAndCalcInput.getText());
                this.weekAssLabel.setText("Week: " + resultBeingViewed.getWeekAss());
                JOptionPane.showMessageDialog(null, "Week changed successfully", "Complete!", JOptionPane.INFORMATION_MESSAGE);
                editAndCalcInput.setText("");
            } catch (IOException ex) {
                JOptionPane.showMessageDialog(null, "Error with Result object. Error Code: 4000X30", "Oops", JOptionPane.ERROR_MESSAGE);
            }

        }
        if(e.getSource() == editGradeEnterButton && !alreadyOpen){
            alreadyOpen = true;
            if(Integer.parseInt((editAndCalcInput.getText()))<101) {
                try {
                    this.resultBeingViewed.setGrade(Integer.parseInt(this.editAndCalcInput.getText()));
                    this.gradeLabel.setText("Grade: " + editAndCalcInput.getText());
                    JOptionPane.showMessageDialog(null, "Grade changed successfully", "Complete!", JOptionPane.INFORMATION_MESSAGE);
                    editAndCalcInput.setText("");
                } catch (IOException ex) {
                    JOptionPane.showMessageDialog(null, "Error with Result object. Error Code: 4000X30", "Oops", JOptionPane.ERROR_MESSAGE);
                }
            }
            else{
                JOptionPane.showMessageDialog(null, "Invalid Input. Error Code: 500", "Oops", JOptionPane.ERROR_MESSAGE);
            }
        }
        if(e.getSource() == editFeedbackEnterButton && !alreadyOpen){
            alreadyOpen = true;
            try {
                this.resultBeingViewed.setFeedback(this.editAndCalcInput.getText());
                this.feedbackLabel.setText("Feedback: " + resultBeingViewed.getFeedback());
                JOptionPane.showMessageDialog(null, "Feedback changed successfully", "Complete!", JOptionPane.INFORMATION_MESSAGE);
                editAndCalcInput.setText("");
            } catch (IOException ex) {
                JOptionPane.showMessageDialog(null, "Error with Result object. Error Code: 4000X30", "Oops", JOptionPane.ERROR_MESSAGE);
            }
        }
        if (!alreadyOpen) {
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
            if(e.getSource() == assStudentEditButton){
                InputFrame.setTitle("Edit Assigned Student");
                title.setText("Enter new Student");

                this.editAssStudentEnterButton.setBounds(200,100,75,25);
                this.editAssStudentEnterButton.addActionListener(this);

                InputFrame.add(editAssStudentEnterButton);
            }
            if(e.getSource() == assModuleEditButton){
                InputFrame.setTitle("Edit Assigned Module");
                title.setText("Enter new Module");

                this.editAssModuleEnterButton.setBounds(200,100,75,25);
                this.editAssModuleEnterButton.addActionListener(this);

                InputFrame.add(editAssModuleEnterButton);
            }
            if(e.getSource() == weekAssEditButton){
                InputFrame.setTitle("Edit Week Assigned");
                title.setText("Enter new Week");

                this.editWeekAssEnterButton.setBounds(200,100,75,25);
                this.editWeekAssEnterButton.addActionListener(this);

                InputFrame.add(editWeekAssEnterButton);
            }
            if(e.getSource() == gradeEditButton){
                InputFrame.setTitle("Edit Grade");
                title.setText("Enter new Grade");

                this.editGradeEnterButton.setBounds(200,100,75,25);
                this.editGradeEnterButton.addActionListener(this);

                InputFrame.add(editGradeEnterButton);
            }
            if(e.getSource() == feedbackEditButton){
                InputFrame.setTitle("Edit feedback");
                title.setText("Enter new feedback");

                this.editFeedbackEnterButton.setBounds(200,100,75,25);
                this.editFeedbackEnterButton.addActionListener(this);

                InputFrame.add(editFeedbackEnterButton);
            }
        }
    }
}
