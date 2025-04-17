package com.keremyurekli.happyghastbuilding.gui.chest;

import com.keremyurekli.happyghastbuilding.Constant;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.collection.DefaultedList;

public final class PortableChestInventory implements ImplementedInventory {

	private final Entity e;
	private DefaultedList<ItemStack> items = DefaultedList.ofSize(9, ItemStack.EMPTY);

	public PortableChestInventory(Entity e) {
		this.e = e;
//		Constant.LOGGER.info("HAS "+items.si);
		if (!Constant.INFO_LIST.get(e.getUuid()).inventories.containsKey("chest")) {
			Constant.INFO_LIST.get(e.getUuid()).inventories.put("chest",DefaultedList.ofSize(27, ItemStack.EMPTY));// empty
		}
		this.items = (DefaultedList<ItemStack>) Constant.INFO_LIST.get(e.getUuid()).inventories.get("chest");

		updateInventory();
	}

	private void updateInventory() {
		Constant.INFO_LIST.get(e.getUuid()).inventories.put("chest", items);
	}

	@Override
	public DefaultedList<ItemStack> getItems() {
		return items;
	}

	@Override
	public void onClose(PlayerEntity player) {
		ImplementedInventory.super.onClose(player);
	}

	@Override
	public void markDirty() {
		updateInventory();
//		items.forEach(item -> {
//			if(!item.isEmpty()) {
//				Constant.LOGGER.info("HAS33 "+item.getItem().getTranslationKey());
//			}
//		});
//		e.setComponent(DataComponentTypes.CONTAINER, ContainerComponent.fromStacks(items));

		//fuck components im gonna use a my goddamn hashmap
//		if (Constant.INFO_LIST.get(e.getUuid()).inventories.get("chest") != items) {
//			Constant.LOGGER.info("STARTING NEW");
//			Constant.LOGGER.info("BEFORE");
//			Constant.LOGGER.info(String.valueOf(items));
//			Constant.LOGGER.info(String.valueOf(Constant.INFO_LIST.get(e.getUuid()).inventories.get("chest")));
//			Constant.LOGGER.info("BEFORE");


//			DefaultedList<ItemStack> temp = DefaultedList.ofSize(9, ItemStack.EMPTY);
//			for (int i = 0; i < items.size(); i++) {
//				temp.set(i,items.get(i).copy());
//			}
//			Constant.INFO_LIST.get(e.getUuid()).inventories.replace("chest",temp);



//			Constant.LOGGER.info("AFTER");
//			Constant.LOGGER.info(String.valueOf(items));
//			Constant.LOGGER.info(String.valueOf(Constant.INFO_LIST.get(e.getUuid()).inventories.get("chest")));
//			Constant.LOGGER.info("AFTER");
//		}


//		e.get(DataComponentTypes.CONTAINER).stream().forEach(i->{
//			if(!i.isEmpty()) {
//				Constant.LOGGER.info("HASWEW "+i.getItem().getTranslationKey());
//			}
//		});
	}
}