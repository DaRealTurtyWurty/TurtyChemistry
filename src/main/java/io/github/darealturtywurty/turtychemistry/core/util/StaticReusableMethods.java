package io.github.darealturtywurty.turtychemistry.core.util;

import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

public final class StaticReusableMethods {

    public static void dropItemsOnDestroy(final Level level, final ItemStack stack, final BlockPos pos) {
        final int stackCount = stack.getCount();
        for (int i = 0; i < stackCount; i++) {
            level.addFreshEntity(new ItemEntity(level, pos.getX(), pos.getY(), pos.getZ(), stack));
        }
    }

    public static void dropItemsOnDestroy(final Level level, final ItemStack stack, final BlockPos pos, final int amount) {
        for (int i = 0; i < amount; i++) {
            level.addFreshEntity(new ItemEntity(level, pos.getX(), pos.getY(), pos.getZ(), stack));
        }
    }
}
