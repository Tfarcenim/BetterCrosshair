package tfar.hitmarker.mixin;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.projectile.ProjectileEntity;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.hit.EntityHitResult;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import tfar.hitmarker.C2SMessageLockSlot;

@Mixin(ProjectileEntity.class)
abstract class ProjectileEntityMixin extends Entity {

    public ProjectileEntityMixin(EntityType<?> type, World world) {
        super(type, world);
    }

    @Inject(method = "onEntityHit",at = @At("RETURN"))
    private void sendHitToPlayer(EntityHitResult entityHitResult, CallbackInfo ci) {
        if (((ProjectileEntityAccessor)this).getOwnerUuid() != null && !world.isClient) {
            Entity entity = ((ServerWorld)this.world).getEntity(((ProjectileEntityAccessor)this).getOwnerUuid());
            if (entity instanceof ServerPlayerEntity) {
                C2SMessageLockSlot.send((ServerPlayerEntity) entity);
            }
        }
    }
}
