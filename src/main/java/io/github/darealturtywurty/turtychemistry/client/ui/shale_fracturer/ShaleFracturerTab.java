package io.github.darealturtywurty.turtychemistry.client.ui.shale_fracturer;

import static io.github.darealturtywurty.turtychemistry.client.ui.shale_fracturer.ShaleFracturerScreen.calculateTabHeight;
import static io.github.darealturtywurty.turtychemistry.client.ui.shale_fracturer.ShaleFracturerScreen.calculateTabWidth;
import static io.github.darealturtywurty.turtychemistry.client.ui.shale_fracturer.ShaleFracturerScreen.calculateTabX;

import javax.annotation.Nonnull;

import com.mojang.blaze3d.vertex.PoseStack;

import io.github.darealturtywurty.turtychemistry.TurtyChemistry;
import io.github.darealturtywurty.turtychemistry.client.ui.core.comonents.tab.Tab;
import io.github.darealturtywurty.turtychemistry.client.ui.core.comonents.tab.TabPage;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.resources.ResourceLocation;

public class ShaleFracturerTab extends Tab {
    private final Component label;

    public ShaleFracturerTab(ShaleFracturerScreen screen, Orientation orientation, String name,
            ResourceLocation icon, @Nonnull TabPage page, int leftPos, int topPos, int xPos, int yPos) {
        super(screen, page, orientation, icon, xPos, yPos, calculateTabWidth(orientation),
                calculateTabHeight(orientation), calculateTabX(orientation),
                tab -> tab.isHovered || tab.isSelected ? 32 : 0);
        this.label = new TranslatableComponent("tab." + TurtyChemistry.MODID + ".shale_fracturer." + name);
    }

    @Override
    public void render(PoseStack stack, int mouseX, int mouseY, float partialTicks) {
        super.render(stack, mouseX, mouseY, partialTicks);
        if (this.isHovered) {
            this.screen.getScreen().renderTooltip(stack, this.label, mouseX, mouseY);
        }
    }
}