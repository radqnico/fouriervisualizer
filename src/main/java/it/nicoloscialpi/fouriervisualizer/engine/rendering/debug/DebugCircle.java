/*
 * Copyright (c) Nicolò Scialpi 2022. Contact info@nicoloscialpi.it . Free to use and share. Do not sell this software.
 */

package it.nicoloscialpi.fouriervisualizer.engine.rendering.debug;

import it.nicoloscialpi.fouriervisualizer.engine.math.Vector2;
import it.nicoloscialpi.fouriervisualizer.engine.rendering.elements.Rendering;
import it.nicoloscialpi.fouriervisualizer.engine.rendering.interfaces.CoordinateTransform;
import it.nicoloscialpi.fouriervisualizer.engine.rendering.interfaces.RenderingObject;
import processing.core.PApplet;

public class DebugCircle extends RenderingObject {

    private final Vector2 location;
    private final float radius;

    public DebugCircle(Vector2 location, float radius) {
        super();
        this.location = location;
        this.radius = radius;
    }

    @Override
    public void render(Rendering rendering) {
        CoordinateTransform coordinateTransform = rendering.getCoordinateTransform();
        Vector2 worldToScreen = coordinateTransform.worldToScreen(location, rendering.getScreenProperties(), rendering.getActiveCamera());
        float scale = (float) rendering.getScale();
        PApplet applet = rendering.getApplet();
        applet.push();
        applet.noStroke();
        applet.fill((int) (Math.random() * 255), (int) (Math.random() * 255), (int) (Math.random() * 255));
        applet.circle(worldToScreen.getXf(), worldToScreen.getYf(), radius * 2 * scale);
        applet.pop();
    }
}
