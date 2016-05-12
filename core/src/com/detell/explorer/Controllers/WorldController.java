package com.detell.explorer.Controllers;

import com.detell.explorer.Models.World;

/**
 * Created by Derick on 5/11/2016.
 */
public class WorldController {

    private World world;

    public WorldController(World world){
        this.world = world;

        createMap();
    }

    public void update(float delta){

    }

    private void createMap(){
        world.addMap(chunkController.generateMap());
    }
}
