package io.github.darealturtywurty.turtychemistry.menu.slot;

import net.minecraft.world.item.ItemStack;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.SlotItemHandler;
import org.jetbrains.annotations.NotNull;

import java.util.function.Predicate;

public class SlotWithRestriction extends SlotItemHandler {
    private final Predicate<ItemStack> validator;

    public SlotWithRestriction(IItemHandler itemHandler, int index, int xPosition, int yPosition,
            Predicate<ItemStack> validator) {
        super(itemHandler, index, xPosition, yPosition);
        this.validator = validator;
    }

    @Override
    public boolean mayPlace(@NotNull ItemStack stack) {
        return validator.test(stack);
    }
}
