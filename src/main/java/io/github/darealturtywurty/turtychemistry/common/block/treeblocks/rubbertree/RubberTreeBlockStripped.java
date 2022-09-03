package io.github.darealturtywurty.turtychemistry.common.block.treeblocks.rubbertree;

import io.github.darealturtywurty.turtychemistry.core.init.BlockInit;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.common.ToolAction;
import net.minecraftforge.common.ToolActions;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public final class RubberTreeBlockStripped extends RubberTreeBaseBlock {
    public RubberTreeBlockStripped(final Properties properties) {
        super(properties);
    }


    @Override
    public @Nullable BlockState getToolModifiedState(final BlockState state, final UseOnContext context, final ToolAction toolAction, final boolean simulate) {
        if (toolAction == ToolActions.AXE_STRIP) {
            return null;
        }

        return super.getToolModifiedState(state, context, toolAction, simulate);
    }

    @Override
    public void randomTick(final @NotNull BlockState state, final @NotNull ServerLevel level, final @NotNull BlockPos pos, final @NotNull RandomSource source) {
        super.randomTick(state, level, pos, source);
        if (state.getValue(HAS_RUBBER) && !level.isClientSide()) {
            for (final Direction cachedDirection : Direction.values()) {
                final BlockState currentCheckedBlockState = level.getBlockState(pos.relative(cachedDirection));
                //TODO:replace the placeholder block with the actual treetap
                if (currentCheckedBlockState.is(BlockInit.RUBBER_TREE_SLAB.get())) {
                    processRubber(currentCheckedBlockState, state, level, pos);
                }
            }
        }
    }

    private static void processRubber(final BlockState treeTapState, final BlockState currentState, final Level level, final BlockPos pos) {
        final int currentRubberValue = currentState.getValue(RUBBER_IN_TREE);
        if (currentRubberValue != 0 /*&& treeTapState.getValue(TreeTap.IS_TAPPING)*/) {
            level.setBlockAndUpdate(pos, currentState.setValue(RUBBER_IN_TREE, currentRubberValue - 1));
        } else {
            level.setBlockAndUpdate(pos, currentState.setValue(HAS_RUBBER, false));
        }
    }
}
