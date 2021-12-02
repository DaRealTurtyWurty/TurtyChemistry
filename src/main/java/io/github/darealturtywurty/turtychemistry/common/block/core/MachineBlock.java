package io.github.darealturtywurty.turtychemistry.common.block.core;

import net.minecraft.world.level.block.EntityBlock;
import net.minecraft.world.level.block.entity.BlockEntity;

public abstract class MachineBlock<Type extends BlockEntity> extends ChemistryBlock implements EntityBlock {

    public MachineBlock(ChemistryBlock.Builder builder) {
        super(builder);
    }
}
