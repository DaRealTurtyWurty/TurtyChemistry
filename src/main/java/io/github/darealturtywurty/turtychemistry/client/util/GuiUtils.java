package io.github.darealturtywurty.turtychemistry.client.util;

import com.mojang.blaze3d.vertex.PoseStack;

import net.minecraft.network.chat.Component;

public final class GuiUtils {
    private GuiUtils() {
        throw new IllegalStateException("Attempted to construct utility class!");
    }

    public static void drawCenteredString(PoseStack stack, Component text, int xPos, int yPos, int colour) {
        final int textWidth = ClientUtils.getFont().width(text);
        ClientUtils.getFont().draw(stack, text, xPos - textWidth / 2, yPos, colour);
    }
}
