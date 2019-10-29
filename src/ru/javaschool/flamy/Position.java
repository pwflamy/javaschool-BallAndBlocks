package ru.javaschool.flamy;

import javafx.geometry.Point2D;

public class Position {
    private Point2D coord;
    private Point2D direction;
    private int speed;

    public Position(Position position) {
        coord = position.coord;
        direction = position.direction;
        speed = position.speed;
    }

    public Position(double x, double y, double vectorX, double vectorY, int speed) {
        coord = new Point2D(x, y);
        direction = new Point2D(vectorX, vectorY).normalize();
        this.speed = speed;
    }

    public Position nextPosition() {
        Position oldPosition = new Position(this);

        coord = coord.add(direction.multiply(speed));

        return oldPosition;
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

    //метод, проверяющий пересекаются ли 2 отрезка [p1, p2] и [p3, p4]
    public static boolean checkIntersectionTwoLine(Point2D p1, Point2D p2, Point2D p3, Point2D p4) {
        //сначала расставим точки по порядку, т.е. чтобы было p1x <= p2x
        double p1x,p2x,p3x,p4x,p1y,p2y,p3y,p4y;
        if (p2.getX() < p1.getX()) {
            Point2D tmp = p1;
            p1 = p2;
            p2 = tmp;
        }
        //и p3x <= p4x
        if (p4.getX() < p3.getX()) {
            Point2D tmp = p3;
            p3 = p4;
            p4 = tmp;
        }
        p1x = p1.getX(); p2x = p2.getX(); p3x = p3.getX(); p4x = p4.getX();
        p1y = p1.getY(); p2y = p2.getY(); p3y = p3.getY(); p4y = p4.getY();
        //проверим существование потенциального интервала для точки пересечения отрезков
        if (p2x < p3x) {
            return false; //ибо у отрезков нету взаимной абсциссы
        }
        //если оба отрезка вертикальные
        if((p1x - p2x == 0) && (p3x - p4x == 0)) {
            //если они лежат на одном X
            if(p1x == p3x) {
                //проверим пересекаются ли они, т.е. есть ли у них общий Y
                //для этого возьмём отрицание от случая, когда они НЕ пересекаются
                return !((Math.max(p1y, p2y) < Math.min(p3y, p4y)) ||
                        (Math.min(p1y, p2y) > Math.max(p3y, p4y)));
            }
            return false;
        }
        //найдём коэффициенты уравнений, содержащих отрезки
        //f1(x) = A1*x + b1 = y
        //f2(x) = A2*x + b2 = y
        //если первый отрезок вертикальный
        if (p1x - p2x == 0) {
        //найдём Xa, Ya - точки пересечения двух прямых
            double Xa = p1x;
            double A2 = (p3y - p4y) / (p3x - p4x);
            double b2 = p3y - A2 * p3x;
            double Ya = A2 * Xa + b2;
            if (p3x <= Xa && p4x >= Xa && Math.min(p1y, p2y) <= Ya &&
                    Math.max(p1y, p2y) >= Ya) {
                return true;
            }
            return false;
        }
        //если второй отрезок вертикальный
        if (p3x - p4x == 0) {
        //найдём Xa, Ya - точки пересечения двух прямых
            double Xa = p3x;
            double A1 = (p1y - p2y) / (p1x - p2x);
            double b1 = p1y - A1 * p1x;
            double Ya = A1 * Xa + b1;
            if (p1x <= Xa && p2x >= Xa && Math.min(p3y, p4y) <= Ya &&
                    Math.max(p3y, p4y) >= Ya) {
                return true;
            }
            return false;
        }
        //оба отрезка невертикальные
        double A1 = (p1y - p2y) / (p1x - p2x);
        double A2 = (p3y - p4y) / (p3x - p4x);
        double b1 = p1y - A1 * p1x;
        double b2 = p3y - A2 * p3x;
        if (A1 == A2) {
            return false; //отрезки параллельны
        }
        //Xa - абсцисса точки пересечения двух прямых
        double Xa = (b2 - b1) / (A1 - A2);
        if ((Xa < Math.max(p1x, p3x)) || (Xa > Math.min( p2x, p4x))) {
            return false; //точка Xa находится вне пересечения проекций отрезков на ось X
        }
        else {
            return true;
        }
    }

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
