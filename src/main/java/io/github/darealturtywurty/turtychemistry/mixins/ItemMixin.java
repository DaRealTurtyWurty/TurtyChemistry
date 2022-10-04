package io.github.darealturtywurty.turtychemistry.mixins;

import io.github.darealturtywurty.turtychemistry.block.entity.AnvilBlockEntity;
import io.github.darealturtywurty.turtychemistry.core.MixinHooks;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.UseAnim;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelReader;
import net.minecraftforge.common.extensions.IForgeItem;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.List;

@Mixin(Item.class)
public abstract class ItemMixin implements ItemLike, IForgeItem {

    @Override
    public boolean doesSneakBypassUse(ItemStack stack, LevelReader level, BlockPos pos, Player player) {
        if (level.getBlockEntity(pos) instanceof AnvilBlockEntity) {
            return true;
        }
        return IForgeItem.super.doesSneakBypassUse(stack, level, pos, player);
    }
    @Inject(method = "appendHoverText", at = @At("HEAD"))
    public void turtychemistry$appendHoverText(final ItemStack pStack, final Level pLevel, final List<Component> pTooltipComponents, final TooltipFlag pIsAdvanced, final CallbackInfo ci) {
        MixinHooks.ItemMixinImplementation.addTemperatureToolTip(pStack, pTooltipComponents);
    }

    @Inject(method = "inventoryTick", at = @At("HEAD"))
    public void turtychemistry$inventoryTick(final ItemStack pStack, final Level pLevel, final Entity pEntity, final int pSlotId, final boolean pIsSelected, final CallbackInfo ci) {
            MixinHooks.ItemMixinImplementation.calculateStackTemperature(pStack, pLevel, pEntity);
    }


}
