package it.nicoloscialpi.fouriervisualizer.fourier.elements;

import it.nicoloscialpi.fouriervisualizer.engine.math.Vector2;
import it.nicoloscialpi.fouriervisualizer.engine.math.VectorMath;
import it.nicoloscialpi.fouriervisualizer.engine.rendering.elements.Camera;
import it.nicoloscialpi.fouriervisualizer.engine.rendering.elements.Rendering;
import it.nicoloscialpi.fouriervisualizer.engine.rendering.elements.ScreenProperties;
import it.nicoloscialpi.fouriervisualizer.engine.rendering.interfaces.CoordinateTransform;
import it.nicoloscialpi.fouriervisualizer.engine.rendering.interfaces.RenderingObject;
import it.nicoloscialpi.fouriervisualizer.fourier.math.Complex;
import processing.core.PApplet;

public class ComplexVisualizer extends RenderingObject {

    private Vector2 centerLocation;
    private Complex complex;
    private float width;
    private int[] lineColor;
    private int[] bigCircleColor;
    private int[] endCircleColor;

    public ComplexVisualizer(Vector2 centerLocation, Complex complex, float width, int[] lineColor, int[] bigCircleColor, int[] endCircleColor) {
        super();
        this.centerLocation = centerLocation;
        this.complex = complex;
        this.width = width;
        this.lineColor = lineColor;
        this.bigCircleColor = bigCircleColor;
        this.endCircleColor = endCircleColor;
    }

    public Vector2 getCenterLocation() {
        return centerLocation;
    }

    public void setCenterLocation(Vector2 centerLocation) {
        this.centerLocation = centerLocation;
    }

    public Complex getComplex() {
        return complex;
    }

    public void setComplex(Complex complex) {
        this.complex = complex;
    }

    public float getWidth() {
        return width;
    }

    public void setWidth(float width) {
        this.width = width;
    }

    public void setLineColor(int[] lineColor) {
        this.lineColor = lineColor;
    }

    public void setBigCircleColor(int[] bigCircleColor) {
        this.bigCircleColor = bigCircleColor;
    }

    public void setEndCircleColor(int[] endCircleColor) {
        this.endCircleColor = endCircleColor;
    }

    @Override
    public void render(Rendering rendering) {
        PApplet applet = rendering.getApplet();
        Camera activeCamera = rendering.getActiveCamera();
        ScreenProperties screenProperties = rendering.getScreenProperties();
        float scale = (float) rendering.getScale();
        CoordinateTransform coordinateTransform = rendering.getCoordinateTransform();
        Vector2 centerScreen = coordinateTransform.worldToScreen(centerLocation, screenProperties, activeCamera);
        Vector2 offset = (new Vector2(Math.cos(complex.getPhase()), Math.sin(complex.getPhase()))).scale(complex.getAmplitude());
        Vector2 end = VectorMath.add(centerLocation, offset);
        Vector2 endScreen = coordinateTransform.worldToScreen(end, screenProperties, activeCamera);

        applet.push();

        applet.strokeWeight(width / 2);
        applet.stroke(bigCircleColor[0], bigCircleColor[1], bigCircleColor[2]);
        applet.noFill();
        applet.circle(centerScreen.getXf(), centerScreen.getYf(), complex.getAmplitude() * scale * 2);

        applet.strokeWeight(width);
        applet.stroke(lineColor[0], lineColor[1], lineColor[2]);
        applet.line(centerScreen.getXf(), centerScreen.getYf(), endScreen.getXf(), endScreen.getYf());

        applet.strokeWeight(width / 5);
        applet.stroke(endCircleColor[0] / 3.f, endCircleColor[1] / 3.f, endCircleColor[2] / 3.f);
        applet.fill(endCircleColor[0], endCircleColor[1], endCircleColor[2]);
        applet.circle(endScreen.getXf(), endScreen.getYf(), (complex.getAmplitude() / 50) * scale);
        applet.pop();
    }
}
