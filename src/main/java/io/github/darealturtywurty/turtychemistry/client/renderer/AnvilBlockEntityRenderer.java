package io.github.darealturtywurty.turtychemistry.client.renderer;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Quaternion;
import com.mojang.math.Vector3f;
import io.github.darealturtywurty.turtychemistry.common.block.entity.AnvilBlockEntity;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.block.model.ItemTransforms;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderer;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import net.minecraft.client.renderer.entity.ItemRenderer;
import net.minecraft.world.level.block.AnvilBlock;
import org.jetbrains.annotations.NotNull;

public final class AnvilBlockEntityRenderer implements BlockEntityRenderer<AnvilBlockEntity> {
    private final ItemRenderer renderContext;

    public AnvilBlockEntityRenderer(BlockEntityRendererProvider.Context ctx) {
        this.renderContext = ctx.getItemRenderer();
    }

    @Override
    public void render(@NotNull AnvilBlockEntity pBlockEntity, float pPartialTick, @NotNull PoseStack pPoseStack, @NotNull MultiBufferSource pBufferSource, int pPackedLight, int pPackedOverlay) {
        pPoseStack.pushPose();
        pPoseStack.translate(0.5D, 1, 0.5D);
        switch (pBlockEntity.getBlockState().getValue(AnvilBlock.FACING)) {
            case SOUTH, NORTH -> {
                pPoseStack.mulPose(Vector3f.ZP.rotationDegrees(90));
                pPoseStack.mulPose(Vector3f.YP.rotationDegrees(90));
            }
            case EAST, WEST -> {
                pPoseStack.mulPose(new Quaternion(Vector3f.YP, 180, true));
                pPoseStack.mulPose(Vector3f.XP.rotationDegrees(90));
            }
        }
        renderContext.renderStatic(pBlockEntity.getItem(), ItemTransforms.TransformType.FIXED, pPackedLight,
                pPackedOverlay, pPoseStack, pBufferSource, (int) pBlockEntity.getBlockPos().above().asLong());
        pPoseStack.popPose();
    }
}
