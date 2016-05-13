package com.detell.explorer.Models;

import com.badlogic.gdx.math.Vector2;
import com.detell.explorer.Models.Blocks.Block;

/**
 * Created by Derick on 5/10/2016.
 */
public class Chunks {

    private static final int SIZE_X = 30;
    private static final int SIZE_Y = 30;
    protected static final Vector2 SIZE = new Vector2(SIZE_X,SIZE_Y);

    private Block[][] blocks = new Block[SIZE_X][SIZE_Y];

    public Chunks(Block[][] blocks){
        this.blocks = blocks;
    }

    public Block[][] getBlocks() {
        return blocks;
    }

    public static Vector2 getSize(){
        return SIZE;
    }
}
