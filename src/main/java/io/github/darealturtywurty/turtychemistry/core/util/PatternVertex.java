package io.github.darealturtywurty.turtychemistry.core.util;

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
        return isFinalVertex;
    }
    public boolean isSelected() {
        return selected;
    }
    public boolean isFirstVertex() {
        return isFirstVertex;
    }
    public void setSelected(final boolean selected) {
        this.selected = selected;
    }
}
