package io.github.darealturtywurty.turtychemistry.common.block.treeblocks;

import io.github.darealturtywurty.turtychemistry.TurtyChemistry;
import io.github.darealturtywurty.turtylib.client.util.MathUtils;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraftforge.common.Tags;
import net.minecraftforge.common.ToolAction;
import net.minecraftforge.common.ToolActions;
import org.apache.commons.lang3.RandomUtils;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Random;

public final class RubberTreeBlock extends RotatedPillarBlock {

    public static final IntegerProperty RUBBER_IN_TREE = IntegerProperty.create("rubber_in_tree",0,5);
    public static final BooleanProperty IS_STRIPPED = BooleanProperty.create("is_stripped");
    public static final BooleanProperty HAS_RUBBER = BooleanProperty.create("has_rubber");
    public RubberTreeBlock(final Properties blockProperty) {
        super(blockProperty);
        this.registerDefaultState(this.getStateDefinition().any().setValue(IS_STRIPPED,Boolean.FALSE).setValue(HAS_RUBBER,Boolean.TRUE).setValue(RUBBER_IN_TREE, new Random().nextInt(0, 5)));

    }

    @Override
    protected void createBlockStateDefinition(final StateDefinition.@NotNull Builder<Block, BlockState> stateBuilder) {
       super.createBlockStateDefinition(stateBuilder.add(RUBBER_IN_TREE).add(IS_STRIPPED).add(HAS_RUBBER));
    }

    @Override
    public @Nullable BlockState getToolModifiedState(final BlockState state, final UseOnContext context, final ToolAction toolAction, final boolean simulate) {

        if(toolAction == ToolActions.AXE_STRIP)
        {
            TurtyChemistry.LOGGER.info("strip");
            if(state.hasProperty(IS_STRIPPED) && !state.getValue(IS_STRIPPED)) {
                TurtyChemistry.LOGGER.info("I have this state");
               return state.setValue(IS_STRIPPED, true);
            }
        }
        return super.getToolModifiedState(state, context, toolAction, simulate);
    }



    public BlockState processRubber(final Block treeTapState)
    {
        final int currentRubberValue = this.getStateDefinition().any().getValue(RUBBER_IN_TREE);
        final boolean hasRubber = this.getStateDefinition().any().getValue(HAS_RUBBER);
        final boolean isStripped = this.getStateDefinition().any().getValue(IS_STRIPPED);
        if(currentRubberValue != 0 && hasRubber)
        {
            //TODO: check for the treesap

            return this.getStateDefinition().any().setValue(RUBBER_IN_TREE,currentRubberValue - 1);
        }
        return null;
    }

}
