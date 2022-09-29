package it.nicoloscialpi.fouriervisualizer.engine.math;

import it.nicoloscialpi.fouriervisualizer.engine.interfaces.Duplicable;
import it.nicoloscialpi.fouriervisualizer.engine.interfaces.TolerantEquals;

import java.util.Objects;

public class Vector2 implements TolerantEquals, Duplicable<Vector2> {

    private double x;
    private double y;

    public Vector2(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public Vector2(Vector2 vector2) {
        this.x = vector2.x;
        this.y = vector2.y;
    }

    public static Vector2 zero() {
        return new Vector2(0, 0);
    }

    public double getX() {
        return x;
    }

    public void setX(double x) {
        this.x = x;
    }

    public float getXf() {
        return (float) x;
    }

    public float getYf() {
        return (float) y;
    }

    public double getY() {
        return y;
    }

    public void setY(double y) {
        this.y = y;
    }

    public void changeX(double x) {
        this.x += x;
    }

    public void changeY(double y) {
        this.y += y;
    }

    public void set(int position, double value) {
        if (position < 0 || position > 1) {
            throw new IllegalArgumentException("Position is not 0 or 1");
        }
        if (position == 0) {
            setX(value);
        } else {
            setY(value);
        }
    }

    public double get(int position) {
        if (position < 0 || position > 1) {
            throw new IllegalArgumentException("Position is not 0 or 1");
        }
        if (position == 0) {
            return x;
        } else {
            return y;
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Vector2 vector2)) {
            return false;
        }
        return Double.compare(vector2.x, x) == 0 && Double.compare(vector2.y, y) == 0;
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y);
    }

    @Override
    public String toString() {
        return "Vector2{" +
                "x=" + x +
                ", y=" + y +
                '}';
    }

    @Override
    public boolean tolerantEquals(Object o, double tolerance) {
        if (this == o) return true;
        if (!(o instanceof Vector2 vector2)) return false;
        double distanceSquared = VectorMath.distanceSquared(this, vector2);
        return distanceSquared < tolerance * tolerance;
    }

    public Vector2 duplicate() {
        return new Vector2(x, y);
    }

    public Vector2 scale(double a) {
        return new Vector2(this.x * a, this.y * a);
    }

    public double normSquared() {
        return x * x + y + y;
    }

    public double norm() {
        return Math.sqrt(normSquared());
    }

    public double angle() {
        return Math.atan2(y, x);
    }

    public Vector2 toUnitVector() {
        return scale(1 / norm());
    }


}
