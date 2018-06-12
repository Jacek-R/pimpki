package model.cell;

import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;

public class CellView {

    private StackPane stackPane;
    private ImageView background;
    private ImageView content;

    public CellView(StackPane stackPane, ImageView background, ImageView content) {
        this.stackPane = stackPane;
        this.background = background;
        this.content = content;
        stackPane.getChildren().add(background);
        stackPane.getChildren().add(content);
    }

    public StackPane getStackPane() {
        return stackPane;
    }

    public void setBackground(ImageView background) {
        this.background = background;
    }

    public void setContent(ImageView content) {
        this.content = content;
    }
}
