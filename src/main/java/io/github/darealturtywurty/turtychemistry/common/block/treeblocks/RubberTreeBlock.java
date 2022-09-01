package io.github.darealturtywurty.turtychemistry.common.block.treeblocks;

import io.github.darealturtywurty.turtychemistry.core.init.BlockInit;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.RotatedPillarBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import net.minecraftforge.common.ToolAction;
import net.minecraftforge.common.ToolActions;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Random;

public final class RubberTreeBlock extends RotatedPillarBlock {

    public static final IntegerProperty RUBBER_IN_TREE = IntegerProperty.create("rubber_in_tree", 0, 5);
    public static final BooleanProperty HAS_RUBBER = BooleanProperty.create("has_rubber");

    public RubberTreeBlock(final Properties blockProperty) {
        super(blockProperty);
        this.registerDefaultState(this.getStateDefinition().any().setValue(HAS_RUBBER, Boolean.TRUE).setValue(RUBBER_IN_TREE,
                ThreadLocalRandom.current().nextInt(0, 5)));
    }

    @Override
    protected void createBlockStateDefinition(final StateDefinition.@NotNull Builder<Block, BlockState> stateBuilder) {
        super.createBlockStateDefinition(stateBuilder.add(RUBBER_IN_TREE).add(HAS_RUBBER));
    }

    @Override
    public @Nullable BlockState getToolModifiedState(final BlockState state, final UseOnContext context, final ToolAction toolAction, final boolean simulate) {
        if (toolAction == ToolActions.AXE_STRIP) {
            return BlockInit.RUBBER_TREE_BLOCK_STRIPPED.get().defaultBlockState().setValue(RUBBER_IN_TREE, state.getValue(RUBBER_IN_TREE))
                    .setValue(HAS_RUBBER, state.getValue(HAS_RUBBER)).setValue(AXIS, state.getValue(AXIS));
        }
        return super.getToolModifiedState(state, context, toolAction, simulate);
    }

    public BlockState processRubber(final Block treeTapState) {
        final int currentRubberValue = this.getStateDefinition().any().getValue(RUBBER_IN_TREE);
        final boolean hasRubber = this.getStateDefinition().any().getValue(HAS_RUBBER);
        if (currentRubberValue != 0 && hasRubber) {
            //TODO: check for the treesap
            return this.getStateDefinition().any().setValue(RUBBER_IN_TREE, currentRubberValue - 1);
        }
        return null;
    }

}
