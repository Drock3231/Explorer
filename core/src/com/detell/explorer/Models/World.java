package com.detell.explorer.Models;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.Vector2;

import java.util.ArrayList;

/**
 * Created by Derick on 5/10/2016.
 */
public class World {

    private ArrayList<Chunks> map;

    private Player player;

    private OrthographicCamera camera;
    private final static Vector2 VIEWPORT = new Vector2(20,12);

    public World(){

        map = new ArrayList<>();

        camera = new OrthographicCamera();
    }

    public void addChunks(Chunks chunk){
        map.add(chunk);
    }

    public ArrayList<Chunks> getChunks(){
        return map;
    }

    public Player getPlayer(){
        return player;
    }

}
