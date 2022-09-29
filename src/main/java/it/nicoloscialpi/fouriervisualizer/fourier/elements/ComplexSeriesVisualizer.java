package it.nicoloscialpi.fouriervisualizer.fourier.elements;

import it.nicoloscialpi.fouriervisualizer.engine.math.Vector2;
import it.nicoloscialpi.fouriervisualizer.engine.math.VectorMath;
import it.nicoloscialpi.fouriervisualizer.engine.rendering.elements.Rendering;
import it.nicoloscialpi.fouriervisualizer.engine.rendering.interfaces.RenderingObject;
import it.nicoloscialpi.fouriervisualizer.fourier.math.Complex;

import java.util.ArrayList;

public class ComplexSeriesVisualizer extends RenderingObject {

    private final ArrayList<ComplexVisualizer> complexVisualizers;
    private ArrayList<Complex> complexes;
    private Vector2 lastEndPosition;
    private float width;

    public ComplexSeriesVisualizer(ArrayList<Complex> complexes, float width) {
        super();
        this.complexes = complexes;
        this.complexVisualizers = new ArrayList<>();
        this.width = width;
        lastEndPosition = Vector2.zero();
    }

    public ArrayList<Complex> getComplexes() {
        return new ArrayList<>(complexes);
    }

    public void setComplexes(ArrayList<Complex> complexes) {
        this.complexes = complexes;
    }

    public float getWidth() {
        return width;
    }

    public void setWidth(float width) {
        this.width = width;
    }

    private void checkComplexVisualizersAmount() {
        if (complexes.size() == complexVisualizers.size()) {
            return;
        }
        while (complexes.size() < complexVisualizers.size()) {
            complexVisualizers.remove(complexVisualizers.size() - 1);
        }
        while (complexes.size() > complexVisualizers.size()) {
            complexVisualizers.add(new ComplexVisualizer(
                    Vector2.zero(),
                    new Complex(0, 0),
                    width,
                    new int[]{255, 255, 255},
                    new int[]{100, 100, 100},
                    new int[]{200, 200, 200}
            ));
        }
    }

    private void computeSeriesState() {
        for (int i = 0; i < complexes.size(); i++) {
            Complex complex = complexes.get(i);
            ComplexVisualizer complexVisualizer = complexVisualizers.get(i);
            complexVisualizer.setComplex(complex);
        }
        Vector2 prevEnd = Vector2.zero();
        complexVisualizers.get(0).setCenterLocation(prevEnd);
        for (int i = 1; i < complexes.size(); i++) {
            Complex prevComplex = complexes.get(i - 1);
            ComplexVisualizer complexVisualizer = complexVisualizers.get(i);
            Vector2 end = prevComplex.toVector2();
            Vector2 finalEnd = VectorMath.add(prevEnd, end);
            prevEnd = finalEnd;
            complexVisualizer.setCenterLocation(finalEnd);
        }
        Complex lastComplex = complexes.get(complexes.size() - 1);
        lastEndPosition = VectorMath.add(prevEnd, lastComplex.toVector2());
    }

    public Vector2 getLastEndPosition() {
        return lastEndPosition;
    }

    @Override
    public void render(Rendering rendering) {
        if (complexes.size() == 0) {
            return;
        }

        checkComplexVisualizersAmount();
        computeSeriesState();

        for (ComplexVisualizer complexVisualizer : complexVisualizers) {
            complexVisualizer.render(rendering);
        }
    }
}
