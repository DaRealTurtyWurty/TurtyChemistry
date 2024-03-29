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
import net.minecraft.world.item.crafting.*;
import net.minecraft.world.level.Level;
import net.minecraftforge.items.IItemHandlerModifiable;
import net.minecraftforge.items.wrapper.RecipeWrapper;
import org.jetbrains.annotations.NotNull;

public final class ClayAlloyFurnaceRecipe implements Recipe<Container> {
    public static final String ID = "clay_alloy";

    private final ResourceLocation id;
    private final ItemStack output;
    private final NonNullList<Ingredient> recipeItems;
    private final int processTime;

    public ClayAlloyFurnaceRecipe(ResourceLocation id, ItemStack output, NonNullList<Ingredient> recipeItems,
            int processTime) {
        this.id = id;
        this.output = output;
        this.recipeItems = recipeItems;
        this.processTime = processTime;
    }

    @Override
    public @NotNull NonNullList<Ingredient> getIngredients() {
        return this.recipeItems;
    }

    @Override
    public boolean matches(@NotNull Container container, Level level) {
        if (level.isClientSide())
            return false;

        return recipeItems.get(0).test(container.getItem(1)) && recipeItems.get(1)
                .test(container.getItem(2)) || recipeItems.get(0).test(container.getItem(2)) && recipeItems.get(1)
                .test(container.getItem(1));
    }

    public boolean matches(IItemHandlerModifiable container, Level level) {
        return matches(new RecipeWrapper(container), level);
    }

    @Override
    public @NotNull ItemStack assemble(@NotNull Container container) {
        return this.output;
    }

    public int getProcessTime() {
        return this.processTime;
    }

    @Override
    public boolean canCraftInDimensions(int width, int height) {
        return true;
    }

    @Override
    public @NotNull ItemStack getResultItem() {
        return this.output.copy();
    }

    @Override
    public @NotNull ResourceLocation getId() {
        return this.id;
    }

    @Override
    public @NotNull RecipeSerializer<?> getSerializer() {
        return Serializer.INSTANCE;
    }

    @Override
    public @NotNull RecipeType<?> getType() {
        return Type.INSTANCE;
    }

    public static final class Type implements RecipeType<ClayAlloyFurnaceRecipe> {
        public static final Type INSTANCE = new Type();

        private Type() {
        }
    }

    public static final class Serializer implements RecipeSerializer<ClayAlloyFurnaceRecipe> {
        public static final Serializer INSTANCE = new Serializer();
        public static final ResourceLocation ID = new ResourceLocation(TurtyChemistry.MODID, ClayAlloyFurnaceRecipe.ID);

        private Serializer() {
        }

        @Override
        public @NotNull ClayAlloyFurnaceRecipe fromJson(@NotNull ResourceLocation recipeId, @NotNull JsonObject serializedRecipe) {
            ItemStack output = ShapedRecipe.itemStackFromJson(GsonHelper.getAsJsonObject(serializedRecipe, "output"));

            JsonArray ingredients = GsonHelper.getAsJsonArray(serializedRecipe, "ingredients");
            NonNullList<Ingredient> inputs = NonNullList.withSize(2, Ingredient.EMPTY);

            for (int i = 0; i < inputs.size(); i++) {
                inputs.set(i, Ingredient.fromJson(ingredients.get(i)));
            }

            int processTime = GsonHelper.getAsInt(serializedRecipe, "process_time");

            return new ClayAlloyFurnaceRecipe(recipeId, output, inputs, processTime);
        }

        @Override
        public @NotNull ClayAlloyFurnaceRecipe fromNetwork(@NotNull ResourceLocation recipeId, FriendlyByteBuf buf) {
            NonNullList<Ingredient> inputs = NonNullList.withSize(buf.readInt(), Ingredient.EMPTY);
            inputs.replaceAll(ignored -> Ingredient.fromNetwork(buf));

            ItemStack output = buf.readItem();

            int processTime = buf.readInt();
            return new ClayAlloyFurnaceRecipe(recipeId, output, inputs, processTime);
        }

        @Override
        public void toNetwork(FriendlyByteBuf buf, ClayAlloyFurnaceRecipe recipe) {
            buf.writeInt(recipe.getIngredients().size());
            recipe.getIngredients().forEach(ingredient -> ingredient.toNetwork(buf));
            buf.writeItemStack(recipe.getResultItem(), false);
            buf.writeInt(recipe.getProcessTime());
        }
    }
}
