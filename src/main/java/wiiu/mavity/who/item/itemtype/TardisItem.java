package wiiu.mavity.who.item.itemtype;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.*;
import net.minecraft.util.ActionResult;
import net.minecraft.util.math.Direction;
import net.minecraft.world.World;

import org.jetbrains.annotations.NotNull;

import wiiu.mavity.who.component.WhoComponents;
import wiiu.mavity.who.entity.WhoEntities;
import wiiu.mavity.who.entity.entitytype.TardisEntity;

public class TardisItem extends Item {

    public TardisItem(Settings settings) {
        super(settings);
    }

    @Override
    public void onCraft(ItemStack stack, World world, PlayerEntity player) {

    }

    @Override
    public ActionResult useOnBlock(@NotNull ItemUsageContext context) {

        World world = context.getWorld();
        if (!world.isClient()) {
            PlayerEntity player = context.getPlayer();
            ItemStack stack = player.getStackInHand(player.getActiveHand());

            TardisEntity tardisEntity = WhoEntities.TARDIS.create(world);
            if (player.getHorizontalFacing() == Direction.NORTH) {

                tardisEntity.setPosition(player.getX(), player.getY(), player.getZ() - 1);
                tardisEntity.setYaw(180);

            } else if (player.getHorizontalFacing() == Direction.SOUTH) {

                tardisEntity.setPosition(player.getX(), player.getY(), player.getZ() + 1);
                tardisEntity.setYaw(0);

            } else if (player.getHorizontalFacing() == Direction.EAST) {

                tardisEntity.setPosition(player.getX() + 1, player.getY(), player.getZ());
                tardisEntity.setYaw(-90);

            } else if (player.getHorizontalFacing() == Direction.WEST) {

                tardisEntity.setPosition(player.getX() - 1, player.getY(), player.getZ());
                tardisEntity.setYaw(90);

            }
            int newValue = WhoComponents.TARDIS_IDS.get(world).getTardisIds() + 1;
            tardisEntity.setTardisId(newValue);
            WhoComponents.TARDIS_IDS.get(world).setTardisIds(newValue);
            tardisEntity.setTardisOwner(player.getEntityName());
            world.spawnEntity(tardisEntity);
            if (!player.isCreative()) {

                stack.decrement(1);

            }
        }

        return ActionResult.CONSUME;
    }
}