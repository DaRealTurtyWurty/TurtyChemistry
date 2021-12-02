package io.github.darealturtywurty.turtychemistry.common.block.core;

import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityTicker;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;

public abstract class TickingMachineBlock<Type extends BlockEntity> extends MachineBlock<Type> {

    public TickingMachineBlock(ChemistryBlock.Builder builder) {
        super(builder);
    }

    @SuppressWarnings("unchecked")
    @Override
    public <BE extends BlockEntity> BlockEntityTicker<BE> getTicker(Level level, BlockState state,
            BlockEntityType<BE> type) {
        return level.isClientSide ? null
                : (level0, pos, state0, blockEntity) -> getTickMethod((Type) blockEntity);
    }

    public abstract BlockEntityTicker<Type> getTickMethod(Type blockEntity);
}
