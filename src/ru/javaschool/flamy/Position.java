package ru.javaschool.flamy;

public class Position {
    public Coordinate coord;
    private VectorNormal direction;
    private int speed;

    public Position() {}

    public Position(Position position) {
        this.coord = position.coord;
        this.direction = position.direction;
        this.speed = position.speed;
    }

    public Position(Coordinate coord, VectorNormal direction, int speed) {
        this.coord = coord;
        this.direction = direction;
        this.speed = speed;
    }

    public Position nextPosition() {
        Position oldPosition = new Position(this);
        coord.addVector(direction.scalarMul(speed));
        return oldPosition;
    }

    public Vector getDirection() {
        return direction;
    }

    public void setDirection(VectorNormal vector) {
        direction = vector;
    }
}
