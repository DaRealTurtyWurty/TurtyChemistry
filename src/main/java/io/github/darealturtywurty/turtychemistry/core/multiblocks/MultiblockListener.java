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
import org.apache.logging.log4j.Level;
import org.jetbrains.annotations.Nullable;

@Mod.EventBusSubscriber(modid = TurtyChemistry.MODID, bus = Mod.EventBusSubscriber.Bus.FORGE)
public final class MultiblockListener {
    @SubscribeEvent
    public static void blockPlace(BlockEvent.EntityPlaceEvent event) {
        //cache the event caller's Level and Block Position

        if(!(event.getEntity() instanceof Player) || event.getLevel().isClientSide())
            return;
        final LevelAccessor currentEventLevel = event.getLevel();
        final BlockPos currentEventPosition = event.getPos();
        for(Multiblock multiblock : MultiblockRegistry.REGISTRY.get()) {
            if(multiblock.isValid(event.getPlacedBlock())) {

                // A0A
                // 000
                // A0A

                final BlockPattern.BlockPatternMatch match = testFind(
                        multiblock.getPatternMatcher(),
                        currentEventLevel,
                        currentEventPosition
                );

                if(match == null)
                    continue;

                final Pair<Vec3i, BlockState> controller = multiblock.getController();
                final BlockPos controllerPosition = match.getBlock(controller.getKey().getX(), controller.getKey().getY(), controller.getKey().getZ()).getPos();


                //cache the width, height, and depth of the multiblock's pattern so it doesn't have to call it every cycle of the loop
                final int multiBlockWidth = multiblock.getPatternMatcher().getWidth();
                final int multiBlockHeight = multiblock.getPatternMatcher().getHeight();
                final int multiBlockDepth = multiblock.getPatternMatcher().getDepth();
                for(int x = 0; x < multiBlockWidth; x++) {
                    for(int y = 0; y < multiBlockHeight; y++) {
                        for(int z = 0; z < multiBlockDepth; z++) {
                            currentEventLevel.setBlock(match.getBlock(x, y, z).getPos(), BlockInit.MULTIBLOCK.get().defaultBlockState(), Block.UPDATE_ALL);

                        }
                    }
                }

                currentEventLevel.setBlock(controllerPosition, controller.getValue(), Block.UPDATE_ALL);

                return;
            }
        }
    }

    // TODO: Make this more efficient
    private static @Nullable BlockPattern.BlockPatternMatch testFind(BlockPattern pattern, LevelAccessor level, BlockPos pos) {
        //cache the width, height, and depth of the multiblock's pattern so it doesn't have to call it every cycle of the loop
        final int patternWidth = pattern.getWidth();
        final int patternDepth = pattern.getDepth();
        final int patternHeight = pattern.getHeight();


        for(int x = -patternWidth; x < patternWidth; x++) {
            for(int y = -patternDepth; y < patternDepth; y++) {
                for(int z = -patternHeight; z < patternHeight; z++) {
                    final BlockPos offset = pos.offset(x, y, z);
                    final BlockPattern.BlockPatternMatch found = pattern.find(level, offset);

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
