package io.github.darealturtywurty.turtychemistry.client.screens;

import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.PoseStack;
import io.github.darealturtywurty.turtychemistry.TurtyChemistry;
import io.github.darealturtywurty.turtychemistry.common.menus.MolderMenu;
import io.github.darealturtywurty.turtylib.client.ui.components.LineGraphWidget;
import net.minecraft.client.gui.components.AbstractButton;
import net.minecraft.client.gui.components.AbstractWidget;
import net.minecraft.client.gui.narration.NarrationElementOutput;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.client.renderer.GameRenderer;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Inventory;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public final class MolderScreen extends AbstractContainerScreen<MolderMenu> {
    public static final ResourceLocation TEXTURE = new ResourceLocation(TurtyChemistry.MODID,
            "textures/gui/molder_gui.png");
    private static final int BUTTON_SIZE = 18;

    public MolderScreen(final MolderMenu pMenu, final Inventory pPlayerInventory, final Component pTitle) {
        super(pMenu, pPlayerInventory, pTitle);

    }
    private final List<AbstractButton> patternButtonList = new ArrayList<>();
    private AbstractButton drawingPanelWidget;
    @Override
    protected void init() {
        super.init();
        drawingPanelWidget = addWidget(new AbstractButton(this.leftPos,this.topPos >> 1,
                imageWidth, (int) (imageHeight * 1.2f),
                Component.literal("test")) {
            @Override
            public void onPress() {
                TurtyChemistry.LOGGER.info("panel pressed");
            }

            @Override
            public void updateNarration(final NarrationElementOutput pNarrationElementOutput) {

            }
        });
        for(int i = 0; i < 4; i++)
        {
            for(int z = 0; z < 10; z++) {
                patternButtonList.add(addRenderableWidget(new PatternButton(85 + (i * BUTTON_SIZE),
                        (z * BUTTON_SIZE) + 30, BUTTON_SIZE,
                        BUTTON_SIZE,
                        Component.literal("test"))));
            }
        }
    }

    @Override
    public void render(final @NotNull PoseStack pPoseStack, final int pMouseX, final int pMouseY, final float pPartialTick) {
        this.renderBackground(pPoseStack);
        RenderSystem.setShader(GameRenderer::getPositionTexColorShader);
        RenderSystem.setShaderColor(1.0f, 1.0f, 1.0f, 1.0f);
        RenderSystem.setShaderTexture(0, TEXTURE);
        drawingPanelWidget.blit(pPoseStack, this.leftPos >> 1, this.topPos >> 1, 0, 0,
                (int) (this.imageWidth * 1.5f),
                (int) (this.imageHeight * 1.2f));

        //drawingPanelWidget.render(pPoseStack,pMouseX,pMouseY,pPartialTick);
        super.render(pPoseStack, pMouseX, pMouseY, pPartialTick);
    }

    @Override
    protected void renderBg(final @NotNull PoseStack pPoseStack, final float pPartialTick, final int pMouseX, final int pMouseY) {
        RenderSystem.setShader(GameRenderer::getPositionTexColorShader);
        RenderSystem.setShaderColor(1.0f, 1.0f, 1.0f, 1.0f);
        RenderSystem.setShaderTexture(0, TEXTURE);
        //this.blit(pPoseStack, this.leftPos, this.topPos, 0, 0, this.imageWidth, this.imageHeight);
    }

    @Override
    protected void renderLabels(final @NotNull PoseStack pPoseStack, final int pMouseX, final int pMouseY) {
        this.font.draw(pPoseStack, this.title, (float)this.titleLabelX, (float)this.titleLabelY, 4210752);
    }

    private static final class PatternButton extends AbstractButton {

        public PatternButton(final int pX, final int pY, final int pWidth, final int pHeight,final Component message) {
            super(pX, pY, pWidth, pHeight, message);

        }

        @Override
        public void onPress() {

        }

        @Override
        public void updateNarration(final NarrationElementOutput pNarrationElementOutput) {

        }
    }

}
