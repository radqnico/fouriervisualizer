/*
 * Copyright (c) Nicolò Scialpi 2022. Contact info@nicoloscialpi.it . Free to use and share. Do not sell this software.
 */

package it.nicoloscialpi.fouriervisualizer.fourier.math;

import it.nicoloscialpi.fouriervisualizer.engine.math.Vector2;

public class Complex {

    private float real;
    private float imaginary;

    public Complex(float real, float imaginary) {
        this.real = real;
        this.imaginary = imaginary;
    }

    public Complex(Vector2 vector2) {
        this(vector2.getXf(), vector2.getYf());
    }

    public Complex() {
        this(0, 0);
    }

    public static Complex zero() {
        return new Complex(0, 0);
    }

    public Vector2 toVector2() {
        return new Vector2(real, imaginary);
    }

    public float getReal() {
        return real;
    }

    public void setReal(float real) {
        this.real = real;
    }

    public float getImaginary() {
        return imaginary;
    }

    public void setImaginary(float imaginary) {
        this.imaginary = imaginary;
    }

    public float getAmplitude() {
        return (float) Math.sqrt(real * real + imaginary * imaginary);
    }

    public float getPhase() {
        return (float) Math.atan2(imaginary, real);
    }
}
