package ru.javaschool.flamy;

import javafx.geometry.Point2D;

public interface Constant {
    int WINDOW_WIDTH = 500;
    int WINDOW_HEIGHT = 500;
    int PLATFORM_WIDTH = 100;
    int PLATFORM_HEIGHT = 10;
    int BALL_SIZE = 8;

    Point2D VECTOR_LEFT = new Point2D(-1,0);
    Point2D VECTOR_RIGHT = new Point2D(1, 0);
    Point2D VECTOR_DOWN = new Point2D(0,1);
    Point2D VECTOR_UP = new Point2D(0,-1);
}
