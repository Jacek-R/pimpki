package pimpek.pimpekModel;

import pimpek.events.EventType;
import worldManager.WorldManager;
import cell.CellPaths;
import coordinates.Coordinates;
import pimpek.events.BasicEvent;
import pimpek.events.Event;
import food.foodModel.Food;
import observer.MatchObserver;
import observer.NullObserver;

import java.io.FileNotFoundException;
import java.util.*;
import java.util.stream.Collectors;

/**
 * basic genre - a bit stupid
 */

public class SimplePimpek implements Pacifist {

    private final Pimpek ancestor;  // use it to update statistic (observer)
    private static final String IMAGE_PATH = CellPaths.PIMPEK.getPath();
    private final String name;
    private Coordinates currentLocation;
    private int energy;
    private final int cloningCost;
    private MatchObserver observer = new NullObserver();  // null object pattern to avoid null pointer ex
    private final int totalEnergy;
    private final WorldManager worldManager;
    private final PimpekGenre genre = PimpekGenre.PACIFIST;


    // constructor for origin/root pimpekModel:
    public SimplePimpek(String name, int energy, int cloningCost, WorldManager worldManager) {
        this.ancestor = this;
        this.name = name;
        this.energy = energy;
        this.cloningCost = cloningCost;
        this.totalEnergy = energy;
        this.worldManager = worldManager;
    }

    // constructor for clones:
    public SimplePimpek(Pimpek ancestor, String name, int energy, int cloningCost, WorldManager worldManager) {
        this.ancestor = ancestor;
        this.name = name;
        this.energy = energy;
        this.cloningCost = cloningCost;
        this.totalEnergy = energy;
        this.worldManager = worldManager;
    }

    @Override
    public void act() throws FileNotFoundException {
        Event event = scan();
        switch(event.getType()){
            case MOVE:
                move(event.getCords());
                break;
            case EAT:
                eat(event.getCords());
                break;
            case WAIT:
                break;
        }
    }

    protected void move(Coordinates coordinates) throws FileNotFoundException {
            System.out.println("ruszam się tu: " + coordinates);
            System.out.println(this);
            worldManager.registerBeing(coordinates, this);

    }

    protected void eat(Coordinates coordinates) throws FileNotFoundException {
        Food food = worldManager.getFood(coordinates);

        this.energy += food.getEnergy();
        observer.registerEnergyPoints(this, food.getEnergy());
        move(coordinates);
    }

    protected Event scan() {
        Set<Coordinates> possibleCordsToGo = currentLocation.getNeighbors();

        // check for predator
        for (Coordinates coordinate : possibleCordsToGo) {
            if (worldManager.hasPredator(coordinate) ) {
                return handlePredatorProblem();
            }
        }

        // check for food
        for (Coordinates coordinate : possibleCordsToGo) {
            if (worldManager.hasFood(coordinate) ) {
                return new BasicEvent(EventType.EAT, coordinate);
            }
        }

        return chaoticMove(possibleCordsToGo);
    }

    protected Event handlePredatorProblem() {
        Set<Coordinates> neighbors = currentLocation.getNeighbors();
        List<Coordinates> possiblePlacesToRun = new ArrayList<>();

        for (Coordinates cords: neighbors) {
            if (worldManager.areCoordinatesOnMap(cords) &&
                    (worldManager.isEmpty(cords) || worldManager.hasFood(cords)) ) {
                possiblePlacesToRun.add(cords);
            }
        }

        if (possiblePlacesToRun.size() == 0) {
            return new BasicEvent(EventType.WAIT, currentLocation);
        }

        for (Coordinates coordinates : possiblePlacesToRun) {
            if (worldManager.isNeighborhoodEmpty(coordinates) ) {
                return new BasicEvent(EventType.MOVE, coordinates);
            }
        }

        return new BasicEvent(EventType.MOVE, possiblePlacesToRun.get(0));
    }

    private Event chaoticMove(Set<Coordinates> possiblePlacesToGo) {
        Random chaos = new Random();
        if ( chaos.nextBoolean() ) {
            return new BasicEvent(EventType.WAIT, currentLocation);
        }

        List<Coordinates> placesAsList = new ArrayList<>(possiblePlacesToGo);
        Collections.shuffle(placesAsList);
        return new BasicEvent(EventType.MOVE, placesAsList.get(0));
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
    public void setLocation(Coordinates location) {
        currentLocation = location;
    }

    @Override
    public Coordinates getLocation() {
        return currentLocation;
    }

    @Override
    public void setObserver(MatchObserver observer) {
        this.observer = observer;
    }

    @Override
    public void regenerate() {
        this.energy = totalEnergy;
    }

    @Override
    public PimpekGenre getGenre() {
        return genre;
    }

    protected MatchObserver getObserver() {
        return observer;
    }

    @Override
    public Pimpek getAncestor() {
        return ancestor;
    }

    protected int getCloningCost() {
        return cloningCost;
    }

    protected int getTotalEnergy() {
        return totalEnergy;
    }

    protected WorldManager getWorldManager() {
        return worldManager;
    }

    @Override
    public String getImagePath() {
        return IMAGE_PATH;
    }

    @Override
    public String toString() {
        return String.format("%s energy: %s, location: %s", name, energy, currentLocation);
    }
}
