package io.github.darealturtywurty.turtychemistry.common.menus;

import io.github.darealturtywurty.turtychemistry.common.block.entity.ClayAlloyFurnaceBlockEntity;
import io.github.darealturtywurty.turtychemistry.common.slots.SlotNoPlace;
import io.github.darealturtywurty.turtychemistry.common.slots.SlotWithRestriction;
import io.github.darealturtywurty.turtychemistry.core.init.BlockInit;
import io.github.darealturtywurty.turtychemistry.core.init.MenuInit;
import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.*;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.ItemStackHandler;
import net.minecraftforge.items.SlotItemHandler;

public final class ClayAlloyFurnaceMenu extends AbstractContainerMenu {
    private final ContainerLevelAccess access;
    private final ContainerData data;

    protected ClayAlloyFurnaceMenu(int id, Inventory playerInventory, IItemHandler slots, BlockPos pos,
            ContainerData data) {
        super(MenuInit.CLAY_ALLOY_FURNACE.get(), id);
        this.access = ContainerLevelAccess.create(playerInventory.player.level, pos);
        this.data = data;

        final int slotSizePlus2 = 18;

        // Fuel Slot
        this.addSlot(new SlotWithRestriction(slots, 0, 56, 53, ClayAlloyFurnaceBlockEntity::isFuel));

        // Input Slots
        this.addSlot(new SlotItemHandler(slots, 1, 42, 17));
        this.addSlot(new SlotItemHandler(slots, 2, 69, 17));

        // Output Slot
        this.addSlot(new SlotNoPlace(slots, 3, 116, 35));

        // Leftover Slot
        this.addSlot(new SlotNoPlace(slots, 4, 139, 35));

        // Player Inventory
        for (int row = 0; row < 3; ++row) {
            for (int col = 0; col < 9; ++col) {
                this.addSlot(new Slot(playerInventory, col + row * 9 + 9, 8 + col * slotSizePlus2,
                        84 + row * slotSizePlus2));
            }
        }

        // Player Hotbar
        for (int row = 0; row < 9; ++row) {
            this.addSlot(new Slot(playerInventory, row, 8 + row * slotSizePlus2, 142));
        }

        this.addDataSlots(data);
    }

    public static MenuConstructor getServerMenu(ClayAlloyFurnaceBlockEntity blockEntity, BlockPos pos) {
        return (id, playerInventory, player) -> new ClayAlloyFurnaceMenu(id, playerInventory,
                blockEntity.inventory.getCapability(), pos, blockEntity.getContainerData());
    }

    public static ClayAlloyFurnaceMenu getClientMenu(int id, Inventory playerInventory) {
        return new ClayAlloyFurnaceMenu(id, playerInventory,
                new ItemStackHandler(5), BlockPos.ZERO, new SimpleContainerData(3));
    }

    @Override
    public ItemStack quickMoveStack(Player player, int index) {
        ItemStack itemstack = ItemStack.EMPTY;
        Slot slot = this.slots.get(index);
        if (slot.hasItem()) {
            ItemStack current = slot.getItem();
            itemstack = current.copy();
            if (index < 5) {
                if (!this.moveItemStackTo(current, 5, this.slots.size(), true)) {
                    return ItemStack.EMPTY;
                }
            } else if (!this.moveItemStackTo(current, 0, 5, false)) {
                return ItemStack.EMPTY;
            }

            if (current.isEmpty()) {
                slot.set(ItemStack.EMPTY);
            } else {
                slot.setChanged();
            }
        }

        return itemstack;
    }

    @Override
    public boolean stillValid(Player player) {
        return stillValid(this.access, player, BlockInit.CLAY_ALLOY_FURNACE.get()) || stillValid(this.access,
                player, io.github.darealturtywurty.turtylib.core.init.BlockInit.MULTIBLOCK.get());
    }

    public ContainerData getData() {
        return this.data;
    }
}
