package wiiu.mavity.who.entity.model;

import net.fabricmc.api.*;

import net.minecraft.client.model.*;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.entity.model.*;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.Identifier;

import org.jetbrains.annotations.NotNull;

import wiiu.mavity.who.Who;
import wiiu.mavity.who.entity.entitytype.TimeRotorEntity;

/**
 * {@link TimeRotorEntityModel<T>}<br>
 * This link is to avoid a stupid warning that I can't be stuffed to remove.
 */
@Environment(EnvType.CLIENT)
public class TimeRotorEntityModel<T extends TimeRotorEntity> extends EntityModel<TimeRotorEntity> {

	public static final EntityModelLayer LAYER_LOCATION = new EntityModelLayer(new Identifier(Who.MOD_ID, "time_rotor"), "main");

	private final ModelPart time_rotor;

	public TimeRotorEntityModel(@NotNull ModelPart root) {
		this.time_rotor = root.getChild("time_rotor");
	}

	public static @NotNull TexturedModelData getTexturedModelData() {
		ModelData modelData = new ModelData();
		ModelPartData modelPartData = modelData.getRoot();
		ModelPartData time_rotor = modelPartData.addChild("time_rotor", ModelPartBuilder.create().uv(0, 0).cuboid(-8.0f, 0.0f, -8.0f, 16.0f, 14.0f, 16.0f, new Dilation(0.0f))
		.uv(0, 46).cuboid(-5.0f, 0.0f, -5.0f, 10.0f, 8.0f, 10.0f, new Dilation(0.0f)), ModelTransform.pivot(0.0f, 0.0f, 0.0f));
		return TexturedModelData.of(modelData, 64, 64);
	}

	@Override
	public void setAngles(TimeRotorEntity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
	}

	@Override
	public void render(MatrixStack matrices, VertexConsumer vertexConsumer, int light, int overlay, float red, float green, float blue, float alpha) {
		time_rotor.render(matrices, vertexConsumer, light, overlay, red, green, blue, alpha);
	}
}