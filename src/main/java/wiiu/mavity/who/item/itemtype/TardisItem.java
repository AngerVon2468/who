package wiiu.mavity.who.item.itemtype;

import com.faux.customentitydata.api.CustomDataHelper;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.*;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.text.Text;
import net.minecraft.util.ActionResult;
import net.minecraft.util.math.Direction;
import net.minecraft.world.World;

import org.jetbrains.annotations.NotNull;

import wiiu.mavity.who.entity.WhoEntities;
import wiiu.mavity.who.entity.entitytype.TardisEntity;

public class TardisItem extends Item {

    /*
    public static final Predicate<LivingEntity> TARDIS_PREDICATE = entity -> entity.isAlive() && entity.isAttackable();
    */

    public TardisItem(Settings settings) {
        super(settings);
    }

    @Override
    public void onCraft(ItemStack stack, World world, PlayerEntity player) {

    }

    @Override
    public ActionResult useOnBlock(@NotNull ItemUsageContext context) {
        World world = context.getWorld();
        PlayerEntity player = context.getPlayer();

        TardisEntity tardisEntity = WhoEntities.TARDIS.create(world);
        /*
        final TargetPredicate TARGET_PREDICATE = TargetPredicate.createAttackable().setBaseMaxDistance(Double.MAX_VALUE).setPredicate(TARDIS_PREDICATE);
        List<TardisEntity> tardises = world.getTargets(TardisEntity.class, TARGET_PREDICATE, tardisEntity, tardisEntity.getBoundingBox().expand(Double.MAX_VALUE)).stream().toList();
        for (TardisEntity tardis : tardises) {
            tardisEntity.setTardises(tardisEntity.getTardises() + 1);
            tardisEntity.setTardisId(tardisEntity.getTardises() + 1);
        }
        */
        if (player.getHorizontalFacing() == Direction.NORTH) {

            tardisEntity.setPosition(player.getX(), player.getY(), player.getZ() - 1);

        } else if (player.getHorizontalFacing() == Direction.SOUTH) {

            tardisEntity.setPosition(player.getX(), player.getY(), player.getZ() + 1);

        } else if (player.getHorizontalFacing() == Direction.EAST) {

            tardisEntity.setPosition(player.getX() + 1, player.getY(), player.getZ());

        } else if (player.getHorizontalFacing() == Direction.WEST) {

            tardisEntity.setPosition(player.getX() - 1, player.getY(), player.getZ());

        }
        tardisEntity.setYaw(-player.getHeadYaw());
        world.spawnEntity(tardisEntity);

        return ActionResult.CONSUME;
    }
}