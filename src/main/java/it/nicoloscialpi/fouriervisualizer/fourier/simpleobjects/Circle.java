package it.nicoloscialpi.fouriervisualizer.fourier.simpleobjects;

import it.nicoloscialpi.fouriervisualizer.engine.math.Vector2;
import it.nicoloscialpi.fouriervisualizer.engine.rendering.elements.Camera;
import it.nicoloscialpi.fouriervisualizer.engine.rendering.elements.Rendering;
import it.nicoloscialpi.fouriervisualizer.engine.rendering.elements.ScreenProperties;
import it.nicoloscialpi.fouriervisualizer.engine.rendering.interfaces.RenderingObject;
import processing.core.PApplet;

public class Circle extends RenderingObject {

    private Vector2 location;
    private float radius;
    private float colorR;
    private float colorG;
    private float colorB;
    private boolean filled;

    public Circle(Vector2 location, float radius, float colorR, float colorG, float colorB, boolean filled) {
        super();
        this.location = location;
        this.radius = radius;
        this.colorR = colorR;
        this.colorG = colorG;
        this.colorB = colorB;
        this.filled = filled;
    }

    @Override
    public void render(Rendering rendering) {
        PApplet applet = rendering.getApplet();
        Camera activeCamera = rendering.getActiveCamera();
        ScreenProperties screenProperties = rendering.getScreenProperties();
        float scale = (float) rendering.getScale();
        Vector2 screenPoint = rendering.getCoordinateTransform().worldToScreen(location, screenProperties, activeCamera);
        applet.push();
        applet.stroke(colorR, colorG, colorB);
        if (filled) {
            applet.fill(colorR, colorG, colorB);
        } else {
            applet.noFill();
        }
        applet.circle(screenPoint.getXf(), screenPoint.getYf(), radius * 2 * scale);
        applet.pop();
    }

    public Vector2 getLocation() {
        return location;
    }

    public void setLocation(Vector2 location) {
        this.location = location;
    }

    public float getRadius() {
        return radius;
    }

    public void setRadius(float radius) {
        this.radius = radius;
    }

    public boolean isFilled() {
        return filled;
    }

    public void setFilled(boolean filled) {
        this.filled = filled;
    }
}
