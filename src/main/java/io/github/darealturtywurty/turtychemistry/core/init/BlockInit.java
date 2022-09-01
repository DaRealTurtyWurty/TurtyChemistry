package io.github.darealturtywurty.turtychemistry.core.init;

import io.github.darealturtywurty.turtychemistry.TurtyChemistry;
import io.github.darealturtywurty.turtychemistry.common.block.ClayAlloyFurnaceBlock;
import io.github.darealturtywurty.turtychemistry.common.block.core.ChemistryBlock;
import io.github.darealturtywurty.turtychemistry.common.block.treeblocks.RubberTreeBlock;
import io.github.darealturtywurty.turtychemistry.common.block.treeblocks.RubberTreeBlockStripped;
import io.github.darealturtywurty.turtylib.TurtyLib;
import io.github.darealturtywurty.turtylib.core.init.AbstractInit;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.WaterlilyBlock;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.Material;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import java.util.function.Supplier;

import static io.github.darealturtywurty.turtychemistry.common.block.core.ChemistryBlock.Builder.build;
import static net.minecraft.world.level.block.state.BlockBehaviour.Properties.copy;

public final class BlockInit extends AbstractInit {

    public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS,
        TurtyChemistry.MODID);

    public static final RegistryObject<WaterlilyBlock> GREEN_ALGAE = BLOCKS.register("green_algae",
        () -> new WaterlilyBlock(copy(Blocks.LILY_PAD)));

    public static final RegistryObject<Block> BORON_BLOCK = register("boron_block",
        () -> new ChemistryBlock(build(copy(Blocks.COBBLESTONE))), new Item.Properties().tab(TurtyChemistry.TAB));

    public static final RegistryObject<Block> CADMIUM_BLOCK = register("cadmium_block",
        () -> new ChemistryBlock(build(copy(Blocks.COBBLESTONE).strength(1.0f, 4.5f))),
        new Item.Properties().tab(TurtyChemistry.TAB));

    public static final RegistryObject<Block> GADOLINIUM_BLOCK = register("gadolinium_block",
        () -> new ChemistryBlock(build(copy(Blocks.IRON_BLOCK))), new Item.Properties().tab(TurtyChemistry.TAB));

    public static final RegistryObject<Block> HAFNIUM_BLOCK = register("hafnium_block",
        () -> new ChemistryBlock(build(copy(Blocks.DIAMOND_BLOCK))), new Item.Properties().tab(TurtyChemistry.TAB));

    public static final RegistryObject<Block> INDIUM_BLOCK = register("indium_block",
        () -> new ChemistryBlock(build(copy(Blocks.COBBLESTONE).strength(0.5f, 2.5f))),
        new Item.Properties().tab(TurtyChemistry.TAB));

    public static final RegistryObject<Block> LEAD_BLOCK = register("lead_block",
        () -> new ChemistryBlock(build(copy(Blocks.COBBLESTONE).strength(5f, 7f))),
        new Item.Properties().tab(TurtyChemistry.TAB));

    public static final RegistryObject<Block> OSMIUM_BLOCK = register("osmium_block",
        () -> new ChemistryBlock(build(copy(Blocks.IRON_BLOCK).strength(10f, 2f))),
        new Item.Properties().tab(TurtyChemistry.TAB));

    public static final RegistryObject<Block> PALLADIUM_BLOCK = register("palladium_block",
        () -> new ChemistryBlock(build(copy(Blocks.COBBLESTONE).strength(5f, 7f))),
        new Item.Properties().tab(TurtyChemistry.TAB));

    public static final RegistryObject<Block> RUTHENIUM_BLOCK = register("ruthenium_block",
        () -> new ChemistryBlock(build(copy(Blocks.DIAMOND_BLOCK))), new Item.Properties().tab(TurtyChemistry.TAB));

    public static final RegistryObject<Block> PAINITE = register("painite",
        () -> new ChemistryBlock(build(copy(Blocks.IRON_ORE))), new Item.Properties().tab(TurtyChemistry.TAB));

    public static final RegistryObject<Block> COLEMANITE = register("colemanite",
        () -> new ChemistryBlock(build(copy(Blocks.IRON_ORE))), new Item.Properties().tab(TurtyChemistry.TAB));

    public static final RegistryObject<Block> BORACITE = register("boracite",
        () -> new ChemistryBlock(build(copy(Blocks.IRON_ORE))), new Item.Properties().tab(TurtyChemistry.TAB));

    public static final RegistryObject<Block> BORAX = register("borax",
        () -> new ChemistryBlock(build(copy(Blocks.IRON_ORE).strength(0.5f, 15.0f))),
        new Item.Properties().tab(TurtyChemistry.TAB));

    /*
     * public static final RegistryObject<Block> ULEXITE = register("ulexite", () ->
     * new ChemistryBlock(build(of(Material.ROCK, MaterialColor.WHITE_TERRACOTTA)
     * .harvestTool(ToolType.PICKAXE).harvestLevel(1).hardnessAndResistance(3.0f).
     * lightValue(5).sound(SoundType.GLASS).notSolid(), new
     * Item.Properties().tab(TurtyChemistry.TAB));
     */

    public static final RegistryObject<Block> PEGMATITE = register("pegmatite",
        () -> new ChemistryBlock(build(copy(Blocks.DIORITE))), new Item.Properties().tab(TurtyChemistry.TAB));

    public static final RegistryObject<Block> ALMANDINE = register("almandine",
        () -> new ChemistryBlock(build(copy(Blocks.DIORITE))), new Item.Properties().tab(TurtyChemistry.TAB));

    public static final RegistryObject<Block> COLUMBITE = register("columbite",
        () -> new ChemistryBlock(build(copy(Blocks.DIORITE))), new Item.Properties().tab(TurtyChemistry.TAB));

    public static final RegistryObject<Block> SPESSARTINE = register("spessartine",
        () -> new ChemistryBlock(build(copy(Blocks.DIORITE))), new Item.Properties().tab(TurtyChemistry.TAB));

    public static final RegistryObject<Block> TANTALITE = register("tantalite",
        () -> new ChemistryBlock(build(copy(Blocks.DIORITE))), new Item.Properties().tab(TurtyChemistry.TAB));

    public static final RegistryObject<Block> CASSITERITE = register("cassiterite",
        () -> new ChemistryBlock(build(copy(Blocks.DIORITE))), new Item.Properties().tab(TurtyChemistry.TAB));

    public static final RegistryObject<Block> HIDDENITE = register("hiddenite",
        () -> new ChemistryBlock(build(copy(Blocks.DIORITE))), new Item.Properties().tab(TurtyChemistry.TAB));

    public static final RegistryObject<Block> KUNZITE = register("kunzite",
        () -> new ChemistryBlock(build(copy(Blocks.DIORITE))), new Item.Properties().tab(TurtyChemistry.TAB));

    public static final RegistryObject<Block> SPODUMENE = register("spodumene",
        () -> new ChemistryBlock(build(copy(Blocks.DIORITE).dynamicShape())),
        new Item.Properties().tab(TurtyChemistry.TAB));

    public static final RegistryObject<Block> TRIPHANE = register("triphane",
        () -> new ChemistryBlock(build(copy(Blocks.DIORITE))), new Item.Properties().tab(TurtyChemistry.TAB));

    public static final RegistryObject<Block> LEPIDOLITE = register("lepidolite",
        () -> new ChemistryBlock(build(copy(Blocks.DIORITE))), new Item.Properties().tab(TurtyChemistry.TAB));

    public static final RegistryObject<Block> TOURMALINE = register("tourmaline",
        () -> new ChemistryBlock(build(copy(Blocks.DIORITE).dynamicShape())),
        new Item.Properties().tab(TurtyChemistry.TAB));

    /*
     * public static final RegistryObject<Block> KERNITE = register("kernite", () ->
     * new ChemistryBlock(new build(of(Material.ROCK,
     * MaterialColor.STONE).harvestTool(ToolType.PICKAXE).harvestLevel(1).
     * hardnessAndResistance(5.0f).lightValue(1).sound(SoundType.GLASS).notSolid()).
     * blockItem(true), new Item.Properties().tab(TurtyChemistry.TAB));
     */

    public static final RegistryObject<ClayAlloyFurnaceBlock> CLAY_ALLOY_FURNACE = BLOCKS.register("clay_alloy_furnace",
            () -> new ClayAlloyFurnaceBlock(BlockBehaviour.Properties.copy(Blocks.TERRACOTTA).dynamicShape().noOcclusion()));

    public static final RegistryObject<RubberTreeBlock> RUBBER_TREE_BLOCK = register("rubber_tree_block",
            () -> new RubberTreeBlock(BlockBehaviour.Properties.of(Material.WOOD)),new Item.Properties().tab(TurtyChemistry.TAB));
    public static final RegistryObject<RubberTreeBlockStripped> RUBBER_TREE_BLOCK_STRIPPED = register("rubber_tree_block_stripped",
            () -> new RubberTreeBlockStripped(BlockBehaviour.Properties.of(Material.WOOD)),new Item.Properties().tab(TurtyChemistry.TAB));


    private static <T extends Block> RegistryObject<T> register(String name, Supplier<T> supplier,
        Item.Properties properties) {
        final RegistryObject<T> block = BLOCKS.register(name, supplier);
        ItemInit.ITEMS.register(name, () -> new BlockItem(block.get(), properties));
        return block;
    }
}
