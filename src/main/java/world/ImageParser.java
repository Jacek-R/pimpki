package world;

import javafx.scene.image.Image;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class ImageParser {
    public static Image getImage(String path) throws FileNotFoundException {
        return new Image(new FileInputStream(path));
    }
}
