package io.github.darealturtywurty.turtychemistry.core.world.levelgen.feature.trunkplacers;

import com.mojang.serialization.Codec;
import io.github.darealturtywurty.turtychemistry.TurtyChemistry;
import net.minecraft.core.Registry;
import net.minecraft.world.level.levelgen.feature.trunkplacers.TrunkPlacer;
import net.minecraft.world.level.levelgen.feature.trunkplacers.TrunkPlacerType;

public class ModTrunkPlacers {
    public static final TrunkPlacerType<RubberTreeTrunkPlacer> RUBBER_TREE_TRUNK_PLACER = register("straight_trunk_placer", RubberTreeTrunkPlacer.CODEC);

    public static <T extends TrunkPlacer> TrunkPlacerType<T> register(String name, Codec<T> codec) {
        return Registry.register(Registry.TRUNK_PLACER_TYPES, TurtyChemistry.MODID + ":" + name, new TrunkPlacerType<>(codec));
    }

}
