package io.github.darealturtywurty.turtychemistry.client.ui;

import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.PoseStack;

import io.github.darealturtywurty.turtychemistry.client.ui.core.ContainerMachineScreen;
import io.github.darealturtywurty.turtychemistry.client.util.Resources.ShaleFracturer;
import io.github.darealturtywurty.turtychemistry.common.container.ShaleFracturerContainer;
import net.minecraft.client.gui.components.ImageButton;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.player.Inventory;

public class ShaleFracturerScreen extends ContainerMachineScreen<ShaleFracturerContainer> {
    // Buttons
    private StartButton startButton;
    private int doorFrames = 0;

    public ShaleFracturerScreen(ShaleFracturerContainer container, Inventory playerInv, Component title) {
        super(container, playerInv, title);
    }

    @Override
    protected void init() {
        super.init();
        addWidget(
                new StartButton(this, this.leftPos + this.width / 2, this.topPos + this.height / 2, 32, 32));
    }

    @Override
    protected void renderBg(PoseStack stack, float partialTicks, int mouseX, int mouseY) {
        if (this.startButton != null && this.doorFrames <= ShaleFracturer.DOOR_ANIM.frames.length) {
            RenderSystem.setShaderTexture(0, ShaleFracturer.BASE_DOOR);
        } else {
            RenderSystem.setShaderTexture(0, ShaleFracturer.DOOR_ANIM.getFrame(this.doorFrames++));
        }

        RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);
        final int x = this.leftPos + this.width / 2;
        final int y = this.topPos + this.height / 2;
        blit(stack, x, y, 0, 0, this.width, this.height);
    }

    public static class StartButton extends ImageButton {
        public boolean hasPressed;

        public StartButton(ShaleFracturerScreen screen, int xPos, int yPos, int width, int height) {
            super(xPos, yPos, width, height, 0, 0, 32, ShaleFracturer.START_BUTTON, 32, 64, btn -> {
                if (btn instanceof final StartButton button) {
                    press(screen, button);
                }
            });

            screen.startButton = this;
        }

        private static void press(ShaleFracturerScreen screen, StartButton btn) {
            screen.startButton = null;
            screen.removeWidget(btn);
        }
    }
}
