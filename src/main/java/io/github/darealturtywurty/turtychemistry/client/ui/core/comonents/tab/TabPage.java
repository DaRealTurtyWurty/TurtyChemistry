package io.github.darealturtywurty.turtychemistry.client.ui.core.comonents.tab;

import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.PoseStack;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiComponent;
import net.minecraft.resources.ResourceLocation;

public abstract class TabPage {
    public final ResourceLocation baseTexture;
    private final Minecraft minecraft = Minecraft.getInstance();
    private final int leftPos, topPos, width, height, textureWidth, textureHeight;

    public TabPage(ResourceLocation texture, int leftPos, int topPos, int width, int height, int textureWidth,
            int textureHeight) {
        this.baseTexture = texture;
        this.leftPos = leftPos;
        this.topPos = topPos;
        this.width = width;
        this.height = height;
        this.textureWidth = textureWidth;
        this.textureHeight = textureHeight;
    }

    public abstract void initWidgets();

    public void renderBackground(PoseStack stack, int mouseX, int mouseY) {
        RenderSystem.setShaderTexture(0, this.baseTexture);
        RenderSystem.setShaderColor(1.0f, 1.0f, 1.0f, 1.0f);
        GuiComponent.blit(stack, this.leftPos, this.topPos, 0, 0, this.width, this.height, this.textureWidth,
                this.textureHeight);
    }

    public abstract void renderCenterground(PoseStack stack, int mouseX, int mouseY);

    public abstract void renderForeground(PoseStack stack, int mouseX, int mouseY);

    public abstract void uninitWidgets();
}
