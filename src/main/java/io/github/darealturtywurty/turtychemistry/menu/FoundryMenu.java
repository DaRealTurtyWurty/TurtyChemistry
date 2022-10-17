package io.github.darealturtywurty.turtychemistry.menu;

import io.github.darealturtywurty.turtychemistry.block.entity.ClayAlloyFurnaceBlockEntity;
import io.github.darealturtywurty.turtychemistry.block.entity.FoundryBlockEntity;
import io.github.darealturtywurty.turtychemistry.init.BlockInit;
import io.github.darealturtywurty.turtychemistry.init.MenuInit;
import io.github.darealturtywurty.turtychemistry.menu.slot.SlotWithRestriction;
import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.*;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.ItemStackHandler;
import net.minecraftforge.items.SlotItemHandler;
import org.jetbrains.annotations.NotNull;

public final class FoundryMenu extends AbstractContainerMenu {
    private final ContainerLevelAccess access;
    private final ContainerData data;

    private FoundryMenu(final int id, final Inventory playerInventory, final IItemHandler slots, final BlockPos pos, final ContainerData data) {
        super(MenuInit.CLAY_ALLOY_FURNACE.get(), id);
        this.access = ContainerLevelAccess.create(playerInventory.player.level, pos);
        this.data = data;

        final int slotSizePlus2 = 18;

        // Fuel Slot
        this.addSlot(new SlotWithRestriction(slots, 0, 56, 53, ClayAlloyFurnaceBlockEntity::isFuel));

        // Input Slots
        this.addSlot(new SlotItemHandler(slots, 1, (int) 55.5f, 17));


        // Player Inventory
        for (int row = 0; row < 3; ++row) {
            for (int col = 0; col < 9; ++col) {
                this.addSlot(
                        new Slot(playerInventory, col + row * 18, 8 + col * slotSizePlus2, 84 + row * slotSizePlus2));
            }
        }

        // Player Hotbar
        for (int row = 0; row < 9; ++row) {
            this.addSlot(new Slot(playerInventory, row, 8 + row * slotSizePlus2, 142));
        }

        this.addDataSlots(data);
    }

    public static MenuConstructor getServerMenu(FoundryBlockEntity blockEntity, BlockPos pos) {
        return (id, playerInventory, player) -> new FoundryMenu(id, playerInventory,
                blockEntity.inventory.getCapability(), pos, blockEntity.getContainerData());
    }

    public static FoundryMenu getClientMenu(int id, Inventory playerInventory) {
        return new FoundryMenu(id, playerInventory, new ItemStackHandler(2), BlockPos.ZERO, new SimpleContainerData(2));
    }

    @Override
    public @NotNull ItemStack quickMoveStack(@NotNull Player player, int index) {
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
    public boolean stillValid(@NotNull Player player) {
        return stillValid(this.access, player, BlockInit.FOUNDRY_BLOCK.get()) || stillValid(this.access, player,
                io.github.darealturtywurty.turtylib.core.init.BlockInit.MULTIBLOCK.get());
    }

    public ContainerData getData() {
        return this.data;
    }
}
