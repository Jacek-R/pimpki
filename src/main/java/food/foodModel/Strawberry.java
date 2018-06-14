package food.foodModel;

import cell.CellPaths;

public class Strawberry implements Food {

    private static final String IMAGE_PATH = CellPaths.STRAWBERRY.getPath();
    private static final FoodGenre genre = FoodGenre.STRAWBERRY;

    @Override
    public int getEnergy() {
        return 40;
    }

    @Override
    public String getImagePath() {
        return IMAGE_PATH;
    }

    @Override
    public FoodGenre getGenre() {
        return genre;
    }
}
