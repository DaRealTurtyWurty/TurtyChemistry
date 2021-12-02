package io.github.darealturtywurty.turtychemistry.client.util;

import io.github.darealturtywurty.turtychemistry.TurtyChemistry;
import io.github.darealturtywurty.turtychemistry.client.ui.core.Animation;
import net.minecraft.resources.ResourceLocation;

public final class Resources {
    private Resources() {
        throw new IllegalStateException("Attempted to construct constants class!");
    }

    public static ResourceLocation gui(String fileName) {
        return new ResourceLocation(TurtyChemistry.MODID, "textures/gui/" + fileName + ".png");
    }

    public static final class ShaleFracturer {
        public static final ResourceLocation START_BUTTON = gui("shale_fracturer/start_button");
        public static final ResourceLocation BASE_DOOR = gui("shale_fracturer/doors_closed");
        public static final Animation DOOR_ANIM = new Animation("shale_fracturer_door_open",
                gui("shale_fracturer/door_anim/"), 50, 10);

        private ShaleFracturer() {
            throw new IllegalStateException("Attempted to construct constants class!");
        }
    }
}
