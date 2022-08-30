package io.github.darealturtywurty.turtychemistry.core.multiblocks;

import io.github.darealturtywurty.turtychemistry.TurtyChemistry;
import io.github.darealturtywurty.turtychemistry.core.init.BlockInit;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.IForgeRegistry;
import net.minecraftforge.registries.RegistryBuilder;
import net.minecraftforge.registries.RegistryObject;

import java.util.function.Predicate;
import java.util.function.Supplier;

public class MultiblockRegistry {
    public static final DeferredRegister<Multiblock> MULTIBLOCKS =
            DeferredRegister.create(new ResourceLocation(TurtyChemistry.MODID, "multiblocks"), TurtyChemistry.MODID);

    public static final Supplier<IForgeRegistry<Multiblock>> REGISTRY = MULTIBLOCKS.makeRegistry(RegistryBuilder::new);

    public static final RegistryObject<Multiblock> TEST = MULTIBLOCKS.register("test",
            () -> new Multiblock(
                    Multiblock.Builder.start()
                            .aisle("ABC", "ABC", "ABC")
                            .where('A', block(Blocks.STONE))
                            .where('B', block(Blocks.DIRT))
                            .where('C', block(Blocks.DIAMOND_BLOCK))
                            .finish()
                            .controller(0, 0, 0, BlockInit.BORAX.get().defaultBlockState())
                    )
            );

    private static Predicate<BlockState> block(Block block) {
        return state -> state.is(block);
    }
}