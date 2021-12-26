package io.github.darealturtywurty.turtychemistry.network.packet;

import java.util.concurrent.atomic.AtomicBoolean;
import java.util.function.Supplier;

import io.github.darealturtywurty.turtychemistry.common.block.entity.ShaleFracturerBlockEntity;
import net.minecraft.core.BlockPos;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraftforge.network.NetworkEvent;

public class ServerboundShaleFracturerPlayerInvPacket {
    private final BlockPos position;
    private final boolean shouldShowInventory;

    public ServerboundShaleFracturerPlayerInvPacket(BlockPos pos, boolean showInventory) {
        this.position = pos;
        this.shouldShowInventory = showInventory;
    }

    public ServerboundShaleFracturerPlayerInvPacket(FriendlyByteBuf buffer) {
        this(buffer.readBlockPos(), buffer.readBoolean());
    }

    public void encode(FriendlyByteBuf buffer) {
        buffer.writeBlockPos(this.position);
        buffer.writeBoolean(this.shouldShowInventory);
    }

    public boolean handle(Supplier<NetworkEvent.Context> context) {
        final var success = new AtomicBoolean(false);
        final NetworkEvent.Context ctx = context.get();
        ctx.enqueueWork(() -> {
            final BlockEntity blockEntity = ctx.getSender().level.getBlockEntity(this.position);
            if (blockEntity instanceof final ShaleFracturerBlockEntity shaleFracturer) {
                shaleFracturer.setShowPlayerInv(this.shouldShowInventory);
            }
        });
        ctx.setPacketHandled(true);
        return success.get();
    }
}
