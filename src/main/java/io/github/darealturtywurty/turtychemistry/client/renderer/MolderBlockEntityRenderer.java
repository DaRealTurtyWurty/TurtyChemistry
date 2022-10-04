package io.github.darealturtywurty.turtychemistry.client.renderer;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Vector3f;
import io.github.darealturtywurty.turtychemistry.common.block.entity.MolderBlockEntity;
import io.github.darealturtywurty.turtychemistry.common.block.molder.MolderBlock;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.block.model.ItemTransforms;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderer;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import net.minecraft.client.renderer.entity.ItemRenderer;
import org.jetbrains.annotations.NotNull;

public final class MolderBlockEntityRenderer implements BlockEntityRenderer<MolderBlockEntity> {
    private final ItemRenderer itemRenderer;
    public MolderBlockEntityRenderer(BlockEntityRendererProvider.Context ctx) {
        this.itemRenderer = ctx.getItemRenderer();
    }
    @Override
    public void render(final @NotNull MolderBlockEntity pBlockEntity, final float pPartialTick, final @NotNull PoseStack pPoseStack, final @NotNull MultiBufferSource pBufferSource, final int pPackedLight, final int pPackedOverlay) {
        pPoseStack.pushPose();
        pPoseStack.translate(0.5f, 0.96f, 0.5f);
        switch (pBlockEntity.getBlockState().getValue(MolderBlock.FACING)) {
            case SOUTH -> {
                pPoseStack.mulPose(Vector3f.ZN.rotationDegrees(180));
                pPoseStack.mulPose(Vector3f.YP.rotationDegrees(180));
                pPoseStack.mulPose(Vector3f.XP.rotationDegrees(90));
            }
            case NORTH -> {
                pPoseStack.mulPose(Vector3f.ZP.rotationDegrees(-180));
                pPoseStack.mulPose(Vector3f.YP.rotationDegrees(180));
                pPoseStack.mulPose(Vector3f.XN.rotationDegrees(90));
            }
            case EAST -> {
                pPoseStack.mulPose(Vector3f.YP.rotationDegrees(90));
                pPoseStack.mulPose(Vector3f.XP.rotationDegrees(90));
            }
            case WEST -> {
                pPoseStack.mulPose(Vector3f.YP.rotationDegrees(-90));
                pPoseStack.mulPose(Vector3f.XP.rotationDegrees(90));
            }
        }
        pPoseStack.scale(0.6f, 0.6f, 0.6f);
        itemRenderer.renderStatic(pBlockEntity.getItem(), ItemTransforms.TransformType.FIXED, pPackedLight,
                pPackedOverlay, pPoseStack, pBufferSource, (int) pBlockEntity.getBlockPos().asLong());

        pPoseStack.popPose();
    }
}
