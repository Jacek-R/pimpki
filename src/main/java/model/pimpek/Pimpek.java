package model.pimpek;

import model.cellcontent.Content;
import model.coordinates.Coordinates;
import model.observer.MatchObserver;
import world.Board;

import java.io.FileNotFoundException;

public interface Pimpek extends Content {

    void act(Board world) throws FileNotFoundException;
    int getEnergy();
    String getName();
    void setLocation(Coordinates location);
    Coordinates getLocation();
    void setObserver(MatchObserver observer);
    void regenerate();
    PimpekGenre getGenre();
    Pimpek getAncestor();
}
