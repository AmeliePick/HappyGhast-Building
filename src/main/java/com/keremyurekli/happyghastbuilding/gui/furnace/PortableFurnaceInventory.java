package com.keremyurekli.happyghastbuilding.gui.furnace;

import com.keremyurekli.happyghastbuilding.Constant;
import com.keremyurekli.happyghastbuilding.gui.chest.ImplementedInventory;
import net.minecraft.block.Blocks;
import net.minecraft.block.entity.AbstractFurnaceBlockEntity;
import net.minecraft.entity.Entity;
import net.minecraft.item.FuelRegistry;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.recipe.AbstractCookingRecipe;
import net.minecraft.recipe.RecipeEntry;
import net.minecraft.recipe.RecipeType;
import net.minecraft.recipe.ServerRecipeManager;
import net.minecraft.recipe.input.SingleStackRecipeInput;
import net.minecraft.registry.DynamicRegistryManager;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.screen.PropertyDelegate;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.collection.DefaultedList;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

public class PortableFurnaceInventory implements ImplementedInventory {
    private final Entity e;
    private DefaultedList<ItemStack> items;

    public final ServerRecipeManager.MatchGetter<SingleStackRecipeInput, ? extends AbstractCookingRecipe> matchGetter;

    public PortableFurnaceInventory(Entity e) {
        if (e == null) {
            this.e = null;
            matchGetter = null;
        } else {
            this.e = e;
            if (!Constant.INFO_LIST.get(e.getUuid()).inventories.containsKey("furnace")) {
                Constant.INFO_LIST.get(e.getUuid()).inventories.put("furnace",DefaultedList.ofSize(3, ItemStack.EMPTY));// empty
            }
            this.items = (DefaultedList<ItemStack>) Constant.INFO_LIST.get(e.getUuid()).inventories.get("furnace");

            updateInventory();

            this.matchGetter = ServerRecipeManager.createCachedMatchGetter(RecipeType.SMELTING);
        }

    }
    private void updateInventory() {
        Constant.INFO_LIST.get(e.getUuid()).inventories.put("furnace", items);
    }

    public int getCookTime(ServerWorld world) {
        SingleStackRecipeInput singleStackRecipeInput = new SingleStackRecipeInput(items.get(0));
        return matchGetter.getFirstMatch(singleStackRecipeInput, world).map(recipe -> ((AbstractCookingRecipe)recipe.value()).getCookingTime()).orElse(200);
    }
    public boolean isBurning() {
        return this.litTimeRemaining > 0;
    }

    public int getFuelTime(FuelRegistry fuelRegistry, ItemStack stack) {
        return fuelRegistry.getFuelTicks(stack);
    }


    @Override
    public void setStack(int slot, ItemStack stack) {
        ItemStack itemStack = items.get(slot);
        boolean bl = !stack.isEmpty() && ItemStack.areItemsAndComponentsEqual(itemStack, stack);
        items.set(slot, stack);
        stack.capCount(this.getMaxCount(stack));
        if (slot == 0 && !bl) {
            ServerWorld serverWorld = (ServerWorld)e.getWorld();
            this.cookingTotalTime = this.getCookTime(serverWorld);
            this.cookingTimeSpent = 0;
            this.markDirty();
        }
    }

    @Override
    public DefaultedList<ItemStack> getItems() {
        return items;
    }
    public int litTimeRemaining;
    public int litTotalTime;
    public int cookingTimeSpent;
    public int cookingTotalTime;
    protected final PropertyDelegate propertyDelegate = new PropertyDelegate(){

        @Override
        public int get(int index) {
            switch (index) {
                case 0: {
                    return litTimeRemaining;
                }
                case 1: {
                    return litTotalTime;
                }
                case 2: {
                    return cookingTimeSpent;
                }
                case 3: {
                    return cookingTotalTime;
                }
            }
            return 0;
        }

        @Override
        public void set(int index, int value) {
            switch (index) {
                case 0: {
                    litTimeRemaining = value;
                    break;
                }
                case 1: {
                    litTotalTime = value;
                    break;
                }
                case 2: {
                    cookingTimeSpent = value;
                    break;
                }
                case 3: {
                    cookingTotalTime = value;
                    break;
                }
            }
        }

        @Override
        public int size() {
            return 4;
        }
    };

    public static boolean canAcceptRecipeOutput(DynamicRegistryManager dynamicRegistryManager, @Nullable RecipeEntry<? extends AbstractCookingRecipe> recipe, SingleStackRecipeInput input, DefaultedList<ItemStack> inventory, int maxCount) {
        if (inventory.get(0).isEmpty() || recipe == null) {
            return false;
        }
        ItemStack itemStack = recipe.value().craft(input, (RegistryWrapper.WrapperLookup)dynamicRegistryManager);
        if (itemStack.isEmpty()) {
            return false;
        }
        ItemStack itemStack2 = inventory.get(2);
        if (itemStack2.isEmpty()) {
            return true;
        }
        if (!ItemStack.areItemsAndComponentsEqual(itemStack2, itemStack)) {
            return false;
        }
        if (itemStack2.getCount() < maxCount && itemStack2.getCount() < itemStack2.getMaxCount()) {
            return true;
        }
        return itemStack2.getCount() < itemStack.getMaxCount();
    }
    public static boolean craftRecipe(DynamicRegistryManager dynamicRegistryManager, @Nullable RecipeEntry<? extends AbstractCookingRecipe> recipe, SingleStackRecipeInput input, DefaultedList<ItemStack> inventory, int maxCount) {
        if (recipe == null || !canAcceptRecipeOutput(dynamicRegistryManager, recipe, input, inventory, maxCount)) {
            return false;
        }
        ItemStack itemStack = inventory.get(0);
        ItemStack itemStack2 = recipe.value().craft(input, (RegistryWrapper.WrapperLookup)dynamicRegistryManager);
        ItemStack itemStack3 = inventory.get(2);
//        Constant.LOGGER.info("Getting out "+itemStack2.getItem().getTranslationKey());
        if (itemStack3.isEmpty()) {
            inventory.set(2, itemStack2.copy());
        } else if (ItemStack.areItemsAndComponentsEqual(itemStack3, itemStack2)) {
            itemStack3.increment(1);
        }
        if (itemStack.isOf(Blocks.WET_SPONGE.asItem()) && !inventory.get(1).isEmpty() && inventory.get(1).isOf(Items.BUCKET)) {
            inventory.set(1, new ItemStack(Items.WATER_BUCKET));
        }
        itemStack.decrement(1);
        return true;
    }

    @Override
    public void markDirty() {
        updateInventory();
    }
}
