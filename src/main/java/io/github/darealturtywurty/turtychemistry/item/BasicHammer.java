package io.github.darealturtywurty.turtychemistry.item;

import net.minecraft.world.item.ItemStack;

public class BasicHammer extends ChemistryItem {
    public BasicHammer(Builder builder) {
        super(builder);
    }

    @Override
    public ItemStack getCraftingRemainingItem(ItemStack itemStack) {
        final ItemStack stack = new ItemStack(this);
        stack.setDamageValue(itemStack.getDamageValue() + 1);
        return stack;
    }

    @Override
    public boolean hasCraftingRemainingItem(ItemStack stack) {
        return stack.getDamageValue() < stack.getMaxDamage() - 1;
    }
}
