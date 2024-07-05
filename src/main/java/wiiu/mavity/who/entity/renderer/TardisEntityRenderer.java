package wiiu.mavity.who.entity.renderer;

import net.fabricmc.api.*;

import net.minecraft.client.render.*;
import net.minecraft.client.render.entity.*;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.Identifier;

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
        VertexConsumer vertexConsumer = vertexConsumers.getBuffer(this.entityModel.getLayer(getTexture(tardis)));
        this.entityModel.render(matrices, vertexConsumer, light, OverlayTexture.DEFAULT_UV, 1.0f, 1.0f, 1.0f, 1.0f);
    }

    @Override
    public Identifier getTexture(TardisEntity tardis) {
        return new Identifier(Who.MOD_ID, "textures/entity/tardis_base.png");
    }
}