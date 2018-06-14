package model.foodSpawner;

import model.food.Food;

import java.io.FileNotFoundException;
import java.util.Set;

public interface FoodSpawner {

    boolean spawn(Set<Food> foodCollection) throws FileNotFoundException;
}
