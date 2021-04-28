package opengl.util;

import org.joml.Matrix4f;

public class MathUtils {
    public static Matrix4f getOrthographicProjectionMatrix(float left, float right, float bottom, float top, float near, float far) {
        Matrix4f result = new Matrix4f();

        result.set(0,0, 2/(right-left));
        result.set(1,1,2/(top-bottom));
        result.set(2,2,-2/(far-near));
        result.set(3,0, -(right+left)/(right-left));
        result.set(3,0, -(top+bottom)/(top-bottom));
        result.set(3,2, -(far+near)/(far-near));

        return result;
    }

    public static Matrix4f getPerspectiveProjectionMatrix(float fov, float aspect, float near, float far) {
        Matrix4f result = new Matrix4f();

        float tanFov = (float)Math.tan(fov/2);
        float range = far-near;

        result.set(0,0, 1/(aspect*tanFov));
        result.set(1,1,tanFov);
        result.set(2,2,-(far+near)/range);
        result.set(2,3, -1);
        result.set(3,2, -(2*far*near)/range);
        result.set(3,3,0);

        return result;
    }

    public static double clamp(double min, double input, double max) {
        return Math.min(max,Math.max(input, min));
    }
}
