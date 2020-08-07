package Pieces;

import Field.Field;

import java.awt.*;
import java.awt.geom.Point2D;
import java.util.ArrayList;

public class Piece3 extends Piece {
    private final static Color COLOR = Color.GREEN;

    public Piece3(Field field, int offset) {
        super(new Square(new Point2D.Double(offset - 1, 0), COLOR), new Square(new Point2D.Double(offset, 0), COLOR), new Square(new Point2D.Double(offset, 1), COLOR), new Square(new Point2D.Double(offset, 2), COLOR), new Square(new Point2D.Double(offset, 3), COLOR), field);
    }

    @Override
    public void rotate() {
        ArrayList<Point2D> points = new ArrayList<>();
        switch (rotations) {
            case 0:
                points.add(createPoint(pos1,2,1));
                points.add(createPoint(pos2,1,2));
                points.add(createPoint(pos3,0,1));
                points.add(createPoint(pos4,-1,0));
                points.add(createPoint(pos5,-2,-1));
                break;
            case 1:
                points.add(createPoint(pos1,-1,2));
                points.add(createPoint(pos2,-2,1));
                points.add(createPoint(pos3,-1,0));
                points.add(createPoint(pos4,0,-1));
                points.add(createPoint(pos5,1,-2));
                break;
            case 2:
                points.add(createPoint(pos1,-2,-1));
                points.add(createPoint(pos2,-1,-2));
                points.add(createPoint(pos3,0,-1));
                points.add(createPoint(pos4,1,0));
                points.add(createPoint(pos5,2,-1));
                break;
            case 3:
                points.add(createPoint(pos1,1,-2));
                points.add(createPoint(pos2,2,-1));
                points.add(createPoint(pos3,1,0));
                points.add(createPoint(pos4,0,1));
                points.add(createPoint(pos5,-1,2));
                break;
        }
        if (check(points)) {
            switch (rotations) {
                case 0:
                    pos1.offset(2, 1);
                    pos2.offset(1, 2);
                    pos3.offset(0, 1);
                    pos4.offset(-1, 0);
                    pos5.offset(-2, -1);
                    break;
                case 1:
                    pos1.offset(-1, 2);
                    pos2.offset(-2, 1);
                    pos3.offset(-1, 0);
                    pos4.offset(0, -1);
                    pos5.offset(1, -2);
                    break;
                case 2:
                    pos1.offset(-2, -1);
                    pos2.offset(-1, -2);
                    pos3.offset(0, -1);
                    pos4.offset(1, 0);
                    pos5.offset(2, 1);
                    break;
                case 3:
                    pos1.offset(1, -2);
                    pos2.offset(2, -1);
                    pos3.offset(1, 0);
                    pos4.offset(0, 1);
                    pos5.offset(-1, 2);
                    break;
            }
            rotations++;
            if (rotations == 4)
                rotations = 0;

        }
    }


}
