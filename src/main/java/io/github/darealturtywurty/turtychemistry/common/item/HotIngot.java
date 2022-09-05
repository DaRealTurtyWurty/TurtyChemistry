package io.github.darealturtywurty.turtychemistry.common.item;

import io.github.darealturtywurty.turtychemistry.TurtyChemistry;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.server.commands.data.EntityDataAccessor;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import net.minecraftforge.common.capabilities.ICapabilityProvider;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public final class HotIngot extends ChemistryItem  {

    private float temperatureInCelsius;
    private final Item defaultItem;
    private static final String COMPUND_TAG_ID = "temp";
    public HotIngot(final Builder builder, final Item baseItem) {
        super(builder);
        TurtyChemistry.LOGGER.info(baseItem);
        defaultItem=baseItem;
    }

    @Override
    public void onCraftedBy(final ItemStack stack, final @NotNull Level level, final @NotNull Player player) {
        final float randomTemp = ThreadLocalRandom.current().nextFloat(160,300);
        this.temperatureInCelsius = randomTemp;
        stack.setTag(writeNBT(randomTemp));
    }

    @Override
    public void appendHoverText(final @NotNull ItemStack stack, @Nullable final Level level, final @NotNull List<Component> texts, final @NotNull TooltipFlag flag) {
        if(stack.getTag() != null) {
            texts.add(Component.translatable("hot_ingot.temp.status %s", stack.getTag().getFloat(COMPUND_TAG_ID)));
        }
        else
        {
            stack.setTag(new CompoundTag());
        }
    }

    @Override
    public void inventoryTick(final @NotNull ItemStack stack, final @NotNull Level level, final @NotNull Entity entity, final int slot, final boolean selected) {
        if(temperatureInCelsius <= 0)
        {
            temperatureInCelsius = 300;
        }
        TurtyChemistry.LOGGER.info(temperatureInCelsius);
        if(temperatureInCelsius > 60)
        {
            entity.setSecondsOnFire(3);
            entity.hurt(DamageSource.ON_FIRE,2);
            temperatureInCelsius--;

        }
        else
        {
            ((Player)entity).getInventory().setItem(slot,new ItemStack(defaultItem));
        }
        stack.save(writeNBT(temperatureInCelsius));
        super.inventoryTick(stack,level,entity,slot,selected);
    }


    public CompoundTag writeNBT(final float temp)
    {
        final CompoundTag temperature = new CompoundTag();
        temperature.putFloat(COMPUND_TAG_ID,temp);
        return temperature;
    }

    public float readNBT(final CompoundTag compoundTag)
    {

        return compoundTag.getFloat(COMPUND_TAG_ID);
    }
}
