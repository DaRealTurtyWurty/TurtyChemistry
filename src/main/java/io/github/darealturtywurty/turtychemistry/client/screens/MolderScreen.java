package io.github.darealturtywurty.turtychemistry.client.screens;

import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.PoseStack;
import io.github.darealturtywurty.turtychemistry.TurtyChemistry;
import io.github.darealturtywurty.turtychemistry.common.menus.MolderMenu;
import net.minecraft.client.gui.components.AbstractButton;
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
            "textures/gui/clay_alloy_furnace.png");
    private static final int BUTTON_SIZE = 18;

    public MolderScreen(final MolderMenu pMenu, final Inventory pPlayerInventory, final Component pTitle) {
        super(pMenu, pPlayerInventory, pTitle);
    }
    private List<AbstractButton> patternButtonList = new ArrayList<>();
    @Override
    protected void init() {
        super.init();
        for(int i = 0; i < 4; i++)
        {
            for(int z = 0; z < 4; z++) {
                patternButtonList.add(addRenderableWidget(new PatternButton(80 + (i * BUTTON_SIZE),
                        (z * BUTTON_SIZE) + 50, BUTTON_SIZE,
                        BUTTON_SIZE,
                        MoldPatterns.TEST)));
            }
        }
        TurtyChemistry.LOGGER.info(patternButtonList.stream().count());
    }

    @Override
    public void render(final @NotNull PoseStack pPoseStack, final int pMouseX, final int pMouseY, final float pPartialTick) {
        this.renderBackground(pPoseStack);
        super.render(pPoseStack, pMouseX, pMouseY, pPartialTick);
    }

    @Override
    protected void renderBg(final @NotNull PoseStack pPoseStack, final float pPartialTick, final int pMouseX, final int pMouseY) {
        RenderSystem.setShader(GameRenderer::getPositionTexColorShader);
        RenderSystem.setShaderColor(1.0f, 1.0f, 1.0f, 1.0f);
        RenderSystem.setShaderTexture(0, TEXTURE);
        this.blit(pPoseStack, this.leftPos, this.topPos, 0, 0, this.imageWidth, this.imageHeight);
    }

    public enum MoldPatterns {
        TEST("test",Component.literal("test"));
        private final String name;
        private final Component message;
        MoldPatterns(final String name,final Component message) {
            this.name = name;
            this.message = message;
        }

        public Component getMessage() {
            return message;
        }

        public String getName() {
            return name;
        }
    }

    private static final class PatternButton extends AbstractButton {
        private final MoldPatterns moldPatterns;
        public PatternButton(final int pX, final int pY, final int pWidth, final int pHeight,
                final MoldPatterns moldPattern) {
            super(pX, pY, pWidth, pHeight, moldPattern.message);
            this.moldPatterns = moldPattern;
        }

        @Override
        public void onPress() {

        }

        @Override
        public void updateNarration(final NarrationElementOutput pNarrationElementOutput) {

        }
    }

}
