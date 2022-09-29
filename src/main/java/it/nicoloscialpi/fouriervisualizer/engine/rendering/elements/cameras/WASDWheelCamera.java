package it.nicoloscialpi.fouriervisualizer.engine.rendering.elements.cameras;

import it.nicoloscialpi.fouriervisualizer.engine.input.Action;
import it.nicoloscialpi.fouriervisualizer.engine.math.Vector2;
import it.nicoloscialpi.fouriervisualizer.engine.math.VectorMath;
import it.nicoloscialpi.fouriervisualizer.engine.rendering.elements.Camera;

import java.util.HashSet;

public class WASDWheelCamera extends Camera {

    public WASDWheelCamera(Vector2 position, double height, double ratio) {
        super(position, height, ratio);
    }

    @Override
    public void input(HashSet<Action> actions) {
        Vector2 movement = new Vector2(0, 0);
        double speed = getHeight() / 100;
        if (actions.contains(Action.UP)) {
            movement = VectorMath.add(movement, new Vector2(0, speed));
        }
        if (actions.contains(Action.DOWN)) {
            movement = VectorMath.add(movement, new Vector2(0, -speed));
        }
        if (actions.contains(Action.LEFT)) {
            movement = VectorMath.add(movement, new Vector2(speed, 0));
        }
        if (actions.contains(Action.RIGHT)) {
            movement = VectorMath.add(movement, new Vector2(-speed, 0));
        }
        setPosition(VectorMath.add(getPosition(), movement));

        if (actions.contains(Action.WHEEL_UP)) {
            setHeight(getHeight() * 0.9);
        }

        if (actions.contains(Action.WHEEL_DOWN)) {
            setHeight(getHeight() * 1.1);
        }
    }
}
