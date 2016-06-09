package com.detell.explorer.Models;

import com.badlogic.gdx.math.Vector2;
import com.detell.explorer.Models.Blocks.Block;
import com.detell.explorer.Models.Entities.Entity;

import java.util.ArrayList;

/**
 * Created by Derick on 6/9/2016.
 */
public class EntityMap {

    private EntityChunk[][] entityChunks;

    private static final int ENTITY_MAP_SIZE_X = 3;
    private static final int ENTITY_MAP_SIZE_Y = 3;
    private static final Vector2 SIZE = new Vector2(ENTITY_MAP_SIZE_X,ENTITY_MAP_SIZE_Y);

    public EntityMap(){
        entityChunks = new EntityChunk[ENTITY_MAP_SIZE_X][ENTITY_MAP_SIZE_Y];
    }

    public void addEntityChunks(ArrayList<Entity> entities, int x, int y){
        entityChunks[x][y] = new EntityChunk(entities);
    }

    public EntityChunk[][] getEntityChunks(){
        return entityChunks;
    }

    public Vector2 getEntityMapSize(){ return SIZE;}

}
