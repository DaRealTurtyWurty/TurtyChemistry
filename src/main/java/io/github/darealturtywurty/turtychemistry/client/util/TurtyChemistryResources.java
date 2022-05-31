package io.github.darealturtywurty.turtychemistry.client.util;

import io.github.darealturtywurty.turtychemistry.TurtyChemistry;
import net.minecraft.resources.ResourceLocation;

public final class TurtyChemistryResources {
    public static final ResourceLocation START_BUTTON = shaleFracturer("start_button");
    public static final ResourceLocation BASE_DOOR = shaleFracturer("doors_closed");
    public static final ResourceLocation BASE = shaleFracturer("base");

    private TurtyChemistryResources() {
        throw new IllegalStateException("Attempted to construct constants class!");
    }

    public static ResourceLocation gui(String fileName) {
        return new ResourceLocation(TurtyChemistry.MODID, "textures/gui/" + fileName + ".png");
    }

    public static ResourceLocation shaleFracturer(String fileName) {
        return gui("shale_fracturer/" + fileName);
    }
}
