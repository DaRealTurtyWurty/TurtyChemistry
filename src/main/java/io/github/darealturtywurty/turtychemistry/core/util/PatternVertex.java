package io.github.darealturtywurty.turtychemistry.core.util;

public final class PatternVertex {
    public final float x, y, z;
    private boolean selected;
    private final boolean isFinalVertex;
    PatternVertex(final float x, final float y, final float z,final boolean isFinalVertex) {
        this.x = x;
        this.y = y;
        this.z = z;
        this.selected = false;
        this.isFinalVertex = isFinalVertex;
    }

    PatternVertex(final float x, final float y,final boolean isFinalVertex) {
        this(x, y, 0,isFinalVertex);
    }

    public boolean isFinalVertex() {
        return isFinalVertex;
    }

    public boolean isSelected() {
        return selected;
    }

    public void setSelected(final boolean selected) {
        this.selected = selected;
    }
}
