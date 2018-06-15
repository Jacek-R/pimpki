package food.foodGenerator;

import configuration.Configuration;
import food.foodModel.Apple;
import food.foodModel.Food;
import food.foodModel.FoodGenre;
import food.foodModel.Strawberry;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class BasicFoodGenerator implements FoodGenerator {

    private final Configuration configuration;

    public BasicFoodGenerator(Configuration configuration) {
        this.configuration = configuration;
    }

    @Override
    public Set<Food> generate() {

        Set<Food> assortment = new HashSet<>();

        for (Map.Entry<FoodGenre,Integer> entry : configuration.getFoodQuantity().entrySet() ) {
            for (int i = 0; i < entry.getValue(); i++) {
                
                Food food;
                switch (entry.getKey()) {
                    case APPLE:
                        food = new Apple();
                        break;
                    default:
                        food = new Strawberry();
                        break;
                }
                assortment.add(food);
            }
        }
        return assortment;
    }
}


