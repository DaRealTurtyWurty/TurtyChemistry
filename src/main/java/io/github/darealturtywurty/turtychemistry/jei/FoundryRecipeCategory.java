package io.github.darealturtywurty.turtychemistry.jei;

import io.github.darealturtywurty.turtychemistry.TurtyChemistry;
import io.github.darealturtywurty.turtychemistry.client.util.screens.ClayAlloyFurnaceScreen;
import io.github.darealturtywurty.turtychemistry.init.BlockInit;
import io.github.darealturtywurty.turtychemistry.recipe.FoundryRecipie;
import mezz.jei.api.constants.VanillaTypes;
import mezz.jei.api.gui.builder.IRecipeLayoutBuilder;
import mezz.jei.api.gui.drawable.IDrawable;
import mezz.jei.api.helpers.IGuiHelper;
import mezz.jei.api.recipe.IFocusGroup;
import mezz.jei.api.recipe.RecipeIngredientRole;
import mezz.jei.api.recipe.RecipeType;
import mezz.jei.api.recipe.category.IRecipeCategory;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import org.jetbrains.annotations.NotNull;

public final class FoundryRecipeCategory implements IRecipeCategory<FoundryRecipie> {
    public static final ResourceLocation UID = new ResourceLocation(TurtyChemistry.MODID, FoundryRecipie.ID);
    private final IDrawable background;
    private final IDrawable icon;

    public FoundryRecipeCategory(final IGuiHelper helper) {
        this.background = helper.createDrawable(ClayAlloyFurnaceScreen.TEXTURE, 0, 0, 176, 83);

        // TODO: Make this use a custom renderer
        this.icon = helper.createDrawableIngredient(VanillaTypes.ITEM_STACK,
                new ItemStack(BlockInit.FOUNDRY_BLOCK.get()));
    }

    @Override
    public @NotNull RecipeType<FoundryRecipie> getRecipeType() {
        return JEITurtyChemistryPlugin.FOUNDRY_RECIPE_RECIPE_TYPE;
    }

    @Override
    public @NotNull Component getTitle() {
        return Component.literal("Foundry");
    }

    @Override
    public @NotNull IDrawable getBackground() {
        return this.background;
    }

    @Override
    public @NotNull IDrawable getIcon() {
        return this.icon;
    }

    @Override
    public void setRecipe(final IRecipeLayoutBuilder builder,final FoundryRecipie recipe,final @NotNull IFocusGroup focuses) {
        builder.addSlot(RecipeIngredientRole.INPUT, 55, 17).addIngredients(recipe.getIngredients().get(0));
    }
}
