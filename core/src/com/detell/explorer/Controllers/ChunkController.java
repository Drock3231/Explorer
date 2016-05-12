package com.detell.explorer.Controllers;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.detell.explorer.Models.Blocks.Block;
import com.detell.explorer.Models.Blocks.BlockSand;
import com.detell.explorer.Models.Map;

/**
 * Created by Derick on 5/12/2016.
 */
public class ChunkController {

    public ChunkController(){

    }

    public Map generateMap(){
        Map map = new Map();

        String fileName;
        FileHandle handle;
        String text;
        int stringIndex;

        //adds chunks to map
        for(int a = 0; a < map.getMapSize().x; a++){
            for(int b = 0; b < map.getMapSize().y; b++){

                Block[][] blocks = new Block[Math.round(Block.getSize().x)][Math.round(Block.getSize().y)];

                fileName = ("data/chunk" + (a+b) + ".txt");

                handle = Gdx.files.internal(fileName);
                text = handle.readString();
                stringIndex = 0;

                //adds blocks to chunk
                for(int x = 0; x < Block.getSize().x; x++){
                    for(int y = 0; y < Block.getSize().y; y++){

                        char charAt = text.charAt(stringIndex);
                        switch(charAt){
                            case 1: charAt = '\r';
                                    stringIndex++;
                                    break;

                            case 2: charAt = '\n';
                                    stringIndex++;
                                    break;

                            case 3: charAt = '0';
                                    blocks[x][y] = new BlockSand(x,y);
                                    stringIndex++;
                                    break;

                            case 4: charAt = '1';
                                    blocks[x][y] = new BlockSand(x,y);
                                    stringIndex++;
                                    break;

                        }

                        map.addChunks(blocks, x, y);


                    }
                }

            }
        }



        return map;
    }
}
