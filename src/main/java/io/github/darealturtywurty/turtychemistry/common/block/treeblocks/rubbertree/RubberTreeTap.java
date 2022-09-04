package io.github.darealturtywurty.turtychemistry.common.block.treeblocks.rubbertree;

import io.github.darealturtywurty.turtychemistry.TurtyChemistry;
import io.github.darealturtywurty.turtychemistry.core.init.ItemInit;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import net.minecraft.world.level.material.FluidState;
import net.minecraft.world.phys.BlockHitResult;
import org.jetbrains.annotations.NotNull;

public final class RubberTreeTap extends Block {

    public static final IntegerProperty LATEX_AMOUNT = IntegerProperty.create("latex_amount", 0, 5);
    public static final Component TITLE = Component.literal("Rubber Tree Tap");

    public RubberTreeTap(final Properties properties) {
        super(properties);
    }


    @Override
    public @NotNull InteractionResult use(final @NotNull BlockState state, final Level level, final @NotNull BlockPos pos,
                                          final @NotNull Player player, final @NotNull InteractionHand hand, final @NotNull BlockHitResult result) {
        final Inventory playerInventory = player.getInventory();
        if (!level.isClientSide()) {
            TurtyChemistry.LOGGER.info("is crouching " + player.isCrouching());
            if (!player.isCrouching()) {
                if (state.getValue(LATEX_AMOUNT) > 0) {
                    playerInventory.setItem(playerInventory.getFreeSlot(), new ItemStack(ItemInit.LATEX.get()));
                    level.setBlockAndUpdate(pos, state.setValue(LATEX_AMOUNT, state.getValue(LATEX_AMOUNT) - 1));
                    player.displayClientMessage(Component.literal("Latex Left in Tap: " + state.getValue(LATEX_AMOUNT)), false);

                } else {
                    player.displayClientMessage(Component.literal("There Is No latex Left"), false);
                }
            } else {
                player.displayClientMessage(Component.literal("Latex Stored In Tap: " + state.getValue(LATEX_AMOUNT)), false);
            }
        }
        return InteractionResult.SUCCESS;
    }

    @Override
    public boolean onDestroyedByPlayer(final BlockState state, final Level level, final BlockPos pos, final Player player, final boolean willHarvest, final FluidState fluid) {
        final int currentLatexAmount = state.getValue(LATEX_AMOUNT);
        final Inventory playerInventory = player.getInventory();
        for (int i = 0; i < currentLatexAmount; i++) {
            playerInventory.setItem(playerInventory.getFreeSlot(), new ItemStack(ItemInit.LATEX.get()));
        }
        return super.onDestroyedByPlayer(state, level, pos, player, willHarvest, fluid);
    }


    @Override
    public BlockState getStateForPlacement(final @NotNull BlockPlaceContext ctx) {
        return super.getStateForPlacement(ctx).setValue(LATEX_AMOUNT, 0);
    }

    @Override
    protected void createBlockStateDefinition(final StateDefinition.Builder<Block, BlockState> stateBuilder) {
        super.createBlockStateDefinition(stateBuilder.add(LATEX_AMOUNT));
    }
}
