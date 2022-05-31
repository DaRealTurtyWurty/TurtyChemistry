package io.github.darealturtywurty.turtychemistry.common.block;

import io.github.darealturtywurty.turtychemistry.common.block.core.ChemistryBlock;
import io.github.darealturtywurty.turtychemistry.common.block.core.TickingMachineBlock;
import io.github.darealturtywurty.turtychemistry.common.block.entity.ShaleFracturerBlockEntity;
import io.github.darealturtywurty.turtychemistry.common.block.entity.ShaleFracturerBlockEntity.DrillBlockEntity;
import io.github.darealturtywurty.turtychemistry.common.container.ShaleFracturerContainer;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.MenuProvider;
import net.minecraft.world.SimpleMenuProvider;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityTicker;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraftforge.network.NetworkHooks;

public class ShaleFracturerBlock<Type extends ShaleFracturerBlockEntity> extends TickingMachineBlock<Type> {
    public ShaleFracturerBlock(ChemistryBlock.Builder builder) {
        super(builder);
    }

    @SuppressWarnings("unchecked")
    @Override
    public BlockEntityTicker<Type> getTickMethod(Type blockEntity) {
        return (BlockEntityTicker) blockEntity;
    }

    @Override
    public BlockEntity newBlockEntity(BlockPos pos, BlockState state) {
        return new ShaleFracturerBlockEntity(pos, state);
    }

    @Override
    public InteractionResult use(BlockState state, Level level, BlockPos pos, Player player, InteractionHand hand,
            BlockHitResult result) {
        if (!level.isClientSide
                && level.getBlockEntity(pos) instanceof final ShaleFracturerBlockEntity shaleFracturer) {
            final MenuProvider container = new SimpleMenuProvider(
                    ShaleFracturerContainer.getServerContainer(shaleFracturer, pos), shaleFracturer.getTitle());
            NetworkHooks.openGui((ServerPlayer) player, container, pos);
        }

        return InteractionResult.SUCCESS;
    }

    public static class DrillBlock<Type extends DrillBlockEntity> extends TickingMachineBlock<Type> {
        public DrillBlock(Builder builder) {
            super(builder);
        }

        @SuppressWarnings("unchecked")
        @Override
        public BlockEntityTicker<Type> getTickMethod(Type blockEntity) {
            return (BlockEntityTicker) blockEntity;
        }

        @Override
        public BlockEntity newBlockEntity(BlockPos pos, BlockState state) {
            return new DrillBlockEntity(pos, state);
        }
    }
}
