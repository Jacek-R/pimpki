package explorer;

import model.cellcontent.Obstacle;
import model.coordinates.Coordinates;
import model.food.Food;
import model.pimpek.Pacifist;
import model.pimpek.Pimpek;
import model.pimpek.Predator;

public interface WorldManager {


    boolean isEmpty(Coordinates coordinates);
    boolean isFood(Coordinates coordinates);
    boolean isObstacle(Coordinates coordinates);
    boolean isBeing(Coordinates coordinates);
    boolean isPredator(Coordinates coordinates);
    boolean isPacifist(Coordinates coordinates);
    boolean isNeighborhoodEmpty(Coordinates coordinates);

    Predator getPredator(Coordinates coordinates);
    Pacifist getPacifist(Coordinates coordinates);
    Food getFood(Coordinates coordinates);

    boolean registerObstacle(Coordinates coordinates, Obstacle obstacle);
    boolean registerBeing(Coordinates coordinates, Pimpek being);
    boolean registerFood(Coordinates coordinates, Food food);

    void reset();
}
