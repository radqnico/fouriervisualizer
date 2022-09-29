package it.nicoloscialpi.fouriervisualizer.engine.rendering.elements;

public record ScreenProperties(int width, int height) {

    public double getRatio() {
        return (double) width / height;
    }

    public int getPixelCount() {
        return width * height;
    }

    public static class Ratio {
        public static double R_16_9 = 1.7777777777777777;
        public static double R_4_3 = 1.3333333333333333;
    }

}
