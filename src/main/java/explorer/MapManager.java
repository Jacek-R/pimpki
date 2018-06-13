package explorer;

import model.cellcontent.Obstacle;
import model.coordinates.Coordinates;
import model.food.Food;
import model.pimpek.Pacifist;
import model.pimpek.Pimpek;
import model.pimpek.PimpekGenre;
import model.pimpek.Predator;

import java.util.HashMap;
import java.util.Map;

public class MapManager implements WorldManager {

    private Map<Coordinates,Predator> predators = new HashMap<>();
    private Map<Coordinates,Pacifist> pacifists = new HashMap<>();
    private Map<Coordinates,Food> food = new HashMap<>();
    private Map<Coordinates,Obstacle> obstacles = new HashMap<>();

    @Override
    public boolean isEmpty(Coordinates coordinates) {
        return isFood(coordinates) || isObstacle(coordinates) || isBeing(coordinates);
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
    public boolean isNeighborhoodEmpty(Coordinates coordinates) {
        return coordinates.getNeighbors().stream().allMatch(this::isEmpty);
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
    public void registerBeing(Coordinates coordinates, Pimpek pimpek) {

        PimpekGenre genre = pimpek.getGenre();
        clearPlace(coordinates);

        switch(genre) {
            case PACIFIST:
                pacifists.put(coordinates, (Pacifist) pimpek);
                break;
            case PREDATOR:
                predators.put(coordinates, (Predator) pimpek);
                break;
        }
    }

    @Override
    public void registerFood(Coordinates coordinates, Food food) {
        this.food.put(coordinates, food);
    }

    @Override
    public void reset() {
        predators.clear();
        pacifists.clear();
        food.clear();
        obstacles.clear();
    }

    private void clearPlace(Coordinates coordinates) {
        if (! isEmpty(coordinates) ) {
            predators.remove(coordinates);
            pacifists.remove(coordinates);
            food.remove(coordinates);
            obstacles.remove(coordinates);
        }
    }
}
