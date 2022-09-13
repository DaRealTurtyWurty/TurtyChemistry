package io.github.darealturtywurty.turtychemistry.common;

import io.github.darealturtywurty.turtychemistry.TurtyChemistry;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;

public final class TurtyTags {
    public static final TagKey<Item> TURTY_ITEM_TAG_KEY = ItemTags.create(new ResourceLocation(TurtyChemistry.MODID));
    public static final TagKey<Block> TURTY_BLOCK_TAG_KEY = BlockTags.create(new ResourceLocation(TurtyChemistry.MODID));
}
