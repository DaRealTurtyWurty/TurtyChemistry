package io.github.darealturtywurty.turtychemistry.common.block.entity;

import io.github.darealturtywurty.turtychemistry.core.init.BlockEntityInit;
import io.github.darealturtywurty.turtychemistry.core.init.MultiblockInit;
import io.github.darealturtywurty.turtylib.common.blockentity.ModularBlockEntity;
import io.github.darealturtywurty.turtylib.common.blockentity.module.MultiblockModule;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.state.BlockState;

public class ClayAlloyFurnaceBlockEntity extends ModularBlockEntity {
    public final MultiblockModule multiblock;

    public ClayAlloyFurnaceBlockEntity(BlockPos pos, BlockState state) {
        super(BlockEntityInit.CLAY_ALLOY_FURNACE.get(), pos, state);
        this.multiblock = addModule(new MultiblockModule(MultiblockInit.CLAY_ALLOY_FURNACE));
    }
}
