package io.github.darealturtywurty.turtychemistry.common.block.entity;

import io.github.darealturtywurty.turtychemistry.core.init.BlockEntityInit;
import io.github.darealturtywurty.turtylib.common.blockentity.ModularBlockEntity;
import io.github.darealturtywurty.turtylib.common.blockentity.module.InventoryModule;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.inventory.ContainerData;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.state.BlockState;
import org.jetbrains.annotations.NotNull;

public final class MolderBlockEntity extends ModularBlockEntity {
    private static final String LEFTOVER_KEY = "Leftover";
    private static final String PROGRESS_KEY = "Progress";
    public final InventoryModule inventoryModule;
    private final placeHolderClass energyModule;
    private int progress, energy;
    private final ContainerData containerData = new ContainerData() {
        @Override
        public int get(final int pIndex) {
            return switch (pIndex) {
                case 0 -> MolderBlockEntity.this.progress;
                case 1 -> MolderBlockEntity.this.energy;
                case 2 -> 0; //TODO:add recipe to block entity
                default -> 0;
            };
        }

        @Override
        public void set(final int pIndex, final int pValue) {
            switch (pIndex) {
                case 0 -> MolderBlockEntity.this.progress = pValue;
                case 1 -> MolderBlockEntity.this.energy = pValue;
                default -> {
                }
            }
        }

        @Override
        public int getCount() {
            return 3;
        }
    };

    public MolderBlockEntity(final BlockPos pPos, final BlockState pBlockState) {
        super(BlockEntityInit.MOLDER.get(), pPos, pBlockState);
        this.inventoryModule = addModule(new InventoryModule(this, 2));

        this.energyModule = new placeHolderClass();
    }

    public boolean isMolding() {
        return progress > 0;

    }

    private int getCurrentEnergy() {
        return energyModule.getCapability().getEnergyStored;
    }

    public ItemStack getItem(final int slot) {
        return this.inventoryModule.getCapability().getStackInSlot(slot);
    }

    public boolean hasItem(final int slot) {
        return !getItem(slot).isEmpty();
    }

    public ContainerData getContainerData() {
        return this.containerData;
    }

    @Override
    protected void saveAdditional(final CompoundTag nbt) {
        super.saveAdditional(nbt);
        nbt.putInt(PROGRESS_KEY, this.progress);

    }

    @Override
    public void load(@NotNull final CompoundTag nbt) {
        super.load(nbt);
        this.progress = nbt.getInt(PROGRESS_KEY);

    }

    private static final class placeHolderClass {
        public cap getCapability() {
            return new cap();
        }

        private static final class cap {
            public int getEnergyStored = 1;
        }
    }
}
