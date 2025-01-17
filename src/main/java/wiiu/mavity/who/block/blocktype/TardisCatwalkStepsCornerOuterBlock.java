package wiiu.mavity.who.block.blocktype;

import net.minecraft.block.*;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.DirectionProperty;
import net.minecraft.util.*;
import net.minecraft.util.function.BooleanBiFunction;
import net.minecraft.util.math.*;
import net.minecraft.util.shape.*;
import net.minecraft.world.BlockView;

import org.jetbrains.annotations.*;

import java.util.stream.Stream;

public class TardisCatwalkStepsCornerOuterBlock extends Block {

    public static final DirectionProperty FACING = HorizontalFacingBlock.FACING;

    public TardisCatwalkStepsCornerOuterBlock(Settings settings) {
        super(settings);
        this.setDefaultState(this.getStateManager().getDefaultState().with(FACING, Direction.NORTH));
    }

    @Override
    public @Nullable BlockState getPlacementState(@NotNull ItemPlacementContext ctx) {
        return this.getDefaultState().with(FACING, ctx.getHorizontalPlayerFacing().getOpposite());
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
        builder.add(FACING);
    }

    @Override
    @SuppressWarnings("deprecation")
    public VoxelShape getOutlineShape(@NotNull BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
        if (state.get(FACING) == Direction.NORTH) {

            return Stream.of(
                    Block.createCuboidShape(0, 7, 0, 8, 8, 8),
                    Block.createCuboidShape(0, 15, 8, 8, 16, 16),
                    Block.createCuboidShape(8, 15, 0, 16, 16, 16),
                    Block.createCuboidShape(11, 0, 11, 13, 15, 13),
                    Block.createCuboidShape(11, 5, 3, 13, 13, 5),
                    Block.createCuboidShape(3, 5, 11, 5, 13, 13),
                    Block.createCuboidShape(3, 0, 3, 5, 7, 5),
                    Block.createCuboidShape(11, 13, 3, 13, 15, 11),
                    Block.createCuboidShape(3, 13, 11, 11, 15, 13),
                    Block.createCuboidShape(3, 5, 5, 5, 7, 11),
                    Block.createCuboidShape(5, 5, 3, 11, 7, 5)
            ).reduce((v1, v2) -> VoxelShapes.combineAndSimplify(v1, v2, BooleanBiFunction.OR)).get();

        } else if (state.get(FACING) == Direction.SOUTH) {

            return Stream.of(
                    Block.createCuboidShape(8, 7, 8, 16, 8, 16),
                    Block.createCuboidShape(8, 15, 0, 16, 16, 8),
                    Block.createCuboidShape(0, 15, 0, 8, 16, 16),
                    Block.createCuboidShape(3, 0, 3, 5, 15, 5),
                    Block.createCuboidShape(3, 5, 11, 5, 13, 13),
                    Block.createCuboidShape(11, 5, 3, 13, 13, 5),
                    Block.createCuboidShape(11, 0, 11, 13, 7, 13),
                    Block.createCuboidShape(3, 13, 5, 5, 15, 13),
                    Block.createCuboidShape(5, 13, 3, 13, 15, 5),
                    Block.createCuboidShape(11, 5, 5, 13, 7, 11),
                    Block.createCuboidShape(5, 5, 11, 11, 7, 13)
            ).reduce((v1, v2) -> VoxelShapes.combineAndSimplify(v1, v2, BooleanBiFunction.OR)).get();

        } else if (state.get(FACING) == Direction.EAST) {

            return Stream.of(
                    Block.createCuboidShape(8, 7, 0, 16, 8, 8),
                    Block.createCuboidShape(0, 15, 0, 8, 16, 8),
                    Block.createCuboidShape(0, 15, 8, 16, 16, 16),
                    Block.createCuboidShape(3, 0, 11, 5, 15, 13),
                    Block.createCuboidShape(11, 5, 11, 13, 13, 13),
                    Block.createCuboidShape(3, 5, 3, 5, 13, 5),
                    Block.createCuboidShape(11, 0, 3, 13, 7, 5),
                    Block.createCuboidShape(5, 13, 11, 13, 15, 13),
                    Block.createCuboidShape(3, 13, 3, 5, 15, 11),
                    Block.createCuboidShape(5, 5, 3, 11, 7, 5),
                    Block.createCuboidShape(11, 5, 5, 13, 7, 11)
            ).reduce((v1, v2) -> VoxelShapes.combineAndSimplify(v1, v2, BooleanBiFunction.OR)).get();

        } else if (state.get(FACING) == Direction.WEST) {

            return Stream.of(
                    Block.createCuboidShape(0, 7, 8, 8, 8, 16),
                    Block.createCuboidShape(8, 15, 8, 16, 16, 16),
                    Block.createCuboidShape(0, 15, 0, 16, 16, 8),
                    Block.createCuboidShape(11, 0, 3, 13, 15, 5),
                    Block.createCuboidShape(3, 5, 3, 5, 13, 5),
                    Block.createCuboidShape(11, 5, 11, 13, 13, 13),
                    Block.createCuboidShape(3, 0, 11, 5, 7, 13),
                    Block.createCuboidShape(3, 13, 3, 11, 15, 5),
                    Block.createCuboidShape(11, 13, 5, 13, 15, 13),
                    Block.createCuboidShape(5, 5, 11, 11, 7, 13),
                    Block.createCuboidShape(3, 5, 5, 5, 7, 11)
            ).reduce((v1, v2) -> VoxelShapes.combineAndSimplify(v1, v2, BooleanBiFunction.OR)).get();

        } else {

            return VoxelShapes.empty();

        }
    }
}