/*
 * Copyright (c) Nicolò Scialpi 2022. Contact info@nicoloscialpi.it . Free to use and share. Do not sell this software.
 */

package it.nicoloscialpi.fouriervisualizer.engine.rendering.elements;

import it.nicoloscialpi.fouriervisualizer.engine.input.Action;
import it.nicoloscialpi.fouriervisualizer.engine.input.interfaces.InputActionListener;
import it.nicoloscialpi.fouriervisualizer.engine.math.Vector2;

import java.util.HashSet;

public abstract class Camera implements InputActionListener {

    private Vector2 position;
    private double height;
    private double ratio;

    public Camera(Vector2 position, double height, double ratio) {
        this.position = position;
        this.height = height;
        this.ratio = ratio;
    }

    public Vector2 getPosition() {
        return position;
    }

    public void setPosition(Vector2 position) {
        this.position = new Vector2(position);
    }

    public double getHeight() {
        return height;
    }

    public void setHeight(double height) {
        this.height = height;
    }

    public double getRatio() {
        return ratio;
    }

    public void setRatio(double ratio) {
        this.ratio = ratio;
    }

    public double getWidth() {
        return height * ratio;
    }

    public void setWidth(double width) {
        this.height = width / ratio;
    }

    public Vector2 getCornerOffset(Corner corner) {
        switch (corner) {
            case TOP_LEFT -> {
                return new Vector2(-getWidth() / 2, height / 2);
            }
            case TOP_RIGHT -> {
                return new Vector2(getWidth() / 2, height / 2);
            }
            case BOTTOM_LEFT -> {
                return new Vector2(-getWidth() / 2, -height / 2);
            }
            case BOTTOM_RIGHT -> {
                return new Vector2(getWidth() / 2, -height / 2);
            }
            default -> {
                return new Vector2(0, 0);
            }
        }
    }

    @Override
    public abstract void input(HashSet<Action> actions);

    public enum Corner {
        TOP_LEFT,
        TOP_RIGHT,
        BOTTOM_LEFT,
        BOTTOM_RIGHT
    }
}
