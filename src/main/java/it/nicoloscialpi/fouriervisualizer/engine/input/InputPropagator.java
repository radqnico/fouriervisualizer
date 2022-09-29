package it.nicoloscialpi.fouriervisualizer.engine.input;

import it.nicoloscialpi.fouriervisualizer.engine.input.interfaces.InputActionListener;

import java.util.ArrayList;
import java.util.HashSet;

public class InputPropagator {

    private final KeyboardInputManager keyboardInputManager;
    private final MouseInputManager mouseInputManager;
    private final ArrayList<InputActionListener> inputActionListeners;

    public InputPropagator(KeyboardInputManager keyboardInputManager, MouseInputManager mouseInputManager, ArrayList<InputActionListener> inputActionListeners) {
        this.keyboardInputManager = keyboardInputManager;
        this.mouseInputManager = mouseInputManager;
        this.inputActionListeners = inputActionListeners;
    }

    public InputPropagator(KeyboardInputManager keyboardInputManager, MouseInputManager mouseInputManager) {
        this(keyboardInputManager, mouseInputManager, new ArrayList<>());
    }

    public void registerNewListener(InputActionListener inputActionListener) {
        if (!inputActionListeners.contains(inputActionListener)) {
            inputActionListeners.add(inputActionListener);
        }
    }

    public void removeListener(InputActionListener inputActionListener) {
        inputActionListeners.remove(inputActionListener);
    }

    public void propagateInput() {
        HashSet<Action> activeActions = keyboardInputManager.getActiveActions();
        activeActions.addAll(mouseInputManager.getActiveActions());
        for (InputActionListener inputActionListener : inputActionListeners) {
            inputActionListener.input(activeActions);
        }
    }
}
