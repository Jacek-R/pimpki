package model.pimpek;

import explorer.WorldExplorer;

public class Angry extends SimplePimpek implements Predator {


    public Angry(String name, int energy, int cloningCost, WorldExplorer explorer) {
        super(name, energy, cloningCost, explorer);
    }

    public Angry(Pimpek ancestor, String name, int energy, int cloningCost, WorldExplorer explorer) {
        super(ancestor, name, energy, cloningCost, explorer);
    }

    @Override
    public void act() {

    }


}
