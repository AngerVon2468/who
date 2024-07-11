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

public class TardisCatwalkStepsBlock extends Block {

    public static final DirectionProperty FACING = HorizontalFacingBlock.FACING;

    public TardisCatwalkStepsBlock(Settings settings) {
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
                    Block.createCuboidShape(0, 15, 8, 16, 16, 16),
                    Block.createCuboidShape(0, 7, 0, 16, 8, 8),
                    Block.createCuboidShape(7, 0, 11, 9, 15, 13),
                    Block.createCuboidShape(7, 5, 1, 9, 7, 11)
            ).reduce((v1, v2) -> VoxelShapes.combineAndSimplify(v1, v2, BooleanBiFunction.OR)).get();

        } else if (state.get(FACING) == Direction.SOUTH) {

            return Stream.of(
                    Block.createCuboidShape(0, 15, 0, 16, 16, 8),
                    Block.createCuboidShape(0, 7, 8, 16, 8, 16),
                    Block.createCuboidShape(7, 0, 3, 9, 15, 5),
                    Block.createCuboidShape(7, 5, 5, 9, 7, 15)
            ).reduce((v1, v2) -> VoxelShapes.combineAndSimplify(v1, v2, BooleanBiFunction.OR)).get();

        } else if (state.get(FACING) == Direction.EAST) {

            return Stream.of(
                    Block.createCuboidShape(0, 15, 0, 8, 16, 16),
                    Block.createCuboidShape(8, 7, 0, 16, 8, 16),
                    Block.createCuboidShape(3, 0, 7, 5, 15, 9),
                    Block.createCuboidShape(5, 5, 7, 15, 7, 9)
            ).reduce((v1, v2) -> VoxelShapes.combineAndSimplify(v1, v2, BooleanBiFunction.OR)).get();

        } else if (state.get(FACING) == Direction.WEST) {

            return Stream.of(
                    Block.createCuboidShape(8, 15, 0, 16, 16, 16),
                    Block.createCuboidShape(0, 7, 0, 8, 8, 16),
                    Block.createCuboidShape(11, 0, 7, 13, 15, 9),
                    Block.createCuboidShape(1, 5, 7, 11, 7, 9)
            ).reduce((v1, v2) -> VoxelShapes.combineAndSimplify(v1, v2, BooleanBiFunction.OR)).get();

        } else {

            return VoxelShapes.empty();

        }
    }
}