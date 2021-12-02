package io.github.darealturtywurty.turtychemistry;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import io.github.darealturtywurty.turtychemistry.core.init.BlockEntityInit;
import io.github.darealturtywurty.turtychemistry.core.init.BlockInit;
import io.github.darealturtywurty.turtychemistry.core.init.ContainerInit;
import io.github.darealturtywurty.turtychemistry.core.init.ItemInit;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

@Mod(TurtyChemistry.MODID)
public class TurtyChemistry {
    public static final String MODID = "turtychemistry";
    public static final Logger LOGGER = LogManager.getLogger();

    public static final CreativeModeTab TAB = new CreativeModeTab(MODID) {
        @Override
        public ItemStack makeIcon() {
            return ItemInit.THULIUM.get().getDefaultInstance();
        }
    };

    public TurtyChemistry() {
        final var bus = FMLJavaModLoadingContext.get().getModEventBus();
        BlockInit.BLOCKS.register(bus);
        ItemInit.ITEMS.register(bus);
        BlockEntityInit.BLOCK_ENTITIES.register(bus);
        ContainerInit.CONTAINERS.register(bus);
    }
}
