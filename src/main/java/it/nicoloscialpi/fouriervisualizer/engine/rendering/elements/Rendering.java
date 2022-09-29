package it.nicoloscialpi.fouriervisualizer.engine.rendering.elements;

import it.nicoloscialpi.fouriervisualizer.engine.rendering.interfaces.CoordinateTransform;
import it.nicoloscialpi.fouriervisualizer.engine.rendering.interfaces.RenderingObject;
import processing.core.PApplet;

import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;

public class Rendering {

    private final PApplet pApplet;
    private final HashMap<RenderingObject, Integer> renderingObjectsPriorities;
    private Camera activeCamera;
    private ScreenProperties screenProperties;
    private CoordinateTransform coordinateTransform;

    public Rendering(PApplet pApplet, Camera activeCamera, ScreenProperties screenProperties, CoordinateTransform coordinateTransform) {
        this.pApplet = pApplet;
        this.activeCamera = activeCamera;
        this.screenProperties = screenProperties;
        this.coordinateTransform = coordinateTransform;
        this.renderingObjectsPriorities = new HashMap<>();
    }

    public void renderAll() {
        renderingObjectsPriorities.entrySet().stream()
                .sorted(Comparator.comparingInt(Map.Entry::getValue))
                .forEach(
                        renderingObjectIntegerEntry -> renderingObjectIntegerEntry.getKey().render(this)
                );
    }

    public void registerNewRenderingObject(RenderingObject renderingObject, int priority) {
        renderingObjectsPriorities.put(renderingObject, priority);
    }

    public void removeRenderingObject(RenderingObject renderingObject) {
        renderingObjectsPriorities.remove(renderingObject);
    }

    public PApplet getApplet() {
        return pApplet;
    }

    public Camera getActiveCamera() {
        return activeCamera;
    }

    public void setActiveCamera(Camera activeCamera) {
        this.activeCamera = activeCamera;
    }

    public ScreenProperties getScreenProperties() {
        return screenProperties;
    }

    public void setScreenProperties(ScreenProperties screenProperties) {
        this.screenProperties = screenProperties;
    }

    public CoordinateTransform getCoordinateTransform() {
        return coordinateTransform;
    }

    public void setCoordinateTransform(CoordinateTransform coordinateTransform) {
        this.coordinateTransform = coordinateTransform;
    }

    public HashMap<RenderingObject, Integer> getRenderingObjectsPriorities() {
        return renderingObjectsPriorities;
    }

    public double getScale() {
        return screenProperties.height() / activeCamera.getHeight();
    }
}
