package wiiu.mavity.who.block.entitytype;

import net.minecraft.block.*;
import net.minecraft.block.entity.*;
import net.minecraft.entity.mob.PiglinBrain;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.screen.NamedScreenHandlerFactory;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import org.jetbrains.annotations.NotNull;

import wiiu.mavity.who.block.WhoBlockEntities;

public class SonicModifierBlock extends BlockWithEntity {

    public SonicModifierBlock(Settings settings) {
        super(settings);
    }

    @Override
    public BlockEntity createBlockEntity(BlockPos pos, BlockState state) {
        return new SonicModifierBlockEntity(pos, state);
    }

    @Override
    public BlockRenderType getRenderType(BlockState state) {
        return BlockRenderType.MODEL;
    }

    @Override
    public <T extends BlockEntity> BlockEntityTicker<T> getTicker(@NotNull World world, BlockState state, BlockEntityType<T> type) {
        return world.isClient ? null : checkType(type, WhoBlockEntities.DEMO_BLOCK_ENTITY, SonicModifierBlockEntity::serverTick);
    }
}