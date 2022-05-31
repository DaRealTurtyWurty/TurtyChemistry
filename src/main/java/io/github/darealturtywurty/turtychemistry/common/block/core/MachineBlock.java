package io.github.darealturtywurty.turtychemistry.common.block.core;

import net.minecraft.world.level.block.EntityBlock;

public abstract class MachineBlock extends ChemistryBlock implements EntityBlock {
    protected MachineBlock(ChemistryBlock.Builder builder) {
        super(builder);
    }
}
