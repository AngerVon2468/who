package wiiu.mavity.who.util;

import net.minecraft.entity.Entity;
import net.minecraft.registry.*;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.Identifier;
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

    public static void changeEntityDimension(@NotNull Entity entity, String modId, String dimensionName) {

        RegistryKey<World> targetDimension = RegistryKey.of(RegistryKeys.WORLD, new Identifier(modId, dimensionName));
        ServerWorld targetServerWorld = entity.getServer().getWorld(targetDimension);
        entity.moveToWorld(targetServerWorld);
        Who.LOGGER.info("Changed entity dimension.");

    }
}