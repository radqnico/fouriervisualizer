package it.nicoloscialpi.fouriervisualizer.fourier.elements;

import it.nicoloscialpi.fouriervisualizer.engine.math.Vector2;
import it.nicoloscialpi.fouriervisualizer.engine.rendering.elements.Rendering;
import it.nicoloscialpi.fouriervisualizer.engine.rendering.interfaces.RenderingObject;
import it.nicoloscialpi.fouriervisualizer.fourier.math.Complex;
import it.nicoloscialpi.fouriervisualizer.fourier.math.ComplexMath;
import it.nicoloscialpi.fouriervisualizer.fourier.signals.Signal;

import java.util.ArrayList;

public class TransformElementsAnimator extends RenderingObject {

    private ArrayList<TransformElement> transformElements;
    private final ComplexSeriesVisualizer complexSeriesVisualizer;
    private final SignalVisualizer trackedSignalVisualizer;
    private double time;
    private int numberOfSamplesToKeep;

    public TransformElementsAnimator(ArrayList<TransformElement> transformElements, ComplexSeriesVisualizer complexSeriesVisualizer, int numberOfSamplesToKeep) {
        super();
        this.transformElements = transformElements;
        this.complexSeriesVisualizer = complexSeriesVisualizer;
        this.numberOfSamplesToKeep = numberOfSamplesToKeep;
        trackedSignalVisualizer = new SignalVisualizer(new Signal(), 255, 0, 0);
        time = 0;
    }

    public void setNumberOfSamplesToKeep(int numberOfSamplesToKeep) {
        this.numberOfSamplesToKeep = numberOfSamplesToKeep;
    }

    public ArrayList<TransformElement> getTransformElements() {
        return transformElements;
    }

    public void setTransformElements(ArrayList<TransformElement> transformElements) {
        this.transformElements = transformElements;
    }

    public ComplexSeriesVisualizer getComplexSeriesVisualizer() {
        return complexSeriesVisualizer;
    }

    public double getTime() {
        return time;
    }

    public void addTime(double amount) {
        time += amount;
    }

    private void advanceWithTime() {
        ArrayList<Complex> finalComplexes = new ArrayList<>();
        for (TransformElement transformElement : transformElements) {
            Complex complex = transformElement.complex();
            int frequency = transformElement.frequency();
            Complex advancedComplex = ComplexMath.mul(complex, ComplexMath.exp(1, new Complex(0, (float) time * -frequency)));
            finalComplexes.add(advancedComplex);
        }
        complexSeriesVisualizer.setComplexes(finalComplexes);
    }

    @Override
    public void render(Rendering rendering) {
        if (transformElements.size() == 0) {
            return;
        }
        advanceWithTime();

        Signal trackedSignalVisualizerSignal = trackedSignalVisualizer.getSignal();
        ArrayList<Complex> samples = trackedSignalVisualizerSignal.getSamples();
        Vector2 lastEndPosition = complexSeriesVisualizer.getLastEndPosition();
        samples.add(new Complex(lastEndPosition));
        while (samples.size() > numberOfSamplesToKeep) {
            samples.remove(0);
        }
        trackedSignalVisualizerSignal.setSamples(samples);
        trackedSignalVisualizer.setSignal(trackedSignalVisualizerSignal);


        trackedSignalVisualizer.render(rendering);

        complexSeriesVisualizer.render(rendering);
    }
}
