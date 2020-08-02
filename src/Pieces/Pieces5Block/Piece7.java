package Pieces.Pieces5Block;

import Field.Field;
import Pieces.General.Piece;
import Pieces.General.Square;

import java.awt.*;
import java.awt.geom.Point2D;
import java.util.ArrayList;

public class Piece7 extends Piece {
    private final static Color COLOR = Color.PINK;
    private static final int pieceNumber = 7;


    public Piece7(Field field, int offset) {
        super(new Square(offset - 1, 0, COLOR,pieceNumber), new Square(offset, 0, COLOR,pieceNumber), new Square(offset, 1, COLOR,pieceNumber), new Square(offset, 2, COLOR,pieceNumber), new Square(offset + 1, 2, COLOR,pieceNumber), field);
    }

    @Override
    public void rotate() {
        ArrayList<Point2D> points = new ArrayList<>();
        switch (rotations) {
            case 0:
                points.add(createPoint(pos1, 2, 0));
                points.add(createPoint(pos2, 1, 1));
                points.add(createPoint(pos3, 0, 0));
                points.add(createPoint(pos4, -1, -1));
                points.add(createPoint(pos5, -2, 0));
                break;
            case 1:
                points.add(createPoint(pos1, -2, 0));
                points.add(createPoint(pos2, -1, -1));
                points.add(createPoint(pos3, 0, 0));
                points.add(createPoint(pos4, 1, 1));
                points.add(createPoint(pos5, 2, 0));
                break;

        }
        if (check(points)) {
            switch (rotations) {
                case 0:
                    pos1.offset(2, 0);
                    pos2.offset(1, 1);
                    pos3.offset(0, 0);
                    pos4.offset(-1, -1);
                    pos5.offset(-2, 0);
                    break;
                case 1:
                    pos1.offset(-2, 0);
                    pos2.offset(-1, -1);
                    pos3.offset(0, 0);
                    pos4.offset(1,1);
                    pos5.offset(2, 0);
                    break;

            }
            rotations++;
            if (rotations == 2)
                rotations = 0;

        }
    }


}
