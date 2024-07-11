package wiiu.mavity.who.block.blocktype;

import net.minecraft.block.*;
import net.minecraft.util.math.*;
import net.minecraft.world.BlockView;
import net.minecraft.util.shape.*;

import org.jetbrains.annotations.*;

public class TardisCatwalkBlock extends Block {

    public TardisCatwalkBlock(Settings settings) {
        super(settings);
    }

    @Override
    @SuppressWarnings("deprecation")
    public VoxelShape getOutlineShape(@NotNull BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
        return Block.createCuboidShape(0, 15, 0, 16, 16, 16);
    }
}