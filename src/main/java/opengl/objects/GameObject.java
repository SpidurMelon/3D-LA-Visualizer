package opengl.objects;

import lombok.Getter;
import lombok.Setter;
import org.joml.Matrix4f;
import org.joml.Vector3f;
import opengl.util.Tickable;

@Getter
@Setter
public class GameObject implements Tickable {
    private Vector3f position, rotation, scale;
    private Mesh mesh;

    public GameObject(Vector3f position, Vector3f rotation, Vector3f scale, Mesh mesh) {
        this.position = position;
        this.rotation = rotation;
        this.scale = scale;
        this.mesh = mesh;
    }

    public GameObject(Vector3f position, Mesh mesh) {
        this(position, new Vector3f(0, 0, 0), new Vector3f(1, 1, 1), mesh);
    }

    public GameObject(Mesh mesh) {
        this(new Vector3f(0,0,0), new Vector3f(0, 0, 0), new Vector3f(1, 1, 1), mesh);
    }

    public Matrix4f getTransform() {
        Matrix4f result = new Matrix4f();
        result.setTranslation(position);
        result.rotateAffineXYZ(rotation.x, rotation.y, rotation.z);
        result.scale(scale);
        return result;
    }

    public void setPosition(float x, float y, float z) {
        position.set(x,y,z);
    }

    @Override
    public GameObject init() {
        mesh.init();
        return this;
    }

    @Override
    public boolean tick(double delta) {
        return false;
    }

    @Override
    public void destroy() {
        mesh.destroy();
    }
}
