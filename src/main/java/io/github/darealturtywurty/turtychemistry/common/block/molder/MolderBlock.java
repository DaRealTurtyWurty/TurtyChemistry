package io.github.darealturtywurty.turtychemistry.common.block.molder;

import io.github.darealturtywurty.turtychemistry.TurtyChemistry;
import io.github.darealturtywurty.turtychemistry.client.screens.MolderScreen;
import io.github.darealturtywurty.turtychemistry.common.block.entity.MolderBlockEntity;
import io.github.darealturtywurty.turtychemistry.core.init.BlockEntityInit;
import io.github.darealturtywurty.turtychemistry.core.util.StaticReusableMethods;
import net.minecraft.client.Minecraft;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.network.chat.Component;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.block.state.properties.DirectionProperty;
import net.minecraft.world.level.material.FluidState;
import net.minecraft.world.level.material.Fluids;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.BooleanOp;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;
import net.minecraftforge.common.Tags;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public final class MolderBlock extends BaseEntityBlock implements SimpleWaterloggedBlock {
    public static final DirectionProperty FACING = HorizontalDirectionalBlock.FACING;
    private static final BooleanProperty IS_WATERLOGGED = BlockStateProperties.WATERLOGGED;
    private static final VoxelShape thisShape;

    static {
        VoxelShape shape = Shapes.empty();
        shape = Shapes.join(shape, Shapes.box(0.8125, 0, 0, 1, 0.875, 0.1875), BooleanOp.OR);
        shape = Shapes.join(shape, Shapes.box(0.8125, 0.875, 0, 1, 1, 0.1875), BooleanOp.OR);
        shape = Shapes.join(shape, Shapes.box(0, 0.875, 0, 0.1875, 1, 0.1875), BooleanOp.OR);
        shape = Shapes.join(shape, Shapes.box(0, 0, 0, 0.1875, 0.875, 0.1875), BooleanOp.OR);
        shape = Shapes.join(shape, Shapes.box(0, 0.875, 0.8125, 0.1875, 1, 1), BooleanOp.OR);
        shape = Shapes.join(shape, Shapes.box(0, 0, 0.8125, 0.1875, 0.875, 1), BooleanOp.OR);
        shape = Shapes.join(shape, Shapes.box(0.8125, 0.875, 0.8125, 1, 1, 1), BooleanOp.OR);
        shape = Shapes.join(shape, Shapes.box(0.8125, 0, 0.8125, 1, 0.875, 1), BooleanOp.OR);
        shape = Shapes.join(shape, Shapes.box(0.8125, 0.875, 0.1875, 1, 1, 0.8125), BooleanOp.OR);
        shape = Shapes.join(shape, Shapes.box(0, 0.875, 0.1875, 0.1875, 1, 0.8125), BooleanOp.OR);
        shape = Shapes.join(shape, Shapes.box(0.1875, 0.875, 0.8125, 0.8125, 1, 1), BooleanOp.OR);
        shape = Shapes.join(shape, Shapes.box(0.1875, 0.875, 0, 0.8125, 1, 0.1875), BooleanOp.OR);
        shape = Shapes.join(shape, Shapes.box(0.1875, 0.875, 0.1875, 0.8125, 0.9375, 0.8125), BooleanOp.OR);
        thisShape = shape.optimize();
    }

    public MolderBlock(final Properties pProperties) {
        super(pProperties);
        this.registerDefaultState(
                this.getStateDefinition().any().setValue(FACING, Direction.NORTH).setValue(IS_WATERLOGGED, false));
    }

    @Override
    protected void createBlockStateDefinition(final StateDefinition.Builder<Block, BlockState> pBuilder) {
        super.createBlockStateDefinition(pBuilder.add(FACING).add(IS_WATERLOGGED));
    }

    @Override
    public @NotNull FluidState getFluidState(final BlockState pState) {
        return pState.getValue(IS_WATERLOGGED) ? Fluids.WATER.getSource(false) : super.getFluidState(pState);
    }

    @Override
    public @NotNull BlockState rotate(final BlockState pState, final Rotation pRotation) {
        return pState.setValue(FACING, pRotation.rotate(pState.getValue(FACING)));
    }

    @Override
    public @NotNull BlockState mirror(final BlockState pState, final Mirror pMirror) {
        return pState.rotate(pMirror.getRotation(pState.getValue(FACING)));
    }

    @Override
    public @NotNull InteractionResult use(final @NotNull BlockState pState, final @NotNull Level pLevel, final @NotNull BlockPos pPos, final @NotNull Player pPlayer, final @NotNull InteractionHand pHand, final @NotNull BlockHitResult pHit) {
        final ItemStack heldItem = pPlayer.getItemInHand(pHand);
        if (pLevel.getBlockEntity(pPos) instanceof final MolderBlockEntity molderBlockEntity) {
            if (!pPlayer.isCrouching()) {
                if (pLevel.isClientSide() && !heldItem.is(Tags.Items.INGOTS_IRON) && molderBlockEntity.hasItem()) {
                    Minecraft.getInstance().setScreen(
                            new MolderScreen(Component.translatable("container." + TurtyChemistry.MODID + ".molder"),
                                    pPos, molderBlockEntity));
                } else if (!molderBlockEntity.hasItem() && heldItem.is(Tags.Items.INGOTS_IRON)) {
                    molderBlockEntity.insertItem(heldItem.split(1));
                } else {
                    return InteractionResult.PASS;
                }
            } else {
                if (molderBlockEntity.hasItem()) {
                    pPlayer.setItemInHand(pHand, molderBlockEntity.removeItemOutOfSlot());
                } else {
                    return InteractionResult.PASS;
                }
            }
        }

        return InteractionResult.sidedSuccess(pLevel.isClientSide());
    }

    @Override
    public @NotNull BlockState getStateForPlacement(final @NotNull BlockPlaceContext pContext) {
        final boolean isInWater = pContext.getLevel().getFluidState(pContext.getClickedPos()).getType() == Fluids.WATER;
        return super.getStateForPlacement(pContext).setValue(FACING, pContext.getHorizontalDirection())
                .setValue(IS_WATERLOGGED, isInWater);
    }

    @Override
    public boolean onDestroyedByPlayer(final BlockState state, final Level level, final BlockPos pos, final Player player, final boolean willHarvest, final FluidState fluid) {
        if (level.getBlockEntity(pos) instanceof final MolderBlockEntity molderBlockEntity) {
            StaticReusableMethods.dropItemsOnDestroy(level, molderBlockEntity.getItem(), pos);
        }
        return super.onDestroyedByPlayer(state, level, pos, player, willHarvest, fluid);
    }

    @Override
    public @NotNull VoxelShape getShape(final @NotNull BlockState pState, final @NotNull BlockGetter pLevel, final @NotNull BlockPos pPos, final @NotNull CollisionContext pContext) {
        return thisShape;
    }

    @Override
    public @NotNull RenderShape getRenderShape(final @NotNull BlockState pState) {
        return RenderShape.MODEL;
    }

    @Nullable
    @Override
    public BlockEntity newBlockEntity(final @NotNull BlockPos pPos, final @NotNull BlockState pState) {
        return BlockEntityInit.MOLDER.get().create(pPos, pState);
    }
}
