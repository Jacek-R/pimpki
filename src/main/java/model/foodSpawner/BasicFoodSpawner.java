package model.foodSpawner;

import explorer.WorldExplorer;
import model.food.Food;
import world.World;

import java.util.List;

public class BasicFoodSpawner implements FoodSpawner {

    private World world;
    private final WorldExplorer explorer;

    public BasicFoodSpawner(World world, WorldExplorer explorer) {
        this.world = world;
        this.explorer = explorer;
    }

    @Override
    public boolean spawn(List<Food> foodCollection) {

        // set proper content on field!

        return true;
    }
}
