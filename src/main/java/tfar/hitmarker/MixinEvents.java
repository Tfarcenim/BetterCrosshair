package tfar.hitmarker;

import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.DrawableHelper;
import net.minecraft.client.gui.hud.InGameHud;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.Identifier;

public class MixinEvents {

    public static void postRenderCrosshair(MatrixStack matrices, MinecraftClient client, int scaledWidth, int scaledHeight) {
        if (HitMarker.remainingTicks > 0) {
            client.getTextureManager().bindTexture(new Identifier(HitMarker.MODID, "textures/hit.png"));

            DrawableHelper.drawTexture(matrices, (scaledWidth - 11) / 2, (scaledHeight - 11) / 2, 0, 0, 11, 11, 11, 11);
            client.getTextureManager().bindTexture(InGameHud.GUI_ICONS_TEXTURE);
        }
    }
}
