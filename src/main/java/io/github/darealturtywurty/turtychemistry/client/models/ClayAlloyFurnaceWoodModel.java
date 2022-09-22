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

public final class ClayAlloyFurnaceWoodModel extends Model {
    public static final ModelLayerLocation LAYER_LOCATION = new ModelLayerLocation(
            new ResourceLocation(TurtyChemistry.MODID, "clay_alloy_furnace_wood"), "main");
    public static final ResourceLocation TEXTURE = new ResourceLocation(TurtyChemistry.MODID,
            "textures/blocks/clay_alloy_furnace/wood.png");

    public final ModelPart wood;
    public final ModelPart[] logs;

    public ClayAlloyFurnaceWoodModel(ModelPart root) {
        super(RenderType::entitySolid);
        this.wood = root.getChild("wood");
        this.logs = new ModelPart[6];
        for (int i = 0; i < 6; i++) {
            this.logs[i] = this.wood.getChild("log" + (i + 1));
        }
    }

    public static LayerDefinition createLayer() {
        MeshDefinition meshdefinition = new MeshDefinition();
        PartDefinition partdefinition = meshdefinition.getRoot();

        PartDefinition wood = partdefinition.addOrReplaceChild("wood", CubeListBuilder.create(),
                PartPose.offset(0.0F, 24.0F, 0.0F));

        PartDefinition log6 = wood.addOrReplaceChild("log6", CubeListBuilder.create(),
                PartPose.offset(0.0F, 0.0F, 0.0F));
        log6.addOrReplaceChild("cube_r1", CubeListBuilder.create().texOffs(30, 20)
                        .addBox(-10.0F, -10.0F, 2.0F, 13.0F, 4.0F, 4.0F, new CubeDeformation(0.0F)),
                PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, -2.6876F, 0.3913F, -2.9835F));

        PartDefinition log5 = wood.addOrReplaceChild("log5", CubeListBuilder.create(),
                PartPose.offset(0.0F, 0.0F, 0.0F));
        log5.addOrReplaceChild("cube_r2", CubeListBuilder.create().texOffs(30, 12)
                        .addBox(-9.0F, -11.0F, -5.0F, 13.0F, 4.0F, 4.0F, new CubeDeformation(0.0F)),
                PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, -0.7426F, 0.7849F, -0.3755F));

        PartDefinition log4 = wood.addOrReplaceChild("log4", CubeListBuilder.create(),
                PartPose.offset(0.0F, 0.0F, 0.0F));
        log4.addOrReplaceChild("cube_r3", CubeListBuilder.create().texOffs(0, 24)
                        .addBox(-5.0F, -10.0F, -9.0F, 13.0F, 4.0F, 4.0F, new CubeDeformation(0.0F)),
                PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 2.2739F, 0.9169F, 2.5725F));

        PartDefinition log3 = wood.addOrReplaceChild("log3", CubeListBuilder.create(),
                PartPose.offset(0.0F, 0.0F, 0.0F));
        log3.addOrReplaceChild("cube_r4", CubeListBuilder.create().texOffs(0, 16)
                        .addBox(-6.0F, -14.0F, 5.0F, 13.0F, 4.0F, 4.0F, new CubeDeformation(0.0F)),
                PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 1.566F, 1.2063F, 1.8112F));

        PartDefinition log2 = wood.addOrReplaceChild("log2", CubeListBuilder.create(),
                PartPose.offset(0.0F, 0.0F, 0.0F));
        log2.addOrReplaceChild("cube_r5", CubeListBuilder.create().texOffs(0, 8)
                        .addBox(-5.0F, -12.0F, -3.0F, 13.0F, 4.0F, 4.0F, new CubeDeformation(0.0F)),
                PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.5857F, 0.8701F, 0.7148F));

        wood.addOrReplaceChild("log1", CubeListBuilder.create().texOffs(0, 0)
                        .addBox(-9.0F, -9.0F, -3.0F, 20.0F, 4.0F, 4.0F, new CubeDeformation(0.0F)),
                PartPose.offset(0.0F, 0.0F, 0.0F));

        return LayerDefinition.create(meshdefinition, 64, 64);
    }

    @Override
    public void renderToBuffer(@NotNull PoseStack poseStack, @NotNull VertexConsumer vertexConsumer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
        this.wood.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
    }
}