package io.github.darealturtywurty.turtychemistry.common.item;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public final class HotIngot extends ChemistryItem {


    private static final String COMPOUND_TAG_ID = "temperature";
    private static final int COOLING_COEFFICIENT = -4;

    public HotIngot(final Builder builder) {
        super(builder);

    }

    @Override
    public void onCraftedBy(final @NotNull ItemStack stack, final @NotNull Level level, final @NotNull Player player) {
        if (!level.isClientSide()) {
            if (stack.getTag() == null) {
                final CompoundTag initialTagOnCraft = new CompoundTag();
                stack.setTag(initialTagOnCraft);
                initialTagOnCraft.putFloat(COMPOUND_TAG_ID, ThreadLocalRandom.current().nextFloat(160, 300));
            }
        }
    }

    @Override
    public void appendHoverText(final @NotNull ItemStack stack, @Nullable final Level level, final @NotNull List<Component> texts, final @NotNull TooltipFlag flag) {
        if (stack.getTag() != null) {
            texts.add(Component.translatable("hot_ingot.temp.status", stack.getTag().getFloat(COMPOUND_TAG_ID)));
        }
    }

    @Override
    public void inventoryTick(final @NotNull ItemStack stack, final @NotNull Level level, final @NotNull Entity entity, final int slot, final boolean selected) {
        if (!level.isClientSide()) {
            CompoundTag temperatureNBTTag = stack.getTag();
            if (temperatureNBTTag == null) {
                temperatureNBTTag = new CompoundTag();
                stack.setTag(temperatureNBTTag);
            } else if (temperatureNBTTag.getFloat(COMPOUND_TAG_ID) > 35f) {
                float temperatureInCelsius = temperatureNBTTag.getFloat(COMPOUND_TAG_ID);
                entity.setSecondsOnFire(entity.getRemainingFireTicks() + 1);
                entity.hurt(DamageSource.ON_FIRE, 2);
                final float biomesTemperature = level.getBiome(entity.blockPosition()).get().getBaseTemperature();
                temperatureInCelsius -= temperatureInCelsius / (temperatureInCelsius + (Math.pow(biomesTemperature,
                        COOLING_COEFFICIENT)));
                temperatureNBTTag.putFloat(COMPOUND_TAG_ID, temperatureInCelsius);
            }
        }
        super.inventoryTick(stack, level, entity, slot, selected);
    }

    public static float getTemperature(final @NotNull ItemStack stack) {
        if (containsTemperatureTag(stack)) {
            return stack.getTag().getFloat(COMPOUND_TAG_ID);
        }
        return 0;
    }

    public static boolean containsTemperatureTag(final @NotNull ItemStack stack) {
        if (stack.getTag() == null) {
            return false;
        }
        return stack.getTag().contains(COMPOUND_TAG_ID);
    }


}
