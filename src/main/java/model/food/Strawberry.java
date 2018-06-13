package model.food;

import model.CellPaths;

public class Strawberry implements Food {

    private static final String IMAGE_PATH = CellPaths.STRAWBERRY.getPath();

    @Override
    public int getEnergy() {
        return 40;
    }

    @Override
    public String getImagePath() {
        return IMAGE_PATH;
    }
}
