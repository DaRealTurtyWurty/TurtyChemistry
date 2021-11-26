package io.github.darealturtywurty.turtychemistry.common.block;

import org.apache.commons.lang3.tuple.Pair;

import io.github.darealturtywurty.turtychemistry.core.init.BlockInit;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockBehaviour;

public class ChemistryBlock extends Block {

    public ChemistryBlock(Builder builder) {
        super(builder.properties);
        if (builder.blockItem.getLeft()) {
            BlockInit.BLOCK_ITEM_WHITELIST.put(this, builder.blockItem.getRight());
        }
    }

    public static class Builder {
        private final BlockBehaviour.Properties properties;
        private Pair<Boolean, Item.Properties> blockItem = Pair.of(false, null);
        private int radioactivity = 0;

        public Builder(BlockBehaviour.Properties properties) {
            this.properties = properties;
        }

        public Builder blockItem(boolean shouldHave) {
            this.blockItem = Pair.of(shouldHave, this.blockItem.getValue());
            return this;
        }

        public Builder blockItem(boolean shouldHave, Item.Properties properties) {
            this.blockItem = Pair.of(shouldHave, properties);
            return this;
        }

        public Builder radioactivity(int radioactivity) {
            this.radioactivity = radioactivity;
            return this;
        }
    }
}
