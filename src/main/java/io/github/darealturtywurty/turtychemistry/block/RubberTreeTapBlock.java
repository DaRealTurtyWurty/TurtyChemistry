package io.github.darealturtywurty.turtychemistry.block;

import io.github.darealturtywurty.turtychemistry.init.BlockInit;
import io.github.darealturtywurty.turtychemistry.init.ItemInit;
import io.github.darealturtywurty.turtychemistry.util.InventoryUtils;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import net.minecraft.world.level.material.FluidState;
import net.minecraft.world.phys.BlockHitResult;
import org.jetbrains.annotations.NotNull;

public final class RubberTreeTapBlock extends Block {
    public static final IntegerProperty LATEX_AMOUNT = IntegerProperty.create("latex_amount", 0, 5);

    public RubberTreeTapBlock(final Properties properties) {
        super(properties);
    }

    private static void processRubber(final BlockState treeState, final BlockState currentState, final Level level, final BlockPos currentPosition, final BlockPos rubberTreePosition) {
        final int treeRubberValue = treeState.getValue(StrippedRubberLogBlock.RUBBER_IN_TREE);
        if (treeRubberValue > 0 && currentState.getValue(LATEX_AMOUNT) != 5) {
            level.setBlockAndUpdate(currentPosition,
                    currentState.setValue(LATEX_AMOUNT, currentState.getValue(LATEX_AMOUNT) + 1));

            level.setBlockAndUpdate(rubberTreePosition, treeState.setValue(StrippedRubberLogBlock.RUBBER_IN_TREE,
                    treeState.getValue(StrippedRubberLogBlock.RUBBER_IN_TREE) - 1));
        }
    }

    // TODO: Fix these tooltips
    @Override
    public @NotNull InteractionResult use(final @NotNull BlockState state, final Level level, final @NotNull BlockPos pos, final @NotNull Player player, final @NotNull InteractionHand hand, final @NotNull BlockHitResult result) {
        final Inventory playerInventory = player.getInventory();
        if (!level.isClientSide()) {
            if (!player.isCrouching()) {
                if (state.getValue(LATEX_AMOUNT) > 0) {
                    playerInventory.setItem(playerInventory.getFreeSlot(), ItemInit.LATEX.get().getDefaultInstance());
                    level.setBlockAndUpdate(pos, state.setValue(LATEX_AMOUNT, state.getValue(LATEX_AMOUNT) - 1));
                    player.displayClientMessage(
                            Component.translatable("tap.interract.success", state.getValue(LATEX_AMOUNT)), false);
                } else {
                    player.displayClientMessage(Component.translatable("tap.interract.fail"), false);
                }
            } else {
                player.displayClientMessage(Component.translatable("tap.interract.check", state.getValue(LATEX_AMOUNT)),
                        false);
            }
        }

        return InteractionResult.SUCCESS;
    }

    @Override
    public boolean onDestroyedByPlayer(final BlockState state, final Level level, final BlockPos pos, final Player player, final boolean willHarvest, final FluidState fluid) {
        final int currentLatexAmount = state.getValue(LATEX_AMOUNT);
        InventoryUtils.dropItemsOnDestroy(level, ItemInit.LATEX.get().getDefaultInstance(), pos,
                currentLatexAmount);

        return super.onDestroyedByPlayer(state, level, pos, player, willHarvest, fluid);
    }

    @Override
    public void onRemove(final @NotNull BlockState state, final @NotNull Level level, final @NotNull BlockPos pos, final @NotNull BlockState newState, final boolean isMoving) {
        final int currentLatexAmount = state.getValue(LATEX_AMOUNT);
        for (int i = 0; i < currentLatexAmount; i++) {
            level.addFreshEntity(new ItemEntity(level, pos.getX(), pos.getY(), pos.getZ(),
                    ItemInit.LATEX.get().getDefaultInstance()));
        }

        super.onRemove(state, level, pos, newState, isMoving);
    }

    @Override
    public void randomTick(final @NotNull BlockState state, final @NotNull ServerLevel level, final @NotNull BlockPos pos, final @NotNull RandomSource source) {
        super.randomTick(state, level, pos, source);
        if (level.isClientSide()) return;

        for (final Direction cachedDirection : Direction.values()) {
            final BlockPos cachedRelativeBlockPosition = pos.relative(cachedDirection);
            final BlockState currentCheckedBlockState = level.getBlockState(cachedRelativeBlockPosition);
            if (currentCheckedBlockState.is(BlockInit.RUBBER_TREE_BLOCK_STRIPPED.get())) {
                processRubber(currentCheckedBlockState, state, level, pos, cachedRelativeBlockPosition);
            }
        }
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
