/*
 * Copyright (c) Nicolò Scialpi 2022. Contact info@nicoloscialpi.it . Free to use and share. Do not sell this software.
 */

package it.nicoloscialpi.fouriervisualizer;

import it.nicoloscialpi.fouriervisualizer.fourier.application.FourierFinal;
import processing.core.PApplet;

public class Main {

    public static void main(String[] args) {

        if (args.length < 2) {
            System.out.println("Specify width and height as two arguments");
            return;
        }

        int width = Integer.parseInt(args[0]);
        int height = Integer.parseInt(args[1]);
        PApplet pApplet = new FourierFinal(width, height);
        PApplet.runSketch(new String[]{"Fourier Visualizer"}, pApplet);
    }

}
