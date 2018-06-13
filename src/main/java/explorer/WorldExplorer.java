package explorer;

import model.cellcontent.Obstacle;
import model.coordinates.Coordinates;
import model.food.Food;
import model.pimpek.Pacifist;
import model.pimpek.Predator;

public interface WorldExplorer {


    boolean isEmpty(Coordinates coordinates);
    boolean isFood(Coordinates coordinates);
    boolean isObstacle(Coordinates coordinates);
    boolean isBeing(Coordinates coordinates);
    boolean isPredator(Coordinates coordinates);
    boolean isPacifist(Coordinates coordinates);

    Predator getPredator(Coordinates coordinates);
    Pacifist getPacifist(Coordinates coordinates);
    Food getFood(Coordinates coordinates);

    void registerObstacle(Coordinates coordinates, Obstacle obstacle);
    void registerPredator(Coordinates coordinates, Predator predator);
    void registerPacifist(Coordinates coordinates, Pacifist pacifist);
    void registerFood(Coordinates coordinates, Food food);

}
