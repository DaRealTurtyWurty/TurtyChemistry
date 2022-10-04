package io.github.darealturtywurty.turtychemistry.common.block.entity;

import io.github.darealturtywurty.turtychemistry.common.recipe.ClayAlloyFurnaceRecipe;
import io.github.darealturtywurty.turtychemistry.core.init.BlockEntityInit;
import io.github.darealturtywurty.turtychemistry.core.init.MultiblockInit;
import io.github.darealturtywurty.turtylib.common.blockentity.ModularBlockEntity;
import io.github.darealturtywurty.turtylib.common.blockentity.module.InventoryModule;
import io.github.darealturtywurty.turtylib.common.blockentity.module.MultiblockModule;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.inventory.ContainerData;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Recipe;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Collections;
import java.util.Set;
import java.util.stream.Collectors;

public final class ClayAlloyFurnaceBlockEntity extends ModularBlockEntity {
    public static final int MAX_BURN_TIME = 200;
    public final MultiblockModule multiblock;
    public final InventoryModule inventory;
    private int progress = 0, fuelProgress = 0;
    private final ContainerData data = new ContainerData() {
        @Override
        public int get(int index) {
            return switch (index) {
                case 0 -> ClayAlloyFurnaceBlockEntity.this.progress;
                case 1 -> ClayAlloyFurnaceBlockEntity.this.fuelProgress;
                case 2 -> {
                    ClayAlloyFurnaceRecipe recipe = ClayAlloyFurnaceBlockEntity.this.getRecipe();
                    yield recipe == null ? 0 : recipe.getProcessTime();
                }
                default -> 0;
            };
        }

        @Override
        public void set(int index, int value) {
            switch (index) {
                case 0 -> ClayAlloyFurnaceBlockEntity.this.progress = value;
                case 1 -> ClayAlloyFurnaceBlockEntity.this.fuelProgress = value;
                default -> {
                }
            }
        }

        @Override
        public int getCount() {
            return 3;
        }
    };
    private ItemStack leftover;

    public ClayAlloyFurnaceBlockEntity(BlockPos pos, BlockState state) {
        super(BlockEntityInit.CLAY_ALLOY_FURNACE.get(), pos, state);
        this.multiblock = addModule(new MultiblockModule(MultiblockInit.CLAY_ALLOY_FURNACE));
        this.inventory = addModule(new InventoryModule(this, 5));
    }

    public static Set<Recipe<?>> findRecipesByType(RecipeType<?> typeIn, Level world) {
        return world != null ? world.getRecipeManager().getRecipes().stream()
                .filter(recipe -> recipe.getType() == typeIn)
                .collect(Collectors.toSet()) : Collections.emptySet();
    }

    public static boolean isFuel(ItemStack stack) {
        return stack.is(ItemTags.LOGS_THAT_BURN);
    }

    @Override
    public void tick() {
        super.tick();
        if (this.level == null)
            return;

        if (!this.level.isClientSide) {
            if (!hasFuel())
                return;

            ClayAlloyFurnaceRecipe recipe = getRecipe();

            if (this.fuelProgress >= MAX_BURN_TIME || this.fuelProgress == 0) {
                this.fuelProgress = 0;
                if (hasItem(0) && recipe != null) {
                    getItem(0).shrink(1);
                    this.fuelProgress++;
                } else {
                    return;
                }
            } else {
                this.fuelProgress++;
            }

            if (this.leftover != null) {
                handleLeftover(this.leftover);
                return;
            }

            if (recipe == null) {
                this.progress = 0;
                return;
            }

            if (this.progress >= recipe.getProcessTime()) {
                this.progress = 0;
                ItemStack output = recipe.getResultItem();
                getItem(1).shrink(1);
                getItem(2).shrink(1);
                handleLeftover(output);
            } else {
                this.progress++;
            }
        }
    }

    private void handleLeftover(ItemStack leftover) {
        ItemStack output = this.inventory.getCapability().insertItem(3, leftover, false);
        if (!output.isEmpty()) {
            this.leftover = output;
        } else {
            this.leftover = null;
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

    public @Nullable ClayAlloyFurnaceRecipe getRecipe() {
        if (this.level == null)
            return null;

        Set<Recipe<?>> recipes = findRecipesByType(ClayAlloyFurnaceRecipe.Type.INSTANCE, this.level);
        for (Recipe<?> iRecipe : recipes) {
            var recipe = (ClayAlloyFurnaceRecipe) iRecipe;
            if (recipe.matches(this.inventory.getCapability(), this.level))
                return recipe;
        }

        return null;
    }

    public ContainerData getContainerData() {
        return this.data;
    }

    @Override
    protected void saveAdditional(CompoundTag nbt) {
        super.saveAdditional(nbt);

        nbt.putInt("Progress", this.progress);
        nbt.putInt("FuelProgress", this.fuelProgress);
        if (this.leftover != null)
            nbt.put("Leftover", this.leftover.save(new CompoundTag()));
    }

    @Override
    public void load(@NotNull CompoundTag nbt) {
        super.load(nbt);

        this.progress = nbt.getInt("Progress");
        this.fuelProgress = nbt.getInt("FuelProgress");
        if (nbt.contains("Leftover"))
            this.leftover = ItemStack.of(nbt.getCompound("Leftover"));
    }
}
