package com.keremyurekli.happyghastbuilding.mixin;


import com.keremyurekli.happyghastbuilding.weirdstuff.ICustomClassInterface;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.*;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import javax.swing.text.html.parser.Entity;
import java.util.UUID;

@Environment(EnvType.CLIENT)
@Mixin(class_11261.class)
public class CustomClass11261 implements ICustomClassInterface {



    private class_11187 e;

    @Inject(method = "updateRenderState(Lnet/minecraft/class_11187;Lnet/minecraft/class_11262;F)V", at = @At("HEAD"))
    public void rndr(class_11187 arg, class_11262 arg2, float f, CallbackInfo ci) {
        this.e = arg;

    }

    @Override
    public @Nullable class_11187 getEntity() {
        return e;
    }
}
