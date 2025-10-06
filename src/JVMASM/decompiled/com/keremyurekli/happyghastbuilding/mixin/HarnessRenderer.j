// NOTE: Removed chest and bed.


.inner public static {
    name: class_5618,
    inner: net/minecraft/class_5617$class_5618,
    outer: net/minecraft/class_5617
}
.inner public static final {
    name: Lookup,
    inner: java/lang/invoke/MethodHandles$Lookup,
    outer: java/lang/invoke/MethodHandles
}
.sourcefile "HarnessRenderer.java"
.invisible-annotation net/fabricmc/api/Environment {
    value: .enum net/fabricmc/api/EnvType CLIENT
}
.invisible-annotation org/spongepowered/asm/mixin/Mixin {
    value: { net/minecraft/class_9990 }
}
.signature "<T:Lnet/minecraft/class_1308;S:Lnet/minecraft/class_10042;M:Lnet/minecraft/class_583<-TS;>;>Lnet/minecraft/class_927<TT;TS;TM;>;"
.super net/minecraft/class_927
.class public super abstract com/keremyurekli/happyghastbuilding/mixin/HarnessRenderer {


    .signature "(Lnet/minecraft/class_5617$class_5618;TM;F)V"
    .method public <init> (Lnet/minecraft/class_5617$class_5618;Lnet/minecraft/class_583;F)V {
        parameters: { this, context, entityModel, f },
        code: {
        A: 
            line 48
            aload this
            aload context
            aload entityModel
            fload f
            invokespecial net/minecraft/class_927.<init> (Lnet/minecraft/class_5617$class_5618;Lnet/minecraft/class_583;F)V
        B: 
            line 49
            return 
        C: 
        }
    }

    .visible-annotation org/spongepowered/asm/mixin/injection/Inject {
        method: { "render(Lnet/minecraft/client/render/entity/state/EntityRenderState;Lnet/minecraft/client/util/math/MatrixStack;Lnet/minecraft/client/render/VertexConsumerProvider;I)V" },
        at: {         .annotation org/spongepowered/asm/mixin/injection/At {
            value: "TAIL"
        } }
    }
    .method private onInit (Lnet/minecraft/class_10017;Lnet/minecraft/class_4587;Lnet/minecraft/class_4597;ILorg/spongepowered/asm/mixin/injection/callback/CallbackInfo;)V {
        parameters: { this, state, matrices, vertexConsumers, light, ci },
        code: {
        A: 
            line 55
            aload state
            getfield net/minecraft/class_10017.field_58171 Lnet/minecraft/class_1299;
            getstatic net/minecraft/class_1299.field_59668 Lnet/minecraft/class_1299;
            if_acmpne BN
        B: 
            line 59
            aload this
            checkcast net/minecraft/class_11261
            astore a
        C: 
            line 60
            aload state
            checkcast net/minecraft/class_10042
            astore lvre
        D: 
            line 61
            aload lvre
            checkcast net/minecraft/class_11262
            astore itemData
        E: 
            line 63
            aload a
            checkcast com/keremyurekli/happyghastbuilding/weirdstuff/ICustomClassInterface
            astore intf
        F: 
            line 64
            aload intf
            invokeinterface com/keremyurekli/happyghastbuilding/weirdstuff/ICustomClassInterface.getEntity ()Lnet/minecraft/class_11187;
            astore entity
        G: 
            line 66
            aload entity
            ifnull BN
        H: 
            line 67
            getstatic com/keremyurekli/happyghastbuilding/Constant.INFO_LIST Ljava/util/HashMap;
            aload entity
            invokevirtual net/minecraft/class_11187.method_5667 ()Ljava/util/UUID;
            invokevirtual java/util/HashMap.containsKey (Ljava/lang/Object;)Z
            ifeq BN
        I: 
            line 69
            aload itemData
            getfield net/minecraft/class_11262.field_59978 Lnet/minecraft/class_1799;
            getstatic net/minecraft/class_1799.field_8037 Lnet/minecraft/class_1799;
            if_acmpeq BN
        J: 
            line 71
            aload itemData
            getfield net/minecraft/class_11262.field_59978 Lnet/minecraft/class_1799;
            invokevirtual net/minecraft/class_1799.method_7909 ()Lnet/minecraft/class_1792;
            invokevirtual net/minecraft/class_1792.method_7876 ()Ljava/lang/String;
            astore translationKey
        K: 
            line 73
            new org/joml/Vector3d
            dup 
            aload state
            getfield net/minecraft/class_10017.field_53325 D
            aload state
            getfield net/minecraft/class_10017.field_53326 D
            aload state
            getfield net/minecraft/class_10017.field_53327 D
            invokespecial org/joml/Vector3d.<init> (DDD)V
            astore realPos
        L: 
            line 79
            getstatic com/keremyurekli/happyghastbuilding/Constant.INFO_LIST Ljava/util/HashMap;
            aload entity
            invokevirtual net/minecraft/class_11187.method_5667 ()Ljava/util/UUID;
            invokevirtual java/util/HashMap.get (Ljava/lang/Object;)Ljava/lang/Object;
            checkcast com/keremyurekli/happyghastbuilding/weirdstuff/GhastInfo
            astore ghastInfo
        M: 
            line 81
            aload ghastInfo
            getfield com/keremyurekli/happyghastbuilding/weirdstuff/GhastInfo.boxes Ljava/util/List;
            invokeinterface java/util/List.clear ()V
        N: 
            line 82
            aload translationKey
            ldc "adventurers_harness"
            invokevirtual java/lang/String.contains (Ljava/lang/CharSequence;)Z
            ifeq AD
        O: 
            line 83
            aload ghastInfo
            iconst_0 
            putfield com/keremyurekli/happyghastbuilding/weirdstuff/GhastInfo.ghastType I
        P: 
            line 85
            aload translationKey
            ldc "item.happyghastbuilding."
            ldc ""
            invokevirtual java/lang/String.replace (Ljava/lang/CharSequence;Ljava/lang/CharSequence;)Ljava/lang/String;
            ldc "_adventurers_harness"
            ldc ""
        Q: 
            line 86
            invokevirtual java/lang/String.replace (Ljava/lang/CharSequence;Ljava/lang/CharSequence;)Ljava/lang/String;
            astore color
        R: 
            line 88
            aload this
            aload realPos
            aload state
            aload matrices
            aload vertexConsumers
            iload light
            ldc -0.25F
            ldc -0.5F
            getstatic net/minecraft/class_2246.field_9980 Lnet/minecraft/class_2248;
            invokevirtual net/minecraft/class_2248.method_9564 ()Lnet/minecraft/class_2680;
            invokevirtual com/keremyurekli/happyghastbuilding/mixin/HarnessRenderer.createBlockAt (Lorg/joml/Vector3d;Lnet/minecraft/class_10017;Lnet/minecraft/class_4587;Lnet/minecraft/class_4597;IFFLnet/minecraft/class_2680;)Lcom/keremyurekli/happyghastbuilding/weirdstuff/BoxWithData;
            astore table
        S: 
            line 90
            getstatic net/minecraft/class_2246.field_10181 Lnet/minecraft/class_2248;
            invokevirtual net/minecraft/class_2248.method_9564 ()Lnet/minecraft/class_2680;
            astore furnaceState
        T: 
            line 91
            getstatic com/keremyurekli/happyghastbuilding/Constant.INFO_LIST Ljava/util/HashMap;
            aload ghastInfo
            getfield com/keremyurekli/happyghastbuilding/weirdstuff/GhastInfo.ghastUUID Ljava/util/UUID;
            invokevirtual java/util/HashMap.get (Ljava/lang/Object;)Ljava/lang/Object;
            checkcast com/keremyurekli/happyghastbuilding/weirdstuff/GhastInfo
            getfield com/keremyurekli/happyghastbuilding/weirdstuff/GhastInfo.isLit Z
            ifeq V
        U: 
            line 92
            getstatic net/minecraft/class_2246.field_10181 Lnet/minecraft/class_2248;
            invokevirtual net/minecraft/class_2248.method_9564 ()Lnet/minecraft/class_2680;
            getstatic net/minecraft/class_2741.field_12548 Lnet/minecraft/class_2746;
            iconst_1 
            invokestatic java/lang/Boolean.valueOf (Z)Ljava/lang/Boolean;
            invokevirtual net/minecraft/class_2680.method_11657 (Lnet/minecraft/class_2769;Ljava/lang/Comparable;)Ljava/lang/Object;
            checkcast net/minecraft/class_2680
            astore furnaceState
        V: 
            line 94
            aload this
            aload realPos
            aload state
            aload matrices
            aload vertexConsumers
            iload light
            ldc -0.25F
            ldc 1.5F
            aload furnaceState
            invokevirtual com/keremyurekli/happyghastbuilding/mixin/HarnessRenderer.createBlockAt (Lorg/joml/Vector3d;Lnet/minecraft/class_10017;Lnet/minecraft/class_4587;Lnet/minecraft/class_4597;IFFLnet/minecraft/class_2680;)Lcom/keremyurekli/happyghastbuilding/weirdstuff/BoxWithData;
            astore furnace
        W: 
            line 96
            aload table
            getstatic com/keremyurekli/happyghastbuilding/weirdstuff/OnClick.CRAFTING_TABLE Lcom/keremyurekli/happyghastbuilding/weirdstuff/OnClick;
            putfield com/keremyurekli/happyghastbuilding/weirdstuff/BoxWithData.action Lcom/keremyurekli/happyghastbuilding/weirdstuff/OnClick;
        X: 
            line 97
            aload table
            aload ghastInfo
            getfield com/keremyurekli/happyghastbuilding/weirdstuff/GhastInfo.ghastUUID Ljava/util/UUID;
            putfield com/keremyurekli/happyghastbuilding/weirdstuff/BoxWithData.ghastUUID Ljava/util/UUID;
        Y: 
            line 101
            aload furnace
            getstatic com/keremyurekli/happyghastbuilding/weirdstuff/OnClick.FURNACE Lcom/keremyurekli/happyghastbuilding/weirdstuff/OnClick;
            putfield com/keremyurekli/happyghastbuilding/weirdstuff/BoxWithData.action Lcom/keremyurekli/happyghastbuilding/weirdstuff/OnClick;
        Z: 
            line 102
            aload furnace
            aload ghastInfo
            getfield com/keremyurekli/happyghastbuilding/weirdstuff/GhastInfo.ghastUUID Ljava/util/UUID;
            putfield com/keremyurekli/happyghastbuilding/weirdstuff/BoxWithData.ghastUUID Ljava/util/UUID;
        AA: 
            line 109
            aload ghastInfo
            getfield com/keremyurekli/happyghastbuilding/weirdstuff/GhastInfo.boxes Ljava/util/List;
            aload table
            invokeinterface java/util/List.add (Ljava/lang/Object;)Z
            pop 
        AB: 
            line 111
            aload ghastInfo
            getfield com/keremyurekli/happyghastbuilding/weirdstuff/GhastInfo.boxes Ljava/util/List;
            aload furnace
            invokeinterface java/util/List.add (Ljava/lang/Object;)Z
            pop 
        AC: 
            line 115
            goto BN
        AD: 
            aload translationKey
            ldc "demolition_harness"
            invokevirtual java/lang/String.contains (Ljava/lang/CharSequence;)Z
            ifeq BN
        AE: 
            line 117
            aload ghastInfo
            iconst_1 
            putfield com/keremyurekli/happyghastbuilding/weirdstuff/GhastInfo.ghastType I
        AF: 
            line 119
            aload translationKey
            ldc "item.happyghastbuilding."
            ldc ""
            invokevirtual java/lang/String.replace (Ljava/lang/CharSequence;Ljava/lang/CharSequence;)Ljava/lang/String;
            ldc "_adventurers_harness"
            ldc ""
        AG: 
            line 120
            invokevirtual java/lang/String.replace (Ljava/lang/CharSequence;Ljava/lang/CharSequence;)Ljava/lang/String;
            astore color
        AH: 
            line 122
            aload this
            aload realPos
            aload state
            aload matrices
            aload vertexConsumers
            iload light
            fconst_2 
            ldc 3F
            ldc -1.5F
            getstatic net/minecraft/class_2246.field_10200 Lnet/minecraft/class_2248;
            invokevirtual net/minecraft/class_2248.method_9564 ()Lnet/minecraft/class_2680;
            iconst_0 
            invokevirtual com/keremyurekli/happyghastbuilding/mixin/HarnessRenderer.createDispenserAt (Lorg/joml/Vector3d;Lnet/minecraft/class_10017;Lnet/minecraft/class_4587;Lnet/minecraft/class_4597;IFFFLnet/minecraft/class_2680;I)Lcom/keremyurekli/happyghastbuilding/weirdstuff/BoxWithData;
            astore dispenser1
        AI: 
            line 123
            aload this
            aload realPos
            aload state
            aload matrices
            aload vertexConsumers
            iload light
            fconst_2 
            ldc 3F
            ldc -2.5F
            getstatic net/minecraft/class_2246.field_10002 Lnet/minecraft/class_2248;
            invokevirtual net/minecraft/class_2248.method_9564 ()Lnet/minecraft/class_2680;
            invokevirtual com/keremyurekli/happyghastbuilding/mixin/HarnessRenderer.createEmptyBoxAt (Lorg/joml/Vector3d;Lnet/minecraft/class_10017;Lnet/minecraft/class_4587;Lnet/minecraft/class_4597;IFFFLnet/minecraft/class_2680;)Lcom/keremyurekli/happyghastbuilding/weirdstuff/BoxWithData;
            astore dispenser1Indicator
        AJ: 
            line 126
            aload this
            aload realPos
            aload state
            aload matrices
            aload vertexConsumers
            iload light
            fconst_2 
            ldc 3F
            ldc 2.5F
            getstatic net/minecraft/class_2246.field_10200 Lnet/minecraft/class_2248;
            invokevirtual net/minecraft/class_2248.method_9564 ()Lnet/minecraft/class_2680;
            iconst_1 
            invokevirtual com/keremyurekli/happyghastbuilding/mixin/HarnessRenderer.createDispenserAt (Lorg/joml/Vector3d;Lnet/minecraft/class_10017;Lnet/minecraft/class_4587;Lnet/minecraft/class_4597;IFFFLnet/minecraft/class_2680;I)Lcom/keremyurekli/happyghastbuilding/weirdstuff/BoxWithData;
            astore dispenser2
        AK: 
            line 127
            aload this
            aload realPos
            aload state
            aload matrices
            aload vertexConsumers
            iload light
            fconst_2 
            ldc 3F
            ldc 3.5F
            getstatic net/minecraft/class_2246.field_10234 Lnet/minecraft/class_2248;
            invokevirtual net/minecraft/class_2248.method_9564 ()Lnet/minecraft/class_2680;
            invokevirtual com/keremyurekli/happyghastbuilding/mixin/HarnessRenderer.createEmptyBoxAt (Lorg/joml/Vector3d;Lnet/minecraft/class_10017;Lnet/minecraft/class_4587;Lnet/minecraft/class_4597;IFFFLnet/minecraft/class_2680;)Lcom/keremyurekli/happyghastbuilding/weirdstuff/BoxWithData;
            astore dispenser2Indicator
        AL: 
            line 130
            aload this
            aload realPos
            aload state
            aload matrices
            aload vertexConsumers
            iload light
            ldc -1F
            ldc 3F
            ldc -1.5F
            getstatic net/minecraft/class_2246.field_10200 Lnet/minecraft/class_2248;
            invokevirtual net/minecraft/class_2248.method_9564 ()Lnet/minecraft/class_2680;
            iconst_0 
            invokevirtual com/keremyurekli/happyghastbuilding/mixin/HarnessRenderer.createDispenserAt (Lorg/joml/Vector3d;Lnet/minecraft/class_10017;Lnet/minecraft/class_4587;Lnet/minecraft/class_4597;IFFFLnet/minecraft/class_2680;I)Lcom/keremyurekli/happyghastbuilding/weirdstuff/BoxWithData;
            astore dispenser3
        AM: 
            line 131
            aload this
            aload realPos
            aload state
            aload matrices
            aload vertexConsumers
            iload light
            ldc -1F
            ldc 3F
            ldc -2.5F
            getstatic net/minecraft/class_2246.field_10171 Lnet/minecraft/class_2248;
            invokevirtual net/minecraft/class_2248.method_9564 ()Lnet/minecraft/class_2680;
            invokevirtual com/keremyurekli/happyghastbuilding/mixin/HarnessRenderer.createEmptyBoxAt (Lorg/joml/Vector3d;Lnet/minecraft/class_10017;Lnet/minecraft/class_4587;Lnet/minecraft/class_4597;IFFFLnet/minecraft/class_2680;)Lcom/keremyurekli/happyghastbuilding/weirdstuff/BoxWithData;
            astore dispenser3Indicator
        AN: 
            line 133
            aload this
            aload realPos
            aload state
            aload matrices
            aload vertexConsumers
            iload light
            ldc -1F
            ldc 3F
            ldc 2.5F
            getstatic net/minecraft/class_2246.field_10200 Lnet/minecraft/class_2248;
            invokevirtual net/minecraft/class_2248.method_9564 ()Lnet/minecraft/class_2680;
            iconst_1 
            invokevirtual com/keremyurekli/happyghastbuilding/mixin/HarnessRenderer.createDispenserAt (Lorg/joml/Vector3d;Lnet/minecraft/class_10017;Lnet/minecraft/class_4587;Lnet/minecraft/class_4597;IFFFLnet/minecraft/class_2680;I)Lcom/keremyurekli/happyghastbuilding/weirdstuff/BoxWithData;
            astore dispenser4
        AO: 
            line 134
            aload this
            aload realPos
            aload state
            aload matrices
            aload vertexConsumers
            iload light
            ldc -1F
            ldc 3F
            ldc 3.5F
            getstatic net/minecraft/class_2246.field_10201 Lnet/minecraft/class_2248;
            invokevirtual net/minecraft/class_2248.method_9564 ()Lnet/minecraft/class_2680;
            invokevirtual com/keremyurekli/happyghastbuilding/mixin/HarnessRenderer.createEmptyBoxAt (Lorg/joml/Vector3d;Lnet/minecraft/class_10017;Lnet/minecraft/class_4587;Lnet/minecraft/class_4597;IFFFLnet/minecraft/class_2680;)Lcom/keremyurekli/happyghastbuilding/weirdstuff/BoxWithData;
            astore dispenser4Indicator
        AP: 
            line 138
            aload dispenser1
            getstatic com/keremyurekli/happyghastbuilding/weirdstuff/OnClick.DISPENSER1 Lcom/keremyurekli/happyghastbuilding/weirdstuff/OnClick;
            putfield com/keremyurekli/happyghastbuilding/weirdstuff/BoxWithData.action Lcom/keremyurekli/happyghastbuilding/weirdstuff/OnClick;
        AQ: 
            line 139
            aload dispenser1Indicator
            getstatic com/keremyurekli/happyghastbuilding/weirdstuff/OnClick.DI1 Lcom/keremyurekli/happyghastbuilding/weirdstuff/OnClick;
            putfield com/keremyurekli/happyghastbuilding/weirdstuff/BoxWithData.action Lcom/keremyurekli/happyghastbuilding/weirdstuff/OnClick;
        AR: 
            line 140
            aload dispenser2
            getstatic com/keremyurekli/happyghastbuilding/weirdstuff/OnClick.DISPENSER2 Lcom/keremyurekli/happyghastbuilding/weirdstuff/OnClick;
            putfield com/keremyurekli/happyghastbuilding/weirdstuff/BoxWithData.action Lcom/keremyurekli/happyghastbuilding/weirdstuff/OnClick;
        AS: 
            line 141
            aload dispenser2Indicator
            getstatic com/keremyurekli/happyghastbuilding/weirdstuff/OnClick.DI2 Lcom/keremyurekli/happyghastbuilding/weirdstuff/OnClick;
            putfield com/keremyurekli/happyghastbuilding/weirdstuff/BoxWithData.action Lcom/keremyurekli/happyghastbuilding/weirdstuff/OnClick;
        AT: 
            line 142
            aload dispenser3
            getstatic com/keremyurekli/happyghastbuilding/weirdstuff/OnClick.DISPENSER3 Lcom/keremyurekli/happyghastbuilding/weirdstuff/OnClick;
            putfield com/keremyurekli/happyghastbuilding/weirdstuff/BoxWithData.action Lcom/keremyurekli/happyghastbuilding/weirdstuff/OnClick;
        AU: 
            line 143
            aload dispenser3Indicator
            getstatic com/keremyurekli/happyghastbuilding/weirdstuff/OnClick.DI3 Lcom/keremyurekli/happyghastbuilding/weirdstuff/OnClick;
            putfield com/keremyurekli/happyghastbuilding/weirdstuff/BoxWithData.action Lcom/keremyurekli/happyghastbuilding/weirdstuff/OnClick;
        AV: 
            line 144
            aload dispenser4
            getstatic com/keremyurekli/happyghastbuilding/weirdstuff/OnClick.DISPENSER4 Lcom/keremyurekli/happyghastbuilding/weirdstuff/OnClick;
            putfield com/keremyurekli/happyghastbuilding/weirdstuff/BoxWithData.action Lcom/keremyurekli/happyghastbuilding/weirdstuff/OnClick;
        AW: 
            line 145
            aload dispenser4Indicator
            getstatic com/keremyurekli/happyghastbuilding/weirdstuff/OnClick.DI4 Lcom/keremyurekli/happyghastbuilding/weirdstuff/OnClick;
            putfield com/keremyurekli/happyghastbuilding/weirdstuff/BoxWithData.action Lcom/keremyurekli/happyghastbuilding/weirdstuff/OnClick;
        AX: 
            line 147
            aload dispenser1
            aload ghastInfo
            getfield com/keremyurekli/happyghastbuilding/weirdstuff/GhastInfo.ghastUUID Ljava/util/UUID;
            putfield com/keremyurekli/happyghastbuilding/weirdstuff/BoxWithData.ghastUUID Ljava/util/UUID;
        AY: 
            line 148
            aload dispenser1Indicator
            aload ghastInfo
            getfield com/keremyurekli/happyghastbuilding/weirdstuff/GhastInfo.ghastUUID Ljava/util/UUID;
            putfield com/keremyurekli/happyghastbuilding/weirdstuff/BoxWithData.ghastUUID Ljava/util/UUID;
        AZ: 
            line 149
            aload dispenser2
            aload ghastInfo
            getfield com/keremyurekli/happyghastbuilding/weirdstuff/GhastInfo.ghastUUID Ljava/util/UUID;
            putfield com/keremyurekli/happyghastbuilding/weirdstuff/BoxWithData.ghastUUID Ljava/util/UUID;
        BA: 
            line 150
            aload dispenser2Indicator
            aload ghastInfo
            getfield com/keremyurekli/happyghastbuilding/weirdstuff/GhastInfo.ghastUUID Ljava/util/UUID;
            putfield com/keremyurekli/happyghastbuilding/weirdstuff/BoxWithData.ghastUUID Ljava/util/UUID;
        BB: 
            line 151
            aload dispenser3
            aload ghastInfo
            getfield com/keremyurekli/happyghastbuilding/weirdstuff/GhastInfo.ghastUUID Ljava/util/UUID;
            putfield com/keremyurekli/happyghastbuilding/weirdstuff/BoxWithData.ghastUUID Ljava/util/UUID;
        BC: 
            line 152
            aload dispenser3Indicator
            aload ghastInfo
            getfield com/keremyurekli/happyghastbuilding/weirdstuff/GhastInfo.ghastUUID Ljava/util/UUID;
            putfield com/keremyurekli/happyghastbuilding/weirdstuff/BoxWithData.ghastUUID Ljava/util/UUID;
        BD: 
            line 153
            aload dispenser4
            aload ghastInfo
            getfield com/keremyurekli/happyghastbuilding/weirdstuff/GhastInfo.ghastUUID Ljava/util/UUID;
            putfield com/keremyurekli/happyghastbuilding/weirdstuff/BoxWithData.ghastUUID Ljava/util/UUID;
        BE: 
            line 154
            aload dispenser4Indicator
            aload ghastInfo
            getfield com/keremyurekli/happyghastbuilding/weirdstuff/GhastInfo.ghastUUID Ljava/util/UUID;
            putfield com/keremyurekli/happyghastbuilding/weirdstuff/BoxWithData.ghastUUID Ljava/util/UUID;
        BF: 
            line 156
            aload ghastInfo
            getfield com/keremyurekli/happyghastbuilding/weirdstuff/GhastInfo.boxes Ljava/util/List;
            aload dispenser1
            invokeinterface java/util/List.add (Ljava/lang/Object;)Z
            pop 
        BG: 
            line 157
            aload ghastInfo
            getfield com/keremyurekli/happyghastbuilding/weirdstuff/GhastInfo.boxes Ljava/util/List;
            aload dispenser2
            invokeinterface java/util/List.add (Ljava/lang/Object;)Z
            pop 
        BH: 
            line 158
            aload ghastInfo
            getfield com/keremyurekli/happyghastbuilding/weirdstuff/GhastInfo.boxes Ljava/util/List;
            aload dispenser3
            invokeinterface java/util/List.add (Ljava/lang/Object;)Z
            pop 
        BI: 
            line 159
            aload ghastInfo
            getfield com/keremyurekli/happyghastbuilding/weirdstuff/GhastInfo.boxes Ljava/util/List;
            aload dispenser4
            invokeinterface java/util/List.add (Ljava/lang/Object;)Z
            pop 
        BJ: 
            line 160
            aload ghastInfo
            getfield com/keremyurekli/happyghastbuilding/weirdstuff/GhastInfo.boxes Ljava/util/List;
            aload dispenser1Indicator
            invokeinterface java/util/List.add (Ljava/lang/Object;)Z
            pop 
        BK: 
            line 161
            aload ghastInfo
            getfield com/keremyurekli/happyghastbuilding/weirdstuff/GhastInfo.boxes Ljava/util/List;
            aload dispenser2Indicator
            invokeinterface java/util/List.add (Ljava/lang/Object;)Z
            pop 
        BL: 
            line 162
            aload ghastInfo
            getfield com/keremyurekli/happyghastbuilding/weirdstuff/GhastInfo.boxes Ljava/util/List;
            aload dispenser3Indicator
            invokeinterface java/util/List.add (Ljava/lang/Object;)Z
            pop 
        BM: 
            line 163
            aload ghastInfo
            getfield com/keremyurekli/happyghastbuilding/weirdstuff/GhastInfo.boxes Ljava/util/List;
            aload dispenser4Indicator
            invokeinterface java/util/List.add (Ljava/lang/Object;)Z
            pop 
        BN: 
            line 176
            return 
        BO: 
        }
    }

    .method private keepRotationOfImaginaryPos (Lnet/minecraft/class_10017;FF)Lnet/minecraft/class_241; {
        parameters: { this, state, x, z },
        code: {
        A: 
            line 180
            fload x
            fstore xRel
        B: 
            line 181
            fload z
            fstore yRel
        C: 
            line 182
            aload state
            checkcast net/minecraft/class_11262
            getfield net/minecraft/class_11262.field_53446 F
            fneg 
            f2d 
            invokestatic java/lang/Math.toRadians (D)D
            d2f 
            fstore rotationRadians
        D: 
            line 183
            fload xRel
            fload rotationRadians
            f2d 
            invokestatic java/lang/Math.cos (D)D
            d2f 
            fmul 
            fload yRel
            fload rotationRadians
            f2d 
            invokestatic java/lang/Math.sin (D)D
            d2f 
            fmul 
            fsub 
            fstore newX
        E: 
            line 184
            fload xRel
            fload rotationRadians
            f2d 
            invokestatic java/lang/Math.sin (D)D
            d2f 
            fmul 
            fload yRel
            fload rotationRadians
            f2d 
            invokestatic java/lang/Math.cos (D)D
            d2f 
            fmul 
            fadd 
            fstore newY
        F: 
            line 186
            new net/minecraft/class_241
            dup 
            fload newX
            fload newY
            invokespecial net/minecraft/class_241.<init> (FF)V
            areturn 
        G: 
        }
    }

    .method private createBlockAt (Lorg/joml/Vector3d;Lnet/minecraft/class_10017;Lnet/minecraft/class_4587;Lnet/minecraft/class_4597;IFFLnet/minecraft/class_2680;)Lcom/keremyurekli/happyghastbuilding/weirdstuff/BoxWithData; {
        parameters: { this, entityPos, state, matrices, vertexConsumers, light, x, z, bs },
        code: {
        A: 
            line 190
            aload matrices
            invokevirtual net/minecraft/class_4587.method_22903 ()V
        B: 
            line 191
            invokestatic net/minecraft/class_310.method_1551 ()Lnet/minecraft/class_310;
            invokevirtual net/minecraft/class_310.method_1541 ()Lnet/minecraft/class_776;
            astore blockRenderManager
        C: 
            line 195
            fload x
            fstore xRel
        D: 
            line 196
            fload z
            fstore yRel
        E: 
            line 197
            aload state
            checkcast net/minecraft/class_11262
            getfield net/minecraft/class_11262.field_53446 F
            fneg 
            f2d 
            invokestatic java/lang/Math.toRadians (D)D
            d2f 
            fstore rotationRadians
        F: 
            line 198
            fload xRel
            fload rotationRadians
            f2d 
            invokestatic java/lang/Math.cos (D)D
            d2f 
            fmul 
            fload yRel
            fload rotationRadians
            f2d 
            invokestatic java/lang/Math.sin (D)D
            d2f 
            fmul 
            fsub 
            fstore newX
        G: 
            line 199
            fload xRel
            fload rotationRadians
            f2d 
            invokestatic java/lang/Math.sin (D)D
            d2f 
            fmul 
            fload yRel
            fload rotationRadians
            f2d 
            invokestatic java/lang/Math.cos (D)D
            d2f 
            fmul 
            fadd 
            fstore newY
        H: 
            line 203
            aload this
            aload state
            fload x
            fconst_1 
            fsub 
            fload z
            fconst_1 
            fsub 
            invokevirtual com/keremyurekli/happyghastbuilding/mixin/HarnessRenderer.keepRotationOfImaginaryPos (Lnet/minecraft/class_10017;FF)Lnet/minecraft/class_241;
            astore imaginer
        I: 
            line 204
            new net/minecraft/class_243
            dup 
            aload entityPos
            getfield org/joml/Vector3d.x D
            aload imaginer
            getfield net/minecraft/class_241.field_1342 F
            f2d 
            dadd 
            aload entityPos
            getfield org/joml/Vector3d.y D
            ldc 4D
            dadd 
            dconst_1 
            dadd 
            aload entityPos
            getfield org/joml/Vector3d.z D
            aload imaginer
            getfield net/minecraft/class_241.field_1343 F
            f2d 
            dadd 
            invokespecial net/minecraft/class_243.<init> (DDD)V
            astore c1
        J: 
            line 206
            new net/minecraft/class_243
            dup 
            aload entityPos
            getfield org/joml/Vector3d.x D
            fload newY
            f2d 
            dadd 
            aload entityPos
            getfield org/joml/Vector3d.y D
            ldc 4D
            dadd 
            aload entityPos
            getfield org/joml/Vector3d.z D
            fload newX
            f2d 
            dadd 
            invokespecial net/minecraft/class_243.<init> (DDD)V
            astore c2
        K: 
            line 210
            aload matrices
            fload newY
            ldc 4F
            fload newX
            invokevirtual net/minecraft/class_4587.method_46416 (FFF)V
        L: 
            line 211
            aload matrices
            fconst_1 
            fconst_1 
            fconst_1 
            invokevirtual net/minecraft/class_4587.method_22905 (FFF)V
        M: 
            line 212
            aload bs
            invokevirtual net/minecraft/class_2680.method_26204 ()Lnet/minecraft/class_2248;
            getstatic net/minecraft/class_2246.field_10034 Lnet/minecraft/class_2248;
            if_acmpne R
        N: 
            line 213
            aload this
            aload state
            fload x
            fconst_1 
            fadd 
            fload z
            fconst_1 
            fadd 
            invokevirtual com/keremyurekli/happyghastbuilding/mixin/HarnessRenderer.keepRotationOfImaginaryPos (Lnet/minecraft/class_10017;FF)Lnet/minecraft/class_241;
            astore imaginer
        O: 
            line 214
            new net/minecraft/class_243
            dup 
            aload entityPos
            getfield org/joml/Vector3d.x D
            aload imaginer
            getfield net/minecraft/class_241.field_1342 F
            f2d 
            dadd 
            aload entityPos
            getfield org/joml/Vector3d.y D
            ldc 4D
            dadd 
            dconst_1 
            dadd 
            aload entityPos
            getfield org/joml/Vector3d.z D
            aload imaginer
            getfield net/minecraft/class_241.field_1343 F
            f2d 
            dadd 
            invokespecial net/minecraft/class_243.<init> (DDD)V
            astore c1
        P: 
            line 216
            new net/minecraft/class_243
            dup 
            aload entityPos
            getfield org/joml/Vector3d.x D
            fload newY
            f2d 
            dadd 
            aload entityPos
            getfield org/joml/Vector3d.y D
            ldc 4D
            dadd 
            aload entityPos
            getfield org/joml/Vector3d.z D
            fload newX
            f2d 
            dadd 
            invokespecial net/minecraft/class_243.<init> (DDD)V
            astore c2
        Q: 
            line 217
            aload matrices
            getstatic net/minecraft/class_7833.field_40716 Lnet/minecraft/class_7833;
            aload state
            checkcast net/minecraft/class_11262
            getfield net/minecraft/class_11262.field_53446 F
            fneg 
            invokeinterface net/minecraft/class_7833.rotationDegrees (F)Lorg/joml/Quaternionf;
            invokevirtual net/minecraft/class_4587.method_22907 (Lorg/joml/Quaternionfc;)V
            goto S
        R: 
            line 219
            aload matrices
            getstatic net/minecraft/class_7833.field_40716 Lnet/minecraft/class_7833;
            aload state
            checkcast net/minecraft/class_11262
            getfield net/minecraft/class_11262.field_53446 F
            fneg 
            ldc 180F
            fadd 
            invokeinterface net/minecraft/class_7833.rotationDegrees (F)Lorg/joml/Quaternionf;
            invokevirtual net/minecraft/class_4587.method_22907 (Lorg/joml/Quaternionfc;)V
        S: 
            line 222
            aload blockRenderManager
            aload bs
            aload matrices
            aload vertexConsumers
            iload light
            getstatic net/minecraft/class_4608.field_21444 I
            invokevirtual net/minecraft/class_776.method_3353 (Lnet/minecraft/class_2680;Lnet/minecraft/class_4587;Lnet/minecraft/class_4597;II)V
        T: 
            line 223
            aload matrices
            invokevirtual net/minecraft/class_4587.method_22909 ()V
        U: 
            line 228
            new com/keremyurekli/happyghastbuilding/weirdstuff/BoxWithData
            dup 
            aload c1
            aload c2
            invokespecial com/keremyurekli/happyghastbuilding/weirdstuff/BoxWithData.<init> (Lnet/minecraft/class_243;Lnet/minecraft/class_243;)V
            areturn 
        V: 
        }
    }

    .method private createEmptyBoxAt (Lorg/joml/Vector3d;Lnet/minecraft/class_10017;Lnet/minecraft/class_4587;Lnet/minecraft/class_4597;IFFFLnet/minecraft/class_2680;)Lcom/keremyurekli/happyghastbuilding/weirdstuff/BoxWithData; {
        parameters: { this, entityPos, state, matrices, vertexConsumers, light, x, parY, z, bs },
        code: {
        A: 
            line 231
            aload matrices
            invokevirtual net/minecraft/class_4587.method_22903 ()V
        B: 
            line 232
            invokestatic net/minecraft/class_310.method_1551 ()Lnet/minecraft/class_310;
            invokevirtual net/minecraft/class_310.method_1541 ()Lnet/minecraft/class_776;
            astore blockRenderManager
        C: 
            line 236
            fload x
            fstore xRel
        D: 
            line 237
            fload z
            fstore yRel
        E: 
            line 238
            aload state
            checkcast net/minecraft/class_11262
            getfield net/minecraft/class_11262.field_53446 F
            fneg 
            f2d 
            invokestatic java/lang/Math.toRadians (D)D
            d2f 
            fstore rotationRadians
        F: 
            line 239
            fload xRel
            fload rotationRadians
            f2d 
            invokestatic java/lang/Math.cos (D)D
            d2f 
            fmul 
            fload yRel
            fload rotationRadians
            f2d 
            invokestatic java/lang/Math.sin (D)D
            d2f 
            fmul 
            fsub 
            fstore newX
        G: 
            line 240
            fload xRel
            fload rotationRadians
            f2d 
            invokestatic java/lang/Math.sin (D)D
            d2f 
            fmul 
            fload yRel
            fload rotationRadians
            f2d 
            invokestatic java/lang/Math.cos (D)D
            d2f 
            fmul 
            fadd 
            fstore newY
        H: 
            line 244
            aload this
            aload state
            fload x
            fconst_1 
            fsub 
            fload z
            fconst_1 
            fsub 
            invokevirtual com/keremyurekli/happyghastbuilding/mixin/HarnessRenderer.keepRotationOfImaginaryPos (Lnet/minecraft/class_10017;FF)Lnet/minecraft/class_241;
            astore imaginer
        I: 
            line 245
            new net/minecraft/class_243
            dup 
            aload entityPos
            getfield org/joml/Vector3d.x D
            aload imaginer
            getfield net/minecraft/class_241.field_1342 F
            f2d 
            dadd 
            aload entityPos
            getfield org/joml/Vector3d.y D
            fload parY
            f2d 
            dadd 
            dconst_1 
            dadd 
            aload entityPos
            getfield org/joml/Vector3d.z D
            aload imaginer
            getfield net/minecraft/class_241.field_1343 F
            f2d 
            dadd 
            invokespecial net/minecraft/class_243.<init> (DDD)V
            astore c1
        J: 
            line 247
            new net/minecraft/class_243
            dup 
            aload entityPos
            getfield org/joml/Vector3d.x D
            fload newY
            f2d 
            dadd 
            aload entityPos
            getfield org/joml/Vector3d.y D
            fload parY
            f2d 
            dadd 
            aload entityPos
            getfield org/joml/Vector3d.z D
            fload newX
            f2d 
            dadd 
            invokespecial net/minecraft/class_243.<init> (DDD)V
            astore c2
        K: 
            line 251
            aload matrices
            fload newY
            fload parY
            fload newX
            invokevirtual net/minecraft/class_4587.method_46416 (FFF)V
        L: 
            line 252
            aload matrices
            fconst_1 
            fconst_1 
            fconst_1 
            invokevirtual net/minecraft/class_4587.method_22905 (FFF)V
        M: 
            line 253
            aload bs
            invokevirtual net/minecraft/class_2680.method_26204 ()Lnet/minecraft/class_2248;
            getstatic net/minecraft/class_2246.field_10034 Lnet/minecraft/class_2248;
            if_acmpne R
        N: 
            line 254
            aload this
            aload state
            fload x
            fconst_1 
            fadd 
            fload z
            fconst_1 
            fadd 
            invokevirtual com/keremyurekli/happyghastbuilding/mixin/HarnessRenderer.keepRotationOfImaginaryPos (Lnet/minecraft/class_10017;FF)Lnet/minecraft/class_241;
            astore imaginer
        O: 
            line 255
            new net/minecraft/class_243
            dup 
            aload entityPos
            getfield org/joml/Vector3d.x D
            aload imaginer
            getfield net/minecraft/class_241.field_1342 F
            f2d 
            dadd 
            aload entityPos
            getfield org/joml/Vector3d.y D
            fload parY
            f2d 
            dadd 
            dconst_1 
            dadd 
            aload entityPos
            getfield org/joml/Vector3d.z D
            aload imaginer
            getfield net/minecraft/class_241.field_1343 F
            f2d 
            dadd 
            invokespecial net/minecraft/class_243.<init> (DDD)V
            astore c1
        P: 
            line 257
            new net/minecraft/class_243
            dup 
            aload entityPos
            getfield org/joml/Vector3d.x D
            fload newY
            f2d 
            dadd 
            aload entityPos
            getfield org/joml/Vector3d.y D
            fload parY
            f2d 
            dadd 
            aload entityPos
            getfield org/joml/Vector3d.z D
            fload newX
            f2d 
            dadd 
            invokespecial net/minecraft/class_243.<init> (DDD)V
            astore c2
        Q: 
            line 258
            aload matrices
            getstatic net/minecraft/class_7833.field_40716 Lnet/minecraft/class_7833;
            aload state
            checkcast net/minecraft/class_11262
            getfield net/minecraft/class_11262.field_53446 F
            fneg 
            invokeinterface net/minecraft/class_7833.rotationDegrees (F)Lorg/joml/Quaternionf;
            invokevirtual net/minecraft/class_4587.method_22907 (Lorg/joml/Quaternionfc;)V
            goto S
        R: 
            line 260
            aload matrices
            getstatic net/minecraft/class_7833.field_40716 Lnet/minecraft/class_7833;
            aload state
            checkcast net/minecraft/class_11262
            getfield net/minecraft/class_11262.field_53446 F
            fneg 
            ldc 180F
            fadd 
            invokeinterface net/minecraft/class_7833.rotationDegrees (F)Lorg/joml/Quaternionf;
            invokevirtual net/minecraft/class_4587.method_22907 (Lorg/joml/Quaternionfc;)V
        S: 
            line 264
            aload matrices
            invokevirtual net/minecraft/class_4587.method_22909 ()V
        T: 
            line 269
            new com/keremyurekli/happyghastbuilding/weirdstuff/BoxWithData
            dup 
            aload c1
            aload c2
            invokespecial com/keremyurekli/happyghastbuilding/weirdstuff/BoxWithData.<init> (Lnet/minecraft/class_243;Lnet/minecraft/class_243;)V
            areturn 
        U: 
        }
    }

    .method private createDispenserAt (Lorg/joml/Vector3d;Lnet/minecraft/class_10017;Lnet/minecraft/class_4587;Lnet/minecraft/class_4597;IFFFLnet/minecraft/class_2680;I)Lcom/keremyurekli/happyghastbuilding/weirdstuff/BoxWithData; {
        parameters: { this, entityPos, state, matrices, vertexConsumers, light, x, parY, z, bs, facing },
        code: {
        A: 
            line 272
            aload matrices
            invokevirtual net/minecraft/class_4587.method_22903 ()V
        B: 
            line 273
            invokestatic net/minecraft/class_310.method_1551 ()Lnet/minecraft/class_310;
            invokevirtual net/minecraft/class_310.method_1541 ()Lnet/minecraft/class_776;
            astore blockRenderManager
        C: 
            line 277
            fload x
            fstore xRel
        D: 
            line 278
            fload z
            fstore yRel
        E: 
            line 279
            aload state
            checkcast net/minecraft/class_11262
            getfield net/minecraft/class_11262.field_53446 F
            fneg 
            f2d 
            invokestatic java/lang/Math.toRadians (D)D
            d2f 
            fstore rotationRadians
        F: 
            line 280
            fload xRel
            fload rotationRadians
            f2d 
            invokestatic java/lang/Math.cos (D)D
            d2f 
            fmul 
            fload yRel
            fload rotationRadians
            f2d 
            invokestatic java/lang/Math.sin (D)D
            d2f 
            fmul 
            fsub 
            fstore newX
        G: 
            line 281
            fload xRel
            fload rotationRadians
            f2d 
            invokestatic java/lang/Math.sin (D)D
            d2f 
            fmul 
            fload yRel
            fload rotationRadians
            f2d 
            invokestatic java/lang/Math.cos (D)D
            d2f 
            fmul 
            fadd 
            fstore newY
        H: 
            line 289
            iload facing
            ifne O
        I: 
            line 290
            aload bs
            getstatic net/minecraft/class_2741.field_12525 Lnet/minecraft/class_2754;
            getstatic net/minecraft/class_2350.field_11034 Lnet/minecraft/class_2350;
            invokevirtual net/minecraft/class_2680.method_11657 (Lnet/minecraft/class_2769;Ljava/lang/Comparable;)Ljava/lang/Object;
            checkcast net/minecraft/class_2680
            astore bState
        J: 
            line 291
            aload this
            aload state
            fload x
            fconst_1 
            fsub 
            fload z
            fconst_1 
            fsub 
            invokevirtual com/keremyurekli/happyghastbuilding/mixin/HarnessRenderer.keepRotationOfImaginaryPos (Lnet/minecraft/class_10017;FF)Lnet/minecraft/class_241;
            astore imaginer
        K: 
            line 292
            new net/minecraft/class_243
            dup 
            aload entityPos
            getfield org/joml/Vector3d.x D
            aload imaginer
            getfield net/minecraft/class_241.field_1342 F
            f2d 
            dadd 
            aload entityPos
            getfield org/joml/Vector3d.y D
            fload parY
            f2d 
            dadd 
            dconst_1 
            dadd 
            aload entityPos
            getfield org/joml/Vector3d.z D
            aload imaginer
            getfield net/minecraft/class_241.field_1343 F
            f2d 
            dadd 
            invokespecial net/minecraft/class_243.<init> (DDD)V
            astore c1
        L: 
            line 294
            aload this
            aload state
            fload x
            fload z
            ldc 0.5F
            fsub 
            invokevirtual com/keremyurekli/happyghastbuilding/mixin/HarnessRenderer.keepRotationOfImaginaryPos (Lnet/minecraft/class_10017;FF)Lnet/minecraft/class_241;
            astore imaginer2
        M: 
            line 295
            new net/minecraft/class_243
            dup 
            aload entityPos
            getfield org/joml/Vector3d.x D
            aload imaginer2
            getfield net/minecraft/class_241.field_1342 F
            f2d 
            dadd 
            aload entityPos
            getfield org/joml/Vector3d.y D
            fload parY
            f2d 
            dadd 
            aload entityPos
            getfield org/joml/Vector3d.z D
            aload imaginer2
            getfield net/minecraft/class_241.field_1343 F
            f2d 
            dadd 
            invokespecial net/minecraft/class_243.<init> (DDD)V
            astore c2
        N: 
            line 296
            goto S
        O: 
            line 297
            aload bs
            getstatic net/minecraft/class_2741.field_12525 Lnet/minecraft/class_2754;
            getstatic net/minecraft/class_2350.field_11039 Lnet/minecraft/class_2350;
            invokevirtual net/minecraft/class_2680.method_11657 (Lnet/minecraft/class_2769;Ljava/lang/Comparable;)Ljava/lang/Object;
            checkcast net/minecraft/class_2680
            astore bState
        P: 
            line 298
            aload this
            aload state
            fload x
            fconst_1 
            fsub 
            fload z
            ldc 0.5F
            fsub 
            invokevirtual com/keremyurekli/happyghastbuilding/mixin/HarnessRenderer.keepRotationOfImaginaryPos (Lnet/minecraft/class_10017;FF)Lnet/minecraft/class_241;
            astore imaginer
        Q: 
            line 299
            new net/minecraft/class_243
            dup 
            aload entityPos
            getfield org/joml/Vector3d.x D
            aload imaginer
            getfield net/minecraft/class_241.field_1342 F
            f2d 
            dadd 
            aload entityPos
            getfield org/joml/Vector3d.y D
            fload parY
            f2d 
            dadd 
            dconst_1 
            dadd 
            aload entityPos
            getfield org/joml/Vector3d.z D
            aload imaginer
            getfield net/minecraft/class_241.field_1343 F
            f2d 
            dadd 
            invokespecial net/minecraft/class_243.<init> (DDD)V
            astore c1
        R: 
            line 301
            new net/minecraft/class_243
            dup 
            aload entityPos
            getfield org/joml/Vector3d.x D
            fload newY
            f2d 
            dadd 
            aload entityPos
            getfield org/joml/Vector3d.y D
            fload parY
            f2d 
            dadd 
            aload entityPos
            getfield org/joml/Vector3d.z D
            fload newX
            f2d 
            dadd 
            invokespecial net/minecraft/class_243.<init> (DDD)V
            astore c2
        S: 
            line 315
            aload matrices
            fload newY
            fload parY
            fload newX
            invokevirtual net/minecraft/class_4587.method_46416 (FFF)V
        T: 
            line 316
            aload matrices
            fconst_1 
            fconst_1 
            fconst_1 
            invokevirtual net/minecraft/class_4587.method_22905 (FFF)V
        U: 
            line 317
            aload matrices
            getstatic net/minecraft/class_7833.field_40716 Lnet/minecraft/class_7833;
            aload state
            checkcast net/minecraft/class_11262
            getfield net/minecraft/class_11262.field_53446 F
            fneg 
            ldc 180F
            fadd 
            invokeinterface net/minecraft/class_7833.rotationDegrees (F)Lorg/joml/Quaternionf;
            invokevirtual net/minecraft/class_4587.method_22907 (Lorg/joml/Quaternionfc;)V
        V: 
            line 319
            aload blockRenderManager
            aload bState
            aload matrices
            aload vertexConsumers
            iload light
            getstatic net/minecraft/class_4608.field_21444 I
            invokevirtual net/minecraft/class_776.method_3353 (Lnet/minecraft/class_2680;Lnet/minecraft/class_4587;Lnet/minecraft/class_4597;II)V
        W: 
            line 320
            aload matrices
            invokevirtual net/minecraft/class_4587.method_22909 ()V
        X: 
            line 325
            new com/keremyurekli/happyghastbuilding/weirdstuff/BoxWithData
            dup 
            aload c1
            aload c2
            invokespecial com/keremyurekli/happyghastbuilding/weirdstuff/BoxWithData.<init> (Lnet/minecraft/class_243;Lnet/minecraft/class_243;)V
            areturn 
        Y: 
        }
    }

    .method protected bridge synthetic method_55831 (Lnet/minecraft/class_10017;)F {
        parameters: { this, v1 },
        code: {
        A: 
            line 41
            aload this
            aload v1
            checkcast net/minecraft/class_10042
            invokespecial net/minecraft/class_927.method_55832 (Lnet/minecraft/class_10042;)F
            freturn 
        B: 
        }
    }

}