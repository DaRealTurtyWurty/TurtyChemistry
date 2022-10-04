package io.github.darealturtywurty.turtychemistry.block;

import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockBehaviour;

public class ChemistryBlock extends Block {
    public final int radioactivity;

    public ChemistryBlock(Builder builder) {
        super(builder.properties);
        this.radioactivity = builder.radioactivity;
    }

    public static class Builder {
        private final BlockBehaviour.Properties properties;
        private int radioactivity = 0;

        private Builder(BlockBehaviour.Properties properties) {
            this.properties = properties;
        }

        public static Builder build(BlockBehaviour.Properties properties) {
            return new Builder(properties);
        }

        public Builder radioactivity(int radioactivity) {
            this.radioactivity = radioactivity;
            return this;
        }
    }
}
