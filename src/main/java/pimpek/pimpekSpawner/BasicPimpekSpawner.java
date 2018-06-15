package pimpek.pimpekSpawner;

import worldManager.WorldManager;
import coordinates.Coordinates;
import pimpek.pimpekModel.Pimpek;
import world.Board;

import java.io.FileNotFoundException;
import java.util.Set;

public class BasicPimpekSpawner implements PimpekSpawner {

    private final WorldManager worldManager;

    public BasicPimpekSpawner(WorldManager worldManager) {
        this.worldManager = worldManager;
    }

    @Override
    public boolean spawn(Set<Pimpek> pimpki) throws FileNotFoundException {
        for (Pimpek pimpek: pimpki) {
            boolean contentPlaced = false;
            while (!contentPlaced){
                Coordinates coordinates = worldManager.selectRandomCoordinates();
                if (worldManager.isEmpty(coordinates)) {
                    pimpek.setLocation(coordinates);
                    contentPlaced = worldManager.registerBeing(coordinates, pimpek);
                }
            }
        }
        return true;
    }

    @Override
    public boolean spawnClone(Pimpek cloned, Pimpek parent) throws FileNotFoundException {

        Set<Coordinates> possibleFields = parent.getLocation().getNeighbors();

        for (Coordinates field : possibleFields) {
            if (worldManager.areCoordinatesOnMap(field) && worldManager.isEmpty(field) ) {
                cloned.setLocation(field);
                worldManager.registerBeing(field, cloned);
                return true;
            }
        }
        return false;
    }
}
