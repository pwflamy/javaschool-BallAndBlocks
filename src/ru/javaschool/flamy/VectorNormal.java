package ru.javaschool.flamy;

public class VectorNormal extends Vector {

    public VectorNormal(double vectorX, double vectorY) {
        super(vectorX,vectorY);
        if ((length() != 0) && (length() != 1)) normalize();
    }

    private void normalize() {
        vectorX /= length();
        vectorY /= length();
    }

    public void sumVector(Vector b) {
        this.vectorX += b.vectorX;
        this.vectorY += b.vectorY;
        if ((length() != 0) && (length() != 1)) normalize();
    }

}
