package io.github.darealturtywurty.turtychemistry.core.init;

import io.github.darealturtywurty.turtychemistry.TurtyChemistry;
import io.github.darealturtywurty.turtychemistry.common.block.entity.ClayAlloyFurnaceBlockEntity;
import io.github.darealturtywurty.turtylib.core.init.AbstractInit;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public final class BlockEntityInit extends AbstractInit {
    public static final DeferredRegister<BlockEntityType<?>> BLOCK_ENTITIES = DeferredRegister
        .create(ForgeRegistries.BLOCK_ENTITY_TYPES, TurtyChemistry.MODID);

    public static final RegistryObject<BlockEntityType<ClayAlloyFurnaceBlockEntity>> CLAY_ALLOY_FURNACE = registerNewBlockEntity("clay_allow_furnace",ClayAlloyFurnaceBlockEntity::new,BlockInit.CLAY_ALLOY_FURNACE.get());



    private static <T extends BlockEntity>RegistryObject<BlockEntityType<T>> registerNewBlockEntity(final String name, final BlockEntityType.BlockEntitySupplier<T> blockEntitySupplier, final Block block)
    {
        return BLOCK_ENTITIES.register(name,() -> BlockEntityType.Builder.of(blockEntitySupplier,block).build(null));
    }
}
