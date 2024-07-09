package wiiu.mavity.who.item.itemtype;

import net.minecraft.block.*;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.*;
import net.minecraft.registry.tag.BlockTags;
import net.minecraft.state.property.Properties;
import net.minecraft.util.*;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import org.jetbrains.annotations.NotNull;

import wiiu.mavity.who.util.data.NbtUtil;

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
    public TypedActionResult<ItemStack> use(@NotNull World world, @NotNull PlayerEntity user, Hand hand) {
        ItemStack stack = user.getStackInHand(hand);

        if (!world.isClient()) {
            if (user.isSneaking() && stack.getNbt() == null) {

                NbtUtil.setNbt(stack, "who.sonic.colour.green", 1);

            } else if (user.isSneaking() && stack.getNbt().contains("who.sonic.colour.green")) {

                NbtUtil.setNbt(stack, "who.sonic.colour.purple", 1);

            } else if (user.isSneaking() && stack.getNbt().contains("who.sonic.colour.purple")) {

                NbtUtil.setNbt(stack, "who.sonic.colour.orange", 1);

            } else if (user.isSneaking() && stack.getNbt().contains("who.sonic.colour.orange")) {

                NbtUtil.setNbt(stack, "who.sonic.blue_2", 1);

            } else if (user.isSneaking() && stack.getNbt().contains("who.sonic.blue_2")) {

                NbtUtil.setNbt(stack, "who.sonic.green_2", 1);

            } else if (user.isSneaking() && stack.getNbt().contains("who.sonic.green_2")) {

                NbtUtil.setNbt(stack, "who.sonic.purple_2", 1);

            } else if (user.isSneaking() && stack.getNbt().contains("who.sonic.purple_2")) {

                NbtUtil.setNbt(stack, "who.sonic.orange_2", 1);

            } else if (user.isSneaking() && stack.getNbt().contains("who.sonic.orange_2")) {

                stack.removeSubNbt("who.sonic.orange_2");

            }
        }

        return TypedActionResult.consume(stack);
    }

    @Override
    public ActionResult useOnBlock(@NotNull ItemUsageContext context) {

        World world = context.getWorld();
        BlockPos blockPos = context.getBlockPos();
        BlockState blockState = world.getBlockState(blockPos);
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

        return ActionResult.CONSUME;
    }

    public void openDoor(World world, @NotNull BlockState state, BlockPos pos, boolean open) {
        this.i++;
        if (state.get(Properties.OPEN) != open && this.getI() % 20 == 0) {
            this.setI(0);
            world.setBlockState(pos, state.with(Properties.OPEN, open));
        }
    }

    public void turnOnLamp(World world, @NotNull BlockState state, BlockPos pos, boolean lit) {
        this.i++;
        if (state.get(Properties.LIT) != lit && this.getI() % 20 == 0) {
            this.setI(0);
            world.setBlockState(pos, state.with(Properties.LIT, lit));
        }
    }
}