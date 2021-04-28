package opengl.logic;

import static org.lwjgl.glfw.GLFW.GLFW_KEY_ESCAPE;
import static org.lwjgl.glfw.GLFW.GLFW_KEY_LAST;
import static org.lwjgl.glfw.GLFW.GLFW_PRESS;
import static org.lwjgl.glfw.GLFW.GLFW_RELEASE;
import static org.lwjgl.glfw.GLFW.glfwSetCursorPosCallback;
import static org.lwjgl.glfw.GLFW.glfwSetKeyCallback;
import static org.lwjgl.glfw.GLFW.glfwSetMouseButtonCallback;
import static org.lwjgl.glfw.GLFW.glfwSetScrollCallback;
import static org.lwjgl.glfw.GLFW.glfwSetWindowShouldClose;

import lombok.Getter;
import org.lwjgl.glfw.GLFWCursorPosCallback;
import org.lwjgl.glfw.GLFWKeyCallback;
import org.lwjgl.glfw.GLFWMouseButtonCallback;
import org.lwjgl.glfw.GLFWScrollCallback;

public class Input {
    private static boolean[] keys = new boolean[GLFW_KEY_LAST];
    private static boolean[] mouseButtons = new boolean[GLFW_KEY_LAST];
    @Getter
    private static double mouseX, mouseY, scrollX, scrollY;

    public Input(long windowID) {
        glfwSetKeyCallback(windowID, new GLFWKeyCallback() {
            @Override
            public void invoke(long window, int key, int scancode, int action, int mods) {
                keys[key] = (action != GLFW_RELEASE);
                if (key == GLFW_KEY_ESCAPE && action == GLFW_RELEASE) {
                    glfwSetWindowShouldClose(window, true);
                }
            }
        });

        glfwSetCursorPosCallback(windowID, new GLFWCursorPosCallback() {
            @Override
            public void invoke(long window, double x, double y) {
                mouseX = x;
                mouseY = y;
            }
        });

        glfwSetScrollCallback(windowID, new GLFWScrollCallback() {
            @Override
            public void invoke(long window, double x, double y) {
                scrollX += x;
                scrollY += y;
            }
        });

        glfwSetMouseButtonCallback(windowID, new GLFWMouseButtonCallback() {
            @Override
            public void invoke(long window, int button, int action, int mods) {
                mouseButtons[button] = (action == GLFW_PRESS);
            }
        });
    }

    public static boolean isMouseButtonHeldDown(int button) {
        return mouseButtons[button];
    }

    public static boolean isHeldDown(int key) {
        return keys[key];
    }
}
