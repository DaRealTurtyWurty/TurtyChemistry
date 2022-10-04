package io.github.darealturtywurty.turtychemistry.client.util;

import io.github.darealturtywurty.turtychemistry.init.BlockInit;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;

public enum MoldingPatternVertexHolders {
    // TODO: Define each vertex to match the specified pattern
    PATTERN1(new PatternVertex[]{}, Component.empty(), new ItemStack(Items.IRON_INGOT)), PATTERN2(new PatternVertex[]{},
            Component.empty(), new ItemStack(Items.HONEY_BLOCK)), PATTERN3(new PatternVertex[]{}, Component.empty(),
            new ItemStack(BlockInit.RUBBER_TREE_SAPLING.get())), PATTERN4(new PatternVertex[]{}, Component.empty(),
            new ItemStack(Items.IRON_INGOT));

    private final PatternVertex[] vertices;
    private final Component message;
    private final ItemStack patternStack;

    MoldingPatternVertexHolders(final PatternVertex[] vertices, final Component message, final ItemStack stack) {
        this.vertices = vertices;
        this.message = message;
        this.patternStack = stack;
    }

    public ItemStack getPatternStack() {
        return this.patternStack;
    }

    public Component getMessage() {
        return this.message;
    }

    public PatternVertex[] getVertices() {
        return this.vertices;
    }
}
