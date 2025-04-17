package com.keremyurekli.happyghastbuilding.mixin.server;


import com.keremyurekli.happyghastbuilding.Constant;
import com.keremyurekli.happyghastbuilding.gui.furnace.PortableFurnaceInventory;
import com.keremyurekli.happyghastbuilding.networking.FurnaceStatePayload;
import com.keremyurekli.happyghastbuilding.networking.NetworkingManager;
import com.keremyurekli.happyghastbuilding.helpers.ScreenHelper;
import net.minecraft.class_11187;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.recipe.RecipeEntry;
import net.minecraft.recipe.input.SingleStackRecipeInput;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.UUID;

@Mixin(class_11187.class)
public class GhastTickMixin {


    @Inject(method = "mobTick",at = @At("HEAD"))
    public void mobTick(ServerWorld world, CallbackInfo ci) {
        furnaceTick(world);
    }
    @Inject(method = "tickControlled",at = @At("HEAD"))
    public void mobTick(PlayerEntity controllingPlayer, Vec3d movementInput, CallbackInfo ci) {
        if (!controllingPlayer.getWorld().isClient()) {
            furnaceTick((ServerWorld) controllingPlayer.getWorld());
        }
    }

    private void furnaceTick(ServerWorld world) {
        if (!world.isClient) {
            class_11187 entity = (class_11187)(Object)this;
            UUID uuid = entity.getUuid();
            if (Constant.INFO_LIST.containsKey(uuid)) {
                if (ScreenHelper.infos.get(uuid) != null && ScreenHelper.infos.get(uuid).furnace != null) {
                    PortableFurnaceInventory pfi = ScreenHelper.infos.get(uuid).furnaceInventory;
                    boolean bl4;
                    boolean bl = pfi.isBurning();
                    boolean bl2 = false;
                    if (pfi.isBurning()) {
                        --pfi.litTimeRemaining;
                    }
                    if (Constant.INFO_LIST.get(uuid).isLit != pfi.isBurning()) {
                        Constant.INFO_LIST.get(uuid).isLit = !Constant.INFO_LIST.get(uuid).isLit;


                        //just for good look
                        FurnaceStatePayload payload = new FurnaceStatePayload(uuid,pfi.isBurning());
                        world.getPlayers().forEach(serverPlayerEntity -> {
                            NetworkingManager.sendFurnaceStatePayload(serverPlayerEntity,payload);
                        });
                    }
                    ItemStack itemStack = pfi.getItems().get(1);
                    ItemStack itemStack2 = pfi.getItems().get(0);
                    boolean bl3 = !itemStack2.isEmpty();
                    boolean bl5 = bl4 = !itemStack.isEmpty();
                    if (pfi.isBurning() || bl4 && bl3) {
                        SingleStackRecipeInput singleStackRecipeInput = new SingleStackRecipeInput(itemStack2);
                        RecipeEntry recipeEntry = bl3 ? (RecipeEntry)pfi.matchGetter.getFirstMatch(singleStackRecipeInput, world).orElse(null) : null;
                        int i = pfi.getMaxCountPerStack();
                        if (!pfi.isBurning() && PortableFurnaceInventory.canAcceptRecipeOutput(world.getRegistryManager(), recipeEntry, singleStackRecipeInput, pfi.getItems(), i)) {
                            pfi.litTotalTime = pfi.litTimeRemaining = pfi.getFuelTime(world.getFuelRegistry(), itemStack);
                            if (pfi.isBurning()) {
                                bl2 = true;
                                if (bl4) {
                                    Item item = itemStack.getItem();
                                    itemStack.decrement(1);
                                    if (itemStack.isEmpty()) {
                                        pfi.getItems().set(1, item.getRecipeRemainder());
                                    }
                                }
                            }
                        }
                        if (pfi.isBurning() && PortableFurnaceInventory.canAcceptRecipeOutput(world.getRegistryManager(), recipeEntry, singleStackRecipeInput, pfi.getItems(), i)) {
//                            Constant.LOGGER.info("IM SOMWEHERE "+pfi.cookingTimeSpent+"/"+pfi.cookingTotalTime);
                            ++pfi.cookingTimeSpent;
                            if (pfi.cookingTimeSpent == pfi.cookingTotalTime) {
                                pfi.cookingTimeSpent = 0;
                                pfi.cookingTotalTime = pfi.getCookTime(world);
                                if (PortableFurnaceInventory.craftRecipe(world.getRegistryManager(), recipeEntry, singleStackRecipeInput, pfi.getItems(), i)) {
//                                    pfi.setLastRecipe(recipeEntry);
                                }
                                bl2 = true;
                            }
                        } else {
                            pfi.cookingTimeSpent = 0;
                        }
                    } else if (!pfi.isBurning() && pfi.cookingTimeSpent > 0) {
                        pfi.cookingTimeSpent = MathHelper.clamp(pfi.cookingTimeSpent - 2, 0, pfi.cookingTotalTime);
                    }
                    if (bl != pfi.isBurning()) {
                        bl2 = true;
//                        state = (BlockState)state.with(AbstractFurnaceBlock.LIT, pfi.isBurning());
//                        world.setBlockState(pos, state, Block.NOTIFY_ALL);
                    }

                }
            }
        }
    }
}
