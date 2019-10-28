package ru.javaschool.flamy;

public class Coordinate {
    public int x, y;

    public Coordinate() { }

    public Coordinate(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public void addVector(Vector vector) {
        x = (int) Math.round(x + vector.vectorX);
        y = (int) Math.round(y + vector.vectorY);
    }
}
