package model.obstacleSpawner;

import explorer.WorldManager;

public class BasicObstacleSpawner implements ObstacleSpawner {

    private WorldManager worldManager;

    public BasicObstacleSpawner(WorldManager worldManager) {
        this.worldManager = worldManager;
    }

    @Override
    public boolean spawn(int obstacleQuantity) {
        return false;
    }
}
