package it.nicoloscialpi.fouriervisualizer.engine.rendering.interfaces;

import it.nicoloscialpi.fouriervisualizer.engine.rendering.elements.Rendering;

import java.util.Objects;
import java.util.UUID;

public abstract class RenderingObject {

    public UUID renderingUuid;

    public RenderingObject() {
        this.renderingUuid = UUID.randomUUID();
    }

    public abstract void render(Rendering rendering);

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof RenderingObject that)) return false;
        return Objects.equals(renderingUuid, that.renderingUuid);
    }

    @Override
    public int hashCode() {
        return Objects.hash(renderingUuid);
    }
}
