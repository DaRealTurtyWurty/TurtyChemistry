package io.github.darealturtywurty.turtychemistry.common.block.treeblocks;

import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.RotatedPillarBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraftforge.common.ToolAction;
import net.minecraftforge.common.ToolActions;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public final class RubberTreeBlockStripped extends RotatedPillarBlock {
    public RubberTreeBlockStripped(final Properties properties) {
        super(properties);
    }


    @Override
    protected void createBlockStateDefinition(final StateDefinition.@NotNull Builder<Block, BlockState> stateBuilder) {
        super.createBlockStateDefinition(stateBuilder.add(RubberTreeBlock.RUBBER_IN_TREE).add(RubberTreeBlock.HAS_RUBBER));
    }

    @Override
    public @Nullable BlockState getToolModifiedState(final BlockState state, final UseOnContext context, final ToolAction toolAction, final boolean simulate) {
        if(toolAction == ToolActions.AXE_STRIP)
        {
            return null;
        }
        return super.getToolModifiedState(state, context, toolAction, simulate);
    }

    public BlockState processRubber(final Block treeTapState)
    {
        final int currentRubberValue = this.getStateDefinition().any().getValue(RubberTreeBlock.RUBBER_IN_TREE);
        final boolean hasRubber = this.getStateDefinition().any().getValue(RubberTreeBlock.HAS_RUBBER);

        if(currentRubberValue != 0 && hasRubber)
        {
            //TODO: check for the treesap

            return this.getStateDefinition().any().setValue(RubberTreeBlock.RUBBER_IN_TREE,currentRubberValue - 1);
        }
        return null;
    }


}
