package io.github.darealturtywurty.turtychemistry.common.block.entity;

import java.util.ArrayList;
import java.util.List;

import io.github.darealturtywurty.turtychemistry.TurtyChemistry;
import io.github.darealturtywurty.turtychemistry.core.init.BlockEntityInit;
import io.github.darealturtywurty.turtychemistry.core.init.PacketHandler;
import io.github.darealturtywurty.turtychemistry.network.packet.ServerboundShaleFracturerIntegerPacket;
import io.github.darealturtywurty.turtychemistry.network.packet.ServerboundShaleFracturerIntegerPacket.Type;
import io.github.darealturtywurty.turtychemistry.network.packet.ServerboundShaleFracturerPlayerInvPacket;
import io.github.darealturtywurty.turtylib.common.blockentity.ModularBlockEntity;
import io.github.darealturtywurty.turtylib.common.blockentity.module.InventoryModule;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.world.level.block.state.BlockState;

public class ShaleFracturerBlockEntity extends ModularBlockEntity {
    public static final int MIN_SPEED = 1, MIN_ENERGY_INPUT = 1, MIN_WATER_INPUT = 1, MAX_SPEED = 200,
        MAX_ENERGY_INPUT = 100, MAX_WATER_INPUT = 100;
    public final List<BlockPos> drillPositions = new ArrayList<>();
    public boolean shouldShowPlayerInv;
    public int speed = 100, energyInputLimit = 100, waterInputLimit = 100;
    public final InventoryModule inventory;

    public ShaleFracturerBlockEntity(BlockPos pos, BlockState state) {
        super(BlockEntityInit.SHALE_FRACTURER.get(), pos, state);
        this.inventory = addModule(new InventoryModule(this, 1));
    }

    public Component getTitle() {
        return Component.translatable("container." + TurtyChemistry.MODID + ".shale_fracturer");
    }

    @Override
    public void load(CompoundTag compound) {
        super.load(compound);
        this.speed = compound.getInt("Speed");
        this.energyInputLimit = compound.getInt("EnergyInputLimit");
        this.waterInputLimit = compound.getInt("WaterInputLimit");
    }
    
    public void setEnergyInputLimit(int energy) {
        this.energyInputLimit = energy;
        if (this.level.isClientSide) {
            PacketHandler.CHANNEL.sendToServer(
                new ServerboundShaleFracturerIntegerPacket(this.worldPosition, Type.ENERGY_INPUT_LIMIT, energy));
        }
    }

    public void setShowPlayerInv(boolean show) {
        this.shouldShowPlayerInv = show;
        if (this.level.isClientSide) {
            PacketHandler.CHANNEL.sendToServer(new ServerboundShaleFracturerPlayerInvPacket(this.worldPosition, show));
        }
    }

    public void setSpeed(int speed) {
        this.speed = speed;
        if (this.level.isClientSide) {
            PacketHandler.CHANNEL
                .sendToServer(new ServerboundShaleFracturerIntegerPacket(this.worldPosition, Type.SPEED, speed));
        }
    }

    public void setWaterInputLimit(int water) {
        this.waterInputLimit = water;
        if (this.level.isClientSide) {
            PacketHandler.CHANNEL.sendToServer(
                new ServerboundShaleFracturerIntegerPacket(this.worldPosition, Type.WATER_INPUT_LIMIT, water));
        }
    }

    @Override
    protected void saveAdditional(CompoundTag nbt) {
        super.saveAdditional(nbt);
        nbt.putInt("Speed", this.speed);
        nbt.putInt("EnergyInputLimit", this.energyInputLimit);
        nbt.putInt("WaterInputLimit", this.waterInputLimit);
    }

    public static class DrillBlockEntity extends ModularBlockEntity {
        public DrillBlockEntity(BlockPos pos, BlockState state) {
            super(BlockEntityInit.SHALE_DRILL.get(), pos, state);
        }
    }
}
