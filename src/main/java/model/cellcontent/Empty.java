package model.cellcontent;

import javafx.scene.image.Image;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class Empty implements Content {
    private static final String IMAGE_PATH = "src/main/resources/img/empty.png";
    private boolean accessible = true;

    @Override
    public Image getImage() throws FileNotFoundException {
        return new Image(new FileInputStream(IMAGE_PATH));
    }
}
