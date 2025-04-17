
# ![icon](https://github.com/user-attachments/assets/72008879-3a4a-4010-a439-b0f95697d72e) happy-ghast building

Its a mod that adds new features to happy ghast entities. It started as a simple mod that added a small tweak to the game. You can still find the original version in v1 branch.

Download it from here or from modrinth.
https://modrinth.com/mod/happy-ghast-building

> [!WARNING]
> It should work in multiplayer with no issue, but if you think its not, create a new issue [ticket](https://github.com/keremyurekli/happyghastbuilding/issues)


# 2 New Harness Types

## Adventurer's Harness
> ### Equipped with a crafting table, a chest, a furnace and a bed(not implemented yet)
![a-harness](https://github.com/user-attachments/assets/1ba486b5-0a12-4d9a-acb7-4117bcc689a9 )
<br/>
<img src="https://github.com/user-attachments/assets/dc404751-8e2b-4dd4-bfe1-89a3d1121dab" width="350" height="350">

## Demolition Harness
> ### Equipped with 4 powerful dispensers, they can carry every payload you want! (i prefer 2304 tnts)
> ### Press the keybind(default is R) to fire them
![demo-harness](https://github.com/user-attachments/assets/08607f77-4e2d-41f7-9648-6fee43c6e6b0)
<br/>
<img src="https://github.com/user-attachments/assets/8e49ca37-4b1f-4482-8a89-e2f2f4d7821c" width="350" height="350">

# Video
[![2025-04-17_20 19 31](https://github.com/user-attachments/assets/80bd5399-b128-42ed-8262-c00c73464c05)
](https://youtu.be/cVYwzmQ0Ju8)

# TODO
- Implement bed features (spawnpoint & sleep)

# Read this part if you are a developer
If you think this mod's source code can help with the development of your own mod, you’ll need to understand the structure described below. All blocks (dispensers, etc.) you see are fake—their visual appearance and hitbox exist only on the client. The client handles interaction with them and sends a packet to the server containing the necessary information to open a GUI or perform other actions.

### Client
> Modifies renderer via mixin  
> Knows the exact positions of hitboxes and ghasts' UUIDs  
> DOES NOT know the results of actions

### Server
> Loves packets  
> Handles GUIs (save, load, interact, etc.)  
> Receives packets about the boxes the client interacts with and performs the appropriate actions
