package io.github.darealturtywurty.turtychemistry.common.block.molder;

import io.github.darealturtywurty.turtychemistry.TurtyChemistry;
import io.github.darealturtywurty.turtychemistry.client.screens.MolderScreen;
import io.github.darealturtywurty.turtychemistry.common.block.entity.MolderBlockEntity;
import io.github.darealturtywurty.turtychemistry.common.menus.MolderMenu;
import io.github.darealturtywurty.turtychemistry.core.init.BlockEntityInit;
import net.minecraft.client.Minecraft;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.SimpleMenuProvider;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.BaseEntityBlock;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.HorizontalDirectionalBlock;
import net.minecraft.world.level.block.RenderShape;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.DirectionProperty;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraftforge.network.NetworkHooks;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public final class MolderBlock extends BaseEntityBlock {

    public static final DirectionProperty FACING = HorizontalDirectionalBlock.FACING;

    public MolderBlock(final Properties pProperties) {
        super(pProperties);
    }


    @Override
    protected void createBlockStateDefinition(final StateDefinition.Builder<Block, BlockState> pBuilder) {
        super.createBlockStateDefinition(pBuilder.add(FACING));
    }

    @Override
    public @NotNull InteractionResult use(final @NotNull BlockState pState, final @NotNull Level pLevel, final @NotNull BlockPos pPos, final @NotNull Player pPlayer, final @NotNull InteractionHand pHand, final @NotNull BlockHitResult pHit) {
        if (pLevel.isClientSide()) {
            if (pLevel.getBlockEntity(pPos) instanceof MolderBlockEntity molderBlockEntity) {
                //final SimpleMenuProvider menuProvider = new SimpleMenuProvider(
                //        (pContainerId, pPlayerInventory, pPlayer1) -> new MolderMenu(pContainerId, pPlayerInventory,
                //                molderBlockEntity.inventoryModule.getCapability(), pPos,
                //                molderBlockEntity.getContainerData()),
                //        Component.translatable("container." + TurtyChemistry.MODID + ".molder"));
                //NetworkHooks.openScreen((ServerPlayer) pPlayer,menuProvider,pPos);
                Minecraft.getInstance().setScreen(new MolderScreen(null,null, Component.translatable("container." + TurtyChemistry.MODID + ".molder")));
            }
        }
        return InteractionResult.sidedSuccess(pLevel.isClientSide());
    }

    @Override
    public @NotNull BlockState getStateForPlacement(final @NotNull BlockPlaceContext pContext) {
        return super.getStateForPlacement(pContext).setValue(FACING, pContext.getHorizontalDirection().getOpposite());
    }

    @Override
    public @NotNull RenderShape getRenderShape(final @NotNull BlockState pState) {
        return RenderShape.INVISIBLE;
    }

    @Nullable
    @Override
    public BlockEntity newBlockEntity(final @NotNull BlockPos pPos, final @NotNull BlockState pState) {
        return BlockEntityInit.MOLDER.get().create(pPos, pState);
    }
}
