package ca.georgebrown.game2011.mymathgame;

/**
 * Created by Tech on 2018-02-13.
 */

public class Rectangle {
    private Point topLeft;
    private Point bottomRight;

    public Rectangle(Point paramTopLeft, Point paramLowRight) {
        topLeft = paramTopLeft;
        bottomRight = paramLowRight;
    }

    public Point getTopLeft() {
        return topLeft;
    }

    public Point getBottomRight() {
        return bottomRight;
    }

    public void translate(Point newTopLeft) {
        int xOffset = newTopLeft.getX() - topLeft.getX();
        int yOffset = newTopLeft.getY() - topLeft.getY();

        topLeft = newTopLeft;
        int newBottomPosX = bottomRight.getX()+xOffset;
        int newBottomPosY = bottomRight.getY()+yOffset;
        bottomRight = new Point(newBottomPosX, newBottomPosY);
    }
}
