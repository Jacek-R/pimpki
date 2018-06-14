package food.foodSpawner;

import worldManager.WorldManager;
import coordinates.Coordinates;
import food.foodModel.Food;
import world.Board;

import java.io.FileNotFoundException;
import java.util.Set;

public class BasicFoodSpawner implements FoodSpawner {

    private final WorldManager worldManager;
    private Board board;

    public BasicFoodSpawner(WorldManager worldManager) {
        this.worldManager = worldManager;
    }

    @Override
    public boolean spawn(Set<Food> foodCollection) throws FileNotFoundException {
        this.board = worldManager.getBoard();
        for (int i = 0; i < foodCollection.size(); i++) {
            boolean contentPlaced = false;
            while (!contentPlaced){
                Coordinates coordinates = worldManager.selectRandomCoordinates();
                if (worldManager.isEmpty(coordinates)) {
                    contentPlaced = worldManager.registerFood(coordinates, foodCollection.iterator().next());
                }
            }
        }
        return true;
    }
}
