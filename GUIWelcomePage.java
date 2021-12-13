import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Class that houses the GUI that the user sees when opening the program
 */
public class GUIWelcomePage implements ActionListener {
    //Creating the content
    private final JFrame mainFrame;
    private final JLabel logoText = new JLabel("EHYou");
    private final JButton goToCreate = factory.makeFlatButton("Create");
    private final JButton goToSearch = factory.makeFlatButton("Search");

    /**
     *Constructor for the class that will be called to be the first thing the user see or when they are wanting to switch between create and search
     * @param importedFrame The JFrame that the UI elements to be added to
     */
    public GUIWelcomePage(JFrame importedFrame) {
        //Setting up the Main Frame
        this.mainFrame = importedFrame;
        mainFrame.setTitle("EHYou - Home");

        //Setting up the Logo JLabel
        logoText.setBounds(494, 150,305,110);
        logoText.setFont(new Font("Georgia", Font.PLAIN,95));

        //Setting up the Create Button
        goToCreate.setBounds(408,350,200,75);
        goToCreate.addActionListener(this);

        //Setting up the Search Button
        goToSearch.setBounds(658, 350,200,75);
        goToSearch.addActionListener(this);

        //Adding all elements to the frame
        this.mainFrame.add(logoText);
        this.mainFrame.add(goToCreate);
        this.mainFrame.add(goToSearch);
    }
    /**
     * Invoked when an action occurs.
     *
     * @param e the event to be processed
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        //Clearing the JFrame
        mainFrame.getContentPane().removeAll();
        mainFrame.repaint();
        //When the goToCreate button is clicked
        if(e.getSource()==goToCreate){
            GUICreateHome createhomepage = new GUICreateHome(this.mainFrame);
        }

        //When the goToSearch button is pressed
        if(e.getSource()==goToSearch){
            GUISearchHome searchhomepage = new GUISearchHome(this.mainFrame);
        }
    }
}