package wiiu.mavity.who.block.blocktype;

import net.minecraft.block.*;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.*;
import net.minecraft.util.*;
import net.minecraft.util.function.BooleanBiFunction;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.*;
import net.minecraft.util.shape.*;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;

import org.jetbrains.annotations.*;

import java.util.stream.Stream;

public class TardisDoorBlock extends Block {

    public static final BooleanProperty OPEN = BooleanProperty.of("open");

    public static final DirectionProperty FACING = HorizontalFacingBlock.FACING;

    public TardisDoorBlock(Settings settings) {
        super(settings);
        this.setDefaultState(this.getStateManager().getDefaultState().with(FACING, Direction.NORTH).with(OPEN, false));
    }

    @Override
    public @Nullable BlockState getPlacementState(@NotNull ItemPlacementContext ctx) {
        return this.getDefaultState().with(FACING, ctx.getHorizontalPlayerFacing().getOpposite()).with(OPEN, false);
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
        builder.add(FACING, OPEN);
    }

    @Override
    @SuppressWarnings("deprecation")
    public ActionResult onUse(@NotNull BlockState state, @NotNull World world, BlockPos pos, PlayerEntity player, Hand hand, BlockHitResult hit) {
        if (!world.isClient()) {
            if (!state.get(OPEN)) {

                world.playSound(null, pos, SoundEvents.BLOCK_IRON_TRAPDOOR_OPEN, SoundCategory.MUSIC);
                world.setBlockState(pos, state.with(OPEN, true));

            } else {

                world.playSound(null, pos, SoundEvents.BLOCK_IRON_TRAPDOOR_CLOSE, SoundCategory.MUSIC);
                world.setBlockState(pos, state.with(OPEN, false));

            }
        }
        return ActionResult.success(true);
    }

    @Override
    @SuppressWarnings("deprecation")
    public VoxelShape getOutlineShape(@NotNull BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
        if (state.get(FACING) == Direction.NORTH && state.get(OPEN)) {

            return Stream.of(
                    Block.createCuboidShape(15, 0, -7, 16, 32, 1),
                    Block.createCuboidShape(13, 10, -6, 15, 16, 0),
                    Block.createCuboidShape(0, 0, -7, 1, 32, 1)
            ).reduce((v1, v2) -> VoxelShapes.combineAndSimplify(v1, v2, BooleanBiFunction.OR)).get();

        } else if (state.get(FACING) == Direction.SOUTH && state.get(OPEN)) {

            return Stream.of(
                    Block.createCuboidShape(0, 0, 15, 1, 32, 23),
                    Block.createCuboidShape(1, 10, 16, 3, 16, 22),
                    Block.createCuboidShape(15, 0, 15, 16, 32, 23)
            ).reduce((v1, v2) -> VoxelShapes.combineAndSimplify(v1, v2, BooleanBiFunction.OR)).get();

        } else if (state.get(FACING) == Direction.EAST && state.get(OPEN)) {

            return Stream.of(
                    Block.createCuboidShape(15, 0, 15, 23, 32, 16),
                    Block.createCuboidShape(16, 10, 13, 22, 16, 15),
                    Block.createCuboidShape(15, 0, 0, 23, 32, 1)
            ).reduce((v1, v2) -> VoxelShapes.combineAndSimplify(v1, v2, BooleanBiFunction.OR)).get();

        } else if (state.get(FACING) == Direction.WEST && state.get(OPEN)) {

            return Stream.of(
                    Block.createCuboidShape(-7, 0, 0, 1, 32, 1),
                    Block.createCuboidShape(-6, 10, 1, 0, 16, 3),
                    Block.createCuboidShape(-7, 0, 15, 1, 32, 16)
            ).reduce((v1, v2) -> VoxelShapes.combineAndSimplify(v1, v2, BooleanBiFunction.OR)).get();

        } else if (state.get(FACING) == Direction.NORTH && !state.get(OPEN)) {

            return Stream.of(
                    Block.createCuboidShape(8, 0, 0, 16, 32, 1),
                    Block.createCuboidShape(0, 0, 0, 8, 32, 1),
                    Block.createCuboidShape(9, 10, 1, 15, 16, 3)
            ).reduce((v1, v2) -> VoxelShapes.combineAndSimplify(v1, v2, BooleanBiFunction.OR)).get();

        } else if (state.get(FACING) == Direction.SOUTH && !state.get(OPEN)) {

            return Stream.of(
                    Block.createCuboidShape(0, 0, 15, 8, 32, 16),
                    Block.createCuboidShape(8, 0, 15, 16, 32, 16),
                    Block.createCuboidShape(1, 10, 13, 7, 16, 15)
            ).reduce((v1, v2) -> VoxelShapes.combineAndSimplify(v1, v2, BooleanBiFunction.OR)).get();

        } else if (state.get(FACING) == Direction.EAST && !state.get(OPEN)) {

            return Stream.of(
                    Block.createCuboidShape(15, 0, 8, 16, 32, 16),
                    Block.createCuboidShape(15, 0, 0, 16, 32, 8),
                    Block.createCuboidShape(13, 10, 9, 15, 16, 15)
            ).reduce((v1, v2) -> VoxelShapes.combineAndSimplify(v1, v2, BooleanBiFunction.OR)).get();

        } else if (state.get(FACING) == Direction.WEST && !state.get(OPEN)) {

            return Stream.of(
                    Block.createCuboidShape(0, 0, 0, 1, 32, 8),
                    Block.createCuboidShape(0, 0, 8, 1, 32, 16),
                    Block.createCuboidShape(1, 10, 1, 3, 16, 7)
            ).reduce((v1, v2) -> VoxelShapes.combineAndSimplify(v1, v2, BooleanBiFunction.OR)).get();

        } else {

            return VoxelShapes.empty();

        }
    }
}