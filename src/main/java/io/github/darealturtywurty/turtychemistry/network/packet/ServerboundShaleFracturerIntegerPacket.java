package io.github.darealturtywurty.turtychemistry.network.packet;

import java.util.concurrent.atomic.AtomicBoolean;
import java.util.function.Supplier;

import io.github.darealturtywurty.turtychemistry.common.block.entity.ShaleFracturerBlockEntity;
import net.minecraft.core.BlockPos;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraftforge.network.NetworkEvent;

public class ServerboundShaleFracturerIntegerPacket {
    private final BlockPos position;
    private final Type type;
    private final int value;

    public ServerboundShaleFracturerIntegerPacket(BlockPos pos, Type type, int value) {
        this.position = pos;
        this.type = type;
        this.value = value;
    }

    public ServerboundShaleFracturerIntegerPacket(FriendlyByteBuf buffer) {
        this(buffer.readBlockPos(), Type.valueOf(buffer.readUtf()), buffer.readInt());
    }

    public void encode(FriendlyByteBuf buffer) {
        buffer.writeBlockPos(this.position);
        buffer.writeUtf(this.type.name());
        buffer.writeInt(this.value);
    }

    public boolean handle(Supplier<NetworkEvent.Context> context) {
        final var success = new AtomicBoolean(false);
        final NetworkEvent.Context ctx = context.get();
        ctx.enqueueWork(() -> {
            final BlockEntity blockEntity = ctx.getSender().level.getBlockEntity(this.position);
            if (blockEntity instanceof final ShaleFracturerBlockEntity shaleFracturer) {
                switch (this.type) {
                    case SPEED -> shaleFracturer.speed = this.value;
                    case ENERGY_INPUT_LIMIT -> shaleFracturer.energyInputLimit = this.value;
                    case WATER_INPUT_LIMIT -> shaleFracturer.waterInputLimit = this.value;
                    default -> success.set(true);
                }

                success.set(!success.get());
            }
        });

        ctx.setPacketHandled(true);
        return success.get();
    }

    public enum Type {
        SPEED, ENERGY_INPUT_LIMIT, WATER_INPUT_LIMIT;
    }
}
