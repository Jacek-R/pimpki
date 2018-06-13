package explorer;

import model.cellcontent.Obstacle;
import model.coordinates.Coordinates;
import model.food.Food;
import model.pimpek.Pacifist;
import model.pimpek.Predator;

import java.util.HashMap;
import java.util.Map;

public class MapExplorer implements WorldExplorer {


    private Map<Coordinates,Predator> predators = new HashMap<>();
    private Map<Coordinates,Pacifist> pacifists = new HashMap<>();
    private Map<Coordinates,Food> food = new HashMap<>();
    private Map<Coordinates,Obstacle> obstacles = new HashMap<>();

    @Override
    public boolean isEmpty(Coordinates coordinates) {
        return false;
    }

    @Override
    public boolean isFood(Coordinates coordinates) {
        return food.containsKey(coordinates);
    }

    @Override
    public boolean isObstacle(Coordinates coordinates) {
        return obstacles.containsKey(coordinates);
    }

    @Override
    public boolean isBeing(Coordinates coordinates) {
        return isPredator(coordinates) || isPacifist(coordinates);
    }

    @Override
    public boolean isPredator(Coordinates coordinates) {
        return predators.containsKey(coordinates);
    }

    @Override
    public boolean isPacifist(Coordinates coordinates) {
        return pacifists.containsKey(coordinates);
    }

    @Override
    public Predator getPredator(Coordinates coordinates) {
        return predators.get(coordinates);
    }

    @Override
    public Pacifist getPacifist(Coordinates coordinates) {
        return pacifists.get(coordinates);
    }

    @Override
    public Food getFood(Coordinates coordinates) {
        return this.food.get(coordinates);
    }

    @Override
    public void registerObstacle(Coordinates coordinates, Obstacle obstacle) {
        obstacles.put(coordinates, obstacle);
    }

    @Override
    public void registerPredator(Coordinates coordinates, Predator predator) {
        predators.put(coordinates, predator);
    }

    @Override
    public void registerPacifist(Coordinates coordinates, Pacifist pacifist) {
        pacifists.put(coordinates, pacifist);
    }

    @Override
    public void registerFood(Coordinates coordinates, Food food) {
        this.food.put(coordinates, food);
    }
}
