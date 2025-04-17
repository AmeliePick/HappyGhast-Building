package com.keremyurekli.happyghastbuilding.client;

import com.keremyurekli.happyghastbuilding.networking.DispenserKeybindPayload;
import com.keremyurekli.happyghastbuilding.networking.NetworkingManager;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.screen.ingame.FurnaceScreen;
import net.minecraft.client.gui.screen.ingame.Generic3x3ContainerScreen;
import net.minecraft.client.gui.screen.ingame.GenericContainerScreen;
import net.minecraft.client.gui.screen.ingame.HandledScreens;
import net.minecraft.client.option.KeyBinding;
import net.minecraft.client.util.InputUtil;
import org.lwjgl.glfw.GLFW;

import static com.keremyurekli.happyghastbuilding.helpers.ScreenHelper.*;




@Environment(EnvType.CLIENT)
public class HappyghastbuildingClient implements ClientModInitializer {
    private static final int KEY_COOLDOWN_TICKS = 40;//2secs
    private static int keyCooldown = 0;
    private static KeyBinding keyBinding;
//    private ScreenHandlerTest test;
    @Override
    public void onInitializeClient() {
//        test = new ScreenHandlerTest();

        NetworkingManager.imClient();
        HandledScreens.register(PORTABLE_CHEST_SCREEN_HANDLER, GenericContainerScreen::new);
        HandledScreens.register(PORTABLE_FURNACE_SCREEN_HANDLER, FurnaceScreen::new);
        HandledScreens.register(PORTABLE_DISPENSER_SCREEN_HANDLER, Generic3x3ContainerScreen::new);


        keyBinding = KeyBindingHelper.registerKeyBinding(new KeyBinding(
                "key.happyghastbuilding.dispenser", // The translation key of the keybinding's name
                InputUtil.Type.KEYSYM, // The type of the keybinding, KEYSYM for keyboard, MOUSE for mouse.
                GLFW.GLFW_KEY_R, // The keycode of the key
                "category.happyghastbuilding.main" // The translation key of the keybinding's category.
        ));
        ClientTickEvents.END_CLIENT_TICK.register(client -> {
            if (keyCooldown > 0) {
                keyCooldown--;
            }
            if (MinecraftClient.getInstance().player != null) {
                while (keyBinding.wasPressed()) {
                    if (keyCooldown == 0) {
                        NetworkingManager.sendKeybindingPayload(new DispenserKeybindPayload(MinecraftClient.getInstance().player.getUuid()));
                        keyCooldown = KEY_COOLDOWN_TICKS;
                    } else {
                        //cooldown
                    }
                }
            }

        });
//        HandledScreens.register(BAG_SCREEN_HANDLER, BagScreenHandler::new);
//        Registry.register(Registries.SCREEN_HANDLER, Constant.id("bag"), ScreenHandlerTest.BAG_SCREEN_HANDLER);
//        Constant.LOGGER.info("Starting up client pal...");
//        NetworkingManager.registerPayloads();
    }
}