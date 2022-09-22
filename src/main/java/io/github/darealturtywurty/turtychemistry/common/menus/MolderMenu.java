package io.github.darealturtywurty.turtychemistry.common.menus;

import io.github.darealturtywurty.turtychemistry.common.slots.SlotNoPlace;
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
import org.jetbrains.annotations.NotNull;

public final class MolderMenu extends AbstractContainerMenu {

    private final ContainerLevelAccess containerLevelAccess;
    private final ContainerData data;

    public MolderMenu(final int id, final Inventory playerInventory) {
        this(id, playerInventory, new ItemStackHandler(2), BlockPos.ZERO, new SimpleContainerData(3));
    }

    public MolderMenu(final int pContainerId, final Inventory playerInventory, final IItemHandler slots, final BlockPos pos, final ContainerData data) {
        super(MenuInit.MOLDER.get(), pContainerId);
        containerLevelAccess = ContainerLevelAccess.create(playerInventory.player.getLevel(), pos);
        this.data = data;
        final int slotSizePlus2 = 18;


        // Input Slots
        this.addSlot(new SlotItemHandler(slots, 0, 42, 35));

        // Output Slot
        this.addSlot(new SlotNoPlace(slots, 1, 116, 35));



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


    @Override
    public ItemStack quickMoveStack(final Player pPlayer, final int pIndex) {
        return null;
    }

    @Override
    public boolean stillValid(final @NotNull Player pPlayer) {
        return stillValid(this.containerLevelAccess, pPlayer, BlockInit.MOLDER_BLOCK.get());
    }
}
