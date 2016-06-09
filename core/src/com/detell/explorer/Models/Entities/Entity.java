package com.detell.explorer.Models.Entities;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

/**
 * Created by Derick on 6/9/2016.
 */
public class Entity {

    private Rectangle bounds;
    private Vector2 position;
    protected static final Vector2 SIZE = new Vector2(1,1);

    protected boolean isSolid = true;
    private static final long HITSTUN = 100L;

    protected static Texture texture;

    public Entity(int x, int y){
        position = new Vector2(x,y);
        bounds = new Rectangle(x,y,SIZE.x,SIZE.y);
    }

    public Rectangle getBounds(){
        return bounds;
    }

    public Vector2 getPosition(){
        return position;
    }

    public static Vector2 getSize(){
        return SIZE;
    }

    public boolean isSolid(){
        return isSolid;
    }

    public static long getHITSTUN(){
        return HITSTUN;
    }

    public Texture getTexture(){
        return texture;
    }

}
