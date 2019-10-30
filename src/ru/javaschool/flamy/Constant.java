package ru.javaschool.flamy;

import javafx.geometry.Point2D;

public class Constant {
    public static final int WINDOW_WIDTH = 500;
    public static final int WINDOW_HEIGHT = 500;
    public static final int PLATFORM_WIDTH = 100;
    public static final int PLATFORM_HEIGHT = 10;
    public static final int BALL_SIZE = 8;
    public static final int BLOCK_WIDTH = 100;
    public static final int BLOCK_HEIGHT = 50;
    public static final int PLATFORM_START_X = 200;
    public static final int PLATFORM_START_Y = 470;

    public static final Point2D VECTOR_LEFT = new Point2D(-1,0);
    public static final Point2D VECTOR_RIGHT = new Point2D(1, 0);
    public static final Point2D VECTOR_DOWN = new Point2D(0,1);
    public static final Point2D VECTOR_UP = new Point2D(0,-1);
}
