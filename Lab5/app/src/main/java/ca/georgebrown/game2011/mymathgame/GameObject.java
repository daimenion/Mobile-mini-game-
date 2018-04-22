package ca.georgebrown.game2011.mymathgame;

import android.graphics.Rect;

/**
 * Created by user on 4/21/2018.
 */

public abstract class GameObject {
    protected int x;
    protected int y;
    protected int width;
    protected int height;

    public void setX(int x){this.x = x;}
    public void setY(int y) {this.y = y;}
    public int getX(){return x;}
    public int getY(){return y;}
    public void setWidth(int width){this.width = width;}
    public void setHeight(int height) {this.height = height;}
    public int getWidth() {return width;}
    public int getHeight(){return height;}
    public Rect getRectangle(){return new Rect(x,y,x+width,y+height);}
}
