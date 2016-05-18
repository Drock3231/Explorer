package com.detell.explorer.Views;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.detell.explorer.Models.Blocks.Block;
import com.detell.explorer.Models.Chunks;
import com.detell.explorer.Models.Player;
import com.detell.explorer.Models.World;

import java.lang.reflect.Array;

/**
 * Created by Derick on 5/11/2016.
 *
 * Class that renders game-world variables
 */
public class WorldRenderer {

    private SpriteBatch spriteBatch;

    private OrthographicCamera camera;
    private Vector2 viewPort;

    private World world;

    public WorldRenderer(World world){
        spriteBatch = new SpriteBatch();

        camera = world.getCamera();
        camera.setToOrtho(false, world.getViewport().x, world.getViewport().y);
        viewPort = new Vector2(world.getViewport());

        this.world = world;
    }

    public void render(){

        Gdx.gl.glClearColor(0.1f, 0.1f, 0.1f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        spriteBatch.setProjectionMatrix(camera.combined);
        spriteBatch.begin();

        renderWorld();

        spriteBatch.end();

    }

    private void renderWorld(){
        //acquire player object so that we can cull tiles
        Player player = world.getPlayer();

        //needs to be rewritten
        //iterates through all the chunks and draws the blocks that are in viewport range (a little larger than viewport range, actually)
        for(int a = 0; a < world.getMap().getMapSize().x; a++){
            for(int b = 0; b < world.getMap().getMapSize().y; b++) {

                Chunks chunk = world.getMap().getChunks()[a][b];

                for (int x = 0; x < chunk.getBlocks().length; x++) {
                    for (int y = 0; y < chunk.getBlocks().length; y++) {
                        Block block = chunk.getBlocks()[x][y];
                        if (!(player.getPosition().x + viewPort.x < block.getPosition().x) && !(player.getPosition().x - viewPort.x > block.getPosition().x)) {
                            spriteBatch.draw(block.getTexture(), block.getPosition().x, block.getPosition().y, block.getSize().x, block.getSize().y);
                        }
                    }
                }
            }
        }


    }

}
