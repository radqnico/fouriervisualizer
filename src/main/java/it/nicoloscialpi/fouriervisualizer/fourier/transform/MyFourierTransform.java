/*
 * Copyright (c) Nicolò Scialpi 2022. Contact info@nicoloscialpi.it . Free to use and share. Do not sell this software.
 */

package it.nicoloscialpi.fouriervisualizer.fourier.transform;

import it.nicoloscialpi.fouriervisualizer.fourier.math.Complex;
import it.nicoloscialpi.fouriervisualizer.fourier.math.ComplexMath;
import it.nicoloscialpi.fouriervisualizer.fourier.signals.Signal;
import it.nicoloscialpi.fouriervisualizer.fourier.elements.TransformElement;

import java.util.ArrayList;

public class MyFourierTransform implements FourierTransform {
    @Override
    public TransformElement computeFourierForFrequency(Signal signal, int frequency) {
        Complex output = Complex.zero();
        ArrayList<Complex> samples = signal.getSamples();
        int numberOfSamples = samples.size();
        for (int i = 0; i < numberOfSamples; i++) {
            double exponent = 2 * Math.PI * frequency * i / numberOfSamples;
            Complex rotation = ComplexMath.exp(1, new Complex(0, (float) exponent));
            output = ComplexMath.add(output, ComplexMath.mul(samples.get(i), rotation));
        }
        output = ComplexMath.div(output, numberOfSamples);
        return new TransformElement(output, frequency);
    }

    @Override
    public ArrayList<TransformElement> computeSymmetricFourierSeries(Signal signal, int numberOfFrequencies) {
        ArrayList<TransformElement> output = new ArrayList<>();
        output.add(computeFourierForFrequency(signal, 0));
        for (int i = 0; i < numberOfFrequencies; i++) {
            output.add(computeFourierForFrequency(signal, i + 1));
            output.add(computeFourierForFrequency(signal, -(i + 1)));
        }
        return output;
    }
}
