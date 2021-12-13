
import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import java.awt.*;

/**
 * factory class used for making pre styled GUI elements
 * Currently only houses a method for JButtons
 */
public class factory {
    /**
     * Method to make simple flat button supplied from:
     * https://stackoverflow.com/questions/1839074/howto-make-jbutton-with-simple-flat-style
     * I have added it into my own factory.makeFlatButton method
     * @param text The text that will be contained in the created JButton
     * @return The newly created button with the flat design
     */
    public static JButton makeFlatButton(String text){
        JButton button = new JButton(text);//Creating the button
        //Setting the buttons style
        button.setForeground(Color.BLACK);
        button.setBackground(Color.WHITE);
        Border line = new LineBorder(Color.BLACK);
        Border margin = new EmptyBorder(5, 15, 5, 15);
        Border compound = new CompoundBorder(line, margin);
        button.setFocusPainted(false);
        button.setBorder(compound);
        return button;//Returning the button

    }
}
