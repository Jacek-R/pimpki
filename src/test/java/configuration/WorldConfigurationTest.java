package configuration;

import food.foodModel.FoodGenre;
import org.junit.Test;
import pimpek.pimpekModel.PimpekGenre;

import java.util.Map;

import static org.junit.Assert.*;

public class WorldConfigurationTest {

    private Integer obstacleQuantity = 3;

    private Configuration configuration = WorldConfiguration.getInstance(10, 10, 1,
            obstacleQuantity, 50, 100, 1);

    @Test
    public void checkFoodQuantity_if_correct() {
        Integer appleQuantity = 2;
        Integer strawberryQuantity = 3;

        configuration.addFoodQuantityByGenre(FoodGenre.APPLE, appleQuantity);
        configuration.addFoodQuantityByGenre(FoodGenre.STRAWBERRY, strawberryQuantity);

        Map<FoodGenre,Integer> mapQuantity = configuration.getFoodQuantity();
        int quantity = mapQuantity.values().stream().mapToInt(Integer::intValue).sum();


        assertEquals(appleQuantity, mapQuantity.get(FoodGenre.APPLE));
        assertEquals(strawberryQuantity, mapQuantity.get(FoodGenre.STRAWBERRY));

        assertEquals(appleQuantity+strawberryQuantity, quantity);

    }


    @Test
    public void checkBeingsQuantity_if_correct() {
        Integer pacifistQuantity = 2;
        Integer predatorQuantity = 3;

        configuration.addPimpeksQuantityByGenre(PimpekGenre.PACIFIST, pacifistQuantity);
        configuration.addPimpeksQuantityByGenre(PimpekGenre.PREDATOR, predatorQuantity);

        Map<PimpekGenre,Integer> mapQuantity = configuration.getPimpeksQuantity();
        int quantity = mapQuantity.values().stream().mapToInt(Integer::intValue).sum();

        assertEquals(pacifistQuantity, mapQuantity.get(PimpekGenre.PACIFIST));
        assertEquals(predatorQuantity, mapQuantity.get(PimpekGenre.PREDATOR));

        assertEquals(pacifistQuantity+predatorQuantity, quantity);

    }



    @Test
    public void checkObstacleQuantity_if_correct() {

        assertEquals(obstacleQuantity, (Integer) configuration.getObstaclesQuantity());

    }

    @Test
    public void checkFoodQuantity_using_incorrect_arguments() {
        Integer appleQuantity = 200;
        Integer strawberryQuantity = 5;

        configuration.addFoodQuantityByGenre(FoodGenre.APPLE, appleQuantity);
        configuration.addFoodQuantityByGenre(FoodGenre.STRAWBERRY, strawberryQuantity);

        Map<FoodGenre,Integer> mapQuantity = configuration.getFoodQuantity();


        assertNotSame(appleQuantity, mapQuantity.get(FoodGenre.APPLE));
        assertNotSame(strawberryQuantity, mapQuantity.get(FoodGenre.STRAWBERRY));
        
    }


    @Test
    public void checkBeingsQuantity_using_incorrect_arguments() {
        Integer pacifistQuantity = 2;
        Integer predatorQuantity = 300;

        configuration.addPimpeksQuantityByGenre(PimpekGenre.PACIFIST, pacifistQuantity);
        configuration.addPimpeksQuantityByGenre(PimpekGenre.PREDATOR, predatorQuantity);

        Map<PimpekGenre,Integer> mapQuantity = configuration.getPimpeksQuantity();

        assertNotSame(pacifistQuantity, mapQuantity.get(PimpekGenre.PACIFIST));
        assertNotSame(predatorQuantity, mapQuantity.get(PimpekGenre.PREDATOR));

    }



    @Test
    public void checkObstacleQuantity_using_incorrect_arguments() {
        Integer obstaclesQuantity = 100;
        Configuration con = WorldConfiguration.getInstance(10, 10, 1,
                obstaclesQuantity, 50, 100, 1);

        assertNotSame(obstaclesQuantity, con.getObstaclesQuantity());

    }


    @Test(expected = IllegalArgumentException.class)
    public void createConfigurationUsingInvalidArguments() throws IllegalArgumentException {
        Configuration con = WorldConfiguration.getInstance(-1, 10, 1,
                20, 100, 100, -1);
    }




}