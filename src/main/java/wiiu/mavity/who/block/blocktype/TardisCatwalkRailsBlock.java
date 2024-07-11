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

public class TardisCatwalkRailsBlock extends Block {

    public static final DirectionProperty FACING = HorizontalFacingBlock.FACING;

    public TardisCatwalkRailsBlock(Settings settings) {
        super(settings);
        this.setDefaultState(this.getStateManager().getDefaultState().with(FACING, Direction.NORTH));
    }

    @Override
    public @Nullable BlockState getPlacementState(@NotNull ItemPlacementContext ctx) {
        return this.getDefaultState().with(FACING, ctx.getHorizontalPlayerFacing());
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
                    Block.createCuboidShape(7, 0, 0, 9, 14, 1),
                    Block.createCuboidShape(0, 12, -1, 16, 14, 0),
                    Block.createCuboidShape(0, 6, -1, 16, 8, 0)
            ).reduce((v1, v2) -> VoxelShapes.combineAndSimplify(v1, v2, BooleanBiFunction.OR)).get();

        } else if (state.get(FACING) == Direction.SOUTH) {

            return Stream.of(
                    Block.createCuboidShape(7, 0, 15, 9, 14, 16),
                    Block.createCuboidShape(0, 12, 16, 16, 14, 17),
                    Block.createCuboidShape(0, 6, 16, 16, 8, 17)
            ).reduce((v1, v2) -> VoxelShapes.combineAndSimplify(v1, v2, BooleanBiFunction.OR)).get();

        } else if (state.get(FACING) == Direction.EAST) {

            return Stream.of(
                    Block.createCuboidShape(15, 0, 7, 16, 14, 9),
                    Block.createCuboidShape(16, 12, 0, 17, 14, 16),
                    Block.createCuboidShape(16, 6, 0, 17, 8, 16)
            ).reduce((v1, v2) -> VoxelShapes.combineAndSimplify(v1, v2, BooleanBiFunction.OR)).get();

        } else if (state.get(FACING) == Direction.WEST) {

            return Stream.of(
                    Block.createCuboidShape(0, 0, 7, 1, 14, 9),
                    Block.createCuboidShape(-1, 12, 0, 0, 14, 16),
                    Block.createCuboidShape(-1, 6, 0, 0, 8, 16)
            ).reduce((v1, v2) -> VoxelShapes.combineAndSimplify(v1, v2, BooleanBiFunction.OR)).get();

        } else {

            return VoxelShapes.empty();

        }
    }
}