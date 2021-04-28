package opengl.util;

import static org.lwjgl.opengl.GL11.GL_INVALID_ENUM;
import static org.lwjgl.opengl.GL11.GL_INVALID_OPERATION;
import static org.lwjgl.opengl.GL11.GL_INVALID_VALUE;
import static org.lwjgl.opengl.GL11.GL_NO_ERROR;
import static org.lwjgl.opengl.GL11.GL_OUT_OF_MEMORY;
import static org.lwjgl.opengl.GL11.GL_STACK_UNDERFLOW;
import static org.lwjgl.opengl.GL11.glGetError;

public class ErrorCatcher {
    public static void catchErrors() {
        int errorCode = 0;
        while ((errorCode = glGetError()) != GL_NO_ERROR) {
            switch(errorCode) {
                case(GL_INVALID_ENUM):
                    System.err.println("Enumeration parameter not allowed");
                    break;
                case(GL_INVALID_VALUE):
                    System.err.println("Value parameter not allowed");
                    break;
                case(GL_INVALID_OPERATION):
                    System.err.println("Set of state for a command is not legal");
                    break;
                case(GL_STACK_UNDERFLOW):
                    System.err.println("Stack underflow");
                    break;
                case(GL_OUT_OF_MEMORY):
                    System.err.println("Out of memory");
                    break;
            }
        }
    }
}
