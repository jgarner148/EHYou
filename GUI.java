import javax.swing.*;
import java.awt.*;

public class GUI {
    public static <Jframe> void main(String[] args) {
        JFrame frame = new JFrame();
        JPanel panel = new JPanel();
        JButton addButton = new JButton("Add new");
        addButton.setPreferredSize(new Dimension(40, 40));

        JButton searchButton = new JButton("Search");
        searchButton.setPreferredSize(new Dimension(40, 40));






        panel.setBorder(BorderFactory.createEmptyBorder(30,30,10,30));
        panel.setLayout(new GridLayout(0,1));

        panel.add(addButton);
        panel.add(searchButton);

        frame.setPreferredSize(new Dimension(1280, 720));
        frame.add(panel, BorderLayout.CENTER);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setTitle("EHYOU");
        frame.pack();
        frame.setVisible(true);
    }

}
