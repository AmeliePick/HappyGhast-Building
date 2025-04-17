package com.keremyurekli.happyghastbuilding.networking;

import com.keremyurekli.happyghastbuilding.Constant;
import net.minecraft.network.RegistryByteBuf;
import net.minecraft.network.codec.PacketCodec;
import net.minecraft.network.codec.PacketCodecs;
import net.minecraft.network.packet.CustomPayload;
import net.minecraft.util.Uuids;
import org.joml.Vector3f;

import java.util.UUID;

public record DispenserKeybindPayload(UUID player) implements CustomPayload {
    public static final Id<DispenserKeybindPayload> ID = new Id<>(Constant.id(Constant.KEYBINDING_PACKET));
//    public static final PacketCodec<RegistryByteBuf, ClickPayload> CODEC = PacketCodec.tuple(BlockPos.PACKET_CODEC, ClickPayload::blockPos, ClickPayload::new);
    // should you need to send more data, add the appropriate record parameters and change your codec:
     public static final PacketCodec<RegistryByteBuf, DispenserKeybindPayload> CODEC = PacketCodec.tuple(
             Uuids.PACKET_CODEC, DispenserKeybindPayload::player,
             DispenserKeybindPayload::new
     );

    @Override
    public Id<? extends CustomPayload> getId() {
        return ID;
    }
}