package io.github.darealturtywurty.turtychemistry.block.fluidtype;

import io.github.darealturtywurty.turtychemistry.TurtyChemistry;
import net.minecraft.client.Minecraft;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.level.pathfinder.BlockPathTypes;
import net.minecraftforge.client.extensions.common.IClientFluidTypeExtensions;
import net.minecraftforge.common.SoundActions;
import net.minecraftforge.fluids.FluidType;

import java.util.function.Consumer;

public final class MoltenSteelType extends FluidType {
   public static final ResourceLocation MOLTEN_STEEL_STILL = new ResourceLocation(TurtyChemistry.MODID);
    public static final ResourceLocation MOLTEN_STEEL_FLOWING = new ResourceLocation(TurtyChemistry.MODID);

    public MoltenSteelType() {
        super(FluidType.Properties.create()
                .supportsBoating(false)
                .canHydrate(false)
                .canDrown(true)
                .canExtinguish(false)
                .canPushEntity(true)
                .canSwim(true)
                .pathType(BlockPathTypes.LAVA)
                .adjacentPathType(BlockPathTypes.LAVA)
                .canConvertToSource(false)
                .fallDistanceModifier(0.15f)
                .motionScale(0.0115)
                .rarity(Rarity.UNCOMMON)
                .viscosity(5000)
                .density(2000)
                .temperature(3000)
                .sound(SoundActions.BUCKET_FILL, SoundEvents.BUCKET_FILL)
                .sound(SoundActions.BUCKET_EMPTY, SoundEvents.BUCKET_EMPTY)
                .lightLevel(15)
        );
    }
    @Override
    public void initializeClient(Consumer<IClientFluidTypeExtensions> consumer) {
        consumer.accept(new IClientFluidTypeExtensions() {

            @Override
            public ResourceLocation getStillTexture() {
                return MOLTEN_STEEL_STILL;
            }

            @Override
            public ResourceLocation getFlowingTexture() {
                return MOLTEN_STEEL_FLOWING;
            }

            @Override
            public ResourceLocation getOverlayTexture() {
                return MOLTEN_STEEL_STILL;
            }

            @Override
            public ResourceLocation getRenderOverlayTexture(Minecraft mc) {
                return null;
            }

            @Override
            public int getTintColor() {
                return 0xFFFFFFFF;
            }
        });
    }
}
