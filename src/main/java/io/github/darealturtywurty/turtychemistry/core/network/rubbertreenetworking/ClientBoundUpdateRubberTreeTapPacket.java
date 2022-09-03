package io.github.darealturtywurty.turtychemistry.core.network.rubbertreenetworking;

import io.github.darealturtywurty.turtychemistry.common.block.treeblocks.rubbertree.RubberTreeTap;
import io.github.darealturtywurty.turtychemistry.core.init.BlockInit;
import net.minecraft.client.Minecraft;
import net.minecraft.core.BlockPos;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.fml.DistExecutor;
import net.minecraftforge.network.NetworkEvent;

import java.util.function.Supplier;

public final class ClientBoundUpdateRubberTreeTapPacket {
    public final BlockPos treeTapBlockPos;
    public final boolean isTapping;

    public ClientBoundUpdateRubberTreeTapPacket(final BlockPos pos, final boolean isTapping) {
        this.treeTapBlockPos = pos;
        this.isTapping = isTapping;
    }

    public ClientBoundUpdateRubberTreeTapPacket(final FriendlyByteBuf byteBuf) {
        this(byteBuf.readBlockPos(), byteBuf.readBoolean());
    }

    public void encapsulate(final FriendlyByteBuf byteBuf) {
        byteBuf.writeBlockPos(this.treeTapBlockPos);
    }

    public void handle(Supplier<NetworkEvent.Context> networkContext) {
        networkContext.get().enqueueWork(
                () -> DistExecutor.unsafeRunWhenOn(Dist.CLIENT, () -> () -> {
                    final BlockState blockState = Minecraft.getInstance().level.getBlockState(this.treeTapBlockPos);
                    if (blockState.is(BlockInit.RUBBER_TREE_TAP.get())) {
                        Minecraft.getInstance().level.setBlockAndUpdate(this.treeTapBlockPos, blockState.setValue(RubberTreeTap.IS_TAPPING, isTapping));
                    }
                })
        );
        networkContext.get().setPacketHandled(true);
    }
}
