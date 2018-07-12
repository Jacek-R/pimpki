package cell.cellcontent;

import cell.CellPaths;
import javafx.scene.image.Image;

public class Empty implements Content {
    private static final Image IMAGE = CellPaths.EMPTY.getImage();

    @Override
    public Image getImage() {
        return IMAGE;
    };
}
