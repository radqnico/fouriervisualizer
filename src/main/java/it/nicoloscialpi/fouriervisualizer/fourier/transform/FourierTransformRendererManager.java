/*
 * Copyright (c) Nicolò Scialpi 2022. Contact info@nicoloscialpi.it . Free to use and share. Do not sell this software.
 */

package it.nicoloscialpi.fouriervisualizer.fourier.transform;

import it.nicoloscialpi.fouriervisualizer.engine.input.Action;
import it.nicoloscialpi.fouriervisualizer.engine.input.interfaces.InputActionListener;
import it.nicoloscialpi.fouriervisualizer.engine.rendering.elements.Rendering;
import it.nicoloscialpi.fouriervisualizer.engine.rendering.interfaces.RenderingObject;
import it.nicoloscialpi.fouriervisualizer.fourier.elements.ComplexSeriesVisualizer;
import it.nicoloscialpi.fouriervisualizer.fourier.elements.TransformElement;
import it.nicoloscialpi.fouriervisualizer.fourier.elements.TransformElementsAnimator;
import it.nicoloscialpi.fouriervisualizer.fourier.signals.Signal;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;

public class FourierTransformRendererManager extends RenderingObject implements InputActionListener {

    private final TransformElementsAnimator transformElementsAnimator;
    private FourierTransform fourierTransform;
    private Signal signal;
    private int numberOfFrequencies;
    private double timeStep;
    private boolean lockCameraOnLastVector;
    private LocalDateTime lastInput;

    public FourierTransformRendererManager(FourierTransform fourierTransform, Signal signal, int numberOfFrequencies, int numberOfSamplesToKeep, int lineWidth) {
        this.transformElementsAnimator = new TransformElementsAnimator(new ArrayList<>(), new ComplexSeriesVisualizer(new ArrayList<>(), lineWidth), numberOfSamplesToKeep);
        this.fourierTransform = fourierTransform;
        this.signal = signal;
        this.timeStep = 0.01;
        this.numberOfFrequencies = numberOfFrequencies;
        this.lockCameraOnLastVector = false;
        this.lastInput = LocalDateTime.now();
    }

    public int getNumberOfFrequencies() {
        return numberOfFrequencies;
    }

    public void setNumberOfFrequencies(int numberOfFrequencies) {
        this.numberOfFrequencies = numberOfFrequencies;
        compute();
    }

    public double getTimeStep() {
        return timeStep;
    }

    public boolean isLockCameraOnLastVector() {
        return lockCameraOnLastVector;
    }

    public FourierTransform getFourierTransform() {
        return fourierTransform;
    }

    public void setFourierTransform(FourierTransform fourierTransform) {
        this.fourierTransform = fourierTransform;
        compute();
    }

    public Signal getSignal() {
        return signal;
    }

    public void setSignal(Signal signal) {
        this.signal = new Signal(signal);
        compute();
    }

    public void compute() {
        ArrayList<TransformElement> transformElements = fourierTransform.computeSymmetricFourierSeries(signal, numberOfFrequencies);
        transformElementsAnimator.setTransformElements(transformElements);
    }

    public void setNumberOfSamplesToKeep(int numberOfSamplesToKeep) {
        this.transformElementsAnimator.setNumberOfSamplesToKeep(numberOfSamplesToKeep);
    }

    public void step() {
        transformElementsAnimator.addTime(timeStep);
    }

    public void resetTime() {
        double time = transformElementsAnimator.getTime();
        transformElementsAnimator.addTime(-time);
    }

    public boolean shouldLockCameraOnLastVector() {
        return lockCameraOnLastVector;
    }

    public TransformElementsAnimator getTransformElementsAnimator() {
        return transformElementsAnimator;
    }

    @Override
    public void render(Rendering rendering) {
        transformElementsAnimator.render(rendering);
    }

    @Override
    public void input(HashSet<Action> actions) {
        Duration between = Duration.between(lastInput, LocalDateTime.now());
        if (between.getSeconds() < 1
                && (between.getNano() < 100000000)) {
            return;
        }
        lastInput = LocalDateTime.now();
        if (actions.contains(Action.Q_BUTTON)) {
            timeStep *= 0.9;
        }
        if (actions.contains(Action.E_BUTTON)) {
            timeStep *= 1.1;
        }
        if (actions.contains(Action.PLUS)) {
            setNumberOfFrequencies(numberOfFrequencies + 1);
        }
        if (actions.contains(Action.MINUS)) {
            if (numberOfFrequencies > 0) {
                setNumberOfFrequencies(numberOfFrequencies - 1);
            }
        }
        if (actions.contains(Action.SPACE)) {
            lockCameraOnLastVector = !lockCameraOnLastVector;
        }
    }
}
