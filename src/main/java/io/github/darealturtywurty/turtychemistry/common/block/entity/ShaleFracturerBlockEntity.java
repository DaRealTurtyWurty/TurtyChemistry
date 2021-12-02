package io.github.darealturtywurty.turtychemistry.common.block.entity;

import java.util.ArrayList;
import java.util.List;

import io.github.darealturtywurty.turtychemistry.TurtyChemistry;
import io.github.darealturtywurty.turtychemistry.core.init.BlockEntityInit;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityTicker;
import net.minecraft.world.level.block.state.BlockState;

public class ShaleFracturerBlockEntity extends InventoryTile implements BlockEntityTicker {
    public final List<BlockPos> drillPositions = new ArrayList<>();

    public ShaleFracturerBlockEntity(BlockPos pos, BlockState state) {
        super(BlockEntityInit.SHALE_FRACTURER.get(), pos, state, 0);
    }

    public Component getTitle() {
        return new TranslatableComponent("container." + TurtyChemistry.MODID + ".shale_fracturer");
    }

    public boolean shouldShowPlayerInv() {
        return true;
    }

    @Override
    public void tick(Level level, BlockPos pos, BlockState state, BlockEntity blockEntity) {

    }

    public static class DrillBlockEntity extends BlockEntity implements BlockEntityTicker {
        public DrillBlockEntity(BlockPos pos, BlockState state) {
            super(BlockEntityInit.SHALE_DRILL.get(), pos, state);
        }

        @Override
        public void tick(Level level, BlockPos pos, BlockState state, BlockEntity blockEntity) {

        }
    }
}
