package io.github.darealturtywurty.turtychemistry.core;

import io.github.darealturtywurty.turtychemistry.common.TurtyTags;
import io.github.darealturtywurty.turtychemistry.core.init.ItemInit;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;

public final class MixinHooks {


    public static boolean isValidStackForAnvil(final ItemStack stack) {
        return stack.is(Items.IRON_INGOT) || stack.is(TurtyTags.TURTY_ITEM_TAG_KEY) || stack.is(Items.COPPER_INGOT);
    }

    public static boolean isValidHammerForAnvil(final ItemStack stack) {
        return stack.is(ItemInit.BASIC_HAMMER.get());
    }
}
