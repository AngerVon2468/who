package wiiu.mavity.who.block.blocktype;

import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.WorldRenderer;
import net.minecraft.client.render.block.entity.*;
import net.minecraft.client.render.item.ItemRenderer;
import net.minecraft.client.render.model.json.ModelTransformationMode;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.item.*;
import net.minecraft.util.math.RotationAxis;

import org.jetbrains.annotations.NotNull;

public class TimeRotorBlockEntityRenderer implements BlockEntityRenderer<TimeRotorBlockEntity> {

    private static final ItemStack STACK = new ItemStack(Items.JUKEBOX, 1);

    private final ItemRenderer itemRenderer;

    public TimeRotorBlockEntityRenderer(BlockEntityRendererFactory. @NotNull Context ctx) {
        itemRenderer = ctx.getItemRenderer();
    }

    @Override
    public void render(@NotNull TimeRotorBlockEntity blockEntity, float tickDelta, @NotNull MatrixStack matrices, VertexConsumerProvider vertexConsumers, int light, int overlay) {
        // Mandatory when doing GL calls
        matrices.push();
        // Calculate the current offset in the y value
        double offset = Math.sin((blockEntity.getWorld().getTime() + tickDelta) / 8.0) / 4.0;
        // Move the item
        matrices.translate(0.5, 1.25 + offset, 0.5);

        // Rotate the item
        matrices.multiply(RotationAxis.POSITIVE_Y.rotationDegrees((blockEntity.getWorld().getTime() + tickDelta) * 4));
        int lightAbove = WorldRenderer.getLightmapCoordinates(blockEntity.getWorld(), blockEntity.getPos().up());
        this.itemRenderer.renderItem(STACK, ModelTransformationMode.GROUND, lightAbove, overlay, matrices, vertexConsumers, blockEntity.getWorld(), 0);

        // Mandatory call after GL calls
        matrices.pop();
    }
}