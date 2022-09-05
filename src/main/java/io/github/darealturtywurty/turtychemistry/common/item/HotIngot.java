package io.github.darealturtywurty.turtychemistry.common.item;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public final class HotIngot extends ChemistryItem {

    private float temperatureInCelsius;
    private final Item defaultItem;
    private static final String COMPOUND_TAG_ID = "temperature";
    private static final int COOLING_COEFFICIENT = -4;

    public HotIngot(final Builder builder, final Item nonHeatedUpVersion) {
        super(builder);
        defaultItem = nonHeatedUpVersion;
    }

    @Override
    public void onCraftedBy(final @NotNull ItemStack stack, final @NotNull Level level, final @NotNull Player player) {
        if (!level.isClientSide()) {
            this.temperatureInCelsius = ThreadLocalRandom.current().nextFloat(160, 300);
            if (stack.getTag() == null) {
                final CompoundTag initalTagOnCraft = new CompoundTag();
                stack.setTag(initalTagOnCraft);
                initalTagOnCraft.putFloat(COMPOUND_TAG_ID, temperatureInCelsius);
            }
        }
    }

    @Override
    public void appendHoverText(final @NotNull ItemStack stack, @Nullable final Level level, final @NotNull List<Component> texts, final @NotNull TooltipFlag flag) {
        if (stack.getTag() != null) {
            texts.add(Component.translatable("hot_ingot.temp.status %s", stack.getTag().getFloat(COMPOUND_TAG_ID)));
        }
    }

    @Override
    public void inventoryTick(final @NotNull ItemStack stack, final @NotNull Level level, final @NotNull Entity entity, final int slot, final boolean selected) {
        if (!level.isClientSide()) {
            CompoundTag temperatureTag = stack.getTag();
            if (temperatureTag == null) {
                temperatureTag = new CompoundTag();
                stack.setTag(temperatureTag);
            }
            if (temperatureInCelsius > 35) {
                entity.setSecondsOnFire(3);
                entity.hurt(DamageSource.ON_FIRE, 2);
                final float biomesTemperature = level.getBiome(entity.blockPosition()).get().getBaseTemperature();
                temperatureInCelsius -= temperatureInCelsius / (temperatureInCelsius + (Math.pow(biomesTemperature, COOLING_COEFFICIENT)));
                temperatureTag.putFloat(COMPOUND_TAG_ID, temperatureInCelsius);

            } else {
                if (entity instanceof Player player) {
                    player.getInventory().setItem(slot, new ItemStack(defaultItem));
                }
            }
        }
        super.inventoryTick(stack, level, entity, slot, selected);
    }


}
