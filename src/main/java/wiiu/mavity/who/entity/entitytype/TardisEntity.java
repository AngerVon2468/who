package wiiu.mavity.who.entity.entitytype;

import com.faux.customentitydata.api.CustomDataHelper;

import net.minecraft.entity.*;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.network.packet.s2c.play.EntitySpawnS2CPacket;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.text.Text;
import net.minecraft.util.*;
import net.minecraft.world.World;

import org.jetbrains.annotations.NotNull;

import wiiu.mavity.who.Who;
import wiiu.mavity.who.item.WhoItems;
import wiiu.mavity.who.util.DimensionalUtil;
import wiiu.mavity.who.util.data.NbtUtil;

import java.util.Random;

public class TardisEntity extends Entity {

    Random random = new Random();

    public TardisEntity(EntityType<?> type, World world) {
        super(type, world);
    }

    @Override
    protected void initDataTracker() {
    }

    @Override
    public void onSpawnPacket(EntitySpawnS2CPacket packet) {
        super.onSpawnPacket(packet);
        NbtCompound tardisId = NbtUtil.createNbt("who.tardis.id", random.nextInt(Integer.MIN_VALUE, Integer.MAX_VALUE));
        NbtCompound existingTardisId = CustomDataHelper.getCustomData(this);
        if (existingTardisId == null) {

            CustomDataHelper.setCustomData(this, tardisId);

        } else {

            CustomDataHelper.setCustomData(this, existingTardisId);

        }
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
    public ActionResult interact(@NotNull PlayerEntity player, Hand hand) {
        NbtCompound tardisIdNbt = CustomDataHelper.getCustomData(this);
        int tardisId = tardisIdNbt.getInt("who.tardis.id");
        player.sendMessage(Text.literal("(Tardis Entity) Tardis id: " + tardisId));
        ItemStack stack = player.getStackInHand(hand);
        if (player instanceof ServerPlayerEntity serverPlayer && stack.isEmpty()) {

            DimensionalUtil.changePlayerEntityDimension(serverPlayer, Who.MOD_ID, "tardis_dim");
            return ActionResult.SUCCESS;

        } else if (stack.isOf(WhoItems.TARDIS)) {

            NbtUtil.setNbt(stack, "who.tardis.id", tardisId);
            return ActionResult.SUCCESS;

        } else {
            return ActionResult.FAIL;
        }
    }
}