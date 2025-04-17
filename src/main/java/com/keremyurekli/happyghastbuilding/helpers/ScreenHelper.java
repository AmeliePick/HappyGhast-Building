package com.keremyurekli.happyghastbuilding.helpers;

import com.keremyurekli.happyghastbuilding.Constant;
import com.keremyurekli.happyghastbuilding.gui.PortableCraftingScreenHandlerFactory;
import com.keremyurekli.happyghastbuilding.gui.chest.PortableChestInventory;
import com.keremyurekli.happyghastbuilding.gui.chest.PortableChestScreenHandler;
import com.keremyurekli.happyghastbuilding.gui.dispenser.PortableDispenserInventory;
import com.keremyurekli.happyghastbuilding.gui.dispenser.PortableDispenserScreenHandler;
import com.keremyurekli.happyghastbuilding.gui.furnace.PortableFurnaceInventory;
import com.keremyurekli.happyghastbuilding.gui.furnace.PortableFurnaceScreenHandler;
import com.keremyurekli.happyghastbuilding.weirdstuff.OnClick;
import com.keremyurekli.happyghastbuilding.weirdstuff.ScreenInfo;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.resource.featuretoggle.FeatureFlags;
import net.minecraft.screen.ScreenHandlerType;
import net.minecraft.screen.SimpleNamedScreenHandlerFactory;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.text.Text;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.joml.Vector3f;

import java.util.HashMap;
import java.util.UUID;



public class ScreenHelper {

    public static final ScreenHandlerType<PortableChestScreenHandler> PORTABLE_CHEST_SCREEN_HANDLER = new ScreenHandlerType<>(PortableChestScreenHandler::new, FeatureFlags.VANILLA_FEATURES);
    public static final ScreenHandlerType<PortableFurnaceScreenHandler> PORTABLE_FURNACE_SCREEN_HANDLER = new ScreenHandlerType<>(PortableFurnaceScreenHandler::new, FeatureFlags.VANILLA_FEATURES);
    public static final ScreenHandlerType<PortableDispenserScreenHandler> PORTABLE_DISPENSER_SCREEN_HANDLER = new ScreenHandlerType<>(PortableDispenserScreenHandler::new, FeatureFlags.VANILLA_FEATURES);


    public static void registerStuff() {
        Registry.register(Registries.SCREEN_HANDLER, Constant.id("chest"), PORTABLE_CHEST_SCREEN_HANDLER);
        Registry.register(Registries.SCREEN_HANDLER, Constant.id("furnace"), PORTABLE_FURNACE_SCREEN_HANDLER);
        Registry.register(Registries.SCREEN_HANDLER, Constant.id("dispenser"), PORTABLE_DISPENSER_SCREEN_HANDLER);
    }

    public static HashMap<UUID, ScreenInfo> infos = new HashMap<>();


    //TOO MUCH WORK FOR NOW
//    public static HashMap<UUID, List<UUID>> respawnInfos = new HashMap<>();

    public static void ghastRemoved(UUID uuid, World world) {
        infos.remove(uuid);
//        if (respawnInfos.containsKey(uuid)) {
//            respawnInfos.get(uuid).forEach(pId -> {
//                ((ServerPlayerEntity)world.getPlayerByUuid(pId)).setSpawnPoint(null,false);
//            });
//            respawnInfos.remove(uuid);
//        }
    }
    public static void ghastAdded(UUID uuid, World world) {
        infos.put(uuid,new ScreenInfo());
    }



    public static void playersWantsToOpenAFuckingScreen(PlayerEntity player, UUID ghastId, OnClick which, Vector3f pos) {
        Entity ghastEntity = player.getWorld().getEntity(ghastId);
        if (!infos.containsKey(ghastId)) {
            infos.put(ghastId,new ScreenInfo());
        }
        ScreenInfo info = infos.get(ghastId);
        switch (which){
            case CRAFTING_TABLE:
                //no need for fancy shit because crafting tables exist for per player
                player.openHandledScreen(new PortableCraftingScreenHandlerFactory(player.getWorld(),player.getBlockPos()));
                break;
            case CHEST:
                if (info.chestInventory == null) {
                    info.chestInventory = new PortableChestInventory(ghastEntity);
                }
                if (info.chest == null) {
                    info.chest = new SimpleNamedScreenHandlerFactory((syncId, inventory, p) -> {
                        return new PortableChestScreenHandler(syncId, inventory, info.chestInventory);
                    }, Text.translatable("container.chest"));
                }
                player.openHandledScreen(info.chest);
                player.getWorld().playSound(null, BlockPos.ofFloored(pos.x,pos.y,pos.z), SoundEvents.BLOCK_CHEST_OPEN, SoundCategory.BLOCKS);
//                player.getCu

//                        new SimpleNamedScreenHandlerFactory(((syncId, playerInventory, p) -> new GhastChestScreenHandler()));
                break;
            case FURNACE:
//                        player.openHandledScreen(new PortableFurnaceScreenHandlerFactory());
                if (info.furnaceInventory == null) {
                    info.furnaceInventory = new PortableFurnaceInventory(ghastEntity);
                }
                if (info.furnace == null) {
                    info.furnace = new SimpleNamedScreenHandlerFactory((syncId, inventory, p) -> {
                        return new PortableFurnaceScreenHandler(syncId, inventory, info.furnaceInventory);
                    }, Text.translatable("container.furnace"));
                }
                player.openHandledScreen(info.furnace);
                break;

            case BED:
                //SLEEPING AND RESPAWN WILL BE IMPLEMENTED IN THE FUTURE
//                ServerPlayerEntity spe = (ServerPlayerEntity) player;
//                spe.setSpawnPoint(new ServerPlayerEntity.Respawn(player.getWorld().getRegistryKey(),
//                        BlockPos.ofFloored(ghastEntity.getPos().x,ghastEntity.getPos().y+6,ghastEntity.getPos().z),
//                        90,false),true);
//                if (respawnInfos.containsKey(ghastId)) {
//                    respawnInfos.get(ghastId).add(player.getUuid());
//                } else {
//                    List<UUID> tempList = new ArrayList<>();
//                    tempList.add(player.getUuid());
//                    respawnInfos.put(ghastId,tempList);
//                }
                break;
            case DISPENSER1:
                if (info.portableDispenserInventory == null) {
                    info.portableDispenserInventory = new PortableDispenserInventory(ghastEntity,1);
                }
                if (info.dispenser1 == null) {
                    info.dispenser1 = new SimpleNamedScreenHandlerFactory((syncId, inventory, p) -> {
                        return new PortableDispenserScreenHandler(syncId, inventory, info.portableDispenserInventory);
                    }, Text.translatable("container.dispenser"));
                }
                player.openHandledScreen(info.dispenser1);
                break;
            case DISPENSER2:
                if (info.portableDispenserInventory2 == null) {
                    info.portableDispenserInventory2 = new PortableDispenserInventory(ghastEntity,2);
                }
                if (info.dispenser2 == null) {
                    info.dispenser2 = new SimpleNamedScreenHandlerFactory((syncId, inventory, p) -> {
                        return new PortableDispenserScreenHandler(syncId, inventory, info.portableDispenserInventory2);
                    }, Text.translatable("container.dispenser"));
                }
                player.openHandledScreen(info.dispenser2);
                break;
            case DISPENSER3:
                if (info.portableDispenserInventory3 == null) {
                    info.portableDispenserInventory3 = new PortableDispenserInventory(ghastEntity,3);
                }
                if (info.dispenser3 == null) {
                    info.dispenser3 = new SimpleNamedScreenHandlerFactory((syncId, inventory, p) -> {
                        return new PortableDispenserScreenHandler(syncId, inventory, info.portableDispenserInventory3);
                    }, Text.translatable("container.dispenser"));
                }
                player.openHandledScreen(info.dispenser3);
                break;
            case DISPENSER4:
                if (info.portableDispenserInventory4 == null) {
                    info.portableDispenserInventory4 = new PortableDispenserInventory(ghastEntity,4);
                }
                if (info.dispenser4 == null) {
                    info.dispenser4 = new SimpleNamedScreenHandlerFactory((syncId, inventory, p) -> {
                        return new PortableDispenserScreenHandler(syncId, inventory, info.portableDispenserInventory4);
                    }, Text.translatable("container.dispenser"));
                }
                player.openHandledScreen(info.dispenser4);
                break;

        }
    }



}
