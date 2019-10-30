package ru.javaschool.flamy;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import static ru.javaschool.flamy.Constant.*;

public class BallAndBlocks extends Application {


    private GraphicsContext gc;
    private DrawUI drawUI;
    private World world;
    private MyAnimationTimer myAnimationTimer;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        Group root = new Group();
        Scene scene = new Scene(root, WINDOW_WIDTH, WINDOW_HEIGHT, Color.BLACK);

        Canvas canvas = new Canvas(WINDOW_WIDTH, WINDOW_HEIGHT);
        gc = canvas.getGraphicsContext2D();

        world = new World();
        drawUI = new DrawUI(gc, world);

        canvas.setFocusTraversable(true);
        canvas.setOnKeyPressed(event -> {
            switch (event.getCode()) {
                case LEFT:
                    world.platformLeft();
                    break;
                case RIGHT:
                    world.platformRight();
                    break;
                case SPACE:
                    if (!world.gameEnd) {
                        if (!world.gameOn) world.startGame();
                    } else {
                        reset();
                        myAnimationTimer = new MyAnimationTimer(world, drawUI);
                        myAnimationTimer.start();
                    }
                    break;
            }
        });
        canvas.setOnKeyReleased(event -> {
            if (!world.gameEnd && world.gameOn) {
                switch (event.getCode()) {
                    case LEFT:
                    case RIGHT:
                        world.platformStop();
                        break;
                }
            }
        });
        root.getChildren().add(canvas);

        primaryStage.setResizable(false);
        primaryStage.setTitle("Ball and blocks");
        primaryStage.setOnCloseRequest(e -> System.exit(0));
        primaryStage.setScene(scene);
        primaryStage.show();

        myAnimationTimer = new MyAnimationTimer(world, drawUI);
        myAnimationTimer.start();
    }

    private void reset() {
        gc.clearRect(0,0, WINDOW_WIDTH, WINDOW_HEIGHT);
        world = new World();
        drawUI = new DrawUI(gc, world);
    }
}
