package io.github.darealturtywurty.turtychemistry.client.models;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import io.github.darealturtywurty.turtychemistry.TurtyChemistry;
import net.minecraft.client.model.Model;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.resources.ResourceLocation;
import org.jetbrains.annotations.NotNull;

public final class ClayAlloyFurnaceTrayModel extends Model {
    public static final ModelLayerLocation LAYER_LOCATION = new ModelLayerLocation(
            new ResourceLocation(TurtyChemistry.MODID, "clay_alloy_furnace_tray"), "main");
    public static final ResourceLocation TEXTURE = new ResourceLocation(TurtyChemistry.MODID,
            "textures/blocks/clay_alloy_furnace/tray.png");

    private final ModelPart tray;

    public ClayAlloyFurnaceTrayModel(ModelPart root) {
        super(RenderType::entitySolid);
        this.tray = root.getChild("tray");
    }

    public static LayerDefinition createLayer() {
        MeshDefinition meshdefinition = new MeshDefinition();
        PartDefinition partdefinition = meshdefinition.getRoot();

        partdefinition.addOrReplaceChild("tray", CubeListBuilder.create().texOffs(0, 0)
                        .addBox(-11.0F, -1.5F, -4.6667F,
                                22.0F, 1.0F, 24.0F,
                                new CubeDeformation(0.0F))
                        .texOffs(0, 4)
                        .addBox(10.0F, -0.5F, -4.6667F,
                                1.0F, 2.0F, 2.0F,
                                new CubeDeformation(0.0F))
                        .texOffs(0, 0)
                        .addBox(-11.0F, -0.5F, -4.6667F,
                                1.0F, 2.0F, 2.0F,
                                new CubeDeformation(
                                        0.0F)),
                PartPose.offset(0.0F, 11.5F, -8.3333F));

        return LayerDefinition.create(meshdefinition, 128, 128);
    }

    @Override
    public void renderToBuffer(@NotNull PoseStack poseStack, @NotNull VertexConsumer vertexConsumer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
        this.tray.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
    }
}