package com.keremyurekli.happyghastbuilding.gui;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.screen.NamedScreenHandlerFactory;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.text.Text;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class PortableCraftingScreenHandlerFactory implements NamedScreenHandlerFactory {

    private final World world;
    private final BlockPos fakePos;

    public PortableCraftingScreenHandlerFactory(World world, BlockPos fakePos) {
        this.world = world;
        this.fakePos = fakePos;
    }

    @Override
    public Text getDisplayName() {
        return Text.translatable("container.crafting");
    }

    @Override
    public ScreenHandler createMenu(int syncId, PlayerInventory inv, PlayerEntity player) {
        return new PortableCraftingScreenHandler(syncId, inv, world, fakePos);
    }
}