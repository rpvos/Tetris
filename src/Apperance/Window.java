package Apperance;

import Field.Field;
import Utils.KeyListener;
import Utils.ScoreCallBack;
import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import org.jfree.fx.FXGraphics2D;

import java.awt.*;


public class Window extends Application implements ScoreCallBack {
    private Canvas canvas;
    private Field field;
    private Label label;
    private int score;

    @Override
    public void start(Stage primaryStage) throws Exception {
        Group root = new Group();
        this.canvas = new Canvas(320, 636);
        this.label = new Label("Score: ");
        this.score = 0;
        updateLabel();

        root.getChildren().add(canvas);
        root.getChildren().add(label);
        Scene scene = new Scene(root);
        FXGraphics2D graphics = new FXGraphics2D(canvas.getGraphicsContext2D());

        KeyListener keyListener = new KeyListener(scene);

        this.field = new Field(keyListener, this);

        new AnimationTimer() {
            long last = -1;

            @Override
            public void handle(long now) {
                if (last == -1) {
                    last = now;
                }
                update((now - last) / 1000000000.0);
                last = now;
                draw(graphics);

            }
        }.start();

        primaryStage.setTitle("5 piece Tetris - by Rik Vos");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private void update(double deltatime) {
        field.update(deltatime);
    }

    private void draw(FXGraphics2D graphics) {
        graphics.setBackground(Color.GRAY);
        graphics.clearRect(0, 0, (int) canvas.getWidth(), (int) canvas.getHeight());

        field.draw(graphics);
    }

    @Override
    public void scoreUpdate(int score) {
        this.score += score;
        updateLabel();
    }

    private void updateLabel() {
        String labelText = "Score: ";
        labelText += this.score;
        this.label.setText(labelText);
    }
}
