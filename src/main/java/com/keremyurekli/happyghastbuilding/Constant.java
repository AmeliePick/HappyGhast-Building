package com.keremyurekli.happyghastbuilding;

import net.minecraft.util.Identifier;
import org.jetbrains.annotations.NotNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;



public interface Constant {

    String MOD_ID = "happyghastbuilding";
    Logger LOGGER = LoggerFactory.getLogger(MOD_ID);


    static @NotNull Identifier id(String name) {
        return Identifier.of(MOD_ID, name);
    }
}
