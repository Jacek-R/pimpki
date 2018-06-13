package model.pimpek;

import model.cell.Cell;
import model.observer.MatchObserver;

public interface Pimpek {

    void act();
    int getEnergy();
    String getName();
    void setLocation(Cell location);
    Cell getLocation();
    void setObserver(MatchObserver observer);
    void regenerate();
}
