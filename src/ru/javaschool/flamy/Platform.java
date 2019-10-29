package ru.javaschool.flamy;

import javafx.geometry.Point2D;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class Platform implements Drawable, HavingNextStation, Constant {

    private Position position;
    private int height = PLATFORM_HEIGHT, width = PLATFORM_WIDTH;
    private boolean moving = false;

    public Platform(double x, double y, double vectorX, double vectorY, int speed) {
        position = new Position(x, y, vectorX, vectorY, speed);
    }

    public boolean isMoving() {
        return moving;
    }

    public void moveLeft() {
        if (!moving) {
            moving = true;
            position.changeDirection(VECTOR_LEFT);
        }
    }

    public void moveRight() {
        if (!moving) {
            moving = true;
            position.changeDirection(VECTOR_RIGHT);
        }
    }

    public void moveStop() {
        moving = false;
        position.setZeroDirection();
    }

    @Override
    public void draw(GraphicsContext gc) {
        gc.setFill(Color.RED);
        gc.fillRect(position.getCoord().getX(),position.getCoord().getY(), width, height);
    }

    @Override
    public void nextState() {
        if (moving) {
            Position oldPosition = position.nextPosition();
            if (position.getCoord().getX() + width > WINDOW_HEIGHT) position.setCoord(new Point2D(WINDOW_HEIGHT-width, position.getCoord().getY()));
            if (position.getCoord().getX() < 0) position.setCoord(new Point2D(0, position.getCoord().getY()));
        }
    }

    public AABB getAABB() {
        return new AABB(position.getCoord(),position.getCoord().add(PLATFORM_WIDTH,PLATFORM_HEIGHT));
    }

    public Point2D getCoord() {
        return position.getCoord();
    }
}
