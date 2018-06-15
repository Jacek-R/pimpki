package pimpek.events;

import coordinates.Coordinates;

import java.util.List;

public class BasicEvent implements Event {

    private EventType type;
    private Coordinates cords;

    public BasicEvent(EventType name, Coordinates coordinates) {
        this.type = name;
        this.cords = coordinates;
    }

    @Override
    public EventType getType() {
        return type;
    }

    @Override
    public Coordinates getCords() {
        return cords;
    }
}
