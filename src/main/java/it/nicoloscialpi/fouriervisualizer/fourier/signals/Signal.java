package it.nicoloscialpi.fouriervisualizer.fourier.signals;

import it.nicoloscialpi.fouriervisualizer.fourier.math.Complex;

import java.util.ArrayList;
import java.util.Objects;

public class Signal {

    private ArrayList<Complex> samples;

    public Signal(ArrayList<Complex> samples) {
        this.samples = samples;
    }

    public Signal() {
        this(new ArrayList<>());
    }

    public Signal(Signal signal) {
        this.samples = signal.getSamples();
    }

    public ArrayList<Complex> getSamples() {
        return new ArrayList<>(samples);
    }

    public void setSamples(ArrayList<Complex> samples) {
        this.samples = samples;
    }

    public void clearSignal() {
        samples.clear();
    }

    public void addSample(Complex complex) {
        samples.add(complex);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Signal signal)) return false;
        return Objects.equals(samples, signal.samples);
    }

    @Override
    public int hashCode() {
        return Objects.hash(samples);
    }
}
