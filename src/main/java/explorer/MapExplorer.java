package explorer;

import model.configuration.Configuration;
import model.coordinates.Coordinates;
import model.food.Food;
import model.pimpek.Pacifist;
import model.pimpek.Predator;

import java.util.HashMap;
import java.util.Map;

public class MapExplorer implements WorldExplorer {


    private Map<Coordinates,Predator> predators = new HashMap<>();
    private Map<Coordinates,Pacifist> pacifist = new HashMap<>();
    private Map<Coordinates,Food> obstacles = new HashMap<>();




    @Override
    public boolean isEmpty(Coordinates coordinates) {
        return false;
    }

    @Override
    public boolean isFood(Coordinates coordinates) {
        return false;
    }

    @Override
    public boolean isObstacle(Coordinates coordinates) {
        return false;
    }

    @Override
    public boolean isBeing(Coordinates coordinates) {
        return false;
    }

    @Override
    public boolean isPredator(Coordinates coordinates) {
        return false;
    }

    @Override
    public boolean isPacifist(Coordinates coordinates) {
        return false;
    }

    @Override
    public Predator getPredator(Coordinates coordinates) {
        return null;
    }

    @Override
    public Pacifist getPacifist(Coordinates coordinates) {
        return null;
    }

    @Override
    public Food getFood(Coordinates coordinates) {
        return null;
    }
}
