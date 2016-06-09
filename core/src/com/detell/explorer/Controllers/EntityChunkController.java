package com.detell.explorer.Controllers;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.detell.explorer.Models.Chunks;
import com.detell.explorer.Models.Entities.Entity;
import com.detell.explorer.Models.Entities.EntityTree;
import com.detell.explorer.Models.EntityMap;
import com.detell.explorer.Models.Map;

import java.util.ArrayList;

/**
 * Created by Derick on 6/9/2016.
 */
public class EntityChunkController {

    public EntityChunkController(){

    }

    public EntityMap generateEntityMap(){
        EntityMap entityMap = new EntityMap();

        String fileName;
        FileHandle handle;
        String text;
        int stringIndex;

        //adds chunks to map
        for(int a = 0; a < Map.getMapSize().x; a++){
            for(int b = 0; b < Map.getMapSize().y; b++){

                ArrayList<Entity> entities = new ArrayList<>();

                fileName = ("entityMap/entityChunk" + a + "_" + b + ".txt");

                handle = Gdx.files.internal(fileName);
                text = handle.readString();
                stringIndex = 0;

                //adds blocks to chunk
                for(int x = 0; x < Chunks.getSize().x; x++){
                    for(int y = 0; y < Chunks.getSize().y; y++){

                        if(text.charAt(stringIndex) == '\r') stringIndex++;
                        if(text.charAt(stringIndex) == '\n') stringIndex++;

                        char charAt = text.charAt(stringIndex);

                        switch(charAt){
                            case '0': stringIndex++;
                                break;

                            case '1': stringIndex++;
                                break;
                            case '2': entities.add(new EntityTree(Math.round(x + Chunks.getSize().x * a), Math.round(y + Chunks.getSize().y * b)));
                                stringIndex++;
                                break;

                        }




                    }
                }

                entityMap.addEntityChunks(entities, a, b);

            }
        }



        return entityMap;
    }

}
