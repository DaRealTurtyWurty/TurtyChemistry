package io.github.darealturtywurty.turtychemistry.core.network.molder;

import io.github.darealturtywurty.turtychemistry.common.block.entity.MolderBlockEntity;
import io.github.darealturtywurty.turtychemistry.core.network.PacketHandler;
import net.minecraft.core.BlockPos;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.protocol.game.ClientboundBlockEntityDataPacket;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraftforge.network.NetworkEvent;
import net.minecraftforge.network.PacketDistributor;

import java.util.function.Supplier;

public final class ServerBoundMolderClickPacket {
    final boolean complete;
    final BlockPos pos;
    final ItemStack stack;

    public ServerBoundMolderClickPacket(final boolean isComplete, final BlockPos molderBlockEntityPos, final ItemStack stack) {
        complete = isComplete;
        this.pos = molderBlockEntityPos;
        this.stack = stack;
    }

    ServerBoundMolderClickPacket(final FriendlyByteBuf byteBuf) {
        this.complete = byteBuf.readBoolean();
        this.pos = byteBuf.readBlockPos();
        this.stack = byteBuf.readItem();

    }

    public static void encode(final ServerBoundMolderClickPacket clickPacket, final FriendlyByteBuf byteBuf) {
        byteBuf.writeBoolean(clickPacket.complete);
        byteBuf.writeBlockPos(clickPacket.pos);
        byteBuf.writeItem(clickPacket.stack);
    }

    public static ServerBoundMolderClickPacket decode(final FriendlyByteBuf byteBuf) {
        return new ServerBoundMolderClickPacket(byteBuf.readBoolean(), byteBuf.readBlockPos(), byteBuf.readItem());
    }

    public static void handle(final ServerBoundMolderClickPacket clickPacket, final Supplier<NetworkEvent.Context> contextSupplier) {
        final ServerPlayer sender = contextSupplier.get().getSender();

        contextSupplier.get().enqueueWork(() -> {

            if (sender == null) {
                return;
            }
            final Level serverLevel = sender.level;
            if (!serverLevel.hasChunk(clickPacket.pos.getX(), clickPacket.pos.getZ())) {
                return;
            }
            if (sender.level.getBlockEntity(
                    clickPacket.pos) instanceof MolderBlockEntity molderBlockEntity && clickPacket.complete) {
                if (!molderBlockEntity.getItem().sameItem(clickPacket.stack)) {
                    molderBlockEntity.setItem(clickPacket.stack);
                    PacketHandler.CHANNEL.send(PacketDistributor.ALL.noArg(),
                            ClientboundBlockEntityDataPacket.create(molderBlockEntity));
                }
            }

        });
        contextSupplier.get().setPacketHandled(true);
    }

}
