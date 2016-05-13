package com.detell.explorer.Models.Blocks;

import com.badlogic.gdx.graphics.Texture;

/**
 * Created by Derick on 5/12/2016.
 */
public class BlockRock extends Block {

    protected static Texture texture = new Texture("rock.png");
    public static int ID = 1;

    public BlockRock(int x, int y) {
        super(x, y);
    }

    @Override
    public Texture getTexture(){
        return texture;
    }
}
