package wiiu.mavity.who.entity.model;

import net.fabricmc.api.*;

import net.minecraft.client.model.*;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.entity.model.*;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.Identifier;

import org.jetbrains.annotations.NotNull;

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

	public TardisEntityModel(@NotNull ModelPart root) {
		this.tardis_main = root.getChild("tardis_main");
	}

	public static @NotNull TexturedModelData getTexturedModelData() {
		ModelData modelData = new ModelData();
		ModelPartData modelPartData = modelData.getRoot();
		ModelPartData tardis_main = modelPartData.addChild("tardis_main", ModelPartBuilder.create().uv(0, 0).cuboid(-8.0F, -34.0F, -9.0F, 8.0F, 32.0F, 1.0F, new Dilation(0.0F))
		.uv(0, 39).cuboid(-11.0F, -2.0F, -11.0F, 6.0F, 2.0F, 22.0F, new Dilation(0.0F))
		.uv(18, 0).cuboid(0.0F, -34.0F, -9.0F, 8.0F, 32.0F, 1.0F, new Dilation(0.0F))
		.uv(56, 0).cuboid(8.0F, -34.0F, -10.0F, 2.0F, 32.0F, 2.0F, new Dilation(0.0F))
		.uv(0, 40).cuboid(-5.0F, -2.0F, -11.0F, 10.0F, 2.0F, 22.0F, new Dilation(0.0F))
		.uv(56, 0).cuboid(-10.0F, -34.0F, 8.0F, 2.0F, 32.0F, 2.0F, new Dilation(0.0F))
		.uv(56, 0).cuboid(-10.0F, -34.0F, -10.0F, 2.0F, 32.0F, 2.0F, new Dilation(0.0F))
		.uv(56, 0).cuboid(8.0F, -34.0F, 8.0F, 2.0F, 32.0F, 2.0F, new Dilation(0.0F))
		.uv(6, 50).cuboid(-6.0F, -39.0F, -6.0F, 12.0F, 1.0F, 12.0F, new Dilation(0.0F))
		.uv(0, 39).cuboid(5.0F, -2.0F, -11.0F, 6.0F, 2.0F, 22.0F, new Dilation(0.0F))
		.uv(2, 39).cuboid(5.0F, -37.0F, -11.0F, 6.0F, 3.0F, 22.0F, new Dilation(0.0F))
		.uv(0, 39).cuboid(-5.0F, -37.0F, -11.0F, 10.0F, 3.0F, 22.0F, new Dilation(0.0F))
		.uv(0, 39).cuboid(-11.0F, -37.0F, -11.0F, 6.0F, 3.0F, 22.0F, new Dilation(0.0F))
		.uv(0, 33).cuboid(-8.0F, -36.0F, -12.0F, 17.0F, 2.0F, 1.0F, new Dilation(0.0F))
		.uv(48, 37).cuboid(-2.0F, -43.0F, -2.0F, 4.0F, 4.0F, 4.0F, new Dilation(0.0F))
		.uv(40, 55).cuboid(-3.0F, -44.0F, -3.0F, 6.0F, 1.0F, 6.0F, new Dilation(0.0F))
		.uv(48, 57).cuboid(-2.0F, -45.0F, -2.0F, 4.0F, 1.0F, 4.0F, new Dilation(0.0F))
		.uv(2, 48).cuboid(-7.0F, -38.0F, -7.0F, 14.0F, 1.0F, 14.0F, new Dilation(0.0F)), ModelTransform.pivot(0.0F, 24.0F, 0.0F));

		tardis_main.addChild("sign4_r1", ModelPartBuilder.create().uv(0, 33).cuboid(-16.0F, -2.0F, -1.0F, 17.0F, 2.0F, 1.0F, new Dilation(0.0F)), ModelTransform.of(-11.0F, -34.0F, -8.0F, 0.0F, 1.5708F, 0.0F));
		tardis_main.addChild("sign2_r1", ModelPartBuilder.create().uv(0, 33).cuboid(-16.0F, -2.0F, -1.0F, 17.0F, 2.0F, 1.0F, new Dilation(0.0F)), ModelTransform.of(11.0F, -34.0F, 7.0F, 0.0F, -1.5708F, 0.0F));
		tardis_main.addChild("sign3_r1", ModelPartBuilder.create().uv(0, 33).cuboid(-16.0F, -2.0F, -1.0F, 17.0F, 2.0F, 1.0F, new Dilation(0.0F)), ModelTransform.of(-8.0F, -34.0F, 11.0F, 0.0F, 3.1416F, 0.0F));
		tardis_main.addChild("panel4_r1", ModelPartBuilder.create().uv(36, 0).cuboid(-7.0F, -32.0F, -1.0F, 8.0F, 32.0F, 1.0F, new Dilation(0.0F)), ModelTransform.of(-7.0F, -2.0F, 8.0F, 0.0F, 3.1416F, 0.0F));
		tardis_main.addChild("panel3_r1", ModelPartBuilder.create().uv(36, 0).cuboid(-7.0F, -32.0F, -1.0F, 8.0F, 32.0F, 1.0F, new Dilation(0.0F)), ModelTransform.of(1.0F, -2.0F, 8.0F, 0.0F, 3.1416F, 0.0F));
		tardis_main.addChild("panel6_r1", ModelPartBuilder.create().uv(36, 0).cuboid(-7.0F, -32.0F, -1.0F, 8.0F, 32.0F, 1.0F, new Dilation(0.0F)), ModelTransform.of(-8.0F, -2.0F, -7.0F, 0.0F, 1.5708F, 0.0F));
		tardis_main.addChild("panel5_r1", ModelPartBuilder.create().uv(36, 0).cuboid(-7.0F, -32.0F, -1.0F, 8.0F, 32.0F, 1.0F, new Dilation(0.0F)), ModelTransform.of(-8.0F, -2.0F, 1.0F, 0.0F, 1.5708F, 0.0F));
		tardis_main.addChild("panel2_r1", ModelPartBuilder.create().uv(36, 0).cuboid(-7.0F, -32.0F, -1.0F, 8.0F, 32.0F, 1.0F, new Dilation(0.0F)), ModelTransform.of(8.0F, -2.0F, 7.0F, 0.0F, -1.5708F, 0.0F));
		tardis_main.addChild("panel1_r1", ModelPartBuilder.create().uv(36, 0).cuboid(-7.0F, -32.0F, -1.0F, 8.0F, 32.0F, 1.0F, new Dilation(0.0F)), ModelTransform.of(8.0F, -2.0F, -1.0F, 0.0F, -1.5708F, 0.0F));
		return TexturedModelData.of(modelData, 64, 64);
	}

	@Override
	public void setAngles(TardisEntity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
	}

	@Override
	public void render(MatrixStack matrices, VertexConsumer vertexConsumer, int light, int overlay, float red, float green, float blue, float alpha) {
		tardis_main.render(matrices, vertexConsumer, light, overlay, red, green, blue, alpha);
	}
}