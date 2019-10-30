package ru.javaschool.flamy;

public class MyThread implements Runnable {

    private DrawUI drawUI;
    private World world;
    private boolean stopped = false;

    public Thread thread;

    public MyThread(DrawUI drawUI, World world) {
        this.drawUI = drawUI;
        this.world = world;
        thread = new Thread(this, "Drawer");
        thread.start();
    }

    @Override
    public void run() {
        while (!stopped) {
            if (!world.gameEnd) {
                if (world.gameOn) world.newState();
                drawUI.paint();
                try {
                    Thread.sleep(10);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            } else stopped = true;
        }
        drawUI.paintEndGame();
    }
}
