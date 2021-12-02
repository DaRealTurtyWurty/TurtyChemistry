package io.github.darealturtywurty.turtychemistry.common.block;

import io.github.darealturtywurty.turtychemistry.common.block.core.ChemistryBlock;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.block.state.properties.IntegerProperty;

public class ShaleBlock extends ChemistryBlock {
    public static final IntegerProperty LAYER = IntegerProperty.create("layers", 0, 16);
    public static final BooleanProperty CRACKED = BooleanProperty.create("cracked");
    public static final BooleanProperty HAS_GAS = BooleanProperty.create("has_gas");

    public ShaleBlock(ChemistryBlock.Builder builder) {
        super(builder);
        registerDefaultState(
                defaultBlockState().setValue(LAYER, 16).setValue(CRACKED, false).setValue(HAS_GAS, false));
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        super.createBlockStateDefinition(builder);
        builder.add(LAYER, CRACKED, HAS_GAS);
    }
}
