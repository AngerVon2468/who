package wiiu.mavity.who.entity.renderer;

import net.fabricmc.api.*;

import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.client.render.*;
import net.minecraft.client.render.entity.*;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.RotationAxis;

import org.jetbrains.annotations.NotNull;

import wiiu.mavity.who.Who;
import wiiu.mavity.who.entity.entitytype.TardisEntity;
import wiiu.mavity.who.entity.model.TardisEntityModel;

@Environment(EnvType.CLIENT)
public class TardisEntityRenderer extends EntityRenderer<TardisEntity> {

    private final TardisEntityModel<TardisEntity> entityModel;

    public TardisEntityRenderer(EntityRendererFactory.Context ctx) {
        super(ctx);
        this.entityModel = new TardisEntityModel<>(ctx.getPart(TardisEntityModel.LAYER_LOCATION));
    }

    @Override
    public void render(@NotNull TardisEntity tardis, float yaw, float tickDelta, @NotNull MatrixStack matrices, @NotNull VertexConsumerProvider vertexConsumers, int light) {
        super.render(tardis, yaw, tickDelta, matrices, vertexConsumers, light);

        // Directional Facing
        if (tardis.getHorizontalFacing() == Direction.NORTH || tardis.getHorizontalFacing() == Direction.SOUTH) {

            matrices.multiply(RotationAxis.POSITIVE_Y.rotationDegrees(tardis.getYaw()));

        } else if (tardis.getHorizontalFacing() == Direction.EAST || tardis.getHorizontalFacing() == Direction.WEST) {

            matrices.multiply(RotationAxis.POSITIVE_Y.rotationDegrees(-tardis.getYaw()));

        }

        // Fix for accidental y axis inversion.
        matrices.multiply(RotationAxis.POSITIVE_X.rotationDegrees(180f));
        matrices.translate(0, -1.5, 0);

        // Off-ground rotation movement
        BlockPos pos = tardis.getBlockPos().offset(Direction.DOWN, 1);
        BlockState state = tardis.getWorld().getBlockState(pos);
        if (state.isOf(Blocks.AIR)) {
            double offset = Math.sin((tardis.getWorld().getTime() + tickDelta) / 8.0) / 4.0;
            // Move the entity
            matrices.translate(0, -1 - offset, 0);
            // Rotate the entity
            matrices.multiply(RotationAxis.POSITIVE_Y.rotationDegrees((tardis.getWorld().getTime() + tickDelta) * 4));
        }

        VertexConsumer vertexConsumer = vertexConsumers.getBuffer(this.entityModel.getLayer(getTexture(tardis)));
        this.entityModel.render(matrices, vertexConsumer, light, OverlayTexture.DEFAULT_UV, 1.0f, 1.0f, 1.0f, 1.0f);
    }

    @Override
    public Identifier getTexture(TardisEntity tardis) {
        return new Identifier(Who.MOD_ID, "textures/entity/tardis_main.png");
    }
}