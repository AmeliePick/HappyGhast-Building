package com.keremyurekli.happyghastbuilding.mixin;

import com.keremyurekli.happyghastbuilding.Constant;
import com.keremyurekli.happyghastbuilding.weirdstuff.BoxWithData;
import com.keremyurekli.happyghastbuilding.weirdstuff.GhastInfo;
import com.keremyurekli.happyghastbuilding.weirdstuff.ICustomClassInterface;
import com.keremyurekli.happyghastbuilding.weirdstuff.OnClick;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.class_10017;
import net.minecraft.class_10042;
import net.minecraft.class_11187;
import net.minecraft.class_11261;
import net.minecraft.class_11262;
import net.minecraft.class_1299;
import net.minecraft.class_1308;
import net.minecraft.class_1799;
import net.minecraft.class_2246;
import net.minecraft.class_2350;
import net.minecraft.class_241;
import net.minecraft.class_243;
import net.minecraft.class_2680;
import net.minecraft.class_2741;
import net.minecraft.class_2769;
import net.minecraft.class_310;
import net.minecraft.class_4587;
import net.minecraft.class_4597;
import net.minecraft.class_4608;
import net.minecraft.class_5617;
import net.minecraft.class_583;
import net.minecraft.class_776;
import net.minecraft.class_7833;
import net.minecraft.class_927;
import net.minecraft.class_9990;
import org.joml.Quaternionfc;
import org.joml.Vector3d;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Environment(value=EnvType.CLIENT)
@Mixin(value={class_9990.class})
public abstract class HarnessRenderer<T extends class_1308, S extends class_10042, M extends class_583<? super S>>
extends class_927<T, S, M> {
    public HarnessRenderer(class_5617.class_5618 context, M entityModel, float f) {
        super(context, entityModel, f);
    }

    @Inject(method={"render(Lnet/minecraft/client/render/entity/state/EntityRenderState;Lnet/minecraft/client/util/math/MatrixStack;Lnet/minecraft/client/render/VertexConsumerProvider;I)V"}, at={@At(value="TAIL")})
    private void onInit(class_10017 state, class_4587 matrices, class_4597 vertexConsumers, int light, CallbackInfo ci) {
        if (state.field_58171 == class_1299.field_59668) {
            class_11261 a = (class_11261)this;
            class_10042 lvre = (class_10042)state;
            class_11262 itemData = (class_11262)lvre;
            ICustomClassInterface intf = (ICustomClassInterface)a;
            class_11187 entity = intf.getEntity();
            if (entity != null && Constant.INFO_LIST.containsKey(entity.method_5667()) && itemData.field_59978 != class_1799.field_8037) {
                String translationKey = itemData.field_59978.method_7909().method_7876();
                Vector3d realPos = new Vector3d(state.field_53325, state.field_53326, state.field_53327);
                GhastInfo ghastInfo = (GhastInfo)Constant.INFO_LIST.get(entity.method_5667());
                ghastInfo.boxes.clear();
                if (translationKey.contains("adventurers_harness")) {
                    ghastInfo.ghastType = 0;
                    String color = translationKey.replace("item.happyghastbuilding.", "").replace("_adventurers_harness", "");
                    BoxWithData table = this.createBlockAt(realPos, state, matrices, vertexConsumers, light, -0.25f, -0.5f, class_2246.field_9980.method_9564());
                    class_2680 furnaceState = class_2246.field_10181.method_9564();
                    if (((GhastInfo)Constant.INFO_LIST.get((Object)ghastInfo.ghastUUID)).isLit) {
                        furnaceState = (class_2680)class_2246.field_10181.method_9564().method_11657((class_2769)class_2741.field_12548, (Comparable)Boolean.valueOf(true));
                    }
                    BoxWithData furnace = this.createBlockAt(realPos, state, matrices, vertexConsumers, light, -0.25f, 1.5f, furnaceState);
                    table.action = OnClick.CRAFTING_TABLE;
                    table.ghastUUID = ghastInfo.ghastUUID;
                    furnace.action = OnClick.FURNACE;
                    furnace.ghastUUID = ghastInfo.ghastUUID;
                    ghastInfo.boxes.add(table);
                    ghastInfo.boxes.add(furnace);
                } else if (translationKey.contains("demolition_harness")) {
                    ghastInfo.ghastType = 1;
                    String color = translationKey.replace("item.happyghastbuilding.", "").replace("_adventurers_harness", "");
                    BoxWithData dispenser1 = this.createDispenserAt(realPos, state, matrices, vertexConsumers, light, 2.0f, 3.0f, -1.5f, class_2246.field_10200.method_9564(), 0);
                    BoxWithData dispenser1Indicator = this.createEmptyBoxAt(realPos, state, matrices, vertexConsumers, light, 2.0f, 3.0f, -2.5f, class_2246.field_10002.method_9564());
                    BoxWithData dispenser2 = this.createDispenserAt(realPos, state, matrices, vertexConsumers, light, 2.0f, 3.0f, 2.5f, class_2246.field_10200.method_9564(), 1);
                    BoxWithData dispenser2Indicator = this.createEmptyBoxAt(realPos, state, matrices, vertexConsumers, light, 2.0f, 3.0f, 3.5f, class_2246.field_10234.method_9564());
                    BoxWithData dispenser3 = this.createDispenserAt(realPos, state, matrices, vertexConsumers, light, -1.0f, 3.0f, -1.5f, class_2246.field_10200.method_9564(), 0);
                    BoxWithData dispenser3Indicator = this.createEmptyBoxAt(realPos, state, matrices, vertexConsumers, light, -1.0f, 3.0f, -2.5f, class_2246.field_10171.method_9564());
                    BoxWithData dispenser4 = this.createDispenserAt(realPos, state, matrices, vertexConsumers, light, -1.0f, 3.0f, 2.5f, class_2246.field_10200.method_9564(), 1);
                    BoxWithData dispenser4Indicator = this.createEmptyBoxAt(realPos, state, matrices, vertexConsumers, light, -1.0f, 3.0f, 3.5f, class_2246.field_10201.method_9564());
                    dispenser1.action = OnClick.DISPENSER1;
                    dispenser1Indicator.action = OnClick.DI1;
                    dispenser2.action = OnClick.DISPENSER2;
                    dispenser2Indicator.action = OnClick.DI2;
                    dispenser3.action = OnClick.DISPENSER3;
                    dispenser3Indicator.action = OnClick.DI3;
                    dispenser4.action = OnClick.DISPENSER4;
                    dispenser4Indicator.action = OnClick.DI4;
                    dispenser1.ghastUUID = ghastInfo.ghastUUID;
                    dispenser1Indicator.ghastUUID = ghastInfo.ghastUUID;
                    dispenser2.ghastUUID = ghastInfo.ghastUUID;
                    dispenser2Indicator.ghastUUID = ghastInfo.ghastUUID;
                    dispenser3.ghastUUID = ghastInfo.ghastUUID;
                    dispenser3Indicator.ghastUUID = ghastInfo.ghastUUID;
                    dispenser4.ghastUUID = ghastInfo.ghastUUID;
                    dispenser4Indicator.ghastUUID = ghastInfo.ghastUUID;
                    ghastInfo.boxes.add(dispenser1);
                    ghastInfo.boxes.add(dispenser2);
                    ghastInfo.boxes.add(dispenser3);
                    ghastInfo.boxes.add(dispenser4);
                    ghastInfo.boxes.add(dispenser1Indicator);
                    ghastInfo.boxes.add(dispenser2Indicator);
                    ghastInfo.boxes.add(dispenser3Indicator);
                    ghastInfo.boxes.add(dispenser4Indicator);
                }
            }
        }
    }

    private class_241 keepRotationOfImaginaryPos(class_10017 state, float x, float z) {
        float xRel = x;
        float yRel = z;
        float rotationRadians = (float)Math.toRadians(-((class_11262)state).field_53446);
        float newX = xRel * (float)Math.cos(rotationRadians) - yRel * (float)Math.sin(rotationRadians);
        float newY = xRel * (float)Math.sin(rotationRadians) + yRel * (float)Math.cos(rotationRadians);
        return new class_241(newX, newY);
    }

    private BoxWithData createBlockAt(Vector3d entityPos, class_10017 state, class_4587 matrices, class_4597 vertexConsumers, int light, float x, float z, class_2680 bs) {
        matrices.method_22903();
        class_776 blockRenderManager = class_310.method_1551().method_1541();
        float xRel = x;
        float yRel = z;
        float rotationRadians = (float)Math.toRadians(-((class_11262)state).field_53446);
        float newX = xRel * (float)Math.cos(rotationRadians) - yRel * (float)Math.sin(rotationRadians);
        float newY = xRel * (float)Math.sin(rotationRadians) + yRel * (float)Math.cos(rotationRadians);
        class_241 imaginer = this.keepRotationOfImaginaryPos(state, x - 1.0f, z - 1.0f);
        class_243 c1 = new class_243(entityPos.x + (double)imaginer.field_1342, entityPos.y + 4.0 + 1.0, entityPos.z + (double)imaginer.field_1343);
        class_243 c2 = new class_243(entityPos.x + (double)newY, entityPos.y + 4.0, entityPos.z + (double)newX);
        matrices.method_46416(newY, 4.0f, newX);
        matrices.method_22905(1.0f, 1.0f, 1.0f);
        if (bs.method_26204() == class_2246.field_10034) {
            imaginer = this.keepRotationOfImaginaryPos(state, x + 1.0f, z + 1.0f);
            c1 = new class_243(entityPos.x + (double)imaginer.field_1342, entityPos.y + 4.0 + 1.0, entityPos.z + (double)imaginer.field_1343);
            c2 = new class_243(entityPos.x + (double)newY, entityPos.y + 4.0, entityPos.z + (double)newX);
            matrices.method_22907((Quaternionfc)class_7833.field_40716.rotationDegrees(-((class_11262)state).field_53446));
        } else {
            matrices.method_22907((Quaternionfc)class_7833.field_40716.rotationDegrees(-((class_11262)state).field_53446 + 180.0f));
        }
        blockRenderManager.method_3353(bs, matrices, vertexConsumers, light, class_4608.field_21444);
        matrices.method_22909();
        return new BoxWithData(c1, c2);
    }

    private BoxWithData createEmptyBoxAt(Vector3d entityPos, class_10017 state, class_4587 matrices, class_4597 vertexConsumers, int light, float x, float parY, float z, class_2680 bs) {
        matrices.method_22903();
        class_776 blockRenderManager = class_310.method_1551().method_1541();
        float xRel = x;
        float yRel = z;
        float rotationRadians = (float)Math.toRadians(-((class_11262)state).field_53446);
        float newX = xRel * (float)Math.cos(rotationRadians) - yRel * (float)Math.sin(rotationRadians);
        float newY = xRel * (float)Math.sin(rotationRadians) + yRel * (float)Math.cos(rotationRadians);
        class_241 imaginer = this.keepRotationOfImaginaryPos(state, x - 1.0f, z - 1.0f);
        class_243 c1 = new class_243(entityPos.x + (double)imaginer.field_1342, entityPos.y + (double)parY + 1.0, entityPos.z + (double)imaginer.field_1343);
        class_243 c2 = new class_243(entityPos.x + (double)newY, entityPos.y + (double)parY, entityPos.z + (double)newX);
        matrices.method_46416(newY, parY, newX);
        matrices.method_22905(1.0f, 1.0f, 1.0f);
        if (bs.method_26204() == class_2246.field_10034) {
            imaginer = this.keepRotationOfImaginaryPos(state, x + 1.0f, z + 1.0f);
            c1 = new class_243(entityPos.x + (double)imaginer.field_1342, entityPos.y + (double)parY + 1.0, entityPos.z + (double)imaginer.field_1343);
            c2 = new class_243(entityPos.x + (double)newY, entityPos.y + (double)parY, entityPos.z + (double)newX);
            matrices.method_22907((Quaternionfc)class_7833.field_40716.rotationDegrees(-((class_11262)state).field_53446));
        } else {
            matrices.method_22907((Quaternionfc)class_7833.field_40716.rotationDegrees(-((class_11262)state).field_53446 + 180.0f));
        }
        matrices.method_22909();
        return new BoxWithData(c1, c2);
    }

    private BoxWithData createDispenserAt(Vector3d entityPos, class_10017 state, class_4587 matrices, class_4597 vertexConsumers, int light, float x, float parY, float z, class_2680 bs, int facing) {
        class_243 c2;
        class_243 c1;
        class_2680 bState;
        matrices.method_22903();
        class_776 blockRenderManager = class_310.method_1551().method_1541();
        float xRel = x;
        float yRel = z;
        float rotationRadians = (float)Math.toRadians(-((class_11262)state).field_53446);
        float newX = xRel * (float)Math.cos(rotationRadians) - yRel * (float)Math.sin(rotationRadians);
        float newY = xRel * (float)Math.sin(rotationRadians) + yRel * (float)Math.cos(rotationRadians);
        if (facing == 0) {
            bState = (class_2680)bs.method_11657((class_2769)class_2741.field_12525, (Comparable)class_2350.field_11034);
            class_241 imaginer = this.keepRotationOfImaginaryPos(state, x - 1.0f, z - 1.0f);
            c1 = new class_243(entityPos.x + (double)imaginer.field_1342, entityPos.y + (double)parY + 1.0, entityPos.z + (double)imaginer.field_1343);
            class_241 imaginer2 = this.keepRotationOfImaginaryPos(state, x, z - 0.5f);
            c2 = new class_243(entityPos.x + (double)imaginer2.field_1342, entityPos.y + (double)parY, entityPos.z + (double)imaginer2.field_1343);
        } else {
            bState = (class_2680)bs.method_11657((class_2769)class_2741.field_12525, (Comparable)class_2350.field_11039);
            class_241 imaginer = this.keepRotationOfImaginaryPos(state, x - 1.0f, z - 0.5f);
            c1 = new class_243(entityPos.x + (double)imaginer.field_1342, entityPos.y + (double)parY + 1.0, entityPos.z + (double)imaginer.field_1343);
            c2 = new class_243(entityPos.x + (double)newY, entityPos.y + (double)parY, entityPos.z + (double)newX);
        }
        matrices.method_46416(newY, parY, newX);
        matrices.method_22905(1.0f, 1.0f, 1.0f);
        matrices.method_22907((Quaternionfc)class_7833.field_40716.rotationDegrees(-((class_11262)state).field_53446 + 180.0f));
        blockRenderManager.method_3353(bState, matrices, vertexConsumers, light, class_4608.field_21444);
        matrices.method_22909();
        return new BoxWithData(c1, c2);
    }
}
