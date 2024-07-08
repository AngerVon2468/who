package wiiu.mavity.who.entity.renderer;

import net.fabricmc.api.*;

import net.minecraft.client.render.*;
import net.minecraft.client.render.entity.*;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.RotationAxis;

import org.jetbrains.annotations.NotNull;

import wiiu.mavity.who.Who;
import wiiu.mavity.who.entity.entitytype.DalekBeamEntity;
import wiiu.mavity.who.entity.model.DalekBeamEntityModel;

@Environment(EnvType.CLIENT)
public class DalekBeamEntityRenderer extends EntityRenderer<DalekBeamEntity> {

    private final DalekBeamEntityModel<DalekBeamEntity> entityModel;

    public DalekBeamEntityRenderer(EntityRendererFactory.Context ctx) {
        super(ctx);
        this.entityModel = new DalekBeamEntityModel<>(ctx.getPart(DalekBeamEntityModel.LAYER_LOCATION));
    }

    @Override
    public void render(@NotNull DalekBeamEntity dalekBeam, float yaw, float tickDelta, @NotNull MatrixStack matrices, @NotNull VertexConsumerProvider vertexConsumers, int light) {
        super.render(dalekBeam, yaw, tickDelta, matrices, vertexConsumers, light);
        matrices.multiply(RotationAxis.POSITIVE_Y.rotationDegrees(dalekBeam.getYaw()));
        matrices.multiply(RotationAxis.POSITIVE_Z.rotationDegrees(dalekBeam.getPitch()));
        VertexConsumer vertexConsumer = vertexConsumers.getBuffer(this.entityModel.getLayer(getTexture(dalekBeam)));
        this.entityModel.render(matrices, vertexConsumer, light, OverlayTexture.DEFAULT_UV, 1.0f, 1.0f, 1.0f, 1.0f);
    }

    @Override
    public Identifier getTexture(DalekBeamEntity dalekBeam) {
        return new Identifier(Who.MOD_ID, "textures/entity/dalek_beam.png");
    }
}