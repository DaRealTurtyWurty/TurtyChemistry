package io.github.darealturtywurty.turtychemistry.core.util;

import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.PoseStack;
import io.github.darealturtywurty.turtychemistry.TurtyChemistry;
import io.github.darealturtywurty.turtychemistry.client.screens.MolderScreen;
import io.github.darealturtywurty.turtychemistry.core.network.PacketHandler;
import io.github.darealturtywurty.turtychemistry.core.network.molder.ServerBoundMolderClickPacket;
import io.github.darealturtywurty.turtylib.client.util.GuiUtils;
import net.minecraft.client.gui.components.AbstractButton;
import net.minecraft.client.gui.narration.NarrationElementOutput;
import net.minecraft.client.renderer.GameRenderer;
import net.minecraft.client.sounds.SoundManager;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public class DrawingSpace extends AbstractButton {
    private static final ResourceLocation DRAWING_AREA_TEXTURE = new ResourceLocation(TurtyChemistry.MODID,
            "textures/gui/molder/molder_drawing_area.png");
    private final List<MolderScreen.PatternButton> buttonList;
    private final BlockPos pos;
    private final MolderScreen molderScreen;
    private boolean isHeld;

    private double beginX, beginY, endX, endY;
    private boolean isOnArea = true;

    public DrawingSpace(final int pX, final int pY, final int pWidth, final int pHeight, final Component pMessage, final List<MolderScreen.PatternButton> patternButtonList, final BlockPos pos, final MolderScreen molderScreen) {
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
        pPoseStack.pushPose();
        if (isHeld() && isMouseOnDrawingArea()) {
            GuiUtils.drawLine(beginX, beginY, endX, endY, 0, 0, 255, 255, 10);
        }
        pPoseStack.popPose();
    }

    @Override
    public void playDownSound(final @NotNull SoundManager pHandler) {
        super.playDownSound(pHandler);
    }

    @Override
    public void onClick(final double pMouseX, final double pMouseY) {
        beginY = pMouseY;
        beginX = pMouseX;
        endX = pMouseX;
        endY = pMouseY;
        super.onClick(pMouseX, pMouseY);
    }

    @Override
    protected void onDrag(final double pMouseX, final double pMouseY, final double pDragX, final double pDragY) {
        super.onDrag(pMouseX, pMouseY, pDragX, pDragY);

        if (isHeld) {
            if (pMouseX < this.x) {
                endX = this.x + 1;
            } else if (pMouseX > this.width + this.x) {
                endX = this.width + this.x;
            } else {
                endX = pMouseX;
            }
            if (pMouseY < this.y) {
                endY = this.y;
            } else if (pMouseY > this.height + this.y) {
                endY = this.height + this.y;
            } else {
                endY = pMouseY;
            }
            for (final MolderScreen.PatternButton button : buttonList) {
                for (final PatternVertex vertex : button.moldingPatternHolder.getVertices()) {
                    if (pMouseX == vertex.x && pMouseX == vertex.y) {
                        vertex.setSelected(true);
                    }
                }
            }
        }
    }

    @Override
    public void mouseMoved(final double pMouseX, final double pMouseY) {
        super.mouseMoved(pMouseX, pMouseY);
        isOnArea = !(pMouseX < this.x) && !(pMouseX > this.width + this.x) && !(pMouseY < this.y) && !(pMouseY > this.y + this.height);
        if (!isOnArea) {
            mouseReleased(pMouseX, pMouseY, 0);
        }
    }

    private void clearLine() {
        beginX = 0;
        beginY = 0;
        endX = 0;
        endY = 0;
    }

    public boolean isMouseOnDrawingArea() {
        if (!isOnArea) {
            clearLine();
        }
        return isOnArea;
    }

    @Override
    public void onRelease(final double pMouseX, final double pMouseY) {
        super.onRelease(pMouseX, pMouseY);
        clearLine();
        //TODO:remove this after testing
        if (!molderScreen.getCurrentStack().isEmpty()) {
            molderScreen.getMolderBlockEntity().setItem(molderScreen.getCurrentStack());
            PacketHandler.CHANNEL.sendToServer(new ServerBoundMolderClickPacket(pos, molderScreen.getCurrentStack()));
        }
        for (final MolderScreen.PatternButton button : buttonList) {
            for (final PatternVertex vertex : button.moldingPatternHolder.getVertices()) {
                if (!vertex.isSelected()) {
                    return;
                } else if (vertex.isFinalVertex() && molderScreen.getCurrentStack() != null) {
                    molderScreen.getMolderBlockEntity().setItem(molderScreen.getCurrentStack());
                    PacketHandler.CHANNEL.sendToServer(
                            new ServerBoundMolderClickPacket(pos, molderScreen.getCurrentStack()));
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
