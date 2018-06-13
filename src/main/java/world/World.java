package world;

import javafx.scene.layout.GridPane;
import model.cell.Cell;

public class World implements Board {

    private int width;
    private int height;

    private Cell[][] cells;
    private GridPane gridPane;

    public World(int width, int height, Cell[][] cells) {
        this.width = width;
        this.height = height;
        this.cells = cells;
        createView();
    }

    private void createView() {
        gridPane = new GridPane();
        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                gridPane.add(cells[x][y].getCellView().getStackPane(), x, y);
            }
        }
    }

    @Override
    public int getWidth() {
        return width;
    }

    @Override
    public int getHeight() {
        return height;
    }

    @Override
    public Cell getCellAt(int x, int y) {
        return cells[x][y];
    }

    @Override
    public GridPane getGridPane() {
        return gridPane;
    }
}
