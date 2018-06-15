package worldManager;

import cell.Cell;
import cell.cellcontent.Content;
import cell.cellcontent.Empty;
import cell.cellcontent.Obstacle;
import cell.cellcontent.Wall;
import coordinates.Coordinates;
import coordinates.Coords;
import food.foodModel.Food;
import pimpek.pimpekModel.Pacifist;
import pimpek.pimpekModel.Pimpek;
import pimpek.pimpekModel.PimpekGenre;
import pimpek.pimpekModel.Predator;
import world.Board;
import world.ImageParser;

import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import static cell.CellPaths.BLOOD;

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
//        resetMaps();  // todo
    }

    @Override
    public boolean isEmpty(Coordinates coordinates) {
        return !hasFood(coordinates) && !hasObstacle(coordinates) && !hasBeing(coordinates);
    }

    @Override
    public boolean hasFood(Coordinates coordinates) {
        return food.containsKey(coordinates);
    }

    @Override
    public boolean hasObstacle(Coordinates coordinates) {
        return obstacles.containsKey(coordinates);
    }

    @Override
    public boolean hasBeing(Coordinates coordinates) {
        return (hasPredator(coordinates) || hasPacifist(coordinates));
    }

    @Override
    public boolean hasPredator(Coordinates coordinates) {
        return predators.containsKey(coordinates);
    }

    @Override
    public boolean hasPacifist(Coordinates coordinates) {
        return pacifists.containsKey(coordinates);
    }

    @Override
    public boolean isNeighborhoodEmpty(Coordinates coordinates) {
        return coordinates.getNeighbors().stream().allMatch(this::isEmpty);
    }

    @Override
    public boolean areCoordinatesOnMap(Coordinates coordinates) {
        int x = coordinates.getX();
        int y = coordinates.getY();

        if (x < 0 || y < 0) {
            return false;
        }

        int worldWidth = board.getWidth();
        int worldHeight = board.getHeight();

        return x < worldWidth && y < worldHeight;
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
    public void cleanUpPlace(Coordinates coordinates) throws FileNotFoundException {
        clearPlace(coordinates);
        Cell cell = board.getCellAt(coordinates.getX(), coordinates.getY());
        Content newContent = new Empty();
        cell.setContent(newContent);
        cell.getCellView().setBackground(ImageParser.getImage(BLOOD.getPath()));
        cell.getCellView().setContent(ImageParser.getImage(newContent.getImagePath()));
    }

    @Override
    public boolean registerObstacle(Coordinates coordinates, Obstacle obstacle) throws FileNotFoundException {
        if (! areCoordinatesOnMap(coordinates) ) {
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
    public boolean registerBeing(Coordinates coordinates, Pimpek pimpek) throws FileNotFoundException {

        if (! areCoordinatesOnMap(coordinates) ) {
            return false;
        }

        PimpekGenre genre = pimpek.getGenre();

        // clean old place
        clearPlace(pimpek.getLocation());

        // clean up new place
        clearPlace(coordinates);

        switch(genre) {
            case PACIFIST:
                pacifists.put(coordinates, (Pacifist) pimpek);
                break;
            case PREDATOR:
                predators.put(coordinates, (Predator) pimpek);
                break;
        }

        pimpek.setLocation(coordinates);

        Cell cell = board.getCellAt(coordinates.getX(), coordinates.getY());
        if (cell == null) {
            return false;
        } else {
            placePimpek(cell, pimpek);
        }

        return true;
    }

    @Override
    public boolean registerFood(Coordinates coordinates, Food newFood) throws FileNotFoundException{

        if (! areCoordinatesOnMap(coordinates) ) {
            return false;
        }

        food.put(coordinates, newFood);

        Cell cell = board.getCellAt(coordinates.getX(), coordinates.getY());
        if (cell == null) {
            return false;
        } else {
            placeFood(cell, newFood);
        }

        return true;
    }

    private void placePimpek(Cell cell, Pimpek pimpek) throws FileNotFoundException {
        cell.setContent(pimpek);
        cell.getCellView().setContent(ImageParser.getImage(pimpek.getImagePath()));
    }

    private void placeFood(Cell cell, Food food) throws FileNotFoundException {
        cell.setContent(food);
        cell.getCellView().setContent(ImageParser.getImage(food.getImagePath()));
    }

    private void placeObstacle(Cell cell) throws FileNotFoundException {
        Content content = new Wall();
        cell.setContent(content);
        cell.getCellView().setContent(ImageParser.getImage(content.getImagePath()));
    }

    private void resetMaps() {
        predators.clear();
        pacifists.clear();
        food.clear();
        obstacles.clear();
    }

    @Override
    public Coordinates selectRandomCoordinates() {
        Random random = new Random();
        int x = random.nextInt(board.getWidth());
        int y = random.nextInt(board.getHeight());
        return new Coords(x, y);
    }

    private void clearPlace(Coordinates coordinates) throws FileNotFoundException {
        if (! isEmpty(coordinates) ) {
            predators.remove(coordinates);
            pacifists.remove(coordinates);
            food.remove(coordinates);
            obstacles.remove(coordinates);
        }

        Cell oldCell = board.getCellAt(coordinates.getX(), coordinates.getY());
        Content newContent = new Empty();
        oldCell.setContent(newContent);
        oldCell.getCellView().setContent(ImageParser.getImage(newContent.getImagePath()));
    }
}
