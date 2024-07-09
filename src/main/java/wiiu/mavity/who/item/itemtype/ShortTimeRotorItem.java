package wiiu.mavity.who.item.itemtype;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.*;
import net.minecraft.util.ActionResult;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;

import org.jetbrains.annotations.NotNull;

import wiiu.mavity.who.entity.WhoEntities;
import wiiu.mavity.who.entity.entitytype.TimeRotorEntity;

public class ShortTimeRotorItem extends Item {

    public ShortTimeRotorItem(Settings settings) {
        super(settings);
    }

    @Override
    public ActionResult useOnBlock(@NotNull ItemUsageContext context) {

        World world = context.getWorld();
        if (!world.isClient()) {

            PlayerEntity player = context.getPlayer();
            Vec3d hitPos = context.getHitPos();
            ItemStack stack = player.getStackInHand(player.getActiveHand());
            TimeRotorEntity timeRotor = WhoEntities.TIME_ROTOR.create(world);
            if (player.getHorizontalFacing() == Direction.NORTH) {

                timeRotor.setYaw(180);

            } else if (player.getHorizontalFacing() == Direction.SOUTH) {

                timeRotor.setYaw(0);

            } else if (player.getHorizontalFacing() == Direction.EAST) {

                timeRotor.setYaw(-90);

            } else if (player.getHorizontalFacing() == Direction.WEST) {

                timeRotor.setYaw(90);

            }
            timeRotor.teleport(hitPos.x, hitPos.y, hitPos.z);
            world.spawnEntity(timeRotor);
            if (!player.isCreative()) {

                stack.decrement(1);

            }

        }
        return ActionResult.CONSUME;
    }
}