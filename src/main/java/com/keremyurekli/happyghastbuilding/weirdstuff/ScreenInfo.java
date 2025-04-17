package com.keremyurekli.happyghastbuilding.weirdstuff;

import com.keremyurekli.happyghastbuilding.gui.chest.PortableChestInventory;
import com.keremyurekli.happyghastbuilding.gui.dispenser.PortableDispenserInventory;
import com.keremyurekli.happyghastbuilding.gui.furnace.PortableFurnaceInventory;
import com.keremyurekli.happyghastbuilding.gui.furnace.PortableFurnaceScreenHandler;
import net.minecraft.screen.NamedScreenHandlerFactory;
import net.minecraft.screen.SimpleNamedScreenHandlerFactory;

public class ScreenInfo {

    public ScreenInfo() {

    }

    public SimpleNamedScreenHandlerFactory chest;
    public PortableChestInventory chestInventory;
    public SimpleNamedScreenHandlerFactory furnace;
    public PortableFurnaceInventory furnaceInventory;


    //moved to ghastinfo
//    public boolean furnaceState = false;

    public SimpleNamedScreenHandlerFactory dispenser1;
    public PortableDispenserInventory portableDispenserInventory;
    public SimpleNamedScreenHandlerFactory dispenser2;
    public PortableDispenserInventory portableDispenserInventory2;
    public SimpleNamedScreenHandlerFactory dispenser3;
    public PortableDispenserInventory portableDispenserInventory3;
    public SimpleNamedScreenHandlerFactory dispenser4;
    public PortableDispenserInventory portableDispenserInventory4;

}
