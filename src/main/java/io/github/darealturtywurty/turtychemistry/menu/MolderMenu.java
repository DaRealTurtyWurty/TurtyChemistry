package io.github.darealturtywurty.turtychemistry.menu;

import io.github.darealturtywurty.turtychemistry.menu.slot.SlotNoPlace;
import io.github.darealturtywurty.turtychemistry.init.BlockInit;
import io.github.darealturtywurty.turtychemistry.init.MenuInit;
import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.ContainerData;
import net.minecraft.world.inventory.ContainerLevelAccess;
import net.minecraft.world.inventory.SimpleContainerData;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.ItemStackHandler;
import net.minecraftforge.items.SlotItemHandler;
import org.jetbrains.annotations.NotNull;

// TODO: This shouldn't exist. There are no item slots in the molder.
public final class MolderMenu extends AbstractContainerMenu {
    private final ContainerLevelAccess access;
    private final ContainerData data;

    public MolderMenu(final int id, final Inventory playerInventory) {
        this(id, playerInventory, new ItemStackHandler(2), BlockPos.ZERO, new SimpleContainerData(3));
    }

    public MolderMenu(final int id, final Inventory playerInventory, final IItemHandler slots, final BlockPos pos, final ContainerData data) {
        super(MenuInit.MOLDER.get(), id);
        this.access = ContainerLevelAccess.create(playerInventory.player.getLevel(), pos);
        this.data = data;

        // Input Slots
        this.addSlot(new SlotItemHandler(slots, 0, 42, 35));

        // Output Slot
        this.addSlot(new SlotNoPlace(slots, 1, 116, 35));

        this.addDataSlots(data);
    }

    @Override
    public ItemStack quickMoveStack(final Player pPlayer, final int pIndex) {
        return null;
    }

    @Override
    public boolean stillValid(final @NotNull Player pPlayer) {
        return stillValid(this.access, pPlayer, BlockInit.MOLDER_BLOCK.get());
    }
}
