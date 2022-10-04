package io.github.darealturtywurty.turtychemistry.init;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;

public final class TagInit {
    public static final TagKey<Item> TURTY_HAMMER_TAG_KEY = ItemInit.ITEMS.createTagKey("basic_hammers");

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
