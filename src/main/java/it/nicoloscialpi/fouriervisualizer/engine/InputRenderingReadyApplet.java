/*
 * Copyright (c) Nicolò Scialpi 2022. Contact info@nicoloscialpi.it . Free to use and share. Do not sell this software.
 */

package it.nicoloscialpi.fouriervisualizer.engine;

import it.nicoloscialpi.fouriervisualizer.engine.input.InputSystem;
import it.nicoloscialpi.fouriervisualizer.engine.input.KeyboardInputMapping;
import it.nicoloscialpi.fouriervisualizer.engine.input.MouseInputMapping;
import it.nicoloscialpi.fouriervisualizer.engine.math.Vector2;
import it.nicoloscialpi.fouriervisualizer.engine.rendering.elements.Camera;
import it.nicoloscialpi.fouriervisualizer.engine.rendering.elements.Rendering;
import it.nicoloscialpi.fouriervisualizer.engine.rendering.elements.ScreenProperties;
import it.nicoloscialpi.fouriervisualizer.engine.rendering.elements.cameras.WASDWheelCamera;
import it.nicoloscialpi.fouriervisualizer.engine.rendering.interfaces.CoordinateTransform;
import it.nicoloscialpi.fouriervisualizer.engine.rendering.transforms.StandardCoordinateTransform;
import processing.core.PApplet;
import processing.event.KeyEvent;
import processing.event.MouseEvent;

public class InputRenderingReadyApplet extends PApplet {

    private final int _width;
    private final int _height;
    protected InputSystem inputSystem;
    protected Rendering rendering;

    public InputRenderingReadyApplet(int width, int height) {
        this._width = width;
        this._height = height;
    }

    @Override
    public void settings() {
        super.settings();
        size(_width, _height);
    }

    @Override
    public void start() {
        KeyboardInputMapping keyboardInputMapping = KeyboardInputMapping.getDefault();
        MouseInputMapping mouseInputMapping = MouseInputMapping.getDefault();
        inputSystem = new InputSystem(keyboardInputMapping, mouseInputMapping, null);

        ScreenProperties screenProperties = new ScreenProperties(_width, _height);
        Camera camera = new WASDWheelCamera(new Vector2(0, 0), 30, screenProperties.getRatio());

        inputSystem.getInputPropagator().registerNewListener(camera);

        CoordinateTransform coordinateTransform = new StandardCoordinateTransform();

        rendering = new Rendering(this, camera, screenProperties, coordinateTransform);
    }

    @Override
    public void draw() {
        inputSystem.getInputPropagator().propagateInput();
        inputSystem.getMouseInputManager().cleanWheelInput();
        rendering.renderAll();
    }

    @Override
    public void keyPressed(KeyEvent event) {
        inputSystem.getKeyboardInputManager().keyPressed(event.getKeyCode());
    }

    @Override
    public void keyReleased(KeyEvent event) {
        System.out.println("Key Code Pressed: " + event.getKeyCode());
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
