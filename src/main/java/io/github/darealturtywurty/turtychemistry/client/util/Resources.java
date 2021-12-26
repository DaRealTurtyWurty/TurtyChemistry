package io.github.darealturtywurty.turtychemistry.client.util;

import io.github.darealturtywurty.turtychemistry.TurtyChemistry;
import io.github.darealturtywurty.turtychemistry.client.ui.core.Animation;
import net.minecraft.resources.ResourceLocation;

public final class Resources {
    public static final ResourceLocation TAB_LOC = new ResourceLocation(TurtyChemistry.MODID,
            "textures/gui/components/tabs.png");

    private Resources() {
        throw new IllegalStateException("Attempted to construct constants class!");
    }

    public static ResourceLocation gui(String fileName) {
        return new ResourceLocation(TurtyChemistry.MODID, "textures/gui/" + fileName + ".png");
    }

    public static class Icons {
        public static final ResourceLocation HOME = icon("home");
        public static final ResourceLocation FLAME = icon("flame");
        public static final ResourceLocation WATER_DROPLET = icon("water_droplet");
        public static final ResourceLocation GAS = icon("gas");
        public static final ResourceLocation BACKPACK = icon("backpack");
        public static final ResourceLocation RADAR = icon("radar");
        public static final ResourceLocation MINECART = icon("minecart");
        public static final ResourceLocation LIGHTNING_BOLT = icon("lightning_bolt");

        public static ResourceLocation icon(String name) {
            return gui("icons/" + name);
        }
    }

    public static final class ShaleFracturer {
        public static final ResourceLocation START_BUTTON = gui("shale_fracturer/start_button");
        public static final ResourceLocation BASE_DOOR = gui("shale_fracturer/doors_closed");
        public static final Animation DOOR_ANIM = new Animation("shale_fracturer_door_open",
                new ResourceLocation(TurtyChemistry.MODID, "shale_fracturer/door_anim/door"), 50, 10);
        public static final ResourceLocation BASE = gui("shale_fracturer/base");

        private ShaleFracturer() {
            throw new IllegalStateException("Attempted to construct constants class!");
        }
    }
}
