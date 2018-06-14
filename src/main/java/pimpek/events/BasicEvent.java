package pimpek.events;

import coordinates.Coordinates;

import java.util.List;

public class BasicEvent implements Event {

    private EventType type;
    private List<Coordinates> cords;

    public BasicEvent(EventType name, List<Coordinates> cords) {
        this.type = name;
        this.cords = cords;
    }

    @Override
    public EventType getType() {
        return type;
    }

    @Override
    public List<Coordinates> getCoords() {
        return cords;
    }
}
