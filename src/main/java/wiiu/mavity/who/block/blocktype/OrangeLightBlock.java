package wiiu.mavity.who.block.blocktype;

import net.minecraft.block.*;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.DirectionProperty;
import net.minecraft.util.*;
import net.minecraft.util.math.*;
import net.minecraft.world.BlockView;
import net.minecraft.util.shape.*;

import org.jetbrains.annotations.*;

public class OrangeLightBlock extends Block {

    public static final DirectionProperty FACING = HorizontalFacingBlock.FACING;

    public OrangeLightBlock(Settings settings) {
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

            return Block.createCuboidShape(0, 0, 15, 16, 16, 16);

        } else if (state.get(FACING) == Direction.SOUTH) {

            return Block.createCuboidShape(0, 0, 0, 16, 16, 1);

        } else if (state.get(FACING) == Direction.EAST) {

            return Block.createCuboidShape(0, 0, 0, 1, 16, 16);

        } else if (state.get(FACING) == Direction.WEST) {

            return Block.createCuboidShape(15, 0, 0, 16, 16, 16);

        } else {

            return VoxelShapes.empty();

        }
    }
}