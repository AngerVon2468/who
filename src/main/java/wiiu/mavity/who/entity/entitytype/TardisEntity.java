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

    public TardisEntity(EntityType<?> type, World world) {
        super(type, world);
        this.tardisId = 0;
        this.tardisOwner = null;
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
    }

    @Override
    protected void writeCustomDataToNbt(@NotNull NbtCompound nbt) {
        nbt.putInt("tardisId", tardisId);
        nbt.putString("tardisOwner", tardisOwner);
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

        if (!world.isClient()) {
            ItemStack stack = player.getStackInHand(hand);
            player.sendMessage(Text.literal("(Tardis Entity) Tardis id: " + this.getTardisId()));
            player.sendMessage(Text.literal("(Tardis Entity) Tardis Owner: " + this.getTardisOwner()));
            if (player instanceof ServerPlayerEntity serverPlayer && stack.isEmpty()) {

                DimensionalUtil.changePlayerEntityDimension(serverPlayer, Who.MOD_ID, "tardis_dim");
                return ActionResult.SUCCESS;

            } else if (stack.isOf(WhoItems.TARDIS)) {

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