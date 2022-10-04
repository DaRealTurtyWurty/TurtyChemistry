package io.github.darealturtywurty.turtychemistry.core;

import io.github.darealturtywurty.turtychemistry.common.TurtyTags;
import io.github.darealturtywurty.turtychemistry.common.block.entity.AnvilBlockEntity;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.tags.TagKey;
import net.minecraft.util.Mth;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public final class MixinHooks {

    public static final class AnvilMixinImplementation {
        private static boolean isValidStackForAnvil(final ItemStack stack) {
            for (TagKey<Item> itemTagKey : TurtyTags.TURTY_INGOT_TAG_KEY) {
                if (stack.is(itemTagKey) && ItemMixinImplementation.containsTemperatureTag(stack)) {
                    return true;
                }
            }
            return (stack.is(Items.IRON_INGOT) || stack.is(
                    Items.COPPER_INGOT)) && ItemMixinImplementation.containsTemperatureTag(stack);
        }

        private static boolean isValidHammerForAnvil(final ItemStack stack) {
            return stack.is(TurtyTags.TURTY_HAMMER_TAG_KEY);
        }

        public static void addIngotToAnvil(final Level pLevel, final BlockPos pPos, final Player pPlayer, final InteractionHand pHand, final CallbackInfoReturnable<InteractionResult> cir) {
            final Inventory playerInventory = pPlayer.getInventory();
            final ItemStack stack = pPlayer.getItemInHand(pHand);

            if (pPlayer.isCrouching() && pLevel.getBlockEntity(pPos) instanceof AnvilBlockEntity anvilBlockEntity) {
                if (!isValidHammerForAnvil(stack)) {
                    if (anvilBlockEntity.getItem().isEmpty() || (anvilBlockEntity.getItem()
                            .getCount() < 2 && anvilBlockEntity.getItem().sameItem(stack))) {
                        if (isValidStackForAnvil(stack) && ItemMixinImplementation.getTemperature(stack) > 35f) {
                            anvilBlockEntity.addStackToSlot(stack.split(1));
                            anvilBlockEntity.setChanged();
                            cir.setReturnValue(InteractionResult.CONSUME);
                        }
                    } else {
                        playerInventory.add(anvilBlockEntity.extractItem());
                        cir.setReturnValue(InteractionResult.CONSUME);
                    }
                } else if (isValidHammerForAnvil(stack) && isValidStackForAnvil(anvilBlockEntity.getItem())) {
                    anvilBlockEntity.smithItem();
                    pLevel.playSound(null, pPos, SoundEvents.ANVIL_USE, SoundSource.BLOCKS, 1, 1);
                    if (stack.getDamageValue() < stack.getMaxDamage() - 1) {
                        stack.setDamageValue(stack.getDamageValue() + 1);
                    } else {
                        stack.shrink(1);
                    }
                    cir.setReturnValue(InteractionResult.CONSUME);
                }
            }
        }
    }


    public static final class ItemMixinImplementation {
        private static final String COMPOUND_TAG_ID = "temperature";
        private static final DamageSource hotIngotDamageSource = new DamageSource("damage.heat").setIsFire()
                .setNoAggro();

        public static void addTemperatureToolTip(final ItemStack stack, final List<Component> texts) {
            if (stack.getTag() != null && containsTemperatureTag(stack)) {
                texts.add(Component.translatable("hot_ingot.temp.status",
                        Mth.floor(stack.getTag().getFloat(COMPOUND_TAG_ID))));
            }
        }

        private static boolean checkHeatableIngot(final ItemStack stack) {
            for (TagKey<Item> itemTagKey : TurtyTags.TURTY_INGOT_TAG_KEY) {
                if (stack.is(itemTagKey)) {
                    return true;
                }
            }
            return stack.is(Items.IRON_INGOT) || stack.is(Items.COPPER_INGOT);
        }

        private static boolean containsTemperatureTag(final ItemStack stack) {
            if (stack.getTag() == null) {
                return false;
            }
            return stack.getTag().contains(COMPOUND_TAG_ID);
        }

        private static float getTemperature(final ItemStack stack) {
            if (stack.getTag() == null || !containsTemperatureTag(stack)) {
                return 0;
            }
            return stack.getTag().getFloat(COMPOUND_TAG_ID);
        }

        public static void calculateStackTemperature(final ItemStack stack, final Level level, final Entity entity) {
            if (!level.isClientSide() && checkHeatableIngot(stack)) {
                CompoundTag temperatureNBTTag = stack.getTag();
                if (temperatureNBTTag == null || !containsTemperatureTag(stack)) {
                    temperatureNBTTag = new CompoundTag();
                    temperatureNBTTag.putFloat(COMPOUND_TAG_ID, ThreadLocalRandom.current().nextFloat(160, 300));
                    stack.setTag(temperatureNBTTag);
                } else if (temperatureNBTTag.getFloat(COMPOUND_TAG_ID) > 35f) {
                    float temperatureInCelsius = temperatureNBTTag.getFloat(COMPOUND_TAG_ID);
                    if (!(entity instanceof Player player)) {
                        entity.getArmorSlots().forEach(stack1 -> {
                            if (stack1.is(Items.LEATHER_CHESTPLATE)) {
                                entity.hurt(hotIngotDamageSource, 0.5f);
                            }
                        });
                    } else if (player.getItemBySlot(EquipmentSlot.CHEST).is(Items.LEATHER_CHESTPLATE)) {
                        player.hurt(hotIngotDamageSource, 0.5f);
                    } else {
                        entity.setSecondsOnFire(4);
                        entity.hurt(hotIngotDamageSource, 4);
                    }
                    final float biomesTemperature = level.getBiome(entity.blockPosition()).get().getBaseTemperature();
                    temperatureInCelsius -= temperatureInCelsius / (temperatureInCelsius + (Math.pow(biomesTemperature,
                            -25)));
                    temperatureNBTTag.putFloat(COMPOUND_TAG_ID, temperatureInCelsius);
                }
            }
        }
    }
}
