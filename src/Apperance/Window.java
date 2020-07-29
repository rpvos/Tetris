package Apperance;

import Field.Field;
import Utils.KeyListener;
import Utils.ScoreCallBack;
import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.Label;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import org.jfree.fx.FXGraphics2D;



public class Window extends Application implements ScoreCallBack {
    private Canvas canvas;
    private Field field;
    private Label scoreLabel;
    private int score;
    private Label highscore;

    @Override
    public void start(Stage primaryStage) throws Exception {
        BorderPane root = new BorderPane();
        root.setBackground(new Background(new BackgroundFill(new Color(0,0,0.1,1), new CornerRadii(0), new Insets(0,0,0,0))));
        VBox infoPane = new VBox();
        infoPane.setPadding(new Insets(5,10,5,10));
        infoPane.setSpacing(5);
        infoPane.setBackground(new Background(new BackgroundFill(new Color(1,1,1,0.8), new CornerRadii(1), new Insets(0,0,0,0))));
        this.canvas = new Canvas(320, 636);
        this.score = 0;

        Label nextPieceText = new Label("Next piece: ");
        infoPane.getChildren().add(nextPieceText);
        //todo make a way to show the next piece

        Label scoreText = new Label("Score: ");
        infoPane.getChildren().add(scoreText);
        this.scoreLabel = new Label(score+"");
        infoPane.getChildren().add(scoreLabel);

        Label highscoreText = new Label("Highscore: ");
        infoPane.getChildren().add(highscoreText);
        this.highscore = new Label(highscore+"");
        infoPane.getChildren().add(highscore);

        updateLabel();

        root.setCenter(canvas);
        root.setRight(infoPane);
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
        graphics.setBackground(Colors.GRAY);
        graphics.clearRect(0, 0, (int) canvas.getWidth(), (int) canvas.getHeight());

        field.draw(graphics);
    }

    @Override
    public void scoreUpdate(int score) {
        this.score += score;
        updateLabel();
    }

    private void updateLabel() {
        this.scoreLabel.setText(this.score+"");
    }
}
