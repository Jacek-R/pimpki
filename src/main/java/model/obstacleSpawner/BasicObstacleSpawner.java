package model.obstacleSpawner;

import explorer.WorldManager;
import model.cellcontent.Wall;
import model.coordinates.Coordinates;
import world.Board;

import java.io.FileNotFoundException;

public class BasicObstacleSpawner implements ObstacleSpawner {

    private WorldManager worldManager;
    private Board board;
    private int width;
    private int height;

    public BasicObstacleSpawner(WorldManager worldManager) {
        this.worldManager = worldManager;
    }

    @Override
    public boolean spawn(int obstacleQuantity) throws FileNotFoundException {
        this.board = worldManager.getBoard();
        width = board.getWidth();
        height = board.getHeight();
        for (int i = 0; i < obstacleQuantity; i++) {
            boolean contentPlaced = false;
            int triesLeft = width * height * 10;
            do {
                Coordinates coordinates = worldManager.selectRandomCoordinates();
                if (worldManager.isEmpty(coordinates)) {
                    contentPlaced = worldManager.registerObstacle(coordinates, new Wall());
                }
                triesLeft--;
            } while (!contentPlaced && triesLeft > 0);
        }
        return false;
    }

}
