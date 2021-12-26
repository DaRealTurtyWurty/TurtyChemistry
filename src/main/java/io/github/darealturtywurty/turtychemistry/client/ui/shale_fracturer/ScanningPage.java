package io.github.darealturtywurty.turtychemistry.client.ui.shale_fracturer;

import com.mojang.blaze3d.vertex.PoseStack;

import io.github.darealturtywurty.turtychemistry.client.ui.core.comonents.tab.TabPage;
import io.github.darealturtywurty.turtychemistry.client.util.GuiUtils;
import io.github.darealturtywurty.turtychemistry.client.util.Resources.ShaleFracturer;

public class ScanningPage extends TabPage {
    private final ShaleFracturerScreen screen;
    private final int leftPos, topPos;

    public ScanningPage(ShaleFracturerScreen screen, int leftPos, int topPos) {
        super(ShaleFracturer.BASE, leftPos, topPos, 256, 256, 256, 256);
        this.screen = screen;
        this.leftPos = leftPos;
        this.topPos = topPos;
    }

    @Override
    public void initWidgets() {
    }

    @Override
    public void renderCenterground(PoseStack stack, int mouseX, int mouseY) {
        GuiUtils.drawCenteredString(stack, this.screen.getTitle(), this.leftPos + 128, this.topPos + 8,
                0x404040);
        GuiUtils.drawCenteredString(stack, this.screen.getSelectedTab().getMessage(), this.leftPos + 128,
                this.topPos + 38, 0x404040);
    }

    @Override
    public void renderForeground(PoseStack stack, int mouseX, int mouseY) {

    }

    @Override
    public void uninitWidgets() {
    }
}