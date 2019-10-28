package ru.javaschool.flamy;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class BallAndBlocks extends Application {

    public static final int WINDOW_WIDTH = 500;
    public static final int WINDOW_HEIGHT = 500;

    private GraphicsContext gc;
    private DrawUI drawUI;
    private World world;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        world = new World();

        Group root = new Group();
        Scene scene = new Scene(root, WINDOW_WIDTH, WINDOW_HEIGHT, Color.BLACK);

        Canvas canvas = new Canvas(WINDOW_WIDTH, WINDOW_HEIGHT);
        gc = canvas.getGraphicsContext2D();

        canvas.setFocusTraversable(true);
        canvas.setOnKeyPressed(event -> {
            Platform platform = world.getPlatform();
            switch (event.getCode()) {
                case LEFT:
                    platform.moveLeft();
                    break;
                case RIGHT:
                    platform.moveRight();
                    break;
            }
        });
        canvas.setOnKeyReleased(event -> {
            Platform platform = world.getPlatform();
            switch (event.getCode()) {
                case LEFT:
                case RIGHT:
                    platform.moveStop();
                    break;
            }
        });
        root.getChildren().add(canvas);

        primaryStage.setResizable(false);
        primaryStage.setTitle("Ball and blocks");
        primaryStage.setOnCloseRequest(e -> System.exit(0));
        primaryStage.setScene(scene);
        primaryStage.show();


        drawUI = new DrawUI(gc, world);
    }
}
