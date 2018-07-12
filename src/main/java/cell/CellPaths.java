package cell;

import javafx.scene.image.Image;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

public enum CellPaths {
    GRASS("src/main/resources/img/grass.png"),
    EMPTY("src/main/resources/img/empty.png"),
    WALL("src/main/resources/img/wall.png"),
    PIMPEK("src/main/resources/img/pimpek.png"),
    PREDATOR("src/main/resources/img/predator.png"),
    APPLE("src/main/resources/img/apple.png"),
    STRAWBERRY("src/main/resources/img/strawberry.png"),
    SAUSAGE("src/main/resources/img/sausage.png"),
    BLOOD("src/main/resources/img/blood.png");

    private Image image;

    CellPaths(String path) {
        try {
            image = new Image(new FileInputStream(path));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public Image getImage() {
        return image;
    }
}
