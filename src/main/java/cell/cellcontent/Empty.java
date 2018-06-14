package cell.cellcontent;

import cell.CellPaths;

public class Empty implements Content {
    private static final String IMAGE_PATH = CellPaths.EMPTY.getPath();

    @Override
    public String getImagePath() {
        return IMAGE_PATH;
    };
}
