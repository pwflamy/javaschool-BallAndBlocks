package ru.javaschool.flamy;

import javafx.geometry.Point2D;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class Block implements Drawable, Constant {

    private Position position;
    private int width = BLOCK_WIDTH, height = BLOCK_HEIGHT;
    private AABB aabb;

    public Block(double x, double y) {
        position = new Position(x, y, 0, 0);
        aabb = new AABB(position.getCoord(),new Point2D(position.getCoord().getX()+width,position.getCoord().getY()+height));
    }

    @Override
    public void draw(GraphicsContext gc) {
        gc.setFill(Color.GREEN);
        gc.fillRect(position.getCoord().getX(),position.getCoord().getY(),width,height);
    }

    public AABB getAabb() {
        return aabb;
    }
}
