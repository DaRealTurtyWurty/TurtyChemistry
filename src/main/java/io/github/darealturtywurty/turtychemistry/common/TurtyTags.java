package io.github.darealturtywurty.turtychemistry.common;

import io.github.darealturtywurty.turtychemistry.TurtyChemistry;
import io.github.darealturtywurty.turtychemistry.core.init.BlockInit;
import io.github.darealturtywurty.turtychemistry.core.init.ItemInit;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;

public final class TurtyTags {

    public static final TagKey<Item> TURTY_HAMMER_TAG_KEY = ItemInit.ITEMS.createTagKey("basic_hammers");
    public static final TagKey<Block> TURTY_BLOCK_TAG_KEY = BlockInit.BLOCKS.createTagKey(TurtyChemistry.MODID);
    private static final String INGOT_DIRECTORY = "ingots/";
    public static final TagKey<Item>[] TURTY_INGOT_TAG_KEY = new TagKey[]{
            ItemInit.ITEMS.createTagKey(forgeResourceLocation(INGOT_DIRECTORY + "aluminum")),
            ItemInit.ITEMS.createTagKey(forgeResourceLocation(INGOT_DIRECTORY + "brass")),
            ItemInit.ITEMS.createTagKey(forgeResourceLocation(INGOT_DIRECTORY + "steel"))};
    private static final String SHEETMETAL_DIRECTORY = "sheetmetals/";
    public static final TagKey<Item>[] TURTY_SHEET_TAG_KEY = new TagKey[]{
            ItemInit.ITEMS.createTagKey(forgeResourceLocation(SHEETMETAL_DIRECTORY + "aluminum")),
            ItemInit.ITEMS.createTagKey(forgeResourceLocation(SHEETMETAL_DIRECTORY + "steel"))
    };

    private static ResourceLocation forgeResourceLocation(final String location) {
        return new ResourceLocation("forge", location);
    }
}
