package opengl.objects;

import java.nio.FloatBuffer;
import java.nio.IntBuffer;
import lombok.Getter;
import lombok.Setter;
import org.joml.Vector3f;
import static org.lwjgl.opengl.GL15.*;
import static org.lwjgl.opengl.GL20.*;
import static org.lwjgl.opengl.GL30.*;

import org.lwjgl.system.MemoryUtil;

@Getter
public class Mesh {
    private Vector3f[] vertices, colors;
    private int[] indices;
    private int vao, pbo, ibo, cbo;
    @Setter
    private int drawingMode = GL_TRIANGLES;

    public Mesh(Vector3f[] vertices, Vector3f[] colors, int[] indices) {
        this.vertices = vertices;
        this.colors = colors;
        this.indices = indices;
    }

    public Mesh init() {
        vao = glGenVertexArrays();
        glBindVertexArray(vao);

        FloatBuffer positionBuffer = MemoryUtil.memAllocFloat(vertices.length * 3);
        float[] positionArray = new float[vertices.length * 3];
        for (int i = 0; i < vertices.length; i++) {
            positionArray[i*3]   = vertices[i].x;
            positionArray[i*3+1] = vertices[i].y;
            positionArray[i*3+2] = vertices[i].z;
        }
        positionBuffer.put(positionArray).flip();
        pbo = storeData(positionBuffer, 0, 3);

        FloatBuffer colorBuffer = MemoryUtil.memAllocFloat(colors.length * 3);
        float[] colorArray = new float[colors.length * 3];
        for (int i = 0; i < colors.length; i++) {
            colorArray[i*3]   = colors[i].x;
            colorArray[i*3+1] = colors[i].y;
            colorArray[i*3+2] = colors[i].z;
        }
        colorBuffer.put(colorArray).flip();
        cbo = storeData(colorBuffer, 1, 3);

        IntBuffer indicesBuffer = MemoryUtil.memAllocInt(indices.length);
        indicesBuffer.put(indices).flip();
        ibo = glGenBuffers();
        glBindBuffer(GL_ELEMENT_ARRAY_BUFFER, ibo);
        glBufferData(GL_ELEMENT_ARRAY_BUFFER, indicesBuffer, GL_STATIC_DRAW);
        glBindBuffer(GL_ELEMENT_ARRAY_BUFFER, 0);
        return this;
    }

    public int storeData(FloatBuffer buffer, int index, int size) {
        int bufferID = glGenBuffers();
        glBindBuffer(GL_ARRAY_BUFFER, bufferID);
        glBufferData(GL_ARRAY_BUFFER, buffer, GL_STATIC_DRAW);
        glVertexAttribPointer(index, size, GL_FLOAT, false, 0, 0);
        glBindBuffer(GL_ARRAY_BUFFER, 0);
        return bufferID;
    }

    public void destroy() {
        glDeleteBuffers(vao);
        glDeleteBuffers(pbo);
        glDeleteBuffers(ibo);

        glDeleteVertexArrays(vao);
    }
}
