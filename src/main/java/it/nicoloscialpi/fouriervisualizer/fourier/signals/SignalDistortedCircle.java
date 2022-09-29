package it.nicoloscialpi.fouriervisualizer.fourier.signals;

import it.nicoloscialpi.fouriervisualizer.fourier.math.Complex;
import it.nicoloscialpi.fouriervisualizer.fourier.math.ComplexMath;

public class SignalDistortedCircle {

    private final Signal signal;
    private final int divisions;
    private final float radius;

    public SignalDistortedCircle(double radius, int divisions) {
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
        for (double p = 0; p < pi2; p += step) {
            signal.addSample(ComplexMath.exp((float) ((Math.sin(p * 10) + Math.cos(p * 4) + Math.sin(p * 7)) + 3) * radius, new Complex(0, (float) p)));
        }
    }
}
