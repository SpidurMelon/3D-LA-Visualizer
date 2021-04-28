package opengl.util;

public interface Tickable {
    Object init();

    /**
     *
     * @param delta The time passed since last call
     * @return Whether the window should close or not
     */
    boolean tick(double delta);
    void destroy();
}
