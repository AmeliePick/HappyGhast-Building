package com.keremyurekli.happyghastbuilding.helpers;

import com.keremyurekli.happyghastbuilding.Constant;
import com.keremyurekli.happyghastbuilding.helpers.GhastHelper;
import com.keremyurekli.happyghastbuilding.weirdstuff.GhastInfo;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import com.mojang.serialization.DataResult;
import com.mojang.serialization.DynamicOps;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.Writer;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import net.minecraft.class_1799;
import net.minecraft.class_2371;
import net.minecraft.class_2509;
import net.minecraft.class_2520;
import net.minecraft.class_5455;
import org.yaml.snakeyaml.Yaml;

public class FileManager {
    private static final String SUBFOLDER_NAME = "/happyghastbuilding/";

    public static void save(Path path) {
        File folder = new File(String.valueOf(path) + SUBFOLDER_NAME);
        if (folder.mkdirs()) {
            Constant.LOGGER.info("Folder created successfully: " + folder.getPath());
        } else {
            Constant.LOGGER.warn("Folder already exists or could not be created.");
        }
        Constant.INFO_LIST.forEach((uuid, ghastInfo) -> {
            File target = new File(folder, String.valueOf(uuid) + ".yml");
            try (FileWriter writer = new FileWriter(target, false);){
                Yaml yaml = new Yaml();
                ghastInfo.inventories.forEach((s, itemStacks) -> yaml.dump(FileManager.saveInventoryWithCodec(s, itemStacks), (Writer)writer));
            }
            catch (IOException e) {
                e.printStackTrace();
            }
        });
    }

    public static Map<String, Map<Integer, String>> saveInventoryWithCodec(String name, List<class_1799> inventory) {
        HashMap<Integer, String> datas = new HashMap<Integer, String>();
        for (int i = 0; i < inventory.size(); ++i) {
            class_1799 stack = inventory.get(i);
            if (stack.method_7960()) continue;
            DataResult nbt = class_1799.field_24671.encode((Object)stack, (DynamicOps)class_2509.field_11560, (Object)class_2509.field_11560.method_10668());
            String string = "";
            if (!nbt.result().isPresent()) {
                throw new AssertionError(nbt.error().get());
            }
            string = ((class_2520)nbt.result().get()).toString();
            datas.put(i, string);
        }
        HashMap<String, Map<Integer, String>> dataMap = new HashMap<String, Map<Integer, String>>();
        dataMap.put(name, datas);
        return dataMap;
    }

    public static void destroyExtras(Path path) {
        File folder = new File(String.valueOf(path) + SUBFOLDER_NAME);
        File[] ymlFiles = folder.listFiles((dir, name) -> name.toLowerCase().endsWith(".yml"));
        Constant.INFO_LIST.clear();
        ArrayList<File> DESTROYABLES = new ArrayList<File>();
        if (ymlFiles != null) {
            for (File file : ymlFiles) {
                String uuid = file.getName().replaceFirst("[.][^.]+$", "");
                if (Constant.INFO_LIST.containsKey(UUID.fromString(uuid))) continue;
                DESTROYABLES.add(file);
            }
        } else {
            Constant.LOGGER.warn("Folder does not exist or is not a directory.");
        }
        for (int i = 0; i < DESTROYABLES.size(); ++i) {
            ((File)DESTROYABLES.get(i)).delete();
        }
    }

    public static void load(Path path, class_5455.class_6890 registryManager) {
        File folder = new File(String.valueOf(path) + SUBFOLDER_NAME);
        File[] ymlFiles = folder.listFiles((dir, name) -> name.toLowerCase().endsWith(".yml"));
        Constant.INFO_LIST.clear();
        if (ymlFiles != null) {
            for (File file : ymlFiles) {
                String uuid = file.getName().replaceFirst("[.][^.]+$", "");
                GhastInfo info = GhastHelper.addNewGhast((UUID)UUID.fromString(uuid));
                Yaml yaml = new Yaml();
                try (FileInputStream input = new FileInputStream(file);){
                    Map data = (Map)yaml.load((InputStream)input);
                    if (data != null) {
                        data.forEach((key, integerStringMap) -> {
                            Object list = key.contains("dispenser") ? class_2371.method_10213((int)9, (Object)class_1799.field_8037) : (key.contains("chest") ? class_2371.method_10213((int)27, (Object)class_1799.field_8037) : (key.contains("furnace") ? class_2371.method_10213((int)3, (Object)class_1799.field_8037) : null));
                            if (!integerStringMap.isEmpty()) {
                                integerStringMap.forEach((arg_0, arg_1) -> FileManager.lambda$load$4(registryManager, (class_2371)list, arg_0, arg_1));
                            }
                            GhastInfo ghastInfo = (GhastInfo)Constant.INFO_LIST.get(UUID.fromString(uuid));
                            ghastInfo.inventories.computeIfAbsent(key, arg_0 -> FileManager.lambda$load$5((class_2371)list, arg_0));
                        });
                        continue;
                    }
                    Constant.LOGGER.warn("Somethings wrong! please contact the dev #fm126");
                }
                catch (FileNotFoundException e) {
                    throw new RuntimeException(e);
                }
                catch (IOException e2) {
                    throw new RuntimeException(e2);
                }
            }
        } else {
            Constant.LOGGER.warn("Folder does not exist or is not a directory.");
        }
    }

    private static /* synthetic */ List lambda$load$5(class_2371 list, String k) {
        return list;
    }

    private static /* synthetic */ void lambda$load$4(class_5455.class_6890 registryManager, class_2371 list, Integer integer, String s) {
        try {
            return;
        }
        catch (CommandSyntaxException e) {
            throw new RuntimeException(e);
        }
    }
}
