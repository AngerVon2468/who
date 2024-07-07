package wiiu.mavity.who.util;

import net.minecraft.block.*;
import net.minecraft.entity.Entity;
import net.minecraft.registry.*;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import org.jetbrains.annotations.NotNull;

import wiiu.mavity.who.Who;

public class DimensionalUtil {

    public static void changePlayerEntityDimension(@NotNull ServerPlayerEntity serverPlayer, String modId, String dimensionName) {

        RegistryKey<World> targetDimension = RegistryKey.of(RegistryKeys.WORLD, new Identifier(modId, dimensionName));
        ServerWorld targetServerWorld = serverPlayer.getServer().getWorld(targetDimension);
        serverPlayer.teleport(targetServerWorld, 0, 100, 0, serverPlayer.getYaw(), serverPlayer.getPitch());
        Who.LOGGER.info("Changed player dimension.");

    }

    public static void changePlayerEntityDimensionAndCreateTardisInterior(@NotNull ServerPlayerEntity serverPlayer, String modId, String dimensionName) {

        RegistryKey<World> targetDimension = RegistryKey.of(RegistryKeys.WORLD, new Identifier(modId, dimensionName));
        ServerWorld targetServerWorld = serverPlayer.getServer().getWorld(targetDimension);
        serverPlayer.teleport(targetServerWorld, 0.5, 64, 0.5, serverPlayer.getYaw(), serverPlayer.getPitch());
        DimensionalUtil.createTardisInterior(targetServerWorld);
        Who.LOGGER.info("Changed player dimension and created tardis interior.");

    }

    public static void createTardisInterior(ServerWorld world) {

        BlockPos initialPos = new BlockPos(0, 63, 0);
        BlockState initialState = world.getBlockState(initialPos);
        if (initialState.isOf(Blocks.AIR)) {

            world.setBlockState(initialPos, Blocks.COBBLESTONE.getDefaultState());

        }

    }

    public static void changeEntityDimension(@NotNull Entity entity, String modId, String dimensionName) {

        RegistryKey<World> targetDimension = RegistryKey.of(RegistryKeys.WORLD, new Identifier(modId, dimensionName));
        ServerWorld targetServerWorld = entity.getServer().getWorld(targetDimension);
        entity.moveToWorld(targetServerWorld);
        Who.LOGGER.info("Changed entity dimension.");

    }
}