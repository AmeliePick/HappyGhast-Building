package com.keremyurekli.happyghastbuilding;

import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.component.DataComponentTypes;
import net.minecraft.component.type.EquippableComponent;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroups;
import net.minecraft.item.Items;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.util.DyeColor;

import java.util.function.Function;

public class ItemManager {

    public ItemManager() {

    }

    public static final Item WHITE_ADVENTURERS_HARNESS = register("white_adventurers_harness", new Item.Settings().maxCount(1).component(DataComponentTypes.EQUIPPABLE, EquippableComponent.method_70740(DyeColor.WHITE)));
    public static final Item ORANGE_ADVENTURERS_HARNESS = register("orange_adventurers_harness", new Item.Settings().maxCount(1).component(DataComponentTypes.EQUIPPABLE, EquippableComponent.method_70740(DyeColor.ORANGE)));
    public static final Item MAGENTA_ADVENTURERS_HARNESS = register("magenta_adventurers_harness", new Item.Settings().maxCount(1).component(DataComponentTypes.EQUIPPABLE, EquippableComponent.method_70740(DyeColor.MAGENTA)));
    public static final Item LIGHT_BLUE_ADVENTURERS_HARNESS = register("light_blue_adventurers_harness", new Item.Settings().maxCount(1).component(DataComponentTypes.EQUIPPABLE, EquippableComponent.method_70740(DyeColor.LIGHT_BLUE)));
    public static final Item YELLOW_ADVENTURERS_HARNESS = register("yellow_adventurers_harness", new Item.Settings().maxCount(1).component(DataComponentTypes.EQUIPPABLE, EquippableComponent.method_70740(DyeColor.YELLOW)));
    public static final Item LIME_ADVENTURERS_HARNESS = register("lime_adventurers_harness", new Item.Settings().maxCount(1).component(DataComponentTypes.EQUIPPABLE, EquippableComponent.method_70740(DyeColor.LIME)));
    public static final Item PINK_ADVENTURERS_HARNESS = register("pink_adventurers_harness", new Item.Settings().maxCount(1).component(DataComponentTypes.EQUIPPABLE, EquippableComponent.method_70740(DyeColor.PINK)));
    public static final Item GRAY_ADVENTURERS_HARNESS = register("gray_adventurers_harness", new Item.Settings().maxCount(1).component(DataComponentTypes.EQUIPPABLE, EquippableComponent.method_70740(DyeColor.GRAY)));
    public static final Item LIGHT_GRAY_ADVENTURERS_HARNESS = register("light_gray_adventurers_harness", new Item.Settings().maxCount(1).component(DataComponentTypes.EQUIPPABLE, EquippableComponent.method_70740(DyeColor.LIGHT_GRAY)));
    public static final Item CYAN_ADVENTURERS_HARNESS = register("cyan_adventurers_harness", new Item.Settings().maxCount(1).component(DataComponentTypes.EQUIPPABLE, EquippableComponent.method_70740(DyeColor.CYAN)));
    public static final Item PURPLE_ADVENTURERS_HARNESS = register("purple_adventurers_harness", new Item.Settings().maxCount(1).component(DataComponentTypes.EQUIPPABLE, EquippableComponent.method_70740(DyeColor.PURPLE)));
    public static final Item BLUE_ADVENTURERS_HARNESS = register("blue_adventurers_harness", new Item.Settings().maxCount(1).component(DataComponentTypes.EQUIPPABLE, EquippableComponent.method_70740(DyeColor.BLUE)));
    public static final Item BROWN_ADVENTURERS_HARNESS = register("brown_adventurers_harness", new Item.Settings().maxCount(1).component(DataComponentTypes.EQUIPPABLE, EquippableComponent.method_70740(DyeColor.BROWN)));
    public static final Item GREEN_ADVENTURERS_HARNESS = register("green_adventurers_harness", new Item.Settings().maxCount(1).component(DataComponentTypes.EQUIPPABLE, EquippableComponent.method_70740(DyeColor.GREEN)));
    public static final Item RED_ADVENTURERS_HARNESS = register("red_adventurers_harness", new Item.Settings().maxCount(1).component(DataComponentTypes.EQUIPPABLE, EquippableComponent.method_70740(DyeColor.RED)));
    public static final Item BLACK_ADVENTURERS_HARNESS = register("black_adventurers_harness", new Item.Settings().maxCount(1).component(DataComponentTypes.EQUIPPABLE, EquippableComponent.method_70740(DyeColor.BLACK)));

    public static Item register(String path, Item.Settings settings) {
        final RegistryKey<Item> registryKey = RegistryKey.of(RegistryKeys.ITEM, Constant.id(path));
        Item i = Items.register(registryKey, Item::new ,settings);
        ItemGroupEvents.modifyEntriesEvent(ItemGroups.SPAWN_EGGS).register(content -> {
            content.add(i);
            Constant.LOGGER.info("ADDING "+i.getName());
        });
        return i;
    }

}
