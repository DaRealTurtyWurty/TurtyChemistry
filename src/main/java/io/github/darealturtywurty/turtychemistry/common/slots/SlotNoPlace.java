package io.github.darealturtywurty.turtychemistry.common.slots;

import net.minecraftforge.items.IItemHandler;

public class SlotNoPlace extends SlotWithRestriction {
    public SlotNoPlace(IItemHandler itemHandler, int index, int xPosition, int yPosition) {
        super(itemHandler, index, xPosition, yPosition, stack -> false);
    }
}
