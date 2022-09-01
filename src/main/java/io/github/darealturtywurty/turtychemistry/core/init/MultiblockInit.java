package io.github.darealturtywurty.turtychemistry.core.init;

import io.github.darealturtywurty.turtychemistry.TurtyChemistry;
import io.github.darealturtywurty.turtylib.TurtyLib;
import io.github.darealturtywurty.turtylib.core.multiblock.Multiblock;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.predicate.BlockStatePredicate;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;

public class MultiblockInit {
    public static final DeferredRegister<Multiblock> MULTIBLOCKS = DeferredRegister.create(TurtyLib.MULTIBLOCK_REGISTRY_KEY, TurtyChemistry.MODID);

    public static final RegistryObject<Multiblock> CLAY_ALLOY_FURNACE = MULTIBLOCKS.register("clay_alloy_furnace",
            () -> new Multiblock(
                    Multiblock.Builder.start()
                            .aisle("AA", "AA")
                            .aisle("AA", "AA")
                            .aisle("..", ".B")
                            .where('A', BlockStatePredicate.forBlock(Blocks.TERRACOTTA))
                            .where('B', BlockStatePredicate.forBlock(Blocks.FLOWER_POT))
                            .where('.', BlockStatePredicate.ANY)
                            .finish()
                            .controller(0, 0, 0, BlockInit.CLAY_ALLOY_FURNACE.get().defaultBlockState())
            )
    );
}
