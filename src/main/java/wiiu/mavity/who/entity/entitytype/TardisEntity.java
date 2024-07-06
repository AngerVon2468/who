package wiiu.mavity.who.entity.entitytype;

import net.minecraft.entity.*;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.*;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.text.Text;
import net.minecraft.util.*;
import net.minecraft.world.World;

import org.jetbrains.annotations.NotNull;

import wiiu.mavity.who.Who;
import wiiu.mavity.who.item.WhoItems;
import wiiu.mavity.who.util.DimensionalUtil;
import wiiu.mavity.who.util.data.NbtUtil;

public class TardisEntity extends Entity {

    public int tardisId;

    public TardisEntity(EntityType<?> type, World world) {
        super(type, world);
        this.tardisId = 0;
    }

    @Override
    protected void initDataTracker() {
    }

    @Override
    protected void readCustomDataFromNbt(@NotNull NbtCompound nbt) {
        if (nbt.contains("tardisId")) {

            this.tardisId = nbt.getInt("tardisId");

        }
    }

    @Override
    protected void writeCustomDataToNbt(@NotNull NbtCompound nbt) {
        nbt.putInt("tardisId", tardisId);
    }

    public void setTardisId(Integer value) {
        this.tardisId = value;
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
        int tardisId = this.tardisId;
        player.sendMessage(Text.literal("(Tardis Entity) Tardis id: " + tardisId));
        if (player instanceof ServerPlayerEntity serverPlayer && stack.isEmpty()) {

            DimensionalUtil.changePlayerEntityDimension(serverPlayer, Who.MOD_ID, "tardis_dim");
            return ActionResult.SUCCESS;

        } else if (stack.isOf(WhoItems.TARDIS)) {

            NbtUtil.setNbt(stack, "who.tardis.id", tardisId);
            return ActionResult.SUCCESS;

        }
        else {

            return ActionResult.FAIL;

        }

    }
}