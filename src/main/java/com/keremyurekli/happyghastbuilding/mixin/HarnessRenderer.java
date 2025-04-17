package com.keremyurekli.happyghastbuilding.mixin;

import com.keremyurekli.happyghastbuilding.Constant;
import com.keremyurekli.happyghastbuilding.weirdstuff.BoxWithData;
import com.keremyurekli.happyghastbuilding.weirdstuff.GhastInfo;
import com.keremyurekli.happyghastbuilding.weirdstuff.ICustomClassInterface;
import com.keremyurekli.happyghastbuilding.weirdstuff.OnClick;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.class_11187;
import net.minecraft.class_11261;
import net.minecraft.class_11262;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.render.OverlayTexture;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.block.BlockRenderManager;
import net.minecraft.client.render.entity.*;
import net.minecraft.client.render.entity.model.EntityModel;
import net.minecraft.client.render.entity.state.EntityRenderState;
import net.minecraft.client.render.entity.state.LivingEntityRenderState;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.mob.MobEntity;
import net.minecraft.item.*;
import net.minecraft.registry.Registries;
import net.minecraft.state.property.Properties;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.Vec2f;
import net.minecraft.util.math.Vec3d;
import org.joml.Vector3d;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import static net.minecraft.util.math.RotationAxis.POSITIVE_Y;

@Environment(EnvType.CLIENT)
@Mixin(AgeableMobEntityRenderer.class)
public abstract class HarnessRenderer<T extends MobEntity, S extends LivingEntityRenderState, M extends EntityModel<? super S>>
        extends MobEntityRenderer<T, S, M> {


    public HarnessRenderer(EntityRendererFactory.Context context, M entityModel, float f) {
        super(context, entityModel, f);
    }

    @Inject(method = "render(Lnet/minecraft/client/render/entity/state/EntityRenderState;Lnet/minecraft/client/util/math/MatrixStack;Lnet/minecraft/client/render/VertexConsumerProvider;I)V", at = @At("TAIL"))
    private void onInit(EntityRenderState state, MatrixStack matrices, VertexConsumerProvider vertexConsumers, int light, CallbackInfo ci) {
//        Constant.LOGGER.info(String.valueOf(state.entityType));

        if (state.entityType == EntityType.HAPPY_GHAST) {



            class_11261 a = ((class_11261)(Object)this);
            LivingEntityRenderState lvre = (LivingEntityRenderState) state;
            class_11262 itemData = (class_11262) lvre;

            ICustomClassInterface intf = (ICustomClassInterface)a;
            class_11187 entity = intf.getEntity();

            if (entity != null) {
                if (Constant.INFO_LIST.containsKey(entity.getUuid())) {
//                Constant.LOGGER.info("UHH");
                    if (itemData.field_59978 != ItemStack.EMPTY) {
//                    Constant.LOGGER.info("SECONDSHIt");
                        String translationKey = itemData.field_59978.getItem().getTranslationKey();

                        Vector3d realPos = new Vector3d(state.x,state.y,
                                state.z);

                        // I KNOW THIS SEEMS LIKE A BAD WAY TO CHECK IF ITS THE RIGHT HARNESS TYPE
                        // BUT IM STORING CUSTOM ITEMS SEPARATELY, I DONT WANT TO CHECK THEM ONE BY ONE
                        // actually i can put them in a list while registering but screw it, it stays like this for now
                        GhastInfo ghastInfo = Constant.INFO_LIST.get(entity.getUuid());
//                    ghastInfo.ghastUUID = entity.getUuid();
                        ghastInfo.boxes.clear();
                        if (translationKey.contains("adventurers_harness")) {
                            ghastInfo.ghastType = 0;

                            String color = translationKey.replace("item.happyghastbuilding.","")
                                    .replace("_adventurers_harness","");

                            BoxWithData table = createBlockAt(realPos,state,matrices,vertexConsumers,light,-0.25f,-0.5f, Blocks.CRAFTING_TABLE.getDefaultState());
                            BoxWithData chest  = createBlockAt(realPos,state,matrices,vertexConsumers,light,-1.25f,-0.5f, Blocks.CHEST.getDefaultState());
                            BlockState furnaceState = Blocks.FURNACE.getDefaultState();
                            if (Constant.INFO_LIST.get(ghastInfo.ghastUUID).isLit) {
                                furnaceState = Blocks.FURNACE.getDefaultState().with(Properties.LIT,true);
                            }
                            BoxWithData furnace  = createBlockAt(realPos,state,matrices,vertexConsumers,light,-0.25f,1.5f, furnaceState);

                            table.action = OnClick.CRAFTING_TABLE;
                            table.ghastUUID = ghastInfo.ghastUUID;
//                        chest = new BoxWithData(table.getMinPos().add(0,0,1),table.getMaxPos().add(0,0,1));
                            chest.action = OnClick.CHEST;
                            chest.ghastUUID = ghastInfo.ghastUUID;
                            furnace.action = OnClick.FURNACE;
                            furnace.ghastUUID = ghastInfo.ghastUUID;

                            BoxWithData bed = createBedTop(realPos,state,matrices,vertexConsumers,light,-0.25f,-0.5f, color);

                            bed.ghastUUID = ghastInfo.ghastUUID;
                            bed.action = OnClick.BED;

                            ghastInfo.boxes.add(table);
                            ghastInfo.boxes.add(chest);
                            ghastInfo.boxes.add(furnace);
                            ghastInfo.boxes.add(bed);
//                        createBlockAt(state,matrices,vertexConsumers,light,-1,1, Items.BED);

                        } else if (translationKey.contains("demolition_harness")) {
//                            Constant.LOGGER.info("RENDERING");
                            ghastInfo.ghastType = 1;

                            String color = translationKey.replace("item.happyghastbuilding.","")
                                    .replace("_adventurers_harness","");

                            BoxWithData dispenser1 = createDispenserAt(realPos,state,matrices,vertexConsumers,light,2,3,-1.5f, Blocks.DISPENSER.getDefaultState(),0);
                            BoxWithData dispenser1Indicator = createEmptyBoxAt(realPos,state,matrices,vertexConsumers,light,2,3,-2.5f, Blocks.REDSTONE_BLOCK.getDefaultState());


                            BoxWithData dispenser2 = createDispenserAt(realPos,state,matrices,vertexConsumers,light,2,3,2.5f, Blocks.DISPENSER.getDefaultState(),1);
                            BoxWithData dispenser2Indicator = createEmptyBoxAt(realPos,state,matrices,vertexConsumers,light,2,3,3.5f, Blocks.EMERALD_BLOCK.getDefaultState());


                            BoxWithData dispenser3 = createDispenserAt(realPos,state,matrices,vertexConsumers,light,-1,3,-1.5f, Blocks.DISPENSER.getDefaultState(),0);
                            BoxWithData dispenser3Indicator = createEmptyBoxAt(realPos,state,matrices,vertexConsumers,light,-1,3,-2.5f, Blocks.GLOWSTONE.getDefaultState());

                            BoxWithData dispenser4 = createDispenserAt(realPos,state,matrices,vertexConsumers,light,-1,3,2.5f, Blocks.DISPENSER.getDefaultState(),1);
                            BoxWithData dispenser4Indicator = createEmptyBoxAt(realPos,state,matrices,vertexConsumers,light,-1,3,3.5f, Blocks.DIAMOND_BLOCK.getDefaultState());



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


                    } else {
//                    Constant.LOGGER.info("no item");
                    }

                }
            }

        }
    }


    private Vec2f keepRotationOfImaginaryPos(EntityRenderState state, float x, float z) {
        float xRel = x;
        float yRel = z;
        float rotationRadians = (float) Math.toRadians(-((class_11262) state).bodyYaw);
        float newX = xRel * (float) Math.cos(rotationRadians) - yRel * (float) Math.sin(rotationRadians);//reversed
        float newY = xRel * (float) Math.sin(rotationRadians) + yRel * (float) Math.cos(rotationRadians);//with this

        return new Vec2f(newX,newY);
    }

    private BoxWithData createBlockAt(Vector3d entityPos,EntityRenderState state, MatrixStack matrices, VertexConsumerProvider vertexConsumers, int light, float x, float z, BlockState bs) {
        matrices.push();
        BlockRenderManager blockRenderManager = MinecraftClient.getInstance().getBlockRenderManager();
        // DEVIL AHH MATH I GOT FROM INTERNET AND CHATGPT,  IT WORKS
        // but i don't fully know how
        // but i can understand that y is actually z in my situation
        float xRel = x;
        float yRel = z;
        float rotationRadians = (float) Math.toRadians(-((class_11262) state).bodyYaw);
        float newX = xRel * (float) Math.cos(rotationRadians) - yRel * (float) Math.sin(rotationRadians);//reversed
        float newY = xRel * (float) Math.sin(rotationRadians) + yRel * (float) Math.cos(rotationRadians);//with this
        //


        Vec2f imaginer = keepRotationOfImaginaryPos(state,x-1,z-1);
        Vec3d c1 = new Vec3d(entityPos.x+imaginer.y,entityPos.y+4f+1,entityPos.z+imaginer.x);

        Vec3d c2 = new Vec3d(entityPos.x+newY,entityPos.y+4f,entityPos.z+newX);//sağ alt



        matrices.translate(newY,4f,newX);
        matrices.scale(1f,1f,1f);
        if (bs.getBlock() == Blocks.CHEST) {
            imaginer = keepRotationOfImaginaryPos(state,x+1,z+1);
            c1 = new Vec3d(entityPos.x+imaginer.y,entityPos.y+4f+1,entityPos.z+imaginer.x);

            c2 = new Vec3d(entityPos.x+newY,entityPos.y+4f,entityPos.z+newX);
            matrices.multiply(POSITIVE_Y.rotationDegrees(-((class_11262) state).bodyYaw));
        } else {
            matrices.multiply(POSITIVE_Y.rotationDegrees(-((class_11262) state).bodyYaw + 180));
        }

        blockRenderManager.renderBlockAsEntity(bs,matrices,vertexConsumers,light,OverlayTexture.DEFAULT_UV);
        matrices.pop();


//        Constant.LOGGER.info(c1+"TO"+c2);

        return new BoxWithData(c1,c2);
    }
    private BoxWithData createEmptyBoxAt(Vector3d entityPos,EntityRenderState state, MatrixStack matrices, VertexConsumerProvider vertexConsumers, int light, float x, float parY, float z, BlockState bs) {
        matrices.push();
        BlockRenderManager blockRenderManager = MinecraftClient.getInstance().getBlockRenderManager();
        // DEVIL AHH MATH I GOT FROM INTERNET AND CHATGPT,  IT WORKS
        // but i don't fully know how
        // but i can understand that y is actually z in my situation
        float xRel = x;
        float yRel = z;
        float rotationRadians = (float) Math.toRadians(-((class_11262) state).bodyYaw);
        float newX = xRel * (float) Math.cos(rotationRadians) - yRel * (float) Math.sin(rotationRadians);//reversed
        float newY = xRel * (float) Math.sin(rotationRadians) + yRel * (float) Math.cos(rotationRadians);//with this
        //


        Vec2f imaginer = keepRotationOfImaginaryPos(state,x-1,z-1);
        Vec3d c1 = new Vec3d(entityPos.x+imaginer.y,entityPos.y+parY+1,entityPos.z+imaginer.x);

        Vec3d c2 = new Vec3d(entityPos.x+newY,entityPos.y+parY,entityPos.z+newX);//sağ alt



        matrices.translate(newY,parY,newX);
        matrices.scale(1f,1f,1f);
        if (bs.getBlock() == Blocks.CHEST) {
            imaginer = keepRotationOfImaginaryPos(state,x+1,z+1);
            c1 = new Vec3d(entityPos.x+imaginer.y,entityPos.y+parY+1,entityPos.z+imaginer.x);

            c2 = new Vec3d(entityPos.x+newY,entityPos.y+parY,entityPos.z+newX);
            matrices.multiply(POSITIVE_Y.rotationDegrees(-((class_11262) state).bodyYaw));
        } else {
            matrices.multiply(POSITIVE_Y.rotationDegrees(-((class_11262) state).bodyYaw + 180));
        }

//        blockRenderManager.renderBlockAsEntity(bs,matrices,vertexConsumers,light,OverlayTexture.DEFAULT_UV);
        matrices.pop();


//        Constant.LOGGER.info(c1+"TO"+c2);

        return new BoxWithData(c1,c2);
    }
    private BoxWithData createDispenserAt(Vector3d entityPos, EntityRenderState state, MatrixStack matrices, VertexConsumerProvider vertexConsumers, int light, float x, float parY, float z, BlockState bs, int facing) {
        matrices.push();
        BlockRenderManager blockRenderManager = MinecraftClient.getInstance().getBlockRenderManager();
        // DEVIL AHH MATH I GOT FROM INTERNET AND CHATGPT,  IT WORKS
        // but i don't fully know how
        // but i can understand that y is actually z in my situation
        float xRel = x;
        float yRel = z;
        float rotationRadians = (float) Math.toRadians(-((class_11262) state).bodyYaw);
        float newX = xRel * (float) Math.cos(rotationRadians) - yRel * (float) Math.sin(rotationRadians);//reversed
        float newY = xRel * (float) Math.sin(rotationRadians) + yRel * (float) Math.cos(rotationRadians);//with this
        //

        Vec3d c1;
        Vec3d c2;


        BlockState bState;
        if (facing == 0) {
            bState = bs.with(Properties.FACING, Direction.EAST);
            Vec2f imaginer = keepRotationOfImaginaryPos(state,x-1,z-1f);
            c1 = new Vec3d(entityPos.x+imaginer.y,entityPos.y+parY+1,entityPos.z+imaginer.x);

            Vec2f imaginer2 = keepRotationOfImaginaryPos(state,x,z-0.5f);
            c2 = new Vec3d(entityPos.x+imaginer2.y,entityPos.y+parY,entityPos.z+imaginer2.x);//sağ alt
        } else {
            bState = bs.with(Properties.FACING, Direction.WEST);
            Vec2f imaginer = keepRotationOfImaginaryPos(state,x-1,z-0.5f);
            c1 = new Vec3d(entityPos.x+imaginer.y,entityPos.y+parY+1,entityPos.z+imaginer.x);

            c2 = new Vec3d(entityPos.x+newY,entityPos.y+parY,entityPos.z+newX);//sağ alt
        }
//        if (facing == 0) {
//            imaginer = keepRotationOfImaginaryPos(state,x+1,z+1);
//            c1 = new Vec3d(entityPos.x+imaginer.y,entityPos.y+4f+1,entityPos.z+imaginer.x);
//
//            c2 = new Vec3d(entityPos.x+newY,entityPos.y+4f,entityPos.z+newX);
//
//            matrices.multiply(POSITIVE_Y.rotationDegrees(-((class_11262) state).bodyYaw));
//        } else {
//            matrices.multiply(POSITIVE_Y.rotationDegrees(-((class_11262) state).bodyYaw + 180));
//        }


        matrices.translate(newY,parY,newX);
        matrices.scale(1f,1f,1f);
        matrices.multiply(POSITIVE_Y.rotationDegrees(-((class_11262) state).bodyYaw + 180));

        blockRenderManager.renderBlockAsEntity(bState,matrices,vertexConsumers,light,OverlayTexture.DEFAULT_UV);
        matrices.pop();


//        Constant.LOGGER.info(c1+"TO"+c2);

        return new BoxWithData(c1,c2);
    }



    // No need for the bottom part because i found out that when you try to render bed with BlockRenderManager,
    // it just renders both, nice
    private BoxWithData createBedTop(Vector3d entityPos,EntityRenderState state, MatrixStack matrices, VertexConsumerProvider vertexConsumers, int light, float x, float z, String color) {
        matrices.push();
        BlockRenderManager blockRenderManager = MinecraftClient.getInstance().getBlockRenderManager();

        BlockState bs = Registries.BLOCK.get(Identifier.ofVanilla(color+"_bed")).getDefaultState();
        // DEVIL AHH MATH I GOT FROM INTERNET AND CHATGPT,  IT WORKS
        // but i don't fully know how
        // but i can understand that y is actually z in my situation
        float xRel = x;
        float yRel = z;
        float rotationRadians = (float) Math.toRadians(-((class_11262) state).bodyYaw);
        float newX = xRel * (float) Math.cos(rotationRadians) - yRel * (float) Math.sin(rotationRadians);//reversed
        float newY = xRel * (float) Math.sin(rotationRadians) + yRel * (float) Math.cos(rotationRadians);//with this
        //

        Vec2f imaginer = keepRotationOfImaginaryPos(state,x+1,z-1);
        Vec3d c1 = new Vec3d(entityPos.x+imaginer.y,entityPos.y+4f+0.5,entityPos.z+imaginer.x);

        Vec2f imaginer2 = keepRotationOfImaginaryPos(state,x,z+1);
        Vec3d c2 = new Vec3d(entityPos.x+imaginer2.y,entityPos.y+4f,entityPos.z+imaginer2.x);

        matrices.translate(newY,4f,newX);
        matrices.scale(1f,1f,1f);
        matrices.multiply(POSITIVE_Y.rotationDegrees(-((class_11262) state).bodyYaw - 90));

        blockRenderManager.renderBlockAsEntity(bs,matrices,vertexConsumers,light,OverlayTexture.DEFAULT_UV);
        matrices.pop();




        return new BoxWithData(c1,c2);
    }

}