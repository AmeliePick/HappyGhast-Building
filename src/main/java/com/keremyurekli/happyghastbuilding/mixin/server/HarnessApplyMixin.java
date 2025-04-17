package com.keremyurekli.happyghastbuilding.mixin.server;


import com.keremyurekli.happyghastbuilding.helpers.GhastHelper;
import com.keremyurekli.happyghastbuilding.networking.GhastAddedPayload;
import com.keremyurekli.happyghastbuilding.networking.GhastRemovedPayload;
import com.keremyurekli.happyghastbuilding.networking.NetworkingManager;
import com.keremyurekli.happyghastbuilding.helpers.ScreenHelper;
import com.keremyurekli.happyghastbuilding.weirdstuff.GhastInfo;
import net.minecraft.class_11187;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.UUID;

@Mixin(class_11187.class)
public class HarnessApplyMixin {
//serverside
    @Inject(method = "interactMob", at = @At(value = "RETURN",ordinal = 1))
    private void applyHarness(PlayerEntity player, Hand hand, CallbackInfoReturnable<ActionResult> cir) {
        if (!player.getWorld().isClient()) {
            ItemStack itemStack = ((class_11187)(Object)this).getEquippedStack(EquipmentSlot.BODY);
            String translationKey = itemStack.getItem().getTranslationKey();
//            player.sendMessage(Text.of(translationKey),false);
            if (translationKey.contains("happyghastbuilding") && translationKey.contains("harness")) {
//                player.sendMessage(Text.of("Adding2"),false);
//                Constant.INFO_LIST.put(((Entity)(Object)this).getUuid(),null);
                GhastInfo info = GhastHelper.addNewGhast(((Entity)(Object)this).getUuid());
                ScreenHelper.ghastAdded(info.ghastUUID,player.getWorld());

//                player.getServer().sendMessage(Text.of("ADDING GHAST TO LIST"));
                GhastAddedPayload payload = new GhastAddedPayload(info.ghastUUID);
                player.getServer().getPlayerManager().getPlayerList().forEach(serverPlayerEntity -> {
                    NetworkingManager.sendGhastAddedPayload(serverPlayerEntity,payload);
//                    player.getServer().sendMessage(Text.of("sent to "+serverPlayerEntity.getName()));
                });
            }
        }
    }
    @Inject(method = "interactMob", at = @At(value = "RETURN",ordinal = 2))
    private void removeHarness(PlayerEntity player, Hand hand, CallbackInfoReturnable<ActionResult> cir) {
        if (!player.getWorld().isClient()) {
            ItemStack itemStack = ((class_11187)(Object)this).getEquippedStack(EquipmentSlot.BODY);
            if (itemStack.isEmpty()) {
//                player.sendMessage(Text.of("removing"),false);
                UUID uuid = ((Entity)(Object)this).getUuid();




                GhastHelper.dropItems(((Entity)(Object)this));



                GhastHelper.removeGhast(uuid,1);
                ScreenHelper.ghastRemoved(uuid,player.getWorld());

                GhastRemovedPayload payload = new GhastRemovedPayload(uuid);
                player.getServer().getPlayerManager().getPlayerList().forEach(serverPlayerEntity -> {
                    NetworkingManager.sendGhastRemovedPayload(serverPlayerEntity,payload);
                });
            }
        }
    }

}
