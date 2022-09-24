package io.github.darealturtywurty.turtychemistry.core.network;

import io.github.darealturtywurty.turtychemistry.TurtyChemistry;
import io.github.darealturtywurty.turtychemistry.core.network.molder.ServerBoundMolderClickPacket;
import io.github.darealturtywurty.turtylib.core.init.AbstractInit;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.network.NetworkRegistry;
import net.minecraftforge.network.simple.SimpleChannel;

public final class PacketHandler extends AbstractInit {
    private static final String PROTOCOL_VERSION = "1";

    public static final SimpleChannel CHANNEL = NetworkRegistry.newSimpleChannel(
            new ResourceLocation(TurtyChemistry.MODID, "main"), () -> PROTOCOL_VERSION,
            PROTOCOL_VERSION::equals, PROTOCOL_VERSION::equals);

    public static void init() {
        int index = 0;
        CHANNEL.registerMessage(index++, ServerBoundMolderClickPacket.class, ServerBoundMolderClickPacket::encode,
                ServerBoundMolderClickPacket::decode,ServerBoundMolderClickPacket::handle);
        TurtyChemistry.LOGGER.info("Registered {} packets!", index);

    }
}
