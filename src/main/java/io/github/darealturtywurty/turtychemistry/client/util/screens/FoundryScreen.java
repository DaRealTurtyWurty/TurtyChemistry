package io.github.darealturtywurty.turtychemistry.client.util.screens;

import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.PoseStack;
import io.github.darealturtywurty.turtychemistry.TurtyChemistry;
import io.github.darealturtywurty.turtychemistry.block.entity.FoundryBlockEntity;
import io.github.darealturtywurty.turtychemistry.menu.FoundryMenu;
import io.github.darealturtywurty.turtylib.client.ui.components.FluidWidget;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.client.renderer.GameRenderer;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.level.material.Fluids;
import net.minecraftforge.fluids.FluidStack;
import org.jetbrains.annotations.NotNull;

public final class FoundryScreen extends AbstractContainerScreen<FoundryMenu> {
    public static final ResourceLocation TEXTURE = new ResourceLocation(TurtyChemistry.MODID,
            "textures/gui/clay_alloy_furnace.png");
    private FluidWidget fluidWidget;

    public FoundryScreen(final FoundryMenu screenContainer, final Inventory inv, final Component title) {
        super(screenContainer, inv, title);
        this.leftPos = 0;
        this.topPos = 0;
        this.imageWidth = 176;
        this.imageHeight = 166;
    }

    @Override
    protected void init() {
        super.init();
        this.fluidWidget = addRenderableOnly(
                new FluidWidget(new FluidStack(Fluids.LAVA, this.menu.getData().get(2)), 1920 >> 4, 1080 >> 4, 9, 9, 9,
                        9));
    }

    @Override
    protected void containerTick() {
        super.containerTick();
    }

    @Override
    public void render(@NotNull PoseStack poseStack, int mouseX, int mouseY, float partialTicks) {
        this.renderBackground(poseStack);
        super.render(poseStack, mouseX, mouseY, partialTicks);
        this.renderTooltip(poseStack, mouseX, mouseY);
        if (this.fluidWidget != null) {
            this.fluidWidget.render(poseStack, mouseX, mouseY, partialTicks);
            this.fluidWidget.blit(poseStack, 1920 >> 4, 1080 >> 4, 0, 0, 10, 10);
            this.fluidWidget.drawTooltip(poseStack, mouseX, mouseY, partialTicks);
            this.fluidWidget.renderToolTip(poseStack, mouseX, mouseY);
        }
    }

    @Override
    protected void renderLabels(@NotNull PoseStack poseStack, int mouseX, int mouseY) {
        this.font.draw(poseStack, this.title, 8.0F, 6.0F, 4210752);
        this.font.draw(poseStack, this.playerInventoryTitle, 8.0F, (float) (this.imageHeight - 96 + 2), 4210752);
    }

    @Override
    protected void renderBg(@NotNull PoseStack poseStack, float partialTicks, int mouseX, int mouseY) {
        RenderSystem.setShader(GameRenderer::getPositionTexColorShader);
        RenderSystem.setShaderColor(1.0f, 1.0f, 1.0f, 1.0f);
        RenderSystem.setShaderTexture(0, TEXTURE);
        this.blit(poseStack, this.leftPos, this.topPos, 0, 0, this.imageWidth, this.imageHeight);

        int fuel = -this.menu.getData().get(1) + FoundryBlockEntity.MAX_BURN_TIME;
        if (fuel < FoundryBlockEntity.MAX_BURN_TIME) {
            int scaledFuel = FoundryBlockEntity.MAX_BURN_TIME;
            scaledFuel = fuel * 13 / scaledFuel;

            this.blit(poseStack, this.leftPos + 56, this.topPos + 36 + 12 - scaledFuel, 176, 12 - scaledFuel, 14,
                    scaledFuel + 1);
        }

        int progress = this.menu.getData().get(0);
        int progressScaled = progress != 0 ? progress * 24 / this.menu.getData().get(2) : 0;
        this.blit(poseStack, this.leftPos + 79, this.topPos + 34, 176, 14, progressScaled + 1, 16);
    }
}
