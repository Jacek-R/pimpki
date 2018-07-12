package food.foodModel;

import cell.CellPaths;
import javafx.scene.image.Image;

public class Apple implements Food {

    private static final Image IMAGE = CellPaths.APPLE.getImage();
    private static final FoodGenre genre = FoodGenre.APPLE;

    @Override
    public int getEnergy() {
        return 35;
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
