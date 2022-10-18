package io.github.darealturtywurty.turtychemistry.block.entity;

import io.github.darealturtywurty.turtychemistry.init.BlockEntityInit;
import io.github.darealturtywurty.turtychemistry.init.FluidInit;
import io.github.darealturtywurty.turtychemistry.init.ItemInit;
import io.github.darealturtywurty.turtychemistry.init.MultiblockInit;
import io.github.darealturtywurty.turtylib.common.blockentity.ModularBlockEntity;
import io.github.darealturtywurty.turtylib.common.blockentity.module.FluidModule;
import io.github.darealturtywurty.turtylib.common.blockentity.module.InventoryModule;
import io.github.darealturtywurty.turtylib.common.blockentity.module.MultiblockModule;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.inventory.ContainerData;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.capability.IFluidHandler;
import org.jetbrains.annotations.NotNull;

public final class FoundryBlockEntity extends ModularBlockEntity {
    public static final int MAX_BURN_TIME = 200;
    
    public final FluidModule fluidInventory;
    public final InventoryModule inventory;
    public final MultiblockModule multiblockModule;
    
    private int progress, fuelProgress;
    private FluidStack currentFluid;
    
    private final ContainerData containerData = new ContainerData() {
        @Override
        public int get(int pIndex) {
            return switch (pIndex) {
                case 0 -> FoundryBlockEntity.this.progress;
                case 1 -> FoundryBlockEntity.this.fuelProgress;
                case 2 -> FoundryBlockEntity.this.fluidInventory.getCapability().getFluidAmount();
                default -> 0;
            };
        }

        @Override
        public void set(int pIndex, int pValue) {
            switch (pIndex) {
                case 0 -> FoundryBlockEntity.this.progress = pValue;
                case 1 -> FoundryBlockEntity.this.fuelProgress = pValue;
                case 2 -> FoundryBlockEntity.this.setFluidLevel(pValue,FluidStack.EMPTY);
                default -> {
                }
            }
        }

        @Override
        public int getCount() {
            return 3;
        }
    };

    public FoundryBlockEntity(BlockPos pPos, BlockState pBlockState) {
        super(BlockEntityInit.FOUNDRY.get(), pPos, pBlockState);
        this.fluidInventory = addModule(new FluidModule(this, 80));
        this.inventory = addModule(new InventoryModule(this, 2));
        this.multiblockModule = addModule(new MultiblockModule(MultiblockInit.FOUNDRY));

    }

    public static boolean isFuel(ItemStack stack) {
        return stack.is(ItemTags.COALS) || stack.is(Items.LAVA_BUCKET);
    }

    public int getFluidLevel() {
        return this.fluidInventory.getCapability().getFluidAmount();
    }
    public void setFluidLevel(final int newLevel, final FluidStack stack)
    {
        this.fluidInventory.getCapability().fill(stack, IFluidHandler.FluidAction.EXECUTE);
    }
    public ContainerData getContainerData() {
        return this.containerData;
    }

    @Override
    protected void saveAdditional(CompoundTag nbt) {
        super.saveAdditional(nbt);
        nbt.putInt("Progress", this.progress);
        nbt.putInt("FuelProgress", this.fuelProgress);
    }

    @Override
    public void load(@NotNull CompoundTag nbt) {
        super.load(nbt);
        this.progress = nbt.getInt("Progress");
        this.fuelProgress = nbt.getInt("FuelProgress");
    }

    public FluidStack getFluidType()
    {
        this.currentFluid = this.fluidInventory.getCapability().getFluid();
        return currentFluid;
    }
    @Override
    public void tick() {
        super.tick();
        if (this.level == null) return;
        if (!this.level.isClientSide) {
            if (!hasFuel()) return;
            if (this.fuelProgress >= MAX_BURN_TIME || this.fuelProgress == 0) {
                this.fuelProgress = 0;
                if (hasItem(0)) {
                    getItem(0).shrink(1);
                    this.fuelProgress++;
                } else {
                    return;
                }
            } else {
                this.fuelProgress++;
            }
            if (this.progress >= 100) {
                this.progress = 0;
                processFluid(getItem(1));
                getItem(1).shrink(1);


            } else {
                this.progress++;
            }
        }
    }

    public boolean hasFuel() {
        return this.fuelProgress > 0 || hasItem(0);
    }

    private boolean hasItem(int slot) {
        return !getItem(slot).isEmpty();
    }

    private ItemStack getItem(int slot) {
        return this.inventory.getCapability().getStackInSlot(slot);
    }

    private void processFluid(final ItemStack stack) {
        if (stack.is(ItemInit.Ingots.STEEL_INGOT.get())) {
            fluidInventory.getCapability()
                    .fill(new FluidStack(FluidInit.MOLTEN_STEEL.get(), 3), IFluidHandler.FluidAction.EXECUTE);
        }

    }
}
