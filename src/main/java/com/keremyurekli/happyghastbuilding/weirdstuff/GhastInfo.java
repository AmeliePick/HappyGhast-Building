package com.keremyurekli.happyghastbuilding.weirdstuff;


import io.netty.buffer.ByteBuf;
import net.minecraft.item.ItemStack;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.network.RegistryByteBuf;
import net.minecraft.network.codec.PacketCodec;
import net.minecraft.network.codec.PacketCodecs;
import net.minecraft.network.encoding.StringEncoding;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Box;

import java.util.*;

public class GhastInfo {


    public UUID ghastUUID;

    public int ghastType;

    public List<BoxWithData> boxes = new ArrayList<>();


    public GhastInfo(UUID u) {
        this.ghastUUID = u;
    }

    public HashMap<String,List<ItemStack>> inventories = new HashMap<>();

    public boolean isLit = false;//no need to transfer it

    public static final PacketCodec<ByteBuf, GhastInfo> PACKET_CODEC = new PacketCodec<ByteBuf, GhastInfo>(){

        @Override
        public void encode(ByteBuf buf, GhastInfo value) {
            PacketByteBuf.writeUuid(buf,value.ghastUUID);
            buf.writeInt(value.ghastType);
            buf.writeInt(value.boxes.size());
            for (int i = 0; i < value.boxes.size(); i++) {
                BoxWithData b = value.boxes.get(i);
                PacketByteBuf.writeVec3d(buf,b.getMaxPos());
                PacketByteBuf.writeVec3d(buf,b.getMinPos());
                StringEncoding.encode(buf, b.action.label, Short.MAX_VALUE);
            }
            //bu ikisini birlestirip kompaktlastirabilirim, label zaten stringle aynı
            buf.writeInt(value.inventories.keySet().size());
            List<String> keys = new ArrayList<>(value.inventories.keySet());
            for (int i = 0; i < keys.size(); i++) {
                String key = keys.get(i);
                List<ItemStack> val = value.inventories.get(key);
                StringEncoding.encode(buf, key, Short.MAX_VALUE);
                buf.writeInt(val.size());
                for (int j = 0; j < val.size(); j++) {
                    ItemStack.PACKET_CODEC.encode((RegistryByteBuf) buf,val.get(j));
                }
//                StringEncoding.
            }
            //DONE
        }

        @Override
        public GhastInfo decode(ByteBuf buf) {
            UUID ghastUUID = PacketByteBuf.readUuid(buf);
            GhastInfo info = new GhastInfo(ghastUUID);
            info.ghastType = buf.readInt();
            int howManyBox = buf.readInt();
            for (int i = 0; i < howManyBox; i++) {
                BoxWithData b = new BoxWithData(PacketByteBuf.readVec3d(buf),PacketByteBuf.readVec3d(buf));
                b.action = OnClick.valueOf(StringEncoding.decode(buf,Short.MAX_VALUE));
                b.ghastUUID = ghastUUID;
                info.boxes.add(b);
            }
            int howManyInventories = buf.readInt();
            for (int i = 0; i < howManyInventories; i++) {
                String string = StringEncoding.decode(buf,Short.MAX_VALUE);//storage name

                List<ItemStack> items = new ArrayList<>();
                int howManyItems = buf.readInt();
                for (int j = 0; j < howManyItems; j++) {
                    ItemStack item = ItemStack.PACKET_CODEC.decode((RegistryByteBuf) buf);
                    items.add(item);
                }
                info.inventories.put(string,items);
            }

            return info;
        }
    };

    //0 chest
    //1 furnace

    //or

    //0 dispenser1
    //1 dispenser2
    //2 dispenser3
    //3 dispenser4



    //this was a miserable idea
//    public Box craftingTable;
//    public Box chest;
//    public Box furnace;

}
