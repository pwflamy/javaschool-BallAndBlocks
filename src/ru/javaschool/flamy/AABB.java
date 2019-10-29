package ru.javaschool.flamy;

import javafx.geometry.Point2D;

import javax.sound.sampled.Line;

public class AABB implements Constant {
    private Point2D min, max;

    public AABB(Point2D min, Point2D max) {
        this.min = min;
        this.max = max;
       /* minX = min.getX();
        minY = min.getY();
        maxX = max.getX();
        maxY = max.getY();*/
    }

    public boolean testAABB(AABB test) {
        if ((max.getX() < test.min.getX()) || (min.getX() > test.max.getX())) return false;
        if ((max.getY() < test.min.getY()) || (min.getY() > test.max.getY())) return false;
        return true;
    }

    public Point2D normalToAABB(AABB test, Point2D coordA, Point2D coordB) {
        Point2D n = coordA.subtract(coordB);
        // Вычисление половины ширины вдоль оси x для каждого объекта
        double a_extent = (max.getX() - min.getX()) / 2;
        double b_extent = (test.max.getX() - test.min.getX()) / 2;

        // Вычисление наложения по оси x
        double x_overlap = a_extent + b_extent - Math.abs(n.getX());
        // Вычисление половины ширины вдоль оси y для каждого объекта
        double a_extentY = (max.getY() - min.getY()) / 2;
        double b_extentY = (test.max.getY() - test.min.getY()) / 2;

        // Вычисление наложения по оси y
        double y_overlap = a_extentY + b_extentY - Math.abs(n.getY());
        // Проверка SAT по оси x и у
        if ((x_overlap > 0) || (y_overlap > 0)) {
                // Определяем, по какой из осей проникновение наименьшее
                if (x_overlap > y_overlap) {
                    // Указываем в направлении B, зная, что n указывает в направлении от A к B
                    if (n.getX() < 0)
                        return VECTOR_LEFT;
                    else
                        return VECTOR_RIGHT;
                } else {
                    // Указываем в направлении B, зная, что n указывает в направлении от A к B
                    if (n.getY() < 0)
                        return VECTOR_UP;
                    else
                        return VECTOR_DOWN;
                }
        }
        return Point2D.ZERO;
    }

    private Lines[] getLines(AABB test) {
        Lines[] result = new Lines[4];
        result[0] = new Lines(test.min, new Point2D(test.max.getX(),test.min.getY()));
        result[1] = new Lines(new Point2D(test.max.getX(),test.min.getY()),test.max);
        result[2] = new Lines(new Point2D(test.min.getX(),test.max.getY()),test.max);
        result[3] = new Lines(test.min, new Point2D(test.min.getX(),test.max.getY()));
        return result;
    }

    private class Lines {
        Point2D a,b;
        Lines(Point2D a, Point2D b) {
            this.a = a;
            this.b = b;
        }
    }
}
