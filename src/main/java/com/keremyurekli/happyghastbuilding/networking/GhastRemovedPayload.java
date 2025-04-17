package com.keremyurekli.happyghastbuilding.networking;

import com.keremyurekli.happyghastbuilding.Constant;
import com.keremyurekli.happyghastbuilding.weirdstuff.GhastInfo;
import net.minecraft.network.RegistryByteBuf;
import net.minecraft.network.codec.PacketCodec;
import net.minecraft.network.packet.CustomPayload;
import net.minecraft.util.Uuids;

import java.util.UUID;

public record GhastRemovedPayload(UUID ghastId) implements CustomPayload {
    public static final Id<GhastRemovedPayload> ID = new Id<>(Constant.id(Constant.GHAST_REMOVED_PACKET));
//    public static final PacketCodec<RegistryByteBuf, ClickPayload> CODEC = PacketCodec.tuple(BlockPos.PACKET_CODEC, ClickPayload::blockPos, ClickPayload::new);
    // should you need to send more data, add the appropriate record parameters and change your codec:
     public static final PacketCodec<RegistryByteBuf, GhastRemovedPayload> CODEC = PacketCodec.tuple(
             Uuids.PACKET_CODEC, GhastRemovedPayload::ghastId,
             GhastRemovedPayload::new
     );

    @Override
    public Id<? extends CustomPayload> getId() {
        return ID;
    }
}