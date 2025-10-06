.inner public static interface abstract {
    name: Init,
    inner: net/fabricmc/fabric/api/networking/v1/ServerPlayConnectionEvents$Init,
    outer: net/fabricmc/fabric/api/networking/v1/ServerPlayConnectionEvents
}
.inner public static interface abstract {
    name: ServerStarting,
    inner: net/fabricmc/fabric/api/event/lifecycle/v1/ServerLifecycleEvents$ServerStarting,
    outer: net/fabricmc/fabric/api/event/lifecycle/v1/ServerLifecycleEvents
}
.inner public static interface abstract {
    name: ServerStopping,
    inner: net/fabricmc/fabric/api/event/lifecycle/v1/ServerLifecycleEvents$ServerStopping,
    outer: net/fabricmc/fabric/api/event/lifecycle/v1/ServerLifecycleEvents
}
.inner public static final {
    name: class_9859,
    inner: net/minecraft/class_1269$class_9859,
    outer: net/minecraft/class_1269
}
.inner public static abstract enum {
    name: class_2351,
    inner: net/minecraft/class_2350$class_2351,
    outer: net/minecraft/class_2350
}
.inner public static final {
    name: class_9860,
    inner: net/minecraft/class_1269$class_9860,
    outer: net/minecraft/class_1269
}
.inner public static interface abstract {
    name: class_6890,
    inner: net/minecraft/class_5455$class_6890,
    outer: net/minecraft/class_5455
}
.inner public static final {
    name: Lookup,
    inner: java/lang/invoke/MethodHandles$Lookup,
    outer: java/lang/invoke/MethodHandles
}
.sourcefile "Happyghastbuilding.java"
.super java/lang/Object
.implements net/fabricmc/api/ModInitializer
.class public super com/keremyurekli/happyghastbuilding/Happyghastbuilding {


    .method public <init> ()V {
        parameters: { this },
        code: {
        A: 
            line 34
            aload this
            invokespecial java/lang/Object.<init> ()V
            return 
        B: 
        }
    }

    .method public onInitialize ()V {
        parameters: { this },
        code: {
        A: 
            line 43
            invokestatic com/keremyurekli/happyghastbuilding/networking/NetworkingManager.registerPayloads ()V
        B: 
            line 44
            invokestatic com/keremyurekli/happyghastbuilding/networking/NetworkingManager.imServer ()V
        C: 
            line 46
            invokestatic com/keremyurekli/happyghastbuilding/helpers/ScreenHelper.registerStuff ()V
        D: 
            line 50
            new com/keremyurekli/happyghastbuilding/ItemManager
            dup 
            invokespecial com/keremyurekli/happyghastbuilding/ItemManager.<init> ()V
            pop 
        E: 
            line 53
            getstatic net/fabricmc/fabric/api/event/player/UseEntityCallback.EVENT Lnet/fabricmc/fabric/api/event/Event;
            invokedynamic interact ()Lnet/fabricmc/fabric/api/event/player/UseEntityCallback; LambdaMetafactory.metafactory { (Lnet/minecraft/class_1657;Lnet/minecraft/class_1937;Lnet/minecraft/class_1268;Lnet/minecraft/class_1297;Lnet/minecraft/class_3966;)Lnet/minecraft/class_1269;, { invokestatic, com/keremyurekli/happyghastbuilding/Happyghastbuilding.lambda$onInitialize$0, (Lnet/minecraft/class_1657;Lnet/minecraft/class_1937;Lnet/minecraft/class_1268;Lnet/minecraft/class_1297;Lnet/minecraft/class_3966;)Lnet/minecraft/class_1269; }, (Lnet/minecraft/class_1657;Lnet/minecraft/class_1937;Lnet/minecraft/class_1268;Lnet/minecraft/class_1297;Lnet/minecraft/class_3966;)Lnet/minecraft/class_1269; }
            invokevirtual net/fabricmc/fabric/api/event/Event.register (Ljava/lang/Object;)V
        F: 
            line 56
            getstatic net/fabricmc/fabric/api/networking/v1/ServerPlayConnectionEvents.INIT Lnet/fabricmc/fabric/api/event/Event;
            invokedynamic onPlayInit ()Lnet/fabricmc/fabric/api/networking/v1/ServerPlayConnectionEvents$Init; LambdaMetafactory.metafactory { (Lnet/minecraft/class_3244;Lnet/minecraft/server/MinecraftServer;)V, { invokestatic, com/keremyurekli/happyghastbuilding/Happyghastbuilding.lambda$onInitialize$2, (Lnet/minecraft/class_3244;Lnet/minecraft/server/MinecraftServer;)V }, (Lnet/minecraft/class_3244;Lnet/minecraft/server/MinecraftServer;)V }
            invokevirtual net/fabricmc/fabric/api/event/Event.register (Ljava/lang/Object;)V
        G: 
            line 65
            getstatic net/fabricmc/fabric/api/event/lifecycle/v1/ServerLifecycleEvents.SERVER_STARTING Lnet/fabricmc/fabric/api/event/Event;
            invokedynamic onServerStarting ()Lnet/fabricmc/fabric/api/event/lifecycle/v1/ServerLifecycleEvents$ServerStarting; LambdaMetafactory.metafactory { (Lnet/minecraft/server/MinecraftServer;)V, { invokestatic, com/keremyurekli/happyghastbuilding/Happyghastbuilding.lambda$onInitialize$3, (Lnet/minecraft/server/MinecraftServer;)V }, (Lnet/minecraft/server/MinecraftServer;)V }
            invokevirtual net/fabricmc/fabric/api/event/Event.register (Ljava/lang/Object;)V
        H: 
            line 70
            getstatic net/fabricmc/fabric/api/event/lifecycle/v1/ServerLifecycleEvents.SERVER_STOPPING Lnet/fabricmc/fabric/api/event/Event;
            invokedynamic onServerStopping ()Lnet/fabricmc/fabric/api/event/lifecycle/v1/ServerLifecycleEvents$ServerStopping; LambdaMetafactory.metafactory { (Lnet/minecraft/server/MinecraftServer;)V, { invokestatic, com/keremyurekli/happyghastbuilding/Happyghastbuilding.lambda$onInitialize$4, (Lnet/minecraft/server/MinecraftServer;)V }, (Lnet/minecraft/server/MinecraftServer;)V }
            invokevirtual net/fabricmc/fabric/api/event/Event.register (Ljava/lang/Object;)V
        I: 
            line 80
            return 
        J: 
        }
    }

    .method private static interactMob (Lnet/minecraft/class_1657;Lnet/minecraft/class_1268;Lnet/minecraft/class_11187;)V {
        parameters: { player, hand, ghast },
        exceptions: { 
            { S, V, W, Ljava/lang/Exception; }
         },
        code: {
        A: 
            line 25
            aload player
            aload hand
            invokevirtual net/minecraft/class_1657.method_5998 (Lnet/minecraft/class_1268;)Lnet/minecraft/class_1799;
            astore itemStack
        B: 
            line 26
            aload itemStack
            getstatic net/minecraft/class_1802.field_8388 Lnet/minecraft/class_1792;
            invokevirtual net/minecraft/class_1799.method_31574 (Lnet/minecraft/class_1792;)Z
            ifeq Y
            aload ghast
            invokevirtual net/minecraft/class_11187.method_5685 ()Ljava/util/List;
            invokeinterface java/util/List.size ()I
            iconst_3 
            if_icmpge Y
            aload ghast
            getstatic net/minecraft/class_1304.field_48824 Lnet/minecraft/class_1304;
            invokevirtual net/minecraft/class_11187.method_6118 (Lnet/minecraft/class_1304;)Lnet/minecraft/class_1799;
            invokevirtual net/minecraft/class_1799.method_7960 ()Z
            ifne Y
        C: 
            line 27
            aload ghast
            invokevirtual net/minecraft/class_11187.method_37908 ()Lnet/minecraft/class_1937;
            getfield net/minecraft/class_1937.field_9236 Z
            ifne X
        D: 
            line 28
            new net/minecraft/class_1694
            dup 
            getstatic net/minecraft/class_1299.field_6126 Lnet/minecraft/class_1299;
            aload ghast
            invokevirtual net/minecraft/class_11187.method_37908 ()Lnet/minecraft/class_1937;
            invokespecial net/minecraft/class_1694.<init> (Lnet/minecraft/class_1299;Lnet/minecraft/class_1937;)V
            astore chestMinecart
        E: 
            line 29
            aload chestMinecart
            aload ghast
            invokevirtual net/minecraft/class_11187.method_23317 ()D
            aload ghast
            invokevirtual net/minecraft/class_11187.method_23318 ()D
            aload ghast
            invokevirtual net/minecraft/class_11187.method_23321 ()D
            aload ghast
            invokevirtual net/minecraft/class_11187.method_36454 ()F
            aload ghast
            invokevirtual net/minecraft/class_11187.method_36455 ()F
            invokevirtual net/minecraft/class_1694.method_5808 (DDDFF)V
        F: 
            line 30
            aload ghast
            invokevirtual net/minecraft/class_11187.method_37908 ()Lnet/minecraft/class_1937;
            aload chestMinecart
            invokevirtual net/minecraft/class_1937.method_8649 (Lnet/minecraft/class_1297;)Z
            pop 
        G: 
            line 31
            aload chestMinecart
            aload ghast
            invokevirtual net/minecraft/class_1694.method_5804 (Lnet/minecraft/class_1297;)Z
            pop 
        H: 
            line 32
            aload player
            invokevirtual net/minecraft/class_1657.method_31549 ()Lnet/minecraft/class_1656;
            getfield net/minecraft/class_1656.field_7477 Z
            ifne J
        I: 
            line 33
            aload itemStack
            iconst_1 
            invokevirtual net/minecraft/class_1799.method_7934 (I)V
        J: 
            line 34
            aload ghast
            invokevirtual net/minecraft/class_11187.method_37908 ()Lnet/minecraft/class_1937;
            astore v8
            aload v8
            instanceof net/minecraft/class_3218
            ifeq X
            aload v8
            checkcast net/minecraft/class_3218
            astore serverWorld
        K: 
            line 35
            aload serverWorld
            invokevirtual net/minecraft/class_3218.method_14170 ()Lnet/minecraft/class_2995;
            astore scoreboard
        L: 
            line 36
            aload scoreboard
            ldc "NoCollision"
            invokevirtual net/minecraft/class_269.method_1153 (Ljava/lang/String;)Lnet/minecraft/class_268;
            astore team
        M: 
            line 37
            aload team
            ifnonnull P
        N: 
            line 38
            aload scoreboard
            ldc "NoCollision"
            invokevirtual net/minecraft/class_269.method_1171 (Ljava/lang/String;)Lnet/minecraft/class_268;
            astore team
        O: 
            line 39
            aload team
            getstatic net/minecraft/class_270$class_271.field_1435 Lnet/minecraft/class_270$class_271;
            invokevirtual net/minecraft/class_268.method_1145 (Lnet/minecraft/class_270$class_271;)V
            goto R
        P: 
            line 40
            aload team
            invokevirtual net/minecraft/class_268.method_1203 ()Lnet/minecraft/class_270$class_271;
            getstatic net/minecraft/class_270$class_271.field_1435 Lnet/minecraft/class_270$class_271;
            if_acmpeq R
        Q: 
            line 41
            aload team
            getstatic net/minecraft/class_270$class_271.field_1435 Lnet/minecraft/class_270$class_271;
            invokevirtual net/minecraft/class_268.method_1145 (Lnet/minecraft/class_270$class_271;)V
        R: 
            line 43
            ldc "team join NoCollision %s"
            iconst_1 
            anewarray java/lang/Object
            dup 
            iconst_0 
            aload chestMinecart
            invokevirtual net/minecraft/class_1694.method_5845 ()Ljava/lang/String;
            aastore 
            invokestatic java/lang/String.format (Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
            astore command
        S: 
            // try-start:   range=[S-V] handler=W:java/lang/Exception 
            line 45
            aload serverWorld
            invokevirtual net/minecraft/class_3218.method_8503 ()Lnet/minecraft/server/MinecraftServer;
            invokevirtual net/minecraft/server/MinecraftServer.method_3734 ()Lnet/minecraft/class_2170;
            aload serverWorld
        T: 
            line 46
            invokevirtual net/minecraft/class_3218.method_8503 ()Lnet/minecraft/server/MinecraftServer;
            invokevirtual net/minecraft/server/MinecraftServer.method_3739 ()Lnet/minecraft/class_2168;
            invokevirtual net/minecraft/class_2168.method_9217 ()Lnet/minecraft/class_2168;
            aload command
        U: 
            line 45
            invokevirtual net/minecraft/class_2170.method_44252 (Lnet/minecraft/class_2168;Ljava/lang/String;)V
        V: 
            // try-end:     range=[S-V] handler=W:java/lang/Exception 
            line 48
            goto X
        W: 
            // try-handler: range=[S-V] handler=W:java/lang/Exception 
            astore v11
        X: 
            line 51
            return 
        Y: 
            line 53
            return 
        Z: 
        }
    }

    .method private static entityHandleV1 (Lnet/minecraft/class_1657;Lnet/minecraft/class_1937;Lnet/minecraft/class_1268;Lnet/minecraft/class_1297;Lnet/minecraft/class_3966;)Lnet/minecraft/class_1269; {
        parameters: { player, world, hand, entity, hitResult },
        code: {
        A: 
            line 84
            aload hitResult
            ifnonnull C
        B: 
            line 85
            getstatic net/minecraft/class_1269.field_5811 Lnet/minecraft/class_1269$class_9859;
            areturn 
        C: 
            aload entity
            instanceof net/minecraft/class_11187
            ifeq D
            aload player
            checkcast net/minecraft/class_1657
            aload hand
            checkcast net/minecraft/class_1268
            aload entity
            checkcast net/minecraft/class_11187
            invokestatic com/keremyurekli/happyghastbuilding/Happyghastbuilding.interactMob (Lnet/minecraft/class_1657;Lnet/minecraft/class_1268;Lnet/minecraft/class_11187;)V
        D: 
            line 87
            aload world
            getfield net/minecraft/class_1937.field_9236 Z
            ifne BF
        E: 
            line 98
            aload entity
            invokevirtual net/minecraft/class_1297.method_5864 ()Lnet/minecraft/class_1299;
            getstatic net/minecraft/class_1299.field_59668 Lnet/minecraft/class_1299;
            if_acmpne BF
            aload player
            invokevirtual net/minecraft/class_1657.method_5715 ()Z
            ifeq BF
        F: 
            line 99
            aload player
            aload hand
            invokevirtual net/minecraft/class_1657.method_5998 (Lnet/minecraft/class_1268;)Lnet/minecraft/class_1799;
            astore itemInHand
        G: 
            line 100
            aload itemInHand
            invokevirtual net/minecraft/class_1799.method_7909 ()Lnet/minecraft/class_1792;
            astore v7
            aload v7
            instanceof net/minecraft/class_1747
            ifeq BF
            aload v7
            checkcast net/minecraft/class_1747
            astore blockItem
        H: 
            line 101
            aload entity
            checkcast net/minecraft/class_11187
            astore ghast
        I: 
            line 102
            aload entity
            checkcast net/minecraft/class_1296
            astore pe
        J: 
            line 103
            aload pe
            invokevirtual net/minecraft/class_1296.method_6109 ()Z
            ifeq L
        K: 
            line 104
            getstatic net/minecraft/class_1269.field_5811 Lnet/minecraft/class_1269$class_9859;
            areturn 
        L: 
            line 106
            aload ghast
        M: 
            line 107
            aload hitResult
            invokevirtual net/minecraft/class_3966.method_17784 ()Lnet/minecraft/class_243;
            astore hitPos
        N: 
            line 108
            aload hitPos
            invokestatic net/minecraft/class_2338.method_49638 (Lnet/minecraft/class_2374;)Lnet/minecraft/class_2338;
            astore blockPos
        O: 
            line 109
            new net/minecraft/class_238
            dup 
            aload blockPos
            invokespecial net/minecraft/class_238.<init> (Lnet/minecraft/class_2338;)V
            astore blockBox
        P: 
            line 111
            aload pe
            invokevirtual net/minecraft/class_1296.method_5829 ()Lnet/minecraft/class_238;
            astore entityBox
        Q: 
            line 112
            aload entityBox
            invokevirtual net/minecraft/class_238.method_1005 ()Lnet/minecraft/class_243;
            astore entityCenter
        R: 
            line 115
            aload blockBox
            invokevirtual net/minecraft/class_238.method_1005 ()Lnet/minecraft/class_243;
            astore blockCenter
        S: 
            line 117
            aload blockBox
            invokevirtual net/minecraft/class_238.method_1005 ()Lnet/minecraft/class_243;
            astore newBlockPos
        T: 
            line 119
            iconst_0 
            istore x1
        U: 
            line 120
            iconst_0 
            istore y1
        V: 
            line 121
            iconst_0 
            istore z1
        W: 
            line 123
            aload entityBox
            aload blockBox
            invokevirtual net/minecraft/class_238.method_994 (Lnet/minecraft/class_238;)Z
            ifne X
            goto AI
        X: 
            line 126
            aload pe
            invokevirtual net/minecraft/class_1296.method_5829 ()Lnet/minecraft/class_238;
            aload blockBox
            invokevirtual net/minecraft/class_238.method_999 (Lnet/minecraft/class_238;)Lnet/minecraft/class_238;
            astore intersection
        Y: 
            line 127
            aload hitPos
            getfield net/minecraft/class_243.field_1351 D
            aload entityBox
            getstatic net/minecraft/class_2350$class_2351.field_11052 Lnet/minecraft/class_2350$class_2351;
            invokevirtual net/minecraft/class_238.method_990 (Lnet/minecraft/class_2350$class_2351;)D
            dcmpl 
            ifne AB
        Z: 
            line 128
            aload intersection
            invokevirtual net/minecraft/class_238.method_17940 ()D
            dconst_0 
            dcmpl 
            ifle AI
        AA: 
            line 129
            iconst_1 
            istore y1
            goto AI
        AB: 
            line 136
            aload intersection
            invokevirtual net/minecraft/class_238.method_17939 ()D
            aload intersection
            invokevirtual net/minecraft/class_238.method_17941 ()D
            dcmpg 
            ifge AF
        AC: 
            line 137
            aload entityCenter
            getfield net/minecraft/class_243.field_1352 D
            aload blockCenter
            getfield net/minecraft/class_243.field_1352 D
            dcmpl 
            ifle AE
        AD: 
            line 138
            iconst_m1 
            istore x1
            goto AI
        AE: 
            line 140
            iconst_1 
            istore x1
            goto AI
        AF: 
            line 143
            aload entityCenter
            getfield net/minecraft/class_243.field_1350 D
            aload blockCenter
            getfield net/minecraft/class_243.field_1350 D
            dcmpl 
            ifle AH
        AG: 
            line 144
            iconst_m1 
            istore z1
            goto AI
        AH: 
            line 146
            iconst_1 
            istore z1
        AI: 
            line 151
            iload x1
            ifne AJ
            iload y1
            ifne AJ
            iload z1
            ifeq AK
        AJ: 
            line 152
            new net/minecraft/class_2338
            dup 
            aload blockPos
            invokevirtual net/minecraft/class_2338.method_10263 ()I
            iload x1
            iadd 
            aload blockPos
            invokevirtual net/minecraft/class_2338.method_10264 ()I
            iload y1
            iadd 
            aload blockPos
            invokevirtual net/minecraft/class_2338.method_10260 ()I
            iload z1
            iadd 
            invokespecial net/minecraft/class_2338.<init> (III)V
            astore blockPos
        AK: 
            line 154
            aload world
            aload blockPos
            invokevirtual net/minecraft/class_1937.method_8320 (Lnet/minecraft/class_2338;)Lnet/minecraft/class_2680;
            invokevirtual net/minecraft/class_2680.method_45474 ()Z
            ifeq BE
        AL: 
            line 158
            aconst_null 
            astore p
        AM: 
            line 159
            aload blockItem
            invokevirtual net/minecraft/class_1747.method_7711 ()Lnet/minecraft/class_2248;
            invokevirtual net/minecraft/class_2248.method_9564 ()Lnet/minecraft/class_2680;
            invokevirtual net/minecraft/class_2680.method_28501 ()Ljava/util/Collection;
            invokeinterface java/util/Collection.iterator ()Ljava/util/Iterator;
            astore v20
        AN: 
            aload v20
            invokeinterface java/util/Iterator.hasNext ()Z
            ifeq AR
            aload v20
            invokeinterface java/util/Iterator.next ()Ljava/lang/Object;
            checkcast net/minecraft/class_2769
            astore p1
        AO: 
            line 160
            aload p1
            invokevirtual net/minecraft/class_2769.method_11899 ()Ljava/lang/String;
            ldc "facing"
            invokevirtual java/lang/String.equals (Ljava/lang/Object;)Z
            ifeq AQ
        AP: 
            line 161
            aload p1
            astore p
        AQ: 
            line 163
            goto AN
        AR: 
            line 165
            aload p
            ifnull AT
        AS: 
            line 167
            aload world
            aload blockPos
            aload blockItem
            invokevirtual net/minecraft/class_1747.method_7711 ()Lnet/minecraft/class_2248;
            invokevirtual net/minecraft/class_2248.method_9564 ()Lnet/minecraft/class_2680;
            aload p
            aload player
            invokevirtual net/minecraft/class_1657.method_5735 ()Lnet/minecraft/class_2350;
            invokevirtual net/minecraft/class_2680.method_47968 (Lnet/minecraft/class_2769;Ljava/lang/Comparable;)Ljava/lang/Object;
            checkcast net/minecraft/class_2680
            invokevirtual net/minecraft/class_1937.method_8501 (Lnet/minecraft/class_2338;Lnet/minecraft/class_2680;)Z
            pop 
            goto AU
        AT: 
            line 169
            aload world
            aload blockPos
            aload blockItem
            invokevirtual net/minecraft/class_1747.method_7711 ()Lnet/minecraft/class_2248;
            invokevirtual net/minecraft/class_2248.method_9564 ()Lnet/minecraft/class_2680;
            invokevirtual net/minecraft/class_1937.method_8501 (Lnet/minecraft/class_2338;Lnet/minecraft/class_2680;)Z
            pop 
        AU: 
            line 172
            aload world
            aload blockPos
            aload itemInHand
            invokestatic com/keremyurekli/happyghastbuilding/Happyghastbuilding.copyComponentsToBlockEntity (Lnet/minecraft/class_1937;Lnet/minecraft/class_2338;Lnet/minecraft/class_1799;)V
        AV: 
            line 173
            aload player
            aload hand
            invokevirtual net/minecraft/class_1657.method_6104 (Lnet/minecraft/class_1268;)V
        AW: 
            line 174
            aload blockItem
            invokevirtual net/minecraft/class_1747.method_7711 ()Lnet/minecraft/class_2248;
            invokevirtual net/minecraft/class_2248.method_9564 ()Lnet/minecraft/class_2680;
            invokevirtual net/minecraft/class_2680.method_26231 ()Lnet/minecraft/class_2498;
            astore soundGroup
        AX: 
            line 175
            aload world
            aconst_null 
            aload blockPos
            aload soundGroup
        AY: 
            line 178
            invokevirtual net/minecraft/class_2498.method_10598 ()Lnet/minecraft/class_3414;
            getstatic net/minecraft/class_3419.field_15245 Lnet/minecraft/class_3419;
            aload soundGroup
        AZ: 
            line 180
            invokevirtual net/minecraft/class_2498.method_10597 ()F
            fconst_1 
            fadd 
            fconst_2 
            fdiv 
            aload soundGroup
        BA: 
            line 181
            invokevirtual net/minecraft/class_2498.method_10599 ()F
            ldc 0.8000000119F
            fmul 
        BB: 
            line 175
            invokevirtual net/minecraft/class_1937.method_8396 (Lnet/minecraft/class_1297;Lnet/minecraft/class_2338;Lnet/minecraft/class_3414;Lnet/minecraft/class_3419;FF)V
        BC: 
            line 183
            aload itemInHand
            iconst_1 
            aload player
            invokevirtual net/minecraft/class_1799.method_57008 (ILnet/minecraft/class_1309;)V
        BD: 
            line 184
            getstatic net/minecraft/class_1269.field_5812 Lnet/minecraft/class_1269$class_9860;
            areturn 
        BE: 
            line 189
            getstatic net/minecraft/class_1269.field_5812 Lnet/minecraft/class_1269$class_9860;
            areturn 
        BF: 
            line 193
            getstatic net/minecraft/class_1269.field_5811 Lnet/minecraft/class_1269$class_9859;
            areturn 
        BG: 
        }
    }

    .method private static copyComponentsToBlockEntity (Lnet/minecraft/class_1937;Lnet/minecraft/class_2338;Lnet/minecraft/class_1799;)V {
        parameters: { world, pos, stack },
        code: {
        A: 
            line 196
            aload world
            aload pos
            invokevirtual net/minecraft/class_1937.method_8321 (Lnet/minecraft/class_2338;)Lnet/minecraft/class_2586;
            astore blockEntity
        B: 
            line 197
            aload blockEntity
            ifnull E
        C: 
            line 198
            aload blockEntity
            aload stack
            invokevirtual net/minecraft/class_2586.method_58683 (Lnet/minecraft/class_1799;)V
        D: 
            line 199
            aload blockEntity
            invokevirtual net/minecraft/class_2586.method_5431 ()V
        E: 
            line 201
            return 
        F: 
        }
    }

    .method private static synthetic lambda$onInitialize$4 (Lnet/minecraft/server/MinecraftServer;)V {
        parameters: { server },
        code: {
        A: 
            line 72
            aload server
            getstatic net/minecraft/class_5218.field_24188 Lnet/minecraft/class_5218;
            invokevirtual net/minecraft/server/MinecraftServer.method_27050 (Lnet/minecraft/class_5218;)Ljava/nio/file/Path;
            invokestatic com/keremyurekli/happyghastbuilding/helpers/FileManager.save (Ljava/nio/file/Path;)V
        B: 
            line 76
            return 
        C: 
        }
    }

    .method private static synthetic lambda$onInitialize$3 (Lnet/minecraft/server/MinecraftServer;)V {
        parameters: { server },
        code: {
        A: 
            line 67
            aload server
            getstatic net/minecraft/class_5218.field_24188 Lnet/minecraft/class_5218;
            invokevirtual net/minecraft/server/MinecraftServer.method_27050 (Lnet/minecraft/class_5218;)Ljava/nio/file/Path;
            aload server
            invokevirtual net/minecraft/server/MinecraftServer.method_30611 ()Lnet/minecraft/class_5455$class_6890;
            invokestatic com/keremyurekli/happyghastbuilding/helpers/FileManager.load (Ljava/nio/file/Path;Lnet/minecraft/class_5455$class_6890;)V
        B: 
            line 68
            return 
        C: 
        }
    }

    .method private static synthetic lambda$onInitialize$2 (Lnet/minecraft/class_3244;Lnet/minecraft/server/MinecraftServer;)V {
        parameters: { handler, server },
        code: {
        A: 
            line 57
            getstatic com/keremyurekli/happyghastbuilding/Constant.INFO_LIST Ljava/util/HashMap;
            aload handler
            invokedynamic accept (Lnet/minecraft/class_3244;)Ljava/util/function/BiConsumer; LambdaMetafactory.metafactory { (Ljava/lang/Object;Ljava/lang/Object;)V, { invokestatic, com/keremyurekli/happyghastbuilding/Happyghastbuilding.lambda$onInitialize$1, (Lnet/minecraft/class_3244;Ljava/util/UUID;Lcom/keremyurekli/happyghastbuilding/weirdstuff/GhastInfo;)V }, (Ljava/util/UUID;Lcom/keremyurekli/happyghastbuilding/weirdstuff/GhastInfo;)V }
            invokevirtual java/util/HashMap.forEach (Ljava/util/function/BiConsumer;)V
        B: 
            line 61
            return 
        C: 
        }
    }

    .method private static synthetic lambda$onInitialize$1 (Lnet/minecraft/class_3244;Ljava/util/UUID;Lcom/keremyurekli/happyghastbuilding/weirdstuff/GhastInfo;)V {
        parameters: { handler, uuid, ghastInfo },
        code: {
        A: 
            line 58
            aload handler
            getfield net/minecraft/class_3244.field_14140 Lnet/minecraft/class_3222;
            new com/keremyurekli/happyghastbuilding/networking/GhastAddedPayload
            dup 
            aload ghastInfo
            getfield com/keremyurekli/happyghastbuilding/weirdstuff/GhastInfo.ghastUUID Ljava/util/UUID;
            invokespecial com/keremyurekli/happyghastbuilding/networking/GhastAddedPayload.<init> (Ljava/util/UUID;)V
            invokestatic com/keremyurekli/happyghastbuilding/networking/NetworkingManager.sendGhastAddedPayload (Lnet/minecraft/class_3222;Lcom/keremyurekli/happyghastbuilding/networking/GhastAddedPayload;)V
        B: 
            line 59
            aload handler
            getfield net/minecraft/class_3244.field_14140 Lnet/minecraft/class_3222;
            new com/keremyurekli/happyghastbuilding/networking/FurnaceStatePayload
            dup 
            aload ghastInfo
            getfield com/keremyurekli/happyghastbuilding/weirdstuff/GhastInfo.ghastUUID Ljava/util/UUID;
            aload ghastInfo
            getfield com/keremyurekli/happyghastbuilding/weirdstuff/GhastInfo.isLit Z
            invokespecial com/keremyurekli/happyghastbuilding/networking/FurnaceStatePayload.<init> (Ljava/util/UUID;Z)V
            invokestatic com/keremyurekli/happyghastbuilding/networking/NetworkingManager.sendFurnaceStatePayload (Lnet/minecraft/class_3222;Lcom/keremyurekli/happyghastbuilding/networking/FurnaceStatePayload;)V
        C: 
            line 60
            return 
        D: 
        }
    }

    .method private static synthetic lambda$onInitialize$0 (Lnet/minecraft/class_1657;Lnet/minecraft/class_1937;Lnet/minecraft/class_1268;Lnet/minecraft/class_1297;Lnet/minecraft/class_3966;)Lnet/minecraft/class_1269; {
        parameters: { player, world, hand, entity, hitResult },
        code: {
        A: 
            line 54
            aload player
            aload world
            aload hand
            aload entity
            aload hitResult
            invokestatic com/keremyurekli/happyghastbuilding/Happyghastbuilding.entityHandleV1 (Lnet/minecraft/class_1657;Lnet/minecraft/class_1937;Lnet/minecraft/class_1268;Lnet/minecraft/class_1297;Lnet/minecraft/class_3966;)Lnet/minecraft/class_1269;
            areturn 
        B: 
        }
    }

}