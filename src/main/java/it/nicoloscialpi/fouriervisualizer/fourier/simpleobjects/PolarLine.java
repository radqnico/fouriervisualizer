/*
 * Copyright (c) Nicolò Scialpi 2022. Contact info@nicoloscialpi.it . Free to use and share. Do not sell this software.
 */

package it.nicoloscialpi.fouriervisualizer.fourier.simpleobjects;

import it.nicoloscialpi.fouriervisualizer.engine.math.Vector2;
import it.nicoloscialpi.fouriervisualizer.engine.math.VectorMath;
import it.nicoloscialpi.fouriervisualizer.engine.rendering.elements.Camera;
import it.nicoloscialpi.fouriervisualizer.engine.rendering.elements.Rendering;
import it.nicoloscialpi.fouriervisualizer.engine.rendering.elements.ScreenProperties;
import it.nicoloscialpi.fouriervisualizer.engine.rendering.interfaces.RenderingObject;
import processing.core.PApplet;

public class PolarLine extends RenderingObject {

    private final Vector2 location;
    private final float colorR;
    private final float colorG;
    private final float colorB;
    private float length;
    private float width;
    private float phase;

    public PolarLine(Vector2 location, float length, float width, float phase, float colorR, float colorG, float colorB) {
        super();
        this.location = location;
        this.length = length;
        this.width = width;
        this.phase = phase;
        this.colorR = colorR;
        this.colorG = colorG;
        this.colorB = colorB;
    }

    @Override
    public void render(Rendering rendering) {
        PApplet applet = rendering.getApplet();
        Camera activeCamera = rendering.getActiveCamera();
        ScreenProperties screenProperties = rendering.getScreenProperties();
        float scale = (float) rendering.getScale();
        Vector2 centerScreen = rendering.getCoordinateTransform().worldToScreen(location, screenProperties, activeCamera);

        Vector2 end = getEndLocation();

        Vector2 endScreen = rendering.getCoordinateTransform().worldToScreen(end, screenProperties, activeCamera);

        applet.push();
        applet.stroke(colorR, colorG, colorB);
        applet.strokeWeight(width);
        applet.line(centerScreen.getXf(), centerScreen.getYf(), endScreen.getXf(), endScreen.getYf());
        applet.pop();
    }

    public Vector2 getLocation() {
        return location;
    }

    public float getLength() {
        return length;
    }

    public void setLength(float length) {
        this.length = length;
    }

    public float getWidth() {
        return width;
    }

    public void setWidth(float width) {
        this.width = width;
    }

    public float getPhase() {
        return phase;
    }

    public void setPhase(float phase) {
        this.phase = phase;
    }

    public Vector2 getEndLocation(){
        Vector2 offset = new Vector2(length * Math.cos(phase), length * Math.sin(phase));
        return VectorMath.add(location, offset);
    }
}
