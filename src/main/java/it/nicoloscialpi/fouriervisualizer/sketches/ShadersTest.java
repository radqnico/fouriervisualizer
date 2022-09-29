package it.nicoloscialpi.fouriervisualizer.sketches;

import processing.core.PApplet;
import processing.opengl.PShader;

public class ShadersTest extends PApplet {

    PShader shader;

    @Override
    public void settings() {
        size(1280, 720);
    }

    @Override
    public void setup() {
        frameRate(60);

        shader = loadShader("shaders/");
    }

    @Override
    public void draw() {

    }
}
