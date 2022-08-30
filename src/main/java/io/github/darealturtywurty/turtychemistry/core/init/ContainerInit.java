package io.github.darealturtywurty.turtychemistry.core.init;

import io.github.darealturtywurty.turtychemistry.TurtyChemistry;
import io.github.darealturtywurty.turtylib.core.init.AbstractInit;
import net.minecraft.world.inventory.MenuType;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public final class ContainerInit extends AbstractInit {

    public static final DeferredRegister<MenuType<?>> MENU_TYPES = DeferredRegister
            .create(ForgeRegistries.MENU_TYPES, TurtyChemistry.MODID);
}
