package io.github.darealturtywurty.turtychemistry.client.ui.components;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.Tesselator;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.mojang.math.Matrix4f;

import io.github.darealturtywurty.turtylib.client.util.ClientUtils;
import net.minecraft.client.gui.components.AbstractWidget;
import net.minecraft.client.gui.narration.NarrationElementOutput;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.block.BlockRenderDispatcher;
import net.minecraft.client.renderer.texture.DynamicTexture;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.TextComponent;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.state.BlockState;

public class WorldPreviewWidget extends AbstractWidget {
    private final ResourceLocation textureLoc;
    private final DynamicTexture texture;
    private final int imageWidth, imageHeight;
    private final RenderType renderType;
    
    public WorldPreviewWidget(int x, int y, int width, int height, int blockWidth, int blockHeight) {
        super(x, y, width, height, TextComponent.EMPTY);
        this.imageWidth = blockWidth;
        this.imageHeight = blockHeight;
        this.texture = new DynamicTexture(this.imageWidth, this.imageHeight, true);
        this.textureLoc = ClientUtils.getMinecraft().getTextureManager().register("world_preview/1", this.texture);
        this.renderType = RenderType.text(this.textureLoc);
    }
    
    @Override
    public void render(PoseStack stack, int mouseX, int mouseY, float partialTicks) {
        if (this.visible) {
            final Matrix4f matrix = stack.last().pose();
            final MultiBufferSource.BufferSource buffer = MultiBufferSource
                    .immediate(Tesselator.getInstance().getBuilder());
            final VertexConsumer vertexConsumer = buffer.getBuffer(this.renderType);
            vertexConsumer.vertex(matrix, 0.0F, this.imageHeight, 0F).color(255, 255, 255, 255).uv(0.0F, 1.0F)
                    .uv2(15728880).endVertex();
            vertexConsumer.vertex(matrix, this.imageWidth, this.imageHeight, 0F).color(255, 255, 255, 255)
                    .uv(1.0F, 1.0F).uv2(15728880).endVertex();
            vertexConsumer.vertex(matrix, this.imageWidth, 0.0F, 0F).color(255, 255, 255, 255).uv(1.0F, 0.0F)
                    .uv2(15728880).endVertex();
            vertexConsumer.vertex(matrix, 0.0F, 0.0F, 0F).color(255, 255, 255, 255).uv(0.0F, 0.0F).uv2(15728880)
                    .endVertex();
            buffer.endBatch();
        }
    }

    public void setPixels(int x, int y, int rgba) {
        this.texture.getPixels().setPixelRGBA(x, y, rgba);
    }
    
    @Override
    public void updateNarration(NarrationElementOutput narration) {
        defaultButtonNarrationText(narration);
    }
    
    public void upload() {
        this.texture.upload();
    }
    
    @SuppressWarnings("resource")
    public static int getAverageBlockColor(BlockState state, BlockPos pos) {
        final BlockRenderDispatcher dispatcher = ClientUtils.getMinecraft().getBlockRenderer();
        final TextureAtlasSprite texture = dispatcher.getBlockModelShaper().getTexture(state,
                ClientUtils.getMinecraft().level, pos);
        final int pixelCount = texture.getWidth() * texture.getHeight();
        int totalAlpha = 0, totalRed = 0, totalGreen = 0, totalBlue = 0;
        for (int x = 0; x < texture.getWidth(); x++) {
            for (int y = 0; y < texture.getHeight(); y++) {
                final int color = texture.getPixelRGBA(0, x, y);
                totalAlpha += color >> 24 & 0xFF;
                totalBlue += color >> 16 & 0xFF;
                totalGreen += color >> 8 & 0xFF;
                totalRed += color & 0xFF;
            }
        }
        
        final int alpha = totalAlpha / pixelCount;
        final int red = totalRed / pixelCount;
        final int green = totalGreen / pixelCount;
        final int blue = totalBlue / pixelCount;
        
        return alpha << 24 | red << 16 | green << 8 | blue;
    }
}
