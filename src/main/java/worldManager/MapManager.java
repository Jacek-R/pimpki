package worldManager;

import cell.Cell;
import cell.cellcontent.Content;
import cell.cellcontent.Obstacle;
import cell.cellcontent.Wall;
import coordinates.Coordinates;
import coordinates.Coords;
import food.foodModel.Apple;
import food.foodModel.Food;
import food.foodModel.FoodGenre;
import food.foodModel.Strawberry;
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
    public boolean registerBeing(Coordinates coordinates, Pimpek pimpek) throws FileNotFoundException {

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
        } else {
            placePimpek(cell, pimpek);
        }

        return true;
    }

    @Override
    public boolean registerFood(Coordinates coordinates, Food newFood) throws FileNotFoundException{

        if (! areCoordinatesValid(coordinates) ) {
            return false;
        }

        FoodGenre foodGenre = newFood.getGenre();


        switch(foodGenre) {
            case APPLE:
                newFood = (Apple)newFood;
                break;
            case STRAWBERRY:
                newFood = (Strawberry)newFood ;
                break;
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
        Content content = pimpek;
        cell.setContent(pimpek);
        cell.getCellView().setContent(ImageParser.getImage(content.getImagePath()));
    }

    private void placeFood(Cell cell, Food food) throws FileNotFoundException {
        Content content = food;
        cell.setContent(food);
        cell.getCellView().setContent(ImageParser.getImage(content.getImagePath()));
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
