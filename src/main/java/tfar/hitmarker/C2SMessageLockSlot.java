package tfar.hitmarker;

import io.netty.buffer.Unpooled;
import net.fabricmc.fabric.api.network.PacketConsumer;
import net.fabricmc.fabric.api.network.PacketContext;
import net.fabricmc.fabric.api.network.ServerSidePacketRegistry;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.server.network.ServerPlayerEntity;

public class C2SMessageLockSlot implements PacketConsumer {

  public static void send(ServerPlayerEntity player) {
    PacketByteBuf buf = new PacketByteBuf(Unpooled.buffer());
    ServerSidePacketRegistry.INSTANCE.sendToPlayer(player, PacketHandler.sync_hit, buf);
  }

  @Override
  public void accept(PacketContext packetContext, PacketByteBuf packetByteBuf) {
    packetContext.getTaskQueue().execute(HitMarker::playHitMarkerEffect);
  }
}

