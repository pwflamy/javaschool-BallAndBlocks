package ru.javaschool.flamy;

import java.util.ArrayList;

public class World {
    private ArrayList<Drawable> drawableObject = new ArrayList<>();
    private ArrayList<HavingNextStation> havingNextStations = new ArrayList<>();

    private Platform platform;

    public World() {
        platform = new Platform(new Coordinate(250,480), new VectorNormal(0,0), 10);
        drawableObject.add(platform);
        havingNextStations.add(platform);
    }

    public Platform getPlatform() {
        return platform;
    }

    public ArrayList<Drawable> getDrawableObject() {
        return drawableObject;
    }

    public void newState() {
        havingNextStations.forEach(HavingNextStation::nextState);
    }

}
