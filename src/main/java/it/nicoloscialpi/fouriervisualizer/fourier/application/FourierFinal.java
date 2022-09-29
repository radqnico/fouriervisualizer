/*
 * Copyright (c) Nicolò Scialpi 2022. Contact info@nicoloscialpi.it . Free to use and share. Do not sell this software.
 */

package it.nicoloscialpi.fouriervisualizer.fourier.application;

import it.nicoloscialpi.fouriervisualizer.engine.InputRenderingReadyApplet;
import it.nicoloscialpi.fouriervisualizer.engine.input.Action;
import it.nicoloscialpi.fouriervisualizer.engine.math.Vector2;
import it.nicoloscialpi.fouriervisualizer.engine.rendering.elements.Camera;
import it.nicoloscialpi.fouriervisualizer.engine.rendering.interfaces.CoordinateTransform;
import it.nicoloscialpi.fouriervisualizer.fourier.elements.SignalVisualizer;
import it.nicoloscialpi.fouriervisualizer.fourier.math.Complex;
import it.nicoloscialpi.fouriervisualizer.fourier.signals.*;
import it.nicoloscialpi.fouriervisualizer.fourier.simpleobjects.ComplexPlaneAxis;
import it.nicoloscialpi.fouriervisualizer.fourier.transform.FourierTransformRendererManager;
import it.nicoloscialpi.fouriervisualizer.fourier.transform.MyFourierTransform;
import processing.core.PFont;
import processing.event.KeyEvent;
import processing.event.MouseEvent;

import java.util.HashSet;
import java.util.Optional;

public class FourierFinal extends InputRenderingReadyApplet {

    private ApplicationState applicationState;
    private SignalVisualizer signalVisualizer;
    private FourierTransformRendererManager fourierTransformRendererManager;
    private PFont titleFont;
    private PFont font;
    private boolean isShowingAxes;
    private ComplexPlaneAxis complexPlaneAxis;

    public FourierFinal(int width, int height) {
        super(width, height);
        isShowingAxes = true;
    }


    @Override
    public void start() {
        super.start();
        frameRate(60);
        applicationState = ApplicationState.START;

        Signal signal = new SignalCircle(5, 200).getSignal();
        signalVisualizer = new SignalVisualizer(signal, 0, 255, 0);

        fourierTransformRendererManager = new FourierTransformRendererManager(
                new MyFourierTransform(),
                signal,
                3,
                500,
                1
        );

        fourierTransformRendererManager.compute();

        complexPlaneAxis = new ComplexPlaneAxis();

        rendering.registerNewRenderingObject(complexPlaneAxis, -2);

        rendering.registerNewRenderingObject(signalVisualizer, 0);
        rendering.registerNewRenderingObject(fourierTransformRendererManager, 1);

        inputSystem.getInputPropagator().registerNewListener(fourierTransformRendererManager);

        titleFont = createFont("Open Sans SemiBold", 30);
        font = createFont("Open Sans SemiBold", 14);
    }

    @Override
    public void settings() {
        super.settings();
    }

    @Override
    public void draw() {
        background(0);
        switch (applicationState) {
            case START -> {
                fourierTransformRendererManager.step();
                super.draw();
                showTitle();
                return;
            }
            case DRAWING -> drawing();
            case VECTORS -> vectors();
        }
        super.draw();
    }

    public void showTitle() {
        push();
        textSize(40);
        stroke(0);
        textAlign(CENTER);
        textFont(titleFont);
        text("Start by drawing a signal", width / 2f, 0.5f * height / 3f);
        text("Nicolò Scialpi", width / 2f, 2.5f * height / 3f);
        pop();
    }

    public void drawing() {
        addSampleFromMouse();
    }

    private void addSampleFromMouse() {
        CoordinateTransform coordinateTransform = rendering.getCoordinateTransform();
        Vector2 point = new Vector2(mouseX, mouseY);
        Vector2 worldSample = coordinateTransform.screenToWorld(point, rendering.getScreenProperties(), rendering.getActiveCamera());
        signalVisualizer.getSignal().addSample(new Complex(worldSample));
    }

    private void vectors() {
        fourierTransformRendererManager.step();
        if (fourierTransformRendererManager.shouldLockCameraOnLastVector()) {
            Camera activeCamera = rendering.getActiveCamera();
            Vector2 lastEndPosition = fourierTransformRendererManager.getTransformElementsAnimator().getComplexSeriesVisualizer().getLastEndPosition();
            activeCamera.setPosition(lastEndPosition);
        }
        putInfoOnScreen();
    }

    private void putInfoOnScreen() {
        push();
        stroke(255);
        fill(255);
        strokeWeight(3);
        textFont(font);
        int vectors = (fourierTransformRendererManager.getNumberOfFrequencies() * 2) + 1;
        double timeStep = fourierTransformRendererManager.getTimeStep();
        text("[+/-] Number of vectors: " + vectors, 20, 20);
        text("[Q/E] Time step: " + timeStep, 20, 35);
        text("[WASD] Move camera", 20, 50);
        text("[MouseWheel] Zoom", 20, 65);
        textAlign(RIGHT);
        text("Show/Hide Axes [TAB]", width - 20, 20);
        text("Lock on last vector [SPACE]", width - 20, 35);
        text("Example signals [1-9]", width - 20, 50);
        pop();
    }

    @Override
    public void mousePressed(MouseEvent event) {
        super.mousePressed(event);
        applicationState = ApplicationState.DRAWING;
        rendering.removeRenderingObject(fourierTransformRendererManager);
        signalVisualizer.getSignal().clearSignal();
    }

    @Override
    public void mouseReleased(MouseEvent event) {
        super.mouseReleased(event);
        fourierTransformRendererManager.resetTime();
        fourierTransformRendererManager.setSignal(signalVisualizer.getSignal());
        rendering.registerNewRenderingObject(fourierTransformRendererManager, 1);
        applicationState = ApplicationState.VECTORS;
    }

    @Override
    public void keyPressed(KeyEvent event) {
        super.keyPressed(event);
        if (inputSystem.getKeyboardInputManager().getActiveActions().contains(Action.TAB)) {
            if (isShowingAxes) {
                rendering.removeRenderingObject(complexPlaneAxis);
            } else {
                rendering.registerNewRenderingObject(complexPlaneAxis, -2);
            }
            isShowingAxes = !isShowingAxes;
        }
        switchPrefabSignals(inputSystem.getKeyboardInputManager().getActiveActions());
    }

    private void switchPrefabSignals(HashSet<Action> actions) {
        Optional<Action> actionOptional = actions.stream().filter(action -> action.name().contains("KEY")).findFirst();
        if (actionOptional.isEmpty()) {
            return;
        }
        Action action = actionOptional.get();
        Signal signal = new Signal();
        switch (action) {
            case KEY_1 -> signal = new SignalCircle(1, 200).getSignal();
            case KEY_2 -> signal = new SignalSinus(1, 200).getSignal();
            case KEY_3 -> signal = new SignalCosinus(1, 200).getSignal();
            case KEY_4 -> signal = new SignalSquare(1, 200).getSignal();
        }
        signalVisualizer.setSignal(signal);
        fourierTransformRendererManager.setSignal(signal);
    }
}
