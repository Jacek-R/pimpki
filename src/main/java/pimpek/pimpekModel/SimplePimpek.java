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

    protected void move(List<Coordinates> coords) throws FileNotFoundException{
        explorer.registerBeing(coords.get(0), this);
        setLocation(coords.get(0));
    }

    protected void move() throws FileNotFoundException{
        move(getRandomCoordinate());
    }

    protected List<Coordinates> getRandomCoordinate() {
        Set<Coordinates> direction = currentLocation.getNeighbors();
        Coordinates direct = currentLocation.getE();
        do {
            int size = direction.size();
            int item = new Random().nextInt(size); // In real life, the Random object should be rather more shared than this
            int i = 0;
            for (Coordinates coord : direction) {
                if (i == item)
                    direct = coord;
                i++;
            }
        }while(!explorer.isObstacle(direct));
        return Collections.singletonList(direct);
    }

    private void run(List<Coordinates> coords) throws FileNotFoundException {

        /**
         * need to check if we can move there (there are no obstacle, other predators or something)
         */
            int currentY = getLocation().getY();
            int currentX = getLocation().getX();
            List<Coordinates> cord = getRandomCoordinate();
            do {
                for (Coordinates coord : coords) {
                    if (coord.getX() >= currentX && coord.getY() == currentY) {
                        cord = Collections.singletonList(getLocation().getW());
                    } else if (coord.getX() <= currentX && coord.getY() == currentY) {
                        cord = Collections.singletonList(getLocation().getE());
                    } else if (coord.getX() == currentX && coord.getY() <= currentY) {
                        cord = Collections.singletonList(getLocation().getN());
                    } else if (coord.getX() == currentX && coord.getY() >= currentY) {
                        cord = Collections.singletonList(getLocation().getS());
                    } else if (coord.getX() <= currentX && coord.getY() <= currentY) {
                        cord = Collections.singletonList(getLocation().getNE());
                    } else if (coord.getX() >= currentX && coord.getY() >= currentY) {
                        cord = Collections.singletonList(getLocation().getSW());
                    } else if (coord.getX() >= currentX && coord.getY() <= currentY) {
                        cord = Collections.singletonList(getLocation().getNW());
                    } else if (coord.getX() <= currentX && coord.getY() >= currentY) {
                        cord = Collections.singletonList(getLocation().getSE());
                    }
                }
            }while(!explorer.isObstacle(cord.get(0)) && !explorer.registerBeing(cord.get(0), this));
            }





    protected void eat(List<Coordinates> coords) throws FileNotFoundException {
        Food food = explorer.getFood(coords.get(0));
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

    private Set<Coordinates> whereArePredators(Set<Coordinates> neighbors){
        Set<Coordinates> predators = new HashSet<>();
        for(Coordinates coord : neighbors){
            if(explorer.isPredator(coord)){
                predators.add(coord);
            }
        }
        return predators;
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
