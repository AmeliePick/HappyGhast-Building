
# Happy Ghast Building

This mod is based on [Happy Ghast Building by keremyurekli](https://github.com/keremyurekli/happyghastbuilding) and [Chest On Ghast by noramibu](https://github.com/noramibu/chest-on-ghast).

This fork is for Minecraft(singleplayer) 1.21.8 version. See ["Details"](#details).

# For developers
I wrote this fork and made code merges only through the JVM bytecode/assembler. I just don't want to deal with all that modding stuff for the game and Java itself.
You can check usual Java code but it's hard to read sometimes because this is an output from a decompiler. There are also some original java code files with it's original comments.
My notes and comments can be found only in ASM files in "decompiled" dir.


```
├── Root
│   ├── src/main — Java code.
│   └── src/JVMASM — only changed bytecode. 
│       └── bin/ - compiled code. Can be pasted straight into mod's JAR file.
│       └── decompiled/ - decompiled(CFR-0-152) raw bytecode(assembler).
└── README.md
```

# Details
+ This fork allows to use harnesses from the first base mod. But instead of a one chest, you can place 3 minecarts with chests like in the second base mod(On any harness at all).
+ It has a changed recipe for adventurer's harness - now without a chest. Adventurer's harness also doesn't have a bed bacause at this moment(Oct, 2025), the feature was not released in the original mod. It doesn't change demolition harness but check out ["Issues"](#issues).
+ It also allows to place blocks all around a ghast even not being on top of him.
+ The fork is translated to russian language.


Adventurer's Harness            |  Demolition Harness(Press R key to open fire)
:-------------------------:|:-------------------------:
![a-harness](https://github.com/user-attachments/assets/6b18e9e3-5d37-492b-b97e-b9274893cd9d) <img src="https://github.com/user-attachments/assets/a1dd62db-f57f-44d8-8162-991bbac6bd30" width="350" height="350"> |  ![demo-harness](https://github.com/user-attachments/assets/08607f77-4e2d-41f7-9648-6fee43c6e6b0) <img src="https://github.com/user-attachments/assets/8e49ca37-4b1f-4482-8a89-e2f2f4d7821c" width="350" height="350">


## Issues
At this moment the mod works fine on 1.21.8 but there're some issues:
+ Sometimes you'll fall down from a ghast if you'll save and close the game being on top of him.
**Quick Solution**: Start riding the ghast and then exit the game.
+ Furnace, dispensers doesn't save items inside when you close the game. I made it to prevent the game crashes during a world loading.
**Quick Solution**: Don't leave your stuff inside dispensers and the furnace. *Note*: To fix, it needs to remake the entire save-load logic according to the new minecraft mechanics.

## Requirements
1. Fabric for 1.21.8
2. `fabric-api-0.133.4+1.21.8.jar` inside the mod folder
  

---


