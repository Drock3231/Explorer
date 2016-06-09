package com.detell.explorer.Models;

import com.badlogic.gdx.math.Vector2;
import com.detell.explorer.Models.Blocks.Block;
import com.detell.explorer.Models.Entities.Entity;

import java.util.ArrayList;

/**
 * Created by Derick on 6/9/2016.
 */
public class EntityChunk {

    private ArrayList<Entity> entities = new ArrayList<>();

    public EntityChunk(ArrayList<Entity> entities){
        this.entities = entities;
    }

    public ArrayList<Entity> getEntities() {
        return entities;
    }

}
