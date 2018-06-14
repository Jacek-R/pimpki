package worldManager;

import cell.cellcontent.Obstacle;
import coordinates.Coordinates;
import food.foodModel.Food;
import pimpek.pimpekModel.Pacifist;
import pimpek.pimpekModel.Pimpek;
import pimpek.pimpekModel.Predator;
import world.Board;

import java.io.FileNotFoundException;

public interface WorldManager {

    Board getBoard();
    void setBoard(Board board);

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

    boolean registerObstacle(Coordinates coordinates, Obstacle obstacle) throws FileNotFoundException;
    boolean registerBeing(Coordinates coordinates, Pimpek being) throws FileNotFoundException;
    boolean registerFood(Coordinates coordinates, Food food) throws FileNotFoundException;

    void reset();
    Coordinates selectRandomCoordinates();
}
