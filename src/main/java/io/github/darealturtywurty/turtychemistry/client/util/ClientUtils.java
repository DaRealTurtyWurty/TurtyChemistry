package io.github.darealturtywurty.turtychemistry.client.util;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Font;

@SuppressWarnings("resource")
public final class ClientUtils {
    private ClientUtils() {
        throw new IllegalStateException("Attempted to construct utility class!");
    }

    public static Font getFont() {
        return getMinecraft().font;
    }

    public static Minecraft getMinecraft() {
        return Minecraft.getInstance();
    }
}
