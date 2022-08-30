package io.github.darealturtywurty.turtychemistry.core.multiblocks;

import io.github.darealturtywurty.turtychemistry.TurtyChemistry;
import io.github.darealturtywurty.turtychemistry.core.init.BlockInit;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.Vec3i;
import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.pattern.BlockPattern;
import net.minecraftforge.event.level.BlockEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import org.apache.commons.lang3.tuple.Pair;
import org.jetbrains.annotations.Nullable;

@Mod.EventBusSubscriber(modid = TurtyChemistry.MODID, bus = Mod.EventBusSubscriber.Bus.FORGE)
public final class MultiblockListener {
    @SubscribeEvent
    public static void blockPlace(BlockEvent.EntityPlaceEvent event) {
        if(!(event.getEntity() instanceof Player) || event.getLevel().isClientSide())
            return;

        for(Multiblock multiblock : MultiblockRegistry.REGISTRY.get()) {
            if(!multiblock.isValid(event.getPlacedBlock())) {
                continue;
            } else {
                // A0A
                // 000
                // A0A

                BlockPattern.BlockPatternMatch match = testFind(
                        multiblock.getPatternMatcher(),
                        event.getLevel(),
                        event.getPos()
                );

                if(match == null)
                    continue;

                Pair<Vec3i, BlockState> controller = multiblock.getController();
                BlockPos controllerPosition = match.getBlock(controller.getKey().getX(), controller.getKey().getY(), controller.getKey().getZ()).getPos();

                for(int x = 0; x < multiblock.getPatternMatcher().getWidth(); x++) {
                    for(int y = 0; y < multiblock.getPatternMatcher().getHeight(); y++) {
                        for(int z = 0; z < multiblock.getPatternMatcher().getDepth(); z++) {
                            event.getLevel().setBlock(match.getBlock(x, y, z).getPos(), BlockInit.MULTIBLOCK.get().defaultBlockState(), Block.UPDATE_ALL);
                        }
                    }
                }

                event.getLevel().setBlock(controllerPosition, controller.getValue(), Block.UPDATE_ALL);

                return;
            }
        }
    }

    // TODO: Make this more efficient
    private static @Nullable BlockPattern.BlockPatternMatch testFind(BlockPattern pattern, LevelAccessor level, BlockPos pos) {
        for(int x = -pattern.getWidth(); x < pattern.getWidth(); x++) {
            for(int y = -pattern.getDepth(); y < pattern.getDepth(); y++) {
                for(int z = -pattern.getHeight(); z < pattern.getHeight(); z++) {
                    BlockPos offset = pos.offset(x, y, z);
                    BlockPattern.BlockPatternMatch found = pattern.find(level, offset);

                    if(found != null)
                        return found;
                }
            }
        }

        return null;
    }

    private static BlockPattern.BlockPatternMatch find(BlockPattern pattern, LevelAccessor level, BlockPos pos, int xLength, int yLength, int zLength ) {
        int i = Math.max(Math.max(xLength, yLength), zLength);

        for(BlockPos blockpos : BlockPos.betweenClosed(pos, pos.offset(i - 1, i - 1, i - 1))) {
            for(Direction direction : Direction.values()) {
                for(Direction direction1 : Direction.values()) {
                    if (direction1 != direction && direction1 != direction.getOpposite()) {
                        BlockPattern.BlockPatternMatch match = pattern.matches(level, blockpos, direction, direction1);
                        if (match != null) {
                            return match;
                        }
                    }
                }
            }
        }

        return null;
    }
}
