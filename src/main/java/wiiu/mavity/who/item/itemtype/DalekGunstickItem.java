package wiiu.mavity.who.item.itemtype;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.*;
import net.minecraft.util.*;
import net.minecraft.world.World;

import org.jetbrains.annotations.NotNull;

import wiiu.mavity.who.entity.WhoEntities;
import wiiu.mavity.who.entity.entitytype.DalekBeamEntity;

public class DalekGunstickItem extends Item {

    public DalekGunstickItem(Settings settings) {
        super(settings);
    }

    @Override
    public TypedActionResult<ItemStack> use(@NotNull World world, @NotNull PlayerEntity user, Hand hand) {

        ItemStack stack = user.getStackInHand(hand);
        if (!world.isClient()) {

            DalekBeamEntity dalekBeam = WhoEntities.DALEK_BEAM.create(world);
            dalekBeam.setYaw(user.getHeadYaw());
            dalekBeam.setPitch(user.getPitch());
            dalekBeam.setOwner(user);
            dalekBeam.setPosition(user.getX(), user.getY() + user.getEyeHeight(user.getPose()), user.getZ());
            dalekBeam.setVelocity(user, user.getPitch(), user.getYaw(), 0.0f,  1.5f, 0.0f);
            world.spawnEntity(dalekBeam);
            stack.damage(1, user, (p) -> p.sendToolBreakStatus(user.getActiveHand()));
            user.getItemCooldownManager().set(stack.getItem(), 30);

        }

        return TypedActionResult.success(stack, false);
    }
}