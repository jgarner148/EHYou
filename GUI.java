import javax.swing.*;
import java.awt.*;

/**
 * Programs main method that sets up the GUI's JFrame
 */
public class GUI {
    /**
     * Main method to build the GUI for the program
     * @param args Command Line Arguments in the form of a String array
     */
    public static void main(String[] args) {

        //Configuring the master JFrame for the program
        JFrame frame = new JFrame();
        frame.setSize(1280,720);
        frame.getContentPane().setBackground(new Color(216,198,236));
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setTitle("EHYou");
        ImageIcon logoIcon = new ImageIcon("Images/icon.png");
        frame.setIconImage(logoIcon.getImage());
        frame.setLayout(null);
        frame.setResizable(false);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);

        //Runs the welcome page constructor which is the first page the user sees
        GUIWelcomePage welcomepage = new GUIWelcomePage(frame);


    }

}
