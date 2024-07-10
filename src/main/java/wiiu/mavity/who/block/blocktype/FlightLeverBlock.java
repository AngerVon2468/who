package wiiu.mavity.who.block.blocktype;

import net.minecraft.block.*;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.*;
import net.minecraft.util.*;
import net.minecraft.util.math.Direction;

import org.jetbrains.annotations.*;

public class FlightLeverBlock extends Block {

    public static final BooleanProperty PULLED = BooleanProperty.of("pulled");

    public static final DirectionProperty FACING = HorizontalFacingBlock.FACING;

    public FlightLeverBlock(Settings settings) {
        super(settings);
        this.setDefaultState(this.getStateManager().getDefaultState().with(FACING, Direction.NORTH).with(PULLED, false));
    }

    @Override
    public @Nullable BlockState getPlacementState(@NotNull ItemPlacementContext ctx) {
        return this.getDefaultState().with(FACING, ctx.getHorizontalPlayerFacing()).with(PULLED, false);
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
}