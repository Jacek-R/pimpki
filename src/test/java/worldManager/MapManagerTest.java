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
import world.Board;

import java.io.FileNotFoundException;

import static org.junit.Assert.*;

public class MapManagerTest {


    private WorldManager worldManager;

    @Before
    public void setUp() throws FileNotFoundException {

        DIFactory diContainer = BasicDIFactory
                .getInstance(WorldConfiguration.getInstance(10, 10, 3,
                        10, 120, 100, 10) );

        worldManager = diContainer.getWorldManager();
        Board board = diContainer.getBoardCreator().create();
        worldManager.setBoard(board);

    }


    @Test
    public void isEmpty() throws FileNotFoundException {

        Obstacle wall = new Wall();
        Food apple = new Apple();
        Food strawberry = new Strawberry();

        Coordinates coordinates01 = new Coords(0,1);

        worldManager.registerObstacle(coordinates01, wall);

        assertFalse(worldManager.isEmpty(coordinates01));


    }



}