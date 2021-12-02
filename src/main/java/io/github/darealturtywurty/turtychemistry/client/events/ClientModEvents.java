package io.github.darealturtywurty.turtychemistry.client.events;

import io.github.darealturtywurty.turtychemistry.TurtyChemistry;
import io.github.darealturtywurty.turtychemistry.client.ui.ShaleFracturerScreen;
import io.github.darealturtywurty.turtychemistry.core.init.ContainerInit;
import net.minecraft.client.gui.screens.MenuScreens;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber.Bus;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;

@Mod.EventBusSubscriber(modid = TurtyChemistry.MODID, bus = Bus.MOD, value = Dist.CLIENT)
public final class ClientModEvents {
    private ClientModEvents() {
        throw new IllegalStateException("Attempted to construct event bus subscriber!");
    }

    @SubscribeEvent
    public static void setup(FMLClientSetupEvent event) {
        MenuScreens.register(ContainerInit.SHALE_FRACTURER.get(), ShaleFracturerScreen::new);
    }
}
