package io.github.darealturtywurty.turtychemistry.client.events;

import java.util.HashMap;
import java.util.Map;

import io.github.darealturtywurty.turtychemistry.TurtyChemistry;
import io.github.darealturtywurty.turtychemistry.client.ui.core.Animation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber.Bus;

@Mod.EventBusSubscriber(modid = TurtyChemistry.MODID, bus = Bus.FORGE, value = Dist.CLIENT)
public final class ClientForgeEvents {
    private static final Map<Animation, Boolean> ANIMATIONS = new HashMap<>();
    private static final Map<Animation, Integer> ANIMATION_TIME = new HashMap<>();

    private ClientForgeEvents() {
        throw new IllegalStateException("Attempted to construct event bus subscriber!");
    }

    @SubscribeEvent
    public static void render(RenderGameOverlayEvent.Post event) {
        /*
         * if (event.getType() != ElementType.ALL) return;
         *
         * final long time = System.currentTimeMillis();
         * ANIMATION_TIME.forEach((animation, frame) -> { final int fps = animation.fps;
         * });
         */
    }
}
