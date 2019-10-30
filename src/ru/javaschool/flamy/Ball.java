package ru.javaschool.flamy;

import javafx.scene.canvas.GraphicsContext;

import javafx.geometry.Point2D;
import javafx.scene.paint.Color;

public class Ball implements Drawable, HavingNextStation, Constant{

    private final Position position;
    private int size = BALL_SIZE;
    private int speed;
    private boolean moving = false;

    public Ball(double x, double y, double vectorX, double vectorY, int speed) {
        position = new Position(x, y, vectorX, vectorY);
        this.speed = speed;
    }


    public void start() {
        moving = true;
        position.changeDirection(new Point2D(1,-1).normalize());
    }

    public void stop() {
        moving = false;
    }

    @Override
    public void draw(GraphicsContext gc) {
        gc.setFill(Color.CYAN);
        gc.fillOval(position.getCoord().getX(), position.getCoord().getY(), size, size);
    }

    @Override
    public void nextState() {
        if (moving) {
            position.nextPosition(speed);
        }
    }

    public Position getPosition() { return position; }
    public int getSize() { return size; }

    public AABB getAABB() {
        return new AABB(position.getCoord(), position.getCoord().add(BALL_SIZE,BALL_SIZE));
    }
}
