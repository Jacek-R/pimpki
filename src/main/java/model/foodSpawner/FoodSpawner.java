package model.foodSpawner;

import model.food.Food;

import java.util.Set;

public interface FoodSpawner {

    boolean spawn(Set<Food> foodCollection);
}
