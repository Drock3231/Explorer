package com.detell.explorer.Models;

import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

/**
 * Created by Derick on 5/10/2016.
 */
public class Player {

    private Vector2 position;
    private Vector2 acceleration;
    private Vector2 velocity;

    private long hitstunTime = 0;

    private Rectangle bounds;
    private static final Vector2 SIZE = new Vector2(1f,1f);

    public Player(int x, int y){

        position = new Vector2(x,y);
        acceleration = new Vector2();
        velocity = new Vector2();

        bounds = new Rectangle(this.position.x,this.position.y,SIZE.x,SIZE.y);
    }

    public Vector2 getPosition(){
        return position;
    }

    public Vector2 getAcceleration(){
        return acceleration;
    }

    public Vector2 getVelocity(){
        return velocity;
    }

    public long getHitstunTime(){
        return hitstunTime;
    }

    public void setHitstunTime(long hitstunTime){
        this.hitstunTime = hitstunTime;
    }

    public Vector2 getSize(){
        return SIZE;
    }

    public Rectangle getBounds(){
        return bounds;
    }

}
