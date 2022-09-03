package io.github.darealturtywurty.turtychemistry.core.network;

import io.github.darealturtywurty.turtychemistry.TurtyChemistry;
import io.github.darealturtywurty.turtychemistry.core.network.rubbertreenetworking.ClientBoundUpdateRubberTreeTapPacket;
import io.github.darealturtywurty.turtychemistry.core.network.rubbertreenetworking.ServerBoundUpdateRubberTreeTapPacket;
import io.github.darealturtywurty.turtylib.core.init.AbstractInit;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.network.NetworkDirection;
import net.minecraftforge.network.NetworkRegistry;
import net.minecraftforge.network.simple.SimpleChannel;

public final class PacketHandler extends AbstractInit {
    private static final String PROTOCOL_VERSION = "1";

    public static final SimpleChannel CHANNEL = NetworkRegistry.newSimpleChannel(
            new ResourceLocation(TurtyChemistry.MODID, "main"), () -> PROTOCOL_VERSION,
            PROTOCOL_VERSION::equals, PROTOCOL_VERSION::equals);

    public static void init() {
        int index = 0;
        TurtyChemistry.LOGGER.info("Registered {} packets!", index);
        CHANNEL.messageBuilder(ServerBoundUpdateRubberTreeTapPacket.class, index++, NetworkDirection.PLAY_TO_SERVER)
                .encoder(ServerBoundUpdateRubberTreeTapPacket::encapsulate).decoder(ServerBoundUpdateRubberTreeTapPacket::new)
                .consumer(ServerBoundUpdateRubberTreeTapPacket::process).add();
        CHANNEL.messageBuilder(ClientBoundUpdateRubberTreeTapPacket.class, index++, NetworkDirection.PLAY_TO_CLIENT)
                .encoder(ClientBoundUpdateRubberTreeTapPacket::encapsulate).decoder(ClientBoundUpdateRubberTreeTapPacket::new)
                .consumer(ClientBoundUpdateRubberTreeTapPacket::process).add();
    }
}
