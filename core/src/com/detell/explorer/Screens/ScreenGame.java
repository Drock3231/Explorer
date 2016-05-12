package com.detell.explorer.Screens;

import com.badlogic.gdx.Game;

import com.detell.explorer.Controllers.WorldController;
import com.detell.explorer.Models.World;
import com.detell.explorer.Views.WorldRenderer;

/**
 * Created by Derick on 5/10/2016.
 */
public class ScreenGame extends ScreenBase {
    //world variables
    private World world;
    private WorldController worldController;
    private WorldRenderer worldRenderer;

    //initializing world variables
    public ScreenGame(Game game){

        world = new World();
        worldController = new WorldController(world);
        worldRenderer= new WorldRenderer(world);


    }

    //rendering world variables
    @Override
    public void render(float delta){

        if(delta < 0.05f){
            worldController.update(delta);
            worldRenderer.render();
        }
    }

}
