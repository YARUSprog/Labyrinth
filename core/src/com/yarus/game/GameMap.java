package com.yarus.game;

import com.badlogic.gdx.math.Rectangle;

import static com.yarus.game.Constants.*;

/**
 * Created by YARUS
 */

public class GameMap {
    private static Cell[][] cells;

    public GameMap(){
        cells = new Cell[CELLS_COUNT_X][CELLS_COUNT_Y];
        for(int i=0; i<CELLS_COUNT_X; i++){
            for(int j=0; j<CELLS_COUNT_Y; j++){
                cells[i][j] = new Cell(i*CELL_SIZE, j*CELL_SIZE, 0);
            }
        }
    }
}
