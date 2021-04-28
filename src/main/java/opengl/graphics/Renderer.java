package opengl.graphics;

import java.util.ArrayList;
import lombok.Getter;
import lombok.Setter;
import opengl.objects.Camera;
import opengl.objects.GameObject;
import org.joml.Matrix4f;
import org.joml.Vector3f;
import opengl.util.MathUtils;

import static org.lwjgl.opengl.GL15.*;
import static org.lwjgl.opengl.GL20.*;
import static org.lwjgl.opengl.GL30.*;

public class Renderer {
    private ArrayList<RenderContainer> renderContainers = new ArrayList<RenderContainer>();
    @Getter
    @Setter
    private Shader shader;

    @Getter
    private Camera camera;

    public Renderer(Shader shader) {
        this.shader = shader;
        camera = new Camera(new Vector3f(5,5,5), new Vector3f(0,0,0));
    }

    public Renderer() {
        this(null);
    }

    private Matrix4f perspectiveProjection = MathUtils.getPerspectiveProjectionMatrix((float)Math.toRadians(90), 1, 0.1f, 1000f);
    private Matrix4f orthographicProjection = MathUtils.getOrthographicProjectionMatrix(-10, 10, -10, 10, -10, 10);
    private void renderGameObject(GameObject gameObject) {
        glBindVertexArray(gameObject.getMesh().getVao());
        glEnableVertexAttribArray(0);
        glEnableVertexAttribArray(1);
        glBindBuffer(GL_ELEMENT_ARRAY_BUFFER, gameObject.getMesh().getIbo());
        shader.bind();
        shader.setUniform("view", camera.getViewMatrix());
        shader.setUniform("model", gameObject.getTransform());
        shader.setUniform("projection", perspectiveProjection);
        glDrawElements(gameObject.getMesh().getDrawingMode(), gameObject.getMesh().getIndices().length, GL_UNSIGNED_INT, 0);
        shader.unbind();
        glBindBuffer(GL_ELEMENT_ARRAY_BUFFER, 0);
        glDisableVertexAttribArray(1);
        glDisableVertexAttribArray(0);
        glBindVertexArray(gameObject.getMesh().getVao());
    }

    public void renderAll() {
        for (RenderContainer rc:renderContainers) {
            for (GameObject gameObject : rc.getRenderables()) {
                renderGameObject(gameObject);
            }
        }
    }

    public void addContainer(RenderContainer rc) {
        renderContainers.add(rc);
    }

    public void destroy() {
        shader.destroy();
        for (RenderContainer renderContainer : renderContainers) {
            renderContainer.destroy();
        }
    }
}
