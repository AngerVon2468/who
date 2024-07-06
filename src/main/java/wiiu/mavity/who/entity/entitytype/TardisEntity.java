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
    public void onSpawnPacket(EntitySpawnS2CPacket packet) {
        super.onSpawnPacket(packet);
        if (!this.hasAttached(TardisDataAttachments.TARDIS_ID)) {
            this.setAttached(TardisDataAttachments.TARDIS_ID, random.nextInt(Integer.MIN_VALUE, Integer.MAX_VALUE)); // Change Tardis ID
        }
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

        ItemStack stack = player.getStackInHand(hand);
        if (this.hasAttached(TardisDataAttachments.TARDIS_ID)) {

            int tardisId = this.getAttached(TardisDataAttachments.TARDIS_ID);
            player.sendMessage(Text.literal("(Tardis Entity) Tardis id: " + tardisId));
            if (stack.isOf(WhoItems.TARDIS)) {

                NbtUtil.setNbt(stack, "who.tardis.id", tardisId);
                return ActionResult.SUCCESS;

            } else {

                return ActionResult.FAIL;

            }

        } else if (player instanceof ServerPlayerEntity serverPlayer && stack.isEmpty()) {

            DimensionalUtil.changePlayerEntityDimension(serverPlayer, Who.MOD_ID, "tardis_dim");
            return ActionResult.SUCCESS;

        } else {

            return ActionResult.FAIL;

        }

    }

    public static class TardisDataAttachments {

        public static AttachmentType<Integer> TARDIS_ID = AttachmentRegistry.createPersistent(
                new Identifier("who:tardis_id"),
                Codec.INT
        );
    }
}