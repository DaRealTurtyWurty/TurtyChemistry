package io.github.darealturtywurty.turtychemistry.block;

import io.github.darealturtywurty.turtychemistry.init.BlockInit;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.common.ToolAction;
import net.minecraftforge.common.ToolActions;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.concurrent.ThreadLocalRandom;

public final class RubberLogBlock extends AbstractRubberLogBlock {
    public RubberLogBlock(final Properties properties) {
        super(properties);
    }

    @Override
    public @Nullable BlockState getToolModifiedState(final BlockState state, final UseOnContext context, final ToolAction toolAction, final boolean simulate) {
        if (toolAction == ToolActions.AXE_STRIP) {
            return BlockInit.RUBBER_TREE_BLOCK_STRIPPED.get().defaultBlockState()
                    .setValue(RUBBER_IN_TREE, state.getValue(RUBBER_IN_TREE)).setValue(AXIS, state.getValue(AXIS));
        }

        return super.getToolModifiedState(state, context, toolAction, simulate);
    }

    @Override
    public BlockState getStateForPlacement(final @NotNull BlockPlaceContext p_55928_) {
        return super.getStateForPlacement(p_55928_).setValue(RUBBER_IN_TREE, ThreadLocalRandom.current().nextInt(1, 5));
    }
}
