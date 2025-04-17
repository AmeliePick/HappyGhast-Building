package com.keremyurekli.happyghastbuilding.mixin.server;

import com.keremyurekli.happyghastbuilding.Constant;
import com.keremyurekli.happyghastbuilding.helpers.GhastHelper;
import com.keremyurekli.happyghastbuilding.networking.GhastRemovedPayload;
import com.keremyurekli.happyghastbuilding.networking.NetworkingManager;
import com.keremyurekli.happyghastbuilding.weirdstuff.GhastInfo;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.damage.DamageSource;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.UUID;

@Mixin(LivingEntity.class)
public abstract class GhastDieMixin {

    @Shadow
    protected boolean dead;


    @Inject(method = "onDeath", at = @At(value = "HEAD"))
    private void onDeath(DamageSource damageSource, CallbackInfo ci) {
        LivingEntity le = (LivingEntity) (Object) this;
        if (le.isRemoved() || dead) {
            return;
        }
        if (!le.getWorld().isClient()) {
//            Constant.LOGGER.info(String.valueOf(Constant.INFO_LIST.size()));
            UUID uuid = le.getUuid();
            if (Constant.INFO_LIST.containsKey(uuid)) {

                GhastInfo info = Constant.INFO_LIST.get(uuid);

                GhastHelper.dropItems(le);

                GhastHelper.removeGhast(uuid,1);
//                ScreenHelper.ghastRemoved(uuid,le.getWorld());

                GhastRemovedPayload payload = new GhastRemovedPayload(uuid);
                le.getWorld().getServer().getPlayerManager().getPlayerList().forEach(serverPlayerEntity -> {
                    NetworkingManager.sendGhastRemovedPayload(serverPlayerEntity,payload);
                });
//                GhastHelper.removeGhast(uuid);
//                ScreenHelper.ghastRemoved(uuid,le.getWorld());//no need to do that in client side
//
//                GhastRemovedPayload payload = new GhastRemovedPayload(uuid);
//                le.getWorld().getServer().getPlayerManager().getPlayerList().forEach(serverPlayerEntity -> {
//                    NetworkingManager.sendGhastRemovedPayload(serverPlayerEntity,payload);
//                });
//            Constant.LOGGER.info(String.valueOf(Constant.INFO_LIST.size()));
            }
        }
    }
}
