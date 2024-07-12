package wiiu.mavity.who.util;

import me.emafire003.dev.structureplacerapi.StructurePlacerAPI;

import net.minecraft.entity.Entity;
import net.minecraft.registry.*;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.*;
import net.minecraft.world.World;

import org.jetbrains.annotations.NotNull;

import wiiu.mavity.who.Who;
import wiiu.mavity.who.entity.WhoEntities;
import wiiu.mavity.who.entity.entitytype.TimeRotorEntity;

public class DimensionalUtil {

    public static void changePlayerEntityDimension(@NotNull ServerPlayerEntity serverPlayer, String modId, String dimensionName, int x, int z) {

        RegistryKey<World> targetDimension = RegistryKey.of(RegistryKeys.WORLD, new Identifier(modId, dimensionName));
        ServerWorld targetServerWorld = serverPlayer.getServer().getWorld(targetDimension);
        serverPlayer.teleport(targetServerWorld, x + 0.5, 64, z + 0.5, serverPlayer.getYaw(), serverPlayer.getPitch());
        Who.LOGGER.info("Changed player dimension.");

    }

    public static void changePlayerEntityDimensionAndCreateTardisInterior(@NotNull ServerPlayerEntity serverPlayer, String modId, String dimensionName, int x, int z) {

        RegistryKey<World> targetDimension = RegistryKey.of(RegistryKeys.WORLD, new Identifier(modId, dimensionName));
        ServerWorld targetServerWorld = serverPlayer.getServer().getWorld(targetDimension);
        serverPlayer.teleport(targetServerWorld, x + 0.5, 64, z + 0.5, serverPlayer.getYaw(), serverPlayer.getPitch());
        DimensionalUtil.createTardisInterior(targetServerWorld, x, z);
        Who.LOGGER.info("Changed player dimension and created tardis interior.");

    }

    public static void createTardisInterior(@NotNull ServerWorld world, int x, int z) {

        BlockPos pos = new BlockPos(x - 13, 63 - 14, z - 7);
        if (!world.isClient()) {

            StructurePlacerAPI placer = new StructurePlacerAPI(world, new Identifier(Who.MOD_ID, "tardis_interior_one"), pos);
            placer.loadStructure();
            Vec3d timeRotorPos = new BlockPos(x, 61, z + 6).toCenterPos();
            TimeRotorEntity timeRotor = WhoEntities.TIME_ROTOR.create(world);
            timeRotor.teleport(timeRotorPos.x, timeRotorPos.y - 0.5, timeRotorPos.z);
            world.spawnEntity(timeRotor);

        }

    }

    public static void changeEntityDimension(@NotNull Entity entity, String modId, String dimensionName) {

        RegistryKey<World> targetDimension = RegistryKey.of(RegistryKeys.WORLD, new Identifier(modId, dimensionName));
        ServerWorld targetServerWorld = entity.getServer().getWorld(targetDimension);
        entity.moveToWorld(targetServerWorld);
        Who.LOGGER.info("Changed entity dimension.");

    }
}