package io.github.darealturtywurty.turtychemistry.network.serverbound;

import io.github.darealturtywurty.turtychemistry.block.entity.MolderBlockEntity;
import net.minecraft.core.BlockPos;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.network.NetworkEvent;

import java.util.function.Supplier;

public record SMolderClickPacket(BlockPos molderBlockEntityPos, ItemStack stack) {
    SMolderClickPacket(final FriendlyByteBuf byteBuf) {
        this(byteBuf.readBlockPos(), byteBuf.readItem());
    }

    public static void encode(final SMolderClickPacket clickPacket, final FriendlyByteBuf byteBuf) {
        byteBuf.writeBlockPos(clickPacket.molderBlockEntityPos);
        byteBuf.writeItem(clickPacket.stack);
    }

    public static SMolderClickPacket decode(final FriendlyByteBuf byteBuf) {
        return new SMolderClickPacket(byteBuf.readBlockPos(), byteBuf.readItem());
    }

    public static void handle(final SMolderClickPacket clickPacket, final Supplier<NetworkEvent.Context> contextSupplier) {
        final ServerPlayer sender = contextSupplier.get().getSender();
        contextSupplier.get().enqueueWork(() -> {
            if (sender == null) {
                return;
            }

            if (!sender.level.hasChunkAt(clickPacket.molderBlockEntityPos)) {
                return;
            }

            if (sender.level.getBlockEntity(
                    clickPacket.molderBlockEntityPos) instanceof MolderBlockEntity molderBlockEntity) {
                if (!molderBlockEntity.getItem().sameItem(clickPacket.stack)) {
                    molderBlockEntity.setItem(clickPacket.stack);
                }
            }
        });
        contextSupplier.get().setPacketHandled(true);
    }
}
