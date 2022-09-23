package io.github.darealturtywurty.turtychemistry.client.events;

import io.github.darealturtywurty.turtychemistry.TurtyChemistry;
import io.github.darealturtywurty.turtychemistry.client.models.*;
import io.github.darealturtywurty.turtychemistry.client.renderer.AnvilBlockEntityRenderer;
import io.github.darealturtywurty.turtychemistry.client.renderer.ClayAlloyFurnaceBlockEntityRenderer;
import io.github.darealturtywurty.turtychemistry.client.renderer.MolderBlockEntityRenderer;
import io.github.darealturtywurty.turtychemistry.client.screens.ClayAlloyFurnaceScreen;
import io.github.darealturtywurty.turtychemistry.core.init.BlockEntityInit;
import io.github.darealturtywurty.turtychemistry.core.init.MenuInit;
import net.minecraft.client.gui.screens.MenuScreens;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.EntityRenderersEvent;
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
    public static void registerRenderers(EntityRenderersEvent.RegisterRenderers event) {
        event.registerBlockEntityRenderer(BlockEntityInit.CLAY_ALLOY_FURNACE.get(),
                ClayAlloyFurnaceBlockEntityRenderer::new);
        event.registerBlockEntityRenderer(BlockEntityInit.ANVIL.get(), AnvilBlockEntityRenderer::new);
        event.registerBlockEntityRenderer(BlockEntityInit.MOLDER.get(), MolderBlockEntityRenderer::new);
    }

    @SubscribeEvent
    public static void registerLayerDefinitions(EntityRenderersEvent.RegisterLayerDefinitions event) {
        event.registerLayerDefinition(ClayAlloyFurnaceModel.LAYER_LOCATION, ClayAlloyFurnaceModel::createLayer);
        event.registerLayerDefinition(ClayAlloyFurnaceWoodModel.LAYER_LOCATION, ClayAlloyFurnaceWoodModel::createLayer);
        event.registerLayerDefinition(ClayAlloyFurnaceTrayModel.LAYER_LOCATION, ClayAlloyFurnaceTrayModel::createLayer);
    }

    @SubscribeEvent
    public static void clientSetup(FMLClientSetupEvent event) {
        event.enqueueWork(() -> {
            MenuScreens.register(MenuInit.CLAY_ALLOY_FURNACE.get(), ClayAlloyFurnaceScreen::new);
            //MenuScreens.register(MenuInit.MOLDER.get(), MolderScreen::new);

        });
    }
}
