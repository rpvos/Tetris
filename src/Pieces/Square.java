package Pieces;

import java.awt.*;
import java.awt.geom.Point2D;

public class Square {
    private Point2D pos;
    private Color color;

    public Square(Point2D pos, Color color) {
        this.pos = pos;
        this.color = color;
    }

    public Square(int x , int y, Color color) {
        this.pos = new Point2D.Double(x,y);
        this.color = color;
    }

    public Point2D getPos() {
        return pos;
    }

    public Color getColor() {
        return color;
    }

    public void setPos(Point2D pos) {
        this.pos = pos;
    }

    public void moveDown() {
        pos = new Point2D.Double(pos.getX(), pos.getY() + 1);
    }

    public void moveLeft() {
        pos = new Point2D.Double(pos.getX() - 1, pos.getY());
    }

    public void moveRight() {
        pos = new Point2D.Double(pos.getX() + 1, pos.getY());
    }

    public void offset(int x, int y) {
        pos.setLocation(pos.getX() + x, pos.getY() + y);
    }

    @Override
    public String toString() {
        return "Square{" +
                "pos=" + pos +
                '}';
    }
}
