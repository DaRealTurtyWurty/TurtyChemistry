package io.github.darealturtywurty.turtychemistry.client.screen;

import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.PoseStack;
import io.github.darealturtywurty.turtychemistry.TurtyChemistry;
import io.github.darealturtywurty.turtychemistry.common.block.container.RubberTreeTapContainer;
import io.github.darealturtywurty.turtychemistry.common.block.entity.RubberTreeTapBlockEntity;
import io.github.darealturtywurty.turtylib.client.ui.ContainerMachineScreen;
import net.minecraft.client.renderer.GameRenderer;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Inventory;
import net.minecraftforge.client.gui.widget.ExtendedButton;
import org.jetbrains.annotations.NotNull;

public class RubberTreeTapScreen extends ContainerMachineScreen<RubberTreeTapContainer> {

    private static final ResourceLocation TEXTURE = new ResourceLocation(TurtyChemistry.MODID, "textures/gui/treetap.png");
    private ExtendedButton startTappingButtion;

    public RubberTreeTapScreen(final RubberTreeTapContainer container, final Inventory playerInv, final Component title) {
        super(container, playerInv, title);
    }


    @Override
    protected void init() {
        super.init();
        this.startTappingButtion = addRenderableWidget(
                new ExtendedButton(this.leftPos, this.topPos, 128, 16, Component.literal("start tapping"),
                        button -> {
                            if (minecraft.level.getBlockEntity(BlockPos.ZERO) instanceof RubberTreeTapBlockEntity rubberTreeTapBlockEntity) {
                                rubberTreeTapBlockEntity.setTapping();
                            }
                        })
        );
    }

    @Override
    protected void renderBg(final @NotNull PoseStack stack, final float mouseX, final int mouseY, final int partialTicks) {
        renderBackground(stack);
        RenderSystem.setShader(GameRenderer::getPositionTexShader);
        RenderSystem.setShaderColor(1, 1, 1, 1);
        RenderSystem.setShaderTexture(0, TEXTURE);
        blit(stack, this.leftPos, this.topPos, 0, 0, this.imageWidth, this.imageHeight);
    }
}
