package model.pimpek;

import explorer.WorldExplorer;
import model.cell.Cell;
import model.cellcontent.Type;
import model.coordinates.Coordinates;
import model.events.BasicEvent;
import model.events.Event;
import model.observer.MatchObserver;
import model.observer.NullObserver;
import world.Board;

import java.util.ArrayList;
import java.util.List;

/**
 * basic genre - a bit stupid
 */

public class SimplePimpek implements Pacifist {

    private final Pimpek ancestor;  // use it to update statistic (observer)
    private static final String IMAGE_PATH = "src/main/resources/img/pimpek.png";
    private static final Type TYPE = Type.PIMPEK;
    private final String name;
    private Coordinates currentLocation;
    private int energy;
    private final int cloningCost;
    private MatchObserver observer = new NullObserver();  // null object pattern to avoid null pointer ex
    private final int totalEnergy;
    private final WorldExplorer explorer;


    // constructor for origin/root pimpek:
    public SimplePimpek(String name, int energy, int cloningCost, WorldExplorer explorer) {
        this.ancestor = this;
        this.name = name;
        this.energy = energy;
        this.cloningCost = cloningCost;
        this.totalEnergy = energy;
        this.explorer = explorer;
    }

    // constructor for clones:
    public SimplePimpek(Pimpek ancestor, String name, int energy, int cloningCost, WorldExplorer explorer) {
        this.ancestor = ancestor;
        this.name = name;
        this.energy = energy;
        this.cloningCost = cloningCost;
        this.totalEnergy = energy;
        this.explorer = explorer;
    }

    @Override
    public void act(Board world) {
        Event event = scan(world);
        switch(event.getName()){
            case "EAT":
                eat()

        }


    }

    private Event scan(Board world) {
        List<Coordinates> fieldOfView = getFieldOfView();
        Event event = new BasicEvent("WAIT", currentLocation);
        for(Coordinates coord : fieldOfView){
            if(explorer.isFood(coord)){
                 event = new BasicEvent("EAT", coord);
                 return event;
            }else if(explorer.isPredator(coord)){
                event = new BasicEvent("RUN", coord);
                return event;
            }else if(!explorer.isObstacle(coord)){
                event = new BasicEvent("MOVE", coord);
            }
        }
        return event;
    }

    private List<Coordinates> getFieldOfView() {
        List<Coordinates> coords = new ArrayList<>();
        coords.add(currentLocation.getE());
        coords.add(currentLocation.getNE());
        coords.add(currentLocation.getS());
        coords.add(currentLocation.getSE());
        coords.add(currentLocation.getSW());
        coords.add(currentLocation.getW());
        coords.add(currentLocation.getNW());
        coords.add(currentLocation.getN());

        return coords;
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

    protected MatchObserver getObserver() {
        return observer;
    }

    protected Pimpek getAncestor() {
        return ancestor;
    }

    protected int getCloningCost() {
        return cloningCost;
    }

    protected int getTotalEnergy() {
        return totalEnergy;
    }

    protected WorldExplorer getExplorer() {
        return explorer;
    }

    @Override
    public String getImagePath() {
        return IMAGE_PATH;
    }

    @Override
    public Type getType() {
        return TYPE;
    }

    @Override
    public boolean isAccessible() {
        return true;
    }
}
