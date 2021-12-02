package io.github.darealturtywurty.turtychemistry.common.container.slot;

import java.util.function.Predicate;

import net.minecraft.world.Container;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.Slot;

public class HideableSlot extends Slot {
    private final Predicate<AbstractContainerMenu> shouldHide;
    private final AbstractContainerMenu parentContainer;

    public HideableSlot(Container container, AbstractContainerMenu parentContainer,
            Predicate<AbstractContainerMenu> shouldHide, int index, int x, int y) {
        super(container, index, x, y);
        this.parentContainer = parentContainer;
        this.shouldHide = shouldHide;
    }

    @Override
    public boolean isActive() {
        return this.shouldHide.test(this.parentContainer);
    }
}
