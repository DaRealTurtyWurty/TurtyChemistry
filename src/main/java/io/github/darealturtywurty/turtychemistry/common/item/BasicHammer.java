package io.github.darealturtywurty.turtychemistry.common.item;

import io.github.darealturtywurty.turtychemistry.common.block.entity.AnvilBlockEntity;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.UseOnContext;
import org.jetbrains.annotations.NotNull;

public final class BasicHammer extends ChemistryItem {
    public BasicHammer(Builder builder) {
        super(builder);
    }

    @Override
    public boolean isDamageable(ItemStack stack) {
        return true;
    }

    @Override
    public int getMaxDamage(ItemStack stack) {
        return 70;
    }

    @Override
    public ItemStack getCraftingRemainingItem(ItemStack itemStack) {
        final ItemStack stack = new ItemStack(this);
        stack.setDamageValue(itemStack.getDamageValue() + 1);
        return stack;
    }

    @Override
    public int getMaxStackSize(ItemStack stack) {
        return 1;
    }

    @Override
    public boolean hasCraftingRemainingItem(ItemStack stack) {
        return stack.getDamageValue() < stack.getMaxDamage() - 1;
    }

    @Override
    public @NotNull InteractionResult useOn(UseOnContext pContext) {
        final Player player = pContext.getPlayer();
        if (pContext.getLevel().getBlockEntity(
                pContext.getClickedPos()) instanceof AnvilBlockEntity anvilBlockEntity && player.isCrouching()) {
            anvilBlockEntity.smithItem();
            player.level.playSound(null, pContext.getClickedPos(), SoundEvents.ANVIL_USE, SoundSource.BLOCKS, 1, 1);
            if (player.getUseItem().getDamageValue() < player.getUseItem().getMaxDamage()) {
                player.getUseItem().setDamageValue(player.getUseItem().getDamageValue() + 1);
            } else {
                player.getUseItem().shrink(1);
            }
            return InteractionResult.CONSUME;
        }
        return InteractionResult.PASS;
    }
}
