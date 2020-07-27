package Pieces;

import Apperance.SpriteSlicer;
import org.jfree.fx.FXGraphics2D;

import java.awt.*;
import java.awt.geom.Point2D;
import java.awt.image.BufferedImage;
import java.awt.image.ImageConsumer;
import java.awt.image.ImageObserver;

public class Square implements ImageObserver {
    private Point2D pos;
    private Color color;
    private BufferedImage image;

    public Square(Point2D pos, Color color, int gID) {
        this.pos = pos;
        this.color = color;
        this.image = SpriteSlicer.getInstance().getImage(gID);
    }

    public Square(int x, int y, Color color, int gID) {
        this.pos = new Point2D.Double(x, y);
        this.color = color;
        this.image = SpriteSlicer.getInstance().getImage(gID);
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

    public void draw(FXGraphics2D graphics, int SIZE) {
        if (image != null)
            graphics.drawImage(image, (int) pos.getX() * SIZE, (int) pos.getY() * SIZE, SIZE, SIZE, this);
        else {
            graphics.setColor(color);
            graphics.fillRect((int) pos.getX() * SIZE, (int) pos.getY() * SIZE, SIZE, SIZE);
        }
    }

    @Override
    public boolean imageUpdate(Image img, int infoflags, int x, int y, int width, int height) {
        System.out.println("here");
        return false;
    }
}
