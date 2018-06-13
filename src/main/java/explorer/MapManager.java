package explorer;

import model.cell.Cell;
import model.cellcontent.Content;
import model.cellcontent.Obstacle;
import model.cellcontent.Wall;
import model.coordinates.Coordinates;
import model.coordinates.Coords;
import model.food.Food;
import model.pimpek.Pacifist;
import model.pimpek.Pimpek;
import model.pimpek.PimpekGenre;
import model.pimpek.Predator;
import world.Board;
import world.ImageParser;

import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class MapManager implements WorldManager {

    private Board board;

    private Map<Coordinates,Predator> predators = new HashMap<>();
    private Map<Coordinates,Pacifist> pacifists = new HashMap<>();
    private Map<Coordinates,Food> food = new HashMap<>();
    private Map<Coordinates,Obstacle> obstacles = new HashMap<>();

    @Override
    public Board getBoard() {
        return board;
    }

    @Override
    public void setBoard(Board board) {
        this.board = board;
    }

    @Override
    public boolean isEmpty(Coordinates coordinates) {
        return !isFood(coordinates) && !isObstacle(coordinates) && !isBeing(coordinates);
    }

    @Override
    public boolean isFood(Coordinates coordinates) {
        return food.containsKey(coordinates);
    }

    @Override
    public boolean isObstacle(Coordinates coordinates) {
        return obstacles.containsKey(coordinates);
    }

    @Override
    public boolean isBeing(Coordinates coordinates) {
        return isPredator(coordinates) || isPacifist(coordinates);
    }

    @Override
    public boolean isPredator(Coordinates coordinates) {
        return predators.containsKey(coordinates);
    }

    @Override
    public boolean isPacifist(Coordinates coordinates) {
        return pacifists.containsKey(coordinates);
    }

    @Override
    public boolean isNeighborhoodEmpty(Coordinates coordinates) {
        return coordinates.getNeighbors().stream().allMatch(this::isEmpty);
    }

    @Override
    public Predator getPredator(Coordinates coordinates) {
        return predators.get(coordinates);
    }

    @Override
    public Pacifist getPacifist(Coordinates coordinates) {
        return pacifists.get(coordinates);
    }

    @Override
    public Food getFood(Coordinates coordinates) {
        return this.food.get(coordinates);
    }

    @Override
    public boolean registerObstacle(Coordinates coordinates, Obstacle obstacle) throws FileNotFoundException {
        if (! areCoordinatesValid(coordinates) ) {
            return false;
        }

        obstacles.put(coordinates, obstacle);

        Cell cell = board.getCellAt(coordinates.getX(), coordinates.getY());
        if (cell == null) {
            return false;
        } else{
            placeObstacle(cell);
        }


        return true;
    }

    @Override
    public boolean registerBeing(Coordinates coordinates, Pimpek pimpek) {

        if (! areCoordinatesValid(coordinates) ) {
            return false;
        }

        PimpekGenre genre = pimpek.getGenre();
        clearPlace(coordinates);

        switch(genre) {
            case PACIFIST:
                pacifists.put(coordinates, (Pacifist) pimpek);
                break;
            case PREDATOR:
                predators.put(coordinates, (Predator) pimpek);
                break;
        }


        Cell cell = board.getCellAt(coordinates.getX(), coordinates.getY());
        if (cell == null) {
            return false;
        }

        // ustaw ceontent w cell

        return true;
    }

    @Override
    public boolean registerFood(Coordinates coordinates, Food food) {

        if (! areCoordinatesValid(coordinates) ) {
            return false;
        }

        this.food.put(coordinates, food);

        Cell cell = board.getCellAt(coordinates.getX(), coordinates.getY());
        if (cell == null) {
            return false;
        }

        // ustaw ceontent w cell

        return true;

    }

    private void placeObstacle(Cell cell) throws FileNotFoundException {
        Content content = new Wall();
        cell.setContent(content);
        cell.getCellView().setContent(ImageParser.getImage(content.getImagePath()));
    }

    @Override
    public void reset() {
        predators.clear();
        pacifists.clear();
        food.clear();
        obstacles.clear();
    }

    private void clearPlace(Coordinates coordinates) {
        if (! isEmpty(coordinates) ) {
            predators.remove(coordinates);
            pacifists.remove(coordinates);
            food.remove(coordinates);
            obstacles.remove(coordinates);
        }
    }

    private boolean areCoordinatesValid(Coordinates coordinates) {
        int x = coordinates.getX();
        int y = coordinates.getY();

        if (x < 0 || y < 0) {
            return false;
        }

        int worldWidth = board.getWidth();
        int worldHeight = board.getHeight();

        if (x > worldWidth || y > worldHeight) {
            return false;
        }

        return true;
    }

    public Coordinates selectRandomCoordinates() {
        Random random = new Random();
        int x = random.nextInt(board.getWidth());
        int y = random.nextInt(board.getHeight());
        return new Coords(x, y);
    }
}
