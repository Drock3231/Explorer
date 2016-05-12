package com.detell.explorer.Models.Blocks;

import com.badlogic.gdx.graphics.Texture;

/**
 * Created by Derick on 5/12/2016.
 */
public class BlockSand extends Block {

    protected static Texture texture = new Texture("data/air.png");
    public static int ID = 0;

    public BlockSand(int x, int y){
        super(x, y);
        isSolid = false;
    }

    @Override
    public Texture getTexture(){
        return texture;
    }

}
