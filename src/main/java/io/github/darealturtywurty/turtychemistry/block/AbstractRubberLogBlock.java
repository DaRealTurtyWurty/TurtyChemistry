package io.github.darealturtywurty.turtychemistry.block;

import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.RotatedPillarBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import org.jetbrains.annotations.NotNull;

public abstract class AbstractRubberLogBlock extends RotatedPillarBlock {
    public static final IntegerProperty RUBBER_IN_TREE = IntegerProperty.create("rubber_in_tree", 0, 5);

    public AbstractRubberLogBlock(final Properties properties) {
        super(properties);
    }

    @Override
    protected void createBlockStateDefinition(final StateDefinition.@NotNull Builder<Block, BlockState> stateBuilder) {
        super.createBlockStateDefinition(stateBuilder.add(RUBBER_IN_TREE));
    }
}
