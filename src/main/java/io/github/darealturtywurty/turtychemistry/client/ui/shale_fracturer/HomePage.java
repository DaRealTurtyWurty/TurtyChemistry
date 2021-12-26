package io.github.darealturtywurty.turtychemistry.client.ui.shale_fracturer;

import com.mojang.blaze3d.vertex.PoseStack;

import io.github.darealturtywurty.turtychemistry.TurtyChemistry;
import io.github.darealturtywurty.turtychemistry.client.ui.core.comonents.tab.TabPage;
import io.github.darealturtywurty.turtychemistry.client.util.ClientUtils;
import io.github.darealturtywurty.turtychemistry.client.util.GuiUtils;
import io.github.darealturtywurty.turtychemistry.client.util.Resources.ShaleFracturer;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TranslatableComponent;

public class HomePage extends TabPage {
    protected static final Component SPEED = new TranslatableComponent(
            "page." + TurtyChemistry.MODID + ".input.speed").append(": ________");
    protected static final Component LIMIT_ENERGY = new TranslatableComponent(
            "page." + TurtyChemistry.MODID + ".input.limit_energy").append(": ________");
    protected static final Component LIMIT_WATER = new TranslatableComponent(
            "page." + TurtyChemistry.MODID + ".input.limit_water").append(": ________");
    private final ShaleFracturerScreen screen;
    private final int leftPos, topPos;

    public HomePage(ShaleFracturerScreen screen, int leftPos, int topPos) {
        super(ShaleFracturer.BASE, leftPos, topPos, 256, 256, 256, 256);
        this.screen = screen;
        this.leftPos = leftPos;
        this.topPos = topPos;
    }

    @Override
    public void initWidgets() {
        this.screen.speedInput.setVisible(true);
        this.screen.energyInput.setVisible(true);
        this.screen.waterInput.setVisible(true);
    }

    @Override
    public void renderCenterground(PoseStack stack, int mouseX, int mouseY) {
        GuiUtils.drawCenteredString(stack, this.screen.getTitle(), this.leftPos + 128, this.topPos + 8,
                0x404040);
        GuiUtils.drawCenteredString(stack, this.screen.getSelectedTab().getMessage(), this.leftPos + 128,
                this.topPos + 38, 0x404040);

        ClientUtils.getFont().draw(stack, SPEED, this.leftPos + 20, this.topPos + 52, 0x404040);
        ClientUtils.getFont().draw(stack, LIMIT_ENERGY, this.leftPos + 20, this.topPos + 66, 0x404040);
        ClientUtils.getFont().draw(stack, LIMIT_WATER, this.leftPos + 20, this.topPos + 80, 0x404040);
    }

    @Override
    public void renderForeground(PoseStack stack, int mouseX, int mouseY) {

    }

    @Override
    public void uninitWidgets() {
        this.screen.speedInput.setVisible(false);
        this.screen.energyInput.setVisible(false);
        this.screen.waterInput.setVisible(false);
    }
}