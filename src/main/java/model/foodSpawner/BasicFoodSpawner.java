package model.foodSpawner;

import explorer.WorldManager;
import model.food.Food;

import java.util.Set;

public class BasicFoodSpawner implements FoodSpawner {

    private WorldManager worldManager;

    public BasicFoodSpawner(WorldManager worldManager) {
        this.worldManager = worldManager;
    }

    @Override
    public boolean spawn(Set<Food> foodCollection) {


        // algorytm wybiera coordynaty (Coordinates)
        // po wybraniu rejetrujemy w worldManager:
        // worldManager.registerFood(food); <-- ta metoda (worldManagera) mogłaby również seterem ustawić
        // Content w danym cell

        return true;
    }
}
