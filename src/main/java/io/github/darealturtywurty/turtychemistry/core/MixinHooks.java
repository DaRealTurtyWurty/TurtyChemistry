package io.github.darealturtywurty.turtychemistry.core;

import io.github.darealturtywurty.turtychemistry.common.TurtyTags;
import io.github.darealturtywurty.turtychemistry.common.block.entity.AnvilBlockEntity;
import io.github.darealturtywurty.turtychemistry.core.init.ItemInit;
import net.minecraft.core.BlockPos;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.tags.TagKey;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

public final class MixinHooks {


    private static boolean isValidStackForAnvil(final ItemStack stack) {

        for (TagKey<Item> itemTagKey : TurtyTags.TURTY_INGOT_TAG_KEY) {
            if (stack.is(itemTagKey)) {
                return stack.is(Items.IRON_INGOT) || stack.is(Items.COPPER_INGOT) || stack.is(itemTagKey);
            }

        }
        return false;
    }

    private static boolean isValidHammerForAnvil(final ItemStack stack) {
        return stack.is(ItemInit.BASIC_HAMMER.get());
    }

    public static void addIngotToAnvil(final Level pLevel, final BlockPos pPos, final Player pPlayer, final InteractionHand pHand, final CallbackInfoReturnable<InteractionResult> cir) {
        final Inventory playerInventory = pPlayer.getInventory();
        final ItemStack stack = pPlayer.getItemInHand(pHand);

        if (pPlayer.isCrouching() && pLevel.getBlockEntity(pPos) instanceof AnvilBlockEntity anvilBlockEntity) {
            if (!isValidHammerForAnvil(stack)) {
                if (anvilBlockEntity.getItem().isEmpty()) {
                    if (isValidStackForAnvil(stack)) {
                        anvilBlockEntity.setStackInSlot(stack.split(1));
                        anvilBlockEntity.setChanged();
                        cir.setReturnValue(InteractionResult.CONSUME);
                    }
                } else {
                    playerInventory.add(anvilBlockEntity.inventoryModule.getCapability().extractItem(0, 1, false));
                    cir.setReturnValue(InteractionResult.CONSUME);
                }
            } else if (isValidHammerForAnvil(stack)) {
                anvilBlockEntity.smithItem();
                pLevel.playSound(null, pPos, SoundEvents.ANVIL_USE, SoundSource.BLOCKS, 1, 1);
                if (stack.getDamageValue() < stack.getDamageValue() - 1) {
                    stack.setDamageValue(stack.getDamageValue() + 1);
                } else {
                    stack.shrink(1);
                }
                cir.setReturnValue(InteractionResult.CONSUME);
            }
        }
    }
}
