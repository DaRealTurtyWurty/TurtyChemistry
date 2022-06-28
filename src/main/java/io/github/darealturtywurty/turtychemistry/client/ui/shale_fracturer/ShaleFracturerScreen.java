package io.github.darealturtywurty.turtychemistry.client.ui.shale_fracturer;

import java.util.stream.Stream;

import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.PoseStack;

import io.github.darealturtywurty.turtychemistry.TurtyChemistry;
import io.github.darealturtywurty.turtychemistry.client.util.TurtyChemistryResources;
import io.github.darealturtywurty.turtychemistry.common.block.entity.ShaleFracturerBlockEntity;
import io.github.darealturtywurty.turtychemistry.common.container.ShaleFracturerContainer;
import io.github.darealturtywurty.turtylib.client.ui.ContainerMachineScreen;
import io.github.darealturtywurty.turtylib.client.ui.components.AnimatableTexture;
import io.github.darealturtywurty.turtylib.client.ui.components.InformationWidget;
import io.github.darealturtywurty.turtylib.client.ui.components.NumberInput;
import io.github.darealturtywurty.turtylib.client.ui.components.Popout;
import io.github.darealturtywurty.turtylib.client.ui.components.TexturedButton;
import io.github.darealturtywurty.turtylib.client.ui.components.ToggleButton;
import io.github.darealturtywurty.turtylib.client.ui.components.tabs.GroupedTab;
import io.github.darealturtywurty.turtylib.client.ui.components.tabs.Tab;
import io.github.darealturtywurty.turtylib.client.ui.components.tabs.Tab.Orientation;
import io.github.darealturtywurty.turtylib.client.ui.components.tabs.TabHolder;
import io.github.darealturtywurty.turtylib.client.ui.components.tabs.TabPage;
import io.github.darealturtywurty.turtylib.client.util.GuiUtils;
import io.github.darealturtywurty.turtylib.client.util.Resources;
import io.github.darealturtywurty.turtylib.client.util.Resources.Icons;
import net.minecraft.client.gui.components.Button;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.player.Inventory;

public class ShaleFracturerScreen extends ContainerMachineScreen<ShaleFracturerContainer> implements TabHolder<Tab> {
    private static final float FRAMERATE = 1f / 60f;

    // Home Page Widgets
    protected TexturedButton startButton;
    protected NumberInput speedInput, energyInput, waterInput;
    protected InformationWidget speedInfo, energyInfo, waterInfo;
    protected ToggleButton onOffButton, extendDrillButton;
    protected Popout onOffPopout, retractDrillPopout;

    // Counters
    private int doorFrames = 0;

    // Tab system
    private final Tab[] tabs = new Tab[8];
    private Tab currentTab;

    // Random Stuff
    private final ShaleFracturerBlockEntity blockEntity;

    public ShaleFracturerScreen(ShaleFracturerContainer container, Inventory playerInv, Component title) {
        super(container, playerInv, title);
        this.imageWidth = 256;
        this.imageHeight = 208;
        this.blockEntity = container.getBlockEntity();
    }

    @Override
    public TabPage[] getPages() {
        return Stream.of(this.tabs).map(tab -> tab.page).toList().toArray(new TabPage[0]);
    }

    @Override
    public ContainerMachineScreen<?> getScreen() {
        return this;
    }

    @Override
    public Tab getSelectedTab() {
        return this.currentTab;
    }

    @Override
    public Tab[] getTabs() {
        return this.tabs;
    }

    @Override
    public void render(PoseStack stack, int mouseX, int mouseY, float partialTicks) {
        if (this.currentTab != null) {
            this.currentTab.page.renderBackground(stack, mouseX, mouseY);
            this.currentTab.page.renderCenterground(stack, mouseX, mouseY);
        }

        super.render(stack, mouseX, mouseY, partialTicks);
        for (final var tab : getTabs()) {
            if (tab == null) {
                continue;
            }
            tab.render(stack, mouseX, mouseY, partialTicks);
        }

        if (this.currentTab != null) {
            this.currentTab.page.renderForeground(stack, mouseX, mouseY);
        }
    }

    @Override
    public void setSelectedTab(Tab tab) {
        if (tab == null)
            return;
        if (this.currentTab != null) {
            this.currentTab.page.uninitWidgets();
            this.currentTab.isSelected = false;
        }

        this.currentTab = tab;
        tab.isSelected = true;
        this.currentTab.page.initWidgets();
    }

    @Override
    public boolean shouldCloseOnEsc() {
        return true;
    }

    @Override
    protected void containerTick() {
        super.containerTick();
        this.speedInput.tick();
        this.energyInput.tick();
        this.waterInput.tick();
    }

    @Override
    protected void init() {
        super.init();
        if (this.startButton == null && this.doorFrames != 1) {
            this.startButton = addRenderableWidget(new TexturedButton(TurtyChemistryResources.START_BUTTON,
                this.leftPos + 63, this.topPos + 39, 130, 130, 0, 0, 130, 260, 1.0f, btn -> {
                    ShaleFracturerScreen.this.startButton = null;
                    ShaleFracturerScreen.this.removeWidget(btn);
                }, Button.NO_TOOLTIP));
        }

        addTabs();

        this.minecraft.keyboardHandler.setSendRepeatsToGui(true);
        addHomeWidgets();
        
        // createWorldWidget();
    }

    @Override
    protected void renderBg(PoseStack stack, float partialTicks, int mouseX, int mouseY) {
        if (this.startButton != null) {
            RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);
            RenderSystem.setShaderTexture(0, TurtyChemistryResources.BASE_DOOR);
            blit(stack, this.leftPos, this.topPos, 0, 0, this.imageWidth, this.imageHeight);
        } else if (this.doorFrames != 1) {
            this.doorFrames = 0;
            for (final var tab : getTabs()) {
                if (tab == null) {
                    continue;
                }

                tab.visible = true;
                if (tab.hasAnimation()) {
                    tab.getAnimatableIcon().setVisible(true);
                }
            }

            setSelectedTab(this.tabs[0]);
        }
    }
    
    @Override
    protected void renderLabels(PoseStack stack, int mouseX, int mouseY) {

    }

    private void addHomeWidgets() {
        this.speedInput = addRenderableWidget(new NumberInput(this.leftPos + 20 + this.font.width(HomePage.SPEED),
            this.topPos + 50, 20, 5, 3, 0, 200, 100, this.title));
        this.energyInput = addRenderableWidget(new NumberInput(
            this.leftPos + 20 + this.font.width(HomePage.LIMIT_ENERGY), this.topPos + 64, 20, 5, this.title));
        this.waterInput = addRenderableWidget(new NumberInput(this.leftPos + 20 + this.font.width(HomePage.LIMIT_WATER),
            this.topPos + 78, 20, 5, this.title));
        this.energyInput.setUnits("te/t");
        this.waterInput.setUnits("b/t");

        this.speedInfo = addRenderableWidget(new InformationWidget(this,
            this.leftPos + 20 + this.font.width(HomePage.SPEED) + this.speedInput.getWidth() + 5, this.topPos + 47, 16,
            16, HomePage.SPEED_INFO_TOOLTIP.toArray(new Component[0])));
        this.speedInfo.active = false;
        this.energyInfo = addRenderableWidget(new InformationWidget(this,
            this.leftPos + 20 + this.font.width(HomePage.LIMIT_ENERGY) + this.energyInput.getWidth()
                + this.font.width(this.energyInput.getUnits()) + 5,
            this.topPos + 61, 16, 16));
        this.energyInfo.active = false;
        this.waterInfo = addRenderableWidget(new InformationWidget(this,
            this.leftPos + 20 + this.font.width(HomePage.LIMIT_WATER) + this.waterInput.getWidth()
                + this.font.width(this.waterInput.getUnits()) + 5,
            this.topPos + 77, 16, 16));
        this.waterInfo.active = false;

        this.onOffButton = addRenderableWidget(new ToggleButton(this.leftPos + 20, this.topPos + 100, 32, 16));
        this.extendDrillButton = addRenderableWidget(new ToggleButton(this.leftPos + 20, this.topPos + 120, 32, 16));

        this.onOffPopout = new Popout(this.leftPos, this.topPos, 256, 256,
            (stack, mouseX, mouseY, partialTicks) -> GuiUtils.drawWordWrap(stack,
                this.onOffButton.isStateTriggered() ? HomePage.TURN_OFF : HomePage.TURN_ON, this.leftPos + 20,
                this.topPos + 20, 216, 0x404040),
            this::switchTabStates);

        this.retractDrillPopout = new Popout(this.leftPos, this.topPos, 256, 256,
            (stack, mouseX, mouseY, partialTicks) -> GuiUtils.drawWordWrap(stack,
                this.extendDrillButton.isStateTriggered() ? HomePage.RETRACT_DRILL : HomePage.EXTEND_DRILL,
                this.leftPos + 20, this.topPos + 20, 216, 0x404040),
            this::switchTabStates);

        final Button onOffConfirmButton = new Button(this.leftPos + 20, this.topPos + 216, 64, 20,
            HomePage.CONFIRM_BUTTON, btn -> {
                this.onOffPopout.setVisible(false);
                this.onOffButton.setStateTriggered(!this.onOffButton.isStateTriggered());
                switchTabStates();
            });

        final Button onOffCancelButton = new Button(this.leftPos + 172, this.topPos + 216, 64, 20,
            HomePage.CANCEL_BUTTON, btn -> {
                this.onOffPopout.setVisible(false);
                switchTabStates();
            });

        final Button extendDrillConfirmButton = new Button(this.leftPos + 20, this.topPos + 216, 64, 20,
            HomePage.CONFIRM_BUTTON, btn -> {
                this.retractDrillPopout.setVisible(false);
                this.extendDrillButton.setStateTriggered(!this.extendDrillButton.isStateTriggered());
                switchTabStates();
            });

        final Button extendDrillCancelButton = new Button(this.leftPos + 172, this.topPos + 216, 64, 20,
            HomePage.CANCEL_BUTTON, btn -> {
                this.retractDrillPopout.setVisible(false);
                switchTabStates();
            });

        this.onOffPopout.addWidgets(onOffConfirmButton, onOffCancelButton);
        this.retractDrillPopout.addWidgets(extendDrillConfirmButton, extendDrillCancelButton);

        this.speedInput.setOnChanged(this.blockEntity::setSpeed);
        this.energyInput.setOnChanged(this.blockEntity::setEnergyInputLimit);
        this.waterInput.setOnChanged(this.blockEntity::setWaterInputLimit);

        this.onOffButton.initTextureValues(0, 0, 0, 16, Resources.TOGGLE_SWITCH_RED_GREEN);
        this.extendDrillButton.initTextureValues(0, 0, 0, 16, Resources.TOGGLE_SWITCH_RED_GREEN);
        this.onOffButton.setLabel(HomePage.ON_OFF_LABEL);
        this.extendDrillButton.setLabel(HomePage.EXTEND_DRILL_LABEL);
        this.onOffButton.setOnPressed((mouseX, mouseY, key) -> {
            this.onOffPopout.setVisible(true);
            this.onOffButton.setStateTriggered(!this.onOffButton.isStateTriggered());
            switchTabStates();
        });

        this.extendDrillButton.setOnPressed((mouseX, mouseY, key) -> {
            this.retractDrillPopout.setVisible(true);
            this.extendDrillButton.setStateTriggered(!this.extendDrillButton.isStateTriggered());
            switchTabStates();
        });

        this.speedInput.setVisible(false);
        this.energyInput.setVisible(false);
        this.waterInput.setVisible(false);

        this.speedInfo.setVisible(false);
        this.energyInfo.setVisible(false);
        this.waterInfo.setVisible(false);

        this.onOffButton.setVisible(false);
        this.extendDrillButton.setVisible(false);

        this.onOffPopout.setActive(false);
    }

    private void addTabs() {
        final int xPos = this.leftPos - 29;
        final int yPos = this.topPos + 30;
        this.tabs[0] = new Tab(this, new HomePage(this, this.leftPos, this.topPos), Orientation.LEFT_RIGHT,
            TurtyChemistry.MODID + ".home", xPos, yPos);
        this.tabs[0].setIcon(Icons.HOME);

        this.tabs[1] = new Tab(this, new TemperaturePage(this, this.leftPos, this.topPos), Orientation.LEFT_RIGHT,
            TurtyChemistry.MODID + ".temperature", xPos, yPos + 60);
        this.tabs[1].setIcon(Icons.FLAME);

        this.tabs[2] = new Tab(this, new WaterPage(this, this.leftPos, this.topPos), Orientation.LEFT_RIGHT,
            TurtyChemistry.MODID + ".water", xPos, yPos + 120);
        this.tabs[2].setIcon(Icons.WATER_DROPLET);

        this.tabs[3] = new Tab(this, new GasPage(this, this.leftPos, this.topPos), Orientation.LEFT_RIGHT,
            TurtyChemistry.MODID + ".natural_gas", xPos, yPos + 180);
        this.tabs[3].setIcon(Icons.GAS);

        this.tabs[4] = new Tab(this, new ItemsPage(this, this.leftPos, this.topPos), Orientation.RIGHT_LEFT,
            TurtyChemistry.MODID + ".items", this.leftPos + this.imageWidth - 1, yPos);
        this.tabs[4].setIcon(Icons.BACKPACK);

        final Tab worldDisplayTab = new Tab(this, new HomePage(this, this.leftPos, this.topPos), Orientation.RIGHT_LEFT,
            TurtyChemistry.MODID + ".world_display", this.leftPos + this.imageWidth - 1, yPos + 60);
        final AnimatableTexture worldSide = addWidget(new AnimatableTexture(0, 0, Icons.WORLD));

        final Tab qualityTab = new Tab(this, new HomePage(this, this.leftPos, this.topPos), Orientation.RIGHT_LEFT,
            TurtyChemistry.MODID + ".quality", this.leftPos + this.imageWidth - 1, yPos + 60);

        this.tabs[5] = new GroupedTab(this, new ScanningPage(this, this.leftPos, this.topPos), Orientation.RIGHT_LEFT,
            TurtyChemistry.MODID + ".scanning", this.leftPos + this.imageWidth - 1, yPos + 60, worldDisplayTab,
            qualityTab);
        final AnimatableTexture radar = addWidget(
            new AnimatableTexture(this.leftPos + this.imageWidth - 1 + this.tabs[5].getWidth() / 2 - 8,
                yPos + 60 + this.tabs[5].getHeight() / 2 - 8, Icons.RADAR));
        this.tabs[5].setIcon(radar);
        worldDisplayTab.setIcon(worldSide);

        this.tabs[6] = new Tab(this, new DepositPage(this, this.leftPos, this.topPos), Orientation.RIGHT_LEFT,
            TurtyChemistry.MODID + ".deposit", this.leftPos + this.imageWidth - 1, yPos + 120);
        this.tabs[6].setIcon(Icons.MINECART);

        this.tabs[7] = new Tab(this, new EnergyPage(this, this.leftPos, this.topPos), Orientation.RIGHT_LEFT,
            TurtyChemistry.MODID + ".energy", this.leftPos + this.imageWidth - 1, yPos + 180);
        this.tabs[7].setIcon(Icons.LIGHTNING_BOLT);

        for (final var tab : getTabs()) {
            if (tab == null) {
                continue;
            }

            addWidget(tab);
            tab.visible = false;
            if (tab.hasAnimation()) {
                tab.getAnimatableIcon().setVisible(false);
            }
        }
    }

    private void switchTabStates() {
        for (final var tab : this.tabs) {
            if (tab != null) {
                tab.active = !tab.active;
            }
        }
    }

    private static int convertARGBToABGR(int rgb) {
        final int alpha = rgb >> 24 & 0xFF;
        final int red = rgb >> 16 & 0xFF;
        final int green = rgb >> 8 & 0xFF;
        final int blue = rgb & 0xFF;
        return alpha << 24 | blue << 16 | green << 8 | red;
    }
}
