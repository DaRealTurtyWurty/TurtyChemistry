package io.github.darealturtywurty.turtychemistry.common.events;

import io.github.darealturtywurty.turtychemistry.TurtyChemistry;
import io.github.darealturtywurty.turtychemistry.core.network.PacketHandler;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;

@Mod.EventBusSubscriber(modid = TurtyChemistry.MODID,bus = Mod.EventBusSubscriber.Bus.MOD)
public final class CommonModEvents {


    @SubscribeEvent
    public static void commonModSetup(final FMLCommonSetupEvent event)
    {
        event.enqueueWork(() -> {
            PacketHandler.init();
        });
    }
}
