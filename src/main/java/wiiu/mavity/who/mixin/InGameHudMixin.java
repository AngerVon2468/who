package wiiu.mavity.who.mixin;

import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.hud.InGameHud;
import net.minecraft.util.Identifier;

import org.spongepowered.asm.mixin.*;
import org.spongepowered.asm.mixin.injection.*;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import wiiu.mavity.who.Who;

@Mixin(InGameHud.class)
public abstract class InGameHudMixin {

	@Unique
	private final Identifier DALEK_EYESTALK_OVERLAY = new Identifier(Who.MOD_ID, "textures/overlay/new_dalek_eye_v2.png");

	@Shadow
	public void renderOverlay(DrawContext context, Identifier texture, float opacity)  {}

	@Inject(method = "render", at = @At("HEAD"))
	private void render(DrawContext context, float tickDelta, CallbackInfo ci) {
		this.renderOverlay(context, DALEK_EYESTALK_OVERLAY, 0f);
	}
}