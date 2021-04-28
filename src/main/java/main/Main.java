package main;


import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import opengl.graphics.Renderer;
import opengl.logic.LogicController;
import opengl.objects.Graph;
import opengl.util.Clock;
import opengl.graphics.Window;
import org.lwjgl.Version;
import swing.frame.Frame;

public class Main {
    public static Renderer renderer;
    public static Window window;
    public static LogicController logicController;
    public static Graph graph;

    public static void main(String[] args) {
        System.out.println("Hello LWJGL " + Version.getVersion() + "!");

        renderer = new Renderer();
        window = new Window(renderer, args[0]);
        logicController = new LogicController(window);
        graph = new Graph();

        Frame frame = new Frame();
        frame.setVisible(true);

        Clock clock = new Clock();
        clock.add(window);
        clock.add(logicController);
        clock.add(frame);
        clock.add(graph);
        clock.addDestroyListener(renderer::destroy);
        clock.addDestroyListener(frame::dispose);
        clock.start();

        frame.addWindowListener(new WindowListener() {
            @Override
            public void windowOpened(WindowEvent e){}
            @Override
            public void windowClosing(WindowEvent e) {
                clock.stop();
            }
            @Override
            public void windowClosed(WindowEvent e) { }
            @Override
            public void windowIconified(WindowEvent e) { }
            @Override
            public void windowDeiconified(WindowEvent e) { }
            @Override
            public void windowActivated(WindowEvent e) { }
            @Override
            public void windowDeactivated(WindowEvent e) { }
        });
    }


}
