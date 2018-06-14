package food.foodSpawner;

import food.foodModel.Food;

import java.io.FileNotFoundException;
import java.util.Set;

public interface FoodSpawner {

    boolean spawn(Set<Food> foodCollection) throws FileNotFoundException;
}
