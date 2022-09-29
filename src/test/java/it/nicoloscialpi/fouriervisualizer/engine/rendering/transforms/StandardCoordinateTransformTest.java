package it.nicoloscialpi.fouriervisualizer.engine.rendering.transforms;

import it.nicoloscialpi.fouriervisualizer.engine.input.Action;
import it.nicoloscialpi.fouriervisualizer.engine.math.Vector2;
import it.nicoloscialpi.fouriervisualizer.engine.rendering.elements.Camera;
import it.nicoloscialpi.fouriervisualizer.engine.rendering.elements.ScreenProperties;
import org.junit.jupiter.api.Test;

import java.util.HashSet;

class StandardCoordinateTransformTest {

    @Test
    void worldToScreenTest() {
        ScreenProperties screenProperties = new ScreenProperties(1280, 720);
        Camera camera = new Camera(new Vector2(0, 0), 10, ScreenProperties.Ratio.R_16_9) {
            @Override
            public void input(HashSet<Action> actions) {
            }
        };
        StandardCoordinateTransform standardCoordinateTransform = new StandardCoordinateTransform();
        Vector2 worldToScreen = standardCoordinateTransform.worldToScreen(new Vector2(0, 0), screenProperties, camera);
        System.out.println("World to screen: " + worldToScreen);
    }

    @Test
    void screenToWorldTest() {
        ScreenProperties screenProperties = new ScreenProperties(1920, 1080);
        Camera camera = new Camera(new Vector2(0, 0), 10, ScreenProperties.Ratio.R_16_9) {
            @Override
            public void input(HashSet<Action> actions) {
            }
        };
        StandardCoordinateTransform standardCoordinateTransform = new StandardCoordinateTransform();
        Vector2 worldToScreen = standardCoordinateTransform.screenToWorld(new Vector2(960, -540), screenProperties, camera);
        System.out.println("Screen to world: " + worldToScreen);
    }
}
