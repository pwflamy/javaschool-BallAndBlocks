package ru.javaschool.flamy;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

public class Score implements Drawable {

    private int score = 0;
    private Position position;

    public Score(double x, double y) {
        position = new Position(x, y, 0, 0);
    }

    @Override
    public void draw(GraphicsContext gc) {
        gc.setFill(Color.RED);
        gc.setFont(new Font("",16));
        gc.fillText("Score: " + score, position.getCoord().getX(), position.getCoord().getY());
    }

    public void addScore(int addScore) {
        score += addScore;
    }
}
