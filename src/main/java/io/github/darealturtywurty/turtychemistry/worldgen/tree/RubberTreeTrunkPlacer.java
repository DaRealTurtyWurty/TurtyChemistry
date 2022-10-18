package io.github.darealturtywurty.turtychemistry.worldgen.tree;

import com.google.common.collect.Lists;
import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import io.github.darealturtywurty.turtychemistry.worldgen.init.TrunkPlacerInit;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.util.RandomSource;
import net.minecraft.util.valueproviders.IntProvider;
import net.minecraft.world.level.LevelSimulatedReader;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.feature.configurations.TreeConfiguration;
import net.minecraft.world.level.levelgen.feature.foliageplacers.FoliagePlacer;
import net.minecraft.world.level.levelgen.feature.trunkplacers.TrunkPlacer;
import net.minecraft.world.level.levelgen.feature.trunkplacers.TrunkPlacerType;
import org.jetbrains.annotations.NotNull;

import java.util.List;
import java.util.function.BiConsumer;

public final class RubberTreeTrunkPlacer extends TrunkPlacer {
    public static final Codec<RubberTreeTrunkPlacer> CODEC = RecordCodecBuilder.create(
            (codec) -> trunkPlacerParts(codec).and(codec.group(IntProvider.POSITIVE_CODEC.fieldOf("branch_start_height")
                                    .forGetter((placer) -> placer.branchStartHeight),
                            IntProvider.NON_NEGATIVE_CODEC.fieldOf("branch_length").forGetter((placer) -> placer.branchLength)))
                    .apply(codec, RubberTreeTrunkPlacer::new));

    private final IntProvider branchStartHeight;
    private final IntProvider branchLength;

    public RubberTreeTrunkPlacer(int pBaseHeight, int pHeightRandA, int pHeightRandB, IntProvider branchStartHeight, IntProvider branchLength) {
        super(pBaseHeight, pHeightRandA, pHeightRandB);
        this.branchStartHeight = branchStartHeight;
        this.branchLength = branchLength;
    }

    @Override
    protected @NotNull TrunkPlacerType<?> type() {
        return TrunkPlacerInit.RUBBER_TREE_TRUNK_PLACER;
    }

    @Override
    public @NotNull List<FoliagePlacer.FoliageAttachment> placeTrunk(@NotNull LevelSimulatedReader pLevel, @NotNull BiConsumer<BlockPos, BlockState> pBlockSetter, @NotNull RandomSource pRandom, int pFreeTreeHeight, BlockPos pPos, @NotNull TreeConfiguration pConfig) {
        final List<FoliagePlacer.FoliageAttachment> list = Lists.newArrayList();
        setDirtAt(pLevel, pBlockSetter, pRandom, pPos.below(), pConfig);
        list.add(new FoliagePlacer.FoliageAttachment(pPos.above(pFreeTreeHeight), 0, false));

        final int height = this.branchStartHeight.sample(pRandom);
        float branchingPossibility = 0.8F;
        Direction branchingDirection = null;
        for (int yPos = 0; yPos < pFreeTreeHeight; ++yPos) {
            this.placeLog(pLevel, pBlockSetter, pRandom, pPos.above(yPos), pConfig);

            if (yPos >= height - 1) {
                if (pRandom.nextFloat() < branchingPossibility) {
                    branchingPossibility = branchingPossibility * branchingPossibility;

                    Direction direction;
                    do {
                        direction = Direction.Plane.HORIZONTAL.getRandomDirection(pRandom);
                    } while (direction == branchingDirection);

                    branchingDirection = direction;

                    BlockPos.MutableBlockPos blockPos$mutableBlockPos = pPos.above(yPos)
                            .offset(branchingDirection.getStepX(), 0, branchingDirection.getStepZ()).mutable();
                    int xOffset = 0;
                    int zOffset = 0;
                    int length = this.branchLength.sample(pRandom);
                    for (int hPos = 0; hPos < length; ++hPos) {
                        this.placeLog(pLevel, pBlockSetter, pRandom,
                                blockPos$mutableBlockPos.setWithOffset(blockPos$mutableBlockPos, xOffset, hPos, zOffset)
                                        .immutable(), pConfig);

                        if (hPos == 0 || pRandom.nextFloat() > 0.6F) {
                            xOffset += branchingDirection.getStepX();
                            zOffset += branchingDirection.getStepZ();
                        }
                    }

                    list.add(new FoliagePlacer.FoliageAttachment(blockPos$mutableBlockPos.immutable().above(1), 0,
                            false));
                }
            }
        }

        return list;
    }
}
