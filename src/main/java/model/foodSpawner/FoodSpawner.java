package model.foodSpawner;

import model.food.Food;

import java.util.List;

public interface FoodSpawner {

    boolean spawn(List<Food> foodCollection);
}
