package model.pimpekSpawner;

import explorer.WorldManager;
import model.coordinates.Coordinates;
import model.pimpek.Pimpek;
import world.Board;

import java.io.FileNotFoundException;
import java.util.Set;

public class BasicPimpekSpawner implements PimpekSpawner {

    private WorldManager worldManager;
    private Board board;

    public BasicPimpekSpawner(WorldManager worldManager) {
        this.worldManager = worldManager;
    }

    @Override
    public boolean spawn(Set<Pimpek> pimpki) throws FileNotFoundException {
        this.board = worldManager.getBoard();
        for (int i = 0; i < pimpki.size(); i++) {
            boolean contentPlaced = false;
            while (!contentPlaced){
                Coordinates coordinates = worldManager.selectRandomCoordinates();
                if (worldManager.isEmpty(coordinates)) {
                    contentPlaced = worldManager.registerBeing(coordinates, pimpki.iterator().next());
                }
            }
        }
        return true;
    }

    @Override
    public boolean spawnClone(Pimpek cloned, Pimpek parent) {

        // put registerClone on the world map - JW.

        return true;
    }
}
