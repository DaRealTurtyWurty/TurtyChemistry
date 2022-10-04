package io.github.darealturtywurty.turtychemistry.worldgen.init;

import io.github.darealturtywurty.turtychemistry.TurtyChemistry;
import io.github.darealturtywurty.turtychemistry.init.BlockInit;
import io.github.darealturtywurty.turtychemistry.worldgen.tree.RubberTreeTrunkPlacer;
import net.minecraft.core.Holder;
import net.minecraft.data.worldgen.features.FeatureUtils;
import net.minecraft.util.valueproviders.ConstantInt;
import net.minecraft.util.valueproviders.UniformInt;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.configurations.FeatureConfiguration;
import net.minecraft.world.level.levelgen.feature.configurations.TreeConfiguration;
import net.minecraft.world.level.levelgen.feature.featuresize.TwoLayersFeatureSize;
import net.minecraft.world.level.levelgen.feature.foliageplacers.BlobFoliagePlacer;
import net.minecraft.world.level.levelgen.feature.stateproviders.BlockStateProvider;

public class ConfiguredFeatureInit {
    public static final Holder<ConfiguredFeature<TreeConfiguration, ?>> RUBBER_TREE = register("rubber_tree",
            Feature.TREE,
            new TreeConfiguration.TreeConfigurationBuilder(BlockStateProvider.simple(BlockInit.RUBBER_TREE_BLOCK.get()),
                    new RubberTreeTrunkPlacer(5, 2, 1, ConstantInt.of(5), UniformInt.of(1, 2)),
                    BlockStateProvider.simple(BlockInit.RUBBER_TREE_LEAVES.get()),
                    new BlobFoliagePlacer(ConstantInt.of(2), ConstantInt.of(0), 2),
                    new TwoLayersFeatureSize(1, 0, 1)).ignoreVines().build());

    private static <FC extends FeatureConfiguration, F extends Feature<FC>> Holder<ConfiguredFeature<FC, ?>> register(String name, F feature, FC featureConfiguration) {
        return FeatureUtils.register(TurtyChemistry.MODID + ":" + name, feature, featureConfiguration);
    }
}
