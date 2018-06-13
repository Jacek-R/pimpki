package model.pimpek;

import model.cell.Cell;
import model.observer.MatchObserver;
import world.Board;

public interface Pimpek {

    void act(Board world);
    int getEnergy();
    String getName();
    void setLocation(Cell location);
    Cell getLocation();
    void setObserver(MatchObserver observer);
    void regenerate();
}
