package configuration;

import food.foodModel.FoodGenre;
import pimpek.pimpekModel.PimpekGenre;

import java.util.Map;

public interface Configuration {

    int getMapWidth();
    int getMapHeight();
    int getObstaclesQuantity();
    int getInitialEnergy();
    int getCloningCost();
    void addPimpeksQuantityByGenre(PimpekGenre genre, Integer quantity);
    void addFoodQuantityByGenre(FoodGenre genre, Integer quantity);
    Map<PimpekGenre,Integer> getPimpeksQuantity();
    Map<FoodGenre,Integer> getFoodQuantity();
    int getMatchQuantity();
    int getMaxTurns();

}
