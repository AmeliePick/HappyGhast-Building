package com.keremyurekli.happyghastbuilding;

import com.keremyurekli.happyghastbuilding.helpers.FileManager;
import com.keremyurekli.happyghastbuilding.networking.FurnaceStatePayload;
import com.keremyurekli.happyghastbuilding.networking.GhastAddedPayload;
import com.keremyurekli.happyghastbuilding.networking.NetworkingManager;
import com.keremyurekli.happyghastbuilding.helpers.ScreenHelper;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerLifecycleEvents;
import net.fabricmc.fabric.api.event.player.UseEntityCallback;
import net.fabricmc.fabric.api.networking.v1.ServerPlayConnectionEvents;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.class_11187;
import net.minecraft.entity.*;
import net.minecraft.entity.passive.PassiveEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.BlockItem;
import net.minecraft.item.ItemStack;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.network.ServerPlayNetworkHandler;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.sound.SoundCategory;
import net.minecraft.state.property.Property;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.WorldSavePath;
import net.minecraft.util.hit.EntityHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Box;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;

public class Happyghastbuilding implements ModInitializer {





    @Override
    public void onInitialize() {

        NetworkingManager.registerPayloads();
        NetworkingManager.imServer();

        ScreenHelper.registerStuff();



        new ItemManager();


        UseEntityCallback.EVENT.register((player, world, hand, entity, hitResult) -> {
            return entityHandleV1(player,world,hand,entity,hitResult);
        });
        ServerPlayConnectionEvents.INIT.register((ServerPlayNetworkHandler handler, MinecraftServer server)->{
            Constant.INFO_LIST.forEach((uuid, ghastInfo) -> {
                NetworkingManager.sendGhastAddedPayload(handler.player,new GhastAddedPayload(ghastInfo.ghastUUID));
                NetworkingManager.sendFurnaceStatePayload(handler.player,new FurnaceStatePayload(ghastInfo.ghastUUID, ghastInfo.isLit));
            });
        });


//
        ServerLifecycleEvents.SERVER_STARTING.register((server -> {
//            Constant.LOGGER.info("**************STARTING "+Constant.INFO_LIST.keySet().size());
            FileManager.load(server.getSavePath(WorldSavePath.ROOT),server.getRegistryManager());
        }));
//
        ServerLifecycleEvents.SERVER_STOPPING.register((server)->{
//            Constant.LOGGER.info("/////////////////STOPPING "+Constant.INFO_LIST.keySet().size());
            FileManager.save(server.getSavePath(WorldSavePath.ROOT));
//            server.getWorlds().forEach(sw -> {
//                sw.getPersistentStateManager().
//            });
        });



    }

    private static ActionResult entityHandleV1(PlayerEntity player, World world, Hand hand, Entity entity, EntityHitResult hitResult) {
        // This makes sure it runs only on the server
        if (hitResult == null) {
            return ActionResult.PASS;
        }
        if (!world.isClient) {
//            Vec3d start = player.getEyePos();
//            Vec3d rotation = player.getRotationVec(1.0F); // tickDelta doesn't matter as much here
//            Vec3d end = start.add(rotation.multiply(distance));
//
//            HitResult hit = world.raycast(new RaycastContext(
//                    start, end,
//                    RaycastContext.ShapeType.OUTLINE,
//                    RaycastContext.FluidHandling.NONE,
//                    player
//            ));
            if (entity.getType() == EntityType.HAPPY_GHAST && player.isSneaking()) {
                ItemStack itemInHand = player.getStackInHand(hand);
                if (itemInHand.getItem() instanceof  BlockItem blockItem) {
                    class_11187 ghast = (class_11187) entity;
                    PassiveEntity pe = (PassiveEntity) entity;
                    if (pe.isBaby()) {
                        return ActionResult.PASS;
                    }
                    if (ghast.method_70702()) {//player is on top of the ghast
                        Vec3d hitPos = hitResult.getPos();
                        BlockPos blockPos = BlockPos.ofFloored(hitPos);
                        Box blockBox = new Box(blockPos);

                        Box entityBox = pe.getBoundingBox();
                        Vec3d entityCenter = entityBox.getCenter();


                        Vec3d blockCenter = blockBox.getCenter();

                        Vec3d newBlockPos = blockBox.getCenter();

                        int x1 = 0;
                        int y1 = 0;
                        int z1 = 0;

                        if (!entityBox.intersects(blockBox)) {
                            //nothings wrong
                        } else {
                            Box intersection = pe.getBoundingBox().intersection(blockBox);
                            if (hitPos.y == entityBox.getMax(Direction.Axis.Y)) {
                                if (intersection.getLengthY() > 0) {
                                    y1 = 1;
                                }

                            } else {
                                //the problem is on the sides

                                // Checks which axis is the best way to correct it
                                if (intersection.getLengthX() < intersection.getLengthZ()) {
                                    if (entityCenter.x > blockCenter.x) {
                                        x1 = -1;
                                    } else {
                                        x1 = 1;
                                    }
                                } else {
                                    if (entityCenter.z > blockCenter.z) {
                                        z1 = -1;
                                    } else {
                                        z1 = 1;
                                    }
                                }
                            }
                        }
                        if (x1 != 0 || y1 != 0 || z1 != 0) {
                            blockPos = new BlockPos((int) (blockPos.getX() + x1), (int) (blockPos.getY() + y1), (int) (blockPos.getZ() + z1));
                        }
                        if (world.getBlockState(blockPos).isReplaceable()) {
//                                    itemInHand.get(ComponentType<>);


                            Property<Direction> p = null;
                            for (Property p1 : blockItem.getBlock().getDefaultState().getProperties()) {
                                if (p1.getName().equals("facing")) {
                                    p = p1;
                                }
                            }
//                                    player.sendMessage(Text.literal(blockItem.getBlock().getDefaultState().getProperties().toString()), false);
                            if (p != null) {
//                                        player.sendMessage(Text.literal("Yes my boy"), false);
                                world.setBlockState(blockPos, blockItem.getBlock().getDefaultState().withIfExists(p, player.getHorizontalFacing()));
                            } else {
                                world.setBlockState(blockPos, blockItem.getBlock().getDefaultState());

                            }
                            copyComponentsToBlockEntity(world, blockPos, itemInHand);
                            player.swingHand(hand);
                            BlockSoundGroup soundGroup = blockItem.getBlock().getDefaultState().getSoundGroup();
                            world.playSound(
                                    null,
                                    blockPos,
                                    soundGroup.getPlaceSound(),
                                    SoundCategory.BLOCKS,
                                    (soundGroup.getVolume() + 1.0f) / 2.0f,
                                    soundGroup.getPitch() * 0.8f
                            );
                            itemInHand.decrementUnlessCreative(1, player);
                            return ActionResult.SUCCESS;
                        }
                    }


                    return ActionResult.SUCCESS;
                }
            }
        }
        return ActionResult.PASS;
    }
    private static void copyComponentsToBlockEntity(World world, BlockPos pos, ItemStack stack) {
        BlockEntity blockEntity = world.getBlockEntity(pos);
        if (blockEntity != null) {
            blockEntity.readComponents(stack);
            blockEntity.markDirty();
        }
    }


}