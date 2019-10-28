package ru.javaschool.flamy;

import static java.lang.Math.*;

public class Vector {

    public double vectorX, vectorY;

    private Vector() {}

    public Vector(double vectorX, double vectorY) {
        this.vectorX = vectorX;
        this.vectorY = vectorY;
    }

    public double length() {
        return sqrt(pow(vectorX,2) + pow(vectorY,2));
    }


    public void sumVector(Vector b) {
        this.vectorX += b.vectorX;
        this.vectorY += b.vectorY;
    }

    public Vector scalarMul(int mul) {
        return new Vector(this.vectorX * mul, this.vectorY * mul);
    }

}
