package com.detell.explorer.Controllers;

import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.Pool;
import com.detell.explorer.Models.Blocks.Block;
import com.detell.explorer.Models.Player;
import com.detell.explorer.Models.World;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Derick on 5/17/2016.
 */
public class PlayerController {

    //player and world that the calculations will be performed for
    private Player player;
    private World world;

    //Creation and storage of keys that the player uses to interact with the world
    public enum Keys{
        Left,Right,Up,Down
    }

    static Map<Keys,Boolean> keys = new HashMap<Keys, Boolean>();
    static{
        keys.put(Keys.Left,false);
        keys.put(Keys.Right,false);
        keys.put(Keys.Up,false);
        keys.put(Keys.Down,false);
    }

    //Pools for memory saving (not sure if this works?)
    private Pool<Rectangle> rectPool = new Pool<Rectangle>() {
        @Override
        protected Rectangle newObject() {
            return new Rectangle();
        }
    };

    //this array holds the blocks that the player can collide with
    private Array<Block> collidableBlocks = new Array<>();

    //These are all the values the game uses to calculate acceleration
    //multiplying by two gives you real-world values in m/s
    private static final float ACCELERATION = 1f;
    private static final float MAX_VEL = 1f;

    //simple constructor that sets the world and player to calculate for
    public PlayerController(World world){
        this.world = world;
        player = world.getPlayer();
    }

    /*update method.
    *Calls the handleInput method that will set the player's acceleration,
    *Puts acceleration in state time and adds it to velocity
    *Makes sure velocity isn't too large and sets it to its max value if so
    *Calls the blockCollision method which stops the player from going ghost
    */
    public void update(float delta){
        handleInput();

        player.getAcceleration().scl(delta);
        player.getVelocity().add(player.getAcceleration());

        if(player.getVelocity().x > MAX_VEL) player.getVelocity().x = MAX_VEL;
        if(player.getVelocity().x < -MAX_VEL) player.getVelocity().x = -MAX_VEL;

        if(player.getVelocity().y > MAX_VEL) player.getVelocity().y = MAX_VEL;
        if(player.getVelocity().y < -MAX_VEL) player.getVelocity().y = -MAX_VEL;

        player.getPosition().add(player.getVelocity());
    }

    private void handleInput(){

        if(keys.get(Keys.Left)){
            player.getAcceleration().x = -ACCELERATION;
        } else if(keys.get(Keys.Right)){
            player.getAcceleration().x = ACCELERATION;
        }else if(keys.get(Keys.Up)){
            player.getAcceleration().y = ACCELERATION;
        }else if(keys.get(Keys.Down)){
            player.getAcceleration().y = -ACCELERATION;
        }else{
            player.getAcceleration().x = 0;
            player.getVelocity().x = 0;

            player.getAcceleration().y = 0;
            player.getVelocity().y = 0;
        }


    }

    /*~~~~~~~~~~Key Presses~~~~~~~~~~*/

    public void leftPressed(){
        keys.put(Keys.Left, true);
    }

    public void rightPressed(){
        keys.put(Keys.Right, true);
    }

    public void upPressed(){
        keys.put(Keys.Up, true);
    }

    public void downPressed(){
        keys.put(Keys.Down, true);
    }

    public void leftReleased(){
        keys.put(Keys.Left, false);
    }

    public void rightReleased(){
        keys.put(Keys.Right, false);
    }

    public void upReleased(){
        keys.put(Keys.Up, false);
    }

    public void downReleased(){
        keys.put(Keys.Down, false);
    }
}
