package wiiu.mavity.who.entity.model;

import net.minecraft.client.model.*;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.entity.model.*;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.Identifier;

import org.jetbrains.annotations.NotNull;

import wiiu.mavity.who.Who;
import wiiu.mavity.who.entity.entitytype.DalekBeamEntity;

public class DalekBeamEntityModel<T extends DalekBeamEntity> extends EntityModel<DalekBeamEntity> {

	public static final EntityModelLayer LAYER_LOCATION = new EntityModelLayer(new Identifier(Who.MOD_ID, "dalek_beam"), "main");

	private final ModelPart dalek_beam_main;

	public DalekBeamEntityModel(@NotNull ModelPart root) {
		this.dalek_beam_main = root.getChild("dalek_beam_main");
	}

	public static @NotNull TexturedModelData getTexturedModelData() {
		ModelData modelData = new ModelData();
		ModelPartData modelPartData = modelData.getRoot();
		ModelPartData dalek_beam_main = modelPartData.addChild("dalek_beam_main", ModelPartBuilder.create().uv(0, 0).cuboid(-1.0F, 0.0F, -4.0F, 2.0F, 2.0F, 8.0F, new Dilation(0.0F)), ModelTransform.pivot(0.0F, 0.0F, 0.0F));
		return TexturedModelData.of(modelData, 32, 32);
	}

	@Override
	public void setAngles(DalekBeamEntity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
	}

	@Override
	public void render(MatrixStack matrices, VertexConsumer vertexConsumer, int light, int overlay, float red, float green, float blue, float alpha) {
		dalek_beam_main.render(matrices, vertexConsumer, light, overlay, red, green, blue, alpha);
	}
}