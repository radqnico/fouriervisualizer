/*
 * Copyright (c) Nicolò Scialpi 2022. Contact info@nicoloscialpi.it . Free to use and share. Do not sell this software.
 */

package it.nicoloscialpi.fouriervisualizer.fourier.signals;

import it.nicoloscialpi.fouriervisualizer.fourier.math.Complex;
import it.nicoloscialpi.fouriervisualizer.fourier.math.ComplexMath;

public class SignalSquare {

    private final Signal signal;
    private final int divisions;
    private final float radius;

    public SignalSquare(double radius, int divisions) {
        this.signal = new Signal();
        this.divisions = divisions;
        this.radius = (float) radius;
    }

    public Signal getSignal() {
        if (signal.getSamples().size() == 0) {
            calculateSignal();
        }
        return new Signal(signal);
    }

    private void calculateSignal() {
        double pi2 = 2 * Math.PI;
        double step = pi2 / divisions;
        float piFourths = (float) Math.PI / 4;
        for (double p = 0; p < pi2; p += step) {
            if (0 <= p && p < Math.PI / 2) {
                signal.addSample(ComplexMath.exp(radius, new Complex(0, piFourths)));
            } else if (Math.PI / 2 <= p && p < Math.PI) {
                signal.addSample(ComplexMath.exp(radius, new Complex(0, 3 * piFourths)));
            } else if (Math.PI <= p && p < 3 * Math.PI / 2) {
                signal.addSample(ComplexMath.exp(radius, new Complex(0, 5 * piFourths)));
            } else if (3 * Math.PI / 2 <= p) {
                signal.addSample(ComplexMath.exp(radius, new Complex(0, 7 * piFourths)));
            }
        }
        signal.addSample(ComplexMath.exp(radius, new Complex(0, piFourths)));
    }
}
