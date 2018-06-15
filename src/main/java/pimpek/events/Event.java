package pimpek.events;

import coordinates.Coordinates;

import java.util.List;

public interface Event {

    EventType getType();
    Coordinates getCords();

}
