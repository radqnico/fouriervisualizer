package it.nicoloscialpi.fouriervisualizer.sketches;

import it.nicoloscialpi.fouriervisualizer.engine.input.InputSystem;
import it.nicoloscialpi.fouriervisualizer.engine.input.KeyboardInputMapping;
import it.nicoloscialpi.fouriervisualizer.engine.input.MouseInputMapping;
import it.nicoloscialpi.fouriervisualizer.engine.math.Vector2;
import it.nicoloscialpi.fouriervisualizer.engine.rendering.elements.Camera;
import it.nicoloscialpi.fouriervisualizer.engine.rendering.elements.Rendering;
import it.nicoloscialpi.fouriervisualizer.engine.rendering.elements.ScreenProperties;
import it.nicoloscialpi.fouriervisualizer.engine.rendering.elements.cameras.WASDWheelCamera;
import it.nicoloscialpi.fouriervisualizer.engine.rendering.interfaces.CoordinateTransform;
import it.nicoloscialpi.fouriervisualizer.engine.rendering.transforms.StandardCoordinateTransform;
import it.nicoloscialpi.fouriervisualizer.fourier.simpleobjects.Circle;
import it.nicoloscialpi.fouriervisualizer.fourier.simpleobjects.PolarLine;
import processing.core.PApplet;
import processing.event.KeyEvent;
import processing.event.MouseEvent;

public class LinesTest extends PApplet {

    private InputSystem inputSystem;
    private Rendering rendering;

    private PolarLine polarLine;
    private Circle bigCircle;
    private Circle endCircle;

    @Override
    public void settings() {
        size(1280, 720);
    }

    @Override
    public void setup() {
        KeyboardInputMapping keyboardInputMapping = KeyboardInputMapping.getDefault();
        MouseInputMapping mouseInputMapping = MouseInputMapping.getDefault();
        inputSystem = new InputSystem(keyboardInputMapping, mouseInputMapping, null);

        ScreenProperties screenProperties = new ScreenProperties(width, height);
        Camera camera = new WASDWheelCamera(new Vector2(0, 0), 10, screenProperties.getRatio());

        inputSystem.getInputPropagator().registerNewListener(camera);

        CoordinateTransform coordinateTransform = new StandardCoordinateTransform();

        rendering = new Rendering(this, camera, screenProperties, coordinateTransform);

        polarLine = new PolarLine(new Vector2(0, 0), 1, 2, 0, 255, 255, 255);
        bigCircle = new Circle(polarLine.getLocation(), polarLine.getLength(), 100, 100, 100, false);
        endCircle = new Circle(polarLine.getLocation(), polarLine.getLength() / 100, 255, 255, 255, true);

        rendering.registerNewRenderingObject(bigCircle, 0);
        rendering.registerNewRenderingObject(endCircle, 2);
        rendering.registerNewRenderingObject(polarLine, 1);
    }

    @Override
    public void draw() {
        background(0);
        polarLine.setPhase(frameCount / 60.f);
        endCircle.setLocation(polarLine.getEndLocation());
        inputSystem.getInputPropagator().propagateInput();
        inputSystem.getMouseInputManager().cleanWheelInput();
        rendering.renderAll();
    }

    @Override
    public void keyPressed(KeyEvent event) {
        inputSystem.getKeyboardInputManager().keyPressed(event.getKeyCode());
    }

    @Override
    public void keyReleased(KeyEvent event) {
        inputSystem.getKeyboardInputManager().keyReleased(event.getKeyCode());
    }

    @Override
    public void mousePressed(MouseEvent event) {
        inputSystem.getMouseInputManager().mousePressed(event.getButton());
    }

    @Override
    public void mouseReleased(MouseEvent event) {
        inputSystem.getMouseInputManager().mouseReleased(event.getButton());
    }

    @Override
    public void mouseWheel(MouseEvent event) {
        inputSystem.getMouseInputManager().wheelMoved(event.getCount());
    }


}
