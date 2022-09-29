/*
 * Copyright (c) Nicolò Scialpi 2022. Contact info@nicoloscialpi.it . Free to use and share. Do not sell this software.
 */

package it.nicoloscialpi.fouriervisualizer.sketches;

import it.nicoloscialpi.fouriervisualizer.engine.input.InputSystem;
import it.nicoloscialpi.fouriervisualizer.engine.input.KeyboardInputMapping;
import it.nicoloscialpi.fouriervisualizer.engine.input.MouseInputMapping;
import it.nicoloscialpi.fouriervisualizer.engine.input.debug.DummyLoggingListener;
import processing.core.PApplet;
import processing.event.KeyEvent;
import processing.event.MouseEvent;

public class InputTest extends PApplet {

    private final InputSystem inputSystem;

    public InputTest() {
        KeyboardInputMapping keyboardInputMapping = KeyboardInputMapping.getDefault();
        MouseInputMapping mouseInputMapping = MouseInputMapping.getDefault();
        inputSystem = new InputSystem(keyboardInputMapping, mouseInputMapping, null);

        DummyLoggingListener dummyLoggingListener = new DummyLoggingListener("Testing Listener 1");
        inputSystem.getInputPropagator().registerNewListener(dummyLoggingListener);
    }

    @Override
    public void settings() {
        size(200, 200);
    }

    @Override
    public void setup() {
        frameRate(30);
    }

    @Override
    public void draw() {
        inputSystem.getInputPropagator().propagateInput();
        inputSystem.getMouseInputManager().cleanWheelInput();
    }

    @Override
    public void keyPressed(KeyEvent event) {
        inputSystem.getKeyboardInputManager().keyPressed(event.getKeyCode());
    }

    @Override
    public void keyReleased(KeyEvent event) {
        System.out.println(event.getKeyCode());
        inputSystem.getKeyboardInputManager().keyReleased(event.getKeyCode());
    }

    @Override
    public void mousePressed(MouseEvent event) {
        inputSystem.getMouseInputManager().mousePressed(event.getButton());
    }

    @Override
    public void mouseReleased(MouseEvent event) {
        inputSystem.getMouseInputManager().mouseReleased(event.getButton());
    }

    @Override
    public void mouseWheel(MouseEvent event) {
        inputSystem.getMouseInputManager().wheelMoved(event.getCount());
    }
}
