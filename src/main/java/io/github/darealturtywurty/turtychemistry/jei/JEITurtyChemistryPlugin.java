package io.github.darealturtywurty.turtychemistry.jei;

import io.github.darealturtywurty.turtychemistry.TurtyChemistry;
import io.github.darealturtywurty.turtychemistry.recipe.ClayAlloyFurnaceRecipe;
import io.github.darealturtywurty.turtychemistry.recipe.FoundryRecipe;
import mezz.jei.api.IModPlugin;
import mezz.jei.api.JeiPlugin;
import mezz.jei.api.recipe.RecipeType;
import mezz.jei.api.registration.IRecipeCategoryRegistration;
import mezz.jei.api.registration.IRecipeRegistration;
import net.minecraft.client.Minecraft;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.crafting.RecipeManager;
import org.jetbrains.annotations.NotNull;

import java.util.List;
import java.util.Objects;


@JeiPlugin
public class JEITurtyChemistryPlugin implements IModPlugin {
    public static final RecipeType<ClayAlloyFurnaceRecipe> CLAY_ALLOY_FURNACE_TYPE = new RecipeType<>(
            ClayAlloyFurnaceRecipeCategory.UID, ClayAlloyFurnaceRecipe.class);
    public static final RecipeType<FoundryRecipe> FOUNDRY_RECIPE_RECIPE_TYPE = new RecipeType<>(
            FoundryRecipeCategory.UID, FoundryRecipe.class);

    @Override
    public @NotNull ResourceLocation getPluginUid() {
        return new ResourceLocation(TurtyChemistry.MODID, "jei_plugin");
    }

    @Override
    public void registerCategories(IRecipeCategoryRegistration registration) {
        registration.addRecipeCategories(
                new ClayAlloyFurnaceRecipeCategory(registration.getJeiHelpers().getGuiHelper()));
        registration.addRecipeCategories(new FoundryRecipeCategory(registration.getJeiHelpers().getGuiHelper()));
    }

    @Override
    public void registerRecipes(IRecipeRegistration registration) {
        RecipeManager recipeManager = Objects.requireNonNull(Minecraft.getInstance().level).getRecipeManager();
        final List<ClayAlloyFurnaceRecipe> recipes = recipeManager.getAllRecipesFor(
                ClayAlloyFurnaceRecipe.Type.INSTANCE);
        registration.addRecipes(CLAY_ALLOY_FURNACE_TYPE, recipes);
        final List<FoundryRecipe> foundryRecipes = recipeManager.getAllRecipesFor(FoundryRecipe.Type.INSTANCE);
        registration.addRecipes(FOUNDRY_RECIPE_RECIPE_TYPE, foundryRecipes);
    }
}
