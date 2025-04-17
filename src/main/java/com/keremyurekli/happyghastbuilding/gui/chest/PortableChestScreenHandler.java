package com.keremyurekli.happyghastbuilding.gui.chest;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.Inventory;
import net.minecraft.inventory.SimpleInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.screen.GenericContainerScreenHandler;
import net.minecraft.screen.ScreenHandlerType;
import net.minecraft.screen.slot.SlotActionType;

import static com.keremyurekli.happyghastbuilding.helpers.ScreenHelper.PORTABLE_CHEST_SCREEN_HANDLER;



public class PortableChestScreenHandler extends GenericContainerScreenHandler {


	private final ScreenHandlerType<?> type;
	private final Inventory inventory;

	public PortableChestScreenHandler(int syncId, PlayerInventory playerInventory) {
		this(syncId,playerInventory,new SimpleInventory(27));
	}

	public PortableChestScreenHandler(int syncId, PlayerInventory playerInventory, Inventory inventory) {
		this(ScreenHandlerType.GENERIC_9X3,syncId, playerInventory, inventory,3);
	}


	public PortableChestScreenHandler(ScreenHandlerType<?> type, int syncId, PlayerInventory playerInventory, Inventory inventory, int rows) {
		super(type, syncId, playerInventory, inventory, rows);
		this.type = PORTABLE_CHEST_SCREEN_HANDLER;
		this.inventory = inventory;

	}


	@Override
	public ScreenHandlerType<?> getType() {
		return type;
	}


	@Override
	public ItemStack quickMove(PlayerEntity player, int slot) {
//		Constant.LOGGER.info("Quick Move!");
		return super.quickMove(player, slot);
	}

	@Override
	public void onContentChanged(Inventory inventory) {
//		Constant.LOGGER.info("Content changed!");
		super.onContentChanged(inventory);
	}

	@Override
	public void onSlotClick(int slotId, int clickData, SlotActionType actionType, PlayerEntity playerEntity) {
//		if (slotId >= 0 && slots.size() > slotId) {
//			getSlot(slotId).markDirty();
//		}
		super.onSlotClick(slotId, clickData, actionType, playerEntity);
	}
	@Override
	public void onClosed(PlayerEntity player) {
		super.onClosed(player);
		if (inventory instanceof PortableChestInventory b) {
			b.markDirty();
//			Constant.LOGGER.info("CLOSE1");
		}
//		Constant.LOGGER.info("CLOSE2");
 	}
}