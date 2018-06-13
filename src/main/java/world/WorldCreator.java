package world;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import model.cell.BasicCell;
import model.cell.Cell;
import model.cell.CellView;
import model.cellcontent.Content;
import model.cellcontent.Empty;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class WorldCreator implements BoardCreator {

    @Override
    public Board create() throws FileNotFoundException {
        int width = 10;
        int height = 10;
        Cell[][] cells = new Cell[width][height];
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                Content content = new Empty();
                CellView cellView = new CellView(
                        new StackPane(),
                        new ImageView(new Image(new FileInputStream("src/main/resources/img/grass.png"))),
                        new ImageView(content.getImage())
                );
                cells[i][j] = new BasicCell(cellView, content);
            }
        }
        return new World(width, height, cells);
    }

}
