package ru.javaschool.flamy;

import java.util.ArrayList;

public class World implements Constant {
    private ArrayList<Drawable> drawableObject = new ArrayList<>();
    private ArrayList<HavingNextStation> havingNextStations = new ArrayList<>();

    private final Platform platform;
    private final Ball ball;

    public World() {
        platform = new Platform(200,470, 0,0, 5);
        ball = new Ball(200+Constant.PLATFORM_WIDTH/2-BALL_SIZE/2,470-BALL_SIZE, 0, 0, 5, platform);
        drawableObject.add(platform);
        drawableObject.add(ball);
        havingNextStations.add(platform);
        havingNextStations.add(ball);
    }

    public Platform getPlatform() {
        return platform;
    }

    public Ball getBall() {
        return ball;
    }

    public ArrayList<Drawable> getDrawableObject() {
        return drawableObject;
    }

    public void newState() {
        havingNextStations.forEach(HavingNextStation::nextState);
    }

}
