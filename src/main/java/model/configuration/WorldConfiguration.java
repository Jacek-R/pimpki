package model.configuration;

public class WorldConfiguration implements Configuration {

    private final int mapWidth;
    private final int mapHeight;
    private final int pimpkiesQuantity;
    private final int foodQuantity;
    private final int obstaclesQuantity;

    // JK: możemy dodać mapę z enumami Genre, tj.: gatunki pimków : ilość do wygenerowania
    // - żeby manipulować ilością pimpków z poszczególnych gatunków

    public WorldConfiguration(int mapWidth, int mapHeight, int pimpkiesQuantity,
                              int foodQuantity, int obstaclesQuantity) {
        this.mapWidth = mapWidth;
        this.mapHeight = mapHeight;
        this.pimpkiesQuantity = pimpkiesQuantity;
        this.foodQuantity = foodQuantity;
        this.obstaclesQuantity = obstaclesQuantity;
    }

    @Override
    public int getMapWidth() {
        return 0;
    }

    @Override
    public int getMapHeight() {
        return 0;
    }

    @Override
    public int getPimkiesQuantity() {
        return 0;
    }

    @Override
    public int getFoodQuantity() {
        return 0;
    }

    @Override
    public int getObstaclesQuantity() {
        return 0;
    }
}
