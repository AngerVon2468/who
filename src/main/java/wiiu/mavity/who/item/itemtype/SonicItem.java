package wiiu.mavity.who.item.itemtype;

import net.minecraft.block.*;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.*;
import net.minecraft.registry.tag.BlockTags;
import net.minecraft.state.property.Properties;
import net.minecraft.text.Text;
import net.minecraft.util.ActionResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import org.jetbrains.annotations.NotNull;

import wiiu.mavity.who.item.WhoItems;

public class SonicItem extends Item {

    public int i;

    public SonicItem(Settings settings) {
        super(settings);
        i = 0;
    }

    @Override
    public ActionResult useOnBlock(@NotNull ItemUsageContext context) {

        World world = context.getWorld();
        BlockPos blockPos = context.getBlockPos();
        BlockState blockState = world.getBlockState(blockPos);
        PlayerEntity player = context.getPlayer();
        this.i++;
        if (blockState.isIn(BlockTags.DOORS)) {

            if (!blockState.get(Properties.OPEN)) {

                this.openDoor(world, blockState, blockPos, true);

            } else {

                this.openDoor(world, blockState, blockPos, false);

            }

        } else if (blockState.isOf(Blocks.REDSTONE_LAMP)) {

            if (!blockState.get(Properties.LIT)) {

                this.turnOnLamp(world, blockState, blockPos, true);

            } else {

                this.turnOnLamp(world, blockState, blockPos, false);

            }

        }
        player.sendMessage(Text.literal("UsageTicks: " + this.i));

        return ActionResult.SUCCESS;
    }

    @Override
    public void inventoryTick(ItemStack stack, World world, Entity entity, int slot, boolean selected) {
        if (entity instanceof PlayerEntity player && player.getStackInHand(player.getActiveHand()).isOf(WhoItems.SONIC) && player.isUsingItem()) {

            this.i = 0;

        }
    }

    public void openDoor(World world, @NotNull BlockState state, BlockPos pos, boolean open) {
        if (state.get(Properties.OPEN) != open && this.i % 80 == 0) {
            world.setBlockState(pos, state.with(Properties.OPEN, open));
            this.i = 0;
        }
    }

    public void turnOnLamp(World world, @NotNull BlockState state, BlockPos pos, boolean lit) {
        if (state.get(Properties.LIT) != lit && this.i % 80 == 0) {
            world.setBlockState(pos, state.with(Properties.LIT, lit));
            this.i = 0;
        }
    }
}