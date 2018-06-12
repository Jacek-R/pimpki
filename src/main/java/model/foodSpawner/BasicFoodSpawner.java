package model.foodSpawner;

import model.World;
import model.food.Food;

import java.util.List;

public class BasicFoodSpawner implements FoodSpawner {

    private World world;

    public BasicFoodSpawner(World world) {
        this.world = world;
    }

    @Override
    public boolean spawn(List<Food> foodCollection) {

        return true;
    }
}
