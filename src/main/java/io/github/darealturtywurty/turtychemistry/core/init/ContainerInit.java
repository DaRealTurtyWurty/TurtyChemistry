package io.github.darealturtywurty.turtychemistry.core.init;

import io.github.darealturtywurty.turtychemistry.TurtyChemistry;
import io.github.darealturtywurty.turtychemistry.common.block.container.RubberTreeTapContainer;
import io.github.darealturtywurty.turtylib.core.init.AbstractInit;
import net.minecraft.world.inventory.MenuType;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public final class ContainerInit extends AbstractInit {

    public static final DeferredRegister<MenuType<?>> MENU_TYPES = DeferredRegister
            .create(ForgeRegistries.MENU_TYPES, TurtyChemistry.MODID);

    public static final RegistryObject<MenuType<RubberTreeTapContainer>> RUBBER_TREE_TAP_CONTAINER = MENU_TYPES
            .register("rubber_tree_tap", () -> new MenuType<>(RubberTreeTapContainer::new));

}
