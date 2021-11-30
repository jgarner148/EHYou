import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.io.File;
import java.io.IOException;

public class GUIViewModule implements ActionListener {
    private JFrame IDCardFrame = new JFrame();
    private Module modulebeingviewed;
    private String moduleCode;
    private JLabel titleLabel = new JLabel("Module details");
    private JLabel IDLabel = new JLabel("");
    private JLabel modNameLabel = new JLabel("");
    private JButton modNameEditButton = factory.makeFlatButton("Edit");
    private JLabel moderatorLabel = new JLabel("");
    private JButton moderatorEditButton = factory.makeFlatButton("Edit");
    private JLabel allTutorsLabel = new JLabel("");


    private Font labelFont = new Font("Georgia", Font.ITALIC,15);
    private JButton deletebutton = factory.makeFlatButton("Delete");







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
        if(modulebeingviewed.getModerator().equals("")){
            moderator = "None";
        }
        else{
            moderator = modulebeingviewed.getModerator();
        }
        moderatorLabel.setText("Moderator: " + moderator);

        String[] allModerators = modulebeingviewed.getTeachers();
        String AllTutorsText = "";
        for(int i = 0; i<allModerators.length; i++){
            String currentcode = allModerators[i];
            Tutor currentTutor = getobject.tutor(currentcode);
            AllTutorsText = AllTutorsText + currentTutor.getStaffID() + " - " + currentTutor.getFname() + " " + currentTutor.getLname() + "<br>";
        }

        allTutorsLabel.setBounds(xLabel, 200, wLabel, 240);
        allTutorsLabel.setFont(labelFont);
        allTutorsLabel.setText("<html>" + "Teachers:<br>" + AllTutorsText);




        deletebutton.setBounds(320, 460 ,200,50);
        deletebutton.addActionListener(this);

        int xButton = 420;
        int wButton = 75;
        int hButton = 18;
        modNameEditButton.setBounds(xButton,72,wButton,hButton);
        modNameEditButton.addActionListener(this);
        moderatorEditButton.setBounds(xButton,92,wButton,hButton);
        moderatorEditButton.addActionListener(this);


        IDCardFrame.add(titleLabel);

        IDCardFrame.add(IDLabel);
        IDCardFrame.add(modNameLabel);
        IDCardFrame.add(moderatorLabel);
        IDCardFrame.add(allTutorsLabel);

        IDCardFrame.add(deletebutton);

        IDCardFrame.add(modNameEditButton);
        IDCardFrame.add(moderatorEditButton);

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        boolean alreadyOpen = false;
        if(e.getSource() == deletebutton){
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
    }
}
