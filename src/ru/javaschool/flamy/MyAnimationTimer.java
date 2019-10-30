package ru.javaschool.flamy;

import javafx.animation.AnimationTimer;

public class MyAnimationTimer extends AnimationTimer {

    private World world;
    private DrawUI drawUI;

    public MyAnimationTimer(World world, DrawUI drawUI) {
        this.world = world;
        this.drawUI = drawUI;
    }

    @Override
    public void handle(long now) {
        if (!world.gameEnd) {
            if (world.gameOn) world.newState();
            drawUI.paint();
        } else {
            drawUI.paintEndGame();
            stop();
        }
    }
}
