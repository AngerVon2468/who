package wiiu.mavity.who.mixin;

import com.mojang.blaze3d.systems.RenderSystem;

import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.hud.InGameHud;
import net.minecraft.util.Identifier;

import org.jetbrains.annotations.NotNull;

import org.spongepowered.asm.mixin.*;
import org.spongepowered.asm.mixin.injection.*;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import wiiu.mavity.who.Who;

@Mixin(InGameHud.class)
public abstract class InGameHudMixin {

	@Shadow
	private int scaledWidth;

	@Shadow
	private int scaledHeight;

	@Unique
	private final Identifier DALEK_EYESTALK_OVERLAY = new Identifier(Who.MOD_ID, "textures/overlay/dalek_eyestalk_overlay.png");

	@Shadow
	public void renderOverlay(DrawContext context, Identifier texture, float opacity)  {}

	@Inject(method = "render", at = @At("HEAD"))
	private void render(DrawContext context, float tickDelta, CallbackInfo ci) {
		this.renderOverlayButBetter(context, DALEK_EYESTALK_OVERLAY, 5f);
	}

	@Unique
	public final void renderOverlayButBetter(@NotNull DrawContext context, Identifier texture, float opacity) {
		RenderSystem.disableDepthTest();
		RenderSystem.depthMask(false);
		context.setShaderColor(1.0f, 1.0f, 1.0f, opacity);
		context.drawTexture(texture, 0, 0, -90, 0.0f, 0.0f, this.scaledWidth, this.scaledHeight, this.scaledWidth, this.scaledHeight);
		context.setShaderColor(1.0f, 1.0f, 1.0f, 1.0f);
	}
}