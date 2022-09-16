package io.github.darealturtywurty.turtychemistry.core;

import io.github.darealturtywurty.turtychemistry.common.TurtyTags;
import io.github.darealturtywurty.turtychemistry.common.block.entity.AnvilBlockEntity;
import io.github.darealturtywurty.turtychemistry.core.init.ItemInit;
import net.minecraft.core.BlockPos;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

public final class MixinHooks {


    public static boolean isValidStackForAnvil(final ItemStack stack) {
        return stack.is(Items.IRON_INGOT) || stack.is(TurtyTags.TURTY_ITEM_TAG_KEY) || stack.is(Items.COPPER_INGOT);
    }

    public static boolean isValidHammerForAnvil(final ItemStack stack) {
        return stack.is(ItemInit.BASIC_HAMMER.get());
    }

    public static void addIngotToAnvil(BlockState pState, Level pLevel, BlockPos pPos, Player pPlayer, InteractionHand pHand, BlockHitResult pHit, CallbackInfoReturnable<InteractionResult> cir) {
        final Inventory playerInventory = pPlayer.getInventory();
        final ItemStack stack = pPlayer.getItemInHand(pHand);

        if (pPlayer.isCrouching() && pLevel.getBlockEntity(pPos) instanceof AnvilBlockEntity anvilBlockEntity) {
            if (!MixinHooks.isValidHammerForAnvil(stack)) {
                if (anvilBlockEntity.getItem().isEmpty()) {
                    if (MixinHooks.isValidStackForAnvil(stack)) {
                        anvilBlockEntity.setStackInSlot(stack.split(1));
                        anvilBlockEntity.setChanged();
                        cir.setReturnValue(InteractionResult.CONSUME);
                    }
                } else {
                    playerInventory.add(anvilBlockEntity.inventoryModule.getCapability().extractItem(0, 1, false));
                    cir.setReturnValue(InteractionResult.CONSUME);
                }
            } else {
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
