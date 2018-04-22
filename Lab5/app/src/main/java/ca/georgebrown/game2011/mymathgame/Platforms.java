package ca.georgebrown.game2011.mymathgame;

import android.graphics.Bitmap;

import java.util.Random;

/**
 * Created by user on 4/21/2018.
 */

public class Platforms extends GameObject{

    private Bitmap sprite;
    private int speed = 2;

    public Platforms (Bitmap res, int xLocation,int yLocation)
    {
        sprite = res;
        super.setX(xLocation);
        super.setY(yLocation);
        super.setWidth(res.getWidth());
        super.setHeight(res.getHeight());
    }

    public Bitmap getSprite(){
        return sprite;
    }

    public void move()
    {
        super.x -= 5;
    }
}
