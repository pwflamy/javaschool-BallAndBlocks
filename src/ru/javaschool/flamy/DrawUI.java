package ru.javaschool.flamy;

import javafx.scene.canvas.GraphicsContext;

public class DrawUI implements Runnable {

    private Thread thread;
    private boolean suspend = false, stopped = false;

    private World world;
    private GraphicsContext gc;

    public DrawUI(GraphicsContext gc, World world) {
        this.gc = gc;
        this.world = world;
        thread = new Thread(this, "Drawer");
        thread.start();
    }

    private void paint() {
        gc.clearRect(0,0, BallAndBlocks.WINDOW_HEIGHT, BallAndBlocks.WINDOW_HEIGHT);
        world.getDrawableObject().forEach(Drawable -> Drawable.draw(gc));
    }

    @Override
    public void run() {
        try {
            while (!stopped) {
                world.newState();
                paint();
                Thread.sleep(10);

                while (suspend) wait();
            }
        } catch (InterruptedException e) {
            System.out.println("Поток " + thread.getName() + " умер: " + e);
        }
    }

    public void suspend() {
        suspend = true;
    }

    public void resume() {
        suspend = false;
        notify();
    }
}
