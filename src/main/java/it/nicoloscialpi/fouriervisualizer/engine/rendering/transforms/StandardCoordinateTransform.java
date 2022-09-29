/*
 * Copyright (c) Nicolò Scialpi 2022. Contact info@nicoloscialpi.it . Free to use and share. Do not sell this software.
 */

package it.nicoloscialpi.fouriervisualizer.engine.rendering.transforms;

import it.nicoloscialpi.fouriervisualizer.engine.math.Vector2;
import it.nicoloscialpi.fouriervisualizer.engine.math.VectorMath;
import it.nicoloscialpi.fouriervisualizer.engine.rendering.elements.Camera;
import it.nicoloscialpi.fouriervisualizer.engine.rendering.elements.ScreenProperties;
import it.nicoloscialpi.fouriervisualizer.engine.rendering.interfaces.CoordinateTransform;

public class StandardCoordinateTransform implements CoordinateTransform {

    @Override
    public Vector2 worldToScreen(Vector2 worldPoint, ScreenProperties screenProperties, Camera camera) {
        double scale = screenProperties.height() / camera.getHeight();
        Vector2 center = camera.getPosition();
        Vector2 topLeftCorner = VectorMath.add(center, camera.getCornerOffset(Camera.Corner.TOP_LEFT));
        Vector2 halfTransform = VectorMath.sub(worldPoint, topLeftCorner);
        halfTransform.setY(-halfTransform.getY());
        return halfTransform.scale(scale);
    }

    @Override
    public Vector2 screenToWorld(Vector2 screenPoint, ScreenProperties screenProperties, Camera camera) {
        double scale = screenProperties.height() / camera.getHeight();
        Vector2 center = camera.getPosition();
        Vector2 topLeftCorner = VectorMath.add(center, camera.getCornerOffset(Camera.Corner.TOP_LEFT));
        screenPoint.setY(-screenPoint.getY());
        return VectorMath.add(screenPoint.scale(1 / scale), topLeftCorner);
    }
}
