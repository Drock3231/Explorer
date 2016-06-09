package com.detell.explorer.Models;

import com.badlogic.gdx.math.Vector2;
import com.detell.explorer.Models.Blocks.Block;

import java.util.ArrayList;

/**
 * Created by Derick on 5/12/2016.
 */
public class Map {
    
    private Chunks[][] chunks;
    
    private static final int MAP_SIZE_X = 3;
    private static final int MAP_SIZE_Y = 3;
    private static final Vector2 SIZE = new Vector2(MAP_SIZE_X,MAP_SIZE_Y);
    
    public Map(){
           chunks = new Chunks[MAP_SIZE_X][MAP_SIZE_Y];
    }
    
    public void addChunks(Block[][] blocks, int x, int y){
        chunks[x][y] = new Chunks(blocks);
    }
    
    public Chunks[][] getChunks(){
        return chunks;
    }
    
    public static Vector2 getMapSize(){ return SIZE;}
}
