import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GUISearchHome implements ActionListener{
    private JFrame mainFrame;
    String[] types = new String[]{"Student", "Module", "Result", "Tutor", "Academic", "Research"};
    JComboBox typeSelect = new JComboBox(types);

    public GUISearchHome(JFrame mainFrame){
        this.mainFrame = mainFrame;
        Dimension maxsize = new Dimension(125,50);
        this.typeSelect.setMaximumSize(maxsize);
        this.typeSelect.setBounds(525, 120,125,50);
        this.typeSelect.addActionListener(this);
        this.mainFrame.add(typeSelect);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }
}
