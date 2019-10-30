package ru.javaschool.flamy;

import javafx.geometry.Point2D;

public class Position {
    private Point2D coord;
    private Point2D direction;

    public Position(Position position) {
        coord = position.coord;
        direction = position.direction;
    }

    public Position(double x, double y, double vectorX, double vectorY) {
        coord = new Point2D(x, y);
        direction = new Point2D(vectorX, vectorY).normalize();
    }

    public void nextPosition(int speed) {
        coord = coord.add(direction.multiply(speed));
    }

    public void changeDirection(Point2D vector) {
        direction = direction.add(vector).normalize();
    }

    public void reflectDirection(Point2D normal) {
        double k = -2*direction.dotProduct(normal);
        direction = new Point2D(direction.getX()+k*normal.getX(),direction.getY()+k*normal.getY());
    }

    public void setZeroDirection() {
        direction = Point2D.ZERO;
    }

    public Point2D getCoord() {
        return coord;
    }

    public void setCoord(Point2D coord) {
        this.coord = coord;
    }

    public Point2D getDirection() { return direction; }

    public static Point2D pointIntersection(Point2D p1, Point2D p2, Point2D t1, Point2D t2) {
        double p1x,p2x,t1x,t2x,p1y,p2y,t1y,t2y;
        p1x = p1.getX(); p2x = p2.getX(); t1x = t1.getX(); t2x = t2.getX();
        p1y = p1.getY(); p2y = p2.getY(); t1y = t1.getY(); t2y = t2.getY();

        double a1 = p2y - p1y;
        double b1 = p1x - p2x;
        double c1 = -p1x * (p2y - p1y) + p1y * (p2x - p1x);

        double a2 = t2y - t1y;
        double b2 = t1x - t2x;
        double c2 = -t1x * (t2y - t1y) + t1y * (t2x - t1x);

        double d = a1 * b2 - b1 * a2;

        double newX = Math.round( (-c1 * b2 + b1 * c2) / d );
        double newY = Math.round( (-a1 * c2 + c1 * a2) / d );

        return new Point2D(newX, newY);
    }

}
