package com.keremyurekli.happyghastbuilding.helpers;

import com.keremyurekli.happyghastbuilding.Constant;
import com.keremyurekli.happyghastbuilding.weirdstuff.GhastInfo;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtOps;
import net.minecraft.nbt.StringNbtReader;
import net.minecraft.registry.DynamicRegistryManager;
import net.minecraft.util.collection.DefaultedList;
import org.yaml.snakeyaml.Yaml;

import java.io.*;
import java.nio.file.Path;
import java.util.*;

public class FileManager {

//    public static Gson gson = new GsonBuilder().setPrettyPrinting().create();

    private static final String SUBFOLDER_NAME = "/"+ Constant.MOD_ID+"/";


    public static void save(Path path) {
        File folder = new File(path + SUBFOLDER_NAME);
        if (folder.mkdirs()) {
            Constant.LOGGER.info("Folder created successfully: " + folder.getPath());
        } else {
            Constant.LOGGER.warn("Folder already exists or could not be created.");
        }
//        Constant.LOGGER.info("MAJOR ZERO");
        Constant.INFO_LIST.forEach((uuid, ghastInfo) -> {
            File target = new File(folder, uuid + ".yml");
//            Constant.LOGGER.info("UH 11");
            try (FileWriter writer = new FileWriter(target, false)) { // false = overwrite mode
//                Constant.LOGGER.info("UH 22 " + ghastInfo.inventories.size());
                Yaml yaml = new Yaml();
                ghastInfo.inventories.forEach((s, itemStacks) -> {
//                    Constant.LOGGER.info("UH 33");
                    yaml.dump(saveInventoryWithCodec(s, itemStacks),writer);
                });
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }

    public static Map<String,Map<Integer,String>> saveInventoryWithCodec(String name, List<ItemStack> inventory) {
        Map<Integer,String> datas = new HashMap();
//        Constant.LOGGER.info("Trying "+name+" with "+inventory.size());
        for (int i = 0; i < inventory.size(); i++) {
            ItemStack stack = inventory.get(i);
            if (!stack.isEmpty()) {
                var nbt = ItemStack.CODEC.encode(stack, NbtOps.INSTANCE, NbtOps.INSTANCE.empty());
                String string = "";
                if (nbt.result().isPresent()) {
//                    Constant.LOGGER.info("Pre-Present "+nbt.result().get().getNbtType());
                    string = nbt.result().get().toString();
//                    Constant.LOGGER.info("Present "+string);
                } else {
                    throw new AssertionError(nbt.error().get());
                }
                datas.put(i,string);
            }
        }
        Map<String,Map<Integer,String>> dataMap = new HashMap<>();
        dataMap.put(name,datas);

        return dataMap;
    }
    public static void destroyExtras(Path path) {
        File folder = new File(path+SUBFOLDER_NAME);
        File[] ymlFiles = folder.listFiles((dir, name) -> name.toLowerCase().endsWith(".yml"));

        Constant.INFO_LIST.clear();

        List<File> DESTROYABLES = new ArrayList<>();
        if (ymlFiles != null) {
            for (File file : ymlFiles) {
                String uuid = file.getName().replaceFirst("[.][^.]+$", "");

                if (!Constant.INFO_LIST.containsKey(UUID.fromString(uuid))) {
                    DESTROYABLES.add(file);
                }

            }
        } else {
            Constant.LOGGER.warn("Folder does not exist or is not a directory.");
        }
        for (int i = 0; i < DESTROYABLES.size(); i++) {
            DESTROYABLES.get(i).delete();
        }
    }

    public static void load(Path path, DynamicRegistryManager.Immutable registryManager) {
        File folder = new File(path+SUBFOLDER_NAME);
        File[] ymlFiles = folder.listFiles((dir, name) -> name.toLowerCase().endsWith(".yml"));

        Constant.INFO_LIST.clear();

        if (ymlFiles != null) {
            for (File file : ymlFiles) {
                String uuid = file.getName().replaceFirst("[.][^.]+$", "");

                GhastInfo info = GhastHelper.addNewGhast(UUID.fromString(uuid));

                Yaml yaml = new Yaml();
                try (InputStream input = new FileInputStream(file)) {
                    Map<String,Map<Integer,String>> data = yaml.load(input);
                    if (data != null) {
                        data.forEach(((key, integerStringMap) -> {
//                            Constant.LOGGER.info("Trying to load "+key+" contents:");
                            DefaultedList<ItemStack> list;
                            if (key.contains("dispenser")) {
                                list = DefaultedList.ofSize(9, ItemStack.EMPTY);
                            } else if(key.contains("chest")) {
                                list = DefaultedList.ofSize(27, ItemStack.EMPTY);
                            } else if(key.contains("furnace")) {
                                list = DefaultedList.ofSize(3, ItemStack.EMPTY);
                            } else {
                                list = null;//shouldnt happen
                            }

                            if (!integerStringMap.isEmpty()) {//yup
                                integerStringMap.forEach((integer, s) -> {
                                    ItemStack i;
                                    try {
                                        i = ItemStack.fromNbt(registryManager,StringNbtReader.readCompound(s)).orElse(ItemStack.EMPTY);
                                    } catch (CommandSyntaxException e) {
                                        throw new RuntimeException(e);
                                    }
                                    list.set(integer,i);
//                                Constant.LOGGER.info(integer+ " indexed with "+s);
                                });
                            }
                            GhastInfo ghastInfo = Constant.INFO_LIST.get(UUID.fromString(uuid));
                            ghastInfo.inventories.computeIfAbsent(key, k -> list);
                        }));
                    } else {
                        Constant.LOGGER.warn("Somethings wrong! please contact the dev #fm126");
                    }

                } catch (FileNotFoundException e) {
                    throw new RuntimeException(e);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
//                GhastInfo testInfo = Constant.INFO_LIST.get(UUID.fromString(uuid));
//                Constant.LOGGER.info("Final check: GhastInfo for " + uuid + " has " + testInfo.inventories.size() + " inventories");
            }

        } else {
            Constant.LOGGER.warn("Folder does not exist or is not a directory.");
        }
    }

}
