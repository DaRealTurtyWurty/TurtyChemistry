package io.github.darealturtywurty.turtychemistry.recipe;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import io.github.darealturtywurty.turtychemistry.TurtyChemistry;
import net.minecraft.core.NonNullList;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.GsonHelper;
import net.minecraft.world.Container;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.Recipe;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraft.world.level.Level;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.items.IItemHandlerModifiable;
import net.minecraftforge.items.wrapper.RecipeWrapper;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public final class FoundryRecipe implements Recipe<Container> {

    public static final String ID = "foundry_smelt";
    private final ResourceLocation id;
    private final NonNullList<Ingredient> ingredients;

    private final int processTime;

    FoundryRecipe(final ResourceLocation id, final NonNullList<Ingredient> ingredients, final int processTime) {
        this.id = id;
        this.ingredients = ingredients;
        this.processTime = processTime;

    }

    @Override
    public @NotNull NonNullList<Ingredient> getIngredients() {
        return ingredients;
    }

    @Override
    public boolean matches(final @NotNull Container pContainer, final @NotNull Level pLevel) {
        if (pLevel.isClientSide()) {
            return false;
        }
        return ingredients.get(0).test(pContainer.getItem(1));
    }

    public boolean matches(IItemHandlerModifiable container, Level level) {
        return matches(new RecipeWrapper(container), level);
    }

    public int getProcessTime() {
        return processTime;
    }

    @Override
    public ItemStack assemble(Container pContainer) {
        return ItemStack.EMPTY;
    }

    @Override
    public boolean canCraftInDimensions(int pWidth, int pHeight) {
        return true;
    }

    @Override
    public ItemStack getResultItem() {
        return ItemStack.EMPTY;
    }

    @Override
    public ResourceLocation getId() {
        return this.id;
    }

    @Override
    public RecipeSerializer<?> getSerializer() {
        return Serializer.INSTANCE;
    }

    @Override
    public RecipeType<?> getType() {
        return Type.INSTANCE;
    }

    public static final class Type implements RecipeType<FoundryRecipe> {
        public static final Type INSTANCE = new Type();
    }

    public static final class Serializer implements RecipeSerializer<FoundryRecipe> {
        public static final Serializer INSTANCE = new Serializer();
        public static final ResourceLocation ID = new ResourceLocation(TurtyChemistry.MODID, FoundryRecipe.ID);

        @Override
        public @NotNull FoundryRecipe fromJson(@NotNull ResourceLocation pRecipeId, @NotNull JsonObject pSerializedRecipe) {

            final JsonArray ingredients = GsonHelper.getAsJsonArray(pSerializedRecipe, "ingredients");
            final NonNullList<Ingredient> inputs = NonNullList.withSize(1, Ingredient.EMPTY);
            for (int i = 0; i < inputs.size(); i++) {
                inputs.set(i, Ingredient.fromJson(ingredients.get(i)));
            }
            final int processTime = GsonHelper.getAsInt(pSerializedRecipe, "process_time");
            return new FoundryRecipe(pRecipeId, inputs, processTime);
        }

        @Override
        public @Nullable FoundryRecipe fromNetwork(ResourceLocation pRecipeId, FriendlyByteBuf pBuffer) {
            final NonNullList<Ingredient> inputs = NonNullList.withSize(pBuffer.readInt(), Ingredient.EMPTY);
            inputs.replaceAll(ingredient -> Ingredient.fromNetwork(pBuffer));

            final int processTime = pBuffer.readInt();
            return new FoundryRecipe(pRecipeId, inputs, processTime);
        }

        @Override
        public void toNetwork(final @NotNull FriendlyByteBuf pBuffer, final @NotNull FoundryRecipe pRecipe) {
            pBuffer.writeInt(pRecipe.getIngredients().size());
            pRecipe.getIngredients().forEach(ingredient -> ingredient.toNetwork(pBuffer));
            pBuffer.writeFluidStack(FluidStack.loadFluidStackFromNBT(pRecipe.getResultItem().getOrCreateTag()));
            pBuffer.writeInt(pRecipe.processTime);
        }
    }
}
