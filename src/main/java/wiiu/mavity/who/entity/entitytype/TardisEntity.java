package wiiu.mavity.who.entity.entitytype;

import com.faux.customentitydata.api.*;

import net.minecraft.entity.*;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.*;
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

public class TardisEntity extends Entity implements ICustomDataHolder {

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
        if (!existingTardisId.contains("who.tardis.id")) {

            CustomDataHelper.setCustomData(this, tardisId);

        } else {

            CustomDataHelper.setCustomData(this, existingTardisId);

        }
    }

    @Override
    protected void readCustomDataFromNbt(@NotNull NbtCompound nbt) {
        if (nbt.contains(PersistentEntityDataConstants.CUSTOM_NBT_KEY, NbtElement.COMPOUND_TYPE)) {
            faux$setCustomData(nbt.getCompound(PersistentEntityDataConstants.CUSTOM_NBT_KEY));
        }
    }

    @Override
    protected void writeCustomDataToNbt(@NotNull NbtCompound nbt) {
        nbt.put(PersistentEntityDataConstants.CUSTOM_NBT_KEY, faux$getCustomData());
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

    public NbtCompound faux$persistentData;

    @Override
    public NbtCompound faux$getCustomData() {
        if (faux$persistentData == null)
            faux$persistentData = new NbtCompound();

        return faux$persistentData;
    }

    @Override
    public void faux$setCustomData(NbtCompound tag) {
        faux$persistentData = tag;
    }
}