/**
 * Programs main method that sets up the GUI's JFrame
 */

import javax.swing.*;
import java.awt.*;
import java.io.IOException;

public class GUI {
    /**
     *
     * @param args
     * @throws IOException
     * @throws ClassNotFoundException
     */
    public static void main(String[] args) throws IOException, ClassNotFoundException {
        String[] test1 = {};
        int[] test2 = {};

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
        /**
         * Runs the WelcomePage constructor which loads the first page that the user sees
         */
        GUIWelcomePage welcomepage = new GUIWelcomePage(frame);


    }

}
