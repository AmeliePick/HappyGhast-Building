package com.keremyurekli.happyghastbuilding.gui;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.screen.CraftingScreenHandler;
import net.minecraft.screen.ScreenHandlerContext;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class PortableCraftingScreenHandler extends CraftingScreenHandler {

    public PortableCraftingScreenHandler(int syncId, PlayerInventory inv, World world, BlockPos fakePos) {
        super(syncId, inv, ScreenHandlerContext.create(world, fakePos));
    }

    @Override
    public boolean canUse(PlayerEntity player) {
        return true;
    }
}