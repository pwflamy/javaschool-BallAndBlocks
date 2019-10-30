package ru.javaschool.flamy;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

public class DrawUI implements Constant {

    private World world;
    private GraphicsContext gc;

    public DrawUI(GraphicsContext gc, World world) {
        this.gc = gc;
        this.world = world;
    }

    public void paint() {
        gc.clearRect(0,0, WINDOW_HEIGHT, WINDOW_HEIGHT);
        world.getDrawableObject().forEach(Drawable -> Drawable.draw(gc));
    }

    public void paintEndGame() {
        gc.setFont(new Font("",46));
        gc.setFill(Color.RED);
        gc.fillText("GAME END!", WINDOW_WIDTH/2-150,WINDOW_HEIGHT/2-50);
    }

    public void setWorld(World world) {
        this.world = world;
    }
}
