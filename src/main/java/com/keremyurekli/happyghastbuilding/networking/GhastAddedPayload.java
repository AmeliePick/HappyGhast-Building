package com.keremyurekli.happyghastbuilding.networking;

import com.keremyurekli.happyghastbuilding.Constant;
import com.keremyurekli.happyghastbuilding.weirdstuff.BoxWithData;
import com.keremyurekli.happyghastbuilding.weirdstuff.GhastInfo;
import net.minecraft.item.ItemStack;
import net.minecraft.network.RegistryByteBuf;
import net.minecraft.network.codec.PacketCodec;
import net.minecraft.network.codec.PacketCodecs;
import net.minecraft.network.packet.CustomPayload;
import net.minecraft.util.Uuids;
import net.minecraft.util.math.BlockPos;
import org.joml.Vector3f;

import java.util.List;
import java.util.UUID;

import static net.minecraft.client.option.InactivityFpsLimit.Codec;

//public record GhastAddedPayload(UUID ghastId, GhastInfo info) implements CustomPayload {
//    public static final Id<GhastAddedPayload> ID = new Id<>(Constant.id(Constant.GHAST_ADDED_PACKET));
////    public static final PacketCodec<RegistryByteBuf, ClickPayload> CODEC = PacketCodec.tuple(BlockPos.PACKET_CODEC, ClickPayload::blockPos, ClickPayload::new);
//    // should you need to send more data, add the appropriate record parameters and change your codec:
//     public static final PacketCodec<RegistryByteBuf, GhastAddedPayload> CODEC = PacketCodec.tuple(
//             Uuids.PACKET_CODEC, GhastAddedPayload::ghastId,
//             GhastInfo.PACKET_CODEC, GhastAddedPayload::info,
//             GhastAddedPayload::new
//     );
public record GhastAddedPayload(UUID ghastId) implements CustomPayload {
    public static final Id<GhastAddedPayload> ID = new Id<>(Constant.id(Constant.GHAST_ADDED_PACKET));
    //    public static final PacketCodec<RegistryByteBuf, ClickPayload> CODEC = PacketCodec.tuple(BlockPos.PACKET_CODEC, ClickPayload::blockPos, ClickPayload::new);
    // should you need to send more data, add the appropriate record parameters and change your codec:
    public static final PacketCodec<RegistryByteBuf, GhastAddedPayload> CODEC = PacketCodec.tuple(
            Uuids.PACKET_CODEC, GhastAddedPayload::ghastId,
            GhastAddedPayload::new
    );
    @Override
    public Id<? extends CustomPayload> getId() {
        return ID;
    }
}