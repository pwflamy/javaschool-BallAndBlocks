package ru.javaschool.flamy;

import javafx.scene.canvas.GraphicsContext;

import javafx.geometry.Point2D;
import javafx.scene.paint.Color;

public class Ball implements Drawable, HavingNextStation, Constant{

    private final Position position;
    private final Platform platform;
    private int size = BALL_SIZE;
    private int speed;
    private boolean moving = false;
    private AABB aabb;

    public Ball(double x, double y, double vectorX, double vectorY, int speed, Platform platform) {
        position = new Position(x, y, vectorX, vectorY, speed);
        this.platform = platform;
    }


    public void start() {
        moving = true;
        position.changeDirection(new Point2D(1,-1).normalize());
    }

    @Override
    public void draw(GraphicsContext gc) {
        gc.setFill(Color.CYAN);
        gc.fillOval(position.getCoord().getX(), position.getCoord().getY(), size, size);
    }

    @Override
    public void nextState() {
        if (moving) {
            Position oldPosition = position.nextPosition();
            Point2D coord = position.getCoord();
            if (coord.getX()+size > WINDOW_WIDTH) {
                position.setCoord(Position.pointIntersection(oldPosition.getCoord(), coord,
                        new Point2D(WINDOW_WIDTH-BALL_SIZE,0), new Point2D(WINDOW_WIDTH-BALL_SIZE, WINDOW_HEIGHT)));
                position.reflectDirection(VECTOR_LEFT);
            } else if (coord.getX() < 0) {
                position.setCoord(Position.pointIntersection(oldPosition.getCoord(), coord,
                        new Point2D(0,0), new Point2D(0, WINDOW_HEIGHT)));
                position.reflectDirection(VECTOR_RIGHT);
            } else if (coord.getY() < 0) {
                position.setCoord(Position.pointIntersection(oldPosition.getCoord(), coord,
                        new Point2D(0, 0), new Point2D(WINDOW_WIDTH, 0)));
                position.reflectDirection(VECTOR_DOWN);
            } else if (coord.getY() + BALL_SIZE > WINDOW_HEIGHT) {
                moving = false;
            }
            AABB myAABB = getAABB();
            AABB platformAABB = platform.getAABB();
            if (myAABB.testAABB(platformAABB)) position.reflectDirection(myAABB.normalToAABB(platformAABB,coord,platform.getCoord()));
        }
    }

    private AABB getAABB() {
        return new AABB(position.getCoord(), position.getCoord().add(BALL_SIZE,BALL_SIZE));
    }
}
