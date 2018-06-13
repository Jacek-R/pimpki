package world;

import explorer.WorldExplorer;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import model.cell.BasicCell;
import model.cell.Cell;
import model.cell.CellView;
import model.cellcontent.Content;
import model.cellcontent.Empty;
import model.cellcontent.Type;
import model.cellcontent.Wall;
import model.configuration.Configuration;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Random;

public class WorldCreator implements BoardCreator {

    private int height;
    private int width;
    private final WorldExplorer explorer;
    private final Configuration configuration;

    public WorldCreator(WorldExplorer explorer, Configuration configuration) {
        this.explorer = explorer;
        this.configuration = configuration;
        this.height = configuration.getMapHeight();
        this.width = configuration.getMapWidth();
    }

    @Override
    public Board create() throws FileNotFoundException {
        Cell[][] cells = createEmptyCells();
        populateWithElements(cells, Type.WALL);
//        populateWithElements(cells, Type.FOOD);
        return new World(width, height, cells);
    }

    private Cell[][] createEmptyCells() throws FileNotFoundException {
        Cell[][] cells = new Cell[width][height];
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                Content content = new Empty();
                cells[i][j] = new BasicCell(createCellView(content), content);
            }
        }
        return cells;
    }

    private void populateWithElements(Cell[][] cells, Type type) throws FileNotFoundException {
        int quantity = 10;
        for (int i = 0; i < quantity; i++) {
            boolean contentPlaced = false;
            int triesLeft = width * height * 10;
            do {
                Cell cell = selectRandomCell(cells);
                if (cell.getContent().getType() == Type.EMPTY) {
                    placeContent(cell, type);
                    contentPlaced = true;
                }
                triesLeft--;
            } while (!contentPlaced && triesLeft > 0);
        }
    }

    private CellView createCellView(Content content) throws FileNotFoundException {
        return new CellView(
                new StackPane(),
                new ImageView(new Image(new FileInputStream("src/main/resources/img/grass.png"))),
                new ImageView(ImageParser.getImage(content.getImagePath()))
        );
    }

    private Cell selectRandomCell(Cell[][] cells) {
        Random random = new Random();
        int x = random.nextInt(width);
        int y = random.nextInt(height);
        return cells[x][y];
    }

    private void placeContent(Cell cell, Type type) throws FileNotFoundException {
        Content content = createContent(type);
        cell.setContent(content);
        cell.getCellView().setContent(ImageParser.getImage(content.getImagePath()));
    }

    private Content createContent(Type type) {
        Content content;
        switch (type) {
            case EMPTY:
                content = new Empty();
                break;
            case WALL:
                content = new Wall();
                break;
            case PIMPEK:
                content = new Empty();
                break;
            case PREDATOR:
                content = new Empty();
                break;
            case FOOD:
                content = new Empty();
                break;
            default:
                content = new Empty();
                break;
        }
        return content;
    }
}
