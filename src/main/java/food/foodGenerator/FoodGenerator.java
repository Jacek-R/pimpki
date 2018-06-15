package food.foodGenerator;

import food.foodModel.Food;

import java.util.Set;

public interface FoodGenerator {

    Set<Food> generate();
}
