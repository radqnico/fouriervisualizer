/*
 * Copyright (c) Nicolò Scialpi 2022. Contact info@nicoloscialpi.it . Free to use and share. Do not sell this software.
 */

package it.nicoloscialpi.fouriervisualizer.fourier.elements;

import it.nicoloscialpi.fouriervisualizer.engine.math.Vector2;
import it.nicoloscialpi.fouriervisualizer.engine.rendering.elements.Camera;
import it.nicoloscialpi.fouriervisualizer.engine.rendering.elements.Rendering;
import it.nicoloscialpi.fouriervisualizer.engine.rendering.elements.ScreenProperties;
import it.nicoloscialpi.fouriervisualizer.engine.rendering.interfaces.CoordinateTransform;
import it.nicoloscialpi.fouriervisualizer.engine.rendering.interfaces.RenderingObject;
import it.nicoloscialpi.fouriervisualizer.fourier.math.Complex;
import it.nicoloscialpi.fouriervisualizer.fourier.signals.Signal;
import processing.core.PApplet;

import java.util.ArrayList;

public class SignalVisualizer extends RenderingObject {

    private Signal signal;
    private int[] color;

    public SignalVisualizer(Signal signal, int r, int g, int b) {
        super();
        this.signal = signal;
        this.color = new int[]{r, g, b};
    }

    @Override
    public void render(Rendering rendering) {
        ArrayList<Complex> samples = signal.getSamples();
        if (samples.size() == 0) {
            return;
        }
        PApplet applet = rendering.getApplet();
        Camera activeCamera = rendering.getActiveCamera();
        ScreenProperties screenProperties = rendering.getScreenProperties();
        CoordinateTransform coordinateTransform = rendering.getCoordinateTransform();

        applet.push();
        applet.stroke(color[0], color[1], color[2]);
        applet.strokeWeight(0.5f);
        applet.noFill();
        applet.beginShape();
        for (Complex complex : samples) {
            Vector2 point = new Vector2(complex.getReal(), complex.getImaginary());
            Vector2 worldToScreen = coordinateTransform.worldToScreen(point, screenProperties, activeCamera);
            applet.vertex(worldToScreen.getXf(), worldToScreen.getYf());
        }
        applet.endShape();
        applet.pop();
    }

    public Signal getSignal() {
        return signal;
    }

    public void setSignal(Signal signal) {
        this.signal = signal;
    }
}
