package io.github.darealturtywurty.turtychemistry.core.init;

import io.github.darealturtywurty.turtychemistry.TurtyChemistry;
import io.github.darealturtywurty.turtychemistry.common.block.entity.ShaleFracturerBlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public final class BlockEntityInit extends AbstractInit {

    public static final DeferredRegister<BlockEntityType<?>> BLOCK_ENTITIES = DeferredRegister
            .create(ForgeRegistries.BLOCK_ENTITIES, TurtyChemistry.MODID);

    public static final RegistryObject<BlockEntityType<ShaleFracturerBlockEntity>> SHALE_FRACTURER = BLOCK_ENTITIES
            .register("shale_fracturer", () -> BlockEntityType.Builder
                    .of(ShaleFracturerBlockEntity::new, BlockInit.SHALE_FRACTURER.get()).build(null));

    public static final RegistryObject<BlockEntityType<ShaleFracturerBlockEntity.DrillBlockEntity>> SHALE_DRILL = BLOCK_ENTITIES
            .register("shale_drill",
                    () -> BlockEntityType.Builder
                            .of(ShaleFracturerBlockEntity.DrillBlockEntity::new, BlockInit.SHALE_DRILL.get())
                            .build(null));
}
