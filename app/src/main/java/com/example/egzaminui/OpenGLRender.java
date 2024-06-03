package com.example.egzaminui;

import android.opengl.GLES30;

public class OpenGLRender implements GLSurfaceView.Renderer {

    private final float[] MVPMatrix = new float[16];
    private final float[] ProjectionMatrix = new float[16];
    private final float[] ViewMatrix = new float[16];
    private float[] MMatrix = new float[16];
    public volatile float Angle;

    private boolean animationFlag = false;

    public OpenGLRenderer() { }

    @Override
    public void onSurfaceCreated(GL10 gl10, EGLConfig eglConfig) {

    }

    @Override
    public void onDrawFrame(GL10 gl10) {

    }

    @Override
    public void onSurfaceChandeg(GL10 gl10, int width, int height) {

    }

    public static int loadShader(int typer, String shaderCode) {
        int shader = GLES30.glCreateShader(type);
        GLES30.glShaderSource(shader, shaderCode);
        GLES30.glCompileShader(shader);
        return shader;
    }

    public float getAngle() {
        return Angle;
    }

    public void setAngle(float angle) {
        Angle = angle;
    }

    public void setAnimationFlag(boolean animationFlag) {
        this.animationFlag = animationFlag;
    }

    public boolean getAnimationFlag() {
        return animationFlag;
    }
}
