package Field;

import Pieces.*;
import Utils.KeyListener;
import Utils.ScoreCallBack;
import Utils.Timer;
import org.jfree.fx.FXGraphics2D;

import java.awt.*;
import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

public class Field {
    private static final int WIDTH = 10;
    private static final int HEIGHT = 20;
    private static final int SIZE = 32;
    private static final int AMOUNT_OF_PIECES = 6;
    private final KeyListener keylistener;
    private final ScoreCallBack scoreCallback;
    private Random random;

    private Timer speedTimer;

    private boolean aActive;
    private boolean sActive;
    private boolean dActive;
    private boolean rotateActive;

    private Square[][] field;
    private Piece current;

    public Field(KeyListener keyListener, ScoreCallBack scoreCallBack) {
        this.field = new Square[10][40];
        this.speedTimer = new Timer(1000);
        this.keylistener = keyListener;
        this.scoreCallback = scoreCallBack;
        this.rotateActive = false;
        this.aActive = false;
        this.sActive = false;
        this.dActive = false;
        this.random = new Random();
        clearField();
    }

    private void clearField() {
        for (int x = 0; x < WIDTH; x++) {
            for (int y = 0; y < HEIGHT; y++) {
                this.field[x][y] = null;
            }
        }
    }

    public void draw(FXGraphics2D graphics) {
        for (int x = 0; x < WIDTH; x++) {
            for (int y = 0; y < HEIGHT; y++) {
                if (field[x][y] != null) {
//                    field[x][y].draw(graphics, SIZE);todo here it goes wrong
                    graphics.setColor(field[x][y].getColor());
                    graphics.fillRect(x * SIZE, y * SIZE, SIZE, SIZE);
                } else {
                    graphics.setColor(Color.white);
                    graphics.drawRect(x * SIZE, y * SIZE, SIZE, SIZE);
                }
            }
        }

        if (current != null) {
            current.draw(graphics, SIZE);
//            graphics.setColor(current.getPos1().getColor());
//            graphics.fillRect((int) current.getPos1().getPos().getX() * SIZE, (int) current.getPos1().getPos().getY() * SIZE, SIZE, SIZE);
//            graphics.fillRect((int) current.getPos2().getPos().getX() * SIZE, (int) current.getPos2().getPos().getY() * SIZE, SIZE, SIZE);
//            graphics.fillRect((int) current.getPos3().getPos().getX() * SIZE, (int) current.getPos3().getPos().getY() * SIZE, SIZE, SIZE);
//            graphics.fillRect((int) current.getPos4().getPos().getX() * SIZE, (int) current.getPos4().getPos().getY() * SIZE, SIZE, SIZE);
//            graphics.fillRect((int) current.getPos5().getPos().getX() * SIZE, (int) current.getPos5().getPos().getY() * SIZE, SIZE, SIZE);
        }
    }


    public void update(double deltatime) {
        if (current == null)
            current = getPiece();

        if (speedTimer.timeout()) {
            checkLock();
            speedTimer.mark();
        }

        if (current != null) {
            if (keylistener.getInputs().
                    contains("A")) {
                if (!aActive)
                    current.moveLeft();
                aActive = true;
            } else
                aActive = false;

            //todo make it go down
            if (keylistener.getInputs().
                    contains("S")) {
                if (!sActive)
                    current.moveDown();
                sActive = true;
            } else
                sActive = false;

            if (keylistener.getInputs().
                    contains("D")) {
                if (!dActive)
                    current.moveRight();
                dActive = true;
            } else
                dActive = false;


            if (keylistener.getInputs().
                    contains("R")) {
                if (!rotateActive)
                    current.rotate();
                rotateActive = true;
            } else
                rotateActive = false;
        }
    }

    private Piece getPiece() {
        int random = this.random.nextInt(AMOUNT_OF_PIECES) + 1;
        switch (random) {
//        switch (9) {
            case 1:
                return new Piece1(this, WIDTH / 2);
            case 2:
                return new Piece2(this, WIDTH / 2);
            case 3:
                return new Piece3(this, WIDTH / 2);
            case 4:
                return new Piece4(this, WIDTH / 2);
            case 5:
                return new Piece5(this, WIDTH / 2);
            case 6:
                return new Piece6(this, WIDTH / 2);
            case 7:
                return new Piece7(this, WIDTH / 2);
            case 8:
                return new Piece8(this, WIDTH / 2);
            case 9:
                return new Piece9(this, WIDTH / 2);
            default:
                System.out.println("default piece");
                return new Piece1(this, WIDTH / 2);
        }
    }

    private void lockIn(Square pos) {
        field[(int) pos.getPos().getX()][(int) pos.getPos().getY()] = pos;
    }

    //todo make a way to die
    private void checkLock() {
        if (!current.moveDown()) {
            lockIn(current.getPos1());
            lockIn(current.getPos2());
            lockIn(current.getPos3());
            lockIn(current.getPos4());
            lockIn(current.getPos5());
            scoreCallback.scoreUpdate(1);

            //check if a row is filled
            for (int y = 0; y < HEIGHT; y++) {
                boolean rowFilled = true;
                for (int x = 0; x < WIDTH; x++) {
                    if (field[x][y] == null)
                        rowFilled = false;
                }

                if (rowFilled) {
                    removeRow(y);
                    scoreCallback.scoreUpdate(10);
                }
            }

            current = null;
        }
    }

    private void removeRow(int y) {
        for (int x = 0; x < WIDTH; x++) {
            field[x][y] = null;
        }

        //move all the other rows down
        while (y > 0) {
            //move row
            for (int x = 0; x < WIDTH; x++) {
                field[x][y] = field[x][y - 1];
            }
            //delete old location row
            for (int x = 0; x < WIDTH; x++) {
                field[x][y - 1] = null;
            }
            //move one row up
            y--;
        }
    }

    public boolean check(ArrayList<Point2D> positions) {
        for (Point2D pos : positions) {
            int x = (int) pos.getX();
            int y = (int) pos.getY();
            if (x < 0 ||
                    x >= WIDTH)
                return false;


            if (y >= HEIGHT ||
                    y < 0)
                return false;

            if (field[x][y] != null) {
                return false;
            }
        }
        return true;
    }

}
