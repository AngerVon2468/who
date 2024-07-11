package wiiu.mavity.who.block.blocktype;

import net.minecraft.block.*;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.sound.SoundCategory;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.*;
import net.minecraft.util.*;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.*;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.util.shape.VoxelShapes;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;

import org.jetbrains.annotations.*;

import wiiu.mavity.who.sound.WhoSounds;

public class FlightLeverBlock extends Block {

    public static final BooleanProperty PULLED = BooleanProperty.of("pulled");

    public static final DirectionProperty FACING = HorizontalFacingBlock.FACING;

    public FlightLeverBlock(Settings settings) {
        super(settings);
        this.setDefaultState(this.getStateManager().getDefaultState().with(FACING, Direction.NORTH).with(PULLED, false));
    }

    @Override
    public @Nullable BlockState getPlacementState(@NotNull ItemPlacementContext ctx) {
        return this.getDefaultState().with(FACING, ctx.getHorizontalPlayerFacing().getOpposite()).with(PULLED, false);
    }

    @Override
    @SuppressWarnings("deprecation")
    public BlockState rotate(@NotNull BlockState state, @NotNull BlockRotation rotation) {
        return state.with(FACING, rotation.rotate(state.get(FACING)));
    }

    @Override
    @SuppressWarnings("deprecation")
    public BlockState mirror(@NotNull BlockState state, @NotNull BlockMirror mirror) {
        return state.rotate(mirror.getRotation(state.get(FACING)));
    }

    @Override
    protected void appendProperties(@NotNull StateManager.Builder<Block, BlockState> builder) {
        builder.add(FACING, PULLED);
    }

    @Override
    @SuppressWarnings("deprecation")
    public ActionResult onUse(@NotNull BlockState state, @NotNull World world, BlockPos pos, PlayerEntity player, Hand hand, BlockHitResult hit) {
        if (!world.isClient()) {
            if (!state.get(PULLED)) {

                world.playSound(null, pos, WhoSounds.TARDIS_DEMAT, SoundCategory.MUSIC);
                world.setBlockState(pos, state.with(PULLED, true));

            } else {

                world.playSound(null, pos, WhoSounds.TARDIS_REMAT, SoundCategory.MUSIC);
                world.setBlockState(pos, state.with(PULLED, false));

            }
        }
        return ActionResult.CONSUME;
    }

    @Override
    @SuppressWarnings("deprecation")
    public VoxelShape getOutlineShape(@NotNull BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
        if (state.get(FACING) == Direction.NORTH || state.get(FACING) == Direction.SOUTH) {

            return Block.createCuboidShape(2.5, 0, 5, 13.5, 3, 11);

        } else if (state.get(FACING) == Direction.WEST || state.get(FACING) == Direction.EAST) {

            return Block.createCuboidShape(5, 0, 2.5, 11, 3, 13.5);

        } else {

            return VoxelShapes.empty();

        }
    }
}