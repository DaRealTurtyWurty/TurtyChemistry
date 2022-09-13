package io.github.darealturtywurty.turtychemistry.core.mixin;

import io.github.darealturtywurty.turtychemistry.common.TurtyTags;
import io.github.darealturtywurty.turtychemistry.common.block.entity.AlternateAnvilMechanicBlockEntity;
import io.github.darealturtywurty.turtychemistry.core.init.BlockEntityInit;
import net.minecraft.core.BlockPos;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.AnvilBlock;
import net.minecraft.world.level.block.EntityBlock;
import net.minecraft.world.level.block.FallingBlock;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.FluidState;
import net.minecraft.world.phys.BlockHitResult;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;


public final class MixinHooks {


    @Mixin(AnvilBlock.class)
    public static final class AnvilMixin extends FallingBlock implements EntityBlock {

        public AnvilMixin(Properties pProperties) {
            super(pProperties);
        }

        @Override
        public boolean onDestroyedByPlayer(BlockState state, Level level, BlockPos pos, Player player, boolean willHarvest, FluidState fluid) {

            if (level.getBlockEntity(pos) instanceof AlternateAnvilMechanicBlockEntity anvilMechanicBlock) {
                level.addFreshEntity(new ItemEntity(level, pos.getX(), pos.getY(), pos.getZ(), anvilMechanicBlock.getItem()));
            }
            return super.onDestroyedByPlayer(state, level, pos, player, willHarvest, fluid);
        }

        @Inject(method = "use", at = @At("HEAD"), remap = false, cancellable = true)
        public void addIngotToAnvil(BlockState pState, Level pLevel, BlockPos pPos, Player pPlayer, InteractionHand pHand, BlockHitResult pHit, CallbackInfoReturnable<InteractionResult> cir) {
            final Inventory playerInventory = pPlayer.getInventory();
            if (pPlayer.isCrouching() && pLevel.getBlockEntity(pPos) instanceof AlternateAnvilMechanicBlockEntity alternateAnvil) {

                if (alternateAnvil.getItem().isEmpty()) {
                    for (ItemStack stack : playerInventory.items) {
                        if ((stack.is(Items.IRON_INGOT) || stack.is(TurtyTags.TURTY_ITEM_TAG_KEY))) {
                            alternateAnvil.setStackInSlot(stack.split(1));
                            alternateAnvil.setChanged();
                            cir.setReturnValue(InteractionResult.CONSUME);
                            break;
                        }

                    }

                } else {

                    pPlayer.setItemInHand(pHand, alternateAnvil.inventoryModule.getCapability().extractItem(0, 1, false));
                    cir.setReturnValue(InteractionResult.CONSUME);
                }


            }
        }


        @Nullable
        @Override
        public BlockEntity newBlockEntity(@NotNull BlockPos pPos, @NotNull BlockState pState) {
            return BlockEntityInit.ALTERNATE_ANVIL.get().create(pPos, pState);
        }
    }
}
