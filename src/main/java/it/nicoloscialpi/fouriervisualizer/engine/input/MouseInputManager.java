package it.nicoloscialpi.fouriervisualizer.engine.input;

import java.util.HashSet;

public class MouseInputManager {

    private MouseInputMapping mouseInputMapping;
    private HashSet<Action> activeActions;

    public MouseInputManager(MouseInputMapping mouseInputMapping) {
        this.mouseInputMapping = mouseInputMapping;
        this.activeActions = new HashSet<>();
    }

    public void mousePressed(int button) {
        int transformButtonCode = MouseInputMapping.transformButtonCode(button);
        Action action = mouseInputMapping.getAction(transformButtonCode);
        activeActions.add(action);
    }


    public void mouseReleased(int button) {
        int transformButtonCode = MouseInputMapping.transformButtonCode(button);
        Action action = mouseInputMapping.getAction(transformButtonCode);
        activeActions.remove(action);
    }

    public void wheelMoved(int count) {
        int transformWheelCode = MouseInputMapping.transformWheelCode(count);
        Action action = mouseInputMapping.getAction(transformWheelCode);
        activeActions.add(action);
    }

    public void cleanWheelInput() {
        activeActions.removeIf(action -> action == Action.WHEEL_UP || action == Action.WHEEL_DOWN);
    }

    public HashSet<Action> getActiveActions() {
        return new HashSet<>(activeActions);
    }
}
