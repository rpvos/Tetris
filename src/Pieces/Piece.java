package Pieces;

import Apperance.SpriteSlicer;
import Field.Field;
import org.jfree.fx.FXGraphics2D;

import java.awt.geom.Point2D;
import java.awt.image.BufferedImage;
import java.util.ArrayList;


public abstract class Piece {
    Square pos1;
    Square pos2;
    Square pos3;
    Square pos4;
    Square pos5;
    private Field field;
    int rotations;

    public Piece(Square pos1, Square pos2, Square pos3, Square pos4, Square pos5, Field field) {
        this.pos1 = pos1;
        this.pos2 = pos2;
        this.pos3 = pos3;
        this.pos4 = pos4;
        this.pos5 = pos5;
        this.field = field;
        this.rotations = 0;
    }

    public boolean check(ArrayList<Point2D> pos) {
        return field.check(pos);
    }

    public abstract void rotate();

    public boolean moveLeft() {
        ArrayList<Point2D> points = new ArrayList<>();
        points.add(new Point2D.Double(pos1.getPos().getX() - 1, pos1.getPos().getY()));
        points.add(new Point2D.Double(pos2.getPos().getX() - 1, pos2.getPos().getY()));
        points.add(new Point2D.Double(pos3.getPos().getX() - 1, pos3.getPos().getY()));
        points.add(new Point2D.Double(pos4.getPos().getX() - 1, pos4.getPos().getY()));
        points.add(new Point2D.Double(pos5.getPos().getX() - 1, pos5.getPos().getY()));

        if (field.check(points)) {
            pos1.moveLeft();
            pos2.moveLeft();
            pos3.moveLeft();
            pos4.moveLeft();
            pos5.moveLeft();
            return true;
        } else {
            return false;
        }
    }

    public boolean moveRight() {
        ArrayList<Point2D> points = new ArrayList<>();
        points.add(new Point2D.Double(pos1.getPos().getX() + 1, pos1.getPos().getY()));
        points.add(new Point2D.Double(pos2.getPos().getX() + 1, pos2.getPos().getY()));
        points.add(new Point2D.Double(pos3.getPos().getX() + 1, pos3.getPos().getY()));
        points.add(new Point2D.Double(pos4.getPos().getX() + 1, pos4.getPos().getY()));
        points.add(new Point2D.Double(pos5.getPos().getX() + 1, pos5.getPos().getY()));

        if (field.check(points)) {
            pos1.moveRight();
            pos2.moveRight();
            pos3.moveRight();
            pos4.moveRight();
            pos5.moveRight();
            return true;
        } else {
            return false;
        }
    }

    public boolean moveDown() {
        ArrayList<Point2D> points = new ArrayList<>();
        points.add(new Point2D.Double(pos1.getPos().getX(), pos1.getPos().getY() + 1));
        points.add(new Point2D.Double(pos2.getPos().getX(), pos2.getPos().getY() + 1));
        points.add(new Point2D.Double(pos3.getPos().getX(), pos3.getPos().getY() + 1));
        points.add(new Point2D.Double(pos4.getPos().getX(), pos4.getPos().getY() + 1));
        points.add(new Point2D.Double(pos5.getPos().getX(), pos5.getPos().getY() + 1));

        if (field.check(points)) {
            pos1.moveDown();
            pos2.moveDown();
            pos3.moveDown();
            pos4.moveDown();
            pos5.moveDown();
            return true;
        } else {
            return false;
        }
    }

    public Point2D createPoint(Square square, int xOffset, int yOffset) {
        return new Point2D.Double(square.getPos().getX() + xOffset, square.getPos().getY() + yOffset);
    }

    public Square getPos1() {
        return pos1;
    }

    public Square getPos2() {
        return pos2;
    }

    public Square getPos3() {
        return pos3;
    }

    public Square getPos4() {
        return pos4;
    }

    public Square getPos5() {
        return pos5;
    }

    public void draw(FXGraphics2D graphics, int SIZE) {
        pos1.draw(graphics, SIZE);
        pos2.draw(graphics, SIZE);
        pos3.draw(graphics, SIZE);
        pos4.draw(graphics, SIZE);
        pos5.draw(graphics, SIZE);
    }

}
