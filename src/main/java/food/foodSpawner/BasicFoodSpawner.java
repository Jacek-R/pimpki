package food.foodSpawner;

import worldManager.WorldManager;
import coordinates.Coordinates;
import food.foodModel.Food;

import java.io.FileNotFoundException;
import java.util.Set;

public class BasicFoodSpawner implements FoodSpawner {

    private final WorldManager worldManager;
    private int spawnedFood;

    public BasicFoodSpawner(WorldManager worldManager) {
        this.worldManager = worldManager;
    }

    @Override
    public boolean spawn(Set<Food> foodCollection) throws FileNotFoundException {
        spawnedFood = 0;
        for (Food food: foodCollection) {
            boolean contentPlaced = false;
            while (!contentPlaced){
                Coordinates coordinates = worldManager.selectRandomCoordinates();
                if (worldManager.isEmpty(coordinates)) {
                    spawnedFood++;
                    contentPlaced = worldManager.registerFood(coordinates, food);
                }
            }
        }
        return true;
    }

    @Override
    public int getSpawnedFood() {
        return spawnedFood;
    }
}
