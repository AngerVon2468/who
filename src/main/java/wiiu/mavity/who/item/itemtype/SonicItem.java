package wiiu.mavity.who.item.itemtype;

import net.minecraft.block.*;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.*;
import net.minecraft.registry.tag.BlockTags;
import net.minecraft.state.property.Properties;
import net.minecraft.text.Text;
import net.minecraft.util.ActionResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import org.jetbrains.annotations.NotNull;

public class SonicItem extends Item {

    public int i;

    public SonicItem(Settings settings) {
        super(settings);
        i = 0;
    }

    public int getI() {
        return this.i;
    }

    public void setI(int value) {
        this.i = value;
    }

    @Override
    public ActionResult useOnBlock(@NotNull ItemUsageContext context) {

        World world = context.getWorld();
        BlockPos blockPos = context.getBlockPos();
        BlockState blockState = world.getBlockState(blockPos);
        PlayerEntity player = context.getPlayer();
        if (!world.isClient()) {
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
        }

        return ActionResult.SUCCESS;
    }

    public void openDoor(World world, @NotNull BlockState state, BlockPos pos, boolean open) {
        this.i++;
        if (state.get(Properties.OPEN) != open && this.getI() % 40 == 0) {
            this.setI(0);
            world.setBlockState(pos, state.with(Properties.OPEN, open));
        }
    }

    public void turnOnLamp(World world, @NotNull BlockState state, BlockPos pos, boolean lit) {
        this.i++;
        if (state.get(Properties.LIT) != lit && this.getI() % 40 == 0) {
            this.setI(0);
            world.setBlockState(pos, state.with(Properties.LIT, lit));
        }
    }
}