import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

public class GUIViewModule implements ActionListener {
    private final JFrame IDCardFrame = new JFrame();
    private final Module modulebeingviewed;
    private final String moduleCode;
    private final JLabel titleLabel = new JLabel("Module details");
    private final JLabel IDLabel = new JLabel("");
    private final JLabel modNameLabel = new JLabel("");
    private final JButton modNameEditButton = factory.makeFlatButton("Edit");
    private final JLabel moderatorLabel = new JLabel("");
    private final JButton moderatorEditButton = factory.makeFlatButton("Edit");
    private final JLabel allTutorsLabel = new JLabel("");
    private final JButton allTutorsAddButton = factory.makeFlatButton("Add");
    private final JButton allTutorsDeleteButton = factory.makeFlatButton("Delete");

    private final Font labelFont = new Font("Georgia", Font.ITALIC,15);
    private final JButton classListButton = factory.makeFlatButton("View class list");
    private final JButton minMarkButton = factory.makeFlatButton("Calculate minimum mark");
    private final JButton averageMarkButton = factory.makeFlatButton("Calculate average mark");
    private final JButton maxMarkButton = factory.makeFlatButton("Calculate maximum mark");
    private final JButton deletebutton = factory.makeFlatButton("Delete");

    private final JTextField editAndCalcInput = new JTextField();
    private final JButton editModNameEnterbutton = factory.makeFlatButton("Enter");
    private final JButton editModeratorEnterButton = factory.makeFlatButton("Enter");
    private final JButton addTeacherEnterButton = factory.makeFlatButton("Enter");
    private final JButton removeTeacherEnterButton = factory.makeFlatButton("Enter");

    public GUIViewModule(String moduleCode) throws IOException, ClassNotFoundException {
        this.moduleCode = moduleCode;
        this.modulebeingviewed = getobject.module(this.moduleCode);
        this.IDCardFrame.setSize(560,560);
        this.IDCardFrame.getContentPane().setBackground(new Color(165,213,220));
        this.IDCardFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE );
        this.IDCardFrame.setTitle("ID card - " + modulebeingviewed.getModName()+ " (" + modulebeingviewed.getModCode() + ")");
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
        IDLabel.setText("Module Code: " + modulebeingviewed.getModCode());

        modNameLabel.setBounds(xLabel,70,wLabel,hLabel);
        modNameLabel.setFont(labelFont);
        modNameLabel.setText("Module Name: " + modulebeingviewed.getModName());

        moderatorLabel.setBounds(xLabel,90,wLabel,hLabel);
        moderatorLabel.setFont(labelFont);
        String moderator;
        Tutor currentModerator = null;
        if(modulebeingviewed.getModerator().equals("")){
            moderatorLabel.setText("Moderator: None");
        }
        else{
            moderator = modulebeingviewed.getModerator();
            currentModerator = getobject.tutor(moderator);
            moderatorLabel.setText("Moderator: " + currentModerator.getStaffID() + " - " + currentModerator.getFname() + " " + currentModerator.getLname());
        }


        String[] allTeachers = modulebeingviewed.getTeachers();
        String AllTutorsText = "";
        for(int i = 0; i<allTeachers.length; i++){
            String currentcode = allTeachers[i];
            Tutor currentTutor = getobject.tutor(currentcode);
            AllTutorsText = AllTutorsText + currentTutor.getStaffID() + " - " + currentTutor.getFname() + " " + currentTutor.getLname() + "<br>";
        }

        allTutorsLabel.setBounds(xLabel, 200, wLabel, 240);
        allTutorsLabel.setFont(labelFont);
        allTutorsLabel.setText("<html>" + "Teachers:<br>" + AllTutorsText);



        classListButton.setBounds(320, 220,200,50);
        classListButton.addActionListener(this);
        minMarkButton.setBounds(320, 280,200,50);
        minMarkButton.addActionListener(this);
        averageMarkButton.setBounds(320, 340,200,50);
        averageMarkButton.addActionListener(this);
        maxMarkButton.setBounds(320, 400,200,50);
        maxMarkButton.addActionListener(this);
        deletebutton.setBounds(320, 460 ,200,50);
        deletebutton.addActionListener(this);

        int xButton = 420;
        int wButton = 75;
        int hButton = 18;
        modNameEditButton.setBounds(xButton,72,wButton,hButton);
        modNameEditButton.addActionListener(this);
        moderatorEditButton.setBounds(xButton,92,wButton,hButton);
        moderatorEditButton.addActionListener(this);

        allTutorsAddButton.setBounds(20,475,85,hButton);
        allTutorsAddButton.addActionListener(this);

        allTutorsDeleteButton.setBounds(115,475,85,hButton);
        allTutorsDeleteButton.addActionListener(this);


        IDCardFrame.add(titleLabel);

        IDCardFrame.add(IDLabel);
        IDCardFrame.add(modNameLabel);
        IDCardFrame.add(moderatorLabel);
        IDCardFrame.add(allTutorsLabel);

        IDCardFrame.add(averageMarkButton);
        IDCardFrame.add(minMarkButton);
        IDCardFrame.add(maxMarkButton);
        IDCardFrame.add(classListButton);
        IDCardFrame.add(deletebutton);

        IDCardFrame.add(modNameEditButton);
        IDCardFrame.add(moderatorEditButton);
        IDCardFrame.add(allTutorsAddButton);
        IDCardFrame.add(allTutorsDeleteButton);

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
            int confirmation = JOptionPane.showConfirmDialog(null, "Are you sure you want to delete " + modulebeingviewed.getModCode(), "Confirm delete", JOptionPane.YES_NO_OPTION, JOptionPane.ERROR_MESSAGE);
            if (confirmation == 0){
                String filename = "Modules/" +modulebeingviewed.getModCode() + ".txt";
                File oldFile = new File(filename);
                oldFile.delete();
                IDCardFrame.dispatchEvent(new WindowEvent(IDCardFrame, WindowEvent.WINDOW_CLOSING));
                JOptionPane.showMessageDialog(null, "Module Deleted", "Deleted!", JOptionPane.INFORMATION_MESSAGE);
            }
        }
        if(e.getSource() == classListButton && !alreadyOpen){
            alreadyOpen = true;
            JFrame ClassListFrame = new JFrame();
            ClassListFrame.setSize(450, 500);
            ClassListFrame.setTitle(modulebeingviewed.getModName() + " Class List");
            ClassListFrame.getContentPane().setBackground(new Color(165,213,220));
            ClassListFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            ImageIcon logoIcon = new ImageIcon("Images/icon.png");
            ClassListFrame.setIconImage(logoIcon.getImage());
            ClassListFrame.setLayout(null);
            ClassListFrame.setResizable(false);
            ClassListFrame.setLocationRelativeTo(null);
            ClassListFrame.setVisible(true);

            JLabel listTitle = new JLabel(modulebeingviewed.getModName() + " class list");
            JLabel list = new JLabel("");
            JLabel containLabel = new JLabel("");

            listTitle.setFont(new Font("Georgia", Font.ITALIC,25));
            listTitle.setBounds(10,10,400,40);

            String[] allStudents = modulebeingviewed.getStudentsTaking();
            String allStudetnsText = "";
            for(int i = 0; i<allStudents.length; i++){
                String currentcode = allStudents[i];
                Student currentStudnet = null;
                try {
                    currentStudnet = getobject.student(currentcode);
                } catch (IOException | ClassNotFoundException ex) {
                    JOptionPane.showMessageDialog(null, "Error with Student object. Error Code: 4000X10", "Oops", JOptionPane.ERROR_MESSAGE);
                }
                assert currentStudnet != null;
                allStudetnsText = allStudetnsText + currentStudnet.getStudentNum() + " - " + currentStudnet.getFname() + " " + currentStudnet.getLname() + "<br>";
            }

            list.setText("<html>" + allStudetnsText);
            list.setBounds(10,50,500,500);
            list.setFont(labelFont);

            ClassListFrame.add(listTitle);
            ClassListFrame.add(list);

        }
        if(e.getSource() == minMarkButton && !alreadyOpen){
            alreadyOpen = true;
            JOptionPane.showMessageDialog(null, "Minimum mark for " + this.modulebeingviewed.getModName() +": " + this.modulebeingviewed.getMinMark(), "Result", JOptionPane.INFORMATION_MESSAGE);
        }
        if(e.getSource() == averageMarkButton && !alreadyOpen){
            alreadyOpen = true;
            JOptionPane.showMessageDialog(null, "Average mark for " + this.modulebeingviewed.getModName() +": " + this.modulebeingviewed.getAverageMark(), "Result", JOptionPane.INFORMATION_MESSAGE);
        }
        if(e.getSource() == maxMarkButton && !alreadyOpen){
            alreadyOpen = true;
            JOptionPane.showMessageDialog(null, "Maximum mark for " + this.modulebeingviewed.getModName() +": " + this.modulebeingviewed.getMaxMark(), "Result", JOptionPane.INFORMATION_MESSAGE);
        }
        if(e.getSource() == editModNameEnterbutton && !alreadyOpen){
            alreadyOpen = true;
            try {
                this.modulebeingviewed.setModName(this.editAndCalcInput.getText());
                this.modNameLabel.setText("First Name: " + modulebeingviewed.getModName());
                JOptionPane.showMessageDialog(null, "Module Name changed successfully", "Complete!", JOptionPane.INFORMATION_MESSAGE);
                editAndCalcInput.setText("");
            } catch (IOException ex) {
                JOptionPane.showMessageDialog(null, "Error with Module object. Error Code: 4000X20", "Oops", JOptionPane.ERROR_MESSAGE);
            }
        }
        if(e.getSource() == editModeratorEnterButton && !alreadyOpen){
            alreadyOpen = true;
            String inputtedtutor = editAndCalcInput.getText();
            try {
                boolean exists = quickMethods.checkIfInFile("codes/staffcodes.csv",inputtedtutor);
                if (!exists) {
                    JOptionPane.showMessageDialog(null, "That is not a valid Tutor code, make sure you have created the tutor first. Error Code: 50X40", "Oops", JOptionPane.ERROR_MESSAGE);
                }
                if(exists){
                    Tutor oldModerator = getobject.tutor(modulebeingviewed.getModerator());
                    oldModerator.removeFromModulesModerating(this.moduleCode);
                    this.modulebeingviewed.setModerator(inputtedtutor);
                    Tutor newModerator = getobject.tutor(inputtedtutor);
                    this.moderatorLabel.setText("Moderator: " + newModerator.getStaffID() + " - " + newModerator.getFname() + " " + newModerator.getLname());
                    JOptionPane.showMessageDialog(null, "Moderator changed successfully", "Complete!", JOptionPane.INFORMATION_MESSAGE);
                    editAndCalcInput.setText("");
                }
            } catch (FileNotFoundException ex) {
                JOptionPane.showMessageDialog(null, "You are missing a file! Error Code: 1000X40", "Oops", JOptionPane.ERROR_MESSAGE);
            } catch (IOException ioException) {
                JOptionPane.showMessageDialog(null, "Error with Tutor object. Error Code: 4000X20", "Oops", JOptionPane.ERROR_MESSAGE);
            } catch (ClassNotFoundException classNotFoundException) {
                JOptionPane.showMessageDialog(null, "That is not a valid Tutor code, make sure you have created the tutor first. Error Code: 50X40", "Oops", JOptionPane.ERROR_MESSAGE);
            }
        }
        if(e.getSource() == addTeacherEnterButton && !alreadyOpen){
            alreadyOpen = true;
            String inputtedtutor = editAndCalcInput.getText();
            try {
                boolean exists = quickMethods.checkIfInFile("codes/staffcodes.csv",inputtedtutor);
                if (!exists) {
                    JOptionPane.showMessageDialog(null, "That is not a valid Tutor code, make sure you have created the tutor first. Error Code: 50X40", "Oops", JOptionPane.ERROR_MESSAGE);
                }
                if(exists){
                    this.modulebeingviewed.addToTeachers(inputtedtutor);
                    Tutor newTeacher = getobject.tutor(inputtedtutor);
                    this.allTutorsLabel.setText(this.allTutorsLabel.getText() + newTeacher.getStaffID() + " - " + newTeacher.getFname() + " " + newTeacher.getLname() + "<br>");
                    JOptionPane.showMessageDialog(null, "Teacher added successfully", "Complete!", JOptionPane.INFORMATION_MESSAGE);
                    editAndCalcInput.setText("");
                }
            } catch (FileNotFoundException ex) {
                JOptionPane.showMessageDialog(null, "You are missing a file! Error Code: 1000X40", "Oops", JOptionPane.ERROR_MESSAGE);
            } catch (IOException ioException) {
                JOptionPane.showMessageDialog(null, "Error with Tutor object. Error Code: 4000X20", "Oops", JOptionPane.ERROR_MESSAGE);
            } catch (ClassNotFoundException classNotFoundException) {
                JOptionPane.showMessageDialog(null, "That is not a valid Tutor code, make sure you have created the tutor first. Error Code: 50X40", "Oops", JOptionPane.ERROR_MESSAGE);
            }

        }
        if(e.getSource() == removeTeacherEnterButton && !alreadyOpen){
            alreadyOpen = true;
            String inputtedtutor = editAndCalcInput.getText();
            try {
                boolean exists = quickMethods.checkIfInFile("codes/staffcodes.csv",inputtedtutor);
                if (!exists) {
                    JOptionPane.showMessageDialog(null, "That is not a valid Tutor code, make sure you have created the tutor first. Error Code: 50X40", "Oops", JOptionPane.ERROR_MESSAGE);
                }
                if(exists){
                    this.modulebeingviewed.removeFromTeachers(inputtedtutor);
                    Tutor newTeacher = getobject.tutor(inputtedtutor);
                    newTeacher.removeFromModulesTeaching(this.moduleCode);
                    JOptionPane.showMessageDialog(null, "Teacher removed successfully, refresh to see changes", "Complete!", JOptionPane.INFORMATION_MESSAGE);
                    editAndCalcInput.setText("");
                }
            } catch (FileNotFoundException ex) {
                JOptionPane.showMessageDialog(null, "You are missing a file! Error Code: 1000X40", "Oops", JOptionPane.ERROR_MESSAGE);
            } catch (IOException ioException) {
                JOptionPane.showMessageDialog(null, "Error with Tutor object. Error Code: 4000X20", "Oops", JOptionPane.ERROR_MESSAGE);
            } catch (ClassNotFoundException classNotFoundException) {
                JOptionPane.showMessageDialog(null, "That is not a valid Tutor code, make sure you have created the tutor first. Error Code: 50X40", "Oops", JOptionPane.ERROR_MESSAGE);
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
            if(e.getSource() == modNameEditButton){
                InputFrame.setTitle("Edit Module Name");
                title.setText("Enter Module Name");

                this.editModNameEnterbutton.setBounds(200,100,75,25);
                this.editModNameEnterbutton.addActionListener(this);

                InputFrame.add(this.editModNameEnterbutton);
            }
            if(e.getSource() == moderatorEditButton){
                InputFrame.setTitle("Edit Moderator");
                title.setText("Enter Moderator code");

                this.editModeratorEnterButton.setBounds(200,100,75,25);
                this.editModeratorEnterButton.addActionListener(this);

                InputFrame.add(this.editModeratorEnterButton);
            }
            if(e.getSource() == allTutorsAddButton){
                InputFrame.setTitle("Add Teacher");
                title.setText("Enter Teacher code");

                this.addTeacherEnterButton.setBounds(200,100,75,25);
                this.addTeacherEnterButton.addActionListener(this);

                InputFrame.add(this.addTeacherEnterButton);
            }
            if(e.getSource() == allTutorsDeleteButton){
                InputFrame.setTitle("Delete Teacher");
                title.setText("Enter Teacher Code");

                this.removeTeacherEnterButton.setBounds(200,100,75,25);
                this.removeTeacherEnterButton.addActionListener(this);

                InputFrame.add(this.removeTeacherEnterButton);
            }
        }
    }
}
