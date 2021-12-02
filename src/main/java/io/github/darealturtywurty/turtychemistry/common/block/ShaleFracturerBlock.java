package io.github.darealturtywurty.turtychemistry.common.block;

import io.github.darealturtywurty.turtychemistry.common.block.core.ChemistryBlock;
import io.github.darealturtywurty.turtychemistry.common.block.core.TickingMachineBlock;
import io.github.darealturtywurty.turtychemistry.common.block.entity.ShaleFracturerBlockEntity;
import io.github.darealturtywurty.turtychemistry.common.block.entity.ShaleFracturerBlockEntity.DrillBlockEntity;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityTicker;
import net.minecraft.world.level.block.state.BlockState;

public class ShaleFracturerBlock<Type extends ShaleFracturerBlockEntity> extends TickingMachineBlock<Type> {
    public ShaleFracturerBlock(ChemistryBlock.Builder builder) {
        super(builder);
    }

    @SuppressWarnings("unchecked")
    @Override
    public BlockEntityTicker<Type> getTickMethod(Type blockEntity) {
        return blockEntity;
    }

    @Override
    public BlockEntity newBlockEntity(BlockPos pos, BlockState state) {
        return new ShaleFracturerBlockEntity(pos, state);
    }

    public static class DrillBlock<Type extends DrillBlockEntity> extends TickingMachineBlock<Type> {
        public DrillBlock(Builder builder) {
            super(builder);
        }

        @SuppressWarnings("unchecked")
        @Override
        public BlockEntityTicker<Type> getTickMethod(Type blockEntity) {
            return blockEntity;
        }

        @Override
        public BlockEntity newBlockEntity(BlockPos pos, BlockState state) {
            return new DrillBlockEntity(pos, state);
        }
    }
}
