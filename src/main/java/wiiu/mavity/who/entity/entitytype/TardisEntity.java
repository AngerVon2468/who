package wiiu.mavity.who.entity.entitytype;

import net.minecraft.entity.*;
import net.minecraft.entity.data.*;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.util.*;
import net.minecraft.util.collection.DefaultedList;
import net.minecraft.world.World;

import org.jetbrains.annotations.NotNull;

import wiiu.mavity.who.Who;
import wiiu.mavity.who.item.WhoItems;
import wiiu.mavity.who.util.DimensionalUtil;
import wiiu.mavity.who.util.data.NbtUtil;

public class TardisEntity extends LivingEntity {

    public static final TrackedData<Integer> TARDISES = DataTracker.registerData(TardisEntity.class, TrackedDataHandlerRegistry.INTEGER);

    public static final TrackedData<Integer> TARDIS_ID = DataTracker.registerData(TardisEntity.class, TrackedDataHandlerRegistry.INTEGER);

    public TardisEntity(EntityType<? extends LivingEntity> type, World world) {
        super(type, world);
    }

    @Override
    protected void initDataTracker() {
        super.initDataTracker();
        this.dataTracker.startTracking(TARDISES, 0);
        this.dataTracker.startTracking(TARDIS_ID, 0);
    }

    public void setTardises(int amount) {
        this.dataTracker.set(TARDISES, amount);
    }

    public int getTardises() {
        return this.dataTracker.get(TARDISES);
    }

    public void setTardisId(int amount) {
        this.dataTracker.set(TARDIS_ID, amount);
    }

    public int getTardisId() {
        return this.dataTracker.get(TARDIS_ID);
    }

    @Override
    public Iterable<ItemStack> getArmorItems() {
        return DefaultedList.ofSize(0, ItemStack.EMPTY);
    }

    @Override
    public ItemStack getEquippedStack(EquipmentSlot slot) {
        return ItemStack.EMPTY;
    }

    @Override
    public void equipStack(EquipmentSlot slot, ItemStack stack) {

    }

    @Override
    public void readCustomDataFromNbt(NbtCompound nbt) {

    }

    @Override
    public void writeCustomDataToNbt(NbtCompound nbt) {

    }

    @Override
    public boolean canHit() {
        return !this.isRemoved();
    }

    @Override
    public Arm getMainArm() {
        return null;
    }

    @Override
    public boolean isCollidable() {
        return true;
    }

    @Override
    public ActionResult interact(@NotNull PlayerEntity player, Hand hand) {
        ItemStack stack = player.getStackInHand(hand);
        if (player instanceof ServerPlayerEntity serverPlayer && stack.isEmpty()) {

            DimensionalUtil.changePlayerEntityDimension(serverPlayer, Who.MOD_ID, "tardis_dim");
            return ActionResult.SUCCESS;

        } else if (stack.isOf(WhoItems.TARDIS)) {

            NbtUtil.setNbt(stack, "who.tardis.id", this.getTardisId());
            return ActionResult.SUCCESS;

        } else {

            return ActionResult.FAIL;

        }
    }
}