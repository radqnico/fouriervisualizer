package it.nicoloscialpi.fouriervisualizer.engine.rendering.debug;

import it.nicoloscialpi.fouriervisualizer.engine.math.Vector2;
import it.nicoloscialpi.fouriervisualizer.engine.rendering.elements.Camera;
import it.nicoloscialpi.fouriervisualizer.engine.rendering.elements.Rendering;
import it.nicoloscialpi.fouriervisualizer.engine.rendering.elements.ScreenProperties;
import it.nicoloscialpi.fouriervisualizer.engine.rendering.interfaces.CoordinateTransform;
import it.nicoloscialpi.fouriervisualizer.engine.rendering.interfaces.RenderingObject;
import processing.core.PApplet;

public class DebugLine extends RenderingObject {

    private final Vector2 start;
    private final Vector2 end;

    public DebugLine(Vector2 start, Vector2 end) {
        this.start = start;
        this.end = end;
    }

    @Override
    public void render(Rendering rendering) {
        CoordinateTransform coordinateTransform = rendering.getCoordinateTransform();
        Camera activeCamera = rendering.getActiveCamera();
        ScreenProperties screenProperties = rendering.getScreenProperties();

        Vector2 transStart = coordinateTransform.worldToScreen(start, screenProperties, activeCamera);
        Vector2 transEnd = coordinateTransform.worldToScreen(end, screenProperties, activeCamera);

        PApplet applet = rendering.getApplet();
        applet.push();
        applet.stroke(255);
        applet.line(transStart.getXf(), transStart.getYf(), transEnd.getXf(), transEnd.getYf());
        applet.pop();
    }
}
