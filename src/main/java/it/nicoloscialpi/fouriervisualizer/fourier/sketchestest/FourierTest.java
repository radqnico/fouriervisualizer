/*
 * Copyright (c) Nicolò Scialpi 2022. Contact info@nicoloscialpi.it . Free to use and share. Do not sell this software.
 */

package it.nicoloscialpi.fouriervisualizer.fourier.sketchestest;

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
import it.nicoloscialpi.fouriervisualizer.fourier.signals.Signal;
import it.nicoloscialpi.fouriervisualizer.fourier.elements.SignalVisualizer;
import it.nicoloscialpi.fouriervisualizer.fourier.elements.TransformElementsAnimator;
import it.nicoloscialpi.fouriervisualizer.fourier.signals.SignalDistortedCircle;
import it.nicoloscialpi.fouriervisualizer.fourier.transform.FourierTransform;
import it.nicoloscialpi.fouriervisualizer.fourier.transform.FourierTransformRendererManager;
import it.nicoloscialpi.fouriervisualizer.fourier.transform.MyFourierTransform;
import processing.core.PApplet;
import processing.core.PFont;
import processing.event.KeyEvent;
import processing.event.MouseEvent;

public class FourierTest extends PApplet {

    private InputSystem inputSystem;
    private Rendering rendering;

    private FourierTransformRendererManager fourierTransformRendererManager;

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

        FourierTransform fourierTransform = new MyFourierTransform();
        Signal signal = new SignalDistortedCircle(2, 400).getSignal();
        fourierTransformRendererManager = new FourierTransformRendererManager(
                fourierTransform,
                signal,
                3,
                500,
                1
        );

        SignalVisualizer signalVisualizer = new SignalVisualizer(signal, 0, 255, 0);

        rendering.registerNewRenderingObject(signalVisualizer, -1);
        fourierTransformRendererManager.compute();
        rendering.registerNewRenderingObject(fourierTransformRendererManager, 0);
        inputSystem.getInputPropagator().registerNewListener(fourierTransformRendererManager);


        PFont monospaced = createFont("Monospaced", 17);
        textFont(monospaced);
    }

    @Override
    public void draw() {
        background(0);


        fourierTransformRendererManager.step();
        if (fourierTransformRendererManager.shouldLockCameraOnLastVector()) {
            Camera activeCamera = rendering.getActiveCamera();
            TransformElementsAnimator transformElementsAnimator = fourierTransformRendererManager.getTransformElementsAnimator();
            Vector2 lastEndPosition = transformElementsAnimator.getComplexSeriesVisualizer().getLastEndPosition();
            activeCamera.setPosition(lastEndPosition);
        }

        int vectors = (fourierTransformRendererManager.getNumberOfFrequencies() * 2) + 1;
        double timeStep = fourierTransformRendererManager.getTimeStep();
        stroke(255);
        fill(255);
        strokeWeight(3);
        text("[+/-]   Number of vectors: " + vectors, 20, 20);
        text("[Q/E]   Time step: " + timeStep, 20, 40);
        text("[SPACE] Lock on last vector: " + fourierTransformRendererManager.shouldLockCameraOnLastVector(), 20, 60);


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
