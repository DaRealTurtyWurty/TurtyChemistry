package io.github.darealturtywurty.turtychemistry.common.block.container;

import io.github.darealturtywurty.turtychemistry.common.block.entity.RubberTreeTapBlockEntity;
import io.github.darealturtywurty.turtychemistry.core.init.BlockInit;
import io.github.darealturtywurty.turtychemistry.core.init.ContainerInit;
import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.ContainerLevelAccess;
import net.minecraft.world.inventory.MenuConstructor;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.items.IItemHandlerModifiable;
import net.minecraftforge.items.ItemStackHandler;
import net.minecraftforge.items.SlotItemHandler;
import org.jetbrains.annotations.NotNull;

public final class RubberTreeTapContainer extends AbstractContainerMenu {
    private final ContainerLevelAccess containerLevelAccess;

    public RubberTreeTapContainer(final int id, final Inventory playerInventory) {
        this(playerInventory, new ItemStackHandler(27), BlockPos.ZERO, id);
    }

    public RubberTreeTapContainer(final Inventory playerInventory, final IItemHandlerModifiable slots, final BlockPos pos, int id) {
        super(ContainerInit.RUBBER_TREE_TAP_CONTAINER.get(), id);
        this.containerLevelAccess = ContainerLevelAccess.create(playerInventory.player.level, pos);
        final int slotSizePlus2 = 18, startX = 8, startY = 86, hotbarY = 144, inventoryY = 18;
        for (int row = 0; row < 3; row++) {
            for (int column = 0; column < 9; column++) {
                addSlot(new SlotItemHandler(slots, row * 9 + column, startX + column * slotSizePlus2,
                        inventoryY + row * slotSizePlus2));
            }
        }
        for (int row = 0; row < 3; row++) {
            for (int column = 0; column < 9; column++) {
                addSlot(new Slot(playerInventory, 9 + row * 9 + column, startX + column * slotSizePlus2,
                        startY + row * slotSizePlus2));
            }
        }

        for (int column = 0; column < 9; column++) {
            addSlot(new Slot(playerInventory, column, startX + column * slotSizePlus2, hotbarY));
        }
    }


    @Override
    public @NotNull ItemStack quickMoveStack(final @NotNull Player player, final int index) {
        var returnStack = ItemStack.EMPTY;
        final Slot slot = getSlot(index);
        if (slot.hasItem()) {
            final ItemStack currentItem = slot.getItem();
            returnStack = currentItem.copy();
            if (index < 27) {
                if (!moveItemStackTo(currentItem, 27, this.slots.size(), true)) {
                    return ItemStack.EMPTY;
                }
            } else if (!moveItemStackTo(currentItem, 0, 27, false)) {
                return ItemStack.EMPTY;
            }
            if (currentItem.isEmpty()) {
                slot.set(ItemStack.EMPTY);
            } else {
                slot.setChanged();
            }
        }
        return returnStack;
    }

    @Override
    public boolean stillValid(final @NotNull Player player) {
        return stillValid(containerLevelAccess, player, BlockInit.RUBBER_TREE_TAP.get());
    }

    public static MenuConstructor getServerMenu(RubberTreeTapBlockEntity rubberTreeTapBlockEntity, BlockPos pos) {
        return (id, playerInventory, player) -> new RubberTreeTapContainer(playerInventory, rubberTreeTapBlockEntity.inventoryModule.getCapability(), pos, id);
    }
}
