package world;

import javafx.scene.layout.GridPane;
import model.cell.Cell;

public interface Board {

    int getWidth();

    int getHeight();

    Cell getCellAt(int x, int y);

    GridPane getGridPane();

}
