package wiiu.mavity.who.item.itemtype;

import net.minecraft.block.*;
import net.minecraft.item.*;
import net.minecraft.registry.tag.BlockTags;
import net.minecraft.state.property.Properties;
import net.minecraft.util.ActionResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import org.jetbrains.annotations.NotNull;

public class SonicItem extends Item {

    public SonicItem(Settings settings) {
        super(settings);
    }

    @Override
    public ActionResult useOnBlock(@NotNull ItemUsageContext context) {

        World world = context.getWorld();
        BlockPos blockPos = context.getBlockPos();
        BlockState blockState = world.getBlockState(blockPos);
        if (blockState.isIn(BlockTags.DOORS) && !blockState.get(Properties.OPEN)) {

            this.openDoor(world, blockState, blockPos, true);

        } else {

            this.openDoor(world, blockState, blockPos, false);

        }
        if (blockState.isOf(Blocks.REDSTONE_LAMP) && !blockState.get(Properties.LIT)) {

            this.turnOnLamp(world, blockState, blockPos, true);

        } else {

            this.turnOnLamp(world, blockState, blockPos, false);

        }

        return ActionResult.SUCCESS;
    }

    public void openDoor(World world, @NotNull BlockState state, BlockPos pos, boolean open) {
        if (state.get(Properties.OPEN) != open) {
            world.setBlockState(pos, state.with(Properties.OPEN, open));
        }
    }

    public void turnOnLamp(World world, @NotNull BlockState state, BlockPos pos, boolean lit) {
        if (state.get(Properties.LIT) != lit) {
            world.setBlockState(pos, state.with(Properties.LIT, lit));
        }
    }
}