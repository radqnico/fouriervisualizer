/*
 * Copyright (c) Nicolò Scialpi 2022. Contact info@nicoloscialpi.it . Free to use and share. Do not sell this software.
 */

package it.nicoloscialpi.fouriervisualizer.engine.input.debug;

import it.nicoloscialpi.fouriervisualizer.engine.input.Action;
import it.nicoloscialpi.fouriervisualizer.engine.input.interfaces.InputActionListener;

import java.util.HashSet;

public class DummyLoggingListener implements InputActionListener {

    private String name;

    public DummyLoggingListener(String name) {
        this.name = name;
    }

    @Override
    public void input(HashSet<Action> actions) {
        System.out.println("Dummy listener '" + name + "': " + actions);
    }
}
