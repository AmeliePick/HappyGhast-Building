package com.keremyurekli.happyghastbuilding.client;

import com.keremyurekli.happyghastbuilding.Constant;
import net.fabricmc.api.ClientModInitializer;

public class HappyghastbuildingClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        Constant.LOGGER.info("Starting up client pal...");
    }
}