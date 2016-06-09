package com.detell.explorer.Controllers;

import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.Pool;
import com.detell.explorer.Models.Blocks.Block;
import com.detell.explorer.Models.Chunks;
import com.detell.explorer.Models.Player;
import com.detell.explorer.Models.World;

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
    private enum Keys{
        Left,Right,Up,Down
    }

    private static Map<Keys,Boolean> keys = new HashMap<>();
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



    //These are all the values the game uses to calculate acceleration
    //given in real-world values in m/s (blocks/sec)
    private static final float ACCELERATION = 10f;
    private static final float MAX_VEL = 4f;

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

        //scales acceleration and adds it to velocity
        player.getAcceleration().scl(delta);
        player.getVelocity().add(player.getAcceleration());

        //makes sure players velocity does not exceed max
        if(player.getVelocity().x > MAX_VEL) player.getVelocity().x = MAX_VEL;
        if(player.getVelocity().x < -MAX_VEL) player.getVelocity().x = -MAX_VEL;

        if(player.getVelocity().y > MAX_VEL) player.getVelocity().y = MAX_VEL;
        if(player.getVelocity().y < -MAX_VEL) player.getVelocity().y = -MAX_VEL;

        checkBlockCollision(delta);

        //moves player and unscales velocity
        player.getPosition().add(player.getVelocity());
        player.getBounds().x = player.getPosition().x;
        player.getBounds().y = player.getPosition().y;
        player.getVelocity().scl(1/delta);
    }

    private void checkBlockCollision(float delta){

        //scales velocity
        player.getVelocity().scl(delta);

        //grabs Rectangle to represent player's "hurtbox"
        Rectangle playerRectangle = rectPool.obtain();
        playerRectangle.set(player.getBounds());

        //find chunk player is in
        int mapX = (int)Math.floor(player.getPosition().x/Chunks.getSize().x);
        int mapY = (int)Math.floor(player.getPosition().y/Chunks.getSize().y);
        Chunks playerChunk = world.getMap().getChunks()[mapX][mapY];

        //this array holds the blocks that the player can collide with
        Array<Block> collidableBlocks;

        /*find blocks that are near player (currently the 8 blocks player is near and the one
        he is in)
        */
        collidableBlocks = getCollidableBlocks(playerChunk, mapX, mapY);

        /*checks if player is in blocks and if they are solid, stops him from moving through
        if they are
         */
        for (Block block : collidableBlocks){
            if (playerRectangle.overlaps(block.getBounds()) && block.isSolid() == true){
                player.getPosition().add(player.getVelocity().scl(-1));
                player.getVelocity().set(0,0);
                player.getAcceleration().set(0,0);
                player.setHitstunTime(System.currentTimeMillis());

            }
        }

    }

    /*find blocks that are near player (currently the 8 blocks player is near and the one
    he is in)
    */
    public Array<Block> getCollidableBlocks(Chunks chunk, int mapX, int mapY) {

        //creates a place to put the collide-able blocks
        Array<Block> blocks = new Array<>();

        //sets up bounds in the for statement
        int startX = (int)player.getPosition().x -1 - (int)(mapX * Chunks.getSize().x);
        int endX = (int)player.getPosition().x + 1 - (int)(mapX * Chunks.getSize().x);
        int startY = (int)player.getPosition().y -1 - (int)(mapY * Chunks.getSize().y);
        int endY = (int)player.getPosition().y + 1 - (int)(mapY * Chunks.getSize().y);

        /*
        Pulls 8 blocks around player, is the blocks he is eligible for colliding with are in another chunk, pull up
        that chunk.
         */
        for(int x = startX; x <= endX; x++ ){
            for(int y = startY; y <= endY; y++){
                try {
                     blocks.add(chunk.getBlocks()[x][y]);

                /*this entire catch statement only deals with the situation for when the block needed is in a chunk the
                player is not in
                */
                }catch(ArrayIndexOutOfBoundsException e){

                    if(startX < 0){
                        Chunks secondChunk = world.getMap().getChunks()[mapX + (int)Math.ceil(startX/Chunks.getSize().x)][mapY];
                        for(int x2 = (int)Chunks.getSize().x - endX; x2 <= (int)Chunks.getSize().x - endX; x2++ ){
                            for(int y2 = startY; y2 <= endY; y2++){
                                blocks.add(secondChunk.getBlocks()[x2][y2]);
                            }
                        }
                    }else if(startY < 0){
                        Chunks secondChunk = world.getMap().getChunks()[mapX][mapY + (int)Math.ceil(startY/Chunks.getSize().y)];
                        for(int x2 = startX; x2 <= endX; x2++ ){
                            for(int y2 = (int)Chunks.getSize().y - endY; y2 <= (int)Chunks.getSize().y - endY; y2++) {
                                blocks.add(secondChunk.getBlocks()[x2][y2]);
                            }
                        }
                    }

                    if(endX > Chunks.getSize().x - 1){

                        Chunks secondChunk = world.getMap().getChunks()[mapX + (int)Math.ceil(endX/Chunks.getSize().x)][mapY];
                        for(int x2 = (int)Chunks.getSize().x - endX; x2 <= (int)Chunks.getSize().x - endX; x2++ ){
                            for(int y2 = startY; y2 <= endY; y2++){
                                blocks.add(secondChunk.getBlocks()[x2][y2]);
                            }
                        }

                    }else if(endY > Chunks.getSize().y - 1){

                        Chunks secondChunk = world.getMap().getChunks()[mapX][mapY + (int)Math.ceil(endY/Chunks.getSize().y)];
                        for(int x2 = startX; x2 <= endX; x2++ ){
                            for(int y2 = (int)Chunks.getSize().y - endY; y2 <= (int)Chunks.getSize().y - endY; y2++) {
                                blocks.add(secondChunk.getBlocks()[x2][y2]);
                            }
                        }
                    }

                }catch(IndexOutOfBoundsException e){}
            }
        }

        return blocks;
    }

    private void handleInput(){

        boolean isMoveable;
        if(player.getHitstunTime() <= 0){
            isMoveable = true;
        } else isMoveable = false;


        if(isMoveable) {
            if (keys.get(Keys.Left)) {
                player.getAcceleration().x = -ACCELERATION;
            } else if (keys.get(Keys.Right)) {
                player.getAcceleration().x = ACCELERATION;
            } else if (keys.get(Keys.Up)) {
                player.getAcceleration().y = ACCELERATION;
            } else if (keys.get(Keys.Down)) {
                player.getAcceleration().y = -ACCELERATION;
            } else {
                player.getAcceleration().x = 0;
                player.getVelocity().x = 0;

                player.getAcceleration().y = 0;
                player.getVelocity().y = 0;
            }
        }else if(System.currentTimeMillis() - player.getHitstunTime() >= Block.getHITSTUN()){
            player.setHitstunTime(0);
        }

        if(!isMoveable){
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
