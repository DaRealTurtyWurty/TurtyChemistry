package io.github.darealturtywurty.turtychemistry.common;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.tags.FluidTags;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MoverType;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.network.NetworkHooks;

public class FloatWaterItemEntity extends Entity {
    private static final EntityDataAccessor<ItemStack> DATA_ITEM = SynchedEntityData.defineId(ItemEntity.class,
            EntityDataSerializers.ITEM_STACK);
    protected short age = 0;
    
    public final int lifespan;
    
    public FloatWaterItemEntity(Builder builder) {
        super(builder.type, builder.level);
        setItem(builder.stack);
        setPos(builder.pos);
        this.lifespan = builder.lifespan;
    }

    @Override
    public boolean equals(Object obj) {
        return new EqualsBuilder().appendSuper(super.equals(obj)).append(obj != null, true)
                .append(obj.getClass() == this.getClass(), true)
                .append(this.lifespan, ((FloatWaterItemEntity) obj).lifespan).build();
    }
    
    @Override
    public Packet<?> getAddEntityPacket() {
        return NetworkHooks.getEntitySpawningPacket(this);
    }

    public ItemStack getItem() {
        return getEntityData().get(DATA_ITEM);
    }
    
    @Override
    public int hashCode() {
        return new HashCodeBuilder().append(this.lifespan).build();
    }
    
    @Override
    public void onSyncedDataUpdated(EntityDataAccessor<?> data) {
        super.onSyncedDataUpdated(data);
        if (DATA_ITEM.equals(data)) {
            getItem().setEntityRepresentation(this);
        }
    }
    
    public void setItem(ItemStack stack) {
        getEntityData().set(DATA_ITEM, stack);
    }
    
    @Override
    public void tick() {
        if (getItem().isEmpty() || !isInWater()) {
            discard();
        } else {
            super.tick();

            this.xo = this.getX();
            this.yo = this.getY();
            this.zo = this.getZ();
            final Vec3 vec3 = getDeltaMovement();
            final float eyeY = this.getEyeHeight() - 0.11111111F;
            if (isInWater() && getFluidHeight(FluidTags.WATER) > eyeY) {
                setMovement();
            }

            if (this.level.isClientSide) {
                this.noPhysics = false;
            } else {
                this.noPhysics = !this.level.noCollision(this, getBoundingBox().deflate(1.0E-7D));
                if (this.noPhysics) {
                    moveTowardsClosestSpace(this.getX(), (getBoundingBox().minY + getBoundingBox().maxY) / 2.0D,
                            this.getZ());
                }
            }

            if (getDeltaMovement().horizontalDistanceSqr() > 1.0E-5F || (this.tickCount + getId()) % 4 == 0) {
                move(MoverType.SELF, getDeltaMovement());

                this.setDeltaMovement(getDeltaMovement().multiply(0.98, 0.98, 0.98));
            }

            if (this.age != Short.MAX_VALUE) {
                ++this.age;
            }

            this.hasImpulse |= updateInWaterStateAndDoFluidPushing();
            if (!this.level.isClientSide) {
                final double d0 = getDeltaMovement().subtract(vec3).lengthSqr();
                if (d0 > 0.01D) {
                    this.hasImpulse = true;
                }
            }

            final ItemStack item = getItem();
            if (!this.level.isClientSide && this.age >= this.lifespan) {
                discard();
            }

            if (item.isEmpty()) {
                discard();
            }
        }
    }
    
    @Override
    protected void addAdditionalSaveData(CompoundTag nbt) {

    }

    @Override
    protected void defineSynchedData() {
        getEntityData().define(DATA_ITEM, ItemStack.EMPTY);
    }
    
    @Override
    protected Entity.MovementEmission getMovementEmission() {
        return Entity.MovementEmission.NONE;
    }
    
    @Override
    protected void readAdditionalSaveData(CompoundTag nbt) {
    }

    private void setMovement() {
        
    }

    public static class Builder {
        private final EntityType<?> type;
        private final Level level;
        private final ItemStack stack;
        private Vec3 pos = Vec3.ZERO;
        private int lifespan = 10000;
        
        public Builder(EntityType<?> type, Level level, ItemStack stack) {
            this.type = type;
            this.level = level;
            this.stack = stack;
        }
        
        public Builder setLifespan(int lifespan) {
            this.lifespan = lifespan;
            return this;
        }
        
        public Builder setPos(BlockPos pos) {
            return setPos(pos.getX(), pos.getY(), pos.getZ());
        }
        
        public Builder setPos(double x, double y, double z) {
            return setPos(new Vec3(x, y, z));
        }

        public Builder setPos(Vec3 pos) {
            this.pos = pos;
            return this;
        }
    }
}
