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

public class ClayAlloyFurnaceModel extends Model {
    public static final ModelLayerLocation LAYER_LOCATION = new ModelLayerLocation(
            new ResourceLocation(TurtyChemistry.MODID, "clay_alloy_furnace"), "main");
    public static final ResourceLocation TEXTURE = new ResourceLocation(TurtyChemistry.MODID,
            "textures/blocks/clay_alloy_furnace/main.png");

    public final ModelPart clayAlloyFurnace;

    public ClayAlloyFurnaceModel(ModelPart root) {
        super(RenderType::entitySolid);
        this.clayAlloyFurnace = root.getChild("clay_alloy_furnace");
    }

    public static LayerDefinition createLayer() {
        MeshDefinition meshdefinition = new MeshDefinition();
        PartDefinition partdefinition = meshdefinition.getRoot();

        PartDefinition clay_alloy_furnace = partdefinition.addOrReplaceChild("clay_alloy_furnace",
                CubeListBuilder.create(), PartPose.offset(0.0F, 24.0F, 0.0F));

        clay_alloy_furnace.addOrReplaceChild("chimney", CubeListBuilder.create().texOffs(90, 43)
                        .addBox(-5.0F, -48.0F, -3.0F, 2.0F, 16.0F, 6.0F, new CubeDeformation(0.0F)).texOffs(0, 37)
                        .addBox(-5.0F, -48.0F, 3.0F, 10.0F, 16.0F, 2.0F, new CubeDeformation(0.0F)).texOffs(0, 0)
                        .addBox(-5.0F, -48.0F, -5.0F, 10.0F, 16.0F, 2.0F, new CubeDeformation(0.0F)).texOffs(79, 118)
                        .addBox(3.0F, -48.0F, -3.0F, 2.0F, 16.0F, 6.0F, new CubeDeformation(0.0F)),
                PartPose.offset(0.0F, 0.0F, 0.0F));

        clay_alloy_furnace.addOrReplaceChild("main", CubeListBuilder.create().texOffs(84, 37)
                        .addBox(3.0F, -12.0F, -14.65F, 13.0F, 5.0F, 32.0F, new CubeDeformation(0.0F)).texOffs(0, 0)
                        .addBox(-16.0F, 15.0F, -14.65F, 32.0F, 5.0F, 32.0F, new CubeDeformation(0.0F)).texOffs(42, 59)
                        .addBox(11.0F, -7.0F, -14.65F, 5.0F, 22.0F, 32.0F, new CubeDeformation(0.0F)).texOffs(0, 37)
                        .addBox(-16.0F, -7.0F, -14.65F, 5.0F, 22.0F, 32.0F, new CubeDeformation(0.0F)).texOffs(96, 0)
                        .addBox(-11.0F, -7.0F, 12.35F, 22.0F, 22.0F, 5.0F, new CubeDeformation(0.0F)).texOffs(42, 37)
                        .addBox(-11.0F, -7.0F, -14.65F, 22.0F, 7.0F, 5.0F, new CubeDeformation(0.0F)).texOffs(0, 113)
                        .addBox(-11.0F, 9.0F, -14.65F, 22.0F, 6.0F, 5.0F, new CubeDeformation(0.0F)).texOffs(84, 81)
                        .addBox(-16.0F, -12.0F, -14.65F, 13.0F, 5.0F, 32.0F, new CubeDeformation(0.0F)).texOffs(41, 113)
                        .addBox(-3.0F, -12.0F, -14.65F, 6.0F, 5.0F, 13.0F, new CubeDeformation(0.0F)).texOffs(0, 91)
                        .addBox(-3.0F, -12.0F, 4.35F, 6.0F, 5.0F, 13.0F, new CubeDeformation(0.0F)),
                PartPose.offset(0.0F, -20.0F, -1.35F));

        return LayerDefinition.create(meshdefinition, 256, 256);
    }

    @Override
    public void renderToBuffer(@NotNull PoseStack stack, @NotNull VertexConsumer consumer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
        this.clayAlloyFurnace.render(stack, consumer, packedLight, packedOverlay, red, green, blue, alpha);
    }
}