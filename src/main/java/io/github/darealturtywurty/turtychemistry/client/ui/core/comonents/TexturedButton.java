package io.github.darealturtywurty.turtychemistry.client.ui.core.comonents;

import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.PoseStack;

import it.unimi.dsi.fastutil.objects.Object2FloatFunction;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.components.Button;
import net.minecraft.client.renderer.GameRenderer;
import net.minecraft.network.chat.TextComponent;
import net.minecraft.resources.ResourceLocation;

public class TexturedButton extends Button {
    protected final Minecraft minecraft;
    protected final ResourceLocation texture;
    protected final Object2FloatFunction<TexturedButton> alpha;
    protected final int texX, texY, texWidth, texHeight;

    public TexturedButton(ResourceLocation texture, int xPos, int yPos, int width, int height, int texX,
            int texY, int texWidth, int texHeight, float alpha, OnPress pressable, OnTooltip tooltip) {
        this(texture, xPos, yPos, width, height, texX, texY, texWidth, texHeight, btn -> 1.0f, pressable,
                tooltip);
    }

    public TexturedButton(ResourceLocation texture, int xPos, int yPos, int width, int height, int texX,
            int texY, int texWidth, int texHeight, Object2FloatFunction<TexturedButton> alpha,
            OnPress pressable, OnTooltip tooltip) {
        super(xPos, yPos, width, height, new TextComponent(""), pressable, tooltip);
        this.minecraft = Minecraft.getInstance();
        this.texture = texture;
        this.alpha = alpha;
        this.texX = texX;
        this.texY = texY;
        this.texWidth = texWidth;
        this.texHeight = texHeight;
    }

    @Override
    public void render(PoseStack stack, int mouseX, int mouseY, float partialTicks) {
        if (this.visible) {
            this.isHovered = mouseX >= this.x && mouseY >= this.y && mouseX < this.x + this.width
                    && mouseY < this.y + this.height;
            RenderSystem.setShader(GameRenderer::getPositionTexShader);
            RenderSystem.setShaderTexture(0, this.texture);
            final float alpha = this.alpha.getFloat(this);
            RenderSystem.setShaderColor(1.0f, 1.0f, 1.0f,
                    alpha >= 0 && this.alpha.getFloat(this) <= 1 ? alpha : 1.0f);
            final int yTexOffset = this.isHovered ? this.height : 0;
            RenderSystem.enableBlend();
            RenderSystem.defaultBlendFunc();
            RenderSystem.enableDepthTest();
            blit(stack, this.x, this.y, this.texX, this.texY + yTexOffset, this.width, this.height,
                    this.texWidth, this.texHeight);
        }
    }

    @FunctionalInterface
    public interface OnPress<Type extends TexturedButton> extends Button.OnPress {
        @SuppressWarnings("unchecked")
        @Override
        default void onPress(Button btn) {
            this.onPress((Type) btn);
        }

        void onPress(Type button);
    }
}
