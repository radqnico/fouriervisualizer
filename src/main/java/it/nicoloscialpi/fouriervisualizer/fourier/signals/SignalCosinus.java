package it.nicoloscialpi.fouriervisualizer.fourier.signals;

import it.nicoloscialpi.fouriervisualizer.fourier.math.Complex;

public class SignalCosinus {

    private final Signal signal;
    private final int divisions;
    private final float amplitude;

    public SignalCosinus(double radius, int divisions) {
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
            signal.addSample(new Complex(amplitude * (float) Math.cos(p), 0));
        }
    }
}
