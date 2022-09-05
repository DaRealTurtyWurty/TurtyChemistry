package io.github.darealturtywurty.turtychemistry.client.events;

import io.github.darealturtywurty.turtychemistry.TurtyChemistry;
import io.github.darealturtywurty.turtychemistry.client.models.ClayAlloyFurnaceModel;
import io.github.darealturtywurty.turtychemistry.client.models.ClayAlloyFurnaceTrayModel;
import io.github.darealturtywurty.turtychemistry.client.models.ClayAlloyFurnaceWoodModel;
import io.github.darealturtywurty.turtychemistry.client.renderer.ClayAlloyFurnaceBlockEntityRenderer;
import io.github.darealturtywurty.turtychemistry.core.init.BlockEntityInit;
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
    }

    @SubscribeEvent
    public static void registerLayerDefinitions(EntityRenderersEvent.RegisterLayerDefinitions event) {
        event.registerLayerDefinition(ClayAlloyFurnaceModel.LAYER_LOCATION, ClayAlloyFurnaceModel::createLayer);
        event.registerLayerDefinition(ClayAlloyFurnaceWoodModel.LAYER_LOCATION, ClayAlloyFurnaceWoodModel::createLayer);
        event.registerLayerDefinition(ClayAlloyFurnaceTrayModel.LAYER_LOCATION, ClayAlloyFurnaceTrayModel::createLayer);
    }
}
