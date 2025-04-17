package com.keremyurekli.happyghastbuilding.mixin;


import com.keremyurekli.happyghastbuilding.Constant;
import com.keremyurekli.happyghastbuilding.networking.ClickPayload;
import com.keremyurekli.happyghastbuilding.networking.NetworkingManager;
import com.keremyurekli.happyghastbuilding.weirdstuff.BoxWithData;
import com.keremyurekli.happyghastbuilding.weirdstuff.GhastInfo;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.fabricmc.fabric.api.networking.v1.PacketByteBufs;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.network.ClientPlayerEntity;
import net.minecraft.client.world.ClientWorld;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.text.Text;
import net.minecraft.util.math.Box;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.*;
import java.util.concurrent.atomic.AtomicReference;


@Environment(EnvType.CLIENT)
@Mixin(MinecraftClient.class)
public abstract class RightClickMixin {

    @Shadow @Nullable public ClientPlayerEntity player;


    @Shadow @Nullable public ClientWorld world;

    @Inject(method = "doItemUse", at = @At("HEAD"),cancellable = true)
    private void onRightClick(CallbackInfo ci) {
        MinecraftClient client = MinecraftClient.getInstance();

        if (client.crosshairTarget == null || client.player == null || client.world == null)
            return;
        List<BoxWithData> boxes = new ArrayList<>();
        Constant.INFO_LIST.keySet().forEach(key -> {
            GhastInfo value = Constant.INFO_LIST.get(key);
            boxes.addAll(value.boxes);
        });

        Vec3d lookDirection = player.getRotationVec(0).multiply(3);
//        Vec3d lookDirection = getThirdBlockInDirection(player);
        BoxWithData lastBox = null;
        double distance = -1;
        boolean foundAny = false;

//        player.sendMessage(Text.of("Trying from "+player.getEyePos()+" TO"+lookDirection.add(player.getEyePos())),false);
        for (int i = 0; i < boxes.size(); i++) {
            BoxWithData b = boxes.get(i);
            Optional<Vec3d> zanzibar = b.raycast(player.getEyePos(),player.getEyePos().add(lookDirection));
            if (zanzibar.isPresent()) {
                foundAny = true;

                if (lastBox != null) {
                    double sqd = player.getPos().squaredDistanceTo(b.getCenter());
                    if (sqd < distance) {
//                    world.addParticleClient(ParticleTypes.ANGRY_VILLAGER, zanzibar.get().x, zanzibar.get().y, zanzibar.get().z,
//                            0, 0.1, 0);
                        lastBox = b;
                        distance = sqd;
                    }
                } else {
                    double sqd = player.getPos().squaredDistanceTo(b.getCenter());
                    lastBox = b;
                    distance = sqd;
                }


            } else {
//                Constant.LOGGER.info("NOT PRESENT");
            }
        }
        if (foundAny) {
//            player.sendMessage(Text.of("Clicked to "+lastBox.action.label),true);
            NetworkingManager.sendRightClickPayload(new ClickPayload(player.getUuid(),lastBox.ghastUUID,
                    lastBox.getCenter().toVector3f(),lastBox.action.label));


            //PERFECT DEBUG SHIT
//            spawnFlameParticlesAtBoxCorners(world,lastBox);
            ci.cancel();
        } else {
//            player.sendMessage(Text.of("Nothing"),true);
        }


    }


    //only for debbuging rn
    private static void spawnFlameParticlesAtBoxCorners(World world, Box box) {

        Vec3d min = new Vec3d(box.minX, box.minY, box.minZ);
        Vec3d max = new Vec3d(box.maxX, box.maxY, box.maxZ);

        Vec3d[] corners = new Vec3d[] {
                new Vec3d(min.x, min.y, min.z),
                new Vec3d(max.x, min.y, min.z),
                new Vec3d(min.x, min.y, max.z),
                new Vec3d(max.x, min.y, max.z),
                new Vec3d(min.x, max.y, min.z),
                new Vec3d(max.x, max.y, min.z),
                new Vec3d(min.x, max.y, max.z),
                new Vec3d(max.x, max.y, max.z)
        };

        for (Vec3d corner : corners) {
            world.addParticleClient(ParticleTypes.FLAME, corner.x, corner.y, corner.z,
                    0, 0.1, 0);
        }
    }



}
