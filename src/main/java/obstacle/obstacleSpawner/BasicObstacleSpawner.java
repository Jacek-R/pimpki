package obstacle.obstacleSpawner;

import cell.cellcontent.Empty;
import coordinates.Coords;
import worldManager.WorldManager;
import cell.cellcontent.Wall;
import coordinates.Coordinates;
import world.Board;

import java.io.FileNotFoundException;
import java.util.Random;

public class BasicObstacleSpawner implements ObstacleSpawner {

    private final WorldManager worldManager;
    private Board board;
    private int boardWidth;
    private int boardHeight;

    public BasicObstacleSpawner(WorldManager worldManager) {
        this.worldManager = worldManager;
    }

    private enum Direction {DOWN, RIGHT}

    @Override
    public boolean spawn(int obstacleQuantity) throws FileNotFoundException {
        board = worldManager.getBoard();
        boardWidth = board.getWidth();
        boardHeight = board.getHeight();
        int structuresToBuild = 100;

        createWalls();
        createStartingRoom();
        fillWithStructures(structuresToBuild);

        return true;
    }

    private void fillWithStructures(int structuresLeft) throws FileNotFoundException {
        Random rng = new Random();
        for (int i = 0; i < structuresLeft; i++) {
            int tries = 500000;
            boolean structureNotPlaced = true;
            Direction direction = rng.nextInt(Direction.values().length) == 0 ? Direction.DOWN : Direction.RIGHT;
            Structure structure = selectStructure(direction);

            for (int j = 0; j < tries && structureNotPlaced; j++) {
                Coordinates coordinates = worldManager.selectRandomCoordinates();

                if (board.getCellAt(coordinates.getX(), coordinates.getY()).getContent() instanceof Empty) {
                    Coordinates neighbourCoordinates = direction == Direction.DOWN ? coordinates.getS() : coordinates.getE();
                    Coordinates structureStart = neighbourCoordinates;

                    if (structure instanceof Room) {
                        structureStart = direction == Direction.DOWN ? neighbourCoordinates.getS() : neighbourCoordinates.getE();
                    }

                    if (structure.canBePlaced(structureStart)) {
                        structure.fill(structureStart);
                        structureNotPlaced = false;
                        if (structure instanceof Room) {
                            worldManager.cleanUpPlace(neighbourCoordinates);
                        }
                    }
                }
            }
        }
    }

    private void createStartingRoom() throws FileNotFoundException {
        int START_X = 1;
        int START_Y = 1;
        int WIDTH = 5;
        int HEIGHT = 5;

        Coordinates startingRoomCoordinates = new Coords(START_X, START_Y);
        Structure room = new Room(WIDTH, HEIGHT);
        if (room.canBePlaced(startingRoomCoordinates)) {
            room.fill(startingRoomCoordinates);
        }
    }

    private void createWalls() throws FileNotFoundException {
        for (int x = 0; x < boardWidth; x++) {
            for (int y = 0; y < boardHeight; y++) {
                Coordinates coordinates = new Coords(x, y);
                worldManager.registerObstacle(coordinates, new Wall());
            }
        }
    }

    private Structure selectStructure(Direction direction) {
        Structure structure;
        Random rng = new Random();
        int RANGE = 100;
        int CORRIDOR_CHANCE = 75;

        int number = rng.nextInt(RANGE);

        if (number < CORRIDOR_CHANCE) {
            int CORRIDOR_MAX = 10;
            int CORRIDOR_MIN = 1;

            int corridorLength = rng.nextInt(CORRIDOR_MAX - CORRIDOR_MIN + 1) + CORRIDOR_MIN;
            structure = direction == Direction.DOWN ? new VerticalCorridor(corridorLength) : new HorizontalCorridor(corridorLength);
        } else {
            int width = rng.nextInt(Room.MAX_WIDTH - Room.MIN_WIDTH + 1) + Room.MIN_WIDTH;
            int height = rng.nextInt(Room.MAX_HEIGHT - Room.MIN_HEIGHT + 1) + Room.MIN_HEIGHT;
            structure = new Room(width, height);
        }
        return structure;
    }

    private class Structure {

        private int width;
        private int height;

        Structure(int width, int height, int minWidth, int maxWidth, int minHeight, int maxHeight) {
            ensureCorrectBounds(minWidth, maxWidth, minHeight, maxHeight);
            this.width = setDimension(width, minWidth, maxWidth);
            this.height = setDimension(height, minHeight, maxHeight);
        }

        private void ensureCorrectBounds(int minWidth, int maxWidth, int minHeight, int maxHeight) {
            if (minWidth > maxWidth || minHeight > maxHeight) {
                throw new RuntimeException("The bounds are incorrect. Minimum value is larger than maximum or Maximum is smaller than Minimum");
            }
        }

        private int setDimension(int actualSize, int minSize, int maxSize) {
            if (actualSize < minSize) {
                actualSize = minSize;
            } else if (actualSize > maxSize) {
                actualSize = maxSize;
            }
            return actualSize;
        }

        public boolean canBePlaced(Coordinates coordinates) {
            boolean canPlace = true;
            boolean continueSearch = true;
            for (int x = coordinates.getX() - 1; x < coordinates.getX() + getWidth() + 1 && continueSearch; x++) {
                for (int y = coordinates.getY() - 1; y < coordinates.getY() + getHeight() + 1 && continueSearch; y++) {
                    if (isTileUnavailable(x, y)) {
                        continueSearch = false;
                        canPlace = false;
                    }
                }
            }
            return canPlace;
        }

        boolean isTileUnavailable(int x, int y) {
            boolean unavailable = true;
            try {
                if (board.getCellAt(x, y).getContent() instanceof Wall) {
                    unavailable = false;
                }
            } catch (ArrayIndexOutOfBoundsException e) {
            }
            return unavailable;
        }

        void fill(Coordinates coordinates) throws FileNotFoundException {
            for (int x = coordinates.getX(); x < coordinates.getX() + getWidth(); x++) {
                for (int y = coordinates.getY(); y < coordinates.getY() + getHeight(); y++) {
                    worldManager.cleanUpPlace(new Coords(x, y));
                }
            }
        }

        int getWidth() {
            return width;
        }


        int getHeight() {
            return height;
        }

    }

    private class Room extends Structure {
        private static final int MIN_WIDTH = 3;
        private static final int MIN_HEIGHT = 3;
        private static final int MAX_WIDTH = 6;
        private static final int MAX_HEIGHT = 6;

        Room(int width, int height) {
            super(width, height, MIN_WIDTH, MAX_WIDTH, MIN_HEIGHT, MAX_HEIGHT);
        }
    }

    private class HorizontalCorridor extends Structure {
        private static final int MIN_WIDTH = 1;
        private static final int MIN_HEIGHT = 1;
        private static final int MAX_WIDTH = 10;
        private static final int MAX_HEIGHT = 1;

        HorizontalCorridor(int length) {
            super(length, MIN_HEIGHT, MIN_WIDTH, MAX_WIDTH, MIN_HEIGHT, MAX_HEIGHT);
        }

        @Override
        public boolean canBePlaced(Coordinates coordinates) {
            boolean canPlace = true;
            boolean continueSearch = true;
            for (int x = coordinates.getX(); x < coordinates.getX() + getWidth() && continueSearch; x++) {
                for (int y = coordinates.getY() - 1; y < coordinates.getY() + getHeight() + 1 && continueSearch; y++) {
                    if (isTileUnavailable(x, y)) {
                        canPlace = false;
                        continueSearch = false;
                    }
                }
            }
            return canPlace;
        }
    }

    private class VerticalCorridor extends Structure {
        private static final int MIN_WIDTH = 1;
        private static final int MIN_HEIGHT = 1;
        private static final int MAX_WIDTH = 1;
        private static final int MAX_HEIGHT = 10;

        VerticalCorridor(int length) {
            super(MIN_WIDTH, length, MIN_WIDTH, MAX_WIDTH, MIN_HEIGHT, MAX_HEIGHT);
        }

        @Override
        public boolean canBePlaced(Coordinates coordinates) {
            boolean canPlace = true;
            boolean continueSearch = true;
            for (int x = coordinates.getX() - 1; x < coordinates.getX() + getWidth() + 1 && continueSearch; x++) {
                for (int y = coordinates.getY(); y < coordinates.getY() + getHeight() && continueSearch; y++) {
                    if (isTileUnavailable(x, y)) {
                        canPlace = false;
                        continueSearch = false;
                    }
                }
            }
            return canPlace;
        }
    }
}
