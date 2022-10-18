package io.github.darealturtywurty.turtychemistry.block;

import io.github.darealturtywurty.turtychemistry.TurtyChemistry;
import io.github.darealturtywurty.turtychemistry.block.entity.FoundryBlockEntity;
import io.github.darealturtywurty.turtychemistry.init.BlockEntityInit;
import io.github.darealturtywurty.turtychemistry.menu.FoundryMenu;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.SimpleMenuProvider;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.BaseEntityBlock;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraftforge.network.NetworkHooks;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public final class FoundryBlock extends BaseEntityBlock {
    private static final Component TRANSLATION_COMPONENT;

    static {
        TRANSLATION_COMPONENT = Component.translatable("container." + TurtyChemistry.MODID + ".foundry");
    }

    public FoundryBlock(Properties pProperties) {
        super(pProperties);
    }

    public static InteractionResult use(Level level, BlockPos pos, Player player) {
        if (!level.isClientSide()) {
            if (level.getBlockEntity(pos) instanceof FoundryBlockEntity blockEntity) {
                final SimpleMenuProvider provider = new SimpleMenuProvider(FoundryMenu.getServerMenu(blockEntity, pos),
                        TRANSLATION_COMPONENT);
                NetworkHooks.openScreen((ServerPlayer) player, provider, pos);
            }
        }

        return InteractionResult.sidedSuccess(level.isClientSide());
    }

    @Nullable
    @Override
    public BlockEntity newBlockEntity(@NotNull BlockPos pPos, @NotNull BlockState pState) {
        return BlockEntityInit.FOUNDRY.get().create(pPos, pState);
    }

    @Override
    public InteractionResult use(@NotNull BlockState pState, @NotNull Level pLevel, @NotNull BlockPos pPos, @NotNull Player pPlayer, @NotNull InteractionHand pHand, @NotNull BlockHitResult pHit) {
        return use(pLevel, pPos, pPlayer);
    }
}
