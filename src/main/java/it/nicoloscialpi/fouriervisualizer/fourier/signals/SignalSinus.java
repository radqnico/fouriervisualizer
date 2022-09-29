/*
 * Copyright (c) Nicolò Scialpi 2022. Contact info@nicoloscialpi.it . Free to use and share. Do not sell this software.
 */

package it.nicoloscialpi.fouriervisualizer.fourier.signals;

import it.nicoloscialpi.fouriervisualizer.fourier.math.Complex;

public class SignalSinus {

    private final Signal signal;
    private final int divisions;
    private final float amplitude;

    public SignalSinus(double radius, int divisions) {
        this.signal = new Signal();
        this.divisions = divisions;
        this.amplitude = (float) radius;
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
        for (double p = 0; p < pi2; p += step) {
            signal.addSample(new Complex(amplitude * (float) Math.sin(p), 0));
        }
    }
}
