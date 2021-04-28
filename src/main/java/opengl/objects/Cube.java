package opengl.objects;

import java.awt.*;
import org.joml.Vector3f;

public class Cube extends Mesh {
    public Cube(Color color) {
        super(new Vector3f[]{
                new Vector3f(-0.5f, -0.5f, -0.5f),
                new Vector3f(0.5f, -0.5f, -0.5f),
                new Vector3f(0.5f, 0.5f, -0.5f),
                new Vector3f(-0.5f, 0.5f, -0.5f),

                new Vector3f(-0.5f, -0.5f, 0.5f),
                new Vector3f(0.5f, -0.5f, 0.5f),
                new Vector3f(0.5f, 0.5f, 0.5f),
                new Vector3f(-0.5f, 0.5f, 0.5f),
        }, new Vector3f[]{
                new Vector3f(color.getRGBColorComponents(null)),
                new Vector3f(color.getRGBColorComponents(null)),
                new Vector3f(color.getRGBColorComponents(null)),
                new Vector3f(color.getRGBColorComponents(null)),

                new Vector3f(color.getRGBColorComponents(null)),
                new Vector3f(color.getRGBColorComponents(null)),
                new Vector3f(color.getRGBColorComponents(null)),
                new Vector3f(color.getRGBColorComponents(null))
        }, new int[]{
                0, 1, 3,
                1, 2, 3,

                4, 5, 7,
                5, 6, 7,

                0, 4, 3,
                4, 3, 7,

                1, 2, 5,
                2, 6, 5,

                0, 1, 4,
                1, 4, 5,

                3, 7, 2,
                7, 6, 2
        });
    }
}
