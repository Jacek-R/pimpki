package configuration;

import food.foodModel.FoodGenre;
import pimpek.pimpekModel.PimpekGenre;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * pojo class
 */

public class WorldConfiguration implements Configuration {

    private final int mapWidth;
    private final int mapHeight;
    private int matchQuantity;
    private final int obstaclesQuantity;
    private final int cloningCost;
    private final int initialEnergy;
    private final int maxTurns;
    private final Map<PimpekGenre,Integer> pimpeksQuantity;
    private final Map<FoodGenre,Integer> foodQuantity;
    private final float LIMITING_BEING_FACTOR = 0.15f;
    private final float LIMITING_OBSTACLE_FACTOR = 0.1f;
    private final float LIMITING_FOOD_FACTOR = 0.15f;

    public static Configuration getInstance(int mapWidth, int mapHeight, int matchQuantity, int obstaclesQuantity,
                                            int cloningCost, int initialEnergy, int maxTurns) {

        if (areConstructorArgumentIncorrect(mapWidth, mapHeight, matchQuantity, obstaclesQuantity,
                cloningCost, initialEnergy, maxTurns) ) {
            throw new IllegalArgumentException("Invalid arguments!");
        }

        return new WorldConfiguration(mapWidth, mapHeight, matchQuantity, obstaclesQuantity,
                cloningCost, initialEnergy, maxTurns);

    }

    private WorldConfiguration(int mapWidth, int mapHeight, int matchQuantity, int obstaclesQuantity,
                              int cloningCost, int initialEnergy, int maxTurns) {
        this.mapWidth = mapWidth;
        this.mapHeight = mapHeight;
        this.matchQuantity = matchQuantity;
        this.obstaclesQuantity = obstaclesQuantity;
        this.cloningCost = cloningCost;
        this.initialEnergy = initialEnergy;
        this.pimpeksQuantity = new HashMap<>();
        this.foodQuantity = new HashMap<>();
        this.maxTurns = maxTurns;
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
    public int getMatchQuantity() {
        return matchQuantity;
    }

    @Override
    public int getMaxTurns() {
        return maxTurns;
    }

    @Override
    public Map<PimpekGenre, Integer> getPimpeksQuantity() {
        if ( pimpeksQuantity.size() < 2 || ! isBeingsQuantityCorrect() ) {
            return generateDefaultPimpeksQuantity();
        }
        return pimpeksQuantity;
    }

    @Override
    public Map<FoodGenre,Integer> getFoodQuantity() {
        if( foodQuantity.size() < 2 || ! isFoodQuantityCorrect() ) {
            return generateDefaultFoodQuantity();
        }
        return foodQuantity;
    }

    @Override
    public int getObstaclesQuantity() {
        if (obstaclesQuantity == 0 || ! isObstacleQuantityCorrect() ) {
            return generateDefaultObstacleQuantity();
        }
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

    private Map<PimpekGenre, Integer> generateDefaultPimpeksQuantity() {

        Map<PimpekGenre,Integer> defaultPimpeksQuantity = new HashMap<>();
        int defaultGenreQuantity = (int) (mapHeight * mapWidth * LIMITING_BEING_FACTOR) / PimpekGenre.values().length;

        Arrays.stream(PimpekGenre.values()).forEach(g -> defaultPimpeksQuantity.put(g, defaultGenreQuantity));
        return defaultPimpeksQuantity;
    }

    private Map<FoodGenre,Integer> generateDefaultFoodQuantity() {

        Map<FoodGenre, Integer> defaultFoodQuantity = new HashMap<>();

        int defaultGenreQuantity = (int) (mapHeight * mapWidth * LIMITING_FOOD_FACTOR) / FoodGenre.values().length;

        Arrays.stream(FoodGenre.values()).forEach(f -> defaultFoodQuantity.put(f, defaultGenreQuantity));
        return defaultFoodQuantity;
    }

    private int generateDefaultObstacleQuantity() {

        return (int) (mapHeight * mapWidth * LIMITING_OBSTACLE_FACTOR);
    }

    private boolean isBeingsQuantityCorrect() {
        int beingsQuantity = pimpeksQuantity.values().stream().mapToInt(Integer::intValue).sum();
        int acceptableQuantity = (int) (mapWidth * mapHeight * LIMITING_BEING_FACTOR);
        return beingsQuantity <= acceptableQuantity;
    }

    private boolean isFoodQuantityCorrect() {
        int currentQuantity = foodQuantity.values().stream().mapToInt(Integer::intValue).sum();
        int acceptableQuantity = (int) (mapWidth * mapHeight * LIMITING_FOOD_FACTOR);
        return currentQuantity <= acceptableQuantity;
    }

    private boolean isObstacleQuantityCorrect() {
        int acceptableQuantity = (int) (mapWidth * mapHeight * LIMITING_OBSTACLE_FACTOR);
        return obstaclesQuantity <= acceptableQuantity;
    }

    private static boolean areConstructorArgumentIncorrect(int mapWidth, int mapHeight, int matchQuantity, int obstaclesQuantity,
                                                  int cloningCost, int initialEnergy, int maxTurns) {

        return mapWidth < 5 || mapHeight < 5 || mapWidth > 100 || mapHeight > 100 || matchQuantity < 1 ||
                matchQuantity > 100 || obstaclesQuantity < 0 ||
                initialEnergy < cloningCost*2 || maxTurns < 0 || maxTurns > 1000;
    }
}
