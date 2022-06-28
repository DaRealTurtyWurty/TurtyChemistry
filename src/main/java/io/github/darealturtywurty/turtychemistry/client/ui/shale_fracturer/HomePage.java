package io.github.darealturtywurty.turtychemistry.client.ui.shale_fracturer;

import java.util.List;

import com.mojang.blaze3d.vertex.PoseStack;

import io.github.darealturtywurty.turtychemistry.TurtyChemistry;
import io.github.darealturtywurty.turtychemistry.client.util.TurtyChemistryResources;
import io.github.darealturtywurty.turtylib.client.ui.components.tabs.TabPage;
import io.github.darealturtywurty.turtylib.client.util.ClientUtils;
import io.github.darealturtywurty.turtylib.client.util.GuiUtils;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;

public class HomePage extends TabPage {
    protected static final Component SPEED = Component
        .translatable("page.home." + TurtyChemistry.MODID + ".input.speed").append(": ");
    protected static final Component LIMIT_ENERGY = Component
        .translatable("page.home." + TurtyChemistry.MODID + ".input.limit_energy").append(": ");
    protected static final Component LIMIT_WATER = Component
        .translatable("page.home." + TurtyChemistry.MODID + ".input.limit_water").append(": ");
    protected static final MutableComponent ON_OFF_LABEL = Component
        .translatable("page.home." + TurtyChemistry.MODID + ".label.on_off");
    protected static final MutableComponent EXTEND_DRILL_LABEL = Component
        .translatable("page.home." + TurtyChemistry.MODID + ".label.extend_drill");
    protected static final Component CONFIRM_BUTTON = Component.translatable(TurtyChemistry.MODID + ".button.confirm");
    protected static final Component CANCEL_BUTTON = Component.translatable(TurtyChemistry.MODID + ".button.cancel");
    protected static final Component TURN_ON = Component
        .translatable("page.home." + TurtyChemistry.MODID + ".popout.label.turn_on");
    protected static final Component TURN_OFF = Component
        .translatable("page.home." + TurtyChemistry.MODID + ".popout.label.turn_off");
    protected static final Component RETRACT_DRILL = Component
        .translatable("page.home." + TurtyChemistry.MODID + ".popout.label.retract_drill");
    protected static final Component EXTEND_DRILL = Component
        .translatable("page.home." + TurtyChemistry.MODID + ".popout.label.extend_drill");
    protected static final List<Component> SPEED_INFO_TOOLTIP = List.of(
        Component.translatable("page.home." + TurtyChemistry.MODID + ".info.speed_0"),
        Component.translatable("page.home." + TurtyChemistry.MODID + ".info.speed_1"));

    private final ShaleFracturerScreen screen;
    private final int leftPos, topPos;

    public HomePage(ShaleFracturerScreen screen, int leftPos, int topPos) {
        super(TurtyChemistryResources.BASE, leftPos, topPos, 256, 256, 256, 256);
        this.screen = screen;
        this.leftPos = leftPos;
        this.topPos = topPos;
    }

    @Override
    public void initWidgets() {
        this.screen.speedInput.setVisible(true);
        this.screen.energyInput.setVisible(true);
        this.screen.waterInput.setVisible(true);
        this.screen.speedInfo.setVisible(true);
        this.screen.energyInfo.setVisible(true);
        this.screen.waterInfo.setVisible(true);
        this.screen.onOffButton.setVisible(true);
        this.screen.extendDrillButton.setVisible(true);
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

        ClientUtils.getFont().draw(stack, SPEED, this.leftPos + 20, this.topPos + 52, 0x404040);
        ClientUtils.getFont().draw(stack, LIMIT_ENERGY, this.leftPos + 20, this.topPos + 66, 0x404040);
        ClientUtils.getFont().draw(stack, LIMIT_WATER, this.leftPos + 20, this.topPos + 80, 0x404040);
    }

    @Override
    public void uninitWidgets() {
        this.screen.speedInput.setVisible(false);
        this.screen.energyInput.setVisible(false);
        this.screen.waterInput.setVisible(false);
        this.screen.speedInfo.setVisible(false);
        this.screen.energyInfo.setVisible(false);
        this.screen.waterInfo.setVisible(false);
        this.screen.onOffButton.setVisible(false);
        this.screen.extendDrillButton.setVisible(false);
    }
}