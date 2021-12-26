package io.github.darealturtywurty.turtychemistry.client.ui.core.comonents.tab;

import java.util.function.Function;

import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Vector3f;

import io.github.darealturtywurty.turtychemistry.client.ui.core.comonents.TexturedButton;
import io.github.darealturtywurty.turtychemistry.client.util.Resources;
import net.minecraft.client.gui.components.Button;
import net.minecraft.client.renderer.GameRenderer;
import net.minecraft.resources.ResourceLocation;

public class Tab extends TexturedButton {
    protected final TabHolder screen;
    public final TabPage page;
    protected final Orientation orientation;
    protected final ResourceLocation icon;
    public boolean isSelected;
    protected final Function<Tab, Integer> changeYOffset;

    public Tab(TabHolder screen, TabPage page, Orientation orientation, ResourceLocation icon, final int xPos,
            final int yPos, final int width, final int height, final int texX,
            Function<Tab, Integer> changeYOffset) {
        super(Resources.TAB_LOC, xPos, yPos, width, height, texX, 0, 256, 256, 1.0f, btn -> {
            ((Tab) btn).handle();
        }, Button.NO_TOOLTIP);

        this.screen = screen;
        this.page = page;
        this.orientation = orientation;
        this.icon = icon;
        this.changeYOffset = changeYOffset;
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
            RenderSystem.enableBlend();
            RenderSystem.defaultBlendFunc();
            RenderSystem.enableDepthTest();
            blit(stack, this.x, this.y, this.texX, this.changeYOffset.apply(this), this.width, this.height,
                    this.texWidth, this.texHeight);
            RenderSystem.setShaderTexture(0, this.icon);
            blit(stack, this.x + this.width / 2 - 8, this.y + this.height / 2 - 8, 0, 0, 16, 16, 16, 16);
        }
    }

    @SuppressWarnings("unchecked")
    private void handle() {
        if (this.screen.getSelectedTab() != this) {
            this.screen.setSelectedTab(this);
        }
    }

    private void rotateAround(PoseStack stack, Vector3f pivot, Vector3f axis, float angle) {
        stack.translate(pivot.x(), pivot.y(), pivot.z());
        stack.mulPose(axis.rotationDegrees(angle));
        stack.translate(-pivot.x(), -pivot.y(), -pivot.z());
    }

    public enum Orientation {
        TOP_BOTTOM(0f), RIGHT_LEFT(90f), BOTTOM_TOP(180f), LEFT_RIGHT(270f);

        public final float rotDegrees;

        Orientation(float degrees) {
            this.rotDegrees = degrees;
        }
    }
}
