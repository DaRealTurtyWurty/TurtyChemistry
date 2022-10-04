package io.github.darealturtywurty.turtychemistry.client.screens;

import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.PoseStack;
import io.github.darealturtywurty.turtychemistry.TurtyChemistry;
import io.github.darealturtywurty.turtychemistry.common.block.entity.MolderBlockEntity;
import io.github.darealturtywurty.turtychemistry.core.util.DrawingSpace;
import io.github.darealturtywurty.turtychemistry.core.util.MoldingPatternVertexHolders;
import net.minecraft.client.gui.components.AbstractButton;
import net.minecraft.client.gui.narration.NarrationElementOutput;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.client.renderer.GameRenderer;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import net.minecraft.world.item.ItemStack;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public final class MolderScreen extends Screen {
    public static final ResourceLocation TEXTURE = new ResourceLocation(TurtyChemistry.MODID,
            "textures/gui/molder/molder_gui.png");

    private static final int BUTTON_SIZE = 18;
    private static final MoldingPatternVertexHolders[] HOLDERS = MoldingPatternVertexHolders.values();
    private static final List<PatternButton> PATTERN_BUTTON_LIST = new ArrayList<>();
    private final BlockPos blockPos;
    private final MolderBlockEntity molderBlockEntity;
    private DrawingSpace drawingPanelWidget;
    private ItemStack currentStack = ItemStack.EMPTY;

    public MolderScreen(final Component pTitle, final BlockPos pos, final MolderBlockEntity molderBlockEntity) {
        super(pTitle);
        this.blockPos = pos;
        this.molderBlockEntity = molderBlockEntity;
        initPatternButtons(this);

    }

    private static void initPatternButtons(final MolderScreen molderScreen) {
        final int moldingPatternSecondDimensionLength = HOLDERS.length;
        for (int z = 0; z < moldingPatternSecondDimensionLength; z++) {
            PATTERN_BUTTON_LIST.add(
                    new PatternButton(85, (z * BUTTON_SIZE) + 30, BUTTON_SIZE, BUTTON_SIZE, HOLDERS[z], molderScreen));
        }

    }

    @Override
    protected void init() {
        super.init();
        PATTERN_BUTTON_LIST.forEach(this::addRenderableWidget);
        drawingPanelWidget = addRenderableWidget(
                new DrawingSpace((this.width >> 1) - 80, (this.height >> 3) - 2, width >> 1, (int) (height * 0.714f),
                        Component.empty(), PATTERN_BUTTON_LIST, blockPos, this));

    }

    @Override
    public boolean isPauseScreen() {
        return false;
    }

    @Override
    public void onClose() {
        clearWidgets();
        super.onClose();
    }

    @Override
    public boolean mouseClicked(final double pMouseX, final double pMouseY, final int pButton) {
        drawingPanelWidget.onClick(pMouseX, pMouseY);
        return super.mouseClicked(pMouseX, pMouseY, pButton);
    }

    @Override
    public void render(final @NotNull PoseStack pPoseStack, final int pMouseX, final int pMouseY, final float pPartialTick) {
        this.renderBackground(pPoseStack);
        RenderSystem.setShader(GameRenderer::getPositionTexColorShader);
        RenderSystem.setShaderColor(1.0f, 1.0f, 1.0f, 1.0f);
        RenderSystem.setShaderTexture(0, TEXTURE);
        this.blit(pPoseStack, (this.width >> 3) + 8, this.height >> 4, 0, 0, (int) (this.width / 1.4f),
                (int) (this.height / 1.2f));
        super.render(pPoseStack, pMouseX, pMouseY, pPartialTick);
        this.font.draw(pPoseStack, this.title, width >> 1, (height >> 3) - 10, Mth.color(128, 128, 128));
        this.font.draw(pPoseStack, Component.translatable("molder.indicated_item").append(":")
                        .append(currentStack.getItem().getDescription()), (width >> 2) - 30, (height >> 3) - 10,
                Mth.color(1, 1, 1));

    }

    public ItemStack getCurrentStack() {
        return currentStack;
    }

    public MolderBlockEntity getMolderBlockEntity() {
        return molderBlockEntity;
    }

    @Override
    public void mouseMoved(final double pMouseX, final double pMouseY) {
        super.mouseMoved(pMouseX, pMouseY);
        this.drawingPanelWidget.mouseMoved(pMouseX, pMouseY);
    }

    public static final class PatternButton extends AbstractButton {

        public final MoldingPatternVertexHolders moldingPatternHolder;
        private final MolderScreen molderScreen;

        public PatternButton(final int pX, final int pY, final int pWidth, final int pHeight, final MoldingPatternVertexHolders moldingPatternVertexHolders, final MolderScreen molderScreenReference) {
            super(pX, pY, pWidth, pHeight, moldingPatternVertexHolders.getMessage());
            this.moldingPatternHolder = moldingPatternVertexHolders;
            molderScreen = molderScreenReference;

        }

        @Override
        public void onPress() {
            molderScreen.currentStack = moldingPatternHolder.getPatternStack();
        }

        @Override
        public void updateNarration(final @NotNull NarrationElementOutput pNarrationElementOutput) {

        }
    }

}
