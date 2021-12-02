package io.github.darealturtywurty.turtychemistry.network.syncdata;

import io.github.darealturtywurty.turtychemistry.common.block.entity.ShaleFracturerBlockEntity;
import net.minecraft.world.inventory.SimpleContainerData;

public class ShaleFracturerSyncData extends SimpleContainerData {
    private final ShaleFracturerBlockEntity blockEntity;

    public ShaleFracturerSyncData(ShaleFracturerBlockEntity blockEntity) {
        super(0);
        this.blockEntity = blockEntity;
    }

    @Override
    public int get(int value) {
        return super.get(value);
    }

    @Override
    public void set(int key, int value) {
        super.set(key, value);
    }
}
