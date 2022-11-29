package io.github.darealturtywurty.turtychemistry.client.renderer;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Vector3f;
import io.github.darealturtywurty.turtychemistry.client.models.ClayAlloyFurnaceModel;
import io.github.darealturtywurty.turtychemistry.client.models.ClayAlloyFurnaceTrayModel;
import io.github.darealturtywurty.turtychemistry.client.models.ClayAlloyFurnaceWoodModel;
import io.github.darealturtywurty.turtychemistry.block.entity.ClayAlloyFurnaceBlockEntity;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderer;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import org.jetbrains.annotations.NotNull;

public class ClayAlloyFurnaceBlockEntityRenderer implements BlockEntityRenderer<ClayAlloyFurnaceBlockEntity> {
    private final BlockEntityRendererProvider.Context context;
    private final ClayAlloyFurnaceModel furnaceModel;
    private final ClayAlloyFurnaceWoodModel woodModel;
    private final ClayAlloyFurnaceTrayModel trayModel;

    public ClayAlloyFurnaceBlockEntityRenderer(BlockEntityRendererProvider.Context ctx) {
        this.context = ctx;
        this.furnaceModel = new ClayAlloyFurnaceModel(ctx.bakeLayer(ClayAlloyFurnaceModel.LAYER_LOCATION));
        this.woodModel = new ClayAlloyFurnaceWoodModel(ctx.bakeLayer(ClayAlloyFurnaceWoodModel.LAYER_LOCATION));
        this.trayModel = new ClayAlloyFurnaceTrayModel(ctx.bakeLayer(ClayAlloyFurnaceTrayModel.LAYER_LOCATION));
    }

    @Override
    public void render(@NotNull ClayAlloyFurnaceBlockEntity blockEntity, float partialTicks, @NotNull PoseStack stack, @NotNull MultiBufferSource buffer, int packedLight, int packedOverlay) {
        stack.pushPose();
        stack.mulPose(Vector3f.ZP.rotationDegrees(180f));
        stack.translate(0.0f, -1.5f, 1.0f);
        this.furnaceModel.renderToBuffer(stack,
                buffer.getBuffer(RenderType.entitySolid(ClayAlloyFurnaceModel.TEXTURE)), packedLight, packedOverlay,
                1.0f, 1.0f, 1.0f, 1.0f);
        this.woodModel.renderToBuffer(stack,
                buffer.getBuffer(RenderType.entitySolid(ClayAlloyFurnaceWoodModel.TEXTURE)), packedLight,
                packedOverlay, 1.0f, 1.0f, 1.0f, 1.0f);
        this.trayModel.renderToBuffer(stack,
                buffer.getBuffer(RenderType.entityCutout(ClayAlloyFurnaceTrayModel.TEXTURE)), packedLight,
                packedOverlay, 1.0f, 1.0f, 1.0f, 1.0f);
        stack.popPose();
    }

    @Override
    public boolean shouldRenderOffScreen(@NotNull ClayAlloyFurnaceBlockEntity blockEntity) {
        return true;
    }
}
