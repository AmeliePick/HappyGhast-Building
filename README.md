
# ![icon](https://github.com/user-attachments/assets/72008879-3a4a-4010-a439-b0f95697d72e) Happy Ghast Building

The full mod's description can be founded at: https://github.com/keremyurekli/happyghastbuilding

This fork is for Minecraft 1.21.8 version.
I don't want to deal with all modding stuff for the game, so a lot of adapations and fixes were made through JVM ASM.

```
├── Root
│   ├── src/main — a standalone Java code.
│   └── src/JVMASM — only decompiled changed code. Can be pasted straight into mod's JAR file.
└── README.md
```

## Issues
At this moment the mod works fine on 1.21.8 but there're some issues:
+ When you close the game it doesn't save items in harness containters(chest, furnace, dispenser).
  **Solution**: needs to rewrite load method in FileManager.

## Requirements
1. Fabric for 1.21.8
2. `0.128.2+1.21.6` fabric lib inside the mod folder(probably will be changed in next updates)
  
---

Adventurer's Harness            |  Demolition Harness(Press R key to open fire)
:-------------------------:|:-------------------------:
![a-harness](https://github.com/user-attachments/assets/1ba486b5-0a12-4d9a-acb7-4117bcc689a9 ) <img src="https://github.com/user-attachments/assets/dc404751-8e2b-4dd4-bfe1-89a3d1121dab" width="350" height="350"> |  ![demo-harness](https://github.com/user-attachments/assets/08607f77-4e2d-41f7-9648-6fee43c6e6b0) <img src="https://github.com/user-attachments/assets/8e49ca37-4b1f-4482-8a89-e2f2f4d7821c" width="350" height="350">


