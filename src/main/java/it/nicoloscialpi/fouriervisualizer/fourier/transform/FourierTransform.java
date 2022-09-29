package it.nicoloscialpi.fouriervisualizer.fourier.transform;

import it.nicoloscialpi.fouriervisualizer.fourier.signals.Signal;
import it.nicoloscialpi.fouriervisualizer.fourier.elements.TransformElement;

import java.util.ArrayList;

public interface FourierTransform {

    TransformElement computeFourierForFrequency(Signal signal, int frequency);

    ArrayList<TransformElement> computeSymmetricFourierSeries(Signal signal, int numberOfFrequencies);

}
