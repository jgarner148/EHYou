/**
 * The class that houses the GUI for the create home page
 */

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GUICreateHome implements ActionListener {
    //Setting up the global GUI elements
    private final JFrame mainFrame;
    private final JButton goBack = factory.makeFlatButton("BACK");
    private final JLabel titleLabel = new JLabel("Create");
    private final JLabel subtitleLabel = new JLabel("Welcome to create! Select what type you are wanting to make");
    private final JButton makeStudent = factory.makeFlatButton("Student");
    private final JButton makeModule = factory.makeFlatButton("Module");
    private final JButton makeResult = factory.makeFlatButton("Result");
    private final JButton makeTutor = factory.makeFlatButton("Tutor");
    private final JButton makeAcademic = factory.makeFlatButton("Academic");
    private final JButton makeResearch = factory.makeFlatButton("Research");

    /**
     * Constructor for the class that will be called when wanting to display to GUI for the create homepage
     * @param mainFrame The master frame that is already being displayed to the user when the method is called
     */
    public GUICreateHome(JFrame mainFrame) {
        //Setting up the main frame
        this.mainFrame = mainFrame;
        mainFrame.setTitle("EHYou - Create");

        //Setting up the back Button
        goBack.setBounds(0, 0,100,50);
        goBack.addActionListener(this);

        //Setting up the title JLabel
        titleLabel.setBounds(525, 120,303,110);
        titleLabel.setFont(new Font("Georgia", Font.PLAIN,75));

        //Setting up the subtitle JLabel
        subtitleLabel.setBounds(388, 190,750,110);
        subtitleLabel.setFont(new Font("Georgia", Font.PLAIN,20));

        //Setting up the Student Button
        makeStudent.setBounds(277, 300,200,100);
        makeStudent.addActionListener(this);

        //Setting up the Module Button
        makeModule.setBounds(537, 300,200,100);
        makeModule.addActionListener(this);

        //Setting up the Result Button
        makeResult.setBounds(787, 300,200,100);
        makeResult.addActionListener(this);

        //Setting up the Tutor Button
        makeTutor.setBounds(277, 450,200,100);
        makeTutor.addActionListener(this);

        //Setting up the Academic Button
        makeAcademic.setBounds(537, 450,200,100);
        makeAcademic.addActionListener(this);

        //Setting up the Research Button
        makeResearch.setBounds(787, 450,200,100);
        makeResearch.addActionListener(this);

        //Adding the elements to the main frame
        this.mainFrame.add(goBack);
        this.mainFrame.add(titleLabel);
        this.mainFrame.add(subtitleLabel);
        this.mainFrame.add(makeStudent);
        this.mainFrame.add(makeModule);
        this.mainFrame.add(makeResult);
        this.mainFrame.add(makeTutor);
        this.mainFrame.add(makeAcademic);
        this.mainFrame.add(makeResearch);
    }

    /**
     * Invoked when an action occurs.
     *
     * @param e the event to be processed
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        //Clearing the main frame of content
        mainFrame.getContentPane().removeAll();
        mainFrame.repaint();
        //Method for when the back button is pressed which will return the user to the home page
        if (e.getSource()==goBack){
            GUIWelcomePage welcomepage = new GUIWelcomePage(this.mainFrame);
        }
        //Method for when the student button is pressed which launches the create student page
        if (e.getSource()==makeStudent){
            GUICreateStudent studentcreatepage = new GUICreateStudent(this.mainFrame);
        }
        //Method for when the module button is pressed which launches the create module page
        if (e.getSource()==makeModule){
            GUICreateModule modulecreatepage = new GUICreateModule(this.mainFrame);
        }
        //Method for when the result button is pressed which launches the create result page
        if (e.getSource()==makeResult){
            GUICreateResult resultcreatepage = new GUICreateResult(this.mainFrame);
        }
        //Method for when the tutor button is pressed which launches the create tutor page
        if (e.getSource()==makeTutor){
            GUICreateTutor tutorcreatepage = new GUICreateTutor(this.mainFrame);
        }
        //Method for when the academic button is pressed which launches the create academic page
        if (e.getSource()==makeAcademic){
            GUICreateAcademic academiccreatepage = new GUICreateAcademic(this.mainFrame);
        }
        //Method for when the research button is pressed which launches the create research page
        if (e.getSource()==makeResearch){
            GUICreateResearch researchcreatepage = new GUICreateResearch(this.mainFrame);
        }

    }
}
