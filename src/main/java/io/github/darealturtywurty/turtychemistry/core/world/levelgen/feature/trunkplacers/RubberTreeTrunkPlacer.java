package io.github.darealturtywurty.turtychemistry.core.world.levelgen.feature.trunkplacers;

import com.google.common.collect.Lists;
import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
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

import java.util.List;
import java.util.function.BiConsumer;

public class RubberTreeTrunkPlacer extends TrunkPlacer {
    public static final Codec<RubberTreeTrunkPlacer> CODEC = RecordCodecBuilder.create((p_226236_) -> {
        return trunkPlacerParts(p_226236_).and(p_226236_.group(IntProvider.POSITIVE_CODEC.fieldOf("branch_start_height").forGetter((p_226242_) -> {
            return p_226242_.branchStartHeight;
        }), IntProvider.NON_NEGATIVE_CODEC.fieldOf("branch_length").forGetter((p_226238_) -> {
            return p_226238_.branchLength;
        }))).apply(p_226236_, RubberTreeTrunkPlacer::new);
    });

    private final IntProvider branchStartHeight;
    private final IntProvider branchLength;

    public RubberTreeTrunkPlacer(int pBaseHeight, int pHeightRandA, int pHeightRandB, IntProvider branchStartHeight, IntProvider branchLength) {
        super(pBaseHeight, pHeightRandA, pHeightRandB);
        this.branchStartHeight = branchStartHeight;
        this.branchLength = branchLength;
    }

    @Override
    protected TrunkPlacerType<?> type() {
        return ModTrunkPlacers.RUBBER_TREE_TRUNK_PLACER;
    }

    @Override
    public List<FoliagePlacer.FoliageAttachment> placeTrunk(LevelSimulatedReader pLevel, BiConsumer<BlockPos, BlockState> pBlockSetter, RandomSource pRandom, int pFreeTreeHeight, BlockPos pPos, TreeConfiguration pConfig) {
        List<FoliagePlacer.FoliageAttachment> list = Lists.newArrayList();
        setDirtAt(pLevel, pBlockSetter, pRandom, pPos.below(), pConfig);
        list.add(new FoliagePlacer.FoliageAttachment(pPos.above(pFreeTreeHeight), 0, false));
        int j = this.branchStartHeight.sample(pRandom);
        float branchingPossibility = 0.8F;
        Direction branchingDirection = null;

        for(int i = 0; i < pFreeTreeHeight; ++i) {
            this.placeLog(pLevel, pBlockSetter, pRandom, pPos.above(i), pConfig);

            if (i >= j - 1) {
                if (pRandom.nextFloat() < branchingPossibility) {
                    branchingPossibility = branchingPossibility * branchingPossibility;

                    Direction direction;
                    do {
                        direction = Direction.Plane.HORIZONTAL.getRandomDirection(pRandom);
                    } while (direction == branchingDirection);

                    branchingDirection = direction;

                    BlockPos.MutableBlockPos blockPos$mutableBlockPos = pPos.above(i).offset(branchingDirection.getStepX(), 0, branchingDirection.getStepZ()).mutable();
                    int xOffset = 0;
                    int zOffset = 0;
                    int k = this.branchLength.sample(pRandom);

                    for (int l = 0; l < k; ++l) {
                        this.placeLog(pLevel, pBlockSetter, pRandom, blockPos$mutableBlockPos.setWithOffset(blockPos$mutableBlockPos, xOffset, l, zOffset).immutable(), pConfig);

                        if (l == 0 || pRandom.nextFloat() > 0.6F) {
                            xOffset += branchingDirection.getStepX();
                            zOffset += branchingDirection.getStepZ();
                        }
                    }
                    list.add(new FoliagePlacer.FoliageAttachment(blockPos$mutableBlockPos.immutable().above(1), 0, false));
                }
            }
        }

        return list;
    }
}
