package com.detell.explorer.Controllers;

import com.detell.explorer.Models.World;

/**
 * Created by Derick on 5/11/2016.
 *
 * Class that controls and updates game-world variables
 */
public class WorldController {

    private World world;

    private CameraController cameraController;
    private ChunkController chunkController;
    private EntityChunkController entityChunkController;
    private PlayerController playerController;

    public WorldController(World world){
        this.world = world;

        cameraController = new CameraController(world);
        chunkController = new ChunkController();
        entityChunkController = new EntityChunkController();
        playerController = new PlayerController(world);

        createMap();
        createEntityMap();
    }

    public void update(float delta){
        playerController.update(delta);
        cameraController.update();
    }

    private void createMap(){
        world.addMap(chunkController.generateMap());
    }

    private void createEntityMap(){
        world.addEntityMap(entityChunkController.generateEntityMap());
    }

    public PlayerController getPlayerController(){
        return playerController;
    }
}
