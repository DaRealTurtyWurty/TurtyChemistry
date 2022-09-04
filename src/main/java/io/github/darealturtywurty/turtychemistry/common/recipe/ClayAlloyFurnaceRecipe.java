package io.github.darealturtywurty.turtychemistry.common.recipe;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import io.github.darealturtywurty.turtychemistry.TurtyChemistry;
import net.minecraft.core.NonNullList;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.GsonHelper;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.*;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.Nullable;

public class ClayAlloyFurnaceRecipe implements Recipe<SimpleContainer> {
    public static final String ID = "clay_alloy"; //This can be changed to whatever

    private final ResourceLocation id;
    private final ItemStack output;
    private final NonNullList<Ingredient> recipeItems;

    public ClayAlloyFurnaceRecipe(ResourceLocation id, ItemStack output, NonNullList<Ingredient> recipeItems) {
        this.id = id;
        this.output = output;
        this.recipeItems = recipeItems;
    }

    @Override
    public NonNullList<Ingredient> getIngredients() {
        return recipeItems;
    }

    @Override
    public boolean matches(SimpleContainer container, Level level) {
        if(level.isClientSide()) {
            return false;
        }

        return recipeItems.get(0).test(container.getItem(0)) && recipeItems.get(1).test(container.getItem(1));
    }

    @Override
    public ItemStack assemble(SimpleContainer container) {
        return output;
    }

    @Override
    public boolean canCraftInDimensions(int width, int height) {
        return true;
    }

    @Override
    public ItemStack getResultItem() {
        return this.output.copy();
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

    public static class Type implements RecipeType<ClayAlloyFurnaceRecipe> {
        public static final Type INSTANCE = new Type();
        public static final String ID = ClayAlloyFurnaceRecipe.ID;
        private Type() {}
    }

    public static class Serializer implements RecipeSerializer<ClayAlloyFurnaceRecipe> {
        public static final Serializer INSTANCE = new Serializer();
        public static final ResourceLocation ID = new ResourceLocation(TurtyChemistry.MODID, ClayAlloyFurnaceRecipe.ID);

        private Serializer() {}

        @Override
        public ClayAlloyFurnaceRecipe fromJson(ResourceLocation recipeId, JsonObject serializedRecipe) {
            ItemStack output = ShapedRecipe.itemStackFromJson(GsonHelper.getAsJsonObject(serializedRecipe, "output"));

            JsonArray ingredients = GsonHelper.getAsJsonArray(serializedRecipe, "ingredients");
            NonNullList<Ingredient> inputs = NonNullList.withSize(2, Ingredient.EMPTY);

            for(int i = 0; i< inputs.size(); i++) {
                inputs.set(i, Ingredient.fromJson(ingredients.get(i)));
            }

            return new ClayAlloyFurnaceRecipe(recipeId, output, inputs);
        }

        @Override
        public @Nullable ClayAlloyFurnaceRecipe fromNetwork(ResourceLocation recipeId, FriendlyByteBuf buf) {
            NonNullList<Ingredient> inputs = NonNullList.withSize(buf.readInt(), Ingredient.EMPTY);

            for(int i = 0; i < inputs.size(); i++) {
                inputs.set(i, Ingredient.fromNetwork(buf));
            }

            ItemStack output = buf.readItem();
            return new ClayAlloyFurnaceRecipe(recipeId, output, inputs);
        }

        @Override
        public void toNetwork(FriendlyByteBuf buf, ClayAlloyFurnaceRecipe recipe) {
            buf.writeInt(recipe.getIngredients().size());

            for(Ingredient ingredient : recipe.getIngredients()) {
                ingredient.toNetwork(buf);
            }
            buf.writeItemStack(recipe.getResultItem(), false);
        }
    }
}
