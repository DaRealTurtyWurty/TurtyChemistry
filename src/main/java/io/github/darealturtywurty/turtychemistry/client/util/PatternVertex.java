package io.github.darealturtywurty.turtychemistry.client.util;

public final class PatternVertex {
    public final float x, y, z;
    private final boolean isFinalVertex, isFirstVertex;
    private boolean selected;

    PatternVertex(final float x, final float y, final float z, final boolean isFirstVertex, final boolean isFinalVertex) {
        this.x = x;
        this.y = y;
        this.z = z;
        this.selected = false;
        this.isFinalVertex = isFinalVertex;
        this.isFirstVertex = isFirstVertex;
    }

    PatternVertex(final float x, final float y, final boolean isFirstVertex, final boolean isFinalVertex) {
        this(x, y, 0, isFirstVertex, isFinalVertex);
    }

    public boolean isFinalVertex() {
        return this.isFinalVertex;
    }

    public boolean isSelected() {
        return this.selected;
    }

    public void setSelected(final boolean selected) {
        this.selected = selected;
    }

    public boolean isFirstVertex() {
        return this.isFirstVertex;
    }
}
