package wiiu.mavity.who.entity.entitytype;

import net.minecraft.entity.*;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.registry.*;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.*;
import net.minecraft.world.World;

import wiiu.mavity.who.Who;

public class TardisEntity extends Entity {

    public TardisEntity(EntityType<?> type, World world) {
        super(type, world);
    }

    @Override
    protected void initDataTracker() {

    }

    @Override
    protected void readCustomDataFromNbt(NbtCompound nbt) {

    }

    @Override
    protected void writeCustomDataToNbt(NbtCompound nbt) {

    }

    @Override
    public boolean canHit() {
        return !this.isRemoved();
    }

    @Override
    public boolean isCollidable() {
        return true;
    }

    @Override
    public ActionResult interact(PlayerEntity player, Hand hand) {
        if (player instanceof ServerPlayerEntity playerEntity) {

            RegistryKey<World> registryKey = RegistryKey.of(RegistryKeys.WORLD, new Identifier(Who.MOD_ID, "tardis_dim"));
            ServerWorld serverWorld = player.getServer().getWorld(registryKey);
            playerEntity.teleport(serverWorld, 0, 100, 0, player.getYaw(), player.getPitch());
            return ActionResult.SUCCESS;

        } else {

            return ActionResult.FAIL;

        }
    }
}