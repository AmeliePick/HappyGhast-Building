package com.keremyurekli.happyghastbuilding;

import com.keremyurekli.happyghastbuilding.Constant;
import com.keremyurekli.happyghastbuilding.ItemManager;
import com.keremyurekli.happyghastbuilding.helpers.FileManager;
import com.keremyurekli.happyghastbuilding.helpers.ScreenHelper;
import com.keremyurekli.happyghastbuilding.networking.FurnaceStatePayload;
import com.keremyurekli.happyghastbuilding.networking.GhastAddedPayload;
import com.keremyurekli.happyghastbuilding.networking.NetworkingManager;
import java.nio.file.Path;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerLifecycleEvents;
import net.fabricmc.fabric.api.event.player.UseEntityCallback;
import net.fabricmc.fabric.api.networking.v1.ServerPlayConnectionEvents;
import net.minecraft.class_11187;
import net.minecraft.class_1268;
import net.minecraft.class_1269;
import net.minecraft.class_1296;
import net.minecraft.class_1297;
import net.minecraft.class_1299;
import net.minecraft.class_1304;
import net.minecraft.class_1309;
import net.minecraft.class_1657;
import net.minecraft.class_1694;
import net.minecraft.class_1747;
import net.minecraft.class_1792;
import net.minecraft.class_1799;
import net.minecraft.class_1802;
import net.minecraft.class_1937;
import net.minecraft.class_2338;
import net.minecraft.class_2350;
import net.minecraft.class_2374;
import net.minecraft.class_238;
import net.minecraft.class_243;
import net.minecraft.class_2498;
import net.minecraft.class_2586;
import net.minecraft.class_268;
import net.minecraft.class_2680;
import net.minecraft.class_270;
import net.minecraft.class_2769;
import net.minecraft.class_2995;
import net.minecraft.class_3218;
import net.minecraft.class_3222;
import net.minecraft.class_3419;
import net.minecraft.class_3966;
import net.minecraft.class_5218;
import net.minecraft.class_5455;

public class Happyghastbuilding
implements ModInitializer {
    public void onInitialize() {
        NetworkingManager.registerPayloads();
        NetworkingManager.imServer();
        ScreenHelper.registerStuff();
        new ItemManager();
        UseEntityCallback.EVENT.register((player, world, hand, entity, hitResult) -> Happyghastbuilding.entityHandleV1(player, world, hand, entity, hitResult));
        ServerPlayConnectionEvents.INIT.register((handler, server) -> Constant.INFO_LIST.forEach((uuid, ghastInfo) -> {
            NetworkingManager.sendGhastAddedPayload((class_3222)handler.field_14140, (GhastAddedPayload)new GhastAddedPayload(ghastInfo.ghastUUID));
            NetworkingManager.sendFurnaceStatePayload((class_3222)handler.field_14140, (FurnaceStatePayload)new FurnaceStatePayload(ghastInfo.ghastUUID, ghastInfo.isLit));
        }));
        ServerLifecycleEvents.SERVER_STARTING.register(server -> FileManager.load((Path)server.method_27050(class_5218.field_24188), (class_5455.class_6890)server.method_30611()));
        ServerLifecycleEvents.SERVER_STOPPING.register(server -> FileManager.save((Path)server.method_27050(class_5218.field_24188)));
    }

    private static void interactMob(class_1657 player, class_1268 hand, class_11187 ghast) {
        class_1799 itemStack = player.method_5998(hand);
        if (itemStack.method_31574(class_1802.field_8388) && ghast.method_5685().size() < 3 && !ghast.method_6118(class_1304.field_48824).method_7960()) {
            if (!ghast.method_37908().field_9236) {
                class_1937 v8;
                class_1694 chestMinecart = new class_1694(class_1299.field_6126, ghast.method_37908());
                chestMinecart.method_5808(ghast.method_23317(), ghast.method_23318(), ghast.method_23321(), ghast.method_36454(), ghast.method_36455());
                ghast.method_37908().method_8649((class_1297)chestMinecart);
                chestMinecart.method_5804((class_1297)ghast);
                if (!player.method_31549().field_7477) {
                    itemStack.method_7934(1);
                }
                if ((v8 = ghast.method_37908()) instanceof class_3218) {
                    class_3218 serverWorld = (class_3218)v8;
                    class_2995 scoreboard = serverWorld.method_14170();
                    class_268 team = scoreboard.method_1153("NoCollision");
                    if (team == null) {
                        team = scoreboard.method_1171("NoCollision");
                        team.method_1145(class_270.class_271.field_1435);
                    } else if (team.method_1203() != class_270.class_271.field_1435) {
                        team.method_1145(class_270.class_271.field_1435);
                    }
                    String command = String.format("team join NoCollision %s", chestMinecart.method_5845());
                    try {
                        serverWorld.method_8503().method_3734().method_44252(serverWorld.method_8503().method_3739().method_9217(), command);
                    }
                    catch (Exception v11) {
                        // empty catch block
                    }
                }
            }
            return;
        }
    }

    private static class_1269 entityHandleV1(class_1657 player, class_1937 world, class_1268 hand, class_1297 entity, class_3966 hitResult) {
        class_1799 itemInHand;
        class_1792 v7;
        if (hitResult == null) {
            return class_1269.field_5811;
        }
        if (entity instanceof class_11187) {
            Happyghastbuilding.interactMob(player, hand, (class_11187)entity);
        }
        if (!world.field_9236 && entity.method_5864() == class_1299.field_59668 && player.method_5715() && (v7 = (itemInHand = player.method_5998(hand)).method_7909()) instanceof class_1747) {
            class_1747 blockItem = (class_1747)v7;
            class_11187 ghast = (class_11187)entity;
            class_1296 pe = (class_1296)entity;
            if (pe.method_6109()) {
                return class_1269.field_5811;
            }
            class_11187 class_111872 = ghast;
            class_243 hitPos = hitResult.method_17784();
            class_2338 blockPos = class_2338.method_49638((class_2374)hitPos);
            class_238 blockBox = new class_238(blockPos);
            class_238 entityBox = pe.method_5829();
            class_243 entityCenter = entityBox.method_1005();
            class_243 blockCenter = blockBox.method_1005();
            class_243 newBlockPos = blockBox.method_1005();
            int x1 = 0;
            int y1 = 0;
            int z1 = 0;
            if (entityBox.method_994(blockBox)) {
                class_238 intersection = pe.method_5829().method_999(blockBox);
                if (hitPos.field_1351 == entityBox.method_990(class_2350.class_2351.field_11052)) {
                    if (intersection.method_17940() > 0.0) {
                        y1 = 1;
                    }
                } else if (intersection.method_17939() < intersection.method_17941()) {
                    x1 = entityCenter.field_1352 > blockCenter.field_1352 ? -1 : 1;
                } else {
                    z1 = entityCenter.field_1350 > blockCenter.field_1350 ? -1 : 1;
                }
            }
            if (x1 != 0 || y1 != 0 || z1 != 0) {
                blockPos = new class_2338(blockPos.method_10263() + x1, blockPos.method_10264() + y1, blockPos.method_10260() + z1);
            }
            if (world.method_8320(blockPos).method_45474()) {
                class_2769 p = null;
                for (class_2769 p1 : blockItem.method_7711().method_9564().method_28501()) {
                    if (!p1.method_11899().equals("facing")) continue;
                    p = p1;
                }
                if (p != null) {
                    world.method_8501(blockPos, (class_2680)blockItem.method_7711().method_9564().method_47968(p, (Comparable)player.method_5735()));
                } else {
                    world.method_8501(blockPos, blockItem.method_7711().method_9564());
                }
                Happyghastbuilding.copyComponentsToBlockEntity(world, blockPos, itemInHand);
                player.method_6104(hand);
                class_2498 soundGroup = blockItem.method_7711().method_9564().method_26231();
                world.method_8396(null, blockPos, soundGroup.method_10598(), class_3419.field_15245, (soundGroup.method_10597() + 1.0f) / 2.0f, soundGroup.method_10599() * 0.8f);
                itemInHand.method_57008(1, (class_1309)player);
                return class_1269.field_5812;
            }
            return class_1269.field_5812;
        }
        return class_1269.field_5811;
    }

    private static void copyComponentsToBlockEntity(class_1937 world, class_2338 pos, class_1799 stack) {
        class_2586 blockEntity = world.method_8321(pos);
        if (blockEntity != null) {
            blockEntity.method_58683(stack);
            blockEntity.method_5431();
        }
    }
}
