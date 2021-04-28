package opengl.objects;


import opengl.graphics.RenderContainer;
import java.awt.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import opengl.util.Tickable;
import org.joml.Vector3f;

@AllArgsConstructor
@Setter
@Getter
public class Graph implements RenderContainer, Tickable {
    private float size = 10;
    private float minX = -10, minY = -10, minZ = -10, maxX = 10, maxY = 10, maxZ = 10;
    private float slicesPerQuadrant = 10;
    @Getter
    private Vector3f vector = new Vector3f();
    boolean hasToUpdate = false;
    public Graph() {

    }
    public void update() {
        hasToUpdate = true;
    }
    private void reInit() {
        for (GameObject go:renderables) {
            go.destroy();
        }
        renderables.clear();
        init();
    }

    @Override
    public Graph init() {
        for (float x = minX; x <= maxX; x+=(maxX-minX)/(slicesPerQuadrant*2)) {
            if (x != 0) {
                renderables.add(new Line(x, minY, 0, x, maxY, 0, Color.BLACK));
                renderables.add(new Line(x, 0, minZ, x, 0, maxZ, Color.BLACK));
            }
        }
        for (float y = minY; y <= maxY; y+=(maxY-minY)/(slicesPerQuadrant*2)) {
            if (y != 0) {
                renderables.add(new Line(minX, y, 0, maxX, y, 0, Color.BLACK));
                renderables.add(new Line(0, y, minZ, 0, y, maxZ, Color.BLACK));
            }
        }
        for (float z = minZ; z <= maxZ; z+=(maxZ-minZ)/(slicesPerQuadrant*2)) {
            if (z != 0) {
                renderables.add(new Line(minX, 0, z, maxX, 0, z, Color.BLACK));
                renderables.add(new Line(0, minY, z, 0, maxY, z, Color.BLACK));
            }
        }
        renderables.add(new Line(minX, 0, 0, maxX, 0, 0, Color.RED));
        renderables.add(new Line(0, minY, 0, 0, maxY, 0, Color.GREEN));
        renderables.add(new Line(0, 0, minZ, 0, 0, maxZ, Color.BLUE));


        renderables.add(new Line(0,0,0, vector.x, vector.y, vector.z, Color.CYAN));
        GameObject object = new GameObject(new Vector3f(vector.x, vector.y, vector.z), new Cube(Color.CYAN)).init();
        object.setScale(new Vector3f(0.3f));
        renderables.add(object);
        return this;
    }

    @Override
    public boolean tick(double delta) {
        if (hasToUpdate) {
            reInit();
            hasToUpdate = false;
        }
        return false;
    }

    @Override
    public void destroy() {
        for (GameObject go:renderables) {
            go.destroy();
        }
    }
}
