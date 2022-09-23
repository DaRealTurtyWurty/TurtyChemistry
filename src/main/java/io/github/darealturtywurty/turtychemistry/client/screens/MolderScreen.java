package io.github.darealturtywurty.turtychemistry.client.screens;

import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.PoseStack;
import io.github.darealturtywurty.turtychemistry.TurtyChemistry;
import io.github.darealturtywurty.turtychemistry.core.network.PacketHandler;
import io.github.darealturtywurty.turtychemistry.core.network.molder.ServerBoundMolderClickPacket;
import io.github.darealturtywurty.turtychemistry.core.util.MoldingPatternVertexHolders;
import io.github.darealturtywurty.turtychemistry.core.util.PatternVertex;
import net.minecraft.client.gui.components.AbstractButton;
import net.minecraft.client.gui.narration.NarrationElementOutput;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.client.renderer.GameRenderer;
import net.minecraft.client.sounds.SoundManager;
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
    public static final ResourceLocation DRAWING_AREA_TEXTURE = new ResourceLocation(TurtyChemistry.MODID,
            "textures/gui/molder/molder_drawing_area.png");
    private static final int BUTTON_SIZE = 18;
    private final List<PatternButton> patternButtonList = new ArrayList<>();
    private final BlockPos blockPos;
    private DrawingSpace drawingPanelWidget;
    private ItemStack currentStack = ItemStack.EMPTY;

    public MolderScreen(final Component pTitle, final BlockPos pos) {
        super(pTitle);
        this.blockPos = pos;

    }


    @Override
    protected void init() {
        super.init();
        final MoldingPatternVertexHolders[][] holders = new MoldingPatternVertexHolders[][]{MoldingPatternVertexHolders.values()};
        for (int i = 0; i < 4; i++) {
            for (int z = 0; z < 10; z++) {
                if (i < holders.length && z < holders[i].length) {
                    patternButtonList.add(addRenderableWidget(
                            new PatternButton(85 + (i * BUTTON_SIZE), (z * BUTTON_SIZE) + 30, BUTTON_SIZE, BUTTON_SIZE,
                                    holders[i][z], this)));
                }
            }
        }
        drawingPanelWidget = addRenderableWidget(
                new DrawingSpace((this.width >> 1) - 80, (this.height >> 3) - 2, width >> 1, (int) (height * 0.714f),
                        Component.empty(), patternButtonList, blockPos, this) {

                });

    }

    @Override
    public boolean mouseClicked(final double pMouseX, final double pMouseY, final int pButton) {
        drawingPanelWidget.onClick(pMouseX, pMouseY);

        return super.mouseClicked(pMouseX, pMouseY, pButton);
    }

    @Override
    public void render(final @NotNull PoseStack pPoseStack, final int pMouseX, final int pMouseY,
            final float pPartialTick) {
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


    private static final class PatternButton extends AbstractButton {

        public final MoldingPatternVertexHolders moldingPatternHolder;
        private final MolderScreen molderScreen;

        public PatternButton(final int pX, final int pY, final int pWidth, final int pHeight, final MoldingPatternVertexHolders check, final MolderScreen molderScreenReference) {
            super(pX, pY, pWidth, pHeight, check.getMessage());
            this.moldingPatternHolder = check;
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

    private static class DrawingSpace extends AbstractButton {
        private final List<PatternButton> buttonList;
        private final BlockPos pos;
        private final MolderScreen molderScreen;
        private boolean isHeld;

        public DrawingSpace(final int pX, final int pY, final int pWidth, final int pHeight, final Component pMessage, final List<PatternButton> patternButtonList, final BlockPos pos, final MolderScreen molderScreen) {
            super(pX, pY, pWidth, pHeight, pMessage);
            this.buttonList = patternButtonList;
            this.pos = pos;
            this.molderScreen = molderScreen;
        }

        public boolean isHeld() {
            return isHeld;
        }

        @Override
        public void onPress() {
            isHeld = true;

        }

        @Override
        public void renderButton(final @NotNull PoseStack pPoseStack, final int pMouseX, final int pMouseY, final float pPartialTick) {
            RenderSystem.setShader(GameRenderer::getPositionTexColorShader);
            RenderSystem.setShaderColor(1.0f, 1.0f, 1.0f, 1.0f);
            RenderSystem.setShaderTexture(0, DRAWING_AREA_TEXTURE);
            this.blit(pPoseStack, (this.width >> 1) + 40, (this.height >> 3) + 7, 0, 0, (this.width) - 1, this.height);

        }


        @Override
        public void playDownSound(final @NotNull SoundManager pHandler) {

        }

        @Override
        protected void onDrag(final double pMouseX, final double pMouseY, final double pDragX, final double pDragY) {
            super.onDrag(pMouseX, pMouseY, pDragX, pDragY);
            if (isHeld) {
                for (final PatternButton button : buttonList) {
                    for (final PatternVertex vertex : button.moldingPatternHolder.getVertices()) {
                        if (pMouseX == vertex.x && pMouseX == vertex.y) {
                            vertex.setSelected(true);
                        }
                    }
                }
            }
        }

        @Override
        public void onRelease(final double pMouseX, final double pMouseY) {
            super.onRelease(pMouseX, pMouseY);
            if (!molderScreen.currentStack.isEmpty()) {
                PacketHandler.CHANNEL.sendToServer(
                        new ServerBoundMolderClickPacket(true, pos, molderScreen.currentStack));
            }
            for (final PatternButton button : buttonList) {
                for (final PatternVertex vertex : button.moldingPatternHolder.getVertices()) {
                    if (!vertex.isSelected()) {
                        return;
                    } else if (vertex.isFinalVertex() && molderScreen.currentStack != null) {
                        PacketHandler.CHANNEL.sendToServer(
                                new ServerBoundMolderClickPacket(true, pos, molderScreen.currentStack));
                    }
                }
            }
            isHeld = false;
        }

        @Override
        public void updateNarration(final @NotNull NarrationElementOutput pNarrationElementOutput) {

        }

        @Override
        public boolean isHoveredOrFocused() {
            return false;
        }
    }

}
