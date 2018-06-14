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
    private final WorldManager explorer;
    private final PimpekGenre genre = PimpekGenre.PACIFIST;


    // constructor for origin/root pimpekModel:
    public SimplePimpek(String name, int energy, int cloningCost, WorldManager explorer) {
        this.ancestor = this;
        this.name = name;
        this.energy = energy;
        this.cloningCost = cloningCost;
        this.totalEnergy = energy;
        this.explorer = explorer;
    }

    // constructor for clones:
    public SimplePimpek(Pimpek ancestor, String name, int energy, int cloningCost, WorldManager explorer) {
        this.ancestor = ancestor;
        this.name = name;
        this.energy = energy;
        this.cloningCost = cloningCost;
        this.totalEnergy = energy;
        this.explorer = explorer;
    }

    @Override
    public void act() throws FileNotFoundException {
        Set<Coordinates> predators = whereArePredators(currentLocation.getNeighbors());
        Event event = scan(predators);
        switch(event.getType()){
            case MOVE:
                move(event.getCoords());
                break;
            case EAT:
                eat(event.getCoords());
                break;
            case DEFAULT:
                run(event.getCoords());
                break;
            case WAIT:
                break;

        }

    }

    private void move(Coordinates coords) throws FileNotFoundException{
        explorer.registerBeing(coords, this);
        setLocation(coords);
    }

    private void run(Coordinates coords) throws FileNotFoundException {

        /**
         * need to check if we can move there (there are no obstacle, other predators or something)
         */
//        Coordinates whereRun = null;
//
//        if(currentLocation.getN() == coords){
//            whereRun = currentLocation.getSW();
//        }else if(currentLocation.getS() == coords){
//            whereRun = currentLocation.getNE();
//        }else if(currentLocation.getW() == coords){
//            whereRun = currentLocation.getNE();
//        }else if(currentLocation.getE() == coords){
//            whereRun = currentLocation.getSW();
//        }else if(currentLocation.getNE() == coords){
//            whereRun = currentLocation.getSW();
//        }else if(currentLocation.getNW() == coords){
//            whereRun = currentLocation.getSE();
//        }else if(currentLocation.getSE() == coords){
//            whereRun = currentLocation.getNW();
//        }else if(currentLocation.getSW() == coords){
//            whereRun = currentLocation.getNE();
//        }
//
//        move(whereRun);
    }

    private void eat(Coordinates coords) throws FileNotFoundException {
        Food food = explorer.getFood(coords);
        this.energy += food.getEnergy();
        observer.registerEnergyPoints(this, food.getEnergy());
        move(coords);

    }

    protected Event scan(Set<Coordinates> pimpeks) {
        Set<Coordinates> neighbors = currentLocation.getNeighbors();
        List<Coordinates> coords = new ArrayList<>();
        coords.add(currentLocation);
        Event event = new BasicEvent(EventType.WAIT, coords);
        if(pimpeks.size() != 0){
            coords = new ArrayList<>();
            coords.add(currentLocation);
            event = new BasicEvent(EventType.DEFAULT, coords);
        }else {
            for (Coordinates coord : neighbors) {
                if (explorer.isNeighborhoodEmpty(coord)) {
                    coords = new ArrayList<>();
                    coords.add(coord);
                    event = new BasicEvent(EventType.MOVE, coords);
                } else if (explorer.isFood(coord)) {
                    coords = new ArrayList<>();
                    coords.add(coord);
                    event = new BasicEvent(EventType.EAT, coords);
                    return event;
                }
            }
        }
        return event;
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

    protected WorldManager getExplorer() {
        return explorer;
    }

    @Override
    public String getImagePath() {
        return IMAGE_PATH;
    }
}
