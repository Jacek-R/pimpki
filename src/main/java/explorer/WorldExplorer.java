package explorer;

import model.coordinates.Coordinates;

public interface WorldExplorer {


    boolean isEmpty(Coordinates coordinates);
    boolean isFood(Coordinates coordinates);
    boolean isObstacle(Coordinates coordinates);
    boolean isBeing(Coordinates coordinates);
    boolean isPredator(Coordinates coordinates);
    boolean isPacifist(Coordinates coordinates);

}
