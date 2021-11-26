package io.github.darealturtywurty.turtychemistry.core.init;

import java.util.HashMap;
import java.util.Map;

import io.github.darealturtywurty.turtychemistry.TurtyChemistry;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber.Bus;
import net.minecraftforge.fmllegacy.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public final class BlockInit extends AbstractInit {

    public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS,
            TurtyChemistry.MODID);

    public static final Map<Block, Item.Properties> BLOCK_ITEM_WHITELIST = new HashMap<>();

    @Mod.EventBusSubscriber(modid = TurtyChemistry.MODID, bus = Bus.MOD)
    public static class BlockItems {
        @SubscribeEvent
        public static void registerBlockItems(RegistryEvent.Register<Item> event) {
            BLOCKS.getEntries().stream().filter(RegistryObject::isPresent).map(RegistryObject::get)
                    .filter(block -> BLOCK_ITEM_WHITELIST.containsKey(block)).forEachOrdered(block -> {
                        final var blockItem = new BlockItem(block, new Item.Properties())
                                .setRegistryName(block.getRegistryName());
                        event.getRegistry().register(blockItem);
                    });
        }
    }
}
