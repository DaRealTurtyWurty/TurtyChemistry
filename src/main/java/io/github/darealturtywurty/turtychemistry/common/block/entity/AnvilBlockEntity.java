package io.github.darealturtywurty.turtychemistry.common.block.entity;

import io.github.darealturtywurty.turtychemistry.core.init.BlockEntityInit;
import io.github.darealturtywurty.turtylib.common.blockentity.ModularBlockEntity;
import io.github.darealturtywurty.turtylib.common.blockentity.module.InventoryModule;
import net.minecraft.core.BlockPos;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.state.BlockState;

public final class AnvilBlockEntity extends ModularBlockEntity {
    public final InventoryModule inventoryModule;

    public AnvilBlockEntity(final BlockPos pos, final BlockState state) {
        super(BlockEntityInit.ANVIL.get(), pos, state);
        this.inventoryModule = this.addModule(new InventoryModule(this, 1));

    }

    public void setStackInSlot(final ItemStack stack) {
        inventoryModule.getCapability().setStackInSlot(0, stack);
    }

    public void smithItem() {
        //TODO:check to see what sheet the ingot should make
    }

    public ItemStack getItem() {
        return inventoryModule.getCapability().getStackInSlot(0);
    }
}
