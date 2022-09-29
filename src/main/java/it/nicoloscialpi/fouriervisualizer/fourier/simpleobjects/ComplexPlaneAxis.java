package it.nicoloscialpi.fouriervisualizer.fourier.simpleobjects;

import it.nicoloscialpi.fouriervisualizer.engine.math.Vector2;
import it.nicoloscialpi.fouriervisualizer.engine.rendering.elements.Camera;
import it.nicoloscialpi.fouriervisualizer.engine.rendering.elements.Rendering;
import it.nicoloscialpi.fouriervisualizer.engine.rendering.elements.ScreenProperties;
import it.nicoloscialpi.fouriervisualizer.engine.rendering.interfaces.RenderingObject;
import processing.core.PApplet;

public class ComplexPlaneAxis extends RenderingObject {

    public ComplexPlaneAxis() {
        super();
    }

    @Override
    public void render(Rendering rendering) {
        PApplet applet = rendering.getApplet();
        Camera activeCamera = rendering.getActiveCamera();
        ScreenProperties screenProperties = rendering.getScreenProperties();
        Vector2 screenPoint = rendering.getCoordinateTransform().worldToScreen(Vector2.zero(), screenProperties, activeCamera);
        applet.push();
        applet.strokeWeight(1);
        applet.stroke(120);

        applet.line(screenPoint.getXf(), 0, screenPoint.getXf(), screenProperties.height());
        applet.line(0, screenPoint.getYf(), screenProperties.width(), screenPoint.getYf());
        applet.textSize(20);
        applet.text("Re", screenProperties.width() - 40, screenPoint.getYf() - 10);
        applet.text("Im", screenPoint.getXf() + 10, 30);
        applet.pop();
    }
}
