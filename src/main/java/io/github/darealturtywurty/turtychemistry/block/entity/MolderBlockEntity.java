package io.github.darealturtywurty.turtychemistry.block.entity;

import io.github.darealturtywurty.turtychemistry.TurtyChemistry;
import io.github.darealturtywurty.turtychemistry.init.BlockEntityInit;
import io.github.darealturtywurty.turtylib.common.blockentity.ModularBlockEntity;
import io.github.darealturtywurty.turtylib.common.blockentity.module.InventoryModule;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.state.BlockState;
import org.jetbrains.annotations.NotNull;

public final class MolderBlockEntity extends ModularBlockEntity {
    public static final Component TITLE = Component.translatable("container." + TurtyChemistry.MODID + ".molder");

    public final InventoryModule inventory;

    public MolderBlockEntity(final BlockPos pPos, final BlockState pBlockState) {
        super(BlockEntityInit.MOLDER.get(), pPos, pBlockState);
        this.inventory = addModule(new InventoryModule(this, 1));
    }

    public @NotNull ItemStack getItem() {
        return this.inventory.getCapability().getStackInSlot(0);
    }

    public void setItem(final ItemStack stack) {
        this.inventory.getCapability().setStackInSlot(0, stack);
    }

    public @NotNull ItemStack removeItemOutOfSlot() {
        return this.inventory.getCapability().extractItem(0, 1, false);
    }

    public boolean hasItem() {
        return !getItem().isEmpty();
    }

    public void insertItem(final ItemStack stack) {
        this.inventory.getCapability().insertItem(0, stack, false);
    }
}
