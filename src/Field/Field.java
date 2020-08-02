package Field;

import Pieces.General.Piece;
import Pieces.General.Square;
import Pieces.Pieces5Block.*;
import Utils.KeyListener;
import Utils.ScoreCallBack;
import Utils.Timer;
import org.jfree.fx.FXGraphics2D;

import java.awt.*;
import java.awt.geom.Point2D;
import java.io.File;
import java.util.ArrayList;
import java.util.Random;

public class Field {
    private static final int WIDTH = 10;
    private static final int HEIGHT = 20;
    private static final int SIZE = 32;
    private final int AMOUNT_OF_PIECES;
    private final KeyListener keylistener;
    private final ScoreCallBack scoreCallback;
    private Random random;

    private Timer speedTimer;

    private Timer aPressedTimer;
    private Timer sPressedTimer;
    private Timer dPressedTimer;
    private boolean rotateActive;

    private Square[][] field;
    private Piece nextPiece;
    private Piece currentPiece;
    private boolean isAlive;

    public Field(KeyListener keyListener, ScoreCallBack scoreCallBack) {
        this.AMOUNT_OF_PIECES = new File("src/Pieces/Pieces5Block").listFiles().length;

        this.field = new Square[10][40];
        this.speedTimer = new Timer(1000);
        this.keylistener = keyListener;
        this.scoreCallback = scoreCallBack;
        this.rotateActive = false;
        this.aPressedTimer = new Timer(300);
        this.dPressedTimer = new Timer(300);
        this.sPressedTimer = new Timer(200);
        this.random = new Random();
        this.isAlive = true;
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
                    field[x][y].draw(graphics, SIZE);
//                    graphics.setColor(field[x][y].getColor());
//                    graphics.fillRect(x * SIZE, y * SIZE, SIZE, SIZE);
                } else {
                    graphics.setColor(Color.white);
                    graphics.drawRect(x * SIZE, y * SIZE, SIZE, SIZE);
                }
            }
        }

        if (currentPiece != null) {
            currentPiece.draw(graphics, SIZE);
//            graphics.setColor(currentPiece.getPos1().getColor());
//            graphics.fillRect((int) currentPiece.getPos1().getPos().getX() * SIZE, (int) currentPiece.getPos1().getPos().getY() * SIZE, SIZE, SIZE);
//            graphics.fillRect((int) currentPiece.getPos2().getPos().getX() * SIZE, (int) currentPiece.getPos2().getPos().getY() * SIZE, SIZE, SIZE);
//            graphics.fillRect((int) currentPiece.getPos3().getPos().getX() * SIZE, (int) currentPiece.getPos3().getPos().getY() * SIZE, SIZE, SIZE);
//            graphics.fillRect((int) currentPiece.getPos4().getPos().getX() * SIZE, (int) currentPiece.getPos4().getPos().getY() * SIZE, SIZE, SIZE);
//            graphics.fillRect((int) currentPiece.getPos5().getPos().getX() * SIZE, (int) currentPiece.getPos5().getPos().getY() * SIZE, SIZE, SIZE);
        }
    }


    public void update(double deltatime) {
        if (!isAlive)
            return;

        if (currentPiece == null)
            currentPiece = getPiece();

        if (speedTimer.timeout()) {
            checkLock();
            speedTimer.mark();
        }

        if (currentPiece != null) {
            if (keylistener.getInputs().
                    contains("A")) {
                if (aPressedTimer.timeout()) {
                    currentPiece.moveLeft();
                    aPressedTimer.mark();
                }
            } else
                aPressedTimer.setTimeout();


            if (keylistener.getInputs().
                    contains("S")) {
                if (sPressedTimer.timeout()) {
                    currentPiece.moveDown();
                    sPressedTimer.mark();
                }
            } else
                sPressedTimer.setTimeout();


            if (keylistener.getInputs().
                    contains("D")) {
                if (dPressedTimer.timeout()) {
                    currentPiece.moveRight();
                    dPressedTimer.mark();
                }
            } else
                dPressedTimer.setTimeout();


            if (keylistener.getInputs().
                    contains("R")) {
                if (!rotateActive)
                    currentPiece.rotate();
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
        if (!currentPiece.moveDown()) {
            lockIn(currentPiece.getPos1());
            lockIn(currentPiece.getPos2());
            lockIn(currentPiece.getPos3());
            lockIn(currentPiece.getPos4());
            lockIn(currentPiece.getPos5());
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

            for (int x = 0; x < WIDTH; x++) {
                if (field[x][1] != null) {
                    isAlive = false;
                }
            }

            currentPiece = null;
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
                if (field[x][y - 1] != null) {
                    field[x][y] = field[x][y - 1];
                    field[x][y].moveDown();
                    field[x][y - 1] = null;
                }
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
