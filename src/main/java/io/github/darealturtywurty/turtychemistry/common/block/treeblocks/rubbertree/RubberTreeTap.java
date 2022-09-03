package io.github.darealturtywurty.turtychemistry.common.block.treeblocks.rubbertree;

import io.github.darealturtywurty.turtychemistry.common.block.container.RubberTreeTapContainer;
import io.github.darealturtywurty.turtychemistry.common.block.entity.RubberTreeTapBlockEntity;
import io.github.darealturtywurty.turtychemistry.core.init.BlockEntityInit;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.MenuProvider;
import net.minecraft.world.SimpleMenuProvider;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.EntityBlock;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraftforge.network.NetworkHooks;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class RubberTreeTap extends Block implements EntityBlock {
    public static final BooleanProperty IS_TAPPING = BooleanProperty.create("is_tapping");
    public static final Component TITLE = Component.literal("Rubber Tree Tap");

    public RubberTreeTap(final Properties properties) {
        super(properties);
    }

    @Nullable
    @Override
    public BlockEntity newBlockEntity(final @NotNull BlockPos pos, final @NotNull BlockState state) {
        return BlockEntityInit.RUBBER_TREE_TAP.get().create(pos, state);
    }

    @Override
    public InteractionResult use(final @NotNull BlockState state, final Level level, final @NotNull BlockPos pos,
                                 final @NotNull Player player, final @NotNull InteractionHand hand, final @NotNull BlockHitResult result) {
        if (!level.isClientSide() && level.getBlockEntity(pos) instanceof final RubberTreeTapBlockEntity rubberTreeTapBlockEntity) {
            final MenuProvider menuProvider = new SimpleMenuProvider
                    (RubberTreeTapContainer.getServerMenu(rubberTreeTapBlockEntity, pos), TITLE);
            NetworkHooks.openScreen((ServerPlayer) player, menuProvider, pos);
        }
        return InteractionResult.SUCCESS;
    }

    @Nullable
    @Override
    public BlockState getStateForPlacement(final @NotNull BlockPlaceContext ctx) {
        return super.getStateForPlacement(ctx).setValue(IS_TAPPING, false);
    }

    @Override
    protected void createBlockStateDefinition(final StateDefinition.Builder<Block, BlockState> stateBuilder) {
        super.createBlockStateDefinition(stateBuilder.add(IS_TAPPING));
    }
}
