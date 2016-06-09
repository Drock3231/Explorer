package com.detell.explorer.Models.Entities;

import com.badlogic.gdx.graphics.Texture;

/**
 * Created by Derick on 6/9/2016.
 */
public class EntityTree extends Entity{

    protected static Texture texture = new Texture("entityTiles/tree.png");

    public EntityTree(int x, int y) {
        super(x, y);
    }

    @Override
    public Texture getTexture(){
        return texture;
    }

}
