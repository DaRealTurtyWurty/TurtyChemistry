package io.github.darealturtywurty.turtychemistry.jei;

import io.github.darealturtywurty.turtychemistry.TurtyChemistry;
import io.github.darealturtywurty.turtychemistry.client.util.screens.ClayAlloyFurnaceScreen;
import io.github.darealturtywurty.turtychemistry.init.BlockInit;
import io.github.darealturtywurty.turtychemistry.recipe.ClayAlloyFurnaceRecipe;
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

public class ClayAlloyFurnaceRecipeCategory implements IRecipeCategory<ClayAlloyFurnaceRecipe> {
    public static final ResourceLocation UID = new ResourceLocation(TurtyChemistry.MODID, ClayAlloyFurnaceRecipe.ID);

    private final IDrawable background;
    private final IDrawable icon;

    public ClayAlloyFurnaceRecipeCategory(IGuiHelper helper) {
        this.background = helper.createDrawable(ClayAlloyFurnaceScreen.TEXTURE, 0, 0, 176, 83);

        // TODO: Make this use a custom renderer
        this.icon = helper.createDrawableIngredient(VanillaTypes.ITEM_STACK,
                new ItemStack(BlockInit.CLAY_ALLOY_FURNACE.get()));
    }

    @Override
    public @NotNull RecipeType<ClayAlloyFurnaceRecipe> getRecipeType() {
        return JEITurtyChemistryPlugin.CLAY_ALLOY_FURNACE_TYPE;
    }

    @Override
    public @NotNull Component getTitle() {
        return Component.literal("Clay Alloy Furnace");
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
    public void setRecipe(IRecipeLayoutBuilder builder, ClayAlloyFurnaceRecipe recipe, IFocusGroup focuses) {
        builder.addSlot(RecipeIngredientRole.INPUT, 42, 17).addIngredients(recipe.getIngredients().get(0));
        builder.addSlot(RecipeIngredientRole.INPUT, 69, 17).addIngredients(recipe.getIngredients().get(1));
        builder.addSlot(RecipeIngredientRole.OUTPUT, 116, 35).addItemStack(recipe.getResultItem());
    }
}
