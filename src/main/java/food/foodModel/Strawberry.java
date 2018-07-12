package food.foodModel;

import cell.CellPaths;
import javafx.scene.image.Image;

public class Strawberry implements Food {

    private static final Image IMAGE = CellPaths.STRAWBERRY.getImage();
    private static final FoodGenre genre = FoodGenre.STRAWBERRY;

    @Override
    public int getEnergy() {
        return 40;
    }

    @Override
    public Image getImage() {
        return IMAGE;
    }

    @Override
    public FoodGenre getGenre() {
        return genre;
    }
}
