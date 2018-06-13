package explorer;

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

//    boolean registerObstacle(Coo)

    boolean registerPredator(Coordinates coordinates, Predator predator);
    boolean registerPacifist(Coordinates coordinates, Pacifist pacifist);
    boolean registerFood(Coordinates coordinates, Food food);

}
