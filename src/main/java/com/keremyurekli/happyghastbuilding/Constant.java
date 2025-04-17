package com.keremyurekli.happyghastbuilding;

import com.keremyurekli.happyghastbuilding.weirdstuff.GhastInfo;
import net.minecraft.util.Identifier;
import org.jetbrains.annotations.NotNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.UUID;


public interface Constant {

    public static final String MOD_ID = "happyghastbuilding";
    public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

    public static final String CLICK_PACKET = "clickpacket";

    public static final String KEYBINDING_PACKET = "dispenserkeypacket";

    public static final String GHAST_ADDED_PACKET = "ghastaddedpacket";
    public static final String GHAST_REMOVED_PACKET = "ghastremovedpacket";

    public static final String FURNACE_STATE_PACKET = "furnacestatepacket";

    public static final HashMap<UUID, GhastInfo> INFO_LIST = new HashMap<>();


    public static @NotNull Identifier id(String name) {
        return Identifier.of(MOD_ID, name);
    }
}
