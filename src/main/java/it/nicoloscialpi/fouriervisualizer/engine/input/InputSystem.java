package it.nicoloscialpi.fouriervisualizer.engine.input;

import it.nicoloscialpi.fouriervisualizer.engine.input.interfaces.InputActionListener;

import java.util.ArrayList;

public class InputSystem {

    private final KeyboardInputMapping keyboardInputMapping;
    private final MouseInputMapping mouseInputMapping;
    private final KeyboardInputManager keyboardInputManager;
    private final MouseInputManager mouseInputManager;
    private final InputPropagator inputPropagator;

    public InputSystem(KeyboardInputMapping keyboardInputMapping, MouseInputMapping mouseInputMapping, ArrayList<InputActionListener> listeners) {
        this.keyboardInputMapping = keyboardInputMapping;
        this.keyboardInputManager = new KeyboardInputManager(keyboardInputMapping);
        this.mouseInputMapping = mouseInputMapping;
        this.mouseInputManager = new MouseInputManager(mouseInputMapping);
        this.inputPropagator = new InputPropagator(keyboardInputManager, mouseInputManager, listeners != null ? listeners : new ArrayList<>());
    }


    public KeyboardInputMapping getInputMapping() {
        return keyboardInputMapping;
    }

    public KeyboardInputManager getKeyboardInputManager() {
        return keyboardInputManager;
    }

    public InputPropagator getInputPropagator() {
        return inputPropagator;
    }

    public MouseInputManager getMouseInputManager() {
        return mouseInputManager;
    }
}
