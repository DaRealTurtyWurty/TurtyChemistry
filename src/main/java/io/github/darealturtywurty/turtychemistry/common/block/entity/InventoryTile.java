package io.github.darealturtywurty.turtychemistry.common.block.entity;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.ListTag;
import net.minecraft.network.Connection;
import net.minecraft.network.protocol.game.ClientboundBlockEntityDataPacket;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityTicker;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.IItemHandlerModifiable;
import net.minecraftforge.items.ItemStackHandler;

public class InventoryTile extends BlockEntity implements BlockEntityTicker {

    protected final int size;
    protected int timer;
    protected boolean requiresUpdate;

    protected final IItemHandlerModifiable inventory;
    protected LazyOptional<IItemHandlerModifiable> handler;

    public InventoryTile(BlockEntityType<?> type, BlockPos pos, BlockState state, int size) {
        super(type, pos, state);
        this.size = size;
        this.inventory = createInventory();
        this.handler = LazyOptional.of(() -> this.inventory);
    }

    public IItemHandlerModifiable createInventory() {
        return new ItemStackHandler(this.size) {
            @Override
            public ItemStack extractItem(int slot, int amount, boolean simulate) {
                InventoryTile.this.update();
                return super.extractItem(slot, amount, simulate);
            }

            @Override
            public ItemStack insertItem(int slot, ItemStack stack, boolean simulate) {
                InventoryTile.this.update();
                return super.insertItem(slot, stack, simulate);
            }
        };
    }

    public ItemStack extractItem(int slot) {
        final int count = getItemInSlot(slot).getCount();
        this.requiresUpdate = true;
        return this.handler.map(inv -> inv.extractItem(slot, count, false)).orElse(ItemStack.EMPTY);
    }

    @Override
    public <T> LazyOptional<T> getCapability(Capability<T> cap, Direction side) {
        return cap == CapabilityItemHandler.ITEM_HANDLER_CAPABILITY ? this.handler.cast()
                : super.getCapability(cap, side);
    }

    public LazyOptional<IItemHandlerModifiable> getHandler() {
        return this.handler;
    }

    public IItemHandlerModifiable getInventory() {
        return this.inventory;
    }

    public ItemStack getItemInSlot(int index) {
        return this.handler.map(inv -> inv.getStackInSlot(index)).orElse(ItemStack.EMPTY);
    }

    public int getSize() {
        return this.size;
    }

    @Override
    public ClientboundBlockEntityDataPacket getUpdatePacket() {
        return ClientboundBlockEntityDataPacket.create(this);
    }

    @Override
    public CompoundTag getUpdateTag() {
        return save(new CompoundTag());
    }

    @Override
    public void handleUpdateTag(CompoundTag tag) {
        super.handleUpdateTag(tag);
        load(tag);
    }

    public ItemStack insertItem(int slot, ItemStack stack) {
        final ItemStack copy = stack.copy();
        stack.shrink(copy.getCount());
        this.requiresUpdate = true;
        return this.handler.map(inv -> inv.insertItem(slot, copy, false)).orElse(ItemStack.EMPTY);
    }

    @Override
    public void load(CompoundTag compound) {
        super.load(compound);
        final ListTag list = compound.getList("Items", 10);
        for (int x = 0; x < list.size(); ++x) {
            final CompoundTag nbt = list.getCompound(x);
            final int r = nbt.getByte("Slot") & 255;
            this.handler.ifPresent(inv -> {
                final int invslots = inv.getSlots();
                if (r >= 0 && r < invslots) {
                    inv.setStackInSlot(r, ItemStack.of(nbt));
                }
            });
        }
    }

    @Override
    public void onDataPacket(Connection net, ClientboundBlockEntityDataPacket pkt) {
        super.onDataPacket(net, pkt);
        handleUpdateTag(pkt.getTag());
    }

    @Override
    public CompoundTag save(CompoundTag compound) {
        super.save(compound);
        final var list = new ListTag();
        final int slots = this.inventory.getSlots();
        for (int x = 0; x < slots; ++x) {
            final ItemStack stack = this.inventory.getStackInSlot(x);
            final var nbt = new CompoundTag();
            nbt.putByte("Slot", (byte) x);
            stack.save(nbt);
            list.add(nbt);
        }

        compound.put("Items", list);
        return compound;
    }

    @Override
    public void tick(Level level, BlockPos pos, BlockState state, BlockEntity blockEntity) {
        this.timer++;
        if (this.level != null && this.requiresUpdate) {
            update();
        }
    }

    public void update() {
        requestModelDataUpdate();
        this.setChanged();
        if (this.level != null) {
            this.level.setBlockAndUpdate(this.worldPosition, getBlockState());
        }
    }
}
