package com.keremyurekli.happyghastbuilding.networking;

import com.keremyurekli.happyghastbuilding.Constant;
import com.keremyurekli.happyghastbuilding.helpers.GhastHelper;
import com.keremyurekli.happyghastbuilding.helpers.ScreenHelper;
import com.keremyurekli.happyghastbuilding.weirdstuff.BoxWithData;
import com.keremyurekli.happyghastbuilding.weirdstuff.OnClick;
import com.keremyurekli.happyghastbuilding.weirdstuff.ScreenInfo;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.fabricmc.fabric.api.networking.v1.PayloadTypeRegistry;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.DispenserBlock;
import net.minecraft.block.dispenser.DispenserBehavior;
import net.minecraft.block.dispenser.EquippableDispenserBehavior;
import net.minecraft.block.dispenser.ItemDispenserBehavior;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.block.entity.DispenserBlockEntity;
import net.minecraft.component.DataComponentTypes;
import net.minecraft.entity.Entity;
import net.minecraft.entity.TntEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.Inventory;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.math.BlockPointer;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.World;
import net.minecraft.world.WorldEvents;
import net.minecraft.world.event.GameEvent;

import java.util.ArrayList;
import java.util.List;

public class NetworkingManager {

    public static void registerPayloads() {
        PayloadTypeRegistry.playC2S().register(ClickPayload.ID, ClickPayload.CODEC);
        PayloadTypeRegistry.playC2S().register(DispenserKeybindPayload.ID, DispenserKeybindPayload.CODEC);
        PayloadTypeRegistry.playS2C().register(GhastAddedPayload.ID, GhastAddedPayload.CODEC);
        PayloadTypeRegistry.playS2C().register(GhastRemovedPayload.ID, GhastRemovedPayload.CODEC);
        PayloadTypeRegistry.playS2C().register(FurnaceStatePayload.ID, FurnaceStatePayload.CODEC);
    }

    public static void sendRightClickPayload(ClickPayload payload) {
        ClientPlayNetworking.send(payload);
    }
    public static void sendKeybindingPayload(DispenserKeybindPayload payload) {
        ClientPlayNetworking.send(payload);
    }
    public static void sendGhastAddedPayload(ServerPlayerEntity p, GhastAddedPayload payload) {
        ServerPlayNetworking.send(p,payload);
    }
    public static void sendGhastRemovedPayload(ServerPlayerEntity p, GhastRemovedPayload payload) {
        ServerPlayNetworking.send(p,payload);
    }
    public static void sendFurnaceStatePayload(ServerPlayerEntity p, FurnaceStatePayload payload) {
        ServerPlayNetworking.send(p,payload);
    }

    public static void imServer() {
        ServerPlayNetworking.registerGlobalReceiver(ClickPayload.ID, (payload, context) -> {
            context.server().execute(()-> {
                PlayerEntity player = context.server().getPlayerManager().getPlayer(payload.player());
                World world = player.getWorld();
                Entity ghast = world.getEntity(payload.ghastId());
                OnClick action = OnClick.valueOf(payload.action());

                ScreenHelper.playersWantsToOpenAFuckingScreen(player,payload.ghastId(),action,payload.pos());

            });
        });
        ServerPlayNetworking.registerGlobalReceiver(DispenserKeybindPayload.ID, (payload, context) -> {
            context.server().execute(()-> {
                PlayerEntity player = context.server().getPlayerManager().getPlayer(payload.player());
                World world = player.getWorld();
                if (player.getVehicle() != null) {
                    Entity vehicle = player.getVehicle();
                    if (Constant.INFO_LIST.containsKey(vehicle.getUuid())
                    && ScreenHelper.infos.containsKey(vehicle.getUuid())) {
                        if (!Constant.INFO_LIST.get(vehicle.getUuid()).boxes.isEmpty()) {
                            List<BoxWithData> boxListCopy = new ArrayList<>(Constant.INFO_LIST.get(vehicle.getUuid()).boxes);
                            for (BoxWithData boxWithData : boxListCopy) {
                                if (boxWithData.action.label.contains("DI")) {

                                    Inventory inventory = null;
                                    switch (boxWithData.action) {
                                        case DI1:
                                            inventory = ScreenHelper.infos.get(vehicle.getUuid()).portableDispenserInventory;
                                            break;
                                        case DI2:
                                            inventory = ScreenHelper.infos.get(vehicle.getUuid()).portableDispenserInventory2;
                                            break;
                                        case DI3:
                                            inventory = ScreenHelper.infos.get(vehicle.getUuid()).portableDispenserInventory3;
                                            break;
                                        case DI4:
                                            inventory = ScreenHelper.infos.get(vehicle.getUuid()).portableDispenserInventory4;
                                            break;
                                    }

                                    //TODO: TNT ONLY
                                    if (inventory != null) {
                                        dispense((ServerWorld) player.getWorld(),
                                                BlockPos.ofFloored(boxWithData.getCenter()),inventory);
//                                        for (int i = 0; i < inventory.size(); i++) {
//                                            ItemStack itemStack = inventory.getStack(i);
//                                            if (itemStack.getItem() == Items.TNT) {
//                                                itemStack.decrement(1);
//
//                                                TntEntity tnt = new TntEntity(world,
//                                                        boxWithData.getCenter().x,
//                                                        boxWithData.getCenter().y,
//                                                        boxWithData.getCenter().z, null);
//                                                world.spawnEntity(tnt);
//                                                world.playSound(null, BlockPos.ofFloored(boxWithData.getCenter().x,boxWithData.getCenter().y,boxWithData.getCenter().z), SoundEvents.ENTITY_TNT_PRIMED, SoundCategory.BLOCKS);
//
//                                                break;
//                                            }
//                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            });
        });
    }

    private static final ItemDispenserBehavior DEFAULT_BEHAVIOR = new ItemDispenserBehavior();

    protected static void dispense(ServerWorld world, BlockPos pos, Inventory inv) {
        BlockPointer blockPointer = new BlockPointer(world, pos, Blocks.DISPENSER.getDefaultState()
                .with(DispenserBlock.FACING, Direction.DOWN), null);
        int i = chooseNonEmptySlot(inv,world.random);
        if (i < 0) {
            world.syncWorldEvent(WorldEvents.DISPENSER_FAILS, pos, 0);
//            world.emitGameEvent(GameEvent.BLOCK_ACTIVATE, pos, GameEvent.Emitter.of(dispenserBlockEntity.getCachedState()));
            return;
        }
        ItemStack itemStack = inv.getStack(i);
        DispenserBehavior dispenserBehavior = getBehaviorForItem(world, itemStack);
        if (dispenserBehavior != DispenserBehavior.NOOP) {
            inv.setStack(i,dispenserBehavior.dispense(blockPointer, itemStack));
        }
    }

    protected static DispenserBehavior getBehaviorForItem(World world, ItemStack stack) {
        if (!stack.isItemEnabled(world.getEnabledFeatures())) {
            return DEFAULT_BEHAVIOR;
        }
        DispenserBehavior dispenserBehavior = DispenserBlock.BEHAVIORS.get(stack.getItem());
        if (dispenserBehavior != null) {
            return dispenserBehavior;
        }
        return getBehaviorForItem(stack);
    }

    private static DispenserBehavior getBehaviorForItem(ItemStack stack) {
        if (stack.contains(DataComponentTypes.EQUIPPABLE)) {
            return EquippableDispenserBehavior.INSTANCE;
        }
        return DEFAULT_BEHAVIOR;
    }

    private static int chooseNonEmptySlot(Inventory inventory, Random random) {
        int i = -1;
        int j = 1;
        for (int k = 0; k < inventory.size(); ++k) {
            if (inventory.getStack(k).isEmpty() || random.nextInt(j++) != 0) continue;
            i = k;
        }
        return i;
    }
    public static void imClient() {
        //no need to mess with screenhelper, we're client
        ClientPlayNetworking.registerGlobalReceiver(GhastAddedPayload.ID, (payload, context) -> {
//            context.player().sendMessage(Text.of(payload.ghastId().toString()),false);
            GhastHelper.addNewGhast(payload.ghastId());
        });
        ClientPlayNetworking.registerGlobalReceiver(GhastRemovedPayload.ID, (payload, context) -> {
            GhastHelper.removeGhast(payload.ghastId());
        });
        ClientPlayNetworking.registerGlobalReceiver(FurnaceStatePayload.ID, (payload, context) -> {
            if (Constant.INFO_LIST.containsKey(payload.ghastId())) {
                Constant.INFO_LIST.get(payload.ghastId()).isLit = payload.litState();
            }
        });
    }
}
