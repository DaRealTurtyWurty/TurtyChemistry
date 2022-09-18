package io.github.darealturtywurty.turtychemistry.common.item;

import net.minecraft.world.item.Item;

public class ChemistryItem extends Item {

    public final int radioactivity;

    public ChemistryItem(Builder builder) {
        super(builder.properties);
        this.radioactivity = builder.radioactivity;
    }


    public static class Builder {
        private final Item.Properties properties;
        private int radioactivity = 0;

        public Builder(Item.Properties properties) {
            this.properties = properties;
        }

        public Builder radioactivity(int radioactivity) {
            this.radioactivity = radioactivity;
            return this;
        }
    }
}
