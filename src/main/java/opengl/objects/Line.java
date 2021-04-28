package opengl.objects;

import static org.lwjgl.opengl.GL30.*;

import java.awt.*;
import lombok.Getter;
import org.joml.Vector3f;

public class Line extends GameObject {
    @Getter
    private Vector3f p1, p2;
    public Line(float x1, float y1, float z1, float x2, float y2, float z2, Color color) {
        super(new Mesh(new Vector3f[]{new Vector3f(0,0,0), new Vector3f(x2-x1,y2-y1,z2-z1)},
                new Vector3f[]{new Vector3f(color.getRed(), color.getGreen(), color.getBlue()), new Vector3f(color.getRed(), color.getGreen(), color.getBlue())},
                new int[]{0,1}).init());
        getMesh().setDrawingMode(GL_LINES);
        setPosition(x1,y1,z1);
        p1 = new Vector3f(x1,y1,z1);
        p2 = new Vector3f(x2,y2,z2);
    }
    public boolean equals(Object other) {
        if (!(other instanceof Line)) return false;
        Line that = (Line)other;
        return this.p1.equals(that.p1) && this.p2.equals(that.p2);
    }
}
