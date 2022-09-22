package io.github.darealturtywurty.turtychemistry.client.renderer;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Vector3f;
import io.github.darealturtywurty.turtychemistry.client.models.MolderMainBody;
import io.github.darealturtywurty.turtychemistry.client.models.MolderPiston;
import io.github.darealturtywurty.turtychemistry.common.block.entity.MolderBlockEntity;
import io.github.darealturtywurty.turtychemistry.common.block.molder.MolderBlock;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.block.model.ItemTransforms;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderer;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import net.minecraft.client.renderer.entity.ItemRenderer;
import net.minecraft.world.item.ItemStack;
import org.jetbrains.annotations.NotNull;

public final class MolderBlockEntityRenderer implements BlockEntityRenderer<MolderBlockEntity> {


    private static final float OSCILLATION_SPEED = 16;
    private static final float AMPLITUDE = 0.6f;
    private final BlockEntityRendererProvider.Context context;
    private final MolderMainBody molderMainBody;
    private final MolderPiston molderPiston;
    private final ItemRenderer itemRenderer;
    private float oscillationProgress = 0;

    public MolderBlockEntityRenderer(BlockEntityRendererProvider.Context ctx) {
        this.context = ctx;
        this.molderMainBody = new MolderMainBody(ctx.bakeLayer(MolderMainBody.LAYER_LOCATION));
        this.molderPiston = new MolderPiston(ctx.bakeLayer(MolderPiston.LAYER_LOCATION));
        this.itemRenderer = ctx.getItemRenderer();
    }

    @Override
    public void render(final @NotNull MolderBlockEntity pBlockEntity, final float pPartialTick, final @NotNull PoseStack pPoseStack, final @NotNull MultiBufferSource pBufferSource, final int pPackedLight, final int pPackedOverlay) {
        pPoseStack.pushPose();
        pPoseStack.translate(0.5f, 1.5f, 0.5f);
        pPoseStack.mulPose(Vector3f.ZP.rotationDegrees(180));
        pPoseStack.mulPose(
                Vector3f.YP.rotationDegrees(pBlockEntity.getBlockState().getValue(MolderBlock.FACING).toYRot()));
        this.molderMainBody.renderToBuffer(pPoseStack,
                pBufferSource.getBuffer(RenderType.entitySolid(MolderMainBody.TEXTURE)), pPackedLight, pPackedOverlay,
                1f, 1f, 1f, 1f);

        pPoseStack.translate(0, 1f, 0);
        if (!pBlockEntity.getItem(1).isEmpty()) {
            pPoseStack.mulPose(Vector3f.ZP.rotationDegrees(90));
            pPoseStack.mulPose(Vector3f.YP.rotationDegrees(90));
            itemRenderer.renderStatic(new ItemStack(pBlockEntity.getItem(1).getItem()),
                    ItemTransforms.TransformType.FIXED, pPackedLight, pPackedOverlay, pPoseStack, pBufferSource,
                    (int) pBlockEntity.getBlockPos().asLong());
            pPoseStack.mulPose(Vector3f.XP.rotationDegrees(90));
        }
        if (!pBlockEntity.isMolding()) {
            pPoseStack.translate(0, -0.3f, 0);
        } else {
            pPoseStack.translate(0, (Math.sin(
                            oscillationProgress * 2 * Math.PI / OSCILLATION_SPEED) * (AMPLITUDE / 2) + (AMPLITUDE / 2)) - 0.48f,
                    0);
            oscillationProgress += 0.1f;
            if (oscillationProgress >= OSCILLATION_SPEED * 2) {
                oscillationProgress = 0;
            }
        }


        this.molderPiston.renderToBuffer(pPoseStack,
                pBufferSource.getBuffer(RenderType.entitySolid(MolderPiston.TEXTURE)), pPackedLight, pPackedOverlay, 1,
                1, 1, 1);

        pPoseStack.popPose();
    }
}
