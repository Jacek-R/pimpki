package model.pimpek;

import model.cell.Cell;
import model.observer.MatchObserver;
import model.observer.NullObserver;

/**
 * basic genre - a bit stupid
 */

public class SimplePimpek implements Pimpek {

    private final Pimpek ancestor;  // use it to update statistic (observer)
    private final String name;
    private Cell currentLocation;
    private int energy;
    private final int cloningCost;
    private MatchObserver observer = new NullObserver();  // null object pattern to avoid null pointer ex


    // constructor for origin/root pimpek:
    public SimplePimpek(String name, int energy, int cloningCost) {
        this.ancestor = this;
        this.name = name;
        this.energy = energy;
        this.cloningCost = cloningCost;
    }

    // constructor for clones:
    public SimplePimpek(Pimpek ancestor, String name, int energy, int cloningCost) {
        this.ancestor = ancestor;
        this.name = name;
        this.energy = energy;
        this.cloningCost = cloningCost;
    }

    @Override
    public void act() {

        // to implement

    }

    @Override
    public int getEnergy() {
        return energy;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public void setLocation(Cell location) {
        currentLocation = location;
    }

    @Override
    public Cell getLocation() {
        return currentLocation;
    }

    @Override
    public void setObserver(MatchObserver observer) {
        this.observer = observer;
    }

    protected MatchObserver getObserver() {
        return observer;
    }

    protected Pimpek getAncestor() {
        return ancestor;
    }

    protected int getCloningCost() {
        return cloningCost;
    }
}
