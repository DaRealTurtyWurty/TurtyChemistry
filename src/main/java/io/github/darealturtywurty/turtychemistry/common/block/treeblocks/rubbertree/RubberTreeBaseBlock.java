package io.github.darealturtywurty.turtychemistry.common.block.treeblocks.rubbertree;

import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.RotatedPillarBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import org.jetbrains.annotations.NotNull;

public sealed class RubberTreeBaseBlock extends RotatedPillarBlock permits RubberTreeBlock, RubberTreeBlockStripped {
    public static final IntegerProperty RUBBER_IN_TREE = IntegerProperty.create("rubber_in_tree", 0, 5);
    public static final BooleanProperty HAS_RUBBER = BooleanProperty.create("has_rubber");

    public RubberTreeBaseBlock(final Properties property) {
        super(property.randomTicks());
    }

    @Override
    protected void createBlockStateDefinition(final StateDefinition.@NotNull Builder<Block, BlockState> stateBuilder) {
        super.createBlockStateDefinition(stateBuilder.add(RUBBER_IN_TREE).add(HAS_RUBBER));
    }
}
