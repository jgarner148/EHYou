import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GUICreateModule implements ActionListener{
    private JFrame mainFrame;
    private  JButton goBack = factory.makeFlatButton("BACK");
    private  JLabel titleLabel = new JLabel("Create Module");

    private JLabel modNameLabel = new JLabel("Module name:");
    private JTextField modNameInput = new JTextField();

    private JLabel modCodeLabel = new JLabel("Module code: ");
    private JTextField modCodeInput = new JTextField();

    private JLabel studentsLabel = new JLabel("Students: ");
    private JTextField studentsInput = new JTextField();
    private JButton anotherStudent = new JButton("Add Another");

    private JLabel teachersLabel = new JLabel("Teachers: ");
    private JTextField teachersInput = new JTextField();
    private JButton anotherTeacher = new JButton("Add Another");

    private JLabel moderatorLabel = new JLabel("Moderator: ");
    private JTextField moderatorInput = new JTextField();



    public GUICreateModule(JFrame mainFrame) {
        this.mainFrame = mainFrame;
        this.mainFrame.setTitle("EHYou - Create Module");

        //Setting up the back Button
        goBack.setBounds(0, 0,100,50);
        goBack.addActionListener((ActionListener) this);

        titleLabel.setBounds(450, 30,450,110);
        titleLabel.setFont(new Font("Georgia", Font.PLAIN,55));

        int xLabel = 400;
        modNameLabel.setBounds(xLabel, 150,100,50);
        modCodeLabel.setBounds(xLabel, 200,100,50);

        int xInput = 510;
        int wInput = 250;
        int hInput = 30;
        modNameInput.setBounds(xInput, 160,wInput,hInput);
        modCodeInput.setBounds(xInput, 210,wInput,hInput);


        mainFrame.add(goBack);
        mainFrame.add(titleLabel);
        mainFrame.add(modNameLabel);
        mainFrame.add(modCodeLabel);
        mainFrame.add(modNameInput);
        mainFrame.add(modCodeInput);


    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource()==goBack){
            mainFrame.getContentPane().removeAll();
            mainFrame.repaint();
            GUICreateHome createhomepage = new GUICreateHome(this.mainFrame);
        }
    }
}
