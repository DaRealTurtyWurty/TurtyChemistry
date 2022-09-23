package io.github.darealturtywurty.turtychemistry.common.block.entity;

import io.github.darealturtywurty.turtychemistry.core.init.BlockEntityInit;
import io.github.darealturtywurty.turtylib.common.blockentity.ModularBlockEntity;
import io.github.darealturtywurty.turtylib.common.blockentity.module.InventoryModule;
import net.minecraft.core.BlockPos;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.state.BlockState;

public final class MolderBlockEntity extends ModularBlockEntity {

    public final InventoryModule inventoryModule;


    public MolderBlockEntity(final BlockPos pPos, final BlockState pBlockState) {
        super(BlockEntityInit.MOLDER.get(), pPos, pBlockState);
        this.inventoryModule = addModule(new InventoryModule(this, 1));


    }


    public ItemStack getItem() {
        return this.inventoryModule.getCapability().getStackInSlot(0);
    }
    public ItemStack removeItemOutOfSlot()
    {
        return this.inventoryModule.getCapability().extractItem(0,1,false);
    }
    public boolean hasItem() {
        return !getItem().isEmpty();
    }

    public void insertItem(final ItemStack stack)
    {
        inventoryModule.getCapability().insertItem(0,stack,false);
    }
    public void setItem(final ItemStack stack)
    {
        inventoryModule.getCapability().setStackInSlot(0,stack);
    }

}
