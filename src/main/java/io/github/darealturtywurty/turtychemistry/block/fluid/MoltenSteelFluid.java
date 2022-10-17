package io.github.darealturtywurty.turtychemistry.block.fluid;

import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.material.Fluid;
import net.minecraft.world.level.material.FluidState;
import net.minecraftforge.fluids.ForgeFlowingFluid;
import org.jetbrains.annotations.NotNull;

public abstract class MoltenSteelFluid extends ForgeFlowingFluid {

    public MoltenSteelFluid(Properties properties) {
        super(properties);
    }

    @Override
    public boolean isSource(@NotNull FluidState pState) {
        return false;
    }

    @Override
    public int getAmount(@NotNull FluidState pState) {
        return 0;
    }
    public static final class Flowing extends MoltenSteelFluid
    {

        public Flowing(Properties properties) {
            super(properties);
            this.registerDefaultState(this.getStateDefinition().any().setValue(LEVEL, 7));
        }

        @Override
        protected void createFluidStateDefinition(StateDefinition.@NotNull Builder<Fluid, FluidState> builder) {
            super.createFluidStateDefinition(builder);
            builder.add(LEVEL);
        }

        @Override
        public int getAmount(@NotNull FluidState state) {
            return state.getValue(LEVEL);
        }

        @Override
        public boolean isSource(@NotNull FluidState state) {
            return false;
        }
    }
    public static final class Source extends MoltenSteelFluid
    {

        public Source(Properties properties) {
            super(properties);
        }

        @Override
        public int getAmount(@NotNull FluidState state) {
            return 8;
        }

        @Override
        public boolean isSource(@NotNull FluidState state) {
            return true;
        }
    }
}
