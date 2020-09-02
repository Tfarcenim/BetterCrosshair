package tfar.hitmarker;

import net.fabricmc.fabric.api.network.ClientSidePacketRegistry;
import net.minecraft.util.Identifier;

public class PacketHandler {

  public static final Identifier sync_hit = new Identifier(HitMarker.MODID,"sync_hit");

  public static void registerClientMessages() {
    ClientSidePacketRegistry.INSTANCE.register(sync_hit,new C2SMessageLockSlot());
  }
}
