package model.cellcontent;

import javafx.scene.image.Image;

import java.io.FileNotFoundException;

public interface Content {
    Image getImage() throws FileNotFoundException;
}
