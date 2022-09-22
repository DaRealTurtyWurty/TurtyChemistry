package io.github.darealturtywurty.turtychemistry.client.models;// Made with Blockbench 4.4.1
// Exported for Minecraft version 1.17 - 1.18 with Mojang mappings
// Paste this class into your mod and generate all required imports


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

public final class MolderPiston extends Model {
    public static final ModelLayerLocation LAYER_LOCATION = new ModelLayerLocation(
            new ResourceLocation(TurtyChemistry.MODID, "molder_piston"), "main");
    public static final ResourceLocation TEXTURE = new ResourceLocation(TurtyChemistry.MODID,
            "textures/blocks/molder/piston.png");
    private final ModelPart MolderPiston;


    public MolderPiston(ModelPart root) {
        super(RenderType::entitySolid);
        this.MolderPiston = root.getChild("MolderPiston");
    }

    public static LayerDefinition createBodyLayer() {
        final MeshDefinition meshdefinition = new MeshDefinition();
        final PartDefinition partdefinition = meshdefinition.getRoot();
        final PartDefinition MolderPiston = partdefinition.addOrReplaceChild("MolderPiston",
                CubeListBuilder.create().texOffs(0, 0)
                        .addBox(-4.0F, -7.0F, 15.0F, 8.0F, 2.0F, 8.0F, new CubeDeformation(0.0F)).texOffs(0, 0)
                        .addBox(-1.0F, -15.0F, 18.0F, 2.0F, 8.0F, 2.0F, new CubeDeformation(0.0F)),
                PartPose.offset(0.0F, 8.0F, -19.0F));
        return LayerDefinition.create(meshdefinition, 16, 16);
    }


    @Override
    public void renderToBuffer(@NotNull PoseStack poseStack, @NotNull VertexConsumer vertexConsumer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {

        MolderPiston.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);

    }
}