package wiiu.mavity.who.entity.entitytype;

import net.minecraft.entity.*;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.util.*;
import net.minecraft.world.World;

import wiiu.mavity.who.Who;
import wiiu.mavity.who.util.DimensionalUtil;

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
        if (player instanceof ServerPlayerEntity serverPlayer) {

            DimensionalUtil.changePlayerEntityDimension(serverPlayer, Who.MOD_ID, "tardis_dim");
            return ActionResult.SUCCESS;

        } else {

            return ActionResult.FAIL;

        }
    }
}