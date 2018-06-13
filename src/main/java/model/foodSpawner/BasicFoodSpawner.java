package model.foodSpawner;

import explorer.WorldManager;
import model.food.Food;
import world.World;

import java.util.List;

public class BasicFoodSpawner implements FoodSpawner {

    private final WorldManager worldManager;

    public BasicFoodSpawner(WorldManager worldManager) {
        this.worldManager = worldManager;
    }

    @Override
    public boolean spawn(List<Food> foodCollection) {


        // algorytm wybiera coordynaty (Coordinates)
        // po wybraniu rejetrujemy w worldManager:
        // worldManager.registerFood(food); <-- ta metoda (worldManagera) mogłaby również seterem ustawić
        // Content w danym cell

        return true;
    }
}
