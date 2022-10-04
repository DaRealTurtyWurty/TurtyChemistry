package io.github.darealturtywurty.turtychemistry.init;

import io.github.darealturtywurty.turtychemistry.TurtyChemistry;
import io.github.darealturtywurty.turtychemistry.menu.ClayAlloyFurnaceMenu;
import io.github.darealturtywurty.turtychemistry.menu.MolderMenu;
import io.github.darealturtywurty.turtylib.core.init.AbstractInit;
import net.minecraft.world.inventory.MenuType;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public final class MenuInit extends AbstractInit {
    public static final DeferredRegister<MenuType<?>> MENUS = DeferredRegister.create(ForgeRegistries.MENU_TYPES,
            TurtyChemistry.MODID);

    public static final RegistryObject<MenuType<ClayAlloyFurnaceMenu>> CLAY_ALLOY_FURNACE = MENUS
            .register("clay_alloy_furnace", () -> new MenuType<>(ClayAlloyFurnaceMenu::getClientMenu));

    // TODO: Remove this
    public static final RegistryObject<MenuType<MolderMenu>> MOLDER = MENUS
            .register("molder", () -> new MenuType<>(MolderMenu::new));
}
