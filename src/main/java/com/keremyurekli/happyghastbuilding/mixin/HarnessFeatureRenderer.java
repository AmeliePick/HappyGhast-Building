package com.keremyurekli.happyghastbuilding.mixin;

import com.keremyurekli.happyghastbuilding.Constant;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.class_11187;
import net.minecraft.class_11261;
import net.minecraft.class_11262;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.render.OverlayTexture;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.block.BlockModelRenderer;
import net.minecraft.client.render.block.BlockRenderManager;
import net.minecraft.client.render.entity.*;
import net.minecraft.client.render.entity.model.EntityModel;
import net.minecraft.client.render.entity.model.EntityModels;
import net.minecraft.client.render.entity.state.EntityRenderState;
import net.minecraft.client.render.entity.state.LivingEntityRenderState;
import net.minecraft.client.render.item.ItemRenderer;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.mob.MobEntity;
import net.minecraft.item.*;
import net.minecraft.registry.Registries;
import net.minecraft.state.property.Properties;
import net.minecraft.state.property.Property;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.RotationAxis;
import org.joml.Matrix3f;
import org.joml.Matrix4f;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import static net.minecraft.util.math.RotationAxis.POSITIVE_Y;

@Mixin(AgeableMobEntityRenderer.class)
public abstract class HarnessFeatureRenderer<T extends MobEntity, S extends LivingEntityRenderState, M extends EntityModel<? super S>>
        extends MobEntityRenderer<T, S, M> {

    public HarnessFeatureRenderer(EntityRendererFactory.Context context, M entityModel, float f) {
        super(context, entityModel, f);
    }


    @Inject(method = "render(Lnet/minecraft/client/render/entity/state/EntityRenderState;Lnet/minecraft/client/util/math/MatrixStack;Lnet/minecraft/client/render/VertexConsumerProvider;I)V", at = @At("TAIL"))
    private void onInit(EntityRenderState state, MatrixStack matrices, VertexConsumerProvider vertexConsumers, int light, CallbackInfo ci) {
//        Constant.LOGGER.info(String.valueOf(state.entityType));
        if (state.entityType == EntityType.HAPPY_GHAST) {

            if (state.height == 4.0f) { //ITS NOT A BABY

                //this was a pain in the ass
                // i dont know how i managed to pull this off
                class_11261 a = ((class_11261)(Object)this);
                LivingEntityRenderState lvre = (LivingEntityRenderState) state;
                class_11262 itemData = (class_11262) lvre;


                if (itemData.field_59978 != ItemStack.EMPTY) {
//                    itemData.field_59979 // HAS PASSENGERS
                    String translationKey = itemData.field_59978.getItem().getTranslationKey();
//                    Constant.LOGGER.info(translationKey);
//                    Constant.LOGGER.info(color);
                    // I KNOW THIS SEEMS LIKE A BAD WAY TO CHECK IF ITS THE RIGHT HARNESS TYPE
                    // BUT IM STORING CUSTOM ITEMS SEPARATELY, I DONT WANT TO CHECK THEM ONE BY ONE
                    // actually i can put them in a list while registering but screw it, it stays like this for now
                    if (translationKey.contains("adventurers_harness")) {
                        String color = translationKey.replace("item.happyghastbuilding.","")
                                .replace("_adventurers_harness","");

                        createBlockAt(state,matrices,vertexConsumers,light,-0.25f,-0.5f, Blocks.CRAFTING_TABLE.getDefaultState());
                        createBlockAt(state,matrices,vertexConsumers,light,-1.25f,-0.5f, Blocks.CHEST.getDefaultState());
                        createBlockAt(state,matrices,vertexConsumers,light,-0.25f,1.5f, Blocks.FURNACE.getDefaultState());

                        createBedTop(state,matrices,vertexConsumers,light,-0.25f,-0.5f, color);


//                        createBlockAt(state,matrices,vertexConsumers,light,-1,1, Items.BED);

                    } else if (translationKey.contains("fighters_harness")) {

                    }

                } else {
//                    Constant.LOGGER.info("no item");
                }

             }

        }
    }

    private void createBlockAt(EntityRenderState state, MatrixStack matrices, VertexConsumerProvider vertexConsumers, int light, float x, float z, BlockState bs) {
        matrices.push();
        BlockRenderManager blockRenderManager = MinecraftClient.getInstance().getBlockRenderManager();
        // DEVIL AHH MATH I GOT FROM INTERNET AND CHATGPT,  IT WORKS
        // but i don't know how
        // but i can understand that y is actually z in my situation
        float xRel = x;
        float yRel = z;
        float rotationRadians = (float) Math.toRadians(-((class_11262) state).bodyYaw);
        float newX = xRel * (float) Math.cos(rotationRadians) - yRel * (float) Math.sin(rotationRadians);//reversed
        float newY = xRel * (float) Math.sin(rotationRadians) + yRel * (float) Math.cos(rotationRadians);//with this
        //

        matrices.translate(newY,4f,newX);
        matrices.scale(1f,1f,1f);
        if (bs.getBlock() == Blocks.CHEST) {
            matrices.multiply(POSITIVE_Y.rotationDegrees(-((class_11262) state).bodyYaw));
        } else {
            matrices.multiply(POSITIVE_Y.rotationDegrees(-((class_11262) state).bodyYaw + 180));
        }

        blockRenderManager.renderBlockAsEntity(bs,matrices,vertexConsumers,light,OverlayTexture.DEFAULT_UV);
        matrices.pop();
    }


    // No need for the bottom part because i found out that when you try to render bed with BlockRenderManager,
    // it just renders both, nice
    private void createBedTop(EntityRenderState state, MatrixStack matrices, VertexConsumerProvider vertexConsumers, int light, float x, float z, String color) {
        matrices.push();
        BlockRenderManager blockRenderManager = MinecraftClient.getInstance().getBlockRenderManager();

        BlockState bs = Registries.BLOCK.get(Identifier.ofVanilla(color+"_bed")).getDefaultState();
        // DEVIL AHH MATH I GOT FROM INTERNET AND CHATGPT,  IT WORKS
        // but i don't know how
        // but i can understand that y is actually z in my situation
        float xRel = x;
        float yRel = z;
        float rotationRadians = (float) Math.toRadians(-((class_11262) state).bodyYaw);
        float newX = xRel * (float) Math.cos(rotationRadians) - yRel * (float) Math.sin(rotationRadians);//reversed
        float newY = xRel * (float) Math.sin(rotationRadians) + yRel * (float) Math.cos(rotationRadians);//with this
        //

        matrices.translate(newY,4f,newX);
        matrices.scale(1f,1f,1f);
        matrices.multiply(POSITIVE_Y.rotationDegrees(-((class_11262) state).bodyYaw - 90));

        blockRenderManager.renderBlockAsEntity(bs,matrices,vertexConsumers,light,OverlayTexture.DEFAULT_UV);
        matrices.pop();
    }

}