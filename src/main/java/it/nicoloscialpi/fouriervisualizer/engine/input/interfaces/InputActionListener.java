/*
 * Copyright (c) Nicolò Scialpi 2022. Contact info@nicoloscialpi.it . Free to use and share. Do not sell this software.
 */

package it.nicoloscialpi.fouriervisualizer.engine.input.interfaces;

import it.nicoloscialpi.fouriervisualizer.engine.input.Action;

import java.util.HashSet;

public interface InputActionListener {

    void input(HashSet<Action> actions);

}
