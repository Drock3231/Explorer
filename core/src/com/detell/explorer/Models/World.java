package com.detell.explorer.Models;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.Vector2;
import com.detell.explorer.Models.Entities.Entity;

import java.util.ArrayList;

/**
 * Created by Derick on 5/10/2016.
 *
 * Class that contains all the game-world variables
 */
public class World {

    //game map
    private Map map;
    //
    private EntityMap entityMap;
    //player
    private Player player;
    //camera variables
    private OrthographicCamera camera;
    private final static Vector2 VIEWPORT = new Vector2(20,12);

    //initialize variables
    public World(){

        map = new Map();
        entityMap = new EntityMap();

        player = new Player(10,10);

        camera = new OrthographicCamera();
    }

    //methods to set/get variables
    public void addMap(Map map){
        this.map = map;
    }

    public Map getMap(){
        return map;
    }

    public void addEntityMap(EntityMap entityMap){
        this.entityMap = entityMap;
    }

    public EntityMap getEntityMap(){
        return entityMap;
    }

    public Player getPlayer(){
        return player;
    }

    public Vector2 getViewport(){
        return VIEWPORT;
    }

    public OrthographicCamera getCamera(){
        return camera;
    }

}
