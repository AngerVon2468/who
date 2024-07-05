package wiiu.mavity.who.entity.model;

import net.fabricmc.api.*;

import net.minecraft.client.model.*;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.entity.model.EntityModel;
import net.minecraft.client.render.entity.model.EntityModelLayer;
import net.minecraft.client.util.math.MatrixStack;

import net.minecraft.util.Identifier;
import wiiu.mavity.who.Who;
import wiiu.mavity.who.entity.entitytype.TardisEntity;

/**
 * {@link TardisEntityModel<T>}<br>
 * This link is to avoid a stupid warning that I can't be stuffed to remove.
 */
@Environment(EnvType.CLIENT)
public class TardisEntityModel<T extends TardisEntity> extends EntityModel<TardisEntity> {

	public static final EntityModelLayer LAYER_LOCATION = new EntityModelLayer(new Identifier(Who.MOD_ID, "tardis"), "main");

	private final ModelPart tardis_main;

	public TardisEntityModel(ModelPart root) {
		this.tardis_main = root.getChild("tardis_main");
	}

	public static TexturedModelData getTexturedModelData() {
		ModelData modelData = new ModelData();
		ModelPartData modelPartData = modelData.getRoot();
		ModelPartData tardis_main = modelPartData.addChild("tardis_main", ModelPartBuilder.create().uv(0, 60).cuboid(-12.0F, -1.0F, -12.0F, 24.0F, 1.0F, 24.0F, new Dilation(0.0F))
		.uv(0, 0).cuboid(-10.0F, -39.0F, -10.0F, 20.0F, 38.0F, 20.0F, new Dilation(0.0F))
		.uv(56, 34).cuboid(-12.0F, -41.0F, -12.0F, 24.0F, 2.0F, 24.0F, new Dilation(0.0F))
		.uv(72, 60).cuboid(-10.0F, -43.0F, -10.0F, 20.0F, 2.0F, 20.0F, new Dilation(0.0F))
		.uv(60, 0).cuboid(-8.0F, -45.0F, -8.0F, 16.0F, 2.0F, 16.0F, new Dilation(0.0F))
		.uv(0, 0).cuboid(-2.0F, -50.0F, -2.0F, 4.0F, 5.0F, 4.0F, new Dilation(0.0F))
		.uv(24, 102).cuboid(-10.0F, -39.0F, -11.0F, 2.0F, 38.0F, 1.0F, new Dilation(0.0F))
		.uv(18, 102).cuboid(8.0F, -39.0F, -11.0F, 2.0F, 38.0F, 1.0F, new Dilation(0.0F))
		.uv(12, 102).cuboid(-10.0F, -39.0F, 10.0F, 2.0F, 38.0F, 1.0F, new Dilation(0.0F))
		.uv(6, 102).cuboid(8.0F, -39.0F, 10.0F, 2.0F, 38.0F, 1.0F, new Dilation(0.0F))
		.uv(0, 102).cuboid(-11.0F, -39.0F, -10.0F, 1.0F, 38.0F, 2.0F, new Dilation(0.0F))
		.uv(98, 99).cuboid(-11.0F, -39.0F, 8.0F, 1.0F, 38.0F, 2.0F, new Dilation(0.0F))
		.uv(92, 99).cuboid(10.0F, -39.0F, -10.0F, 1.0F, 38.0F, 2.0F, new Dilation(0.0F))
		.uv(86, 99).cuboid(10.0F, -39.0F, 8.0F, 1.0F, 38.0F, 2.0F, new Dilation(0.0F))
		.uv(52, 86).cuboid(10.0F, -2.0F, -8.0F, 1.0F, 1.0F, 16.0F, new Dilation(0.0F))
		.uv(34, 85).cuboid(-11.0F, -2.0F, -8.0F, 1.0F, 1.0F, 16.0F, new Dilation(0.0F))
		.uv(80, 22).cuboid(-8.0F, -2.0F, 10.0F, 16.0F, 1.0F, 1.0F, new Dilation(0.0F))
		.uv(80, 20).cuboid(-8.0F, -2.0F, -11.0F, 16.0F, 1.0F, 1.0F, new Dilation(0.0F))
		.uv(60, 18).cuboid(-8.0F, -39.0F, -11.0F, 16.0F, 1.0F, 1.0F, new Dilation(0.0F))
		.uv(0, 85).cuboid(10.0F, -39.0F, -8.0F, 1.0F, 1.0F, 16.0F, new Dilation(0.0F))
		.uv(0, 58).cuboid(-8.0F, -39.0F, 10.0F, 16.0F, 1.0F, 1.0F, new Dilation(0.0F))
		.uv(80, 82).cuboid(-11.0F, -39.0F, -8.0F, 1.0F, 1.0F, 16.0F, new Dilation(0.0F)), ModelTransform.pivot(0.0F, 24.0F, 0.0F));
		return TexturedModelData.of(modelData, 256, 256);
	}

	@Override
	public void setAngles(TardisEntity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
	}

	@Override
	public void render(MatrixStack matrices, VertexConsumer vertexConsumer, int light, int overlay, float red, float green, float blue, float alpha) {
		tardis_main.render(matrices, vertexConsumer, light, overlay, red, green, blue, alpha);
	}
}