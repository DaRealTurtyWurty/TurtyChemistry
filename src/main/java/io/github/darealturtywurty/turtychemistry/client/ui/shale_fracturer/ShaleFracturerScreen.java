package io.github.darealturtywurty.turtychemistry.client.ui.shale_fracturer;

import java.util.stream.Stream;

import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.PoseStack;

import io.github.darealturtywurty.turtychemistry.client.ui.core.ContainerMachineScreen;
import io.github.darealturtywurty.turtychemistry.client.ui.core.comonents.NumberInput;
import io.github.darealturtywurty.turtychemistry.client.ui.core.comonents.TexturedButton;
import io.github.darealturtywurty.turtychemistry.client.ui.core.comonents.tab.Tab.Orientation;
import io.github.darealturtywurty.turtychemistry.client.ui.core.comonents.tab.TabHolder;
import io.github.darealturtywurty.turtychemistry.client.ui.core.comonents.tab.TabPage;
import io.github.darealturtywurty.turtychemistry.client.util.Resources.Icons;
import io.github.darealturtywurty.turtychemistry.client.util.Resources.ShaleFracturer;
import io.github.darealturtywurty.turtychemistry.common.block.entity.ShaleFracturerBlockEntity;
import io.github.darealturtywurty.turtychemistry.common.container.ShaleFracturerContainer;
import net.minecraft.client.gui.components.Button;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.player.Inventory;

public class ShaleFracturerScreen extends ContainerMachineScreen<ShaleFracturerContainer>
        implements TabHolder<ShaleFracturerTab> {
    private static final float FRAMERATE = 1f / 60f;

    // Widgets
    private TexturedButton startButton;
    protected NumberInput speedInput, energyInput, waterInput;

    // Counters
    private int doorFrames = 0;

    // Framerate Handler
    private final double time = System.currentTimeMillis() / 1000L;

    // Tab system
    private final ShaleFracturerTab[] tabs = new ShaleFracturerTab[8];
    private ShaleFracturerTab currentTab;

    // Random Stuff
    private final ShaleFracturerBlockEntity blockEntity;

    public ShaleFracturerScreen(ShaleFracturerContainer container, Inventory playerInv, Component title) {
        super(container, playerInv, title);
        this.imageWidth = 256;
        this.imageHeight = 208;
        this.blockEntity = container.getBlockEntity();
    }

    protected static int calculateTabHeight(Orientation orientation) {
        int height;
        height = switch (orientation) {
            case TOP_BOTTOM, BOTTOM_TOP -> 30;
            case RIGHT_LEFT, LEFT_RIGHT -> 28;
            default -> throw new IllegalArgumentException("Unexpected value: " + orientation);
        };

        return height;
    }

    protected static int calculateTabWidth(Orientation orientation) {
        int width;
        width = switch (orientation) {
            case TOP_BOTTOM, BOTTOM_TOP -> 28;
            case RIGHT_LEFT, LEFT_RIGHT -> 30;
            default -> throw new IllegalArgumentException("Unexpected value: " + orientation);
        };

        return width;
    }

    protected static int calculateTabX(Orientation orientation) {
        return switch (orientation) {
            case TOP_BOTTOM -> 0;
            case BOTTOM_TOP -> 30;
            case LEFT_RIGHT -> 60;
            case RIGHT_LEFT -> 92;
            default -> throw new IllegalArgumentException("Unexpected value: " + orientation);
        };
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
    public ShaleFracturerTab getSelectedTab() {
        return this.currentTab;
    }

    @Override
    public ShaleFracturerTab[] getTabs() {
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
    public void setSelectedTab(ShaleFracturerTab tab) {
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
    protected void containerTick() {
        super.containerTick();
        this.speedInput.tick();
        this.energyInput.tick();
        this.waterInput.tick();
    }

    @Override
    protected void init() {
        super.init();
        this.startButton = addRenderableWidget(new TexturedButton(ShaleFracturer.START_BUTTON,
                this.leftPos + 63, this.topPos + 39, 130, 130, 0, 0, 130, 260, 1.0f, btn -> {
                    ShaleFracturerScreen.this.startButton = null;
                    ShaleFracturerScreen.this.removeWidget(btn);
                }, Button.NO_TOOLTIP));

        addTabs();

        this.minecraft.keyboardHandler.setSendRepeatsToGui(true);
        addHomeWidgets();
    }

    @Override
    protected void renderBg(PoseStack stack, float partialTicks, int mouseX, int mouseY) {
        final double currentTime = System.currentTimeMillis() / 1000L;
        final double timePassed = currentTime - this.time;

        RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);
        if (this.startButton != null && this.doorFrames <= ShaleFracturer.DOOR_ANIM.frames.length) {
            RenderSystem.setShaderTexture(0, ShaleFracturer.BASE_DOOR);
            blit(stack, this.leftPos, this.topPos, 0, 0, this.imageWidth, this.imageHeight);
        } else if (this.doorFrames < ShaleFracturer.DOOR_ANIM.frames.length && this.currentTab == null) {
            RenderSystem.setShaderTexture(0, ShaleFracturer.DOOR_ANIM.getFrame(this.doorFrames));
            if (timePassed >= 0.5D) {
                this.doorFrames++;
            }
            blit(stack, this.leftPos, this.topPos, 0, 0, this.imageWidth, this.imageHeight);
            if (this.doorFrames == ShaleFracturer.DOOR_ANIM.frames.length - 1) {
                this.doorFrames = 0;
                for (final var tab : getTabs()) {
                    if (tab == null) {
                        continue;
                    }

                    tab.visible = true;
                }

                setSelectedTab(this.tabs[0]);
            }
        }
    }

    @Override
    protected void renderLabels(PoseStack stack, int mouseX, int mouseY) {

    }

    private void addHomeWidgets() {
        this.speedInput = addRenderableWidget(new NumberInput(
                this.leftPos + 20 + this.font.width(HomePage.SPEED) - this.font.width("________"),
                this.topPos + 50, 20, 5, 3, 0, 200, 100, this.title));
        this.energyInput = addRenderableWidget(new NumberInput(
                this.leftPos + 20 + this.font.width(HomePage.LIMIT_ENERGY) - this.font.width("________"),
                this.topPos + 64, 20, 5, this.title));
        this.waterInput = addRenderableWidget(new NumberInput(
                this.leftPos + 20 + this.font.width(HomePage.LIMIT_WATER) - this.font.width("________"),
                this.topPos + 78, 20, 5, this.title));
        this.energyInput.setUnits("te/t");
        this.waterInput.setUnits("b/t");
        this.speedInput.setOnChanged(speed -> this.blockEntity.setSpeed(speed));
        this.energyInput.setOnChanged(energy -> this.blockEntity.setEnergyInputLimit(energy));
        this.waterInput.setOnChanged(water -> this.blockEntity.setWaterInputLimit(water));
        this.speedInput.setVisible(false);
        this.energyInput.setVisible(false);
        this.waterInput.setVisible(false);
    }

    private void addTabs() {
        final int xPos = this.leftPos - 29;
        final int yPos = this.topPos + 30;
        this.tabs[0] = new ShaleFracturerTab(this, Orientation.LEFT_RIGHT, "home", Icons.HOME,
                new HomePage(this, this.leftPos, this.topPos), this.leftPos, this.topPos, xPos, yPos);

        this.tabs[1] = new ShaleFracturerTab(this, Orientation.LEFT_RIGHT, "temperature", Icons.FLAME,
                new TemperaturePage(this, this.leftPos, this.topPos), this.leftPos, this.topPos, xPos,
                yPos + 60);

        this.tabs[2] = new ShaleFracturerTab(this, Orientation.LEFT_RIGHT, "water", Icons.WATER_DROPLET,
                new WaterPage(this, this.leftPos, this.topPos), this.leftPos, this.topPos, xPos, yPos + 120);

        this.tabs[3] = new ShaleFracturerTab(this, Orientation.LEFT_RIGHT, "natural_gas", Icons.GAS,
                new GasPage(this, this.leftPos, this.topPos), this.leftPos, this.topPos, xPos, yPos + 180);

        this.tabs[4] = new ShaleFracturerTab(this, Orientation.RIGHT_LEFT, "items", Icons.BACKPACK,
                new ItemsPage(this, this.leftPos, this.topPos), this.leftPos, this.topPos,
                this.leftPos + this.imageWidth - 1, yPos);

        this.tabs[5] = new ShaleFracturerTab(this, Orientation.RIGHT_LEFT, "scanning", Icons.RADAR,
                new ScanningPage(this, this.leftPos, this.topPos), this.leftPos, this.topPos,
                this.leftPos + this.imageWidth - 1, yPos + 60);

        this.tabs[6] = new ShaleFracturerTab(this, Orientation.RIGHT_LEFT, "deposit", Icons.MINECART,
                new DepositPage(this, this.leftPos, this.topPos), this.leftPos, this.topPos,
                this.leftPos + this.imageWidth - 1, yPos + 120);

        this.tabs[7] = new ShaleFracturerTab(this, Orientation.RIGHT_LEFT, "energy", Icons.LIGHTNING_BOLT,
                new EnergyPage(this, this.leftPos, this.topPos), this.leftPos, this.topPos,
                this.leftPos + this.imageWidth - 1, yPos + 180);

        for (final var tab : getTabs()) {
            if (tab == null) {
                continue;
            }

            addWidget(tab);
            tab.visible = false;
        }
    }
}
