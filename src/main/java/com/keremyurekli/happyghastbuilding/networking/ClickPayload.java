package com.keremyurekli.happyghastbuilding.networking;

import com.keremyurekli.happyghastbuilding.Constant;
import net.minecraft.network.RegistryByteBuf;
import net.minecraft.network.codec.PacketCodec;
import net.minecraft.network.codec.PacketCodecs;
import net.minecraft.network.packet.CustomPayload;
import net.minecraft.util.Uuids;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import org.joml.Vector3d;
import org.joml.Vector3f;

import java.util.UUID;

public record ClickPayload(UUID player, UUID ghastId, Vector3f pos, String action) implements CustomPayload {
    public static final CustomPayload.Id<ClickPayload> ID = new CustomPayload.Id<>(Constant.id(Constant.CLICK_PACKET));
//    public static final PacketCodec<RegistryByteBuf, ClickPayload> CODEC = PacketCodec.tuple(BlockPos.PACKET_CODEC, ClickPayload::blockPos, ClickPayload::new);
    // should you need to send more data, add the appropriate record parameters and change your codec:
     public static final PacketCodec<RegistryByteBuf, ClickPayload> CODEC = PacketCodec.tuple(
             Uuids.PACKET_CODEC, ClickPayload::player,
             Uuids.PACKET_CODEC, ClickPayload::ghastId,
             PacketCodecs.VECTOR_3F, ClickPayload::pos,
             PacketCodecs.STRING, ClickPayload::action,
             ClickPayload::new
     );

    @Override
    public CustomPayload.Id<? extends CustomPayload> getId() {
        return ID;
    }
}