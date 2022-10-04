package io.github.darealturtywurty.turtychemistry.core.network.molder;

import io.github.darealturtywurty.turtychemistry.TurtyChemistry;
import io.github.darealturtywurty.turtychemistry.common.block.entity.MolderBlockEntity;
import net.minecraft.core.BlockPos;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraftforge.network.NetworkEvent;

import java.util.function.Supplier;

public record ServerBoundMolderClickPacket(BlockPos molderBlockEntityPos, ItemStack stack) {

    ServerBoundMolderClickPacket(final FriendlyByteBuf byteBuf) {
        this(byteBuf.readBlockPos(), byteBuf.readItem());
    }

    public static void encode(final ServerBoundMolderClickPacket clickPacket, final FriendlyByteBuf byteBuf) {
        byteBuf.writeBlockPos(clickPacket.molderBlockEntityPos);
        byteBuf.writeItem(clickPacket.stack);
    }

    public static ServerBoundMolderClickPacket decode(final FriendlyByteBuf byteBuf) {
        return new ServerBoundMolderClickPacket(byteBuf.readBlockPos(), byteBuf.readItem());
    }

    public static void handle(final ServerBoundMolderClickPacket clickPacket, final Supplier<NetworkEvent.Context> contextSupplier) {
        final ServerPlayer sender = contextSupplier.get().getSender();
        contextSupplier.get().enqueueWork(() -> {
            if (sender == null) {
                TurtyChemistry.LOGGER.debug("no sender");
                return;
            }
            final Level serverLevel = sender.level;
            if (!serverLevel.hasChunkAt(clickPacket.molderBlockEntityPos)) {
                TurtyChemistry.LOGGER.debug("no chunk present for block entity operations");
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
