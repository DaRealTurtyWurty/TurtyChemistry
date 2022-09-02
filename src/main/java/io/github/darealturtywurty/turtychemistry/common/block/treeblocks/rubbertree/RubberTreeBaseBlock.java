package io.github.darealturtywurty.turtychemistry.common.block.treeblocks.rubbertree;

import io.github.darealturtywurty.turtychemistry.core.init.BlockInit;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.Level;
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
        this.registerDefaultState(this.getStateDefinition().any().setValue(HAS_RUBBER, Boolean.TRUE).setValue(RUBBER_IN_TREE, 5));
    }

    @Override
    protected void createBlockStateDefinition(final StateDefinition.@NotNull Builder<Block, BlockState> stateBuilder) {
        super.createBlockStateDefinition(stateBuilder.add(RUBBER_IN_TREE).add(HAS_RUBBER));
    }

    @Override
    public void randomTick(final @NotNull BlockState state, final @NotNull ServerLevel level, final @NotNull BlockPos pos, final @NotNull RandomSource source) {
        super.randomTick(state, level, pos, source);
        //cache the direction enum array
        final Direction[] cachedDirections = Direction.values();
        for (final Direction cachedDirection : cachedDirections) {
            final BlockState currentCheckedBlockState = level.getBlockState(pos.relative(cachedDirection));
            //TODO:replace the placeholder block with the actual treetap
            if (currentCheckedBlockState == BlockInit.PLACEHOLDER_BLOCK.get().defaultBlockState()) {
                processRubber(currentCheckedBlockState, state, level, pos);
            }
        }
    }

    public void processRubber(final BlockState treeTapState, final BlockState currentState, final Level level, final BlockPos pos) {
        final int currentRubberValue = currentState.getValue(RUBBER_IN_TREE);
        final boolean hasRubber = currentState.getValue(HAS_RUBBER);
        if (currentRubberValue != 0 && hasRubber && !level.isClientSide() /*&& treTapState.getValue(TreeTap.IS_TAPPING)*/) {
            level.setBlockAndUpdate(pos, currentState.setValue(RUBBER_IN_TREE, currentRubberValue - 1));
        }
    }
}
