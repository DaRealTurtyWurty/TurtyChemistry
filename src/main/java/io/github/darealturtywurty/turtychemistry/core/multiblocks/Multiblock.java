package io.github.darealturtywurty.turtychemistry.core.multiblocks;

import com.mojang.authlib.yggdrasil.response.HasJoinedMinecraftServerResponse;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Vec3i;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.pattern.BlockInWorld;
import net.minecraft.world.level.block.state.pattern.BlockPattern;
import net.minecraft.world.level.block.state.pattern.BlockPatternBuilder;
import org.apache.commons.lang3.tuple.Pair;

import java.util.*;
import java.util.function.Predicate;

public class Multiblock {
    private final BlockPattern patternMatcher;
    private final List<Predicate<BlockState>> validStates;
    private final Pair<Vec3i, BlockState> controller;

    public Multiblock(Builder builder) {
        this.patternMatcher = builder.pattern;
        this.validStates = builder.validStates.stream().toList();
        this.controller = builder.controller;
    }

    public List<Predicate<BlockState>> getValidStates()
    {
        return validStates;
    }

    public BlockPattern getPatternMatcher() {
        return this.patternMatcher;
    }

    public boolean isValid(BlockState state) {
        return this.validStates.stream().anyMatch(it -> it.test(state));
    }

    public Pair<Vec3i, BlockState> getController() {
        return this.controller;
    }

    public static class Builder {
        private BlockPattern pattern;
        private final Set<Predicate<BlockState>> validStates = new HashSet<>();
        private Pair<Vec3i, BlockState> controller;

        public final class Pattern {
            private Pattern() {}

            private final BlockPatternBuilder pattern = BlockPatternBuilder.start();
            private final Set<Predicate<BlockState>> validStates = new HashSet<>();

            public Pattern where(char key, Predicate<BlockState> state) {
                this.validStates.add(state);
                this.pattern.where(key, BlockInWorld.hasState(state));
                return this;
            }

            public Pattern aisle(String... aisles) {
                this.pattern.aisle(aisles);
                return this;
            }

            public Builder finish() {
                Builder.this.pattern = pattern.build();
                Builder.this.validStates.addAll(this.validStates);
                return Builder.this;
            }
        }

        private Pattern pattern() {
            return new Pattern();
        }

        public static Pattern start() {
            Builder builder = new Builder();
            return builder.pattern();
        }

        public Builder controller(int x, int y, int z, BlockState state) {
            if(x < 0 || x > this.pattern.getWidth())
                throw new IndexOutOfBoundsException("'x' is out of the range of this multiblock. The width is: " + this.pattern.getWidth());

            if(y < 0 || y > this.pattern.getHeight())
                throw new IndexOutOfBoundsException("'y' is out of the range of this multiblock. The height is: " + this.pattern.getHeight());

            if(z < 0 || z > this.pattern.getDepth())
                throw new IndexOutOfBoundsException("'z' is out of the range of this multiblock. The depth is: " + this.pattern.getDepth());

            this.controller = Pair.of(new Vec3i(x, y, z), state);
            return this;
        }
    }
}
