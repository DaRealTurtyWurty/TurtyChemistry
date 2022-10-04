package io.github.darealturtywurty.turtychemistry.worldgen.tree;

import io.github.darealturtywurty.turtychemistry.worldgen.init.ConfiguredFeatureInit;
import net.minecraft.core.Holder;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.block.grower.AbstractTreeGrower;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class RubberTreeGrower extends AbstractTreeGrower {
    @Nullable
    @Override
    protected Holder<? extends ConfiguredFeature<?, ?>> getConfiguredFeature(@NotNull RandomSource random,
            boolean hasFlowers) {
        return ConfiguredFeatureInit.RUBBER_TREE;
    }
}
