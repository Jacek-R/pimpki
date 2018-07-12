package pimpek.pimpekModel;

import cell.Cell;
import cell.CellPaths;
import coordinates.Coordinates;
import javafx.scene.image.Image;
import pimpek.events.BasicEvent;
import pimpek.events.Event;
import pimpek.events.EventType;
import worldManager.WorldManager;

import java.io.FileNotFoundException;
import java.util.*;

import static cell.CellPaths.BLOOD;

public class Angry extends SimplePimpek implements Predator {

    private static final Image IMAGE = CellPaths.PREDATOR.getImage();
    private final PimpekGenre genre = PimpekGenre.PREDATOR;


    public Angry(String name, int energy, int cloningCost, WorldManager worldManager) {
        super(name, energy, cloningCost, worldManager);
    }

    public Angry(Pimpek ancestor, String name, int energy, int cloningCost, WorldManager worldManager) {
        super(ancestor, name, energy, cloningCost, worldManager);
    }

    protected Event scan() throws FileNotFoundException {
        Set<Coordinates> possibleCordsToGo = getLocation().getNeighbors();

        // check for pimpeks
        for (Coordinates coordinate : possibleCordsToGo) {
            if (getWorldManager().hasBeing(coordinate) ) {
                return handlePimpkiProblem();
            }
        }

        // check for food
        for (Coordinates coordinate : possibleCordsToGo) {
            if (getWorldManager().hasFood(coordinate) ) {
                return new BasicEvent(EventType.EAT, coordinate);
            }
        }

        return chaoticMove(possibleCordsToGo);
    }

    private Event handlePimpkiProblem() throws FileNotFoundException {
        Set<Coordinates> neighbors = getLocation().getNeighbors();
        List<Coordinates> possiblePlacesToRun = new ArrayList<>();
        WorldManager worldManager = getWorldManager();
        for (Coordinates cords: neighbors) {
            if (worldManager.areCoordinatesOnMap(cords) &&
                    (!worldManager.hasObstacle(cords) || worldManager.isEmpty(cords) || worldManager.hasBeing(cords)) ) {
                possiblePlacesToRun.add(cords);
            }
        }

        if (possiblePlacesToRun.size() == 0) {
            return new BasicEvent(EventType.WAIT, getLocation());
        }

        for (Coordinates coordinates : possiblePlacesToRun) {

            if (worldManager.hasBeing(coordinates) || (getEnergy() > checkOpponentLife(coordinates))) {
                if(worldManager.hasPredator(coordinates)){
                    incrementEnergy(worldManager.getPredator(coordinates).getEnergy()/3);
                    worldManager.getPredator(coordinates).kill();
                    worldManager.cleanUpPlace(coordinates);
                    Cell cell = worldManager.getBoard().getCellAt(coordinates.getX(), coordinates.getY());
                    cell.getCellView().setBackground(BLOOD.getImage());
                    return new BasicEvent(EventType.MOVE, coordinates);
                }else if(worldManager.hasPacifist(coordinates)){
                    incrementEnergy(worldManager.getPacifist(coordinates).getEnergy()/3);
                    worldManager.getPacifist(coordinates).kill();
                    worldManager.cleanUpPlace(coordinates);
                    Cell cell = worldManager.getBoard().getCellAt(coordinates.getX(), coordinates.getY());
                    cell.getCellView().setBackground(BLOOD.getImage());
                    return new BasicEvent(EventType.MOVE, coordinates);
                }
            }
        }

        return new BasicEvent(EventType.MOVE, possiblePlacesToRun.get(0));
    }


    private int checkOpponentLife(Coordinates coord) {
        WorldManager worldManager = getWorldManager();
        if(worldManager.hasPredator(coord)){
            return worldManager.getPredator(coord).getEnergy();
        }else if(worldManager.hasPacifist(coord)){
            return worldManager.getPacifist(coord).getEnergy();
        }
        return 0;
    }

    @Override
    public Image getImage() {
        return IMAGE;
    }

    @Override
    public PimpekGenre getGenre() {
        return genre;
    }

}
