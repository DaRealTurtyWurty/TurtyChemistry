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

public final class MolderMainBody extends Model {
    public static final ModelLayerLocation LAYER_LOCATION = new ModelLayerLocation(
            new ResourceLocation(TurtyChemistry.MODID, "molder_main_body"), "main");

    public static final ResourceLocation TEXTURE = new ResourceLocation(TurtyChemistry.MODID,
            "textures/blocks/molder/body.png");
    private final ModelPart MainBody;


    public MolderMainBody(ModelPart root) {
        super(RenderType::entitySolid);
        this.MainBody = root.getChild("MainBody");

    }

    public static LayerDefinition createBodyLayer() {
        final MeshDefinition meshdefinition = new MeshDefinition();
        final PartDefinition partdefinition = meshdefinition.getRoot();

        final PartDefinition MainBody = partdefinition.addOrReplaceChild("MainBody", CubeListBuilder.create().texOffs(0, 0).addBox(-8.0F, -13.0F, -8.0F, 4.0F, 10.0F, 4.0F, new CubeDeformation(0.0F))
                .texOffs(0, 19).addBox(-8.0F, -3.0F, -8.0F, 16.0F, 3.0F, 16.0F, new CubeDeformation(0.0F))
                .texOffs(0, 0).addBox(-8.0F, -16.0F, -8.0F, 16.0F, 3.0F, 16.0F, new CubeDeformation(0.0F))
                .texOffs(16, 38).addBox(4.0F, -13.0F, -8.0F, 4.0F, 10.0F, 4.0F, new CubeDeformation(0.0F))
                .texOffs(0, 38).addBox(4.0F, -13.0F, 4.0F, 4.0F, 10.0F, 4.0F, new CubeDeformation(0.0F))
                .texOffs(0, 19).addBox(-8.0F, -13.0F, 4.0F, 4.0F, 10.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 24.0F, 0.0F));



        return LayerDefinition.create(meshdefinition, 16, 16);
    }


    @Override
    public void renderToBuffer(@NotNull PoseStack poseStack, @NotNull VertexConsumer vertexConsumer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
        MainBody.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);

    }
}