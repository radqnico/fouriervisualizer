package it.nicoloscialpi.fouriervisualizer.engine.input;

import java.util.HashMap;

public class MouseInputMapping {

    private HashMap<Integer, Action> inputMap;

    public MouseInputMapping(HashMap<Integer, Action> inputMap) {
        this.inputMap = inputMap;
    }

    public static MouseInputMapping getDefault() {
        HashMap<Integer, Action> map = new HashMap<>();
        map.put(0, Action.CLICK_LEFT);
        map.put(1, Action.CLICK_RIGHT);
        map.put(2, Action.WHEEL_DOWN);
        map.put(3, Action.WHEEL_UP);
        return new MouseInputMapping(map);
    }

    public static int transformWheelCode(int code) {
        return code > 0 ? 2 : 3;
    }

    public static int transformButtonCode(int code) {
        return code == 37 ? 0 : 1;
    }

    public Action getAction(int code) {
        return inputMap.getOrDefault(code, Action.NULL);
    }
}
