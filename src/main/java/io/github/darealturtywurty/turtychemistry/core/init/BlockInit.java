package io.github.darealturtywurty.turtychemistry.core.init;

import static io.github.darealturtywurty.turtychemistry.common.block.core.ChemistryBlock.Builder.build;
import static net.minecraft.world.level.block.state.BlockBehaviour.Properties.copy;
import static net.minecraft.world.level.block.state.BlockBehaviour.Properties.of;

import java.util.HashMap;
import java.util.Map;

import io.github.darealturtywurty.turtychemistry.TurtyChemistry;
import io.github.darealturtywurty.turtychemistry.common.block.ShaleBlock;
import io.github.darealturtywurty.turtychemistry.common.block.ShaleFracturerBlock;
import io.github.darealturtywurty.turtychemistry.common.block.core.ChemistryBlock;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.WaterlilyBlock;
import net.minecraft.world.level.material.Material;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber.Bus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public final class BlockInit extends AbstractInit {

    public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS,
            TurtyChemistry.MODID);

    public static final Map<Block, Item.Properties> BLOCK_ITEM_WHITELIST = new HashMap<>();

    public static final RegistryObject<WaterlilyBlock> GREEN_ALGAE = BLOCKS.register("green_algae",
            () -> new WaterlilyBlock(copy(Blocks.LILY_PAD)));

    public static final RegistryObject<Block> BORON_BLOCK = BLOCKS.register("boron_block",
            () -> new ChemistryBlock(build(copy(Blocks.COBBLESTONE)).blockItem(true)));

    public static final RegistryObject<Block> CADMIUM_BLOCK = BLOCKS.register("cadmium_block",
            () -> new ChemistryBlock(build(copy(Blocks.COBBLESTONE).strength(1.0f, 4.5f)).blockItem(true)));

    public static final RegistryObject<Block> GADOLINIUM_BLOCK = BLOCKS.register("gadolinium_block",
            () -> new ChemistryBlock(build(copy(Blocks.IRON_BLOCK)).blockItem(true)));

    public static final RegistryObject<Block> HAFNIUM_BLOCK = BLOCKS.register("hafnium_block",
            () -> new ChemistryBlock(build(copy(Blocks.DIAMOND_BLOCK)).blockItem(true)));

    public static final RegistryObject<Block> INDIUM_BLOCK = BLOCKS.register("indium_block",
            () -> new ChemistryBlock(build(copy(Blocks.COBBLESTONE).strength(0.5f, 2.5f)).blockItem(true)));

    public static final RegistryObject<Block> LEAD_BLOCK = BLOCKS.register("lead_block",
            () -> new ChemistryBlock(build(copy(Blocks.COBBLESTONE).strength(5f, 7f)).blockItem(true)));

    public static final RegistryObject<Block> OSMIUM_BLOCK = BLOCKS.register("osmium_block",
            () -> new ChemistryBlock(build(copy(Blocks.IRON_BLOCK).strength(10f, 2f)).blockItem(true)));

    public static final RegistryObject<Block> PALLADIUM_BLOCK = BLOCKS.register("palladium_block",
            () -> new ChemistryBlock(build(copy(Blocks.COBBLESTONE).strength(5f, 7f)).blockItem(true)));

    public static final RegistryObject<Block> RUTHENIUM_BLOCK = BLOCKS.register("ruthenium_block",
            () -> new ChemistryBlock(build(copy(Blocks.DIAMOND_BLOCK)).blockItem(true)));

    public static final RegistryObject<Block> PAINITE = BLOCKS.register("painite",
            () -> new ChemistryBlock(build(copy(Blocks.IRON_ORE)).blockItem(true)));

    public static final RegistryObject<Block> COLEMANITE = BLOCKS.register("colemanite",
            () -> new ChemistryBlock(build(copy(Blocks.IRON_ORE)).blockItem(true)));

    public static final RegistryObject<Block> BORACITE = BLOCKS.register("boracite",
            () -> new ChemistryBlock(build(copy(Blocks.IRON_ORE)).blockItem(true)));

    public static final RegistryObject<Block> BORAX = BLOCKS.register("borax",
            () -> new ChemistryBlock(build(copy(Blocks.IRON_ORE).strength(0.5f, 15.0f)).blockItem(true)));

    /*
     * public static final RegistryObject<Block> ULEXITE =
     * BLOCKS.register("ulexite", () -> new ChemistryBlock(build(of(Material.ROCK,
     * MaterialColor.WHITE_TERRACOTTA)
     * .harvestTool(ToolType.PICKAXE).harvestLevel(1).hardnessAndResistance(3.0f).
     * lightValue(5).sound(SoundType.GLASS).notSolid()).blockItem(true)));
     */

    public static final RegistryObject<Block> PEGMATITE = BLOCKS.register("pegmatite",
            () -> new ChemistryBlock(build(copy(Blocks.DIORITE)).blockItem(true)));

    public static final RegistryObject<Block> ALMANDINE = BLOCKS.register("almandine",
            () -> new ChemistryBlock(build(copy(Blocks.DIORITE)).blockItem(true)));

    public static final RegistryObject<Block> COLUMBITE = BLOCKS.register("columbite",
            () -> new ChemistryBlock(build(copy(Blocks.DIORITE)).blockItem(true)));

    public static final RegistryObject<Block> SPESSARTINE = BLOCKS.register("spessartine",
            () -> new ChemistryBlock(build(copy(Blocks.DIORITE)).blockItem(true)));

    public static final RegistryObject<Block> TANTALITE = BLOCKS.register("tantalite",
            () -> new ChemistryBlock(build(copy(Blocks.DIORITE)).blockItem(true)));

    public static final RegistryObject<Block> CASSITERITE = BLOCKS.register("cassiterite",
            () -> new ChemistryBlock(build(copy(Blocks.DIORITE)).blockItem(true)));

    public static final RegistryObject<Block> HIDDENITE = BLOCKS.register("hiddenite",
            () -> new ChemistryBlock(build(copy(Blocks.DIORITE)).blockItem(true)));

    public static final RegistryObject<Block> KUNZITE = BLOCKS.register("kunzite",
            () -> new ChemistryBlock(build(copy(Blocks.DIORITE)).blockItem(true)));

    public static final RegistryObject<Block> SPODUMENE = BLOCKS.register("spodumene",
            () -> new ChemistryBlock(build(copy(Blocks.DIORITE).dynamicShape()).blockItem(true)));

    public static final RegistryObject<Block> TRIPHANE = BLOCKS.register("triphane",
            () -> new ChemistryBlock(build(copy(Blocks.DIORITE)).blockItem(true)));

    public static final RegistryObject<Block> LEPIDOLITE = BLOCKS.register("lepidolite",
            () -> new ChemistryBlock(build(copy(Blocks.DIORITE)).blockItem(true)));

    public static final RegistryObject<Block> TOURMALINE = BLOCKS.register("tourmaline",
            () -> new ChemistryBlock(build(copy(Blocks.DIORITE).dynamicShape()).blockItem(true)));

    /*
     * public static final RegistryObject<Block> KERNITE =
     * BLOCKS.register("kernite", () -> new ChemistryBlock(new
     * build(of(Material.ROCK,
     * MaterialColor.STONE).harvestTool(ToolType.PICKAXE).harvestLevel(1).
     * hardnessAndResistance(5.0f).lightValue(1).sound(SoundType.GLASS).notSolid()).
     * blockItem(true))));
     */

    public static final RegistryObject<ShaleBlock> SHALE = BLOCKS.register("shale",
            () -> new ShaleBlock(build(of(Material.ICE_SOLID).dynamicShape().noOcclusion()).blockItem(true)));

    public static final RegistryObject<ShaleFracturerBlock> SHALE_FRACTURER = BLOCKS.register(
            "shale_fracturer", () -> new ShaleFracturerBlock<>(build(of(Material.METAL)).blockItem(true)));

    public static final RegistryObject<ShaleFracturerBlock.DrillBlock> SHALE_DRILL = BLOCKS
            .register("shale_drill", () -> new ShaleFracturerBlock.DrillBlock<>(
                    build(of(Material.METAL).strength(-1f)).blockItem(false)));

    @Mod.EventBusSubscriber(modid = TurtyChemistry.MODID, bus = Bus.MOD)
    public static class BlockItems {
        @SubscribeEvent
        public static void registerBlockItems(RegistryEvent.Register<Item> event) {
            BLOCKS.getEntries().stream().filter(RegistryObject::isPresent).map(RegistryObject::get)
                    .filter(block -> BLOCK_ITEM_WHITELIST.containsKey(block)).forEachOrdered(block -> {
                        final var blockItem = new BlockItem(block,
                                new Item.Properties().tab(TurtyChemistry.TAB))
                                        .setRegistryName(block.getRegistryName());
                        event.getRegistry().register(blockItem);
                    });
        }
    }
}
