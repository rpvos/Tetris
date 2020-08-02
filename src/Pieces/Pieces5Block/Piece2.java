package Pieces.Pieces5Block;

import Field.Field;
import Pieces.General.Piece;
import Pieces.General.Square;

import java.awt.*;
import java.awt.geom.Point2D;
import java.util.ArrayList;

public class Piece2 extends Piece {
    private final static Color COLOR = Color.BLUE;
    private static final int pieceNumber = 2;


    public Piece2(Field field, int offset) {
        super(new Square(new Point2D.Double(offset - 2, 0), COLOR,pieceNumber), new Square(new Point2D.Double(offset - 1, 0), COLOR,pieceNumber), new Square(new Point2D.Double(offset, 0), COLOR,pieceNumber), new Square(new Point2D.Double(offset + 1, 0), COLOR,pieceNumber), new Square(new Point2D.Double(offset + 2, 0), COLOR,pieceNumber), field);
    }

    @Override
    public void rotate() {
        ArrayList<Point2D> points = new ArrayList<>();
        switch (rotations) {
            case 0:
                points.add(new Point2D.Double(pos1.getPos().getX() + 2, pos1.getPos().getY() - 2));
                points.add(new Point2D.Double(pos2.getPos().getX() + 1, pos2.getPos().getY() - 1));
                points.add(new Point2D.Double(pos4.getPos().getX() - 1, pos4.getPos().getY() + 1));
                points.add(new Point2D.Double(pos5.getPos().getX() - 2, pos5.getPos().getY() + 2));
                break;
            case 1:
                points.add(new Point2D.Double(pos1.getPos().getX() - 2, pos1.getPos().getY() + 2));
                points.add(new Point2D.Double(pos2.getPos().getX() - 1, pos2.getPos().getY() + 1));
                points.add( new Point2D.Double(pos4.getPos().getX() + 1, pos4.getPos().getY() - 1));
                points.add( new Point2D.Double(pos5.getPos().getX() + 2, pos5.getPos().getY() - 2));
                break;
        }
        points.add( new Point2D.Double(pos3.getPos().getX(), pos3.getPos().getY()));
        if (check(points)) {
            switch (rotations) {
                case 0:
                    pos1.offset(2,-2);
                    pos2.offset(1,-1);
                    pos4.offset(-1,1);
                    pos5.offset(-2,2);
                    break;
                case 1:
                    pos1.offset(-2,2);
                    pos2.offset(-1,1);
                    pos4.offset(1,-1);
                    pos5.offset(2,-2);
                    break;
            }
            rotations++;
            if (rotations == 2)
                rotations = 0;

        }
    }
}
