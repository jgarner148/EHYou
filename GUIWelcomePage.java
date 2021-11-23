import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GUIWelcomePage implements ActionListener {
    private JFrame mainFrame;
    private JLabel logoText = new JLabel("EHYou");
    private JButton goToCreate = factory.makeFlatButton("Create");
    private JButton goToSearch = factory.makeFlatButton("Search");

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

    @Override
    public void actionPerformed(ActionEvent e) {
        mainFrame.getContentPane().removeAll();
        mainFrame.repaint();
        //When the goToCreate button is clicked
        if(e.getSource()==goToCreate){
            GUICreateHome createhomepage = new GUICreateHome(this.mainFrame);
        }

        //When the goToSearch button is pressed
        if(e.getSource()==goToSearch){
            //mainFrame.getContentPane().removeAll();
            //mainFrame.repaint();
            GUISearchHome searchhomepage = new GUISearchHome(this.mainFrame);

        }

    }
}
