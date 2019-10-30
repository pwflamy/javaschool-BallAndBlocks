package ru.javaschool.flamy;

import javafx.geometry.Point2D;

import javax.sound.sampled.Line;
import java.util.Arrays;
import static ru.javaschool.flamy.Constant.*;

/**
 * Класс, представляющий собой объект axis-aligned bounding box, и реализующий
 * работу с столкновениями
 */
public class AABB {
    private Point2D min, max;

    public AABB(Point2D min, Point2D max) {
        this.min = min;
        this.max = max;
       /* minX = min.getX();
        minY = min.getY();
        maxX = max.getX();
        maxY = max.getY();*/
    }

    public Point2D getMin() { return min; }
    public Point2D getMax() { return max; }

    public boolean testAABB(AABB test) {
        if ((max.getX() < test.min.getX()) || (min.getX() > test.max.getX())) return false;
        if ((max.getY() < test.min.getY()) || (min.getY() > test.max.getY())) return false;
        return true;
    }

    public Point2D normalToAABB(AABB test) {
        // Вычисление половины ширины вдоль оси x для каждого объекта
        double a_extent = (max.getX() - min.getX()) / 2;
        double b_extent = (test.max.getX() - test.min.getX()) / 2;

        // Вычисление половины ширины вдоль оси y для каждого объекта
        double a_extentY = (max.getY() - min.getY()) / 2;
        double b_extentY = (test.max.getY() - test.min.getY()) / 2;

        Point2D n = new Point2D(test.min.getX()+b_extent,test.min.getY()+b_extentY)
                .subtract(new Point2D(min.getX()+a_extent,min.getY()+a_extentY));

        // Вычисление наложения по оси x
        double x_overlap = a_extent + b_extent - Math.abs(n.getX());
        // Вычисление наложения по оси y
        double y_overlap = a_extentY + b_extentY - Math.abs(n.getY());
        // Проверка SAT по оси x и у
        if ((x_overlap > 0) && (y_overlap > 0)) {
                // Определяем, по какой из осей проникновение наименьшее
                if (x_overlap > y_overlap) {
                    // Указываем в направлении B, зная, что n указывает в направлении от A к B
                    if (n.getY() < 0)
                        return VECTOR_DOWN;
                    else
                        return VECTOR_UP;
                } else {
                    // Указываем в направлении B, зная, что n указывает в направлении от A к B
                    if (n.getX() < 0)
                        return VECTOR_RIGHT;
                    else
                        return VECTOR_LEFT;
                }
        }
        return Point2D.ZERO;
    }
}
