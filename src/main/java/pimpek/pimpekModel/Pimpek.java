package pimpek.pimpekModel;

import cell.cellcontent.Content;
import coordinates.Coordinates;
import observer.MatchObserver;

import java.io.FileNotFoundException;

public interface Pimpek extends Content {

    void act() throws FileNotFoundException;
    int getEnergy();
    String getName();
    void setLocation(Coordinates location);
    Coordinates getLocation();
    void setObserver(MatchObserver observer);
    void regenerate();
    PimpekGenre getGenre();
    Pimpek getAncestor();
}
