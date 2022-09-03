package io.github.darealturtywurty.turtychemistry.core.init;

import io.github.darealturtywurty.turtychemistry.TurtyChemistry;
import io.github.darealturtywurty.turtychemistry.common.block.entity.ClayAlloyFurnaceBlockEntity;
import io.github.darealturtywurty.turtychemistry.common.block.entity.RubberTreeTapBlockEntity;
import io.github.darealturtywurty.turtylib.core.init.AbstractInit;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public final class BlockEntityInit extends AbstractInit {
    public static final DeferredRegister<BlockEntityType<?>> BLOCK_ENTITIES = DeferredRegister
            .create(ForgeRegistries.BLOCK_ENTITY_TYPES, TurtyChemistry.MODID);

    public static final RegistryObject<BlockEntityType<ClayAlloyFurnaceBlockEntity>> CLAY_ALLOY_FURNACE = BLOCK_ENTITIES.register("clay_alloy_furnace",
            () -> BlockEntityType.Builder.of(ClayAlloyFurnaceBlockEntity::new, BlockInit.CLAY_ALLOY_FURNACE.get()).build(null));

    public static final RegistryObject<BlockEntityType<RubberTreeTapBlockEntity>> RUBBER_TREE_TAP = BLOCK_ENTITIES.register("rubber_tree_tap",
            () -> BlockEntityType.Builder.of(RubberTreeTapBlockEntity::new, BlockInit.RUBBER_TREE_TAP.get()).build(null));
}
