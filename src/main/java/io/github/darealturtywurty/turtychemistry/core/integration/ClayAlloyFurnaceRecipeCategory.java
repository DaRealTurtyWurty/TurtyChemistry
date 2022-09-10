package io.github.darealturtywurty.turtychemistry.core.integration;

import com.mojang.blaze3d.vertex.PoseStack;
import io.github.darealturtywurty.turtychemistry.TurtyChemistry;
import io.github.darealturtywurty.turtychemistry.client.screens.ClayAlloyFurnaceScreen;
import io.github.darealturtywurty.turtychemistry.common.recipe.ClayAlloyFurnaceRecipe;
import io.github.darealturtywurty.turtychemistry.core.init.BlockInit;
import mezz.jei.api.constants.VanillaTypes;
import mezz.jei.api.gui.builder.IRecipeLayoutBuilder;
import mezz.jei.api.gui.drawable.IDrawable;
import mezz.jei.api.gui.drawable.IDrawableAnimated;
import mezz.jei.api.gui.ingredient.IRecipeSlotsView;
import mezz.jei.api.helpers.IGuiHelper;
import mezz.jei.api.recipe.IFocusGroup;
import mezz.jei.api.recipe.RecipeIngredientRole;
import mezz.jei.api.recipe.RecipeType;
import mezz.jei.api.recipe.category.IRecipeCategory;
import mezz.jei.api.recipe.vanilla.IJeiFuelingRecipe;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Font;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;

import java.text.NumberFormat;

public class ClayAlloyFurnaceRecipeCategory implements IRecipeCategory<ClayAlloyFurnaceRecipe> {
    public static final ResourceLocation UID = new ResourceLocation(TurtyChemistry.MODID, ClayAlloyFurnaceRecipe.ID);

    private final IDrawable background;
    private final IDrawable icon;

    public ClayAlloyFurnaceRecipeCategory(IGuiHelper helper) {
        this.background = helper.createDrawable(ClayAlloyFurnaceScreen.TEXTURE, 0, 0, 176, 83);
        this.icon = helper.createDrawableIngredient(VanillaTypes.ITEM_STACK, new ItemStack(BlockInit.CLAY_ALLOY_FURNACE.get()));
    }

    @Override
    public RecipeType<ClayAlloyFurnaceRecipe> getRecipeType() {
        return JEITurtyChemistryPlugin.CLAY_ALLOY_FURNACE_TYPE;
    }

    @Override
    public Component getTitle() {
        return Component.literal("Clay Alloy Furnace");
    }

    @Override
    public IDrawable getBackground() {
        return this.background;
    }

    @Override
    public IDrawable getIcon() {
        return this.icon;
    }

    @Override
    public void setRecipe(IRecipeLayoutBuilder builder, ClayAlloyFurnaceRecipe recipe, IFocusGroup focuses) {
        builder.addSlot(RecipeIngredientRole.INPUT, 42, 17).addIngredients(recipe.getIngredients().get(0));
        builder.addSlot(RecipeIngredientRole.INPUT, 69, 17).addIngredients(recipe.getIngredients().get(1));
        builder.addSlot(RecipeIngredientRole.OUTPUT, 116, 35).addItemStack(recipe.getResultItem());

    }
}
