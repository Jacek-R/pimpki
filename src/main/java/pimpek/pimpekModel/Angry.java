package pimpek.pimpekModel;

import cell.CellPaths;
import coordinates.Coordinates;
import pimpek.events.Event;
import worldManager.WorldManager;

import java.io.FileNotFoundException;
import java.util.*;

public class Angry extends SimplePimpek implements Predator {

    private static final String IMAGE_PATH = CellPaths.PREDATOR.getPath();

    public Angry(String name, int energy, int cloningCost, WorldManager explorer) {
        super(name, energy, cloningCost, explorer);
    }

    public Angry(Pimpek ancestor, String name, int energy, int cloningCost, WorldManager explorer) {
        super(ancestor, name, energy, cloningCost, explorer);
    }

    @Override
    public void act() throws FileNotFoundException {
        Set<Coordinates> pimpeks = whereArePimpeks(getFieldOfView(getLocation()));
        Event event = scan(pimpeks);
        switch(event.getType()){
            case MOVE:
                move(event.getCoords());
                break;
            case EAT:
                eat(event.getCoords());
                break;
            case DEFAULT:
                attack(event.getCoords());
                break;
            case WAIT:
                break;

        }

    }

    private void attack(List<Coordinates> coords) throws FileNotFoundException {
        int currentY = getLocation().getY();
        int currentX = getLocation().getX();
        for(Coordinates coord : coords){
            if(checkLife(coord) > getEnergy()) {
                move();
            }else{
                if (coord.getX() >= currentX && coord.getY() == currentY) {
                    move(Collections.singletonList(getLocation().getE()));
                } else if (coord.getX() <= currentX && coord.getY() == currentY) {
                    move(Collections.singletonList(getLocation().getW()));
                } else if (coord.getX() == currentX && coord.getY() <= currentY) {
                    move(Collections.singletonList(getLocation().getS()));
                } else if (coord.getX() == currentX && coord.getY() >= currentY) {
                    move(Collections.singletonList(getLocation().getN()));
                } else if (coord.getX() <= currentX && coord.getY() <= currentY) {
                    move(Collections.singletonList(getLocation().getSW()));
                } else if (coord.getX() >= currentX && coord.getY() >= currentY) {
                    move(Collections.singletonList(getLocation().getNE()));
                } else if (coord.getX() >= currentX && coord.getY() <= currentY) {
                    move(Collections.singletonList(getLocation().getSE()));
                } else if (coord.getX() <= currentX && coord.getY() >= currentY) {
                    move(Collections.singletonList(getLocation().getNW()));
                }
            }
        }
    }

    private int checkLife(Coordinates coord) {
        WorldManager explorer = getExplorer();
        if(explorer.isPredator(coord)){
            return explorer.getPredator(coord).getEnergy();
        }else if(explorer.isPacifist(coord)){
            return explorer.getPacifist(coord).getEnergy();
        }
        return 0;
    }

    @Override
    public String getImagePath() {
        return IMAGE_PATH;
    }


    private Set<Coordinates> whereArePimpeks(Set<Coordinates> neighbors) {
        Set<Coordinates> pimpeks = new HashSet<>();
        for(Coordinates coord : neighbors){
            if(getExplorer().isBeing(coord)) {
                pimpeks.add(coord);
            }
        }
        return pimpeks;
    }

    private Set<Coordinates> getFieldOfView(Coordinates currentLocation) {
        Set<Coordinates> fieldOfView = new HashSet<>();
        fieldOfView.addAll(currentLocation.getNE().getNeighbors());
        fieldOfView.addAll(currentLocation.getNW().getNeighbors());
        fieldOfView.addAll(currentLocation.getSE().getNeighbors());
        fieldOfView.addAll(currentLocation.getSW().getNeighbors());
        fieldOfView.remove(currentLocation);
        return fieldOfView;



    }

}
