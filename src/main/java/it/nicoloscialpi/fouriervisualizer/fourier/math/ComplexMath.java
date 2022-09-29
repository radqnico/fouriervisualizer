package it.nicoloscialpi.fouriervisualizer.fourier.math;

import it.nicoloscialpi.fouriervisualizer.fourier.math.Complex;

public class ComplexMath {

    public static Complex add(Complex z1, Complex z2) {
        return new Complex(z1.getReal() + z2.getReal(), z1.getImaginary() + z2.getImaginary());
    }

    public static Complex sub(Complex z1, Complex z2) {
        return new Complex(z1.getReal() - z2.getReal(), z1.getImaginary() - z2.getImaginary());
    }

    public static Complex mul(Complex z1, Complex z2) {
        return new Complex(z1.getReal() * z2.getReal() - z1.getImaginary() * z2.getImaginary(), z1.getReal() * z2.getImaginary() + z1.getImaginary() * z2.getReal());
    }

    public static Complex mul(Complex z, float n) {
        return new Complex(z.getReal() * n, z.getImaginary() * n);
    }

    public static Complex reciprocal(Complex z) {
        Complex temp = new Complex(z.getReal(), -z.getImaginary());
        return mul(temp, z.getAmplitude());
    }

    public static Complex div(Complex z1, Complex z2) {
        return mul(z1, reciprocal(z2));
    }

    public static Complex div(Complex z, float n) {
        return mul(z, 1 / n);
    }

    public static Complex exp(float coefficient, Complex exponent) {
        Complex complex = mul(new Complex((float) Math.cos(exponent.getImaginary()), (float) Math.sin(exponent.getImaginary())), coefficient);
        return mul(complex, (float) Math.exp(exponent.getReal()));
    }

}
