package wiiu.mavity.who.entity.renderer;

import net.fabricmc.api.*;

import net.minecraft.client.render.*;
import net.minecraft.client.render.entity.*;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.RotationAxis;

import org.jetbrains.annotations.NotNull;

import wiiu.mavity.who.Who;
import wiiu.mavity.who.entity.entitytype.TimeRotorEntity;
import wiiu.mavity.who.entity.model.TimeRotorEntityModel;

@Environment(EnvType.CLIENT)
public class TimeRotorEntityRenderer extends EntityRenderer<TimeRotorEntity> {

    private final TimeRotorEntityModel<TimeRotorEntity> entityModel;

    public TimeRotorEntityRenderer(EntityRendererFactory.Context ctx) {
        super(ctx);
        this.entityModel = new TimeRotorEntityModel<>(ctx.getPart(TimeRotorEntityModel.LAYER_LOCATION));
    }

    @Override
    public void render(@NotNull TimeRotorEntity timeRotor, float yaw, float tickDelta, @NotNull MatrixStack matrices, @NotNull VertexConsumerProvider vertexConsumers, int light) {
        super.render(timeRotor, yaw, tickDelta, matrices, vertexConsumers, light);
        matrices.multiply(RotationAxis.POSITIVE_Y.rotationDegrees(timeRotor.getYaw()));
        matrices.multiply(RotationAxis.POSITIVE_X.rotationDegrees(-timeRotor.getPitch()));
        // Calculate the current offset in the y value
        double offset = Math.sin((timeRotor.getWorld().getTime() + tickDelta) / 8.0) / 4.0;
        // Move the item
        matrices.translate(0, 0.25 + offset, 0);
        VertexConsumer vertexConsumer = vertexConsumers.getBuffer(this.entityModel.getLayer(getTexture(timeRotor)));
        this.entityModel.render(matrices, vertexConsumer, light, OverlayTexture.DEFAULT_UV, 1.0f, 1.0f, 1.0f, 1.0f);
    }

    @Override
    public Identifier getTexture(TimeRotorEntity timeRotor) {
        return new Identifier(Who.MOD_ID, "textures/entity/time_rotor.png");
    }
}