package io.github.darealturtywurty.turtychemistry.block.entity;

import io.github.darealturtywurty.turtychemistry.init.BlockEntityInit;
import io.github.darealturtywurty.turtychemistry.init.ItemInit;
import io.github.darealturtywurty.turtylib.common.blockentity.ModularBlockEntity;
import io.github.darealturtywurty.turtylib.common.blockentity.module.InventoryModule;
import net.minecraft.core.BlockPos;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.block.state.BlockState;

public final class AnvilBlockEntity extends ModularBlockEntity {
    private final InventoryModule inventory;

    public AnvilBlockEntity(final BlockPos pos, final BlockState state) {
        super(BlockEntityInit.ANVIL.get(), pos, state);
        this.inventory = this.addModule(new InventoryModule(this, 1));
    }

    private void setStackInSlot(final ItemStack stack) {
        this.inventory.getCapability().setStackInSlot(0, stack);
    }

    public ItemStack extractItem() {
        return this.inventory.getCapability().extractItem(0, 1, false);
    }

    public ItemStack extractItem(final int amount) {
        return this.inventory.getCapability().extractItem(0, amount, false);
    }

    public void addStackToSlot(final ItemStack stack) {
        if (getItem().isEmpty()) {
            setStackInSlot(stack);
        } else if (getItem().sameItemStackIgnoreDurability(stack)) {
            getItem().grow(1);
        }
    }

    public void smithItem() {
        if (getItem().getCount() == 1) {
            if (getItem().is(Items.IRON_INGOT)) {
                setStackInSlot(ItemInit.Sheets.IRON_SHEET.get().getDefaultInstance());
                return;
            } else if (getItem().is(Items.COPPER_INGOT)) {
                setStackInSlot(ItemInit.Sheets.COPPER_SHEET.get().getDefaultInstance());
                return;
            }

            ItemInit.INGOT_SHEET_MAP.forEach((ingot, sheet) -> {
                if (getItem().is(ingot.get())) {
                    setStackInSlot(new ItemStack(sheet.get().asItem()));
                }
            });
        } else if (getItem().getCount() == 2) {
            if (getItem().is(Items.IRON_INGOT)) {
                setStackInSlot(ItemInit.Rods.IRON_ROD.get().getDefaultInstance());
                return;
            } else if (getItem().is(Items.COPPER_INGOT)) {
                setStackInSlot(ItemInit.Sheets.COPPER_SHEET.get().getDefaultInstance());
                return;
            }

            ItemInit.INGOT_ROD_MAP.forEach((ingot, rod) -> {
                if (getItem().is(ingot.get())) {
                    setStackInSlot(rod.get().getDefaultInstance());
                }
            });
        }
    }

    public ItemStack getItem() {
        return this.inventory.getCapability().getStackInSlot(0);
    }
}
