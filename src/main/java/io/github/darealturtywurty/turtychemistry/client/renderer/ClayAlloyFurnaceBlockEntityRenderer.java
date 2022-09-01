package io.github.darealturtywurty.turtychemistry.client.renderer;

import com.mojang.blaze3d.vertex.PoseStack;
import io.github.darealturtywurty.turtychemistry.common.block.entity.ClayAlloyFurnaceBlockEntity;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderer;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import net.minecraft.world.level.block.Blocks;
import net.minecraftforge.client.model.data.ModelData;
import org.jetbrains.annotations.NotNull;

public class ClayAlloyFurnaceBlockEntityRenderer implements BlockEntityRenderer<ClayAlloyFurnaceBlockEntity> {
    private final BlockEntityRendererProvider.Context context;

    public ClayAlloyFurnaceBlockEntityRenderer(BlockEntityRendererProvider.Context ctx) {
        this.context = ctx;
    }

    @Override
    public void render(@NotNull ClayAlloyFurnaceBlockEntity blockEntity, float partialTicks, @NotNull PoseStack stack, @NotNull MultiBufferSource buffer,
                       int packedLight, int packedOverlay) {
        blockEntity.multiblock.getPositions().forEach(pos -> {
            stack.pushPose();

            int x = pos.getX() - blockEntity.getBlockPos().getX();
            int y = pos.getY() - blockEntity.getBlockPos().getY();
            int z = pos.getZ() - blockEntity.getBlockPos().getZ();
            stack.translate(x, y, z);

            this.context.getBlockRenderDispatcher().renderSingleBlock(
                    Blocks.PINK_STAINED_GLASS.defaultBlockState(),
                    stack,
                    buffer,
                    packedLight,
                    packedOverlay,
                    ModelData.EMPTY,
                    RenderType.translucent()
            );

            stack.popPose();
        });
    }
}
