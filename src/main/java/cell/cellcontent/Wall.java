package cell.cellcontent;

import cell.CellPaths;
import javafx.scene.image.Image;

public class Wall implements Content, Obstacle {
    private static final Image IMAGE = CellPaths.WALL.getImage();

    @Override
    public Image getImage() {
        return IMAGE;
    }

}
