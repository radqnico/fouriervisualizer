/*
 * Copyright (c) Nicolò Scialpi 2022. Contact info@nicoloscialpi.it . Free to use and share. Do not sell this software.
 */

package it.nicoloscialpi.fouriervisualizer.sketches;

import it.nicoloscialpi.fouriervisualizer.engine.input.InputSystem;
import it.nicoloscialpi.fouriervisualizer.engine.input.KeyboardInputMapping;
import it.nicoloscialpi.fouriervisualizer.engine.input.MouseInputMapping;
import it.nicoloscialpi.fouriervisualizer.engine.math.Vector2;
import it.nicoloscialpi.fouriervisualizer.engine.rendering.debug.DebugCircle;
import it.nicoloscialpi.fouriervisualizer.engine.rendering.debug.DebugLine;
import it.nicoloscialpi.fouriervisualizer.engine.rendering.elements.Camera;
import it.nicoloscialpi.fouriervisualizer.engine.rendering.elements.Rendering;
import it.nicoloscialpi.fouriervisualizer.engine.rendering.elements.ScreenProperties;
import it.nicoloscialpi.fouriervisualizer.engine.rendering.elements.cameras.WASDWheelCamera;
import it.nicoloscialpi.fouriervisualizer.engine.rendering.interfaces.CoordinateTransform;
import it.nicoloscialpi.fouriervisualizer.engine.rendering.transforms.StandardCoordinateTransform;
import processing.core.PApplet;
import processing.event.KeyEvent;
import processing.event.MouseEvent;

public class RenderingTest extends PApplet {

    private InputSystem inputSystem;
    private Rendering rendering;

    @Override
    public void settings() {
        size(1280, 720);
    }

    @Override
    public void setup() {
        KeyboardInputMapping keyboardInputMapping = KeyboardInputMapping.getDefault();
        MouseInputMapping mouseInputMapping = MouseInputMapping.getDefault();
        inputSystem = new InputSystem(keyboardInputMapping, mouseInputMapping, null);

        ScreenProperties screenProperties = new ScreenProperties(width, height);
        Camera camera = new WASDWheelCamera(new Vector2(0, 0), 10, screenProperties.getRatio());

        inputSystem.getInputPropagator().registerNewListener(camera);

        CoordinateTransform coordinateTransform = new StandardCoordinateTransform();

        rendering = new Rendering(this, camera, screenProperties, coordinateTransform);

        rendering.registerNewRenderingObject(new DebugCircle(new Vector2(0, 0), 1), 1);
        rendering.registerNewRenderingObject(new DebugLine(new Vector2(-100, 0), new Vector2(100, 0)), 0);
        rendering.registerNewRenderingObject(new DebugLine(new Vector2(0, -100), new Vector2(0, 100)), 0);
    }

    @Override
    public void draw() {
        background(0);
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
