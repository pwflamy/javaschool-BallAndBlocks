package ru.javaschool.flamy;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class Platform implements Drawable, HavingNextStation {

    private Position position;
    private int height = 10, width = 100;
    private boolean moving = false;

    public Platform(Coordinate coord, VectorNormal direction, int speed) {
        position = new Position(coord, direction, speed);
    }

    public boolean isMoving() {
        return moving;
    }

    public void moveLeft() {
        if (!moving) {
            moving = true;
            position.getDirection().sumVector(new Vector(-1, 0));
        }
    }

    public void moveRight() {
        if (!moving) {
            moving = true;
            position.getDirection().sumVector(new Vector(1, 0));
        }
    }

    public void moveStop() {
        moving = false;
        position.setDirection(new VectorNormal(0,0));
    }

    @Override
    public void draw(GraphicsContext gc) {
        gc.setFill(Color.RED);
        gc.fillRect(position.coord.x-width/2,position.coord.y-height/2,width, height);
    }

    @Override
    public void nextState() {
        if (moving) {
            Position oldPosition = position.nextPosition();
        }
    }
}
