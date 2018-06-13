package model.pimpek;

import explorer.WorldManager;
import model.CellPaths;
import world.Board;

public class Angry extends SimplePimpek implements Predator {

    private static final String IMAGE_PATH = CellPaths.PREDATOR.getPath();

    public Angry(String name, int energy, int cloningCost, WorldManager explorer) {
        super(name, energy, cloningCost, explorer);
    }

    public Angry(Pimpek ancestor, String name, int energy, int cloningCost, WorldManager explorer) {
        super(ancestor, name, energy, cloningCost, explorer);
    }

    @Override
    public void act(Board world) {

    }

    @Override
    public String getImagePath() {
        return IMAGE_PATH;
    }

}
