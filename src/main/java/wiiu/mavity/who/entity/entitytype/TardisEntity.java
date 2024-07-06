package wiiu.mavity.who.entity.entitytype;

import net.minecraft.entity.*;
import net.minecraft.entity.data.*;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.util.*;
import net.minecraft.world.World;

import org.jetbrains.annotations.NotNull;

import wiiu.mavity.who.Who;
import wiiu.mavity.who.item.WhoItems;
import wiiu.mavity.who.util.DimensionalUtil;

import java.util.*;

public class TardisEntity extends Entity {

    public static List<TardisEntity> nearbyTardises = new ArrayList<>();

    private static final TrackedData<Integer> TARDIS_ID = DataTracker.registerData(TardisEntity.class, TrackedDataHandlerRegistry.INTEGER);

    public TardisEntity(EntityType<?> type, World world) {
        super(type, world);
    }

    @Override
    protected void initDataTracker() {
        this.getDataTracker().startTracking(TARDIS_ID, 0);
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

    public int getTardisId() {
        return this.getDataTracker().get(TARDIS_ID);
    }

    private void setTardisId(int value) {
        this.getDataTracker().set(TARDIS_ID, value);
    }

    @Override
    public ActionResult interact(@NotNull PlayerEntity player, Hand hand) {
        ItemStack stack = player.getStackInHand(hand);
        if (player instanceof ServerPlayerEntity serverPlayer && stack.isEmpty()) {

            DimensionalUtil.changePlayerEntityDimension(serverPlayer, Who.MOD_ID, "tardis_dim");
            return ActionResult.SUCCESS;

        } else if (stack.isOf(WhoItems.TARDIS)) {

            NbtCompound nbt = new NbtCompound();
            nbt.putInt("who.tardis.id", getTardisId());
            stack.setNbt(nbt);
            return ActionResult.SUCCESS;

        } else {

            return ActionResult.FAIL;

        }
    }
}