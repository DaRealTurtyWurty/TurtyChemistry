package io.github.darealturtywurty.turtychemistry.common.container;

import java.util.function.Predicate;

import io.github.darealturtywurty.turtychemistry.common.block.entity.ShaleFracturerBlockEntity;
import io.github.darealturtywurty.turtychemistry.common.container.slot.HideableSlot;
import io.github.darealturtywurty.turtychemistry.core.init.BlockInit;
import io.github.darealturtywurty.turtychemistry.core.init.ContainerInit;
import io.github.darealturtywurty.turtychemistry.network.syncdata.ShaleFracturerSyncData;
import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.ContainerData;
import net.minecraft.world.inventory.ContainerLevelAccess;
import net.minecraft.world.inventory.MenuConstructor;
import net.minecraft.world.inventory.SimpleContainerData;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.ItemStackHandler;

public class ShaleFracturerContainer extends AbstractContainerMenu {
    private static ShaleFracturerBlockEntity blockEntity;
    private static final Predicate<AbstractContainerMenu> PLAYER_INV_PREDICATE = container -> container instanceof final ShaleFracturerContainer shaleFracturer
            && shaleFracturer.getBlockEntity().shouldShowPlayerInv;
    private final ContainerLevelAccess containerAccess;
    private final ContainerData data;

    public ShaleFracturerContainer(int id, Inventory playerInv) {
        this(id, playerInv, new ItemStackHandler(0), BlockPos.ZERO, new SimpleContainerData(0));
    }

    public ShaleFracturerContainer(int windowId, Inventory playerInv, IItemHandler slots, BlockPos pos,
            ContainerData data) {
        super(ContainerInit.SHALE_FRACTURER.get(), windowId);
        this.containerAccess = ContainerLevelAccess.create(playerInv.player.level, pos);
        this.data = data;

        final int slotSizePlus2 = 18, startX = 8, startY = 84, hotbarY = 142;
        for (int column = 0; column < 9; column++) {
            for (int row = 0; row < 3; row++) {
                addSlot(new HideableSlot(playerInv, this, PLAYER_INV_PREDICATE, 9 + row * 9 + column,
                        startX + column * slotSizePlus2, startY + row * slotSizePlus2));
            }
            addSlot(new HideableSlot(playerInv, this, PLAYER_INV_PREDICATE, column,
                    startX + column * slotSizePlus2, hotbarY));
        }

        // TODO: Add Slots

        addDataSlots(data);
    }

    public static MenuConstructor getServerContainer(ShaleFracturerBlockEntity blockEntity, BlockPos pos) {
        ShaleFracturerContainer.blockEntity = blockEntity;
        return (id, playerInv, player) -> new ShaleFracturerContainer(id, playerInv,
                blockEntity.getInventory(), pos, new ShaleFracturerSyncData(blockEntity));
    }

    public ShaleFracturerBlockEntity getBlockEntity() {
        return blockEntity;
    }

    public ContainerData getData() {
        return this.data;
    }

    @Override
    public ItemStack quickMoveStack(Player player, int index) {
        var retStack = ItemStack.EMPTY;
        final Slot slot = this.slots.get(index);
        if (slot != null && slot.hasItem()) {
            final ItemStack stack = slot.getItem();
            retStack = stack.copy();

            final int size = this.slots.size() - player.getInventory().getContainerSize();
            if (index < size) {
                if (!moveItemStackTo(stack, 0, this.slots.size(), false))
                    return ItemStack.EMPTY;
            } else if (!moveItemStackTo(stack, 0, size, false))
                return ItemStack.EMPTY;

            if (stack.isEmpty() || stack.getCount() == 0) {
                slot.set(ItemStack.EMPTY);
            } else {
                slot.setChanged();
            }

            if (stack.getCount() == retStack.getCount())
                return ItemStack.EMPTY;

            slot.onTake(player, stack);
        }

        return retStack;
    }

    @Override
    public boolean stillValid(Player player) {
        return stillValid(this.containerAccess, player, BlockInit.SHALE_FRACTURER.get());
    }
}
