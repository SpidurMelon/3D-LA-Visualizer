package opengl.logic;

import main.Main;
import opengl.graphics.RenderContainer;
import opengl.graphics.Renderer;
import opengl.graphics.Window;
import opengl.objects.Graph;
import opengl.util.Tickable;

import static org.lwjgl.glfw.GLFW.*;

public class LogicController implements Tickable, RenderContainer {
    private Renderer renderer;
    private Window window;

    public LogicController(Window window) {
        this.renderer = window.getRenderer();
        this.window = window;
    }

    @Override
    public LogicController init() {
        renderer.addContainer(Main.graph);
        return this;
    }

    @Override
    public boolean tick(double delta) {
        float deltaF = (float) delta;

        boolean isClicking = Input.isMouseButtonHeldDown(GLFW_MOUSE_BUTTON_1);
        if (isClicking) renderer.getCamera().tick(delta);
        renderer.getCamera().updateMouse();

        glfwSetInputMode(window.getWindow(), GLFW_CURSOR, isClicking ? GLFW_CURSOR_DISABLED : GLFW_CURSOR_NORMAL);

        return false;
    }

    @Override
    public void destroy() {

    }
}
