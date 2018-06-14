package obstacle.obstacleSpawner;

import java.io.FileNotFoundException;

public interface ObstacleSpawner {
    boolean spawn(int obstacleQuantity) throws FileNotFoundException;
}
