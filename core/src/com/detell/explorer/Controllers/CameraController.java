package com.detell.explorer.Controllers;

import com.badlogic.gdx.graphics.Camera;
import com.detell.explorer.Models.Player;
import com.detell.explorer.Models.World;

/**
 * Created by Derick on 5/17/2016.
 */
public class CameraController {

    private Camera camera;
    private World world;
    private Player player;

    public CameraController(World world){
        camera = world.getCamera();
        this.world = world;
        player = world.getPlayer();
    }

    public void update(){

        //if(player.getPosition().y - world.getViewport().y/2 < 0) camera.position.x = player.getPosition().x;
        //else
        camera.position.set(player.getPosition().x, player.getPosition().y,0);
        camera.update();
    }

}
