package opengl.objects;

import static org.lwjgl.glfw.GLFW.GLFW_KEY_A;
import static org.lwjgl.glfw.GLFW.GLFW_KEY_D;
import static org.lwjgl.glfw.GLFW.GLFW_KEY_LEFT_SHIFT;
import static org.lwjgl.glfw.GLFW.GLFW_KEY_S;
import static org.lwjgl.glfw.GLFW.GLFW_KEY_SPACE;
import static org.lwjgl.glfw.GLFW.GLFW_KEY_W;

import opengl.logic.Input;
import lombok.Getter;
import org.joml.Matrix4f;
import org.joml.Vector3f;
import opengl.util.MathUtils;

@Getter
public class Camera {
    private Vector3f position, rotation;
    private double oldMouseX = 0, oldMouseY = 0;
    private static final float cameraSpeed = 3f;

    public Camera(Vector3f position, Vector3f rotation) {
        this.position = position;
        this.rotation = rotation;
    }

    public Matrix4f getViewMatrix() {
        return new Matrix4f().rotateXYZ(rotation.x, rotation.y, rotation.z).translate(-position.x, -position.y, -position.z);
    }

    public void updateMouse() {
        oldMouseX = Input.getMouseX();
        oldMouseY = Input.getMouseY();
    }

    public void tick(double delta) {
        float deltaF = (float) delta;
        double newMouseX = Input.getMouseX();
        double newMouseY = Input.getMouseY();

        if (Input.isHeldDown(GLFW_KEY_W)) position.add((float)(cameraSpeed*deltaF*Math.sin(rotation.y)),0, -(float)(cameraSpeed*deltaF*Math.cos(rotation.y)));
        if (Input.isHeldDown(GLFW_KEY_S)) position.add(-(float)(cameraSpeed*deltaF*Math.sin(rotation.y)),0, (float)(cameraSpeed*deltaF*Math.cos(rotation.y)));
        if (Input.isHeldDown(GLFW_KEY_D)) position.add((float)(cameraSpeed*deltaF*Math.cos(rotation.y)),0, (float)(cameraSpeed*deltaF*Math.sin(rotation.y)));
        if (Input.isHeldDown(GLFW_KEY_A)) position.add(-(float)(cameraSpeed*deltaF*Math.cos(rotation.y)),0, -(float)(cameraSpeed*deltaF*Math.sin(rotation.y)));
        if (Input.isHeldDown(GLFW_KEY_SPACE)) position.add(0,cameraSpeed*deltaF,0);
        if (Input.isHeldDown(GLFW_KEY_LEFT_SHIFT)) position.add(0,-cameraSpeed*deltaF,0);

        double dx = newMouseX - oldMouseX;
        double dy = newMouseY - oldMouseY;

        rotation.add((float)(dy*delta), (float) (dx*delta),0);
        rotation.set(MathUtils.clamp(-Math.PI/2, rotation.x, Math.PI/2), rotation.y, rotation.z);

        oldMouseX = newMouseX;
        oldMouseY = newMouseY;
    }
}
