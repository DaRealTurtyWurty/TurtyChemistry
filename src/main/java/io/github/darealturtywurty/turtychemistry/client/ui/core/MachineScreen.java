package io.github.darealturtywurty.turtychemistry.client.ui.core;

import com.mojang.blaze3d.vertex.PoseStack;

import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;

public abstract class MachineScreen extends Screen {

    public MachineScreen(Component title) {
        super(title);
    }

    @Override
    public void render(PoseStack stack, int mouseX, int mouseY, float partialTicks) {
        renderGuiBackground(stack);
        super.render(stack, mouseX, mouseY, partialTicks);
        renderGuiForeground(stack, mouseX, mouseY);
    }

    public abstract void renderGuiBackground(PoseStack stack);

    public abstract void renderGuiForeground(PoseStack stack, int mouseX, int mouseY);
}
