package pimpek.events;

import coordinates.Coordinates;

public class BasicEvent implements Event {

    private String name;
    private Coordinates cords;

    public BasicEvent(String name, Coordinates cords) {
        this.name = name;
        this.cords = cords;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public Coordinates getCoords() {
        return cords;
    }
}