package io.github.darealturtywurty.turtychemistry.init;

import io.github.darealturtywurty.turtychemistry.TurtyChemistry;
import io.github.darealturtywurty.turtychemistry.recipe.ClayAlloyFurnaceRecipe;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public final class RecipeInit {
    public static final DeferredRegister<RecipeSerializer<?>> SERIALIZERS = DeferredRegister
            .create(ForgeRegistries.RECIPE_SERIALIZERS, TurtyChemistry.MODID);

    public static final RegistryObject<RecipeSerializer<ClayAlloyFurnaceRecipe>> CLAY_ALLOY_FURNACE_SERIALIZER =
            SERIALIZERS.register(ClayAlloyFurnaceRecipe.ID, () -> ClayAlloyFurnaceRecipe.Serializer.INSTANCE);
}
