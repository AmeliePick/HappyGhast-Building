package com.keremyurekli.happyghastbuilding.client;

import com.keremyurekli.happyghastbuilding.Constant;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;


@Environment(EnvType.CLIENT)
public class HappyghastbuildingClient implements ClientModInitializer {

    @Override
    public void onInitializeClient() {
        Constant.LOGGER.info("Starting up client pal...");

    }
}