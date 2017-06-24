package com.yarus.game;

import com.badlogic.gdx.math.Rectangle;

import static com.yarus.game.Constants.CELL_SIZE;

/**
 * Created by YARUS
 */

public class Cell {
    private Rectangle rec;
    private int state;

    public Cell (int x, int y, int state){
        rec.x = x;
        rec.y = y;
        rec.width = x + CELL_SIZE;
        rec.height = y + CELL_SIZE;
        this.state = state;
    }

    public Rectangle getRect(){
        return rec;
    }

    public void setRect(Rectangle rectangle){
        rec = rectangle;
    }

    public int getState(){
        return this.state;
    }

    public void setState(int state){
        this.state = state;
    }
}
