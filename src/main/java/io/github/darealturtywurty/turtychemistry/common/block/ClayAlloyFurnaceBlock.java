package io.github.darealturtywurty.turtychemistry.common.block;

import io.github.darealturtywurty.turtychemistry.core.init.BlockEntityInit;
import io.github.darealturtywurty.turtylib.common.blockentity.TickableBlockEntity;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.EntityBlock;
import net.minecraft.world.level.block.RenderShape;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityTicker;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.shapes.BooleanOp;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class ClayAlloyFurnaceBlock extends Block implements EntityBlock {
    private static final VoxelShape SHAPE = createShape();

    public ClayAlloyFurnaceBlock(Properties properties) {
        super(properties);
    }

    private static VoxelShape createShape() {
        VoxelShape shape = Shapes.empty();
        shape = Shapes.join(shape, Shapes.box(0.1875, 2, 0.8125, 0.3125, 3, 1.1875), BooleanOp.OR);
        shape = Shapes.join(shape, Shapes.box(-0.3125, 2, 1.1875, 0.3125, 3, 1.3125), BooleanOp.OR);
        shape = Shapes.join(shape, Shapes.box(-0.3125, 2, 0.6875, 0.3125, 3, 0.8125), BooleanOp.OR);
        shape = Shapes.join(shape, Shapes.box(-0.3125, 2, 0.8125, -0.1875, 3, 1.1875), BooleanOp.OR);
        shape = Shapes.join(shape, Shapes.box(-1, 1.6875, 0, -0.1875, 2, 2), BooleanOp.OR);
        shape = Shapes.join(shape, Shapes.box(-1, 0, 0, 1, 0.3125, 2), BooleanOp.OR);
        shape = Shapes.join(shape, Shapes.box(-1, 0.3125, 0, -0.6875, 1.6875, 2), BooleanOp.OR);
        shape = Shapes.join(shape, Shapes.box(0.6875, 0.3125, 0, 1, 1.6875, 2), BooleanOp.OR);
        shape = Shapes.join(shape, Shapes.box(-0.6875, 0.3125, 1.6875, 0.6875, 1.6875, 2), BooleanOp.OR);
        shape = Shapes.join(shape, Shapes.box(-0.6875, 1.25, 0, 0.6875, 1.6875, 0.3125), BooleanOp.OR);
        shape = Shapes.join(shape, Shapes.box(-0.6875, 0.3125, 0, 0.6875, 0.6875, 0.3125), BooleanOp.OR);
        shape = Shapes.join(shape, Shapes.box(0.1875, 1.6875, 0, 1, 2, 2), BooleanOp.OR);
        shape = Shapes.join(shape, Shapes.box(-0.1875, 1.6875, 0, 0.1875, 2, 0.8125), BooleanOp.OR);
        shape = Shapes.join(shape, Shapes.box(-0.1875, 1.6875, 1.1875, 0.1875, 2, 2), BooleanOp.OR);

        return shape;
    }

    @Override
    public @NotNull RenderShape getRenderShape(@NotNull BlockState state) {
        return RenderShape.INVISIBLE;
    }

    @Nullable
    @Override
    public BlockEntity newBlockEntity(@NotNull BlockPos pos, @NotNull BlockState state) {
        return BlockEntityInit.CLAY_ALLOY_FURNACE.get().create(pos, state);
    }

    @Nullable
    @Override
    public <T extends BlockEntity> BlockEntityTicker<T> getTicker(@NotNull Level level, @NotNull BlockState state, @NotNull BlockEntityType<T> type) {
        return level.isClientSide() ? null : ($0, $1, $2, blockEntity) -> ((TickableBlockEntity) blockEntity).tick();
    }

    @Override
    public @NotNull VoxelShape getShape(@NotNull BlockState state, @NotNull BlockGetter level, @NotNull BlockPos pos, @NotNull CollisionContext ctx) {
        return SHAPE;
    }
}
