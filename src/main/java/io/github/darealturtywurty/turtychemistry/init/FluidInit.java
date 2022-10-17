package io.github.darealturtywurty.turtychemistry.init;

import io.github.darealturtywurty.turtychemistry.TurtyChemistry;
import io.github.darealturtywurty.turtychemistry.block.fluid.MoltenSteelFluid;
import io.github.darealturtywurty.turtychemistry.block.fluid.MoltenSteelFluidBlock;
import io.github.darealturtywurty.turtychemistry.block.fluidtype.MoltenSteelType;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.LiquidBlock;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.FlowingFluid;
import net.minecraft.world.level.material.Fluid;
import net.minecraftforge.fluids.FluidType;
import net.minecraftforge.fluids.ForgeFlowingFluid;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public final class FluidInit {
    public static final DeferredRegister<Fluid> FLUIDS = DeferredRegister.create(ForgeRegistries.FLUIDS,
            TurtyChemistry.MODID);
    public static final DeferredRegister<FluidType> FLUID_TYPES =
            DeferredRegister.create(ForgeRegistries.Keys.FLUID_TYPES,
            TurtyChemistry.MODID);


    //fluids
    public static final RegistryObject<FlowingFluid> MOLTEN_STEEL = FLUIDS.register("molten_steel",
            () -> new MoltenSteelFluid.Source(FluidInit.MOLTEN_STEEL_PROPERTIES));
    public static final RegistryObject<FlowingFluid> MOLTEN_STEEL_FLOWING = FLUIDS.register("molten_steel_flowing",
            () -> new MoltenSteelFluid.Flowing(FluidInit.MOLTEN_STEEL_PROPERTIES));

    //fluidtypes
    public static final RegistryObject<FluidType> MOLTEN_STEEL_TYPE = FLUID_TYPES.register("molten_steel_type",
            MoltenSteelType::new);

    //fluid blocks
    public static final RegistryObject<LiquidBlock> MOLTEN_STEEL_FLUID_BLOCK = BlockInit.BLOCKS.register(
            "molten_steel_block",
            ()->new MoltenSteelFluidBlock(MOLTEN_STEEL, BlockBehaviour.Properties.copy(Blocks.LAVA)));
    public static final ForgeFlowingFluid.Properties MOLTEN_STEEL_PROPERTIES =
            new ForgeFlowingFluid.Properties(MOLTEN_STEEL_TYPE, MOLTEN_STEEL, MOLTEN_STEEL_FLOWING)
                    .bucket(ItemInit.Buckets.MOLTEN_STEEL_BUCKET).block(MOLTEN_STEEL_FLUID_BLOCK);
}
