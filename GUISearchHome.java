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
    private final JFrame mainFrame;
    /**
     * The page title label displaying "Search"
     */
    private final JLabel titleLabel = new JLabel("Search");
    /**
     * Label for the subtitle
     */
    private final JLabel subtitleLabel = new JLabel("Search using the ID code of the entity or a Student's full name");
    /**
     * The button that allows the user to go back
     */
    private final JButton goBack = factory.makeFlatButton("BACK");
    /**
     * The button that is pressed to start the search
     */
    private final JButton goSearch = factory.makeFlatButton("Search");
    /**
     * The text field that the user inputs the code they want to search for into
     */
    private final JTextField searchBox = new JTextField();

    /**
     * The constructor for the GUISearchHome class that is called when the program wants to draw the Search home page
     * @param mainFrame The frame that all the items will be added to
     */
    public GUISearchHome(JFrame mainFrame){
        this.mainFrame = mainFrame;
        this.mainFrame.setTitle("EHYou - Search");

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

        //Adding all the elements to the frame
        mainFrame.add(titleLabel);
        mainFrame.add(goBack);
        mainFrame.add(subtitleLabel);
        mainFrame.add(searchBox);
        mainFrame.add(goSearch);

    }

    /**
     * Invoked when an action occurs.
     *
     * @param e the event to be processed
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        //Method for when the go back button is pressed that returns the user to the create home page
        if (e.getSource()==goBack){
            mainFrame.getContentPane().removeAll();
            mainFrame.repaint();
            GUIWelcomePage welcomepage = new GUIWelcomePage(this.mainFrame);
        }
        //Mwthod for when the search button is clicked that searches for an object using the information inputted by the user
        if(e.getSource()==goSearch){
            String searchContents = searchBox.getText();
            String objectType = "";
            //Runs the search.code method to check if the user has inputted an object code
            try {
                objectType = search.code(searchContents);
            } catch (IOException ex) {
                JOptionPane.showMessageDialog(null, "An error occurred. Error Code: 4050X10", "Oops", JOptionPane.ERROR_MESSAGE);
            }
            //Runs the search.studentname method if the search.code method returns nothing. This will check if the user has entered the full name of a student
            if(objectType.equals("none")){
                String studentCode = "";
                try {
                    studentCode = search.studentName(searchContents);
                } catch (IOException ex) {
                    JOptionPane.showMessageDialog(null, "An error occurred. Error Code: 4050X10", "Oops", JOptionPane.ERROR_MESSAGE);
                    ex.printStackTrace();
                    studentCode = " ";
                } catch (ClassNotFoundException ex) {
                    JOptionPane.showMessageDialog(null, "Invalid input. Error Code: 500", "Oops", JOptionPane.ERROR_MESSAGE);
                    studentCode = " ";
                }
                //If no search methods return results the user is told that the object does not exist
                if(studentCode.equals("")){
                    JOptionPane.showMessageDialog(null, "Invalid Input. Error code: 500","Oops", JOptionPane.ERROR_MESSAGE);
                }
                //Opens the view Student GUI
                else{
                    try {
                        GUIViewStudent viewstudentpage = new GUIViewStudent(studentCode);
                        searchBox.setText("");
                    } catch (IOException ex) {
                        JOptionPane.showMessageDialog(null, "An error occurred. Error Code: 4000X10", "Oops", JOptionPane.ERROR_MESSAGE);
                        ex.printStackTrace();
                    } catch (ClassNotFoundException ex) {
                        JOptionPane.showMessageDialog(null, "Invalid Input Error code: 500","Oops", JOptionPane.ERROR_MESSAGE);
                    }
                }

            }
            //Opens the view Student GUI if the user has inputted a student code
            if(objectType.equals("Students")){
                try {
                    GUIViewStudent viewStudentPage = new GUIViewStudent(searchContents);
                    searchBox.setText("");
                } catch (IOException ex) {
                    JOptionPane.showMessageDialog(null, "An error occurred. Error Code: 4000X10", "Oops", JOptionPane.ERROR_MESSAGE);
                    ex.printStackTrace();
                } catch (ClassNotFoundException ex) {
                    JOptionPane.showMessageDialog(null, "An error occurred. Error Code: 9999", "Oops", JOptionPane.ERROR_MESSAGE);
                }
            }
            //Opens the view module GUI if the user has inputted a module code
            if(objectType.equals("Modules")){
                try {
                    GUIViewModule viewModulePage = new GUIViewModule(searchContents);
                    searchBox.setText("");
                } catch (IOException | ClassNotFoundException ex) {
                    JOptionPane.showMessageDialog(null, "An error occurred. Error Code: 9999", "Oops", JOptionPane.ERROR_MESSAGE);
                }

            }
            //Opens the view results GUI is the user has inputted a results code
            if(objectType.equals("Results")){
                try {
                    GUIViewResult viewResultPage = new GUIViewResult(searchContents);
                    searchBox.setText("");
                } catch (IOException | ClassNotFoundException ex) {
                    JOptionPane.showMessageDialog(null, "An error occurred. Error Code: 9999", "Oops", JOptionPane.ERROR_MESSAGE);
                }
            }
            //Opens the view research GUI is the user has inputted a research code
            if(objectType.equals("Researches")){
                try {
                    GUIViewReseach viewResearchPage = new GUIViewReseach(searchContents);
                    searchBox.setText("");
                } catch (IOException | ClassNotFoundException ex) {
                    JOptionPane.showMessageDialog(null, "An error occurred. Error Code: 9999", "Oops", JOptionPane.ERROR_MESSAGE);
                    ex.printStackTrace();
                }
            }
            //Opens the view tutor GUI is the user has inputted a tutor code
            if(objectType.equals("Tutors")){
                try {
                    GUIViewTutor viewTutorPage = new GUIViewTutor(searchContents);
                    searchBox.setText("");
                } catch (IOException | ClassNotFoundException ex) {
                    JOptionPane.showMessageDialog(null, "An error occurred. Error Code: 9999", "Oops", JOptionPane.ERROR_MESSAGE);
                }
            }
            //Opens the view academic GUI is the user has inputted an academic code
            if(objectType.equals("Academics")){
                try {
                    GUIViewAcademic viewAcademicPage = new GUIViewAcademic(searchContents);
                    searchBox.setText("");
                } catch (IOException | ClassNotFoundException ex) {
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(null, "An error occurred. Error Code: 9999", "Oops", JOptionPane.ERROR_MESSAGE);
                }
            }
        }
    }
}
