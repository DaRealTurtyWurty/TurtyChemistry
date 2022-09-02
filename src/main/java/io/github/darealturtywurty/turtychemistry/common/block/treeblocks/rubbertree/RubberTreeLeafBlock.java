package io.github.darealturtywurty.turtychemistry.common.block.treeblocks.rubbertree;

import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.LeavesBlock;
import net.minecraft.world.level.block.state.BlockBehaviour;

public final class RubberTreeLeafBlock extends LeavesBlock {
    //TODO:add any custom properties to this block if need be
    public RubberTreeLeafBlock() {
        super(BlockBehaviour.Properties.copy(Blocks.ACACIA_LEAVES));
    }
}
