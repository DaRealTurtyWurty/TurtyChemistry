package io.github.darealturtywurty.turtychemistry.client.ui.core;

import javax.annotation.Nullable;

import io.github.darealturtywurty.turtychemistry.TurtyChemistry;
import net.minecraft.resources.ResourceLocation;

public class Animation {
    public final String name;
    public final ResourceLocation path;
    public final ResourceLocation[] frames;
    public final int fps;

    public Animation(String name, ResourceLocation path, int frames, int fps) {
        this.name = name;
        this.path = path;
        this.frames = findFrames(path, frames > 0 ? frames : 0);
        this.fps = fps;
    }

    private static ResourceLocation[] findFrames(ResourceLocation path, int count) {
        final var frames = new ResourceLocation[count];
        for (int frame = 0; frame < count; frame++) {
            frames[frame] = new ResourceLocation(path.getNamespace(), path.getPath() + frame + ".png");
        }

        return frames;
    }

    @Nullable
    public ResourceLocation getFrame(int index) {
        try {
            return this.frames[index];
        } catch (final IndexOutOfBoundsException exception) {
            TurtyChemistry.LOGGER.error("Attempted to get non-existant frame at index: '" + index
                    + "' from animation '" + this.name + "!");
            return null;
        }
    }

    public void play() {

    }
}
