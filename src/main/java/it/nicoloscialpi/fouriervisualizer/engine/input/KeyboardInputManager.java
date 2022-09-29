/*
 * Copyright (c) Nicolò Scialpi 2022. Contact info@nicoloscialpi.it . Free to use and share. Do not sell this software.
 */

package it.nicoloscialpi.fouriervisualizer.engine.input;

import java.util.HashSet;

public class KeyboardInputManager {

    private final KeyboardInputMapping keyboardInputMapping;
    private final HashSet<Action> activeActions;

    public KeyboardInputManager(KeyboardInputMapping keyboardInputMapping) {
        this.keyboardInputMapping = keyboardInputMapping;
        this.activeActions = new HashSet<>();
    }

    public void keyPressed(int keyCode) {
        Action map = keyboardInputMapping.getMap(keyCode);
        activeActions.add(map);
    }

    public void keyReleased(int keyCode) {
        Action map = keyboardInputMapping.getMap(keyCode);
        activeActions.remove(map);
    }

    public HashSet<Action> getActiveActions() {
        return new HashSet<>(activeActions);
    }
}
