package io.github.darealturtywurty.turtychemistry.client.ui.shale_fracturer;

import com.mojang.blaze3d.vertex.PoseStack;

import io.github.darealturtywurty.turtychemistry.client.util.TurtyChemistryResources;
import io.github.darealturtywurty.turtylib.client.ui.components.tabs.TabPage;
import io.github.darealturtywurty.turtylib.client.util.GuiUtils;

public class GasPage extends TabPage {
    private final ShaleFracturerScreen screen;
    private final int leftPos, topPos;
    
    public GasPage(ShaleFracturerScreen screen, int leftPos, int topPos) {
        super(TurtyChemistryResources.BASE, leftPos, topPos, 256, 256, 256, 256);
        this.screen = screen;
        this.leftPos = leftPos;
        this.topPos = topPos;
    }
    
    @Override
    public void initWidgets() {
    }
    
    @Override
    public void renderCenterground(PoseStack stack, int mouseX, int mouseY) {

    }
    
    @Override
    public void renderForeground(PoseStack stack, int mouseX, int mouseY) {
        super.renderForeground(stack, mouseX, mouseY);
        GuiUtils.drawCenteredString(stack, this.screen.getTitle(), this.leftPos + 128, this.topPos + 8, 0x404040);
        GuiUtils.drawCenteredString(stack, this.screen.getSelectedTab().getMessage(), this.leftPos + 128,
                this.topPos + 38, 0x404040);
    }
    
    @Override
    public void uninitWidgets() {
    }
}