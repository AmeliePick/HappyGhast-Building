package com.keremyurekli.happyghastbuilding.gui.furnace;

import net.minecraft.block.entity.AbstractFurnaceBlockEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.Inventory;
import net.minecraft.inventory.SimpleInventory;
import net.minecraft.recipe.RecipePropertySet;
import net.minecraft.recipe.RecipeType;
import net.minecraft.recipe.book.RecipeBookType;
import net.minecraft.screen.FurnaceScreenHandler;
import net.minecraft.screen.PropertyDelegate;
import net.minecraft.screen.ScreenHandlerType;

public class PortableFurnaceScreenHandler extends FurnaceScreenHandler {


    public PortableFurnaceInventory inventory;

    public PortableFurnaceScreenHandler(int syncId, PlayerInventory playerInventory) {
        this(syncId, playerInventory,new PortableFurnaceInventory(null));
    }

    public PortableFurnaceScreenHandler(int syncId, PlayerInventory playerInventory, PortableFurnaceInventory inv) {
        super(syncId, playerInventory, inv, inv.propertyDelegate);
        this.inventory = inv;
//        this(syncId, playerInventory,inv);
    }

//    public PortableFurnaceScreenHandler(int syncId, PlayerInventory playerInventory, Inventory inventory, PropertyDelegate d) {
//        super(syncId, playerInventory, inventory, d);
//        this.inventory = inventory;
//    }





    @Override
    public boolean canUse(PlayerEntity player) {
        return true;
    }

    @Override
    public void onClosed(PlayerEntity player) {
        super.onClosed(player);
    }
}
