package tfar.hitmarker;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.event.client.ClientTickCallback;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.sound.PositionedSoundInstance;
import net.minecraft.sound.SoundEvent;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public class HitMarker implements ModInitializer, ClientModInitializer {

	public static final String MODID = "hitmarker";

	public static int remainingTicks = 0;

	public static SoundEvent HIT;

	@Override
	public void onInitialize() {
		HIT = Registry.register(Registry.SOUND_EVENT, new Identifier(MODID,"hit"), new SoundEvent(new Identifier(MODID,"hit")));
	}

	@Override
	public void onInitializeClient() {
		ClientTickCallback.EVENT.register((minecraftClient -> {
			if (remainingTicks > 0) {
				remainingTicks--;
			}
		}));
		PacketHandler.registerClientMessages();
	}

	public static void playHitMarkerEffect() {
		HitMarker.remainingTicks = 20;
		MinecraftClient.getInstance().getSoundManager().play(PositionedSoundInstance.master(HitMarker.HIT, 1.0F));
	}
}
