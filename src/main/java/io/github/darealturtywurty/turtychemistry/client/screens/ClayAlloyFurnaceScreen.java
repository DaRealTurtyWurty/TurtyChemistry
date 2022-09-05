package io.github.darealturtywurty.turtychemistry.client.screens;

import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.PoseStack;
import io.github.darealturtywurty.turtychemistry.TurtyChemistry;
import io.github.darealturtywurty.turtychemistry.common.block.entity.ClayAlloyFurnaceBlockEntity;
import io.github.darealturtywurty.turtychemistry.common.menus.ClayAlloyFurnaceMenu;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.client.renderer.GameRenderer;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Inventory;

public class ClayAlloyFurnaceScreen extends AbstractContainerScreen<ClayAlloyFurnaceMenu> {
    private static final ResourceLocation TEXTURE = new ResourceLocation(TurtyChemistry.MODID,
            "textures/gui/clay_alloy_furnace.png");

    public ClayAlloyFurnaceScreen(ClayAlloyFurnaceMenu screenContainer, Inventory inv, Component title) {
        super(screenContainer, inv, title);
        this.leftPos = 0;
        this.topPos = 0;
        this.imageWidth = 176;
        this.imageHeight = 166;
    }

    @Override
    public void render(PoseStack poseStack, int mouseX, int mouseY, float partialTicks) {
        this.renderBackground(poseStack);
        super.render(poseStack, mouseX, mouseY, partialTicks);
        this.renderTooltip(poseStack, mouseX, mouseY);
    }

    @Override
    protected void renderLabels(PoseStack poseStack, int mouseX, int mouseY) {
        this.font.draw(poseStack, this.title, 8.0F, 6.0F, 4210752);
        this.font.draw(poseStack, this.playerInventoryTitle, 8.0F, (float) (this.imageHeight - 96 + 2),
                4210752);
    }

    @Override
    protected void renderBg(PoseStack poseStack, float partialTicks, int mouseX, int mouseY) {
        RenderSystem.setShader(GameRenderer::getPositionTexColorShader);
        RenderSystem.setShaderColor(1.0f, 1.0f, 1.0f, 1.0f);
        RenderSystem.setShaderTexture(0, TEXTURE);
        this.blit(poseStack, this.leftPos, this.topPos, 0, 0, this.imageWidth, this.imageHeight);

        // add progress bar
        int progress = this.menu.getData().get(0);
        int progressScaled = progress != 0 ? progress * 24 / this.menu.getData().get(2) : 0;
        this.blit(poseStack, this.leftPos + 79, this.topPos + 34, 176, 14, progressScaled + 1, 16);

        // add fuel bar
        int fuel = this.menu.getData().get(1);
        if (fuel > 0) {
            int k = ClayAlloyFurnaceBlockEntity.MAX_BURN_TIME;
            k = fuel * 13 / k;

            this.blit(poseStack, this.leftPos + 56, this.topPos + 36 + 12 - k, 176, 12 - k, 14, k + 1);
        }
    }
}
