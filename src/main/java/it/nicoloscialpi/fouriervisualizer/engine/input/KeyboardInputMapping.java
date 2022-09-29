/*
 * Copyright (c) Nicolò Scialpi 2022. Contact info@nicoloscialpi.it . Free to use and share. Do not sell this software.
 */

package it.nicoloscialpi.fouriervisualizer.engine.input;

import java.util.HashMap;

public class KeyboardInputMapping {

    private HashMap<Integer, Action> inputMap;

    public KeyboardInputMapping(HashMap<Integer, Action> inputMap) {
        this.inputMap = inputMap;
    }

    public static KeyboardInputMapping getDefault() {
        HashMap<Integer, Action> inputMap = new HashMap<>();
        inputMap.put(9, Action.TAB);
        inputMap.put(87, Action.UP);
        inputMap.put(83, Action.DOWN);
        inputMap.put(65, Action.RIGHT);
        inputMap.put(68, Action.LEFT);
        inputMap.put(81, Action.Q_BUTTON);
        inputMap.put(69, Action.E_BUTTON);
        inputMap.put(45, Action.MINUS);
        inputMap.put(109, Action.MINUS);
        inputMap.put(521, Action.PLUS);
        inputMap.put(107, Action.PLUS);
        inputMap.put(32, Action.SPACE);
        inputMap.put(49, Action.KEY_1);
        inputMap.put(50, Action.KEY_2);
        inputMap.put(51, Action.KEY_3);
        inputMap.put(52, Action.KEY_4);
        inputMap.put(53, Action.KEY_5);
        inputMap.put(54, Action.KEY_6);
        inputMap.put(55, Action.KEY_7);
        inputMap.put(56, Action.KEY_8);
        inputMap.put(57, Action.KEY_9);
        return new KeyboardInputMapping(inputMap);
    }

    public Action getMap(int keyCode) {
        return inputMap.getOrDefault(keyCode, Action.NULL);
    }


}
