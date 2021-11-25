import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

/**
 * Class for the search options home page
 */
public class GUISearchHome implements ActionListener{
    /**
     * The frame that all the items will be added to
     */
    private JFrame mainFrame;
    /**
     * The page title label displaying "Search"
     */
    private JLabel titleLabel = new JLabel("Search");
    /**
     * Label for the subtitle
     */
    private JLabel subtitleLabel = new JLabel("Search using the unique ID code of the entity you are trying to find");
    /**
     * The button that allows the user to go back to the
     */
    private  JButton goBack = factory.makeFlatButton("BACK");
    /**
     * The button that is pressed to start the search
     */
    private JButton goSearch = factory.makeFlatButton("Search");
    /**
     * The text field that the user inputs the code they want to search for into
     */
    private JTextField searchBox = new JTextField();

    /**
     * The constructor for the GUISearchHome class that is called when the program wants to draw the Search home page
     * @param mainFrame The frame that all the items will be added to
     */
    public GUISearchHome(JFrame mainFrame){
        this.mainFrame = mainFrame;

        //Setting up the back Button
        goBack.setBounds(0, 0,100,50);
        goBack.addActionListener(this);

        //Setting up the title label
        titleLabel.setBounds(520, 120,303,110);
        titleLabel.setFont(new Font("Georgia", Font.PLAIN,75));

        //Setting up the subtitle label
        subtitleLabel.setBounds(337, 200, 750, 75);
        subtitleLabel.setFont(new Font("Georgia", Font.ITALIC, 20));

        //Setting up the search box text field
        searchBox.setBounds(300, 280, 550, 35);

        //setting up the gosearch button
        goSearch.setBounds(880, 280, 85, 35);
        goSearch.addActionListener(this);

        mainFrame.add(titleLabel);
        mainFrame.add(goBack);
        mainFrame.add(subtitleLabel);
        mainFrame.add(searchBox);
        mainFrame.add(goSearch);



    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource()==goBack){
            mainFrame.getContentPane().removeAll();
            mainFrame.repaint();
            GUIWelcomePage welcomepage = new GUIWelcomePage(this.mainFrame);
        }
        if(e.getSource()==goSearch){
            String searchContents = searchBox.getText();
            String objectType = "";
            try {
                objectType = search.code(searchContents);
            } catch (IOException ex) {
                JOptionPane.showMessageDialog(null, "An error occured. Error Code: 4050X10", "Oops", JOptionPane.ERROR_MESSAGE);
            }
            if(objectType.equals("none")){
                JOptionPane.showMessageDialog(null, "That wasn't a valid code, make sure you have created the entity. Error code: 500","Oops", JOptionPane.ERROR_MESSAGE);
            }
            if(objectType.equals("Students")){
                try {
                    GUIViewStudent viewStudentPage = new GUIViewStudent(searchContents);
                    searchBox.setText("");
                } catch (IOException ex) {
                    JOptionPane.showMessageDialog(null, "An error occured. Error Code: 4000X10", "Oops", JOptionPane.ERROR_MESSAGE);
                } catch (ClassNotFoundException ex) {
                    JOptionPane.showMessageDialog(null, "An error occured. Error Code: 9999", "Oops", JOptionPane.ERROR_MESSAGE);
                }
            }
            if(objectType.equals("Modules")){
                GUIViewModule viewModulePage = new GUIViewModule();
            }
            if(objectType.equals("Results")){
                GUIViewResult viewResultPage = new GUIViewResult();
            }
            if(objectType.equals("Researches")){
                GUIViewReseach viewResearchPage = new GUIViewReseach();
            }
            if(objectType.equals("Tutors")){
                GUIViewTutor viewTutorPage = new GUIViewTutor();
            }
            if(objectType.equals("Academics")){
                GUIViewAcademic viewAcademicPage = new GUIViewAcademic();
            }

        }

    }
}
