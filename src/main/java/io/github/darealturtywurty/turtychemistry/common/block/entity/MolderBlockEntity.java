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
        this.inventoryModule = addModule(new InventoryModule(this, 2));


    }


    public ItemStack getItem(final int slot) {
        return this.inventoryModule.getCapability().getStackInSlot(slot);
    }

    public boolean hasItem(final int slot) {
        return !getItem(slot).isEmpty();
    }


}
