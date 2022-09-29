/*
 * Copyright (c) Nicolò Scialpi 2022. Contact info@nicoloscialpi.it . Free to use and share. Do not sell this software.
 */

package it.nicoloscialpi.fouriervisualizer.engine.math;

public class VectorMath {

    public static Vector2 add(Vector2 v1, Vector2 v2) {
        return new Vector2(v1.getX() + v2.getX(), v1.getY() + v2.getY());
    }

    public static Vector2 sub(Vector2 v1, Vector2 v2) {
        return new Vector2(v1.getX() - v2.getX(), v1.getY() - v2.getY());
    }

    public static double dot(Vector2 v1, Vector2 v2) {
        return v1.getX() * v2.getX() + v1.getY() * v2.getY();
    }

    public static double distanceSquared(Vector2 v1, Vector2 v2) {
        return Math.pow(v1.getX() - v2.getX(), 2) + Math.pow(v1.getY() - v2.getY(), 2);
    }

    public static double distance(Vector2 v1, Vector2 v2) {
        return Math.sqrt(distanceSquared(v1, v2));
    }

    /**
     * Given two vectors, returns (1-a)v1 + (a)v2
     *
     * @param v1     First vector
     * @param v2     Second vector
     * @param amount From 0 to 1
     * @return (1 - a)v1 + (a)v2
     * @throws IllegalArgumentException if amount is not from 0 to 1
     */
    public static Vector2 linearInterpolate(Vector2 v1, Vector2 v2, double amount) {
        if (amount < 0 || amount > 1) {
            throw new IllegalArgumentException("Amount needs to be from 0 to 1");
        }
        return VectorMath.add(v1.scale(1 - amount), v2.scale(amount));
    }

    public static Vector2 rotate(Vector2 v, double radians) {
        double[][] matrix = new double[2][2];
        Vector2 result = new Vector2(0, 0);
        matrix[0][0] = Math.cos(-radians);
        matrix[0][1] = -Math.sin(-radians);
        matrix[1][0] = Math.sin(-radians);
        matrix[1][1] = Math.cos(-radians);
        for (int i = 0; i < 2; i++) {
            for (int j = 0; j < 2; j++) {
                result.set(i, result.get(i) + matrix[i][j] * v.get(j));
            }
        }
        return result;
    }
}
