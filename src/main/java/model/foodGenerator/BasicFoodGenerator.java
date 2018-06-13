package model.foodGenerator;

import model.configuration.Configuration;
import model.food.Food;

import java.util.List;

public class BasicFoodGenerator implements FoodGenerator {

    private final Configuration configuration;

    public BasicFoodGenerator(Configuration configuration) {
        this.configuration = configuration;
    }

    @Override
    public List<Food> generate() {
        return null;
    }
}
