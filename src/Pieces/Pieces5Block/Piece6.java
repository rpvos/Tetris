package Pieces.Pieces5Block;

import Apperance.Colors;
import Field.Field;
import Pieces.General.Piece;
import Pieces.General.Square;

import java.awt.*;
import java.awt.geom.Point2D;
import java.util.ArrayList;

public class Piece6 extends Piece {
    private final static Color COLOR = Colors.PURPLE;
    private static final int pieceNumber = 6;


    public Piece6(Field field, int offset) {
        super(new Square(offset,2,COLOR,pieceNumber),new Square(offset,1,COLOR,pieceNumber),new Square(offset,0,COLOR,pieceNumber),new Square(offset-1,0,COLOR,pieceNumber),new Square(offset-1,1,COLOR,pieceNumber),field);
    }

    @Override
    public void rotate() {
        ArrayList<Point2D> points = new ArrayList<>();
        switch (rotations) {
            case 0:
                points.add(createPoint(pos1, -2,-1));
                break;
            case 1:
                points.add(createPoint(pos1, 1,-2));
                break;
            case 2:
                points.add(createPoint(pos1,2,1 ));
                break;
            case 3:
                points.add(createPoint(pos1, -1,2));
                break;

        }
        if (check(points)) {
            switch (rotations) {
                case 0:
                    pos1.offset(-2,-1);
                    break;
                case 1:
                    pos1.offset(1, -2);
                    break;
                case 2:
                    pos1.offset(2, 1);
                    break;
                case 3:
                    pos1.offset(-1, 2);
                    break;
            }
            rotations++;
            if (rotations == 4)
                rotations = 0;

        }
    }


}
