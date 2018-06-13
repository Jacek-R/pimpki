package model.foodSpawner;

import model.food.Food;
import world.World;

import java.util.List;

public class BasicFoodSpawner implements FoodSpawner {

    private World world;

    public BasicFoodSpawner(World world) {
        this.world = world;
    }

    @Override
    public boolean spawn(List<Food> foodCollection) {

        // set proper content on field!

        return true;
    }
}
