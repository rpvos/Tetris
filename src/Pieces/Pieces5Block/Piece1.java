package Pieces.Pieces5Block;

import Field.Field;
import Pieces.General.Piece;
import Pieces.General.Square;

import java.awt.*;
import java.awt.geom.Point2D;
import java.util.ArrayList;

public class Piece1 extends Piece {
    private final static Color COLOR = Color.RED;
    private static final int pieceNumber = 1;


    public Piece1(Field field, int offset) {
        super(new Square(new Point2D.Double(offset, 0), COLOR,pieceNumber), new Square(new Point2D.Double(offset, 1), COLOR,pieceNumber), new Square(new Point2D.Double(offset, 2), COLOR,pieceNumber), new Square(new Point2D.Double(offset + 1, 0), COLOR,pieceNumber), new Square(new Point2D.Double(offset + 1, 1), COLOR,pieceNumber), field);
    }

    @Override
    public void rotate() {
        ArrayList<Point2D> points = new ArrayList<>();
        points.add(new Point2D.Double(pos1.getPos().getX(), pos1.getPos().getY()));
        points.add(new Point2D.Double(pos2.getPos().getX(), pos2.getPos().getY()));
        switch (rotations) {
            case 0:
                points.add(new Point2D.Double(pos3.getPos().getX() - 1, pos3.getPos().getY() - 2));
                break;
            case 1:
                points.add(new Point2D.Double(pos3.getPos().getX() + 2, pos3.getPos().getY() - 1));
                break;
            case 2:
                points.add(new Point2D.Double(pos3.getPos().getX() + 1, pos3.getPos().getY() + 2));
                break;
            case 3:
                points.add(new Point2D.Double(pos3.getPos().getX() - 2, pos3.getPos().getY() + 1));
                break;
        }
        points.add(pos4.getPos());
        points.add(pos5.getPos());
        if (check(points)) {
            switch (rotations) {
                case 0:
                    pos3.offset(-1, -2);
                    break;
                case 1:
                    pos3.offset(2, -1);
                    break;
                case 2:
                    pos3.offset(1, 2);
                    break;
                case 3:
                    pos3.offset(-2, 1);
                    break;
            }
            rotations++;
            if (rotations == 4)
                rotations = 0;
        }
    }
}
