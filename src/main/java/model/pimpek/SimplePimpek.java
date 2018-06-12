package model.pimpek;

import model.FieldOccupant;
import model.cell.Cell;

/**
 * basic genre - a bit stupid
 */

public class SimplePimpek implements Pimpek {

    private final Pimpek ancestor;
    private final String name;
    private Cell currentLocation;
    private int energy;
    private final FieldOccupant fieldOccupant = FieldOccupant.PACIFIST;

    // constructor for origin/root pimpek:
    public SimplePimpek(String name, Cell startLocation, int energy) {
        this.ancestor = this;
        this.name = name;
        this.currentLocation = startLocation;
        this.energy = energy;
    }

    // constructor for clones:
    public SimplePimpek(Pimpek ancestor, String name, Cell startLocation, int energy) {
        this.ancestor = ancestor;
        this.name = name;
        this.currentLocation = startLocation;
        this.energy = energy;
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
}
