package worldManager;

import cell.cellcontent.Obstacle;
import cell.cellcontent.Wall;
import configuration.Configuration;
import configuration.WorldConfiguration;
import coordinates.Coordinates;
import coordinates.Coords;
import dependencyFactory.BasicDIFactory;
import dependencyFactory.DIFactory;
import food.foodModel.Apple;
import food.foodModel.Food;
import food.foodModel.Strawberry;
import org.junit.Before;
import org.junit.Test;
import pimpek.pimpekModel.Angry;
import pimpek.pimpekModel.Predator;
import world.Board;

import java.io.FileNotFoundException;
import java.util.HashSet;
import java.util.Set;

import static org.junit.Assert.*;

public class MapManagerTest {


    private WorldManager worldManager;
    private DIFactory diContainer;

    @Before
    public void setUp() throws FileNotFoundException {

        Configuration configuration = WorldConfiguration.getInstance(10, 10, 3,
                10, 120, 100, 10);

        diContainer = BasicDIFactory.getInstance(configuration);

        worldManager = diContainer.getWorldManager();
        Board board = diContainer.getBoardCreator().create();
        worldManager.setBoard(board);
    }


    @Test
    public void isEmpty() throws FileNotFoundException {

        Obstacle wall = new Wall();
        Food apple = new Apple();
        Predator predator = new Angry("Wrrr", 100, 50, worldManager);


        Coordinates wallCoordinates = new Coords(0,1);
        Coordinates appleCoordinates = new Coords(3,9);

        Coordinates predatorCoordinates = new Coords(5,5);
        predator.setLocation(predatorCoordinates);

        worldManager.registerObstacle(wallCoordinates, wall);
        worldManager.registerFood(appleCoordinates, apple);
        worldManager.registerBeing(predatorCoordinates, predator);

        assertFalse(worldManager.isEmpty(wallCoordinates));
        assertFalse(worldManager.isEmpty(appleCoordinates));
        assertFalse(worldManager.isEmpty(predatorCoordinates));

        Set<Coordinates> coordinates = new HashSet<>();
        Set<String> strings = new HashSet<>();

        for (int i=0; i<1000; i++) {
            System.out.println(new Coords(0, 1).hashCode());
            coordinates.add(new Coords(0, 1));
            strings.add("mama");
        }

        System.out.println(coordinates.size());
        System.out.println(strings.size());


        for (int i=0; i<100; i++) {
            assertFalse(worldManager.isEmpty(new Coords(0, 1)));
            assertFalse(worldManager.isEmpty(new Coords(3, 9)));
            assertFalse(worldManager.isEmpty(new Coords(5, 5)));
        }

    }
//
//    Food apple = new Apple();
//    Food strawberry = new Strawberry();


}