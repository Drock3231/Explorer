package com.detell.explorer.Controllers;

import com.detell.explorer.Models.World;

/**
 * Created by Derick on 5/11/2016.
 *
 * Class that controls and updates game-world variables
 */
public class WorldController {

    private World world;

    private ChunkController chunkController;

    public WorldController(World world){
        this.world = world;
        chunkController = new ChunkController();

        createMap();
    }

    public void update(float delta){

    }

    private void createMap(){
        world.addMap(chunkController.generateMap());
    }
}
