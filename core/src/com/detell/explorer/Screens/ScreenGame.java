package com.detell.explorer.Screens;

import com.badlogic.gdx.*;

import com.detell.explorer.Controllers.WorldController;
import com.detell.explorer.Models.World;
import com.detell.explorer.Views.WorldRenderer;

/**
 * Created by Derick on 5/10/2016.
 */
public class ScreenGame extends ScreenBase implements InputProcessor {
    //world variables
    private World world;
    private WorldController worldController;
    private WorldRenderer worldRenderer;

    //initializing world variables
    public ScreenGame(Game game){

        world = new World();
        worldController = new WorldController(world);
        worldRenderer= new WorldRenderer(world);

        Gdx.input.setInputProcessor(this);

    }

    //rendering world variables
    @Override
    public void render(float delta){

        if(delta < 0.05f){
            worldController.update(delta);
            worldRenderer.render();
        }
    }

    /*~~~~~~~~~~~Input Processor Methods~~~~~~~~~~~*/

    @Override
    public boolean keyDown(int keycode) {

        if(Gdx.app.getType() == Application.ApplicationType.Desktop){
            if(keycode == Input.Keys.LEFT){
                worldController.getPlayerController().leftPressed();
            } else if(keycode == Input.Keys.RIGHT){
                worldController.getPlayerController().rightPressed();
            } else if(keycode == Input.Keys.UP){
                worldController.getPlayerController().upPressed();
            } else if(keycode == Input.Keys.DOWN) {
                worldController.getPlayerController().downPressed();
            }
        }


        return false;
    }

    @Override
    public boolean keyUp(int keycode) {

        if(Gdx.app.getType() == Application.ApplicationType.Desktop){
            if(keycode == Input.Keys.LEFT){
                worldController.getPlayerController().leftReleased();
            } else if(keycode == Input.Keys.RIGHT){
                worldController.getPlayerController().rightReleased();
            } else if(keycode == Input.Keys.UP){
                worldController.getPlayerController().upReleased();
            } else if(keycode == Input.Keys.DOWN) {
                worldController.getPlayerController().downReleased();
            }
        }

        return false;
    }

    @Override
    public boolean keyTyped(char character) {
        return false;
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {

        if(Gdx.app.getType() == Application.ApplicationType.Android){
            double realTouchX = Math.floor(screenX/(Gdx.graphics.getWidth()/10));
            double realTouchY = Math.floor(screenY/(Gdx.graphics.getHeight()/6));

            if((realTouchX == 8) && (realTouchY == 4)){
                worldController.getPlayerController().leftPressed();
            }else if(realTouchX == 9 && realTouchY == 4){
                worldController.getPlayerController().rightPressed();
            } else if(realTouchX == 2 && realTouchY == 4){
                worldController.getPlayerController().upPressed();
            } else if(realTouchX == 1 && realTouchY == 4) {
                worldController.getPlayerController().downPressed();
            }
        }

        return false;
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {

        if(Gdx.app.getType() == Application.ApplicationType.Android){
            double realTouchX = Math.floor(screenX/(Gdx.graphics.getWidth()/10));
            double realTouchY = Math.floor(screenY/(Gdx.graphics.getHeight()/6));

            if((realTouchX == 8) && (realTouchY == 4)){
                worldController.getPlayerController().leftReleased();
            }else if(realTouchX == 9 && realTouchY == 4){
                worldController.getPlayerController().rightReleased();
            } else if(realTouchX == 2 && realTouchY == 4){
                worldController.getPlayerController().upReleased();
            } else if(realTouchX == 1 && realTouchY == 4) {
                worldController.getPlayerController().downReleased();
            }
        }

        return false;
    }

    @Override
    public boolean touchDragged(int i, int i2, int i3) {
        return false;
    }

    @Override
    public boolean mouseMoved(int i, int i2) {
        return false;
    }

    @Override
    public boolean scrolled(int i) {
        return false;
    }
}
