package model.foodGenerator;

import model.food.Food;

import java.util.Set;

public interface FoodGenerator {

    Set<Food> generate();
}
