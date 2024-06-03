package com.example.egzaminui;

import android.opengl.GLES30;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;

public class Triangle {

    private FloatBuffer vertexBuffer;

    static final int COORDS_PER_VERTEX = 3;

    static float triangleCoords[] = {
            0.0f, 0.622008459f, 0.0f,
            -0.5f, -0.311004243f, 0.0f,
            0.5f, -0.311004243f, 0.0f
    };

    float color[] =  { 0.63671875f, 0.76953125f, 0.22265625f, 1.0f };

    private final int program;

    private final String vertexShaderCode =
            "uniform mat4 uMVPMatrix;" +
                    "attribute vec4 vPosition;" +
                    "void main() {" +
                    "    gl_Position = uMVPMatrix * vPosition;" +
                    "}";

    private final String fragmentShaderCode =
            "precision mediump float;" +
                    "uniform vec4 vColor;" +
                    "void main() {" +
                    "    gl_FragColor = vColor;" +
                    "}";

    private int MVPMatrixHandle;
    private int positionHandle;
    private int colorHandle;

    private final int vertexCount = triangleCoords.length / COORDS_PER_VERTEX;
    private final int vertexStride = COORDS_PER_VERTEX * 4;

    public Triangle() {
        ByteBuffer bb = ByteBuffer.allocateDirect(triangleCoords.length * 4);
        bb.order(ByteOrder.nativeOrder());
        vertexBuffer = bb.asFloatBuffer();
        vertexBuffer.put(triangleCoords);
        vertexBuffer.position(0);

        int vertexShader = OpenGLRender.loadShader(GLES30.GL_VERTEX_SHADER, vertexShaderCode);
        int fragmentShader = OpenGLRender.loadShader(GLES30.GL_FRAGMENT_SHADER, fragmentShaderCode);

        program = GLES30.glCreateProgram();
        GLES30.glAttachShader(program, vertexShader);
        GLES30.glAttachShader(program, fragmentShader);
        GLES30.glLinkProgram(program);
    }

    public void draw(float[] mvpMatrix) {
        GLES30.glUseProgram(program);
        // Get handle to vertex shader's vPosition member
        positionHandle = GLES30.glGetAttribLocation(program, "vPosition");
        // Enable a handle to the triangle vertices
        GLES30.glEnableVertexAttribArray(positionHandle);
        // Prepare the triangle coordinate data
        GLES30.glVertexAttribPointer(positionHandle, COORDS_PER_VERTEX,
                GLES30.GL_FLOAT, false, vertexStride, vertexBuffer);
        // Get handle to fragment shader's vColor member
        colorHandle = GLES30.glGetUniformLocation(program, "vColor");
        // Set color for drawing the triangle
        GLES30.glUniform4fv(colorHandle, 1, color, 0);
        // Get handle to shape's transformation matrix
        MVPMatrixHandle = GLES30.glGetUniformLocation(program, "uMVPMatrix");
        // Apply the projection and view transformation
        GLES30.glUniformMatrix4fv(MVPMatrixHandle, 1, false, mvpMatrix, 0);
        // Draw the triangle
        GLES30.glDrawArrays(GLES30.GL_TRIANGLES, 0, vertexCount);
        // Disable vertex array
        GLES30.glDisableVertexAttribArray(positionHandle);
    }
}
