package wiiu.mavity.who.item.itemtype;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.*;
import net.minecraft.util.ActionResult;
import net.minecraft.util.math.Direction;
import net.minecraft.world.World;

import wiiu.mavity.who.entity.WhoEntities;
import wiiu.mavity.who.entity.entitytype.TardisEntity;

public class TardisItem extends Item {

    public TardisItem(Settings settings) {
        super(settings);
    }

    @Override
    public ActionResult useOnBlock(ItemUsageContext context) {
        World world = context.getWorld();
        PlayerEntity user = context.getPlayer();

        TardisEntity tardisEntity = WhoEntities.TARDIS.create(world);
        if (user.getHorizontalFacing() == Direction.NORTH) {

            tardisEntity.setPosition(user.getX(), user.getY(), user.getZ() - 1);

        } else if (user.getHorizontalFacing() == Direction.SOUTH) {

            tardisEntity.setPosition(user.getX(), user.getY(), user.getZ() + 1);

        } else if (user.getHorizontalFacing() == Direction.EAST) {

            tardisEntity.setPosition(user.getX() + 1, user.getY(), user.getZ());

        } else if (user.getHorizontalFacing() == Direction.WEST) {

            tardisEntity.setPosition(user.getX() - 1, user.getY(), user.getZ());

        }
        tardisEntity.setYaw(user.getHeadYaw());
        world.spawnEntity(tardisEntity);

        return ActionResult.CONSUME;
    }
}