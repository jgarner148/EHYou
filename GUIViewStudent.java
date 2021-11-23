import javax.swing.*;
import java.awt.*;
import java.io.IOException;

public class GUIViewStudent {
    private JFrame IDCardFrame = new JFrame();
    private String studentCode;
    private JLabel IDLabel = new JLabel("");
    private JLabel fnameLabel = new JLabel("");
    private JLabel lnameLabel = new JLabel("");
    private JLabel dobLabel = new JLabel("");
    private JLabel staryYrLabel = new JLabel("");
    private JLabel endYrLabel = new JLabel("");
    private JLabel allModulesLabel = new JLabel("");
    private JLabel allResultsLabel = new JLabel("");


    public GUIViewStudent( String studentCode) throws IOException, ClassNotFoundException {
        this.studentCode = studentCode;
        Student studentBeingViewed = getobject.student(this.studentCode);
        this.IDCardFrame.setSize(480,480);
        this.IDCardFrame.getContentPane().setBackground(new Color(165,213,220));
        this.IDCardFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE );
        this.IDCardFrame.setTitle("ID card - " + studentBeingViewed.getFname() + " " + studentBeingViewed.getLname() + " (" + studentBeingViewed.getStudentNum() + ")");
        ImageIcon logoIcon = new ImageIcon("Images/icon.png");
        this.IDCardFrame.setIconImage(logoIcon.getImage());
        this.IDCardFrame.setResizable(false);
        this.IDCardFrame.setVisible(true);

        int xLabel = 20;
        int wLabel = 250;
        int hLabel = 15;

        IDLabel.setBounds(xLabel,50,wLabel,hLabel);
        IDLabel.setText("Student Number: " + studentBeingViewed.getStudentNum());

        fnameLabel.setBounds(xLabel,70,wLabel,hLabel);
        fnameLabel.setText("First Name: " + studentBeingViewed.getFname());

        lnameLabel.setBounds(xLabel,90,wLabel,hLabel);
        lnameLabel.setText("Last name: " + studentBeingViewed.getLname());

        dobLabel.setBounds(xLabel,110,wLabel,hLabel);
        dobLabel.setText("Date of Birth: " + studentBeingViewed.getDob());

        staryYrLabel.setBounds(xLabel,130,wLabel,hLabel);
        staryYrLabel.setText("Year Started: " + studentBeingViewed.getStartYr());

        endYrLabel.setBounds(xLabel,150,wLabel,hLabel);
        endYrLabel.setText("Last Year: " + studentBeingViewed.getEndYr());

        String[] allModules = studentBeingViewed.getModulesTaking();
        String allModulesText = "";
        for(int i = 0; i<allModules.length; i++){
            String currentcode = allModules[i];
            System.out.println(currentcode);
            Module currentModule = getobject.module(currentcode);
            allModulesText = allModulesText + currentModule.getModName() + " - " + currentModule.getModCode() + " \n";
        }

        allModulesLabel.setBounds(xLabel, 200, wLabel, 240);
        allModulesLabel.setText(allModulesText);

        allResultsLabel.setBounds(120, 200, wLabel, 240);
        allResultsLabel.setText("this is a test");


        IDCardFrame.add(IDLabel);
        IDCardFrame.add(fnameLabel);
        IDCardFrame.add(lnameLabel);
        IDCardFrame.add(dobLabel);
        IDCardFrame.add(staryYrLabel);
        IDCardFrame.add(endYrLabel);
        IDCardFrame.add(allModulesLabel);
        IDCardFrame.add(allResultsLabel);


    }
}
