package io.github.darealturtywurty.turtychemistry.common.block.entity;

import io.github.darealturtywurty.turtychemistry.TurtyChemistry;
import io.github.darealturtywurty.turtychemistry.core.init.BlockEntityInit;
import io.github.darealturtywurty.turtychemistry.core.network.PacketHandler;
import io.github.darealturtywurty.turtychemistry.core.network.rubbertreenetworking.ServerBoundUpdateRubberTreeTapPacket;
import io.github.darealturtywurty.turtylib.common.blockentity.ModularBlockEntity;
import io.github.darealturtywurty.turtylib.common.blockentity.module.InventoryModule;
import net.minecraft.core.BlockPos;
import net.minecraft.network.protocol.game.ClientboundBlockEntityDataPacket;
import net.minecraft.world.level.block.state.BlockState;
import org.jetbrains.annotations.NotNull;

public final class RubberTreeTapBlockEntity extends ModularBlockEntity {
    public final InventoryModule inventoryModule;
    public boolean isTapping;

    public RubberTreeTapBlockEntity(BlockPos pos, BlockState state) {
        super(BlockEntityInit.RUBBER_TREE_TAP.get(), pos, state);
        this.inventoryModule = addModule(new InventoryModule(this, 27));
    }

    @Override
    public @NotNull ClientboundBlockEntityDataPacket getUpdatePacket() {
        return ClientboundBlockEntityDataPacket.create(this);
    }

    public void setTapping() {
        TurtyChemistry.LOGGER.info("pressed");
        if (!this.isTapping) {

            PacketHandler.CHANNEL.sendToServer(new ServerBoundUpdateRubberTreeTapPacket(this.getBlockPos(), true));
        } else {
            PacketHandler.CHANNEL.sendToServer(new ServerBoundUpdateRubberTreeTapPacket(this.getBlockPos(), false));
        }
    }

}
