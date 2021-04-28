package opengl.graphics;

import java.util.ArrayList;
import opengl.objects.GameObject;

public interface RenderContainer {
    ArrayList<GameObject> renderables = new ArrayList<GameObject>();
    default ArrayList<GameObject> getRenderables() {
        return renderables;
    }
    default void destroy() {
        for (GameObject go:renderables) {
            go.destroy();
        }
    }
}
