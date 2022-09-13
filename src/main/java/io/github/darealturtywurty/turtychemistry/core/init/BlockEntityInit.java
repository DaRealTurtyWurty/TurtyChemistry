package io.github.darealturtywurty.turtychemistry.core.init;

import io.github.darealturtywurty.turtychemistry.TurtyChemistry;
import io.github.darealturtywurty.turtychemistry.common.block.entity.AnvilBlockEntity;
import io.github.darealturtywurty.turtychemistry.common.block.entity.ClayAlloyFurnaceBlockEntity;
import io.github.darealturtywurty.turtylib.core.init.AbstractInit;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public final class BlockEntityInit extends AbstractInit {
    public static final DeferredRegister<BlockEntityType<?>> BLOCK_ENTITIES = DeferredRegister.create(
            ForgeRegistries.BLOCK_ENTITY_TYPES, TurtyChemistry.MODID);

    public static final RegistryObject<BlockEntityType<ClayAlloyFurnaceBlockEntity>> CLAY_ALLOY_FURNACE = BLOCK_ENTITIES.register(
            "clay_alloy_furnace",
            () -> BlockEntityType.Builder.of(ClayAlloyFurnaceBlockEntity::new, BlockInit.CLAY_ALLOY_FURNACE.get())
                    .build(null));
    public static final RegistryObject<BlockEntityType<AnvilBlockEntity>> ANVIL = BLOCK_ENTITIES.register("anvil",
            () -> BlockEntityType.Builder.of(AnvilBlockEntity::new, Blocks.ANVIL, Blocks.CHIPPED_ANVIL,
                    Blocks.DAMAGED_ANVIL).build(null));

}
