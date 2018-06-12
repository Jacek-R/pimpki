package model.pimpek;

import model.cell.Cell;

public class Predator extends SimplePimpek implements Pimpek {

    public Predator(String name, Cell startLocation, int energy) {
        super(name, startLocation, energy);
    }

    public Predator(Pimpek ancestor, String name, Cell startLocation, int energy) {
        super(ancestor, name, startLocation, energy);
    }

    @Override
    public void act() {

    }


}
