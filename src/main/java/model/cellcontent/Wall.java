package model.cellcontent;

import javafx.scene.image.Image;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class Wall implements Content {
    private static final String IMAGE_PATH = "src/main/resources/img/wall.png";

    private static final Type TYPE = Type.WALL;
    private static final boolean ACCESSIBLE = false;

    @Override
    public Image getImage() throws FileNotFoundException {
        return new Image(new FileInputStream(IMAGE_PATH));
    }

    @Override
    public Type getType() {
        return TYPE;
    }

    @Override
    public boolean isAccessible() {
        return ACCESSIBLE;
    }

}
