package wiiu.mavity.who.mixin;

import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.hud.InGameHud;

import net.minecraft.util.Identifier;

import org.spongepowered.asm.mixin.*;
import org.spongepowered.asm.mixin.injection.*;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(InGameHud.class)
public class InGameHudMixin {

	@Unique
	private final Identifier DALEK_EYESTALK_OVERLAY = new Identifier("textures/misc/powder_snow_outline.png");

	@Shadow
	public void renderOverlay(DrawContext context, Identifier texture, float opacity)  {}

	@Inject(method = "render", at = @At("HEAD"))
	private void render(DrawContext context, float tickDelta, CallbackInfo ci) {
		this.renderOverlay(context, DALEK_EYESTALK_OVERLAY, 0f);
	}
}