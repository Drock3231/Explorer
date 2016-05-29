package com.detell.explorer.Controllers;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.detell.explorer.Models.Blocks.Block;
import com.detell.explorer.Models.Blocks.BlockRock;
import com.detell.explorer.Models.Blocks.BlockSand;
import com.detell.explorer.Models.Chunks;
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

                Block[][] blocks = new Block[Math.round(Chunks.getSize().x)][Math.round(Chunks.getSize().y)];

                fileName = ("map/chunk" + a + "_" + b + ".txt");

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
                            case '0': blocks[x][y] = new BlockSand(Math.round(x + Chunks.getSize().x * a), Math.round(y + Chunks.getSize().y * b));
                                      stringIndex++;
                                      break;

                            case '1': blocks[x][y] = new BlockRock(Math.round(x + Chunks.getSize().x * a), Math.round(y + Chunks.getSize().y * b));
                                      stringIndex++;
                                      break;
                            case '2': blocks[x][y] = new BlockRock(Math.round(x + Chunks.getSize().x * a), Math.round(y + Chunks.getSize().y * b));
                                      stringIndex++;
                                      break;

                        }




                    }
                }

                map.addChunks(blocks, a, b);

            }
        }



        return map;
    }
}
