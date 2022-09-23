package io.github.darealturtywurty.turtychemistry.core.util;

import io.github.darealturtywurty.turtychemistry.core.init.BlockInit;
import io.github.darealturtywurty.turtychemistry.core.init.ItemInit;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;

public enum MoldingPatternVertexHolders {
    //TODO:define each vertex to match the specified pattern
    PATTERN1(new PatternVertex[]{
    }, Component.empty(),new ItemStack(Items.IRON_INGOT)),
    PATTERN2(new PatternVertex[]{
    }, Component.empty(),new ItemStack(Items.HONEY_BLOCK)),
    PATTERN3(new PatternVertex[]{
    }, Component.empty(),new ItemStack(BlockInit.RUBBER_TREE_SAPLING.get())),
    PATTERN4(new PatternVertex[]{
    }, Component.empty(),new ItemStack(Items.IRON_INGOT));

    private final PatternVertex[] vertices;
    private final Component message;
    private final ItemStack patternStack;
    MoldingPatternVertexHolders(final PatternVertex[] vertices, final Component message,final ItemStack stack) {
        this.vertices = vertices;
        this.message = message;
        patternStack = stack;
    }

    public ItemStack getPatternStack() {
        return patternStack;
    }

    public Component getMessage() {
        return message;
    }

    public PatternVertex[] getVertices() {
        return vertices;
    }
}
