package swing.frame;

import javax.swing.*;
import opengl.util.Tickable;
import swing.panels.OptionPanel;

public class Frame extends JFrame implements Tickable {
    private OptionPanel optionPanel = new OptionPanel();
    public Frame() {
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setResizable(false);

        add(optionPanel);
        pack();
    }

    @Override
    public Object init() {
        return null;
    }

    @Override
    public boolean tick(double delta) {
        return optionPanel.tick(delta);
    }

    @Override
    public void destroy() {

    }
}
