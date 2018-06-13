package model.configuration;

import model.food.FoodGenre;
import model.pimpek.PimpekGenre;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * pojo class
 */

public class WorldConfiguration implements Configuration {

    private final int mapWidth;
    private final int mapHeight;
    private final int obstaclesQuantity;
    private final int cloningCost;
    private final int initialEnergy;
    private final Map<PimpekGenre,Integer> pimpeksQuantity;
    private final Map<FoodGenre,Integer> foodQuantity;

    public WorldConfiguration(int mapWidth, int mapHeight, int obstaclesQuantity, int cloningCost, int initialEnergy) {
        this.mapWidth = mapWidth;
        this.mapHeight = mapHeight;
        this.obstaclesQuantity = obstaclesQuantity;
        this.cloningCost = cloningCost;
        this.initialEnergy = initialEnergy;
        this.pimpeksQuantity = new HashMap<>();
        this.foodQuantity = new HashMap<>();
    }


    @Override
    public int getMapWidth() {
        return mapWidth;
    }

    @Override
    public int getMapHeight() {
        return mapHeight;
    }

    @Override
    public Map<FoodGenre,Integer> getFoodQuantity() {
        if(foodQuantity.size() < 2) {
            return generateDefaultFoodQuanity();
        }
        return foodQuantity;
    }

    @Override
    public Map<PimpekGenre, Integer> getPimpeksQuantity() {
        if(pimpeksQuantity.size() < 2) {
            return generateDefaultPimpeksQuanity();
        }
        return pimpeksQuantity;
    }

    @Override
    public int getObstaclesQuantity() {
        return obstaclesQuantity;
    }

    @Override
    public int getInitialEnergy() {
        return initialEnergy;
    }

    @Override
    public int getCloningCost() {
        return cloningCost;
    }

    @Override
    public void addPimpeksQuantityByGenre(PimpekGenre genre, Integer quantity) {
        if (quantity < 1) {
            throw new IllegalArgumentException("Quantity should be greater than 0!");
        }

        pimpeksQuantity.put(genre, quantity);
    }

    @Override
    public void addFoodQuantityByGenre(FoodGenre genre, Integer quantity) {
        if (quantity < 1) {
            throw new IllegalArgumentException("Quantity should be greater than 0!");
        }

        foodQuantity.put(genre, quantity);
    }

    private Map<PimpekGenre, Integer> generateDefaultPimpeksQuanity() {

        Map<PimpekGenre,Integer> defaultPimpeksQuantity = new HashMap<>();
        int divideFactor = 20;
        int defaultGenreQuantity = mapHeight/divideFactor;
        Arrays.stream(PimpekGenre.values()).forEach(g -> defaultPimpeksQuantity.put(g, defaultGenreQuantity));
        return defaultPimpeksQuantity;
    }

    private Map<FoodGenre,Integer> generateDefaultFoodQuanity() {

        Map<FoodGenre, Integer> defaultFoodQuantity = new HashMap<>();
        int divideFactor = 20;
        int defaultGenreQuantity = mapHeight/divideFactor;
        Arrays.stream(FoodGenre.values()).forEach(f -> defaultFoodQuantity.put(f, defaultGenreQuantity));
        return defaultFoodQuantity;
    }
}
