package io.github.darealturtywurty.turtychemistry.block;

import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.common.ToolAction;
import net.minecraftforge.common.ToolActions;
import org.jetbrains.annotations.Nullable;

// TODO: Consider removal. This class is not used.
public final class StrippedRubberLogBlock extends AbstractRubberLogBlock {
    public StrippedRubberLogBlock(final Properties properties) {
        super(properties);
    }

    @Override
    public @Nullable BlockState getToolModifiedState(final BlockState state, final UseOnContext context, final ToolAction toolAction, final boolean simulate) {
        if (toolAction == ToolActions.AXE_STRIP) {
            return null;
        }

        return super.getToolModifiedState(state, context, toolAction, simulate);
    }
}
