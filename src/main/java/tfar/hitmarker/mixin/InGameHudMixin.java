package tfar.hitmarker.mixin;

import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.DrawableHelper;
import net.minecraft.client.gui.hud.InGameHud;
import net.minecraft.client.util.math.MatrixStack;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import tfar.hitmarker.MixinEvents;

@Mixin(InGameHud.class)
public class InGameHudMixin extends DrawableHelper {
	@Shadow @Final private MinecraftClient client;

	@Shadow private int scaledHeight;

	@Shadow private int scaledWidth;

	@Inject(at = @At(value = "RETURN"), method = "renderCrosshair")
	private void drawHit(MatrixStack matrices, CallbackInfo ci) {
		MixinEvents.postRenderCrosshair(matrices,client,scaledWidth,scaledHeight);
	}
}
