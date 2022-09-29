package it.nicoloscialpi.fouriervisualizer.engine.input.interfaces;

import it.nicoloscialpi.fouriervisualizer.engine.input.Action;

import java.util.HashSet;

public interface InputActionListener {

    void input(HashSet<Action> actions);

}
