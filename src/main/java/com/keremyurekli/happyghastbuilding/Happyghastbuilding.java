package com.keremyurekli.happyghastbuilding;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.event.player.UseEntityCallback;
import net.minecraft.advancement.criterion.Criteria;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.component.ComponentType;
import net.minecraft.component.DataComponentTypes;
import net.minecraft.component.type.BlockStateComponent;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.passive.PassiveEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtHelper;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.sound.SoundCategory;
import net.minecraft.state.property.Properties;
import net.minecraft.state.property.Property;
import net.minecraft.text.Text;
import net.minecraft.util.ActionResult;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Box;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.GameMode;
import net.minecraft.world.World;
import net.minecraft.world.event.GameEvent;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;

public class Happyghastbuilding implements ModInitializer {
    @Override
    public void onInitialize() {
//        Constant.LOGGER.info("Starting up pal...");

        UseEntityCallback.EVENT.register((player, world, hand, entity, hitResult) -> {
            // This makes sure it runs only on the server
            if (!world.isClient && player.isSneaking()) {
                if (hitResult == null) {
                    return ActionResult.SUCCESS;
                }
                ItemStack itemInHand = player.getStackInHand(hand);
                if (itemInHand.getItem() instanceof  BlockItem blockItem) {
                    if (entity.getType() == EntityType.HAPPY_GHAST) {
                        if (entity instanceof PassiveEntity pe) {
                            if (player.getPos().y > pe.getPos().y+1) {//player is on top of the ghast
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

                                if(!entityBox.intersects(blockBox)) {
                                    //nothings wrong
                                } else {
                                    Box intersection = pe.getBoundingBox().intersection(blockBox);
                                    if (hitPos.y == entityBox.getMax(Direction.Axis.Y)) {
                                        if (intersection.getLengthY() > 0) {
                                            y1 = 1;
                                        }

                                    } else {
                                        //the problem is on the sides
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
                                    blockPos = new BlockPos((int) (blockPos.getX()+x1),(int) (blockPos.getY()+y1),(int) (blockPos.getZ()+z1));
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
                                        world.setBlockState(blockPos,blockItem.getBlock().getDefaultState().withIfExists(p, player.getHorizontalFacing()));
                                    } else {
                                        world.setBlockState(blockPos, blockItem.getBlock().getDefaultState());

                                    }
                                    copyComponentsToBlockEntity(world,blockPos,itemInHand);
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
                                    itemInHand.decrementUnlessCreative(1,player);
                                    return ActionResult.SUCCESS;
                                }
                            }


                        }

                        return ActionResult.SUCCESS;
                    }
                }
            }
            return ActionResult.PASS;
        });

    }
    private static void copyComponentsToBlockEntity(World world, BlockPos pos, ItemStack stack) {
        BlockEntity blockEntity = world.getBlockEntity(pos);
        if (blockEntity != null) {
            blockEntity.readComponents(stack);
            blockEntity.markDirty();
        }
    }


}