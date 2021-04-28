package swing.panels;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

public class ButtonPanel extends JPanel implements ActionListener {
    private OptionPanel panel;
    public ButtonPanel(OptionPanel panel) {
        this.panel = panel;
        JButton apply1 = new JButton("Apply matrix 1");
        apply1.setActionCommand("mat1");
        apply1.addActionListener(this);
        add(apply1);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals("mat1")) {
            panel.applyMatrix1();
        }
    }
}
