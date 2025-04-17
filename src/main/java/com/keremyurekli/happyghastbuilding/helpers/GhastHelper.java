package com.keremyurekli.happyghastbuilding.helpers;

import com.keremyurekli.happyghastbuilding.Constant;
import com.keremyurekli.happyghastbuilding.weirdstuff.GhastInfo;
import net.minecraft.entity.Entity;
import net.minecraft.entity.ItemEntity;

import java.util.UUID;

public class GhastHelper {


    public static GhastInfo addNewGhast(UUID uuid) {
        return Constant.INFO_LIST.computeIfAbsent(uuid, GhastInfo::new);
    }

    public static void addNewGhast(UUID uuid, GhastInfo i) {
        Constant.INFO_LIST.put(uuid,i);
    }

    public static void removeGhast(UUID uuid) {
        Constant.INFO_LIST.remove(uuid);
    }

    public static void removeGhast(UUID uuid, int itsServer) {
        Constant.INFO_LIST.remove(uuid);
    }
    public static void dropItems(Entity e) {
        if (Constant.INFO_LIST.containsKey(e.getUuid())) {
            Constant.INFO_LIST.get(e.getUuid()).inventories.values().forEach(list->{
                list.forEach(stack->{
                    if (!stack.isEmpty()) {
                        ItemEntity itemEntity = new ItemEntity(e.getWorld(), e.getPos().x, e.getPos().y+4f, e.getPos().z, stack);
                        e.getWorld().spawnEntity(itemEntity);
                        itemEntity.setVelocity(e.getWorld().random.nextDouble() * 0.2 - 0.1, 0.2, e.getWorld().random.nextDouble() * 0.2 - 0.1);
                    }

                });
            });
        }
    }
}
