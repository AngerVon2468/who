package wiiu.mavity.who.entity.entitytype;

import net.minecraft.entity.*;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.*;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.text.Text;
import net.minecraft.util.*;
import net.minecraft.world.World;

import org.jetbrains.annotations.*;

import wiiu.mavity.who.Who;
import wiiu.mavity.who.item.WhoItems;
import wiiu.mavity.who.util.DimensionalUtil;
import wiiu.mavity.who.util.data.NbtUtil;

public class TardisEntity extends Entity {

    World world = this.getWorld();

    public int tardisId;
    @Nullable public String tardisOwner;
    public boolean hasInterior;

    public TardisEntity(EntityType<?> type, World world) {
        super(type, world);
        this.tardisId = 0;
        this.tardisOwner = null;
        this.hasInterior = false;
    }

    @Override
    protected void initDataTracker() {
    }

    @Override
    protected void readCustomDataFromNbt(@NotNull NbtCompound nbt) {
        if (nbt.contains("tardisId")) {

            this.tardisId = nbt.getInt("tardisId");

        }
        if (nbt.contains("tardisOwner")) {

            this.tardisOwner = nbt.getString("tardisOwner");

        }
        if (nbt.contains("hasInterior")) {

            this.hasInterior = nbt.getBoolean("hasInterior");

        }
    }

    @Override
    protected void writeCustomDataToNbt(@NotNull NbtCompound nbt) {
        nbt.putInt("tardisId", this.tardisId);
        nbt.putString("tardisOwner", this.tardisOwner);
        nbt.putBoolean("hasInterior", this.hasInterior);
    }

    public void setTardisId(Integer value) {
        this.tardisId = value;
    }

    public void setTardisOwner(@Nullable String value) {
        this.tardisOwner = value;
    }

    public int getTardisId() {
        return this.tardisId;
    }

    public @Nullable String getTardisOwner() {
        return this.tardisOwner;
    }

    public boolean getHasInterior() {
        return this.hasInterior;
    }

    public void setHasInterior(boolean value) {
        this.hasInterior = value;
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
    public ActionResult interact(@NotNull PlayerEntity player, Hand hand) {

        ItemStack stack = player.getStackInHand(hand);
        if (!this.world.isClient()) {
            player.sendMessage(Text.literal("(Tardis Entity) Tardis id: " + this.getTardisId()));
            player.sendMessage(Text.literal("(Tardis Entity) Tardis Owner: " + this.getTardisOwner()));
            player.sendMessage(Text.literal("(Tardis Entity) Has Interior: " + this.getHasInterior()));
            int xz = this.getTardisId() * 100;
            if (player instanceof ServerPlayerEntity serverPlayer && stack.isEmpty()) {

                if (!this.getHasInterior()) {

                    DimensionalUtil.changePlayerEntityDimensionAndCreateTardisInterior(serverPlayer, Who.MOD_ID, "tardis_dim", xz, xz);
                    this.setHasInterior(true);

                } else {

                    DimensionalUtil.changePlayerEntityDimension(serverPlayer, Who.MOD_ID, "tardis_dim", xz, xz);

                }
                return ActionResult.SUCCESS;

            }
            if (stack.isOf(WhoItems.TARDIS)) {

                if (stack.getNbt() != null && !stack.getNbt().contains("who.tardis.id")) {

                    return ActionResult.FAIL;

                } else {

                    NbtUtil.setNbt(stack, "who.tardis.id", this.getTardisId());
                    return ActionResult.SUCCESS;

                }

            } else {

                return ActionResult.FAIL;

            }

        } else {

            return ActionResult.FAIL;

        }
    }
}