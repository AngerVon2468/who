package wiiu.mavity.who.entity.entitytype;

import com.mojang.serialization.Codec;

import net.fabricmc.fabric.api.attachment.v1.*;

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

public class TardisEntity extends Entity {

    Random random = new Random();

    public TardisEntity(EntityType<?> type, World world) {
        super(type, world);
    }

    @Override
    protected void initDataTracker() {
    }

    @Override
    @SuppressWarnings("all")
    public void onSpawnPacket(EntitySpawnS2CPacket packet) {
        super.onSpawnPacket(packet);
        boolean hasAttachment = this.hasAttached(TardisDataAttachments.TARDIS_ID); // false
        int tardisId = this.getAttachedOrCreate(TardisDataAttachments.TARDIS_ID); // 0, auto-initialized !
        hasAttachment = this.hasAttached(TardisDataAttachments.TARDIS_ID); // now true
        this.modifyAttached(TardisDataAttachments.TARDIS_ID, tardis_id -> random.nextInt(Integer.MIN_VALUE, Integer.MAX_VALUE)); // Change Tardis ID
    }

    @Override
    protected void readCustomDataFromNbt(@NotNull NbtCompound nbt) {
    }

    @Override
    protected void writeCustomDataToNbt(@NotNull NbtCompound nbt) {
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

        if (this.hasAttached(TardisDataAttachments.TARDIS_ID)) {

            int tardisId = this.getAttached(TardisDataAttachments.TARDIS_ID).intValue();
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

        } else {

            return ActionResult.FAIL;

        }

    }

    @SuppressWarnings("all")
    public static class TardisDataAttachments {

        public static final AttachmentType<Integer> TARDIS_ID = AttachmentRegistry.<Integer>builder() // Builder for finer control
                .persistent(Codec.INT) // required codec for persistence
                .copyOnDeath() // will persist over entity death and respawn
                .initializer(() -> 0) // default value
                .buildAndRegister(new Identifier(Who.MOD_ID, "tardis_id")
        );
    }
}