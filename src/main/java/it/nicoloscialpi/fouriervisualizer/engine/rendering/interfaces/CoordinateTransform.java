package it.nicoloscialpi.fouriervisualizer.engine.rendering.interfaces;

import it.nicoloscialpi.fouriervisualizer.engine.math.Vector2;
import it.nicoloscialpi.fouriervisualizer.engine.rendering.elements.Camera;
import it.nicoloscialpi.fouriervisualizer.engine.rendering.elements.ScreenProperties;

public interface CoordinateTransform {

    Vector2 worldToScreen(Vector2 worldPoint, ScreenProperties screenProperties, Camera camera);

    Vector2 screenToWorld(Vector2 screenPoint, ScreenProperties screenProperties, Camera camera);

}
