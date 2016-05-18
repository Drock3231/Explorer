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
    private PlayerController playerController;

    public WorldController(World world){
        this.world = world;

        cameraController = new CameraController(world);
        chunkController = new ChunkController();
        playerController = new PlayerController(world);

        createMap();
    }

    public void update(float delta){
        playerController.update(delta);
        cameraController.update();
    }

    private void createMap(){
        world.addMap(chunkController.generateMap());
    }

    public PlayerController getPlayerController(){
        return playerController;
    }
}
